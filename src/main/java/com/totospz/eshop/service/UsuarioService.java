package com.totospz.eshop.service;

import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRes;
import com.totospz.eshop.domain.enums.Estado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.net.URISyntaxException;

public interface UsuarioService {

    UsuarioRes save(UsuarioRegReq usuReq, HttpServletRequest req);

    UsuarioRes findByCod(Integer usuCod);

    PaginationResponse findPagination(
            Pageable pageable, String usuNom, String perNom, Integer perfCod, Integer sedCod, Estado usuEst, HttpServletRequest req
    ) throws URISyntaxException;

    UsuarioRes update(UsuarioEditReq usuReq, Integer usuCod, HttpServletRequest req);

    UsuarioRes changeState(Integer usuCod, HttpServletRequest req);
}
