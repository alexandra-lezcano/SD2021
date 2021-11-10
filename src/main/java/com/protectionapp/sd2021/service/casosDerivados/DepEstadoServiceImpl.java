package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.data.domain.Pageable;

public class DepEstadoServiceImpl extends BaseServiceImpl<DepEstadoDTO, DepEstadoDomain, DepEstadoResult> implements IDepEstadoService {


    @Override
    protected DepEstadoDTO convertDomainToDto(DepEstadoDomain domain) {
        return null;
    }

    @Override
    protected DepEstadoDomain convertDtoToDomain(DepEstadoDTO dto) {
        return null;
    }

    @Override
    public DepEstadoDTO save(DepEstadoDTO dto) {
        return null;
    }

    @Override
    public DepEstadoDTO getById(Integer id) {
        return null;
    }

    @Override
    public DepEstadoResult getAll(Pageable pageable) {
        return null;
    }
}
