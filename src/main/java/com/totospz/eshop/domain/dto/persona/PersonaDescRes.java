package com.totospz.eshop.domain.dto.persona;

import com.totospz.eshop.domain.enums.Documento;
import com.totospz.eshop.domain.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class PersonaDescRes {

    private Integer perCod;

    private String perNom;

    private String perApePat;

    private String perApeMat;

    private Documento perTipDoc;

    private String perNumDoc;

    private String perCorEle;

}
