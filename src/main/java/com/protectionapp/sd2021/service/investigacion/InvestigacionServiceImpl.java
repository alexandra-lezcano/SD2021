package com.protectionapp.sd2021.service.investigacion;

import com.protectionapp.sd2021.dao.investigacion.IInvestigacionDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.service.denuncia.IDenunciaService;
import com.protectionapp.sd2021.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvestigacionServiceImpl extends BaseServiceImpl<InvestigacionDTO, InvestigacionDomain, InvestigacionResult> implements IInvestigacionService{
    @Autowired
    private IInvestigacionDao investigacionDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDenunciaService denunciaService;

    @Override
    protected InvestigacionDTO convertDomainToDto(InvestigacionDomain domain) {
        final InvestigacionDTO dto = new InvestigacionDTO();
        dto.setBasicFields(domain.getFechaInicio(), domain.getFechaFin(), domain.getDescripcion());

        Set<Integer> userIds = new HashSet<>();
        if(domain.getUsers()!=null){
            domain.getUsers().forEach(userDomain -> userIds.add(userDomain.getId()));
        }
        dto.setUserIds(userIds);
        if(domain.getDenuncia()!=null){ dto.setDenunciaId(domain.getDenuncia().getId());}
        return dto;
    }

    @Override
    protected InvestigacionDomain convertDtoToDomain(InvestigacionDTO dto) {
        final InvestigacionDomain domain = new InvestigacionDomain();
        domain.setBasicFields(dto.getFechaInicio(), dto.getFechaFin(), dto.getDescripcion());
        denunciaService.addDenunciaToInvestigacion(dto,domain);
        userService.addUsersToInvestigacion(dto, domain);
        return domain;
    }

    @Override
    @Transactional
    public InvestigacionDTO save(InvestigacionDTO dto) {
        final InvestigacionDomain domain = convertDtoToDomain(dto);
        final InvestigacionDomain investigacionDomain = investigacionDao.save(domain);

        /*todo testear con cache*/
        if(dto.getId()==null){
            Integer id = investigacionDomain.getId();
            dto.setId(id);
            // guardar dto por id en cache manager
        }
        return convertDomainToDto(investigacionDomain);
    }

    @Override
    @Transactional
    /*todo al menos una forma de tolerancia a fallos*/
    public InvestigacionDTO getById(Integer id) {
        final InvestigacionDomain domain = investigacionDao.findById(id).get();
        return convertDomainToDto(domain);
    }

    @Override
    @Transactional
    public InvestigacionResult getAll(Pageable pageable) {
        final List<InvestigacionDTO> investigacionDTOS = new ArrayList<>();
        Page<InvestigacionDomain> investigacionDomains = investigacionDao.findAll(pageable);
        investigacionDomains.forEach(domain ->investigacionDTOS.add(convertDomainToDto(domain)));

        final InvestigacionResult result = new InvestigacionResult();
        result.setInvestigaciones(investigacionDTOS);
        return result;
    }

    @Override
    @Transactional
    public InvestigacionDTO update(InvestigacionDTO dto, Integer id) {
        final InvestigacionDomain updatedDomain = investigacionDao.findById(id).get();
        updatedDomain.setBasicFields(dto.getFechaInicio(), dto.getFechaFin(), dto.getDescripcion());
        userService.addUsersToInvestigacion(dto,updatedDomain);
        denunciaService.addDenunciaToInvestigacion(dto, updatedDomain);
        investigacionDao.save(updatedDomain);
        return convertDomainToDto(updatedDomain);
    }

    @Override
    @Transactional
    public InvestigacionDTO delete(Integer id) {
        final InvestigacionDomain domain = investigacionDao.findById(id).get();
        final InvestigacionDTO dto = convertDomainToDto(domain);
        investigacionDao.delete(domain);
        return dto;
    }

    @Override
    @Transactional
    public void addInvestigacionToDenuncia(DenunciaDTO dto, DenunciaDomain domain) {
        if(dto.getInvestigacionId()!=null){
            domain.setInvestigacion(investigacionDao.findById(dto.getInvestigacionId()).get());
        }
    }

    @Override
    @Transactional
    public void addInvestigacionesToUser(UserDTO dto, UserDomain domain) {
        Set<InvestigacionDomain> investigacionDomains = new HashSet<>();
        if(dto.getInvestigacionIds()!=null){
            dto.getInvestigacionIds().forEach(investigacionId -> investigacionDomains.add(investigacionDao.findById(investigacionId).get()));
        }
        domain.setInvestigaciones(investigacionDomains);
    }
}
