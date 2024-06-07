package com.totospz.eshop.domain.dto.persona;

import com.totospz.eshop.config.validation.annotation.EnumField;
import com.totospz.eshop.config.validation.annotation.NoBlank;
import com.totospz.eshop.domain.enums.Documento;
import com.totospz.eshop.domain.model.Persona;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO for {@link Persona}
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class PersonaEditReq {

    @NoBlank(message = "El nombre de la persona no debe contener espacios en blanco.")
    @Size(min = 2, max = 120, message = "El nombre de la persona debe de contener entre 2 a 120 caracteres.")
    private String perNom;

    @NoBlank(message = "El apellido paterno de la persona no debe contener espacios en blanco.")
    @Size(min = 2, max = 100, message = "El apellido paterno de la persona debe de contener entre 2 a 100 caracteres.")
    private String perApePat;

    @NoBlank(message = "El apellido materno de la persona no debe contener espacios en blanco.")
    @Size(min = 2, max = 100, message = "El apellido materno de la persona debe de contener entre 2 a 100 caracteres.")
    private String perApeMat;

    @EnumField(enumClass = Documento.class, message = "El tipo de documento no coincide.")
    private String perTipDoc;

    @NoBlank(message = "El número de documento de la persona no debe contener espacios en blanco.")
    @Size(min = 8, max = 15, message = "El número de documento de la persona debe de contener entre 8 a 15 caracteres.")
    private String perNumDoc;

    @NoBlank(message = "El correo electrónico de la persona no debe contener espacios en blanco.")
    @Email(message = "El correo electrónico de la persona debe de tener el formato de correo.")
    @Size(min = 5, max = 255, message = "El correo electrónico de la persona debe de contener entre 5 a 255 caracteres.")
    private String perCorEle;

}
