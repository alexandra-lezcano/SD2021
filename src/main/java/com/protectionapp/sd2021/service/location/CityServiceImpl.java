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
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ModelMapper modelMapper;

    // todo como evitar tanta repeticion de codigo...mapper no me funciono.
    @Override
    @Transactional
    protected CityDTO convertDomainToDto(CityDomain domain) {
        final CityDTO cityDTO = new CityDTO();
        cityDTO.setName(domain.getName());
        cityDTO.setDescription(domain.getDescription());

        if (domain.getNeighborhoods() != null) {
            Set<Integer> neighborhood_ids = new HashSet<>();
            domain.getNeighborhoods().forEach(n_domain -> neighborhood_ids.add(n_domain.getId()));

            cityDTO.setNeighborhoods(neighborhood_ids);
        }

        if (domain.getDenuncias() != null) {
            Set<Integer> denuncias_ids = new HashSet<>();
            domain.getDenuncias().forEach(d_domain -> denuncias_ids.add(d_domain.getId()));
            cityDTO.setDenuncias(denuncias_ids);
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

        cityDomain.setName(dto.getName());
        cityDomain.setDescription(dto.getDescription());

        if (dto.getNeighborhoods() != null) {
            cityDomain.setNeighborhoods(getNeighborhoodsIfExists(dto));
        }

        if (dto.getDenuncias() != null) {
            cityDomain.setDenuncias(getDenunciasIfExists(dto));
        }

        if (dto.getUsers() != null) {
            cityDomain.setUsers(getUsersIfExists(dto));
        }

        return cityDomain;
    }

    @Override
    @Transactional
    public CityDTO save(CityDTO dto) {
        final CityDomain cityDomain = convertDtoToDomain(dto);
        final CityDomain city = cityDao.save(cityDomain);
        return convertDomainToDto(city);
    }

    // todo THROW IF ID DOESNT EXIST
    @Override
    @Transactional
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

    @Override
    @Transactional
    public CityDTO update(CityDTO dto, Integer id) {
        final CityDomain updatedCityDomain = cityDao.findById(id).get();

        Set<NeighborhoodDomain> neighborhoodDomains = null;
        if (dto.getNeighborhoods() != null) {
            neighborhoodDomains = getNeighborhoodsIfExists(dto);
        }

        Set<DenunciaDomain> denunciaDomains = null;
        if (dto.getDenuncias() != null) {
            denunciaDomains = getDenunciasIfExists(dto);
        }

        Set<UserDomain> userDomains = null;
        if (dto.getUsers() != null) {
            userDomains = getUsersIfExists(dto);
        }
        updatedCityDomain.updateDomain(
                dto.getName(),
                dto.getDescription(),
                neighborhoodDomains,
                userDomains,
                denunciaDomains
        );

        cityDao.save(updatedCityDomain);
        return convertDomainToDto(updatedCityDomain);
    }

    @Override
    @Transactional
    public CityDTO delete(Integer id) {
        return null;
    }

    public Set<NeighborhoodDomain> getNeighborhoodsIfExists(CityDTO cityDTO) {
        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        cityDTO.getNeighborhoods().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        return neighborhoodDomains;
    }

    public Set<UserDomain> getUsersIfExists(CityDTO cityDTO) {
        Set<UserDomain> userDomains = new HashSet<>();
        cityDTO.getUsers().forEach(u_id -> userDomains.add(userDao.findById(u_id).get()));
        return userDomains;
    }

    public Set<DenunciaDomain> getDenunciasIfExists(CityDTO cityDTO) {
        Set<DenunciaDomain> denunciasDomains = new HashSet<>();
        cityDTO.getDenuncias().forEach(d_id -> denunciasDomains.add(denunciaDao.findById(d_id).get()));
        return denunciasDomains;
    }

}
