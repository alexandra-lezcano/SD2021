package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;

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

	public DenunciaDTO() {
		super();
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private String fecha;
	private String descripcion;
	private Integer estado_id;
	private String codigo;
	private Set<Integer> tipo_ids;
	private Integer city_id;
	private Integer neighborhood_id;
	private Set<Integer> sujeto_ids;
	private Integer investigacionId;

	@XmlElement
	public Integer getInvestigacionId() {
		return investigacionId;
	}

	public void setInvestigacionId(Integer investigacionId) {
		this.investigacionId = investigacionId;
	}

	@XmlElement
	public String getFecha() {
		return fecha;
	}

	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}

	@XmlElement
	public Integer getEstado_id() {
		return estado_id;
	}

	@XmlElement
	public String getCodigo() {
		return codigo;
	}

	@XmlElement
	public Set<Integer> getTipo_ids() {
		return tipo_ids;
	}

	@XmlElement
	public Set<Integer> getSujeto_ids() {
		return sujeto_ids;
	}

	@XmlElement
	public Integer getCity_id() {
		return city_id;
	}

	@XmlElement
	public Integer getNeighborhood_id() {
		return neighborhood_id;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTipo_ids(Set<Integer> tipo_ids) {
		this.tipo_ids = tipo_ids;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public void setNeighborhood_id(Integer neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}

	public void setSujeto_ids(Set<Integer> sujeto_ids) {
		this.sujeto_ids = sujeto_ids;
	}

	public void setEstado_id(Integer estado_id) {
		this.estado_id = estado_id;
	}

}
