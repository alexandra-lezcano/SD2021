package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "neighborhoodResult")
public class NeighborhoodResult extends BaseResult<NeighborhoodDTO> {
    private static final long serialVersionUID = 1L;

    @XmlElement
    public List<NeighborhoodDTO> getUsers() {
        return getList();
    }

    public void setUsers(List<NeighborhoodDTO> dtos) {
        super.setList(dtos);
    }
}
