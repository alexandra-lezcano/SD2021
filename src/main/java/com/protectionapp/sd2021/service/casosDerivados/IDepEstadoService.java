package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.service.base.IBaseService;
import org.springframework.transaction.annotation.Transactional;

public interface IDepEstadoService extends IBaseService<DepEstadoDTO, DepEstadoResult> {
    public void testIndDirectRequired(int id);
    public void testIndDirectRequiredNT(int id);





}
