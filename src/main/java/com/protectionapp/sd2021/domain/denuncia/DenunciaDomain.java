package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;


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
    private String fecha;

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

    @OneToMany(mappedBy = "denuncia")
    private Set<SujetoDomain> sujetos;

    /*Una denuncia tiene una ciudad*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityDomain city;

    /*Una denuncia tiene un barrio*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id", referencedColumnName = "id")
    private NeighborhoodDomain neighborhood;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caso_derivado_id")
    private CasosDerivadosDomain denuncias_caso_derivado;

    @OneToOne(mappedBy = "denuncia")
    private InvestigacionDomain investigacion;

    public InvestigacionDomain getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(InvestigacionDomain investigacion) {
        this.investigacion = investigacion;
    }

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
