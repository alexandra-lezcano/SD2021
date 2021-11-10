package com.protectionapp.sd2021.domain.location;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
public class CityDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "city")
    private UserDomain user;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<NeighborhoodDomain> neighborhood;

    /*Relacion unidireccional entre denuncia y ciudad*/
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name= "ciudad_id")
    private Set<DenunciaDomain> denuncias;

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

    public void setDescription(String department) {
        this.description = department;
    }

    public Set<NeighborhoodDomain> getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Set<NeighborhoodDomain> neighborhood) {
        this.neighborhood = neighborhood;
    }
}
