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
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo")
    private String codigo;

    /*Una denuncia tiene un estado*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private DenunciaEstadoDomain estado;

    /*Crea la tabla intermedia entre denuncia y tipo de denuncia*/
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "denuncia_tipos",
            joinColumns = @JoinColumn(name = "denuncia_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private Set<TipoDenunciaDomain> tipos;

    /*Una denuncia tiene una ciudad*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityDomain city;

    /*Una denuncia tiene un barrio*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id", referencedColumnName = "id")
    private NeighborhoodDomain neighborhood;

    /*Una denuncia tiene varios sujetos*/
    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL)
    private Set<SujetoDomain> sujetos;

    /* Muchas denuncias corresponden a un trabajador social */
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DenunciaEstadoDomain getEstado() {
        return estado;
    }

    public void setEstado(DenunciaEstadoDomain estado) {
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

    public Set<SujetoDomain> getSujetos() {
        return sujetos;
    }

    public void setSujetos(Set<SujetoDomain> sujetos) {
        this.sujetos = sujetos;
    }

    public CityDomain getCity() {
        return city;
    }

    public void setCity(CityDomain city) {
        this.city = city;
    }

    public NeighborhoodDomain getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(NeighborhoodDomain neighborhood) {
        this.neighborhood = neighborhood;
    }
}
