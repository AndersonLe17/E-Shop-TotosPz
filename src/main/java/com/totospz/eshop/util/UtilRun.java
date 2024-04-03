package com.totospz.eshop.util;

import com.totospz.eshop.domain.dto.persona.PersonaRegReq;

public class UtilRun {
    public static void main(String[] args) {
        PersonaRegReq perReq = new PersonaRegReq();
        perReq.setPerNom("Anderson Leonardo");
        perReq.setPerApePat("Orellana");
        perReq.setPerApeMat("Espiritu");
//        System.out.println(new StringBuilder(perReq.getPerNom().toUpperCase().charAt(0)).append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim());
        StringUtil.usernamesBuilder(perReq).stream().forEach(System.out::println);
    }
}
