package com.protectionapp.sd2021.dto.user;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "user")
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    public UserDTO() {
        super();
    }

    private String name;
    private String surname;
    private String username;
    private Integer cn;
    private String address;
    private String email;
    private Integer phone;
    private Integer cityId;
    private Integer roleId;
    private Set<Integer> neighborhoodIds;
    private Set<Integer> denunciasIds;

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
        return cityId;
    }

    @XmlElement
    public Integer getRoleId() {
        return roleId;
    }

    @XmlElement
    public Set<Integer> getNeighborhoodIds() {
        return neighborhoodIds;
    }

    @XmlElement
    public Set<Integer> getDenunciasIds() {
        return denunciasIds;
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
        this.cityId = cityId;
    }

    public void setRoleId(Integer role) {
        this.roleId = role;
    }

    public void setNeighborhoodIds(Set<Integer> neighborhoods) {
        this.neighborhoodIds = neighborhoods;
    }

    public void setDenunciasIds(Set<Integer> denunciasIds) {
        this.denunciasIds = denunciasIds;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", cn=" + cn +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", cityId=" + cityId +
                ", roleId=" + roleId +
                ", neighborhoodIds=" + neighborhoodIds +
                ", denunciasIds=" + denunciasIds +
                '}';
    }
}
