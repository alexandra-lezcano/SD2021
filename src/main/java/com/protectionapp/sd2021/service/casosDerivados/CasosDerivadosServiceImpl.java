package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dao.IDenunciaDao;
import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CasosDerivadosServiceImpl extends BaseServiceImpl<CasosDerivadosDTO, CasosDerivadosDomain, CasosDerivadosResult> implements ICasosDerivadosService {

    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;

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
      final CasosDerivadosDomain domain = new CasosDerivadosDomain();
      domain.setId(dto.getId());
      domain.setDate(dto.getDate());
      domain.setDescription(dto.getDescription());

      //Guardo lista de denuncias
        Set<DenunciaDomain> denunciaDomains = new HashSet<>();

        Set<Integer>denuncias_ids = dto.getDenuncia_ids();
        for (Integer nId : denuncias_ids) {
            denunciaDomains.add(denunciaDao.findById(nId).get());
        }
        domain.setDenuncia(denunciaDomains);

        //Guanrdo lista de users

        Set<UserDomain> userDomains = new HashSet<>();

        Set<Integer>users_ids = dto.getUser_ids();
        for (Integer nId : users_ids) {
          userDomains.add(userDao.findById(nId).get());
        }
        domain.setUsers(userDomains);

        return domain;

    }

    @Override
    public CasosDerivadosResult getAll(Pageable pageable) {


        return null;
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
