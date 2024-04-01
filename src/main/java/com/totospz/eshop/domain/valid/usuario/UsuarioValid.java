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

    public void validSave(UsuarioRegReq usuarioReq) {
        int cantDigits = Documento.valueOf(usuarioReq.getUsuPer().getPerTipDoc()).getCantDigits();
        if (cantDigits != usuarioReq.getUsuPer().getPerNumDoc().length())
            throw new ValidationException("El número de documento no corresponde al tipo de documento.");
        if (personaRepository.existsByPerNumDoc(usuarioReq.getUsuPer().getPerNumDoc()))
            throw new ValidationException("El número de documento ya se encuentra registrado.");
        if (personaRepository.existsByPerCorEle(usuarioReq.getUsuPer().getPerCorEle()))
            throw new ValidationException("El correo electrónico ya se encuentra registrado.");

        List<String> usernames = StringUtil.usernamesBuilder(usuarioReq.getUsuPer());
        usuarioReq.setUsuNom(usernames.stream()
                .filter(Objects::nonNull)
                .filter(u -> !usuarioRepository.existsByUsuNomIgnoreCase(u)).findFirst()
                .orElseThrow(() -> new ValidationException("La generación del usuario fallo, contacte con TI."))
        );
    }

    public void validUpdate(UsuarioEditReq usuarioReq, Usuario usuario) {
        if (usuarioReq.getUsuPer().getPerTipDoc() != null && usuarioReq.getUsuPer().getPerNumDoc() != null) {
            int cantDigits = Documento.valueOf(usuarioReq.getUsuPer().getPerTipDoc()).getCantDigits();
            if (cantDigits != usuarioReq.getUsuPer().getPerNumDoc().length())
                throw new ValidationException("El número de documento no corresponde al tipo de documento.");
            if (!usuarioReq.getUsuPer().getPerNumDoc().equals(usuario.getUsuPer().getPerNumDoc()))
                if (personaRepository.existsByPerNumDoc(usuarioReq.getUsuPer().getPerNumDoc()))
                    throw new ValidationException("El número de documento ya se encuentra registrado.");
        }

        if (usuarioReq.getUsuPer().getPerCorEle() != null)
            if (!usuarioReq.getUsuPer().getPerCorEle().equals(usuario.getUsuCorEle()))
                if (personaRepository.existsByPerCorEle(usuarioReq.getUsuPer().getPerCorEle()))
                    throw new ValidationException("El correo electrónico ya se encuentra registrado.");

        if (usuarioReq.getUsuPer().getPerNom() != null || usuarioReq.getUsuPer().getPerApePat() != null || usuarioReq.getUsuPer().getPerApeMat() != null) {
            List<String> usernames = StringUtil.usernamesBuilder(usuarioReq.getUsuPer());
            usuarioReq.setUsuNom(!usernames.contains(usuario.getUsuNom())
                    ? usernames.stream().filter(Objects::nonNull)
                            .filter(u -> !usuarioRepository.existsByUsuNomIgnoreCase(u)).findFirst()
                            .orElseThrow(() -> new ValidationException("La generación del usuario fallo, contacte con TI."))
                    : usuario.getUsuNom());
        }
    }

}
