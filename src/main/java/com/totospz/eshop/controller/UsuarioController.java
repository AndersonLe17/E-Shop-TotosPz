package com.totospz.eshop.controller;

import com.totospz.eshop.config.response.ResponseHttp;
import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity registrarUsuario(@Valid @RequestBody UsuarioRegReq usuReq, HttpServletRequest req) {
        return ResponseHttp.created(usuarioService.save(usuReq, req));
    }

    @GetMapping("/{usuCod}")
    public ResponseEntity buscarUsuario(@PathVariable Integer usuCod) {
        return ResponseHttp.ok(usuarioService.findByCod(usuCod));
    }

    @PreAuthorize("hasRole('CAJERO')")
    @GetMapping("/pagination")
    public ResponseEntity listarUsuarios(@PageableDefault(sort = {"fecHorMod"}, direction = Sort.Direction.DESC) Pageable pageable,
                                         @RequestParam(name = "usuNom", required = false) String usuNom,
                                         @RequestParam(name = "perNom", required = false) String perNom,
                                         @RequestParam(name = "perfCod", required = false) Integer perfCod,
                                         @RequestParam(name = "sedCod", required = false) Integer sedCod,
                                         @RequestParam(name = "usuEst", required = false) Estado usuEst,
                                         HttpServletRequest req) throws URISyntaxException {
        return ResponseEntity.ok(usuarioService.findPagination(pageable, usuNom, perNom, perfCod, sedCod, usuEst, req));
    }

    @PutMapping("/{usuCod}")
    public ResponseEntity editarUsuario(@Valid @RequestBody UsuarioEditReq usuReq,
                                        @PathVariable Integer usuCod, HttpServletRequest req) {
        return ResponseHttp.ok(usuarioService.update(usuReq, usuCod, req));
    }

}
