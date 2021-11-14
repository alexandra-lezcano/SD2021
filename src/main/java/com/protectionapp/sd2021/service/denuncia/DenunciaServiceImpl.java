package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        denuncia.setId(domain.getId());
        denuncia.setEstado(domain.getEstado());
        denuncia.setDescripcion(domain.getDescripcion());
        denuncia.setFecha(domain.getFecha());
        denuncia.setCodigo(domain.getCodigo());

        if (domain.getTiposDenuncias() != null) {
            Set<Integer> ids = new HashSet<>();
            for (TipoDenunciaDomain t : domain.getTiposDenuncias()) {
                ids.add(t.getId());
            }
            denuncia.setTipoIds(ids);
        }

      /*  if(domain.getDetalles() != null){
            Set<Integer> ids = new HashSet<>();
            for(UserDomain u : domain.getDetalles()){
                ids.add(u.getId());
            }
            denuncia.setDetalleIds(ids);
        }*/

        denuncia.setUserId(domain.getUser().getId());

        return denuncia;
    }

    @Override
    protected DenunciaDomain convertDtoToDomain(DenunciaDTO dto) {
        final DenunciaDomain denuncia = new DenunciaDomain();
        denuncia.setID(dto.getId());
        denuncia.setCodigo(dto.getCodigo());
        denuncia.setDescripcion(dto.getDescripcion());
        denuncia.setEstado(dto.getEstado());
        denuncia.setFecha(dto.getFecha());
/*
        if (dto.getDetalleIds() != null){
            Set<UserDomain> users = new HashSet<>();
            Set<Integer> ids = dto.getDetalleIds();
            for(Integer i : ids){
                users.add(userDao.findById(i).get());
            }
            denuncia.setDetalles(users);
        }*/

        if (dto.getTipoIds() != null) {
            Set<TipoDenunciaDomain> tipos = new HashSet<>();
            Set<Integer> ids = dto.getTipoIds();
            for (Integer i : ids) {
                tipos.add(tipoDenunciaDao.findById(i).get());
            }
            denuncia.setTiposDenuncias(tipos);
        }
        denuncia.setUser(userDao.findById(dto.getUserId()).get());

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
    @Transactional
    public DenunciaDTO getById(Integer id) throws DenunciaNotFoundException {
        final DenunciaDomain denuncia = denunciaDao.findById(id).get();
        return convertDomainToDto(denuncia);
    }

    @Override
    @Transactional
    public DenunciaResult getAll(Pageable pageable) throws DenunciaNotFoundException {
        final List<DenunciaDTO> denuncias = new ArrayList<>();
        Page<DenunciaDomain> results = denunciaDao.findAll(pageable);
        results.forEach(denuncia -> denuncias.add(convertDomainToDto(denuncia)));
        final DenunciaResult denunciaResult = new DenunciaResult();
        denunciaResult.setDenuncias(denuncias);
        return denunciaResult;
    }

    @Override
    public DenunciaDTO update(DenunciaDTO dto, Integer id) {
        final DenunciaDomain updated = denunciaDao.findById(id).get();
        if (dto.getTipoIds() != null) {
            updated.setTiposDenuncias(getTipoDenunciaDomaniFromDTO(dto));
        }
     /*   if(dto.getDetalleIds() != null){
            updated.setDetalles(getDetallesFromDTO(dto));
        }*/
        updated.update(
                dto.getFecha(),
                dto.getDescripcion(),
                dto.getEstado(),
                dto.getCodigo()
        );
        denunciaDao.save(updated);
        return convertDomainToDto(updated);
    }

    @Override
    public DenunciaDTO delete(Integer id) {
        return null;
    }

    public Set<TipoDenunciaDomain> getTipoDenunciaDomaniFromDTO(DenunciaDTO dto) {
        Set<TipoDenunciaDomain> domains = new HashSet<>();
        dto.getTipoIds().forEach(id -> domains.add(tipoDenunciaDao.findById(id).get()));
        return domains;
    }

    public Set<UserDomain> getDetallesFromDTO(DenunciaDTO dto) {
        Set<UserDomain> domains = new HashSet<>();
        //   dto.getDetalleIds().forEach(id->domains.add(userDao.findById(id).get()));
        return domains;
    }
}
