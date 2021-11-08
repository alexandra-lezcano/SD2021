package com.protectionapp.sd2021.dto.base;

import java.io.Serializable;
import java.util.List;

/*recordar que el <DTO extends BaseDTO>  es un placeholder para decir que todas las clases que heredan de esta tambien deben heredar de BaseDTO
 */
public abstract class BaseResult<DTO extends BaseDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DTO> _dtos;

    public Integer getTotal() {
        return null == _dtos ? 0 : _dtos.size();
    }

    /* Polimorfismo: Estos metodos son "protected" porque cada Result debe implementar su propia
     * version del metodo*/
    protected List<DTO> getList() {
        return _dtos;
    }

    protected void setList(List<DTO> dtos) {
        _dtos = dtos;
    }
}
