package com.totospz.eshop.domain.dto.sede;

import com.totospz.eshop.config.validation.annotation.NoBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class SedeRegReq {

    @NotNull(message = "El nombre de la sede es requerido.")
    @NoBlank(message = "El nombre de la sede no debe contener espacios en blanco.")
    @Size(min = 2, max = 128, message = "El nombre de la sede debe de contener entre 2 a 128 caracteres.")
    private String sedNom;

    @NotNull(message = "El ubigeo de la sede es requerido.")
    @NoBlank(message = "El ubigeo de la sede no debe contener espacios en blanco.")
    @Size(min = 6, max = 6, message = "El ubigeo de la sede debe de contener 6 caracteres.")
    private String sedUbiCod;

    @NotNull(message = "La dirección de la sede es requerido.")
    @NoBlank(message = "La dirección de la sede no debe contener espacios en blanco.")
    @Size(min = 2, max = 255, message = "La dirección de la sede debe de contener entre 2 a 255 caracteres.")
    private String sedDir;

    @NotNull(message = "El RUC de la sede es requerido.")
    @NoBlank(message = "El RUC de la sede no debe contener espacios en blanco.")
    @Size(min = 11, max = 11, message = "El RUC de la sede debe de contener 11 caracteres.")
    private String sedRuc;

    @NotNull(message = "La razón social de la sede es requerido.")
    @NoBlank(message = "La razón social de la sede no debe contener espacios en blanco.")
    @Size(min = 2, max = 255, message = "La razón social de la sede debe de contener entre 2 a 255 caracteres.")
    private String sedRazSoc;

}
