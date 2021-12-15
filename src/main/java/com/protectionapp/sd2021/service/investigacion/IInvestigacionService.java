package com.protectionapp.sd2021.service.investigacion;

import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface IInvestigacionService extends IBaseService<InvestigacionDTO, InvestigacionResult> {
    void addInvestigacionToDenuncia(DenunciaDTO dto, DenunciaDomain domain);
    void addInvestigacionesToUser(UserDTO dto, UserDomain domain);
}
