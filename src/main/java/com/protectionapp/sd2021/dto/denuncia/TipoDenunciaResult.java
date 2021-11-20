package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tipoDenunciaResult")
public class TipoDenunciaResult extends BaseResult<TipoDenunciaDTO> {
    private static final long serialVersionUID = 1L;

    public void setTipoDenuncias(List<TipoDenunciaDTO> dtos) {
        super.setList(dtos);
    }

    public List<TipoDenunciaDTO> getTipoDenuncias() {
        return super.getList();
    }
}
