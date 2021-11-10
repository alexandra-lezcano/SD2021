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
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "codigo")
    private String codigo;

    /*Crea la tabla intermedia entre user y denuncia*/
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "denuncias")
    @JoinTable(
            name = "denuncia_detalles_victimas_victimarios",
            joinColumns = {@JoinColumn(name = "denuncia_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<UserDomain> detalles_victimas_victimarios;

    /*Crea la tabla intermedia entre denuncia y tipo de denuncia*/
    @ManyToMany(cascade= CascadeType.ALL, mappedBy = "denuncias")
    @JoinTable(
            name = "denuncia_tipos",
            joinColumns = @JoinColumn(name="denuncia_id"),
            inverseJoinColumns = @JoinColumn(name="tipo_id")
    )


    private Set<TipoDenunciaDomain> tipos;

    /*Crea la tabla intermedia entre com.protectionapp.sd2021.dao.casosDerivados y denuncia*/
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "denuncias")
    @JoinTable(
            name = "casosDerivados_denuncias",
            joinColumns = {@JoinColumn(name = "denuncia_id")},
            inverseJoinColumns = {@JoinColumn(name = "casosDerivados_id")}
    )
    private Set<CasosDerivadosDomain> casosDerivados;

    public Integer getId() {
        return id;
    }
    public void setID(Integer id){this.id = id;}

    public Date getFecha(){return fecha;}
    public void setFecha(Date fecha){this.fecha = fecha;}

    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado = estado;}

    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getCodigo(){return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}

}
