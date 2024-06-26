package com.totospz.eshop.domain.valid.perfil;

import com.totospz.eshop.domain.dto.perfil.PerfilEditReq;
import com.totospz.eshop.domain.dto.perfil.PerfilRegReq;
import com.totospz.eshop.domain.model.Perfil;
import com.totospz.eshop.repository.PerfilRepository;
import com.totospz.eshop.util.StringUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfilValid {

    private final PerfilRepository perfilRepository;

    public void validSave(PerfilRegReq perfReq) {
        perfReq.setPerfNom(StringUtil.perfilBuilder(perfReq.getPerfNom()));
        if (perfilRepository.existsByPerfNomIgnoreCase(perfReq.getPerfNom()))
            throw new ValidationException("El nombre del perfil ya se encuentra registrado.");
    }

    public void validUpdate(PerfilEditReq perfReq, Perfil perfil) {
        if (perfReq.getPerfNom() != null) {
            perfReq.setPerfNom(StringUtil.perfilBuilder(perfReq.getPerfNom()));
            if (!perfil.getPerfNom().equals(perfReq.getPerfNom()))
                if (perfilRepository.existsByPerfNomIgnoreCase(perfReq.getPerfNom()))
                    throw new ValidationException("El nombre del perfil ya se encuentra registrado.");
        }
    }


}
