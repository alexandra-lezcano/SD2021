package com.protectionapp.sd2021.domain.casosDerivados;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "casos_derivados")
public class CasosDerivadosDomain implements IBaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)

    private Integer id;


    @ManyToMany(mappedBy ="casosDerivados", cascade = CascadeType.ALL)
    private Set<DepEstadoDomain> depEstado;

    // @ManyToMany(mappedBy ="denuncias", cascade = CascadeType.ALL)
    //private Set<DepEstadoDomain> com.protectionapp.sd2021.dao.casosDerivados ;

    @ManyToMany(mappedBy ="casosDerivados_persona", cascade = CascadeType.ALL)
    private Set<UserDomain> user;


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
}
