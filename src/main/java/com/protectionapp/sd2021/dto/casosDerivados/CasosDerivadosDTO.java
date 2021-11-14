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
    private Set<Integer> denuncia_ids;
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


    @XmlElement
    public Integer getUsers() {
        return user_id;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public void setDenuncia_ids(Set<Integer> denuncia_ids) {
        this.denuncia_ids = denuncia_ids;
    }
    @XmlElement
    public Set<Integer> getDenuncia_ids() {
        return denuncia_ids;
    }

    public void setUser_id(Integer user_ids) {
        this.user_id = user_ids;
    }

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
