package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.IEstadoDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ISujetoDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoDenunciaDao;
import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DenunciaServiceImpl extends BaseServiceImpl<DenunciaDTO, DenunciaDomain, DenunciaResult> implements IDenunciaService {
    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private ITipoDenunciaDao tipoDenunciaDao;

    @Autowired
    private ISujetoDao sujetoDao;

    @Autowired
    private IEstadoDenunciaDao estadoDao;

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private INeighborhoodDao neighborhoodDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private CacheManager cacheManager;

    private final String cacheKey = "api_denuncia_";

    @Autowired
    private Configurations configurations;

    private final Logger logger = LogManager.getLogger(this.getClass());



    @Override
    protected DenunciaDTO convertDomainToDto(DenunciaDomain domain) {
        final DenunciaDTO denuncia = new DenunciaDTO();
        denuncia.setId(domain.getId());
        denuncia.setEstado_id(domain.getEstado().getId());
        denuncia.setDescripcion(domain.getDescripcion());
        denuncia.setFecha(domain.getFecha());
        denuncia.setCodigo(domain.getCodigo());
        denuncia.setCity_id(domain.getCity().getId());
        denuncia.setNeighborhood_id(domain.getNeighborhood().getId());

        /*Debemos tolerar que la denuncia sea creada in un trabajador social asignado*/
        if (domain.getUser() != null) {
            denuncia.setUser_id(domain.getUser().getId());
        }

        if (domain.getTiposDenuncias() != null) {
            Set<Integer> ids = new HashSet<>();
            for (TipoDenunciaDomain t : domain.getTiposDenuncias()) {
                ids.add(t.getId());
            }
            denuncia.setTipo_ids(ids);
        }

        if (domain.getSujetos() != null) {
            Set<Integer> ids = new HashSet<>();
            for (SujetoDomain t : domain.getSujetos()) {
                ids.add(t.getId());
            }
            denuncia.setSujeto_ids(ids);
        }

        return denuncia;
    }

    @Override
    protected DenunciaDomain convertDtoToDomain(DenunciaDTO dto) {
        final DenunciaDomain denuncia = new DenunciaDomain();
        if(dto.getId() != null){denuncia.setID(dto.getId());}
        if(dto.getEstado_id() != null){denuncia.setEstado(estadoDao.findById(dto.getEstado_id()).get());}
        if(dto.getDescripcion()!=null){denuncia.setDescripcion(dto.getDescripcion());}
        if(dto.getFecha()!=null){denuncia.setFecha(dto.getFecha());}
        if(dto.getNeighborhood_id()!=null){denuncia.setNeighborhood(neighborhoodDao.findById(dto.getNeighborhood_id()).get());}
        if(dto.getCity_id()!=null){denuncia.setCity(cityDao.findById(dto.getCity_id()).get());}
        if(dto.getCodigo() != null){denuncia.setCodigo(dto.getCodigo());}
        if (dto.getUser_id() != null) {
            denuncia.setUser(userDao.findById(dto.getUser_id()).get());
        }

        if (dto.getSujeto_ids() != null) {
            Set<SujetoDomain> sujetos = new HashSet<>();
            Set<Integer> ids = dto.getSujeto_ids();
            for (Integer i : ids) {
                sujetos.add(sujetoDao.findById(i).get());
            }
            denuncia.setSujetos(sujetos);
        }

        if (dto.getTipo_ids() != null) {
            Set<TipoDenunciaDomain> tipos = new HashSet<>();
            Set<Integer> ids = dto.getTipo_ids();
            for (Integer i : ids) {
                tipos.add(tipoDenunciaDao.findById(i).get());
            }
            denuncia.setTiposDenuncias(tipos);
        }

        return denuncia;
    }

    @Override
    @Transactional
    public DenunciaDTO save(DenunciaDTO dto) {
        final DenunciaDomain denunciaDomain = convertDtoToDomain(dto);
        final DenunciaDomain denuncia = denunciaDao.save(denunciaDomain);

        if (dto.getId() == null) {
            Integer id = denuncia.getId();
            dto.setId(id);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put(cacheKey + id, dto);
        }
        return convertDomainToDto(denuncia);
    }

    @Override
    @Transactional
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_denuncia_'+#id")
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
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_denuncia_'+#id")
    public DenunciaDTO update(DenunciaDTO dto, Integer id) {
        final DenunciaDomain updated = denunciaDao.findById(id).get();
        if (dto.getTipo_ids() != null) {
            updated.setTiposDenuncias(getTipoDenunciaDomaniFromDTO(dto));
        }
        if (dto.getFecha() != null) {
            updated.setFecha(dto.getFecha());
        }
        if (dto.getEstado_id() != null) {
            updated.setEstado(estadoDao.findById(dto.getEstado_id()).get());
        }
        if (dto.getCodigo() != null) {
            updated.setCodigo(dto.getCodigo());
        }
        if (dto.getDescripcion() != null) {
            updated.setDescripcion(dto.getDescripcion());
        }
        if (dto.getCity_id() != null) {
            updated.setCity(cityDao.findById(dto.getCity_id()).get());
        }
        if (dto.getNeighborhood_id() != null) {
            updated.setNeighborhood(neighborhoodDao.findById(dto.getCity_id()).get());
        }
        if (dto.getSujeto_ids() != null) {
            updated.setSujetos(getSujetosDomainFromDTO(dto));
        }
        if (dto.getUser_id() != null) {
            updated.setUser(userDao.findById(dto.getUser_id()).get());
        }
        denunciaDao.save(updated);
        return convertDomainToDto(updated);
    }

    private Set<SujetoDomain> getSujetosDomainFromDTO(DenunciaDTO dto) {
        Set<SujetoDomain> domains = new HashSet<>();
        dto.getSujeto_ids().forEach(id -> domains.add(sujetoDao.findById(id).get()));
        return domains;
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_denuncia_'+#id")
    public DenunciaDTO delete(Integer id) {
        final DenunciaDomain deletedDomain = denunciaDao.findById(id).get();
        final DenunciaDTO deteledDto = convertDomainToDto(deletedDomain);
        denunciaDao.delete(deletedDomain);
        return deteledDto;

    }

    public Set<TipoDenunciaDomain> getTipoDenunciaDomaniFromDTO(DenunciaDTO dto) {
        Set<TipoDenunciaDomain> domains = new HashSet<>();
        dto.getTipo_ids().forEach(id -> domains.add(tipoDenunciaDao.findById(id).get()));
        return domains;
    }

    public DenunciaResult getllAllNotPaginated() {
        final DenunciaResult result = new DenunciaResult();
        final Iterable<DenunciaDomain> allDomains = denunciaDao.findAll();
        final List<DenunciaDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(denunciaDomain -> allDtos.add(convertDomainToDto(denunciaDomain)));
        }
        result.setDenuncias(allDtos);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDenunciaToUser(UserDTO dto, UserDomain domain) {
        Set<DenunciaDomain> denunciaDomains = new HashSet<>();
        if (dto.getDenunciasIds() != null) {
            dto.getDenunciasIds().forEach(d_id -> denunciaDomains.add(denunciaDao.findById(d_id).get()));
        }
        domain.setDenuncias(denunciaDomains);
    }


}
