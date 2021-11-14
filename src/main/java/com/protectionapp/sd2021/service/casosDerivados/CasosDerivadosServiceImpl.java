package com.protectionapp.sd2021.service.casosDerivados;


import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.casosDerivados.IDepEstadoDao;
import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CasosDerivadosServiceImpl extends BaseServiceImpl<CasosDerivadosDTO, CasosDerivadosDomain, CasosDerivadosResult> implements ICasosDerivadosService {

    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private IUserDao userDao;


    @Autowired
    private IDepEstadoDao depEstadoDao;

    @Override
    protected CasosDerivadosDTO convertDomainToDto(CasosDerivadosDomain domain) {
        final CasosDerivadosDTO casosDerivados = new CasosDerivadosDTO();
        casosDerivados.setDate(domain.getDate());
        casosDerivados.setDescription(domain.getDescription());

       // casosDerivados.setDenuncia(domain.getDenuncia().getId());

        //Guanrdo lista de users


       // Set<Integer> depEstado = new HashSet<>();
/*
        for (DepEstadoDomain d : domain.getDepEstado()) {
            depEstado.add(d.getId());
        }
        casosDerivados.setDependencias_ids(depEstado);
*/

        return casosDerivados;
    }

    @Override
    protected CasosDerivadosDomain convertDtoToDomain(CasosDerivadosDTO dto) {
      final CasosDerivadosDomain domain = new CasosDerivadosDomain();
      domain.setId(dto.getId());
      domain.setDate(dto.getDate());
      domain.setDescription(dto.getDescription());

      //Guardo lista de denuncias
        Set<DenunciaDomain> denunciaDomains = new HashSet<>();
        //Guanrdo lista de users
        Set<DepEstadoDomain> depEstadoDomains = new HashSet<>();

        Set<Integer>depEstado_ids = dto.getDependencias_ids();
        ;
        for (Integer nId :depEstado_ids) {
            depEstadoDomains.add(depEstadoDao.findById(nId).get());
        }

        return domain;

    }

    @Override
    public CasosDerivadosResult getAll(Pageable pageable) {

        final List<CasosDerivadosDTO> cD = new ArrayList<>();
        Page<CasosDerivadosDomain> cDresults = casosDerivadosDao.findAll(pageable);
        cDresults.forEach(casoDerivado -> cD.add(convertDomainToDto(casoDerivado)));
        final CasosDerivadosResult cDResult = new CasosDerivadosResult();
        cDResult.setCasosDerivados(cD);

      return  cDResult;
    }

    @Override
    public CasosDerivadosDTO update(CasosDerivadosDTO dto, Integer id) {

if(casosDerivadosDao.findById(id)!=null ) {
    final CasosDerivadosDomain casoDerivado = casosDerivadosDao.findById(id).get();

    casoDerivado.setDate(dto.getDate());
    casoDerivado.setDescription((dto.getDescription()));

//cambiamos lista de  users
    Set<UserDomain> userDomains = new HashSet<>();

    final CasosDerivadosDomain nuevo = casosDerivadosDao.save(casoDerivado);
    return convertDomainToDto(nuevo);
}
return null;



    }

    @Override
    public CasosDerivadosDTO delete(Integer id) {
        final  CasosDerivadosDTO deleted=update(null,id);
        return  deleted;
    }


    @Override
    public CasosDerivadosDTO save(CasosDerivadosDTO dto) {

        final CasosDerivadosDomain domain = convertDtoToDomain(dto);
        final CasosDerivadosDomain CD = casosDerivadosDao.save(domain);
        return convertDomainToDto(CD);

    }

    @Override
    public CasosDerivadosDTO getById(Integer id) {

        final CasosDerivadosDomain cD = casosDerivadosDao.findById(id).get();
        return convertDomainToDto(cD);

    }



    @Override
    public Page<CasosDerivadosDomain> findAll(Pageable pageable) {
        return null;

    }
}
