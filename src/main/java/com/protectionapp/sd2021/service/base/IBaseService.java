package com.protectionapp.sd2021.service.base;

import com.protectionapp.sd2021.dto.base.BaseDTO;
import com.protectionapp.sd2021.dto.base.BaseResult;

import org.springframework.data.domain.Pageable;

public interface IBaseService<DTO extends BaseDTO, R extends BaseResult> {

    /* R E S T

     * Save - Post
     * getById & getAll - Get
     * update - Put
     * delete - Delete

     * */
    public DTO save(DTO dto);

    public DTO getById(Integer id);

    public R getAll(Pageable pageable);

    public DTO update(DTO dto, Integer id);

    public DTO delete(Integer id);

}
