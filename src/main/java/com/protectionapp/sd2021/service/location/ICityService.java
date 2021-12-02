package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.CityResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface ICityService extends IBaseService<CityDTO, CityResult> {
   NeighborhoodResult getNeighborhoodByCityId(Integer city_id);
   void addCityToUser(UserDTO dto, UserDomain domain);
}
