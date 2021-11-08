package com.protectionapp.sd2021.dto.base;

import javax.xml.bind.annotation.XmlElement;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer _id;

    @XmlElement
    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }
}
