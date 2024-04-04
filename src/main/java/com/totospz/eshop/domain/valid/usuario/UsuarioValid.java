package com.totospz.eshop.domain.valid.usuario;

import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import com.totospz.eshop.domain.enums.Documento;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.repository.PersonaRepository;
import com.totospz.eshop.repository.UsuarioRepository;
import com.totospz.eshop.util.StringUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UsuarioValid {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public void validSave(UsuarioRegReq usuReq) {
        int cantDigits = Documento.valueOf(usuReq.getUsuPer().getPerTipDoc()).getCantDigits();
        if (cantDigits != usuReq.getUsuPer().getPerNumDoc().length())
            throw new ValidationException("El número de documento no corresponde al tipo de documento.");
        if (personaRepository.existsByPerNumDoc(usuReq.getUsuPer().getPerNumDoc()))
            throw new ValidationException("El número de documento ya se encuentra registrado.");
        if (personaRepository.existsByPerCorEle(usuReq.getUsuPer().getPerCorEle()))
            throw new ValidationException("El correo electrónico ya se encuentra registrado.");

        List<String> usernames = StringUtil.usernamesBuilder(usuReq.getUsuPer());
        usuReq.setUsuNom(usernames.stream()
                .filter(Objects::nonNull)
                .filter(u -> !usuarioRepository.existsByUsuNomIgnoreCase(u)).findFirst()
                .orElseThrow(() -> new ValidationException("La generación del usuario fallo, contacte con TI."))
        );
    }

    public void validUpdate(UsuarioEditReq usuReq, Usuario usuario) {
        if (usuReq.getUsuPer().getPerTipDoc() != null && usuReq.getUsuPer().getPerNumDoc() != null) {
            int cantDigits = Documento.valueOf(usuReq.getUsuPer().getPerTipDoc()).getCantDigits();
            if (cantDigits != usuReq.getUsuPer().getPerNumDoc().length())
                throw new ValidationException("El número de documento no corresponde al tipo de documento.");
            if (!usuReq.getUsuPer().getPerNumDoc().equals(usuario.getUsuPer().getPerNumDoc()))
                if (personaRepository.existsByPerNumDoc(usuReq.getUsuPer().getPerNumDoc()))
                    throw new ValidationException("El número de documento ya se encuentra registrado.");
        }

        if (usuReq.getUsuPer().getPerCorEle() != null)
            if (!usuReq.getUsuPer().getPerCorEle().equals(usuario.getUsuCorEle()))
                if (personaRepository.existsByPerCorEle(usuReq.getUsuPer().getPerCorEle()))
                    throw new ValidationException("El correo electrónico ya se encuentra registrado.");

        if (usuReq.getUsuPer().getPerNom() != null || usuReq.getUsuPer().getPerApePat() != null || usuReq.getUsuPer().getPerApeMat() != null) {
            List<String> usernames = StringUtil.usernamesBuilder(usuReq.getUsuPer());
            usuReq.setUsuNom(!usernames.contains(usuario.getUsuNom())
                    ? usernames.stream().filter(Objects::nonNull)
                            .filter(u -> !usuarioRepository.existsByUsuNomIgnoreCase(u)).findFirst()
                            .orElseThrow(() -> new ValidationException("La generación del usuario fallo, contacte con TI."))
                    : usuario.getUsuNom());
        }
    }

}
