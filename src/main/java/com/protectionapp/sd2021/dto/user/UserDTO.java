package com.protectionapp.sd2021.dto.user;

import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "user")
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String username;
    private Integer cn;
    private String address;
    private String email;
    private Integer phone;
    private CityDomain city;
    private RoleDomain role;
    private Set<NeighborhoodDomain> neighborhoods;

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getSurname() {
        return surname;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    @XmlElement
    public Integer getCn() {
        return cn;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    @XmlElement
    public Integer getPhone() {
        return phone;
    }

    @XmlElement
    public CityDomain getCity() {
        return city;
    }

    @XmlElement
    public RoleDomain getRole() {
        return role;
    }

    @XmlElement
    public Set<NeighborhoodDomain> getNeighborhoods() {
        return neighborhoods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCn(Integer cn) {
        this.cn = cn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCity(CityDomain city) {
        this.city = city;
    }

    public void setRole(RoleDomain role) {
        this.role = role;
    }

    public void setNeighborhoods(Set<NeighborhoodDomain> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }
}
