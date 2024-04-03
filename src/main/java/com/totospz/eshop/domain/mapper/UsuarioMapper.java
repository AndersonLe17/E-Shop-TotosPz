package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.domain.dto.usuario.UsuarioDescRes;
import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Perfil;
import com.totospz.eshop.domain.model.Persona;
import com.totospz.eshop.domain.model.Sede;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.util.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioMapper {

    public static Usuario usuarioMapper(UsuarioRegReq usuReq, Persona persona,
                                        Sede sede, Perfil perfil,
                                        PasswordEncoder passwordEncoder, Integer usuMod) {
        return Usuario.builder()
                .usuPer(persona)
                .usuNom(usuReq.getUsuNom())
                .usuCorEle(StringUtil.correoBuilder(usuReq))
                .usuCon(passwordEncoder.encode(persona.getPerNumDoc()))
                .usuPerf(perfil)
                .usuSed(sede)
                .usuEst(Estado.ACTIVO)
                .usuReg(Usuario.builder().usuCod(usuMod).build())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .build();
    }

    public static Usuario usuarioMapper(UsuarioEditReq usuReq, Usuario usuario,
                                        Persona persona, Sede sede, Perfil perfil,
                                        PasswordEncoder passwordEncoder, Integer usuMod) {
        return Usuario.builder()
                .usuCod(usuario.getUsuCod())
                .usuPer(persona)
                .usuNom(usuReq.getUsuNom() != null ? usuReq.getUsuNom() : usuario.getUsuNom())
                .usuCorEle(usuReq.getUsuNom() != null ? StringUtil.correoBuilder(usuReq) : usuario.getUsuCorEle())
                .usuCon(!persona.getPerNumDoc().equals(usuario.getUsuPer().getPerNumDoc()) ? passwordEncoder.encode(persona.getPerNumDoc()): usuario.getUsuCon())
                .usuPerf(perfil)
                .usuSed(sede)
                .usuEst(usuReq.getUsuEst() != null ? Estado.valueOf(usuReq.getUsuEst()) : usuario.getUsuEst())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .usuReg(usuario.getUsuReg())
                .build();
    }

    public static UsuarioRes usuarioResponseMapper(Usuario usuario) {
        return UsuarioRes.builder()
                .usuCod(usuario.getUsuCod())
                .usuPer(PersonaMapper.personaDescResponseMapper(usuario.getUsuPer()))
                .usuNom(usuario.getUsuNom())
                .usuCorEle(usuario.getUsuCorEle())
                .usuPerf(PerfilMapper.perfilDescResponseMapper(usuario.getUsuPerf()))
                .usuSed(SedeMapper.sedeDescResponseMapper(usuario.getUsuSed()))
                .usuEst(usuario.getUsuEst())
                .usuMod(usuarioDescResponseMapper(usuario.getUsuMod()))
                .fecHorMod(usuario.getFecHorMod())
                .build();
    }

    public static UsuarioDescRes usuarioDescResponseMapper(Usuario usuario) {
        return UsuarioDescRes.builder()
                .usuCod(usuario.getUsuCod())
                .usuNom(usuario.getUsuNom())
                .build();
    }

}
