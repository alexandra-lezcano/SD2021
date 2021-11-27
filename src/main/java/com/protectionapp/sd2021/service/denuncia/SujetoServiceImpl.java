package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ISujetoDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoSujetoDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;
import com.protectionapp.sd2021.dto.denuncia.SujetoResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SujetoServiceImpl extends BaseServiceImpl<SujetoDto, SujetoDomain, SujetoResult> implements ISujetoService {
    @Autowired
    private ITipoSujetoDao tipoSujetoDao;
    @Autowired
    private IDenunciaDao denunciaDao;
    @Autowired
    private ISujetoDao sujetoDao;
    @Autowired
    private CacheManager cacheManager;

    private final String cacheKey = "api_sujeto_";

    @Override
    protected SujetoDto convertDomainToDto(SujetoDomain domain) {
        final SujetoDto dto = new SujetoDto();
        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        dto.setCi(domain.getCi());
        dto.setTelefono(domain.getTelefono());
        dto.setDireccion(domain.getDireccion());
        dto.setCorreo(domain.getCorreo());
        Set<Integer> denunciasIds = new HashSet<>();
        if (domain.getDenuncias() != null) {
            domain.getDenuncias().forEach(denunciaDomain -> denunciasIds.add(denunciaDomain.getId()));
        }
        dto.setDenuncias(denunciasIds);
        dto.setTipo_id(domain.getTipo().getId());
        return dto;
    }

    private Set<DenunciaDomain> getDenunciasFromDto(SujetoDto dto) {
        Set<DenunciaDomain> ret = new HashSet<>();
        dto.getDenuncias().forEach(id -> ret.add(denunciaDao.findById(id).get()));
        return ret;
    }

    @Override
    protected SujetoDomain convertDtoToDomain(SujetoDto dto) {
        final SujetoDomain domain = new SujetoDomain();
        domain.setId(dto.getId());
        domain.setCi(dto.getCi());
        domain.setNombre(dto.getNombre());
        domain.setTelefono(dto.getTelefono());
        domain.setCorreo(dto.getCorreo());
        domain.setDireccion(dto.getDireccion());
        Set<DenunciaDomain> denuncias = new HashSet<>();
        if (dto.getDenuncias() != null) {
            dto.getDenuncias().forEach(denunciaId -> denuncias.add(denunciaDao.findById(denunciaId).get()));
        }

        domain.setDenuncias(denuncias);
        domain.setTipo(tipoSujetoDao.findById(dto.getTipo_id()).get());
        return domain;
    }

    @Override
    @Transactional
    public SujetoDto save(SujetoDto dto) {
        final SujetoDomain sujeto = convertDtoToDomain(dto);
        final SujetoDomain domain = sujetoDao.save(sujeto);
        if (dto.getId() == null) {
            Integer id = domain.getId();
            dto.setId(id);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + id, dto);
        }
        return convertDomainToDto(domain);
    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_sujeto_'+#id")
    public SujetoDto getById(Integer id) {
        final SujetoDomain sujeto = sujetoDao.findById(id).get();
        return convertDomainToDto(sujeto);
    }

    @Override
    public SujetoResult getAll(Pageable pageable) {
        final List<SujetoDto> sujetos = new ArrayList<>();
        Page<SujetoDomain> results = sujetoDao.findAll(pageable);
        results.forEach(sujeto -> sujetos.add(convertDomainToDto(sujeto)));
        final SujetoResult sujetoResult = new SujetoResult();
        sujetoResult.setSujetos(sujetos);
        return sujetoResult;
    }

    public SujetoResult getllAllNotPaginated() {
        final SujetoResult result = new SujetoResult();
        final Iterable<SujetoDomain> allDomains = sujetoDao.findAll();
        final List<SujetoDto> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(sujetoDomain -> allDtos.add(convertDomainToDto(sujetoDomain)));
        }

        result.setSujetos(allDtos);
        return result;
    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_sujeto_'+#id")
    public SujetoDto update(SujetoDto dto, Integer id) {
        final SujetoDomain updated = sujetoDao.findById(id).get();
        if (dto.getCi() != null) {
            updated.setCi(dto.getCi());
        }
        if (dto.getNombre() != null) {
            updated.setNombre(dto.getNombre());
        }
        if (dto.getCorreo() != null) {
            updated.setCorreo(dto.getCorreo());
        }
        if (dto.getTelefono() != null) {
            updated.setTelefono(dto.getTelefono());
        }
        if (dto.getDireccion() != null) {
            updated.setDireccion(dto.getDireccion());
        }
        if (dto.getDenuncias() != null) {
            updated.setDenuncias(getDenunciasFromDto(dto));
        }
        if (dto.getTipo_id() != null) {
            updated.setTipo(tipoSujetoDao.findById(dto.getTipo_id()).get());
        }
        sujetoDao.save(updated);
        return convertDomainToDto(updated);
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_sujeto_'+#id")
    public SujetoDto delete(Integer id) {
        final SujetoDomain deletedDomain = sujetoDao.findById(id).get();
        final SujetoDto deletedDto = convertDomainToDto(deletedDomain);
        sujetoDao.delete(deletedDomain);
        return deletedDto;
    }
}
