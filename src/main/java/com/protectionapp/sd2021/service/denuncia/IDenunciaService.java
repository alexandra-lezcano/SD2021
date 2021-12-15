package com.protectionapp.sd2021.service.denuncia;

import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.IBaseService;

public interface IDenunciaService extends IBaseService<DenunciaDTO, DenunciaResult> {
    void addDenunciaToInvestigacion(InvestigacionDTO dto, InvestigacionDomain domain);
}
