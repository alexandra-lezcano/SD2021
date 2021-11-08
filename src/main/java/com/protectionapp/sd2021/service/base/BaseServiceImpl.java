package com.protectionapp.sd2021.service.base;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.dto.base.BaseDTO;
import com.protectionapp.sd2021.dto.base.BaseResult;
import org.springframework.data.domain.Pageable;

public abstract class BaseServiceImpl<DTO extends BaseDTO, DOMAIN extends IBaseDomain, RESULT extends BaseResult<DTO>>
        implements IBaseService<DTO, RESULT> {

    protected abstract DTO convertDomainToDto(DOMAIN domain);

    protected abstract DOMAIN convertDtoToDomain(DTO dto);
    
    public abstract RESULT getAll(Pageable pageable);
}
