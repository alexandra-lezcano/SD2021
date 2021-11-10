package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseDTO;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement(name = "denuncia")
public class DenunciaDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private Date fecha;
    private String descripcion;
    private String estado;
    private String codigo;


    public Date getFecha(){return fecha;}
    public void setFecha(Date fecha){this.fecha = fecha;}

    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado = estado;}

    public String getCodigo(){ return codigo;}
    public void setCodigo(String codigo){ this.codigo = codigo;}
}
