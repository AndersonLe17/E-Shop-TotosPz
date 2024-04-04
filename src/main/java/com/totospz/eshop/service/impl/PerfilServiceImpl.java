package com.totospz.eshop.service.impl;

import com.totospz.eshop.config.exception.EntityNotFoundException;
import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.perfil.PerfilEditReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.mapper.PaginationMapper;
import com.totospz.eshop.domain.mapper.PerfilMapper;
import com.totospz.eshop.domain.model.Perfil;
import com.totospz.eshop.domain.valid.perfil.PerfilValid;
import com.totospz.eshop.repository.PerfilRepository;
import com.totospz.eshop.service.PerfilService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {
    // Repositories
    private final PerfilRepository perfilRepository;
    // Utils
    private final PerfilValid perfilValid;

    @Override
    @Transactional
    public PerfilRes save(PerfilRegReq perfReq, HttpServletRequest req) {
        // * Validations
        perfilValid.validSave(perfReq);
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        Perfil perfil = perfilRepository.save(PerfilMapper.perfilMapper(perfReq, usuMod));
        // * Return
        return PerfilMapper.perfilResponseMapper(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilRes findByCod(Integer perfCod) {
        Perfil perfil = perfilRepository.findById(perfCod)
                .orElseThrow(() -> new EntityNotFoundException("Perfil", "Cod", perfCod));
        // * Return
        return PerfilMapper.perfilResponseMapper(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse findPagination(Pageable pageable, String perfNom, Estado perfEst, HttpServletRequest req) throws URISyntaxException {
        Page<Perfil> page = perfilRepository.pagePerfil(perfNom, perfEst, pageable);
        List<PerfilRes> data = page.map(p -> PerfilMapper.perfilResponseMapper(p)).stream().toList();
        // * Return
        return PaginationMapper.paginationResponseMapper(data, page, req);
    }

    @Override
    @Transactional
    public PerfilRes update(PerfilEditReq perfReq, Integer perfCod, HttpServletRequest req) {
        // * Validations
        Perfil perfil = perfilRepository.findById(perfCod)
                .orElseThrow(() -> new EntityNotFoundException("Perfil", "Cod", perfCod));
        perfilValid.validUpdate(perfReq, perfil);
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        perfil = perfilRepository.save(PerfilMapper.perfilMapper(perfReq, perfil, usuMod));
        // * Return
        return PerfilMapper.perfilResponseMapper(perfil);
    }
}
