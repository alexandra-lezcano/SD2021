package com.protectionapp.sd2021.domain.investigacion;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "investigacion")
public class InvestigacionDomain implements IBaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "fechaInicio")
    private String fechaInicio;
    @Column(name = "fechaFin")
    private String fechaFin;
    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "denuncia_id", referencedColumnName = "id")
    private DenunciaDomain denuncia;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="investigacion_denuncia",
            joinColumns = @JoinColumn(name = "investigacion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserDomain> users;

    public void setBasicFields(String fechaInicio, String fechaFin, String descripcion){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DenunciaDomain getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaDomain denuncia) {
        this.denuncia = denuncia;
    }

    public Set<UserDomain> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDomain> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "InvestigacionDomain{" +
                "id=" + id +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", denuncia=" + denuncia +
                ", users=" + users +
                '}';
    }
}
