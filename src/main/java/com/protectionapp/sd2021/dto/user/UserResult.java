package com.protectionapp.sd2021.dto.user;


import com.protectionapp.sd2021.dto.base.BaseResult;
import org.apache.catalina.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/* TO-DO: Esta clase se va a encargar de conseguirme todas las denuncias de X usuario
 * El result no es mas que un contenedor de dtos*/
@XmlRootElement(name = "userResult")
public class UserResult extends BaseResult<UserDTO> {
    private static final long serialVersionUID = 1L;

    /* Implemento mis propios metodos para manejar listas ayudandome
     * de los metodos del padre*/
    @XmlElement
    public List<UserDTO> getUsers() {
        return getList();
    }

    public void setUsers(List<UserDTO> dtos) {
        super.setList(dtos);
    }
}
