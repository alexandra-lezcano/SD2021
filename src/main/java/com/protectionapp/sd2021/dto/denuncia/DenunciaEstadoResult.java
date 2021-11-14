package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "denunciaEstadosResult")
public class DenunciaEstadoResult extends BaseResult<DenunciaEstadoDTO> {
    private static final long serialVersionUID = 1L;

    //Falta implementar los metodos de la super clase
    public DenunciaEstadoResult() {
        super();
    }


}
