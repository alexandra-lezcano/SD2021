package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.Sd2021Application;
import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.casosDerivados.IDepEstadoDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DepEstadoServiceImpl extends BaseServiceImpl<DepEstadoDTO, DepEstadoDomain, DepEstadoResult> implements IDepEstadoService {
    private static final Logger logger = LogManager.getLogger(DepEstadoServiceImpl.class);

    @Autowired
    private IDepEstadoDao depEstadoDao;
    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;
    @Autowired
    private CacheManager cacheManager;

    private String cacheKey = "api_dep_estado_";

    @Override
    protected DepEstadoDTO convertDomainToDto(DepEstadoDomain domain) {

        final DepEstadoDTO depEstado = new DepEstadoDTO();
        depEstado.setName(domain.getName());
        depEstado.setDescription(domain.getDescription());
        depEstado.setId(domain.getId());
        if (domain.getCasos_derivados() != null) {
            Set<Integer> casos_derivadosIds = new HashSet<>();
            domain.getCasos_derivados().forEach(n_domain -> casos_derivadosIds.add(n_domain.getId()));
            depEstado.setCasos_derivados_ids(casos_derivadosIds);
        }
        return depEstado;
    }

    @Override

    protected DepEstadoDomain convertDtoToDomain(DepEstadoDTO dto) {
        final DepEstadoDomain depEstado = new DepEstadoDomain();
        depEstado.setName(dto.getName());
        depEstado.setDescription(dto.getDescription());
        depEstado.setId(dto.getId());
        if (dto.getCasos_derivados_ids() != null) {
            Set<CasosDerivadosDomain> domains = new HashSet<>();
            dto.getCasos_derivados_ids().forEach(n_id -> domains.add(casosDerivadosDao.findById(n_id).get()));
            depEstado.setCasos_derivados(domains);
        }


        return depEstado;
    }

    @Override
    @Transactional
    public DepEstadoDTO save(DepEstadoDTO dto) {
        final DepEstadoDomain depEstadoDomain = convertDtoToDomain(dto);
        final DepEstadoDomain depEstado = depEstadoDao.save(depEstadoDomain);
        if (dto.getId() == null) {
            Integer nuevoId = depEstado.getId();
            dto.setId(nuevoId);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + nuevoId, dto);
        }
        return convertDomainToDto(depEstado);
    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_dep_estado_'+#id")
    public DepEstadoDTO getById(Integer id) {
        final DepEstadoDomain dE = depEstadoDao.findById(id).get();
        return convertDomainToDto(dE);
    }

    @Override
    @Transactional
    public DepEstadoResult getAll(Pageable pageable) {
        final List<DepEstadoDTO> depEstados = new ArrayList<DepEstadoDTO>();
        Page<DepEstadoDomain> results = depEstadoDao.findAll(pageable);
        results.forEach(dE -> depEstados.add(convertDomainToDto(dE)));

        final DepEstadoResult DEResult = new DepEstadoResult();
        DEResult.setDepEstados(depEstados);
        return DEResult;

    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_dep_estado_'+#id")
    public DepEstadoDTO update(DepEstadoDTO dto, Integer id) {
        final DepEstadoDomain updated = depEstadoDao.findById(id).get();
        if (dto.getCasos_derivados_ids() != null) {
            updated.setCasos_derivados(getCasosDerFromDto(dto));
        }
        if (dto.getDescription() != null) {
            updated.setDescription(dto.getDescription());
        }

        if (dto.getName() != null) {
            updated.setName(dto.getName());
        }

        depEstadoDao.save(updated);
        return convertDomainToDto(updated);


    }

    private Set<CasosDerivadosDomain> getCasosDerFromDto(DepEstadoDTO dto) {
        Set<CasosDerivadosDomain> ret = new HashSet<>();
        dto.getCasos_derivados_ids().forEach(id -> ret.add(casosDerivadosDao.findById(id).get()));
        return ret;
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_tipo_denuncia_'+#id")
    public DepEstadoDTO delete(Integer id) {
        final DepEstadoDomain deletedDomain = depEstadoDao.findById(id).get();
        final DepEstadoDTO deletedDto = convertDomainToDto(deletedDomain);
        depEstadoDao.delete(deletedDomain);
        return deletedDto;
    }
}