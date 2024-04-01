package com.totospz.eshop.domain.dto.perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class PerfilDescRes {

    private Integer perfCod;

    private String perfNom;

}
