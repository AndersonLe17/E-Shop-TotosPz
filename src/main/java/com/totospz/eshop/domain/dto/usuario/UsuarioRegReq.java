package com.totospz.eshop.domain.dto.usuario;

import com.totospz.eshop.domain.dto.persona.PersonaRegReq;
import com.totospz.eshop.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO for {@link Usuario}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class UsuarioRegReq {

    @NotNull(message = "La persona del usuario es requerido.")
    private PersonaRegReq usuPer;

    @NotNull(message = "El perfil del usuario es requerido.")
    private Integer usuPerfCod;

    @NotNull(message = "La sede del usuario es requerido.")
    private Integer usuSedCod;

    private String usuNom;

}