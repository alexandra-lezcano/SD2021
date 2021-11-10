package com.protectionapp.sd2021.domain.casosDerivados;


import com.protectionapp.sd2021.domain.base.IBaseDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Dependencias_Del_estado")
public class DepEstadoDomain implements IBaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)


    private Integer id;

    private static final long serialVersionUID = 1L;

  

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
