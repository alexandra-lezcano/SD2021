package com.protectionapp.sd2021.dto.casosDerivados;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "depEstadoResult")
public class DepEstadoResult extends BaseResult<DepEstadoDTO> {
    private static final long serialVersionUID = 1L;
    @XmlElement
    public List<DepEstadoDTO> getDepEstado() {
        return getList();
    }

    public void setDepEstado(List<DepEstadoDTO> dtos) {
        super.setList(dtos);
    }


}
