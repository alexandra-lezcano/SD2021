package com.protectionapp.sd2021.service.location;

import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.base.IBaseService;
import org.springframework.data.domain.Pageable;

public interface INeighborhoodService extends IBaseService<NeighborhoodDTO, NeighborhoodResult> {
    void addNeighborhoodToUser(UserDTO dto, UserDomain domain);
    NeighborhoodResult findAllByCityPaged(Integer city, Pageable pageable);
    public NeighborhoodResult getAllByName(Pageable pageable, String search);
}
