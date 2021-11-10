package com.protectionapp.sd2021.dto.user;

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
    private Integer city_id;
    private Integer role_id;
    private Set<Integer> neighborhood_ids;

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
    public Integer getCityId() {
        return city_id;
    }

    @XmlElement
    public Integer getRoleId() {
        return role_id;
    }

    @XmlElement
    public Set<Integer> getNeighborhoodIds() {
        return neighborhood_ids;
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

    public void setCityId(Integer cityId) {
        this.city_id = cityId;
    }

    public void setRole(Integer role) {
        this.role_id = role;
    }

    public void setNeighborhoods(Set<Integer> neighborhoods) {
        this.neighborhood_ids = neighborhoods;
    }
}
