package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sujetos")
public class SujetoDto extends BaseDTO {
	private static final long serialVersionUID = 1L;

	public SujetoDto() {
		super();
	}

	private String ci;
	private String nombre;
	private String telefono;
	private String correo;
	private String direccion;
	private Integer tipo_id;
	private Integer denuncia_id;

	@XmlElement
	public String getCi() {
		return ci;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	@XmlElement
	public String getTelefono() {
		return telefono;
	}

	@XmlElement
	public String getCorreo() {
		return correo;
	}

	@XmlElement
	public String getDireccion() {
		return direccion;
	}

	@XmlElement
	public Integer getTipo_id() {
		return tipo_id;
	}

	@XmlElement
	public Integer getDenuncia_id() {
		return denuncia_id;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTipo_id(Integer tipo_id) {
		this.tipo_id = tipo_id;
	}

	public void setDenuncia_id(Integer denuncia_id) {
		this.denuncia_id = denuncia_id;
	}
}
