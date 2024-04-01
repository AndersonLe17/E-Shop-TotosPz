package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.domain.dto.sede.SedeDescRes;
import com.totospz.eshop.domain.model.Sede;

public class SedeMapper {

    public static SedeDescRes sedeDescResponseMapper(Sede sede) {
        return SedeDescRes.builder()
                .sedCod(sede.getSedCod())
                .sedNom(sede.getSedNom())
                .build();
    }

}
