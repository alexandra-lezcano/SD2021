package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

@XmlRootElement(name = "denuncias")
public class DenunciaDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String fecha;
    private String descripcion;
    private Integer estado_id;
    private String codigo;
    private Set<Integer> tipo_ids;
    private Integer city_id;
    private Integer neighborhood_id;
    private Set<Integer> sujeto_ids;

    @XmlElement
    public String getFecha() {return fecha;}

    public void setFecha(String fecha) {
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
    public Integer getEstado() {
        return estado_id;
    }

    public void setEstado(Integer estado) {
        this.estado_id = estado;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public Set<Integer> getTipo_ids() {
        return tipo_ids;
    }

    public void setTipo_ids(Set<Integer> tipo_ids) {
        this.tipo_ids = tipo_ids;
    }

    @XmlElement
    public Set<Integer> getSujeto_ids() {
        return sujeto_ids;
    }

    public void setSujeto_ids(Set<Integer> sujeto_ids) {
        this.sujeto_ids = sujeto_ids;
    }

    @XmlElement
    public Date getConvertedFecha(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.fecha);
    }

    @XmlElement
    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    @XmlElement
    public Integer getNeighborhood_id() {
        return neighborhood_id;
    }

    public void setNeighborhood_id(Integer neighborhood_id) {
        this.neighborhood_id = neighborhood_id;
    }

    public void setConvertedFecha(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.fecha = dateFormat.format(date);
    }
}
