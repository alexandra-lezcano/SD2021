package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "tipoDenuncia")
public class TipoDenunciaDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descripcion;
    private Set<Integer> denunciaIds;

    @XmlElement
    public String getTitulo() {
        return titulo;
    }

    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

    @XmlElement
    public Set<Integer> getDenunciaIds() {
        return denunciaIds;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDenunciaIds(Set<Integer> denunciaIds) {
        this.denunciaIds = denunciaIds;
    }
}
