package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.Set;

@XmlRootElement(name = "denuncias")
public class DenunciaDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private Date fecha;
    private String descripcion;
    private String estado;
    private String codigo;
    private Set<Integer> detalle_ids;
    private Set<Integer> tipo_ids;

    @XmlElement
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public Set<Integer> getDetalleIds(){return detalle_ids;};

    public void setDetalleIds (Set <Integer> detalles){this.detalle_ids = detalles;};

    @XmlElement
    public Set<Integer> getTipoIds(){return tipo_ids;}

    public void setTipoIds(Set<Integer> tipos){this.tipo_ids = tipos;}
}
