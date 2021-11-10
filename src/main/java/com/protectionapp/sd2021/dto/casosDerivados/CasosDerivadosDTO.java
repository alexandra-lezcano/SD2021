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
    private Set<Integer> user_ids;

    @XmlElement
    public Date getDate() {
        return date;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Set<Integer> getDenuncia() {
        return denuncia_ids;
    }

    @XmlElement
    public Set<Integer> getUsers() {
        return user_ids;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsers(Set<Integer> users) {
        this.user_ids = users;
    }

    public void setDenuncia(Set<Integer> denuncia) {
        this.denuncia_ids = denuncia;
    }


}
