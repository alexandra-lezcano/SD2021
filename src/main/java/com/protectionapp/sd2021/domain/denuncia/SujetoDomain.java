package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.persistence.*;

@Entity
@Table(name="sujetos")
public class SujetoDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "CI")
    private String ci;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    /*Un sujeto tien un tipo*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    private TipoSujetoDomain tipo;

    /*Un sujeto tiene una denuncia*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denuncia_id", referencedColumnName = "id")
    private DenunciaDomain denuncia;

    public Integer getId() {
        return id;
    }

    public String getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCi(String ci) {
        this.ci = ci;
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

    public DenunciaDomain getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaDomain denuncia) {
        this.denuncia = denuncia;
    }

    public TipoSujetoDomain getTipo() {
        return tipo;
    }

    public void setTipo(TipoSujetoDomain tipo) {
        this.tipo = tipo;
    }

}
