package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;
import com.sun.xml.bind.annotation.XmlLocation;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class TipoSujetoResult extends BaseResult<TipoSujetoDTO> {
    private static final long serialVersionUID = 1L;

    public TipoSujetoResult(){super();}

    public void setTipoSujetos(List<TipoSujetoDTO> dtos){super.setList(dtos);}

    @XmlElement
    public List<TipoSujetoDTO> getTipoSujetos(){return super.getList();}
}
