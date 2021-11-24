package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "denunciaEstadosResult")
public class DenunciaEstadoResult extends BaseResult<DenunciaEstadoDTO> {
    private static final long serialVersionUID = 1L;
    
    public DenunciaEstadoResult() { super(); }

    public void setDenunciaEstados(List<DenunciaEstadoDTO> dtos){super.setList(dtos);}

    @XmlElement
    public List<DenunciaEstadoDTO> getDenunciaEstados(){return super.getList(); }

}
