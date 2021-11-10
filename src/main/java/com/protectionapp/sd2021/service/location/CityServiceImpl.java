package com.protectionapp.sd2021.service.location;


import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.CityResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends BaseServiceImpl<CityDTO, CityDomain, CityResult> implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Autowired
    private INeighborhoodDao neighborhoodDao;

    @Autowired
    private IUserDao userDao;

    @Override
    protected CityDTO convertDomainToDto(CityDomain domain) {
        return null;
    }

    @Override
    protected CityDomain convertDtoToDomain(CityDTO dto) {
        return null;
    }

    @Override
    public CityDTO save(CityDTO dto) {
        return null;
    }

    @Override
    public CityDTO getById(Integer id) {
        return null;
    }

    @Override
    public CityResult getAll(Pageable pageable) {
        return null;
    }
}
