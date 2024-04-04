package com.totospz.eshop.domain.dto.auth;

import com.totospz.eshop.config.validation.annotation.NoBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class LoginReq {

    @NotNull(message = "El nombre de usuario/correo electronico no debe ser nulo.")
    @NoBlank(message = "El nombre de usuario/correo electronico no debe estar en blanco.")
    String username;

    @NotNull(message = "La contraseña no debe ser nulo.")
    @NoBlank(message = "La contraseña no debe estar en blanco.")
    String password;

}
