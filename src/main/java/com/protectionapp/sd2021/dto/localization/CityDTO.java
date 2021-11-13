package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "city")
public class CityDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Set<Integer> users;
    private Set<Integer> neighborhoods;
    private Set<Integer> denuncia_ids;
    private Set<Integer> sujeto_ids;

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Set<Integer> getUsers() {
        return users;
    }

    @XmlElement
    public Set<Integer> getNeighborhoods() {
        return neighborhoods;
    }

    @XmlElement
    public Set<Integer> getDenuncias() {
        return denuncia_ids;
    }

    @XmlElement
    public Set<Integer> getSujetos() {return sujeto_ids;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsers(Set<Integer> users) {
        this.users = users;
    }

    public void setNeighborhoods(Set<Integer> neighborhood) {
        this.neighborhoods = neighborhood;
    }

    public void setDenuncias(Set<Integer> denuncias) {
        this.denuncia_ids = denuncias;
    }

    public void setSujetos(Set<Integer> sujetos) {this.sujeto_ids = sujetos;}

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
