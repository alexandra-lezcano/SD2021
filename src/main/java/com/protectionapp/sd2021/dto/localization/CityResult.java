package com.protectionapp.sd2021.dto.localization;

import com.protectionapp.sd2021.dto.base.BaseResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cityResult")
public class CityResult extends BaseResult<CityDTO> {
    private static final long serialVersionUID = 1L;

    public CityResult() {
        super();
    }

    @XmlElement
    public List<CityDTO> getCitys() {
        return getList();
    }

      public void setCitys(List<CityDTO> dtos) {
        super.setList(dtos);
    }
}
