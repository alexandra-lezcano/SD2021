package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.service.base.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICasosDerivadosService extends IBaseService<CasosDerivadosDTO, CasosDerivadosResult> {
    Page<CasosDerivadosDomain> findAll(Pageable pageable);
}
