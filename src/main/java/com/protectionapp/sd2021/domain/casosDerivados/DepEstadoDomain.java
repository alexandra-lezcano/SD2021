package com.protectionapp.sd2021.domain.casosDerivados;


import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dependencias_Del_estado")
public class DepEstadoDomain implements IBaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)


    private Integer id;

    private static final long serialVersionUID = 1L;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CD_DE",
            joinColumns = @JoinColumn(name = "depEstado_id"),
            inverseJoinColumns = @JoinColumn(name = "casosDep_id")
    )
    private Set<CasosDerivadosDomain> casosDerivados;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dC_DE",
            joinColumns = @JoinColumn(name = "depEstado_id"),
            inverseJoinColumns = @JoinColumn(name = "detallesCasos_id")
    )
    private Set<DetallesCasosDerDomain> detCasosDer;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
