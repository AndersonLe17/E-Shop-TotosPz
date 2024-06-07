package com.totospz.eshop.controller;

import com.totospz.eshop.config.response.ResponseHttp;
import com.totospz.eshop.domain.dto.perfil.PerfilEditReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.service.PerfilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping("/")
    public ResponseEntity<?> registrarPerfil(@Valid @RequestBody PerfilRegReq perfReq, HttpServletRequest req) {
        return ResponseHttp.created(perfilService.save(perfReq, req));
    }

    @GetMapping("/{perfCod}")
    public ResponseEntity<?> buscarPerfil(@PathVariable Integer perfCod) {
        return ResponseHttp.ok(perfilService.findByCod(perfCod));
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> listarPerfiles(@PageableDefault(sort = {"fecHorMod"}, direction = Sort.Direction.DESC) Pageable pageable,
                                            @RequestParam(name = "perfNom", required = false) String perfNom,
                                            @RequestParam(name = "perfEst", required = false) Estado perfEst,
                                            HttpServletRequest req) throws URISyntaxException {
        return ResponseHttp.ok(perfilService.findPagination(pageable, perfNom, perfEst, req));
    }

    @PutMapping("/{perfCod}")
    public ResponseEntity<?> editarPerfil(@Valid @RequestBody PerfilEditReq perfReq,
                                          @PathVariable Integer perfCod, HttpServletRequest req) {
        return ResponseHttp.ok(perfilService.update(perfReq, perfCod, req));
    }

    @PutMapping("/change-estado/{perfCod}")
    public ResponseEntity<?> cambiarEstadoPerfil(@PathVariable Integer perfCod, HttpServletRequest req) {
        return ResponseHttp.ok(perfilService.changeState(perfCod, req));
    }

}
