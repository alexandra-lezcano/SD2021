package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.casosDerivados.IDepEstadoDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
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
public class DepEstadoServiceImpl extends BaseServiceImpl<DepEstadoDTO, DepEstadoDomain, DepEstadoResult> implements IDepEstadoService {

    @Autowired
    private IDepEstadoDao depEstadoDao;
    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;


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

        return convertDomainToDto(depEstado);
    }

    @Override
    @Transactional
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
        DEResult.setDepEstado(depEstados);
        return DEResult;

    }

    @Override
    public DepEstadoDTO update(DepEstadoDTO dto, Integer id) {
        return null;
    }

    @Override
    public DepEstadoDTO delete(Integer id) {
        return null;
    }
}
