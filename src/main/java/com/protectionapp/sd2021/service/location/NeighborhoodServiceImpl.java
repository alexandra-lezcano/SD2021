package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.service.casosDerivados.DepEstadoServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NeighborhoodServiceImpl extends BaseServiceImpl<NeighborhoodDTO, NeighborhoodDomain, NeighborhoodResult> implements INeighborhoodService {
    @Autowired
    private INeighborhoodDao neighborhoodDao;

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private CacheManager cacheManager;

    private static final Logger logger = LogManager.getLogger(DepEstadoServiceImpl.class);

    private String cacheKey = "api_neighborhood_";

    @Autowired
    private Configurations configurations;
    @Override
    protected NeighborhoodDTO convertDomainToDto(NeighborhoodDomain domain) {
        final NeighborhoodDTO neighborhoodDTO = new NeighborhoodDTO();
        neighborhoodDTO.setId(domain.getId());
        neighborhoodDTO.setName(domain.getName());
        neighborhoodDTO.setDescription(domain.getDescription());

        if (domain.getCity() != null) {
            neighborhoodDTO.setCity_id(domain.getCity().getId());
        }
        return neighborhoodDTO;
    }

    @Override
    protected NeighborhoodDomain convertDtoToDomain(NeighborhoodDTO dto) {
        final NeighborhoodDomain neighborhoodDomain = new NeighborhoodDomain();
        neighborhoodDomain.setId(dto.getId());
        neighborhoodDomain.setName(dto.getName());
        neighborhoodDomain.setDescription(dto.getDescription());

        if (dto.getCity_id() != null) {
            neighborhoodDomain.setCity(cityDao.findById(dto.getCity_id()).get());
        }

        return neighborhoodDomain;
    }

    @Override
    @Transactional
    public NeighborhoodDTO save(NeighborhoodDTO dto) {
        final NeighborhoodDomain domain = convertDtoToDomain(dto);
        final NeighborhoodDomain neighborhoodDomain = neighborhoodDao.save(domain);
        if (dto.getId() == null) {
            Integer id = neighborhoodDomain.getId();
            dto.setId(id);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + id, dto);
        }
        return convertDomainToDto(neighborhoodDomain);
    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_neighborhood_'+#id")
    public NeighborhoodDTO getById(Integer id) {
        if(configurations.isTransactionTest()){
            logger.info("TEST: Aparecera un error pero no hace rollback");
            throw new RuntimeException("Error para probar el test fallido");
        }
        final NeighborhoodDomain domain = neighborhoodDao.findById(id).get();
        return convertDomainToDto(domain);
    }

    @Override
    @Transactional
    public NeighborhoodResult getAll(Pageable pageable) {
        final List<NeighborhoodDTO> n_dtos = new ArrayList<>();
        Page<NeighborhoodDomain> n_domains = neighborhoodDao.findAll(pageable);
        n_domains.forEach(n_domain -> n_dtos.add(convertDomainToDto(n_domain)));

        final NeighborhoodResult n_result = new NeighborhoodResult();
        n_result.setNeighborhoods(n_dtos);
        return n_result;
    }

    public NeighborhoodResult getllAllNotPaginated() {
        final NeighborhoodResult result = new NeighborhoodResult();
        final Iterable<NeighborhoodDomain> allDomains = neighborhoodDao.findAll();
        final List<NeighborhoodDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(neighborhoodDomain -> allDtos.add(convertDomainToDto(neighborhoodDomain)));
        }
        result.setNeighborhoods(allDtos);
        return result;
    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_neighborhood_'+#id")
    public NeighborhoodDTO update(NeighborhoodDTO dto, Integer id) {
        final NeighborhoodDomain updatedDomain = neighborhoodDao.findById(id).get();
        updatedDomain.setName(dto.getName());
        updatedDomain.setDescription(dto.getDescription());

        if (dto.getCity_id() != null) {
            updatedDomain.setCity(cityDao.findById(dto.getCity_id()).get());
        }
        neighborhoodDao.save(updatedDomain);
        return convertDomainToDto(updatedDomain);
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_neighborhood_'+#id")
    public NeighborhoodDTO delete(Integer id) {
        final NeighborhoodDomain deletedDomain = neighborhoodDao.findById(id).get();
        final NeighborhoodDTO deletedDto = convertDomainToDto(deletedDomain);
        neighborhoodDao.delete(deletedDomain);
        return deletedDto;
    }

    @Override
    @Transactional
    public void addNeighborhoodToUser(UserDTO dto, UserDomain domain) {
        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        if (dto.getNeighborhoodIds() != null) {
            dto.getNeighborhoodIds().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        }
        domain.setNeighborhoods(neighborhoodDomains);
    }
}
