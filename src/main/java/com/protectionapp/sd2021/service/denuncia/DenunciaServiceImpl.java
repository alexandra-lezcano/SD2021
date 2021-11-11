package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@Service
public class DenunciaServiceImpl extends BaseServiceImpl<DenunciaDTO, DenunciaDomain, DenunciaResult> implements IDenunciaService {
    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ITipoDenunciaDao tipoDenunciaDao;

    @Override
    protected DenunciaDTO convertDomainToDto(DenunciaDomain domain) {
        final DenunciaDTO denuncia = new DenunciaDTO();
        denuncia.setEstado(domain.getEstado());
        denuncia.setDescripcion(domain.getDescripcion());
        denuncia.setFecha(domain.getFecha());
        denuncia.setCodigo(domain.getCodigo());

        if(domain.getTiposDenuncias() != null){
            Set<Integer> ids = new HashSet<>();
            for (TipoDenunciaDomain t : domain.getTiposDenuncias()){
                ids.add(t.getId());
            }
            denuncia.setTipoIds(ids);
        }

        if(domain.getDetalles() != null){
            Set<Integer> ids = new HashSet<>();
            for(UserDomain u : domain.getDetalles()){
                ids.add(u.getId());
            }
            denuncia.setDetalleIds(ids);
        }

        return denuncia;
    }

    @Override
    protected DenunciaDomain convertDtoToDomain(DenunciaDTO dto) {
        final DenunciaDomain denuncia = new DenunciaDomain();
        denuncia.setCodigo(dto.getCodigo());
        denuncia.setDescripcion(dto.getDescripcion());
        denuncia.setEstado(dto.getEstado());
        denuncia.setFecha(dto.getFecha());

        if (dto.getDetalleIds() != null){
            Set<UserDomain> users = new HashSet<>();
            Set<Integer> ids = dto.getDetalleIds();
            for(Integer i : ids){
                users.add(userDao.findById(i).get());
            }
            denuncia.setDetalles(users);
        }

        if(dto.getTipoIds() != null){
            Set<TipoDenunciaDomain> tipos = new HashSet<>();
            Set<Integer> ids = dto.getTipoIds();
            for(Integer i : ids){
                tipos.add(tipoDenunciaDao.findById(i).get());
            }
            denuncia.setTiposDenuncias(tipos);
        }
        return denuncia;
    }

    @Override
    public DenunciaDTO save(DenunciaDTO dto) {
        //obtiene el bean/domain de la denuncia
        final DenunciaDomain denunciaDomain = convertDtoToDomain(dto);

        //guarda el bean en la DB
        final DenunciaDomain denuncia = denunciaDao.save(denunciaDomain);

        return convertDomainToDto(denuncia);
    }

    @Override
    public DenunciaDTO getById(Integer id) {
        return null;
    }

    @Override
    public DenunciaResult getAll(Pageable pageable) {
        return null;
    }
}
