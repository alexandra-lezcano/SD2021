package com.protectionapp.sd2021.dto.casosDerivados;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class CasosDerivadosResult extends BaseResult<CasosDerivadosDTO> {
    private static final long serialVersionUID = 1L;



    @XmlElement
    public List<CasosDerivadosDTO> getCasosDerivados() {
        return getList();
    }

    public void setCasosDerivados(List<CasosDerivadosDTO> dtos) {
        super.setList(dtos);
    }

}
