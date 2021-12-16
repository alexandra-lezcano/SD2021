package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ISujetoDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoSujetoDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;
import com.protectionapp.sd2021.dto.denuncia.SujetoResult;
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
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private ITipoSujetoService tipoSujetoService;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private Configurations configurations;

    private final String cacheKey = "api_sujeto_";

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected SujetoDto convertDomainToDto(SujetoDomain domain) {
        final SujetoDto dto = new SujetoDto();
        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        dto.setCi(domain.getCi());
        dto.setTelefono(domain.getTelefono());
        dto.setDireccion(domain.getDireccion());
        dto.setCorreo(domain.getCorreo());
        if (domain.getDenuncia() != null) {
            dto.setDenunciaId(domain.getDenuncia().getId());
        }

        dto.setTipoId(domain.getTipo().getId());
        return dto;
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

        if (dto.getDenunciaId() != null) {
            domain.setDenuncia(denunciaDao.findById(dto.getDenunciaId()).get());
        }
        domain.setTipo(tipoSujetoDao.findById(dto.getTipoId()).get());
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
    //@Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_sujeto_'+#id")
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
        if (dto.getDenunciaId() != null) {
            updated.setDenuncia(denunciaDao.findById(dto.getDenunciaId()).get());
        }
        if (dto.getTipoId() != null) {
            updated.setTipo(tipoSujetoDao.findById(dto.getTipoId()).get());
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
