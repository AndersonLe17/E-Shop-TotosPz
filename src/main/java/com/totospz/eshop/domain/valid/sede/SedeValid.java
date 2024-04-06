package com.totospz.eshop.domain.valid.sede;

import com.totospz.eshop.domain.dto.sede.SedeEditReq;
import com.totospz.eshop.domain.dto.sede.SedeRegReq;
import com.totospz.eshop.domain.model.Sede;
import com.totospz.eshop.repository.SedeRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SedeValid {

    private final SedeRepository sedeRepository;

    public void validSave(SedeRegReq sedeReq) {
        if (sedeRepository.existsBySedNomIgnoreCase(sedeReq.getSedNom()))
            throw new ValidationException("El nombre de la sede ya se encuentra registrado.");
        if (sedeRepository.existsBySedRuc(sedeReq.getSedRuc()))
            throw new ValidationException("El RUC de la sede ya se encuentra registrado.");
        if (sedeRepository.existsBySedRazSocIgnoreCase(sedeReq.getSedRazSoc()))
            throw new ValidationException("La razón social de la sede ya se encuentra registrado.");
    }

    public void validUpdate(SedeEditReq sedeReq, Sede sede) {
        if (sedeReq.getSedNom() != null && !sede.getSedNom().equals(sedeReq.getSedNom()))
            if (sedeRepository.existsBySedNomIgnoreCase(sedeReq.getSedNom()))
                throw new ValidationException("El nombre de la sede ya se encuentra registrado.");
        if (sedeReq.getSedRuc() != null && !sede.getSedRuc().equals(sedeReq.getSedRuc()))
            if (sedeRepository.existsBySedRuc(sedeReq.getSedRuc()))
                throw new ValidationException("El RUC de la sede ya se encuentra registrado.");
        if (sedeReq.getSedRazSoc() != null && !sede.getSedRazSoc().equals(sedeReq.getSedRazSoc()))
            if (sedeRepository.existsBySedRazSocIgnoreCase(sedeReq.getSedRazSoc()))
                throw new ValidationException("La razón social de la sede ya se encuentra registrado.");
    }

//    public void validUpdate(SedeEditReq sedeReq, Sede sede) {
//        validateField(sedeReq.getSedNom(), sede.getSedNom(), "nombre", sedeRepository::existsBySedNomIgnoreCase);
//        validateField(sedeReq.getSedRuc(), sede.getSedRuc(), "ruc", sedeRepository::existsBySedRuc);
//        validateField(sedeReq.getSedRazSoc(), sede.getSedRazSoc(), "razón social", sedeRepository::existsBySedRazSocIgnoreCase);
//    }
//
//    private void validateField(String value, String currentValue, String field, Predicate<String> existsBy) {
//        if (value != null && !value.equals(currentValue)) {
//            if (existsBy.test(value)) {
//                throw new ValidationException("El " + field + " de la sede ya se encuentra registrado.");
//            }
//        }
//    }

//    public void validUpdate(SedeEditReq sedeReq, Sede sede) {
//        String sedNom = sedeReq.getSedNom();
//        if (StringUtils.hasText(sedNom) && !sedNom.equals(sede.getSedNom())) {
//            if (sedeRepository.existsBySedNomIgnoreCase(sedNom)) {
//                throw new ValidationException("El nombre de la sede ya se encuentra registrado.");
//            }
//        }
//
//        validateField(sedeReq.getSedRuc(), sede::getSedRuc, "El RUC de la sede ya se encuentra registrado.");
//        validateField(sedeReq.getSedRazSoc(), sede::getSedRazSoc, "La razón social de la sede ya se encuentra registrada.");
//    }
//
//    private <T> void validateField(T fieldValue, Supplier<T> getter, String errorMessage) {
//        if (Objects.nonNull(fieldValue) && !fieldValue.equals(getter.get())) {
//            if (sedeRepository.existsByFieldIgnoreCase(fieldValue)) {
//                throw new ValidationException(errorMessage);
//            }
//        }
//    }
}
