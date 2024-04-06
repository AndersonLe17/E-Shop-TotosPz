package com.totospz.eshop.domain.dto.sede;

import com.totospz.eshop.domain.dto.usuario.UsuarioDescRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Ubigeo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class SedeRes {

    private Integer sedCod;

    private String sedNom;

    private Ubigeo sedUbi;

    private String sedDir;

    private String sedRuc;

    private String sedRazSoc;

    private Estado sedEst;

    private UsuarioDescRes usuMod;

    private LocalDateTime fecHorMod;

}
