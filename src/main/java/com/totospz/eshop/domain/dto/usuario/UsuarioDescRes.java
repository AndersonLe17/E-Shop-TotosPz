package com.totospz.eshop.domain.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDescRes {

    private Integer usuCod;

    private String usuNom;

}
