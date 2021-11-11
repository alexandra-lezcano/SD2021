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

    /*Una ciudad tiene muchos usuarios ... hay muchos usuarios en una ciudad*/
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<UserDomain> users;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<NeighborhoodDomain> neighborhoods;

    /*Relacion unidireccional entre denuncia y ciudad*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ciudad_id")
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

    public Set<NeighborhoodDomain> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(Set<NeighborhoodDomain> neighborhood) {
        this.neighborhoods = neighborhood;
    }

    public Set<UserDomain> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDomain> users) {
        this.users = users;
    }

    public Set<DenunciaDomain> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(Set<DenunciaDomain> denuncias) {
        this.denuncias = denuncias;
    }

    public void updateDomain(String name, String description, Set<NeighborhoodDomain> neighborhoods, Set<UserDomain> users, Set<DenunciaDomain> denuncias) {
        this.name = name;
        this.description = description;
        if (neighborhoods != null) {
            this.neighborhoods = neighborhoods;
        }

        if (users != null) {
            this.users = users;
        }

        if (denuncias != null) {
            this.denuncias = denuncias;
        }
    }
}
