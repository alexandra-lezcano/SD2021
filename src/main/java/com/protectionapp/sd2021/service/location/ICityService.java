package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.CityResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface ICityService extends IBaseService<CityDTO, CityResult> {
    public NeighborhoodResult getNeighborhoodByCityId(Integer city_id);
}
