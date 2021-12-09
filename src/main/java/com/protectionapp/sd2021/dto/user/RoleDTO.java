package com.protectionapp.sd2021.dto.user;


import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name= "role")
public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    public RoleDTO (){super();}

    private String name;
    private String description;
    private Set<Integer> users;

    @XmlElement
    public String getName(){
        return name;
    }
    @XmlElement
    public String getDescription(){
        return description;
    }

    @XmlElement
    public Set<Integer> getUsers(){
        return users;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(Set<Integer> users) {
        this.users = users;
    }
}
