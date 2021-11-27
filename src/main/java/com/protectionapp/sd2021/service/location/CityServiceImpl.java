package com.protectionapp.sd2021.service.location;


import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.CityResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityServiceImpl extends BaseServiceImpl<CityDTO, CityDomain, CityResult> implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Autowired
    private INeighborhoodDao neighborhoodDao;

    // Lo que no entiendo de la reclacion de uno a muchos es: necesito realmente actualizar la lista de
    // usuarios cuando toco codigo de city domain? Lo mismo va para barrios
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private INeighborhoodService neighborhoodService;

    @Autowired
    private CacheManager cacheManager;

    private final String cacheKey = "api_city_";


    @Override
    @Transactional
    protected CityDTO convertDomainToDto(CityDomain domain) {
        final CityDTO cityDTO = new CityDTO();
        cityDTO.setId(domain.getId());
        cityDTO.setName(domain.getName());
        cityDTO.setDescription(domain.getDescription());

        if (domain.getNeighborhoods() != null) {
            Set<Integer> neighborhood_ids = new HashSet<>();
            domain.getNeighborhoods().forEach(n_domain -> neighborhood_ids.add(n_domain.getId()));

            cityDTO.setNeighborhoods(neighborhood_ids);
        }

        if (domain.getUsers() != null) {
            Set<Integer> users_ids = new HashSet<>();
            domain.getUsers().forEach(u_domain -> users_ids.add(u_domain.getId()));
            cityDTO.setDenuncias(users_ids);
        }

        return cityDTO;
    }

    @Override
    @Transactional
    protected CityDomain convertDtoToDomain(CityDTO dto) {
        final CityDomain cityDomain = new CityDomain();
        cityDomain.setId(dto.getId());
        cityDomain.setName(dto.getName());
        cityDomain.setDescription(dto.getDescription());

        if (dto.getNeighborhoods() != null) {
            cityDomain.setNeighborhoods(getNeighborhoodDomainsFromDTO(dto));
        }

        if (dto.getUsers() != null) {
            cityDomain.setUsers(getUserDomainsFromDTO(dto));
        }

        return cityDomain;
    }

    @Override
    @Transactional
    public CityDTO save(CityDTO dto) {
        final CityDomain cityDomain = convertDtoToDomain(dto);
        final CityDomain city = cityDao.save(cityDomain);
        if (dto.getId() == null) {
            Integer id = city.getId();
            dto.setId(id);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + id, dto);
        }
        return convertDomainToDto(city);
    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_city_'+#id")
    public CityDTO getById(Integer id) {
        final CityDomain city = cityDao.findById(id).get();
        return convertDomainToDto(city);
    }

    @Override
    @Transactional
    public CityResult getAll(Pageable pageable) {
        final List<CityDTO> cities = new ArrayList<>();
        Page<CityDomain> cityDomains = cityDao.findAll(pageable);
        cityDomains.forEach(cityDomain -> cities.add(convertDomainToDto(cityDomain))); //bello lambda

        final CityResult cityResult = new CityResult();
        cityResult.setCities(cities);
        return cityResult;
    }

    public CityResult getllAllNotPaginated() {
        final CityResult result = new CityResult();
        final Iterable<CityDomain> allDomains = cityDao.findAll();
        final List<CityDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(cityDomain -> allDtos.add(convertDomainToDto(cityDomain)));
        }
        result.setCities(allDtos);

        return result;
    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_city_'+#id")
    public CityDTO update(CityDTO dto, Integer id) {
        final CityDomain updatedCityDomain = cityDao.findById(id).get();

        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        if (dto.getNeighborhoods() != null) {
            neighborhoodDomains = getNeighborhoodDomainsFromDTO(dto);
        }

        Set<DenunciaDomain> denunciaDomains = new HashSet<>();
        if (dto.getDenuncias() != null) {
            denunciaDomains = getDenunciaDomainFromDTO(dto);
        }

        Set<UserDomain> userDomains = new HashSet<>();
        if (dto.getUsers() != null) {
            userDomains = getUserDomainsFromDTO(dto);
        }

        updatedCityDomain.updateDomain(
                dto.getName(),
                dto.getDescription(),
                neighborhoodDomains,
                userDomains
        );

        updatedCityDomain.setDenuncias(denunciaDomains);

        cityDao.save(updatedCityDomain);
        return convertDomainToDto(updatedCityDomain);
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_city_'+#id")
    public CityDTO delete(Integer id) {
        final CityDomain deletedDomain = cityDao.findById(id).get();
        final CityDTO deletedDto = convertDomainToDto(deletedDomain);
        cityDao.delete(deletedDomain);
        return deletedDto;
    }

    public Set<NeighborhoodDomain> getNeighborhoodDomainsFromDTO(CityDTO cityDTO) {
        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        cityDTO.getNeighborhoods().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        return neighborhoodDomains;
    }

    public Set<UserDomain> getUserDomainsFromDTO(CityDTO cityDTO) {
        Set<UserDomain> userDomains = new HashSet<>();
        cityDTO.getUsers().forEach(u_id -> userDomains.add(userDao.findById(u_id).get()));
        return userDomains;
    }

    public Set<DenunciaDomain> getDenunciaDomainFromDTO(CityDTO cityDTO) {
        Set<DenunciaDomain> denunciasDomains = new HashSet<>();
        cityDTO.getDenuncias().forEach(d_id -> denunciasDomains.add(denunciaDao.findById(d_id).get()));
        return denunciasDomains;
    }

    @Override
    @Transactional
    public NeighborhoodResult getNeighborhoodByCityId(Integer city_id) {
        final NeighborhoodResult neighborhoodResult = new NeighborhoodResult();
        final List<NeighborhoodDomain> neighborhoodDomainList = neighborhoodDao.findAllByCity_Id(city_id);
        final List<NeighborhoodDTO> neighborhoodDTOList = new ArrayList<>();
        neighborhoodDomainList.forEach(neighborhoodDomain -> neighborhoodDTOList.add(neighborhoodService.getById(neighborhoodDomain.getId())));

        neighborhoodResult.setNeighborhoods(neighborhoodDTOList);
        return neighborhoodResult;
    }
}
