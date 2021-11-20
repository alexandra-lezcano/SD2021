package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.denuncia.ISujetoDao;
import com.protectionapp.sd2021.dao.denuncia.ITipoSujetoDao;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoSujetoDomain;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.dto.denuncia.TipoSujetoDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoSujetoResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TipoSujetoServiceImpl extends BaseServiceImpl<TipoSujetoDTO, TipoSujetoDomain,TipoSujetoResult> implements ITipoSujetoService{
    @Autowired
    private ITipoSujetoDao tipoSujetoDao;
    @Autowired
    private ISujetoDao sujetoDao;


    @Override
    protected TipoSujetoDTO convertDomainToDto(TipoSujetoDomain domain) {
        final TipoSujetoDTO dto = new TipoSujetoDTO();
        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        if(domain.getSujetos()!=null){
            Set<Integer> sujetos = new HashSet<>();
            domain.getSujetos().forEach(SujetoDomain->sujetos.add(domain.getId()));
        }
        return dto;
    }

    @Override
    protected TipoSujetoDomain convertDtoToDomain(TipoSujetoDTO dto) {
        final TipoSujetoDomain domain = new TipoSujetoDomain();
        domain.setId(dto.getId());
        domain.setNombre(dto.getNombre());
        if(dto.getSujetosIds()!=null){
            Set<SujetoDomain> sujetos = new HashSet<>();
            dto.getSujetosIds().forEach(id->sujetos.add(sujetoDao.findById(id).get()));
        }
        return domain;
    }

    @Override
    public TipoSujetoDTO save(TipoSujetoDTO dto) {
        final TipoSujetoDomain tipoSujetoDomain = convertDtoToDomain(dto);
        final TipoSujetoDomain domain = tipoSujetoDao.save(tipoSujetoDomain);
        return convertDomainToDto(domain);
    }

    @Override
    public TipoSujetoDTO getById(Integer id) {
        final TipoSujetoDomain tipo = tipoSujetoDao.findById(id).get();
        return convertDomainToDto(tipo);
    }

    @Override
    public TipoSujetoResult getAll(Pageable pageable) {
        final List<TipoSujetoDTO> tipos = new ArrayList<>();
        Page<TipoSujetoDomain> results = tipoSujetoDao.findAll(pageable);
        results.forEach(tipo->tipos.add(convertDomainToDto(tipo)));
        final TipoSujetoResult result = new TipoSujetoResult();
        result.setTipoSujetos(tipos);
        return result;
    }

    public TipoSujetoResult getllAllNotPaginated() {
        final TipoSujetoResult result = new TipoSujetoResult();
        final Iterable<TipoSujetoDomain> allDomains = tipoSujetoDao.findAll();
        System.out.println("[ITERABLE] ALL DOMAINS " + allDomains.toString());
        final List<TipoSujetoDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(tipoSujetoDomain -> allDtos.add(convertDomainToDto(tipoSujetoDomain)));
        }
        System.out.println("[List] ALL DTOS " + allDtos.toString());

        result.setTipoSujetos(allDtos);

        System.out.println("[RESULT LIST] ALL DTOS " + result.getTipoSujetos().toString());
        return result;
    }

    @Override
    public TipoSujetoDTO update(TipoSujetoDTO dto, Integer id) {
        final TipoSujetoDomain updated = tipoSujetoDao.findById(id).get();
        if(dto.getNombre()!=null){
            updated.setNombre(dto.getNombre());
        }
        if(dto.getSujetosIds()!=null){
            updated.setSujetos(getSujetosFromDto(dto));
        }
        return convertDomainToDto(updated);
    }

    @Override
    public TipoSujetoDTO delete(Integer id) {
        final TipoSujetoDomain deletedDomain = tipoSujetoDao.findById(id).get();
        final TipoSujetoDTO deletedDto = convertDomainToDto(deletedDomain);
        tipoSujetoDao.delete(deletedDomain);
        return deletedDto;
    }

    private Set<SujetoDomain>getSujetosFromDto(TipoSujetoDTO dto){
        Set<SujetoDomain> ret = new HashSet<>();
        dto.getSujetosIds().forEach(id->ret.add(sujetoDao.findById(id).get()));
        return ret;
    }
}
