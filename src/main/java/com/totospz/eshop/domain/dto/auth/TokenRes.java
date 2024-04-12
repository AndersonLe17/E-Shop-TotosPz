package com.totospz.eshop.domain.dto.auth;

import com.totospz.eshop.domain.dto.perfil.PerfilDescRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class TokenRes {
    private String token;
    private Integer usuCod;
    private String usuNom;
    private String usuCorEle;
    private PerfilDescRes usuPerf;
    private Long exp;
}
