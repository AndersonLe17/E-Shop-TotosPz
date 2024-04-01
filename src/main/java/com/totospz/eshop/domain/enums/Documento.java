package com.totospz.eshop.domain.enums;

public enum Documento {
    DNI(8),
    RUC(11),
    CE(12);

    private Integer cantDigits;

    Documento(Integer cantDigits) {
        this.cantDigits = cantDigits;
    }

    public Integer getCantDigits() {
        return cantDigits;
    }
}
