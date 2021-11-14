package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tipo_denuncia")
public class TipoDenunciaDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    public TipoDenunciaDomain() {
    }

    public TipoDenunciaDomain(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(mappedBy = "tipos")
    private Set<DenunciaDomain> denuncias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitlulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<DenunciaDomain> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(Set<DenunciaDomain> denuncias) {
        this.denuncias = denuncias;
    }
}
