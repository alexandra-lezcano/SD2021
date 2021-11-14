package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import com.protectionapp.sd2021.domain.user.UserDomain;

@Entity
@Table(name = "denuncias")
public class DenunciaDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "codigo")
    private String codigo;


    /*Crea la tabla intermedia entre denuncia y tipo de denuncia*/
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "denuncia_tipos",
            joinColumns = @JoinColumn(name = "denuncia_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private Set<TipoDenunciaDomain> tipos;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDomain user;


    public Set<TipoDenunciaDomain> getTiposDenuncias() {
        return this.tipos;
    }

    public void setTiposDenuncias(Set<TipoDenunciaDomain> tipos) {
        this.tipos = tipos;
    }

    public Integer getId() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TipoDenunciaDomain> getTipos() {
        return tipos;
    }

    public void setTipos(Set<TipoDenunciaDomain> tipos) {
        this.tipos = tipos;
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public void update(String fecha, String descripcion, String estado, String codigo) {
        setFecha(fecha);
        setDescripcion(descripcion);
        setCodigo(codigo);
        setEstado(estado);
    }
}
