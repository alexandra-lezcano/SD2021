package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NeighborhoodServiceImpl extends BaseServiceImpl<NeighborhoodDTO, NeighborhoodDomain, NeighborhoodResult> {
    // POST /neighborhoods { name: san pedro, description: big!, city_id: 1}
    // PUT /neighborhoods/1 { name: san pedro, description: peligroso y grande }
    // GET /neighborhoods/1 <---  { name: san pedro, description: peligroso y grande, city_id: 1 }

    @Autowired
    private INeighborhoodDao neighborhoodDao;

    @Autowired
    private ICityDao cityDao;

    @Override
    protected NeighborhoodDTO convertDomainToDto(NeighborhoodDomain domain) {
        final NeighborhoodDTO neighborhoodDTO = new NeighborhoodDTO();

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

        neighborhoodDomain.setName(dto.getName());
        neighborhoodDomain.setDescription(dto.getDescription());

        if (dto.getCity_id() != null) {
            neighborhoodDomain.setCity(cityDao.findById(dto.getCity_id()).get());
        }

        return neighborhoodDomain;
    }

    @Override
    public NeighborhoodDTO save(NeighborhoodDTO dto) {
        final NeighborhoodDomain domain = convertDtoToDomain(dto);
        final NeighborhoodDomain neighborhoodDomain = neighborhoodDao.save(domain);

        return convertDomainToDto(neighborhoodDomain);
    }

    @Override
    public NeighborhoodDTO getById(Integer id) {
        final NeighborhoodDomain domain = neighborhoodDao.findById(id).get();
        return convertDomainToDto(domain);
    }

    @Override
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
        System.out.println("[ITERABLE] ALL DOMAINS " + allDomains.toString());
        final List<NeighborhoodDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(neighborhoodDomain -> allDtos.add(convertDomainToDto(neighborhoodDomain)));
        }
        System.out.println("[List] ALL DTOS " + allDtos.toString());

        result.setNeighborhoods(allDtos);

        System.out.println("[RESULT LIST] ALL DTOS " + result.getNeighborhoods().toString());
        return result;
    }

    @Override
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
    public NeighborhoodDTO delete(Integer id) {
        return null;
    }
}
