package com.protectionapp.sd2021.domain.user;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "cn")
    private Integer cn;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
   @JoinTable(
           name = "user_role",
           joinColumns = @JoinColumn(name="user_id"),
           inverseJoinColumns = @JoinColumn(name="role_id")
   )
    private Set<RoleDomain> roles;

    /*Hay muchos usuarios en una ciudad, una ciudad puede tener muchos usuarios*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityDomain city;

    /* Crea la tabla intermedia user_neighborhood, con la columna "user_id" y "neighborhood_id" */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_neighborhood",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "neighborhood_id")
    )
    private Set<NeighborhoodDomain> neighborhoods;

    @OneToMany(mappedBy = "trabajador_social", cascade = CascadeType.ALL)
    private Set<CasosDerivadosDomain> casos_derivados;


    /*Un trabajador social se ocupa de muchas denuncias*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<DenunciaDomain> denuncias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getCn() {
        return cn;
    }

    public void setCn(Integer cn) {
        this.cn = cn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleDomain> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDomain> role) {
        this.roles = role;
    }

    public Set<NeighborhoodDomain> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(Set<NeighborhoodDomain> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public CityDomain getCity() {
        return city;
    }

    public void setCity(CityDomain city) {
        this.city = city;
    }

    public Set<DenunciaDomain> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(Set<DenunciaDomain> denuncias) {
        this.denuncias = denuncias;
    }

    public Set<CasosDerivadosDomain> getCasos_derivados() {return casos_derivados;}

    public void setCasos_derivados(Set<CasosDerivadosDomain> casos_derivados) {this.casos_derivados = casos_derivados;}

    public void update(String name, String surname, String username, Integer cn, String address, String email, Integer phone,String password) {
        setName(name);
        setSurname(surname);
        setUsername(username);
        setCn(cn);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
    }
}
