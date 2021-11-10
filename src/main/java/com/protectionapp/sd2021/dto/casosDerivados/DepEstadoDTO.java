package com.protectionapp.sd2021.dto.casosDerivados;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import java.util.Date;

public class DepEstadoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;


    private String description;
    private Date date;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
