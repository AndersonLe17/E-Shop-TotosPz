package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.config.validation.annotation.EnumField;
import com.totospz.eshop.config.validation.annotation.NoBlank;
import com.totospz.eshop.domain.enums.Estado;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PerfilEditReq {

    @NoBlank(message = "El nombre del perfil no debe estar en blanco.")
    @Size(min = 2, max = 25, message = "El nombre del perfil debe de contener entre 2 a 25 caracteres.")
    private String perfNom;

    @NoBlank(message = "La descripción del perfil no debe estar en blanco.")
    @Size(min = 2, max = 128, message = "La descripción del perfil debe de contener entre 2 a 128 caracteres.")
    private String perfDes;

    @EnumField(enumClass = Estado.class, message = "El estado del usuario no coincide.")
    private String perfEst;

}
