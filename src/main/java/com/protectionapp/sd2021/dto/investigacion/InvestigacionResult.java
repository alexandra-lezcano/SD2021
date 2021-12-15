package com.protectionapp.sd2021.dto.investigacion;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "investigacionResult")
public class InvestigacionResult extends BaseResult<InvestigacionDTO>{
    private static final long serialVersionUID = 1L;

    public InvestigacionResult(){super();}

    @XmlElement
    public List<InvestigacionDTO> getInvestigaciones(){return getList();}
    public void setInvestigaciones(List<InvestigacionDTO> dtos){super.setList(dtos);}
}
