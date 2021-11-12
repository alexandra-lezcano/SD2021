package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "neighborhood")
public class NeighborhoodDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Integer city_id;
    private Set<Integer> user_ids;

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Integer getCity_id() {
        return city_id;
    }

    @XmlElement
    public Set<Integer> getUser_ids() {
        return user_ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public void setUser_ids(Set<Integer> user_ids) {
        this.user_ids = user_ids;
    }

    @Override
    public String toString() {
        return "NeighborhoodDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", city_id=" + city_id +
                '}';
    }
}
