package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface INeighborhoodService extends IBaseService<NeighborhoodDTO, NeighborhoodResult> {
    void addNeighborhoodToUser(UserDTO dto, UserDomain domain);
}
