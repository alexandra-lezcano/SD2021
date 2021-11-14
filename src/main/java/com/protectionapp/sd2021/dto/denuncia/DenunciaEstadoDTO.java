package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "denunciaEstados")
public class DenunciaEstadoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    public DenunciaEstadoDTO() {
        super();
    }

    private String nombre;
    private Set<Integer> denuncias_ids;

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    @XmlElement
    public Set<Integer> getDenuncias_ids() {
        return denuncias_ids;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDenuncias_ids(Set<Integer> denuncias_ids) {
        this.denuncias_ids = denuncias_ids;
    }
}
