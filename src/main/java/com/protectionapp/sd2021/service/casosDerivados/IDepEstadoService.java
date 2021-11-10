package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dao.IDepEstadoDao;
import com.protectionapp.sd2021.dao.IUserDao;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

public interface IDepEstadoService extends IBaseService<DepEstadoDTO, DepEstadoResult> {

    @Autowired
    private IDepEstadoDao DepEstadoDao;

}
