package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name="tipoSujeto")
public class TipoSujetoDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String nombre;
    Set<Integer> sujetosIds;

    @XmlElement
    public String getNombre(){return nombre; }

    @XmlElement
    public Set<Integer> getSujetosIds(){return sujetosIds;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSujetosIds(Set<Integer> sujetosIds) {
        this.sujetosIds = sujetosIds;
    }
}
