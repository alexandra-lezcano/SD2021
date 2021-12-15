package com.protectionapp.sd2021.domain.casosDerivados;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "casos_derivados")
public class CasosDerivadosDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;


    @ManyToMany
    @JoinTable(
            name = "casos_derivados_dep_estado",
            joinColumns = @JoinColumn(name="casos_derivado_id"),
            inverseJoinColumns = @JoinColumn(name="dep_estado_id")
    )
    private Set<DepEstadoDomain> dependencia_estado;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denuncia_id", referencedColumnName = "id")
    private DenunciaDomain denuncia ;



    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDomain user;



    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DepEstadoDomain> getDependencia_estado() {
        return dependencia_estado;
    }

    public void setDependencia_estado(Set<DepEstadoDomain> dependencia_estado) {
        this.dependencia_estado = dependencia_estado;
    }

    public UserDomain getTrabajador_social() {
        return user;
    }

    public void setTrabajador_social(UserDomain trabajador_social) {
        this.user = trabajador_social;
    }

    public DenunciaDomain getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaDomain denuncia) {
        this.denuncia = denuncia;
    }
    
}