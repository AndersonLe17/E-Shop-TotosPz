package com.totospz.eshop.controller;

import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping("/")
    public ResponseEntity<?> registrarPerfil(@Valid @RequestBody PerfilRegReq perfilReq) {
        return null;
    }

}
