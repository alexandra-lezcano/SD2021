package com.protectionapp.sd2021.dto.casosDerivados;

import com.protectionapp.sd2021.dto.base.BaseDTO;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
@XmlRootElement(name = "depEstado")
public class DepEstadoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private String description;
    private String name;
    private Set<Integer> casos_derivados_ids;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    public Set<Integer> getCasos_derivados_ids() {
        return casos_derivados_ids;
    }

    public void setCasos_derivados_ids(Set<Integer> casos_derivados_ids) {
        this.casos_derivados_ids = casos_derivados_ids;
    }


}