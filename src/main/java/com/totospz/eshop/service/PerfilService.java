package com.totospz.eshop.service;

import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.perfil.PerfilEditReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRes;
import com.totospz.eshop.domain.enums.Estado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.net.URISyntaxException;

public interface PerfilService {
    PerfilRes save(PerfilRegReq perfReq, HttpServletRequest req);

    PerfilRes findByCod(Integer perfCod);

    PaginationResponse findPagination(Pageable pageable, String perfNom, Estado perfEst, HttpServletRequest req) throws URISyntaxException;

    PerfilRes update(PerfilEditReq perfReq, Integer perfCod, HttpServletRequest req);

    Object changeState(Integer perfCod, HttpServletRequest req);
}
