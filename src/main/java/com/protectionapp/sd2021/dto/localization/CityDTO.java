package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "city")
public class CityDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Set<UserDomain> users;
    private Set<NeighborhoodDomain> neighborhood;

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Set<UserDomain> getUsers() {
        return users;
    }

    @XmlElement
    public Set<NeighborhoodDomain> getNeighborhood() {
        return neighborhood;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsers(Set<UserDomain> users) {
        this.users = users;
    }

    public void setNeighborhood(Set<NeighborhoodDomain> neighborhood) {
        this.neighborhood = neighborhood;
    }
}