package com.totospz.eshop.controller;

import com.totospz.eshop.config.response.ResponseHttp;
import com.totospz.eshop.domain.dto.sede.SedeEditReq;
import com.totospz.eshop.domain.dto.sede.SedeRegReq;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.service.SedeService;
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
@RequestMapping("/api/sede")
@RequiredArgsConstructor
public class SedeController {

    private final SedeService sedeService;

    @PostMapping("/")
    public ResponseEntity<?> registrarPerfil(@Valid @RequestBody SedeRegReq sedReq, HttpServletRequest req) {
        return ResponseHttp.created(sedeService.save(sedReq, req));
    }

    @GetMapping("/{sedCod}")
    public ResponseEntity<?> buscarPerfil(@PathVariable Integer sedCod) {
        return ResponseHttp.ok(sedeService.findByCod(sedCod));
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> listarPerfiles(@PageableDefault(sort = {"fecHorMod"}, direction = Sort.Direction.DESC) Pageable pageable,
                                            @RequestParam(name = "sedNom", required = false) String sedNom,
                                            @RequestParam(name = "ubiDpt", required = false) String ubiDpt,
                                            @RequestParam(name = "ubiPro", required = false) String ubiPro,
                                            @RequestParam(name = "ubiDis", required = false) String ubiDis,
                                            @RequestParam(name = "sedRazSoc", required = false) String sedRazSoc,
                                            @RequestParam(name = "sedRuc", required = false) String sedRuc,
                                            @RequestParam(name = "sedEst", required = false) Estado sedEst,
                                            HttpServletRequest req) throws URISyntaxException {
        return ResponseEntity.ok(sedeService.findPagination(pageable, sedNom, ubiDpt, ubiPro, ubiDis, sedRazSoc, sedRuc, sedEst, req));
    }

    @PutMapping("/{sedCod}")
    public ResponseEntity<?> buscarPerfil(@Valid @RequestBody SedeEditReq sedeReq,
                                          @PathVariable Integer sedCod, HttpServletRequest req) {
        return ResponseEntity.ok(sedeService.update(sedeReq, sedCod, req));
    }

}
