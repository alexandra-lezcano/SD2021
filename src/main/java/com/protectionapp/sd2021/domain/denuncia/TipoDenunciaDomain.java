package com.protectionapp.sd2021.domain.denuncia;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tipo_denuncia")
public class TipoDenunciaDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Integer id;

    @Column(name="titulo")
    private String titulo;

    @Column(name="descripcion")
    private String descripcion;

    @ManyToMany (mappedBy = "denuncias", cascade = CascadeType.ALL)
    private Set<TipoDenunciaDomain> tipos;

    public Integer getId(){return id; }
    public void setId(Integer id){this.id = id; }

    public String getTitlulo(){return titulo; }
    public void setTitulo (String titulo){this.titulo = titulo;}

    public String getDescripcion(){return descripcion; }
    public void setDescripcion(String descripcion){this.descripcion = descripcion; }

}
