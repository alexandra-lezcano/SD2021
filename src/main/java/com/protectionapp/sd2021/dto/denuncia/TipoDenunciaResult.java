package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tipoDenunciResult")
public class TipoDenunciaResult extends BaseResult<TipoDenunciaDTO> {
    private static final long serialVersionUID = 1L;

    public TipoDenunciaResult() {
        super();
    }

    public void setTipoDenunciaList(List<TipoDenunciaDTO> dtos) {
        super.setList(dtos);
    }

    @XmlElement
    public List<TipoDenunciaDTO> getTipoDenunciasList() {
        return super.getList();
    }
}
