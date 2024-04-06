package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.domain.dto.sede.SedeDescRes;
import com.totospz.eshop.domain.dto.sede.SedeEditReq;
import com.totospz.eshop.domain.dto.sede.SedeRegReq;
import com.totospz.eshop.domain.dto.sede.SedeRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Sede;
import com.totospz.eshop.domain.model.Ubigeo;
import com.totospz.eshop.domain.model.Usuario;

public class SedeMapper {

    public static SedeDescRes sedeDescResponseMapper(Sede sede) {
        return SedeDescRes.builder()
                .sedCod(sede.getSedCod())
                .sedNom(sede.getSedNom())
                .build();
    }

    public static Sede sedeMapper(SedeRegReq sedReq, Ubigeo ubigeo, Integer usuMod) {
        return Sede.builder()
                .sedNom(sedReq.getSedNom())
                .sedUbi(ubigeo)
                .sedDir(sedReq.getSedDir())
                .sedRuc(sedReq.getSedRuc())
                .sedRazSoc(sedReq.getSedRazSoc())
                .sedEst(Estado.ACTIVO)
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .usuReg(Usuario.builder().usuCod(usuMod).build())
                .build();
    }

    public static Sede sedeMapper(SedeEditReq sedReq, Sede sede, Ubigeo ubigeo, Integer usuMod) {
        return Sede.builder()
                .sedCod(sede.getSedCod())
                .sedNom(sedReq.getSedNom() != null ? sedReq.getSedNom() : sede.getSedNom())
                .sedUbi(ubigeo)
                .sedDir(sedReq.getSedDir() != null ? sedReq.getSedDir() : sede.getSedDir())
                .sedRuc(sedReq.getSedRuc() != null ? sedReq.getSedRuc() : sede.getSedRuc())
                .sedRazSoc(sedReq.getSedRazSoc() != null ? sedReq.getSedRazSoc() : sede.getSedRazSoc())
                .sedEst(sedReq.getSedEst() != null ? Estado.valueOf(sedReq.getSedEst()) : sede.getSedEst())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .usuReg(sede.getUsuReg())
                .build();
    }

    public static SedeRes sedeResponseMapper(Sede sede) {
        return SedeRes.builder()
                .sedCod(sede.getSedCod())
                .sedNom(sede.getSedNom())
                .sedUbi(sede.getSedUbi())
                .sedDir(sede.getSedDir())
                .sedRuc(sede.getSedRuc())
                .sedRazSoc(sede.getSedRazSoc())
                .sedEst(sede.getSedEst())
                .usuMod(UsuarioMapper.usuarioDescResponseMapper(sede.getUsuMod()))
                .fecHorMod(sede.getFecHorMod())
                .build();
    }
}
