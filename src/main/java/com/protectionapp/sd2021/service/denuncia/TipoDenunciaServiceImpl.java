package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
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

        Set<Integer> denunciasIds = new HashSet<>();
        if (domain.getDenuncias() != null) {
            domain.getDenuncias().forEach(denunciaDomain -> denunciasIds.add(denunciaDomain.getId()));
        }
        dto.setDenunciaIds(denunciasIds);
        return dto;
    }

    @Override
    protected TipoDenunciaDomain convertDtoToDomain(TipoDenunciaDTO dto) {
        final TipoDenunciaDomain domain = new TipoDenunciaDomain();
        domain.setId(dto.getId());
        domain.setTitulo(dto.getTitulo());
        domain.setDescripcion(dto.getDescripcion());

        Set<DenunciaDomain> denuncias = new HashSet<>();
        if (dto.getDenunciaIds() != null) {
            dto.getDenunciaIds().forEach(denunciaId -> denuncias.add(denunciaDao.findById(denunciaId).get()));
        }
        domain.setDenuncias(denuncias);
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
        final TipoDenunciaDomain tipoDenunciaDomain = tipoDenunciaDao.findById(id).get();
        return convertDomainToDto(tipoDenunciaDomain);
    }

    @Override
    public TipoDenunciaResult getAll(Pageable pageable) {
        final List<TipoDenunciaDTO> tipoDenunciaDTOS = new ArrayList<>();
        Page<TipoDenunciaDomain> tipoDenunciaDomains = tipoDenunciaDao.findAll(pageable);

        tipoDenunciaDomains.forEach(tipoDenunciaDomain -> tipoDenunciaDTOS.add(convertDomainToDto(tipoDenunciaDomain)));
        final TipoDenunciaResult result = new TipoDenunciaResult();
        result.setTipoDenunciaList(tipoDenunciaDTOS);

        return result;
    }

    public TipoDenunciaResult getAll() {
        final List<TipoDenunciaDTO> tipoDenunciaDTOS = new ArrayList<>();
        Iterable<TipoDenunciaDomain> tipoDenunciaDomains = tipoDenunciaDao.findAll();

        tipoDenunciaDomains.forEach(tipoDenunciaDomain -> tipoDenunciaDTOS.add(convertDomainToDto(tipoDenunciaDomain)));
        final TipoDenunciaResult result = new TipoDenunciaResult();
        result.setTipoDenunciaList(tipoDenunciaDTOS);

        return result;
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
