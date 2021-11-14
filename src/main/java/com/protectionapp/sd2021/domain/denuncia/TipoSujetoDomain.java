package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tipo_sujeto")
public class TipoSujetoDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    /*Un tipo esta relacionado con varios sujetos*/
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private Set<SujetoDomain> sujetos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
