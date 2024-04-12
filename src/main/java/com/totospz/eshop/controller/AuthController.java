package com.totospz.eshop.controller;

import com.totospz.eshop.config.response.ResponseHttp;
import com.totospz.eshop.config.security.TokenService;
import com.totospz.eshop.domain.dto.auth.LoginReq;
import com.totospz.eshop.domain.dto.auth.TokenRes;
import com.totospz.eshop.domain.mapper.PerfilMapper;
import com.totospz.eshop.domain.model.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@Valid @RequestBody LoginReq loginReq) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        Usuario usuario = (Usuario) authenticate.getPrincipal();
        String jsonWebToken = tokenService.generateToken(usuario);
        return ResponseHttp.ok(
                TokenRes.builder()
                        .token(jsonWebToken)
                        .usuCod(usuario.getUsuCod())
                        .usuNom(usuario.getUsuNom())
                        .usuCorEle(usuario.getUsuCorEle())
                        .usuPerf(PerfilMapper.perfilDescResponseMapper(usuario.getUsuPerf()))
                        .exp(tokenService.getExpiredTime(jsonWebToken))
                        .build()
        );
    }

}
