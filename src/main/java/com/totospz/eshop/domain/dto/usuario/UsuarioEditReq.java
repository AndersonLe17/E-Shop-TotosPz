package com.totospz.eshop.domain.dto.usuario;

import com.totospz.eshop.config.validation.annotation.EnumField;
import com.totospz.eshop.domain.dto.persona.PersonaEditReq;
import com.totospz.eshop.domain.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UsuarioEditReq {

    private PersonaEditReq usuPer;

    private Integer usuPerfCod;

    private Integer usuSedCod;

    private String usuNom;

    @EnumField(enumClass = Estado.class, message = "El estado del usuario no coincide.")
    private String usuEst;

}