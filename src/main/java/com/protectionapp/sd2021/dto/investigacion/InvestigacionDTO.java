package com.protectionapp.sd2021.dto.investigacion;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "investigacion")
public class InvestigacionDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    public InvestigacionDTO(){super();}

    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    private Integer denunciaId;
    private Set<Integer> userIds;

    @XmlElement
    public String getFechaInicio() {
        return fechaInicio;
    }

    @XmlElement
    public String getFechaFin() {
        return fechaFin;
    }

    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

    @XmlElement
    public Integer getDenunciaId() {
        return denunciaId;
    }

    @XmlElement
    public Set<Integer> getUserIds() {
        return userIds;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDenunciaId(Integer denunciaId) {
        this.denunciaId = denunciaId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setBasicFields(String fechaInicio, String fechaFin, String descripcion){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "InvestigacionDTO{" +
                "fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", denunciaId=" + denunciaId +
                ", userIds=" + userIds +
                '}';
    }
}
