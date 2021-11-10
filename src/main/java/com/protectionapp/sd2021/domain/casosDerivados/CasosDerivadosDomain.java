package com.protectionapp.sd2021.domain.casosDerivados;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Dependencias_Del_estado")
public class CasosDerivadosDomain implements IBaseDomain {
    @Id
    @Column(name = "id_derivacion", nullable = false)
    private Long id_derivacion;

    @Column(name = "address")
    private String address;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    public Long getId() {
        return id_derivacion;
    }

    public void setId(Long id) {
        this.id_derivacion = id;
    }






}
