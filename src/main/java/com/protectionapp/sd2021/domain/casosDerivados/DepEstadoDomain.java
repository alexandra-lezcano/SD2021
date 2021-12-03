package com.protectionapp.sd2021.domain.casosDerivados;
import com.protectionapp.sd2021.Sd2021Application;
import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dependencias_Del_estado")
public class DepEstadoDomain implements IBaseDomain {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;



    //@ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    private Set<CasosDerivadosDomain>casos_derivados ;


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

    public Set<CasosDerivadosDomain> getCasos_derivados() {
        return casos_derivados;
    }

    public void setCasos_derivados(Set<CasosDerivadosDomain> casos_derivados) {
        this.casos_derivados = casos_derivados;
    }
}