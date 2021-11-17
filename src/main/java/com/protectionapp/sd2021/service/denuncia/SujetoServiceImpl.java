package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ISujetoDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoSujetoDao;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;
import com.protectionapp.sd2021.dto.denuncia.SujetoResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SujetoServiceImpl extends BaseServiceImpl<SujetoDto, SujetoDomain, SujetoResult> implements ISujetoService {
    @Autowired
    private ITipoSujetoDao tipoSujetoDao;
    @Autowired
    private IDenunciaDao denunciaDao;
    @Autowired
    private ISujetoDao sujetoDao;

    @Override
    protected SujetoDto convertDomainToDto(SujetoDomain domain) {
        final SujetoDto dto = new SujetoDto();
        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        dto.setCi(domain.getCi());
        dto.setTelefono(domain.getTelefono());
        dto.setDireccion(domain.getDireccion());
        dto.setDenuncia_id(domain.getDenuncia().getId());
        dto.setTipo_id(domain.getTipo().getId());
        return dto;
    }

    @Override
    protected SujetoDomain convertDtoToDomain(SujetoDto dto) {
        final SujetoDomain domain = new SujetoDomain();
        domain.setId(dto.getId());
        domain.setCi(dto.getCi());
        domain.setNombre(dto.getNombre());
        domain.setTelefono(dto.getTelefono());
        domain.setDireccion(dto.getDireccion());
        domain.setDenuncia(denunciaDao.findById(dto.getDenuncia_id()).get());
        domain.setTipo(tipoSujetoDao.findById(dto.getTipo_id()).get());
        return domain;
    }

    @Override
    public SujetoDto save(SujetoDto dto) {
        final SujetoDomain sujeto = convertDtoToDomain(dto);
        final SujetoDomain domain = sujetoDao.save(sujeto);
        return convertDomainToDto(domain);
    }

    @Override
    public SujetoDto getById(Integer id) {
        final SujetoDomain sujeto = sujetoDao.findById(id).get();
        return convertDomainToDto(sujeto);
    }

    @Override
    public SujetoResult getAll(Pageable pageable) {
        final List<SujetoDto> sujetos = new ArrayList<>();
        Page<SujetoDomain> results = sujetoDao.findAll(pageable);
        results.forEach(sujeto->sujetos.add(convertDomainToDto(sujeto)));
        final SujetoResult sujetoResult = new SujetoResult();
        sujetoResult.setSujetoList(sujetos);
        return sujetoResult;
    }

    @Override
    public SujetoDto update(SujetoDto dto, Integer id) {
        final SujetoDomain updated = sujetoDao.findById(id).get();
        if(dto.getCi() != null){
            updated.setCi(dto.getCi());
        }
        if(dto.getNombre()!=null){
            updated.setNombre(dto.getNombre());
        }
        if(dto.getCorreo()!=null){
            updated.setCorreo(dto.getCorreo());
        }
        if(dto.getTelefono()!=null){
            updated.setTelefono(dto.getTelefono());
        }
        if(dto.getDireccion()!=null){
            updated.setDireccion(dto.getDireccion());
        }
        if(dto.getDenuncia_id()!=null){
            updated.setDenuncia(denunciaDao.findById(dto.getDenuncia_id()).get());
        }
        if(dto.getTipo_id()!=null){
            updated.setTipo(tipoSujetoDao.findById(dto.getTipo_id()).get());
        }
        sujetoDao.save(updated);
        return convertDomainToDto(updated);
    }

    @Override
    public SujetoDto delete(Integer id) {
        final SujetoDomain deletedDomain = sujetoDao.findById(id).get();
        final SujetoDto deletedDto = convertDomainToDto(deletedDomain);
        sujetoDao.delete(deletedDomain);
        return deletedDto;
    }
}
