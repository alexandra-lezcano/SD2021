package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        return null;
    }

    @Override
    public TipoDenunciaResult getAll(Pageable pageable) {
        return null;
    }

    @Override
    public TipoDenunciaDTO update(TipoDenunciaDTO dto, Integer id) {
        return null;
    }

    @Override
    public TipoDenunciaDTO delete(Integer id) {
        return null;
    }
}
