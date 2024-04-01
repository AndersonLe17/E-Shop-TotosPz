package com.totospz.eshop.domain.dto.usuario;

import com.totospz.eshop.domain.dto.perfil.PerfilDescRes;
import com.totospz.eshop.domain.dto.persona.PersonaDescRes;
import com.totospz.eshop.domain.dto.sede.SedeDescRes;
import com.totospz.eshop.domain.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class UsuarioRes {

    private Integer usuCod;

    private PersonaDescRes usuPer;

    private String usuNom;

    private String usuCorEle;

    private PerfilDescRes usuPerf;

    private SedeDescRes usuSed;

    private Estado usuEst;

    private UsuarioDescRes usuMod;

    private LocalDateTime fecHorMod;
}
