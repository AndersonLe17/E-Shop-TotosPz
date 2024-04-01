package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.domain.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PerfilRegReq {
    private Integer perfCod;
    private String perfNom;
    private String perfDes;
    private Estado perfEst;
    private LocalDateTime fecHorMod;
}
