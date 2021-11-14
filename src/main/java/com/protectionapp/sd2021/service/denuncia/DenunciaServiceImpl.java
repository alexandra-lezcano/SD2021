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
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        denuncia.setID(dto.getId());
        denuncia.setEstado(estadoDao.findById(dto.getEstado_id()).get());
        denuncia.setCodigo(dto.getCodigo());
        denuncia.setDescripcion(dto.getDescripcion());
        denuncia.setFecha(dto.getFecha());
        denuncia.setNeighborhood(neighborhoodDao.findById(dto.getNeighborhood_id()).get());
        denuncia.setCity(cityDao.findById(dto.getCity_id()).get());

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
}
