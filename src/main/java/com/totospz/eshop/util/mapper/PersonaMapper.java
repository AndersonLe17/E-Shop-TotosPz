package com.totospz.eshop.util.mapper;

import com.totospz.eshop.domain.dto.persona.PersonaDescRes;
import com.totospz.eshop.domain.dto.persona.PersonaEditReq;
import com.totospz.eshop.domain.dto.persona.PersonaRegReq;
import com.totospz.eshop.domain.enums.Documento;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Persona;
import com.totospz.eshop.domain.model.Usuario;

public class PersonaMapper {

    public static Persona personaMapper(PersonaRegReq perReq, Integer usuMod) {
        return Persona.builder()
                .perNom(perReq.getPerNom().toUpperCase())
                .perApePat(perReq.getPerApePat().toUpperCase())
                .perApeMat(perReq.getPerApeMat().toUpperCase())
                .perTipDoc(Documento.valueOf(perReq.getPerTipDoc()))
                .perNumDoc(perReq.getPerNumDoc())
                .perCorEle(perReq.getPerCorEle())
                .perEst(Estado.ACTIVO)
                .usuReg(Usuario.builder().usuCod(usuMod).build())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .build();
    }

    public static Persona personaMapper(PersonaEditReq perReq, Persona persona, Integer usuMod) {
        return Persona.builder()
                .perNom(perReq.getPerNom() != null ? perReq.getPerNom().toUpperCase() : persona.getPerNom())
                .perApePat(perReq.getPerApePat() != null ? perReq.getPerApePat().toUpperCase() : persona.getPerApePat())
                .perApeMat(perReq.getPerApeMat() != null ? perReq.getPerApeMat().toUpperCase() : persona.getPerApeMat())
                .perTipDoc(perReq.getPerTipDoc() != null ? Documento.valueOf(perReq.getPerTipDoc()) : persona.getPerTipDoc())
                .perNumDoc(perReq.getPerNumDoc() != null ? perReq.getPerNumDoc() : persona.getPerNumDoc())
                .perCorEle(perReq.getPerCorEle() != null ? perReq.getPerCorEle() : persona.getPerCorEle())
                .perEst(persona.getPerEst())
                .usuMod(Usuario.builder().usuCod(usuMod).build())
                .build();
    }

    public static PersonaDescRes personaDescResponseMapper(Persona persona) {
        return PersonaDescRes.builder()
                .perCod(persona.getPerCod())
                .perNom(persona.getPerNom())
                .perApePat(persona.getPerApePat())
                .perApeMat(persona.getPerApeMat())
                .perTipDoc(persona.getPerTipDoc())
                .perNumDoc(persona.getPerNumDoc())
                .perCorEle(persona.getPerCorEle())
                .build();
    }

}
