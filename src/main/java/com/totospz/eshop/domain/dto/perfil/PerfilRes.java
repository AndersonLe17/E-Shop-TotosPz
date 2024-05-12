package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.domain.dto.usuario.UsuarioDescRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Perfil;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link Perfil}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class PerfilRes {

    private Integer perfCod;

    private String perfNom;

    private String perfDes;

    private String perfDet;

    private Estado perfEst;

    private UsuarioDescRes usuMod;

    private LocalDateTime fecHorMod;

}
