package com.protectionapp.sd2021.dto.casosDerivados;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@XmlRootElement(name = "casosEstado")
public class CasosDerivadosDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private Date date;
    private String description;
    private Integer denuncia_id;
    private Integer user_id;
    private Set<Integer> dependencias_ids;


    @XmlElement
    public Date getDate() {
        return date;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }


    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public void setDenuncia_id(Integer denuncia_id) {
        this.denuncia_id = denuncia_id;
    }
    @XmlElement
    public Integer getDenuncia_id() {
        return denuncia_id;
    }

    @XmlElement
    public Integer getUser_id() {
        return user_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(Integer users) {
        this.user_id = users;
    }

    public Set<Integer> getDependencias_ids() {
        return dependencias_ids;
    }

    public void setDependencias_ids(Set<Integer> dependencias_ids) {
        this.dependencias_ids = dependencias_ids;
    }
}