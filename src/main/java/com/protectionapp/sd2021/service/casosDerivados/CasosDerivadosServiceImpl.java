package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dao.IDenunciaDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CasosDerivadosServiceImpl extends BaseServiceImpl<CasosDerivadosDTO, CasosDerivadosDomain, CasosDerivadosResult> implements ICasosDerivadosService {

    @Autowired
    private ICasosDerivadosService casosDerivadosDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private IUserDao userDao;

    @Override
    protected CasosDerivadosDTO convertDomainToDto(CasosDerivadosDomain domain) {
        final CasosDerivadosDTO casosDerivados = new CasosDerivadosDTO();
        casosDerivados.setDate(domain.getDate());
        casosDerivados.setDescription(domain.getDescription());
        return casosDerivados;
    }

    @Override
    protected CasosDerivadosDomain convertDtoToDomain(CasosDerivadosDTO dto) {
      return null;

    }

    @Override
    public CasosDerivadosResult getAll(Pageable pageable) {
        return null;
    }


    @Override
    public CasosDerivadosDTO save(CasosDerivadosDTO dto) {
        return null;
    }

    @Override
    public CasosDerivadosDTO getById(Integer id) {
        return null;
    }



    @Override
    public Page<CasosDerivadosDomain> findAll(Pageable pageable) {


        return null;


    }
}
