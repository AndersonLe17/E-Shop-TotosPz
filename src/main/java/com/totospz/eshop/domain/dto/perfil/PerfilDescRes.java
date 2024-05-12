package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.domain.model.Perfil;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for {@link Perfil}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class PerfilDescRes {

    private Integer perfCod;

    private String perfNom;

}
