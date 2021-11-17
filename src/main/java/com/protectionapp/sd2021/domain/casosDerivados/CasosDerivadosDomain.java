package com.protectionapp.sd2021.domain.casosDerivados;

import com.protectionapp.sd2021.domain.base.IBaseDomain;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "casos_derivados")
public class CasosDerivadosDomain implements IBaseDomain {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denuncia_id", referencedColumnName = "id")
    private DenunciaDomain denuncia;*/

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
