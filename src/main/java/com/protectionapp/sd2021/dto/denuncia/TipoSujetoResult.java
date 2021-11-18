package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;

import java.util.List;

public class TipoSujetoResult extends BaseResult<TipoSujetoDTO> {
    private static final long serialVersionUID = 1L;

    public void setTipoSujetoList(List<TipoSujetoDTO> dtos){super.setList(dtos);}
    public List<TipoSujetoDTO> getTipoSujetoList(){return super.getList();}
}
