package com.totospz.eshop.service;

import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.sede.SedeEditReq;
import com.totospz.eshop.domain.dto.sede.SedeRegReq;
import com.totospz.eshop.domain.dto.sede.SedeRes;
import com.totospz.eshop.domain.enums.Estado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.net.URISyntaxException;

public interface SedeService {
    SedeRes save(SedeRegReq sedReq, HttpServletRequest req);

    SedeRes findByCod(Integer sedCod);

    PaginationResponse findPagination(Pageable pageable, String sedNom, String ubiDpt, String ubiPro, String ubiDis,
                                      String sedRazSoc, String sedRuc, Estado sedEst, HttpServletRequest req) throws URISyntaxException;

    SedeRes update(SedeEditReq sedReq, Integer sedCod, HttpServletRequest req);

    SedeRes changeState(Integer sedCod, HttpServletRequest req);
}
