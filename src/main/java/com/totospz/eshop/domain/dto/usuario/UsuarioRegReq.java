package com.totospz.eshop.domain.dto.usuario;

import com.totospz.eshop.domain.dto.persona.PersonaRegReq;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UsuarioRegReq {

    @NotNull(message = "Los datos personales no debe ser nulo.")
    private PersonaRegReq usuPer;

    @NotNull(message = "El perfil del usuario no debe ser nulo.")
    private Integer usuPerfCod;

    @NotNull(message = "La sede del usuario no debe ser nulo.")
    private Integer usuSedCod;

    private String usuNom;

}