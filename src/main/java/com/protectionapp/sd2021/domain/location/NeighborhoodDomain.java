package com.protectionapp.sd2021.domain.location;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "neighborhood")
public class NeighborhoodDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityDomain city;

    @ManyToMany(mappedBy = "neighborhoods")
    private Set<UserDomain> users;

    /*Reni: un barrio tiene varias denuncias*/
    @OneToMany(mappedBy = "neighborhood")
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

    public CityDomain getCity() {
        return city;
    }

    public void setCity(CityDomain city) {
        this.city = city;
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
}
