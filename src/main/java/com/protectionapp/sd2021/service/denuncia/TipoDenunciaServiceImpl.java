package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
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
public class TipoDenunciaServiceImpl extends BaseServiceImpl<TipoDenunciaDTO, TipoDenunciaDomain, TipoDenunciaResult> implements ITipoDenunciaService {
    @Autowired
    private ITipoDenunciaDao tipoDenunciaDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Override
    protected TipoDenunciaDTO convertDomainToDto(TipoDenunciaDomain domain) {
        final TipoDenunciaDTO dto = new TipoDenunciaDTO();
        dto.setId(domain.getId());
        dto.setTitulo(domain.getTitulo());
        dto.setDescripcion(domain.getDescripcion());

        if (domain.getDenuncias() != null) {
            Set<Integer> denunciasIds = new HashSet<>();
            domain.getDenuncias().forEach(denunciaDomain -> denunciasIds.add(denunciaDomain.getId()));
        }
        return dto;
    }

    @Override
    protected TipoDenunciaDomain convertDtoToDomain(TipoDenunciaDTO dto) {
        final TipoDenunciaDomain domain = new TipoDenunciaDomain();
        domain.setId(dto.getId());
        domain.setTitulo(dto.getTitulo());
        domain.setDescripcion(dto.getDescripcion());

        if (dto.getDenunciaIds() != null) {
            Set<DenunciaDomain> denuncias = new HashSet<>();
            dto.getDenunciaIds().forEach(denunciaId -> denuncias.add(denunciaDao.findById(denunciaId).get()));
        }
        return domain;
    }

    @Override
    public TipoDenunciaDTO save(TipoDenunciaDTO dto) {
        final TipoDenunciaDomain tipoDenunciaDomain = convertDtoToDomain(dto);
        final TipoDenunciaDomain domain = tipoDenunciaDao.save(tipoDenunciaDomain);

        return convertDomainToDto(domain);
    }

    @Override
    public TipoDenunciaDTO getById(Integer id) {
        final TipoDenunciaDomain tipo = tipoDenunciaDao.findById(id).get();
        return convertDomainToDto(tipo);
    }

    @Override
    public TipoDenunciaResult getAll(Pageable pageable) {
        final List<TipoDenunciaDTO> tipos = new ArrayList<>();
        Page<TipoDenunciaDomain> resutls = tipoDenunciaDao.findAll(pageable);
        resutls.forEach(tipo-> tipos.add(convertDomainToDto(tipo)));
        final TipoDenunciaResult result = new TipoDenunciaResult();
        result.setTipoDenunciaList(tipos);
        return result;
    }

    @Override
    public TipoDenunciaDTO update(TipoDenunciaDTO dto, Integer id) {
        final TipoDenunciaDomain updated = tipoDenunciaDao.findById(id).get();
        if(dto.getTitulo() != null){
            updated.setTitulo(dto.getTitulo());
        }
        if(dto.getDescripcion() != null){
            updated.setDescripcion(dto.getDescripcion());
        }

        if(dto.getDenunciaIds() != null){
            updated.setDenuncias(getDenunciasFromDto(dto));
        }
        return convertDomainToDto(updated);
    }

    private Set<DenunciaDomain> getDenunciasFromDto(TipoDenunciaDTO dto) {
        Set<DenunciaDomain> ret = new HashSet<>();
        dto.getDenunciaIds().forEach(id->ret.add(denunciaDao.findById(id).get()));
        return ret;
    }

    @Override
    public TipoDenunciaDTO delete(Integer id) {
        final TipoDenunciaDomain deletedDomain = tipoDenunciaDao.findById(id).get();
        final TipoDenunciaDTO deletedDto = convertDomainToDto(deletedDomain);
        tipoDenunciaDao.delete(deletedDomain);
        return deletedDto;
    }
}
