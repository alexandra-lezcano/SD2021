package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDTO, UserResult> {
void rollbackPropagationNever(UserDTO userDTO, CityDTO cityDTO);
void methodCallPropagationNever(UserDTO userDTO, CityDTO cityDTO);

}
