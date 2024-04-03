package com.totospz.eshop.util;

import com.totospz.eshop.domain.dto.persona.PersonaEditReq;
import com.totospz.eshop.domain.dto.persona.PersonaRegReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StringUtil {

    public static List<String> usernamesBuilder(PersonaRegReq perReq) {

        return Arrays.asList(
                new StringBuilder(String.valueOf(perReq.getPerNom().toUpperCase().charAt(0)))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim(),
                perReq.getPerNom().split(" ").length > 1
                        ? new StringBuilder(String.valueOf(perReq.getPerNom().split(" ")[1].toUpperCase().charAt(0)))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim()
                        : null,
                perReq.getPerNom().split(" ").length > 1
                        ? new StringBuilder(String.valueOf(perReq.getPerNom().toUpperCase().charAt(0)))
                        .append(perReq.getPerNom().split(" ")[1].toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim()
                        : null,
                new StringBuilder(String.valueOf(perReq.getPerNom().toUpperCase().charAt(0)))
                        .append(perReq.getPerApePat().toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApeMat())).toString().trim()
        );
    }

    public static List<String> usernamesBuilder(PersonaEditReq perReq) {
        return Arrays.asList(
                new StringBuilder(perReq.getPerNom().toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim(),
                perReq.getPerNom().split(" ").length > 1
                        ? new StringBuilder(perReq.getPerNom().split(" ")[1].toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim()
                        : null,
                perReq.getPerNom().split(" ").length > 1
                        ? new StringBuilder(perReq.getPerNom().toUpperCase().charAt(0))
                        .append(perReq.getPerNom().split(" ")[1].toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApePat())).toString().trim()
                        : null,
                new StringBuilder(perReq.getPerNom().toUpperCase().charAt(0))
                        .append(perReq.getPerApePat().toUpperCase().charAt(0))
                        .append(StringUtils.capitalize(perReq.getPerApeMat())).toString().trim()
        );
    }

    public static String correoBuilder(UsuarioRegReq usuReq) {
        return new StringBuilder(usuReq.getUsuNom())
                .append("@totospz.com")
                .toString().toLowerCase();
    }

    public static String correoBuilder(UsuarioEditReq usuReq) {
        return new StringBuilder(usuReq.getUsuNom())
                .append("@totospz.com")
                .toString().toLowerCase();
    }

}
