package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDTO, UserResult> {
    void addUsersToInvestigacion(InvestigacionDTO dto, InvestigacionDomain domain);

    public UserDomain getDomainById(Integer id) ;
}
