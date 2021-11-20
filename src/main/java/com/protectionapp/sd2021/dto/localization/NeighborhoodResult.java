package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "neighborhoodResult")
public class NeighborhoodResult extends BaseResult<NeighborhoodDTO> {
    private static final long serialVersionUID = 1L;

    public NeighborhoodResult() {
        super();
    }

    @XmlElement
    public List<NeighborhoodDTO> getNeighborhoodsList() {
        return getList();
    }

    public void setNeighborhoodList(List<NeighborhoodDTO> dtos){ super.setList(dtos);}

    public void setNeighborhoods(List<NeighborhoodDTO> dtos) {
        super.setList(dtos);
    }
}
