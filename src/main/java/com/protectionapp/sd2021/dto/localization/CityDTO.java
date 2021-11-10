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
    private Set<Integer> user_ids;
    private Set<Integer> neighborhood_ids;

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
        return user_ids;
    }

    @XmlElement
    public Set<Integer> getNeighborhood() {
        return neighborhood_ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsers(Set<Integer> users) {
        this.user_ids = users;
    }

    public void setNeighborhood(Set<Integer> neighborhood) {
        this.neighborhood_ids = neighborhood;
    }
}
