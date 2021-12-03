package com.protectionapp.sd2021.service.casosDerivados;


import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.casosDerivados.IDepEstadoDao;
import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
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


import java.util.*;

@Service
public class CasosDerivadosServiceImpl extends BaseServiceImpl<CasosDerivadosDTO, CasosDerivadosDomain, CasosDerivadosResult> implements ICasosDerivadosService {

    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private IUserDao userDao;


    @Autowired
    private IDepEstadoDao depEstadoDao;

    @Autowired
    private CacheManager cacheManager;

    private final String cacheKey = "api_casos_derivados_";

    @Override
    protected CasosDerivadosDTO convertDomainToDto(CasosDerivadosDomain domain) {
        final CasosDerivadosDTO casosDerivados = new CasosDerivadosDTO();
        casosDerivados.setId(domain.getId());

        //  casosDerivados.setDate(domain.getDate());

        casosDerivados.setDate(domain.getDate());
        casosDerivados.setDescription(domain.getDescription());

        if (domain.getTrabajador_social() != null) {
            casosDerivados.setUser(domain.getTrabajador_social().getId());
        }

        if (domain.getDenuncia() != null) {
            Set<Integer> denuncias_ids = new HashSet<>();
            Set<DenunciaDomain> denuncias = domain.getDenuncia();
            for (DenunciaDomain d : denuncias) {
                denuncias_ids.add(d.getId());
            }
            casosDerivados.setDenuncia_ids(denuncias_ids);
        }

        //guardo las depEstado de mi caso derivado
        if(domain.getDependencia_estado()!=null){
            Set<Integer> dependenciasIds= new HashSet<Integer>();
            domain.getDependencia_estado().forEach(d->dependenciasIds.add(d.getId()));
          casosDerivados.setDependencias_ids(dependenciasIds);
        }


        return casosDerivados;
    }

    @Override
    protected CasosDerivadosDomain convertDtoToDomain(CasosDerivadosDTO dto) {
        final CasosDerivadosDomain domain = new CasosDerivadosDomain();
        domain.setId(dto.getId());
        // domain.setDate(dto.getDate());
        domain.setDescription(dto.getDescription());

        if (dto.getDenuncia_ids() != null) {
            domain.setTrabajador_social(userDao.findById(dto.getUser_id()).get());
        }

        if (dto.getDependencias_ids() != null) {
            Set<DepEstadoDomain> depEstadoDomains = new HashSet<>();
            Set<Integer> depEstado_ids = dto.getDependencias_ids();

            for (Integer nId : depEstado_ids) {
                depEstadoDomains.add(depEstadoDao.findById(nId).get());
            }
            domain.setDependencia_estado(depEstadoDomains);

        }

        return domain;

    }

    @Override
    @Transactional
    public CasosDerivadosResult getAll(Pageable pageable) {

        final List<CasosDerivadosDTO> cD = new ArrayList<>();
        Page<CasosDerivadosDomain> cDresults = casosDerivadosDao.findAll(pageable);
        cDresults.forEach(casoDerivado -> cD.add(convertDomainToDto(casoDerivado)));
        final CasosDerivadosResult cDResult = new CasosDerivadosResult();
        cDResult.setCasosDerivados(cD);
        return cDResult;
    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_casos_derivados_'+#id")

    public CasosDerivadosDTO update(CasosDerivadosDTO dto, Integer id) {
        // todo mejorar esto
        if (casosDerivadosDao.findById(id) != null) {
            final CasosDerivadosDomain casoDerivado = casosDerivadosDao.findById(id).get();

            casoDerivado.setDate(dto.getDate());
            casoDerivado.setDescription((dto.getDescription()));

//cambiamos lista de  users
            Set<UserDomain> userDomains = new HashSet<>();

            final CasosDerivadosDomain nuevo = casosDerivadosDao.save(casoDerivado);
            return convertDomainToDto(nuevo);
        }
        return null;



    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_casos_derivados_'+#id")

    public CasosDerivadosDTO delete(Integer id) {
        final CasosDerivadosDomain deletedDomain = casosDerivadosDao.findById(id).get();
        final CasosDerivadosDTO deletedDto = convertDomainToDto(deletedDomain);
        casosDerivadosDao.delete(deletedDomain);
        return deletedDto;
    }


    @Override
    @Transactional
    public CasosDerivadosDTO save(CasosDerivadosDTO dto) {

        final CasosDerivadosDomain domain = convertDtoToDomain(dto);
        final CasosDerivadosDomain CD = casosDerivadosDao.save(domain);

        if (dto.getId() == null) {
            Integer nuevoId = CD.getId();
            dto.setId(nuevoId);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + nuevoId, dto);
        }
        return convertDomainToDto(CD);

    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_casos_derivados_'+#id")
    public CasosDerivadosDTO getById(Integer id) {
        final CasosDerivadosDomain cD = casosDerivadosDao.findById(id).get();
        return convertDomainToDto(cD);
    }


    @Override
    public Page<CasosDerivadosDomain> findAll(Pageable pageable) {
        return null;
    }
}