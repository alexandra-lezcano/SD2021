package com.protectionapp.sd2021.dto.user;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "roleResult")
public class RoleResult extends BaseResult<RoleDTO> {
    private static final long serialVersionUID = 1L;

    public RoleResult(){super();}

    @XmlElement
    public List<RoleDTO> getRole() {
        return getList();
    }

    public void setRole(List<RoleDTO> dtos) {
        super.setList(dtos);
    }


}
