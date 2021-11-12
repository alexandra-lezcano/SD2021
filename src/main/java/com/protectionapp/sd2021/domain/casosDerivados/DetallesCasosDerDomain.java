package com.protectionapp.sd2021.domain.casosDerivados;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "detallesCasos")
public class DetallesCasosDerDomain implements IBaseDomain {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)

    private Integer id;







    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
