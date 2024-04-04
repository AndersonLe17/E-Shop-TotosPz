package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.config.validation.annotation.NoBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PerfilRegReq {

    @NotNull(message = "El nombre del perfil no debe ser nulo.")
    @NoBlank(message = "El nombre del perfil no debe estar en blanco.")
    @Size(min = 2, max = 25, message = "El nombre del perfil debe de contener entre 2 a 25 caracteres.")
    private String perfNom;

    @NotNull(message = "La descripción del perfil no debe ser nulo.")
    @NoBlank(message = "La descripción del perfil no debe estar en blanco.")
    @Size(min = 2, max = 128, message = "La descripción del perfil debe de contener entre 2 a 128 caracteres.")
    private String perfDes;

}
