package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "neighborhood")
public class NeighborhoodDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private CityDomain city;
    private Set<UserDomain> users;

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public CityDomain getCity() {
        return city;
    }

    @XmlElement
    public Set<UserDomain> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(CityDomain city) {
        this.city = city;
    }

    public void setUsers(Set<UserDomain> users) {
        this.users = users;
    }
}
