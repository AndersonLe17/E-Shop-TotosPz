package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.domain.dto.perfil.PerfilDescRes;
import com.totospz.eshop.domain.dto.perfil.PerfilEditReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Perfil;
import com.totospz.eshop.domain.model.Usuario;

public class PerfilMapper {

    public static PerfilDescRes perfilDescResponseMapper(Perfil perfil) {
        return PerfilDescRes.builder()
                .perfCod(perfil.getPerfCod())
                .perfNom(perfil.getPerfNom())
                .build();
    }

    public static Perfil perfilMapper(PerfilRegReq perfReq, Integer usuMod) {
        return Perfil.builder()
                .perfNom(perfReq.getPerfNom())
                .perfDes(perfReq.getPerfDes())
                .perfDet(perfReq.getPerfDet())
                .perfEst(Estado.ACTIVO)
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .usuReg(Usuario.builder().usuCod(usuMod).build())
                .build();
    }

    public static Perfil perfilMapper(PerfilEditReq perfReq, Perfil perfil, Integer usuMod) {
        return Perfil.builder()
                .perfCod(perfil.getPerfCod())
                .perfNom(perfReq.getPerfNom() != null ? perfReq.getPerfNom() : perfil.getPerfNom())
                .perfDes(perfReq.getPerfDes() != null ? perfReq.getPerfDes() : perfil.getPerfDes())
                .perfDet(perfReq.getPerfDet() != null ? perfReq.getPerfDet() : perfil.getPerfDet())
                .perfEst(perfReq.getPerfEst() != null ? Estado.valueOf(perfReq.getPerfEst()) : perfil.getPerfEst())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .usuReg(perfil.getUsuReg())
                .build();
    }

    public static PerfilRes perfilResponseMapper(Perfil perfil) {
        return PerfilRes.builder()
                .perfCod(perfil.getPerfCod())
                .perfNom(perfil.getPerfNom())
                .perfDes(perfil.getPerfDes())
                .perfDet(perfil.getPerfDet())
                .perfEst(perfil.getPerfEst())
                .usuMod(UsuarioMapper.usuarioDescResponseMapper(perfil.getUsuMod()))
                .fecHorMod(perfil.getFecHorMod())
                .build();
    }
}
