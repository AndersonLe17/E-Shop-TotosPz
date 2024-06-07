package com.totospz.eshop.domain.dto.usuario;

import com.totospz.eshop.config.validation.annotation.EnumField;
import com.totospz.eshop.domain.dto.persona.PersonaEditReq;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Usuario;
import lombok.*;

/**
 * DTO for {@link Usuario}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class UsuarioEditReq {

    private PersonaEditReq usuPer;

    private Integer usuPerfCod;

    private Integer usuSedCod;

    private String usuNom;

    @EnumField(enumClass = Estado.class, message = "El estado del usuario no coincide.")
    private String usuEst;

}