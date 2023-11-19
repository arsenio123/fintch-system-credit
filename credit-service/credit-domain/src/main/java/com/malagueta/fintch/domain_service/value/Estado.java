package com.malagueta.fintch.domain_service.value;

public enum Estado{
    PAGA("PAGA"),NAO_PAGA("NAO_PAGA"),EM_VIGOR("EM_VIGOR"),EXPIRADO("EXPIRADO");
    private final String  estado;
    Estado(final String estado) {
        this.estado=estado;
    }
}