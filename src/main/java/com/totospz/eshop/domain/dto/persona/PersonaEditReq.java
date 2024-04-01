package com.totospz.eshop.domain.dto.persona;

import com.totospz.eshop.config.validation.annotation.EnumField;
import com.totospz.eshop.domain.enums.Documento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PersonaEditReq {

    @NotBlank(message = "El nombre no debe estar en blanco.")
    @Size(min = 2, max = 120, message = "El nombre debe de contener entre 2 a 120 caracteres.")
    private String perNom;

    @NotBlank(message = "El apellido paterno no debe estar en blanco.")
    @Size(min = 2, max = 100, message = "El apellido paterno debe de contener entre 2 a 100 caracteres.")
    private String perApePat;

    @NotBlank(message = "El apellido materno no debe estar en blanco.")
    @Size(min = 2, max = 100, message = "El apellido materno debe de contener entre 2 a 100 caracteres.")
    private String perApeMat;

    @EnumField(enumClass = Documento.class, message = "El tipo de documento no coincide.")
    private String perTipDoc;

    @NotBlank(message = "El número de documento no debe estar en blanco.")
    @Size(min = 8, max = 15, message = "El número de documento debe de contener entre 8 a 15 caracteres.")
    private String perNumDoc;

    @NotBlank(message = "El correo electrónico no debe estar en blanco.")
    @Email(message = "El correo electrónico no cumple con el formato.")
    private String perCorEle;

}
