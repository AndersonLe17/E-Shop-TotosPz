package com.totospz.eshop.service.impl;

import com.totospz.eshop.config.exception.EntityNotFoundException;
import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.sede.SedeEditReq;
import com.totospz.eshop.domain.dto.sede.SedeRegReq;
import com.totospz.eshop.domain.dto.sede.SedeRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.util.mapper.PaginationMapper;
import com.totospz.eshop.util.mapper.SedeMapper;
import com.totospz.eshop.domain.model.Sede;
import com.totospz.eshop.domain.model.Ubigeo;
import com.totospz.eshop.domain.valid.sede.SedeValid;
import com.totospz.eshop.repository.SedeRepository;
import com.totospz.eshop.repository.UbigeoRepository;
import com.totospz.eshop.service.SedeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeServiceImpl implements SedeService {
    // Repositories
    private final SedeRepository sedeRepository;
    private final UbigeoRepository ubigeoRepository;
    // Utils
    private final SedeValid sedeValid;

    @Override
    public SedeRes save(SedeRegReq sedReq, HttpServletRequest req) {
        // * Validations
        sedeValid.validSave(sedReq);
        Ubigeo ubigeo = ubigeoRepository.findById(sedReq.getSedUbiCod())
                .orElseThrow(() -> new EntityNotFoundException("Ubigeo", "Cod", sedReq.getSedUbiCod()));
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        Sede sede = sedeRepository.save(SedeMapper.sedeMapper(sedReq, ubigeo, usuMod));
        // * Return
        return SedeMapper.sedeResponseMapper(sede);
    }

    @Override
    public SedeRes findByCod(Integer sedCod) {
        Sede sede = sedeRepository.findById(sedCod)
                .orElseThrow(() -> new EntityNotFoundException("Sede", "Cod", sedCod));
        // * Return
        return SedeMapper.sedeResponseMapper(sede);
    }

    @Override
    public PaginationResponse findPagination(Pageable pageable, String sedNom, String ubiDpt, String ubiPro, String ubiDis,
                                             String sedRazSoc, String sedRuc, Estado sedEst, HttpServletRequest req) throws URISyntaxException {
        Page<Sede> page = sedeRepository.pageSede(sedNom, ubiDpt, ubiPro, ubiDis, sedRazSoc, sedRuc, sedEst, pageable);
        List<SedeRes> data = page.map(s -> SedeMapper.sedeResponseMapper(s)).stream().toList();
        // * Return
        return PaginationMapper.paginationResponseMapper(data, page, req);
    }

    @Override
    public SedeRes update(SedeEditReq sedReq, Integer sedCod, HttpServletRequest req) {
        // * Validations
        Sede sede = sedeRepository.findById(sedCod)
                        .orElseThrow(() -> new EntityNotFoundException("Sede", "Cod", sedCod));
        sedeValid.validUpdate(sedReq, sede);
        Ubigeo ubigeo = sedReq.getSedUbiCod() != null && !sede.getSedUbi().getUbiCod().equals(sedReq.getSedUbiCod())
                ? ubigeoRepository.findById(sedReq.getSedUbiCod())
                        .orElseThrow(() -> new EntityNotFoundException("Ubigeo", "Cod", sedReq.getSedUbiCod()))
                : sede.getSedUbi();
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        sede = sedeRepository.save(SedeMapper.sedeMapper(sedReq, sede, ubigeo, usuMod));
        // * Return
        return SedeMapper.sedeResponseMapper(sede);
    }

    @Override
    public SedeRes changeState(Integer sedCod, HttpServletRequest req) {
        // * Validations
        Sede sede = sedeRepository.findById(sedCod)
                .orElseThrow(() -> new EntityNotFoundException("Sede", "Cod", sedCod));
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        sede.setSedEst(sede.getSedEst().equals(Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO);
        sede.setUsuMod(Usuario.builder().usuCod(usuMod).build());
        sede = sedeRepository.save(sede);
        // * Return
        return SedeMapper.sedeResponseMapper(sede);
    }
}
