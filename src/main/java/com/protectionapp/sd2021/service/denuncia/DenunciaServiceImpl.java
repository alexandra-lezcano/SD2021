package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DenunciaServiceImpl extends BaseServiceImpl<DenunciaDTO, DenunciaDomain, DenunciaResult> implements IDenunciaService {
    @Autowired
    private IDenunciaDao denunciaDao;

    @Override
    protected DenunciaDTO convertDomainToDto(DenunciaDomain domain) {
        return null;
    }

    @Override
    protected DenunciaDomain convertDtoToDomain(DenunciaDTO dto) {
        return null;
    }

    @Override
    public DenunciaDTO save(DenunciaDTO dto) {
        return null;
    }

    @Override
    public DenunciaDTO getById(Integer id) {
        return null;
    }

    @Override
    public DenunciaResult getAll(Pageable pageable) {
        return null;
    }

    @Override
    public DenunciaDTO update(DenunciaDTO dto, Integer id) {
        return null;
    }

    @Override
    public DenunciaDTO delete(Integer id) {
        return null;
    }
}
