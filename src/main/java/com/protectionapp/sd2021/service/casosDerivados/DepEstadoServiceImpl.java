package com.protectionapp.sd2021.service.casosDerivados;

import com.protectionapp.sd2021.dao.casosDerivados.IDepEstadoDao;
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
import java.util.List;
@Service
public class DepEstadoServiceImpl extends BaseServiceImpl<DepEstadoDTO, DepEstadoDomain, DepEstadoResult> implements IDepEstadoService {


    @Autowired
    private IDepEstadoDao depEstadoDao;


    @Override

    protected DepEstadoDTO convertDomainToDto(DepEstadoDomain domain) {

        final DepEstadoDTO depEstado = new DepEstadoDTO();
        depEstado.setName(domain.getName());
        depEstado.setDescription(domain.getDescription());
            return depEstado;
    }
    @Override

    protected DepEstadoDomain convertDtoToDomain(DepEstadoDTO dto) {
        final DepEstadoDomain depEstado = new DepEstadoDomain();
        depEstado.setName(dto.getName());
        depEstado.setDescription(dto.getDescription());
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
}
