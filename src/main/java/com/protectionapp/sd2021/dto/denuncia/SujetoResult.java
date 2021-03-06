package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="sujetoResult")
public class SujetoResult extends BaseResult<SujetoDto> {
    private final static long serialVersionUID = 1L;

    public SujetoResult(){super();}

    public void setSujetos(List<SujetoDto> dtos){super.setList(dtos);}

    public List<SujetoDto> getSujetos(){return super.getList();}
}
