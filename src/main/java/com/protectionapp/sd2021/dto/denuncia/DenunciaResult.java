package com.protectionapp.sd2021.dto.denuncia;

import com.protectionapp.sd2021.dto.base.BaseResult;
import org.apache.catalina.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "denunciaResult")
public class DenunciaResult extends BaseResult<DenunciaDTO>{
    private static final long serialVersionUID = 1L;
}
