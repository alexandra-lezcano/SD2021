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
        casosDerivados.setId(domain.getId());
        casosDerivados.setDate(domain.getDate());
        casosDerivados.setDescription(domain.getDescription());

        if(domain.getTrabajador_social()!=null) {
            casosDerivados.setUser(domain.getTrabajador_social().getId());
        }


        //guardo los ids de las denuncias
        if(domain.getDenuncia()!=null) {
            Set<Integer> denuncias_ids = new HashSet<>();
            Set<DenunciaDomain> denuncias = domain.getDenuncia();
            for (DenunciaDomain d : denuncias) {
                denuncias_ids.add(d.getId());
            }
            casosDerivados.setDenuncia_ids(denuncias_ids);
        }

        //guardo las depEstado de mi caso derivado
        if(domain.getDependencia_estado()!=null){
            Set<Integer> dependenciasIds= new HashSet<Integer>();
            domain.getDependencia_estado().forEach(d->dependenciasIds.add(d.getId()));
            casosDerivados.setDependencias_ids(dependenciasIds);
        }


        return casosDerivados;
    }

    @Override
    protected CasosDerivadosDomain convertDtoToDomain(CasosDerivadosDTO dto) {
      final CasosDerivadosDomain domain = new CasosDerivadosDomain();
      domain.setId(dto.getId());
      domain.setDate(dto.getDate());
      domain.setDescription(dto.getDescription());

      if(dto.getDenuncia_ids()!=null) {
          domain.setTrabajador_social(userDao.findById(dto.getUser_id()).get());
      }

      //ManyToMany
        if(dto.getDependencias_ids()!=null) {
            Set<DepEstadoDomain> depEstadoDomains = new HashSet<>();
            Set<Integer> depEstado_ids = dto.getDependencias_ids();

            for (Integer nId : depEstado_ids) {
                depEstadoDomains.add(depEstadoDao.findById(nId).get());
            }
            domain.setDependencia_estado(depEstadoDomains);

        }

        return domain;

    }
//com
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
