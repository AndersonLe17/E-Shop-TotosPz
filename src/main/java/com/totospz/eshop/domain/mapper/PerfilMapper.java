package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.domain.dto.perfil.PerfilDescRes;
import com.totospz.eshop.domain.model.Perfil;

public class PerfilMapper {

    public static PerfilDescRes perfilDescResponseMapper(Perfil perfil) {
        return PerfilDescRes.builder()
                .perfCod(perfil.getPerfCod())
                .perfNom(perfil.getPerfNom())
                .build();
    }

}
