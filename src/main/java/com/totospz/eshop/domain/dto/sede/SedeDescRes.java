package com.totospz.eshop.domain.dto.sede;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class SedeDescRes {

    private Integer sedCod;

    private String sedNom;

}
