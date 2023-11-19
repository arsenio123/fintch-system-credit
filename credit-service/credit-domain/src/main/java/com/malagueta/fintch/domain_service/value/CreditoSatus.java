package com.malagueta.fintch.domain_service.value;

public enum CreditoSatus {
    PENDENTE("PENDENTE"),VIGOR("VIGOR"),CANCELADO("CANCELADO"),VENCIDO("VENCIDO");
    private final String status;


    CreditoSatus(final String status) {
        this.status=status;
    }
}
