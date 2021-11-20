package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.IDenunciaEstadoDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaEstadoDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.dto.denuncia.*;
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
public class DenunciaEstadoServiceImpl extends BaseServiceImpl<DenunciaEstadoDTO, DenunciaEstadoDomain, DenunciaEstadoResult> implements IDenunciaEstadoService {
    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private IDenunciaEstadoDao estadoDao;

    @Override
    protected DenunciaEstadoDTO convertDomainToDto(DenunciaEstadoDomain domain) {
        final DenunciaEstadoDTO dto = new DenunciaEstadoDTO();
        dto.setNombre(domain.getNombre());
        dto.setId(domain.getId());
        if (domain.getDenuncias() != null) {
            Set<Integer> denunciasIds = new HashSet<>();
            domain.getDenuncias().forEach(denunciaDomain -> denunciasIds.add(denunciaDomain.getId()));
            dto.setDenuncias_ids(denunciasIds);
        }
        return dto;
    }

    @Override
    protected DenunciaEstadoDomain convertDtoToDomain(DenunciaEstadoDTO dto) {
        final DenunciaEstadoDomain domain = new DenunciaEstadoDomain();
        domain.setId(dto.getId());
        domain.setNombre(dto.getNombre());

        if (dto.getDenuncias_ids() != null) {
            Set<DenunciaDomain> denunciaDomains = new HashSet<>();
            dto.getDenuncias_ids().forEach(denunciaId -> denunciaDomains.add(denunciaDao.findById(denunciaId).get()));
            domain.setDenuncias(denunciaDomains);
        }
        return domain;
    }

    @Override
    public DenunciaEstadoDTO save(DenunciaEstadoDTO dto) {
        final DenunciaEstadoDomain estadoDomain = convertDtoToDomain(dto);
        final DenunciaEstadoDomain estado = estadoDao.save(estadoDomain);
        return convertDomainToDto(estado);
    }

    @Override
    public DenunciaEstadoDTO getById(Integer id) {
        final DenunciaEstadoDomain estado = estadoDao.findById(id).get();
        return convertDomainToDto(estado);
    }

    @Override
    public DenunciaEstadoResult getAll(Pageable pageable) {
        final List<DenunciaEstadoDTO> estados = new ArrayList<>();
        Page<DenunciaEstadoDomain> resutls = estadoDao.findAll(pageable);
        resutls.forEach(estado -> estados.add(convertDomainToDto(estado)));

        final DenunciaEstadoResult result = new DenunciaEstadoResult();
        result.setDenunciaEstadoList(estados);
        return result;
    }

    public DenunciaEstadoResult getAllNotPaginated(){
        final DenunciaEstadoResult result = new DenunciaEstadoResult();
        final Iterable<DenunciaEstadoDomain> all = estadoDao.findAll();
        System.out.println("[ITERABLE] ALL DOMAINS " + all.toString());
        final List<DenunciaEstadoDTO> allDtos = new ArrayList<>();
        if(all != null){
            all.forEach(denunciaEstadoDomain -> allDtos.add(convertDomainToDto(denunciaEstadoDomain)));
        }
        System.out.println("[List] ALL DTOS " + allDtos.toString());

        result.setDenunciaEstadoList(allDtos);

        System.out.println("[RESULT LIST] ALL DTOS " + result.getDenunciaEstadoList().toString());
        return result;
    }

    @Override
    public DenunciaEstadoDTO update(DenunciaEstadoDTO dto, Integer id) {
        final DenunciaEstadoDomain updated = estadoDao.findById(id).get();
        updated.setId(dto.getId());
        updated.setNombre(dto.getNombre());

        if (dto.getDenuncias_ids() != null) {
            Set<DenunciaDomain> denunciaDomains = new HashSet<>();
            dto.getDenuncias_ids().forEach(denunciaId -> denunciaDomains.add(denunciaDao.findById(denunciaId).get()));
            updated.setDenuncias(denunciaDomains);
        }

        estadoDao.save(updated);
        return convertDomainToDto(updated);
    }

    @Override
    public DenunciaEstadoDTO delete(Integer id) {
        final DenunciaEstadoDomain deletedDomain = estadoDao.findById(id).get();
        final DenunciaEstadoDTO deletedDto = convertDomainToDto(deletedDomain);
        estadoDao.delete(deletedDomain);
        return deletedDto;
    }
}
