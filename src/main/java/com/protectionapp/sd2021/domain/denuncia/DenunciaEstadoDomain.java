package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "denuncia_estado")
public class DenunciaEstadoDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    /*Un estado puede estar asociado a varias denuncias*/
    @OneToMany(mappedBy = "estado")
    private Set<DenunciaDomain> denuncias;

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<DenunciaDomain> getDenuncias() {
        return denuncias;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDenuncias(Set<DenunciaDomain> denuncias) {
        this.denuncias = denuncias;
    }
}
