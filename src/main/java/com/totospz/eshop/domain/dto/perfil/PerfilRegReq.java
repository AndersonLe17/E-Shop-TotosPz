package com.totospz.eshop.domain.dto.perfil;

import com.totospz.eshop.config.validation.annotation.NoBlank;
import com.totospz.eshop.domain.model.Perfil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO for {@link Perfil}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class PerfilRegReq {

    @NotNull(message = "El nombre del perfil no debe ser nulo.")
    @NoBlank(message = "El nombre del perfil no debe estar en blanco.")
    @Size(min = 2, max = 25, message = "El nombre del perfil debe de contener entre 2 a 25 caracteres.")
    private String perfNom;

    @NotNull(message = "La descripción del perfil no debe ser nulo.")
    @NoBlank(message = "La descripción del perfil no debe estar en blanco.")
    @Size(min = 2, max = 128, message = "La descripción del perfil debe de contener entre 2 a 128 caracteres.")
    private String perfDes;

    @NotNull(message = "El detalle del perfil no debe ser nulo.")
    @NoBlank(message = "El detalle del perfil no debe estar en blanco.")
    @Size(min = 2, max = 512, message = "El detalle del perfil debe de contener entre 2 a 512 caracteres.")
    private String perfDet;

}
