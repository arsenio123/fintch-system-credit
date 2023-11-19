package com.malagueta.fintch.domain_service.value;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCatalog {

    LICENSA_EXPIRADA("LCNS_001","Licensa expirada contacte Loan service support para mais detalhes"),
    CREDITO_VALUE_CANT_BE_LESS_THAN_ZERO("CRED001","Capital deve ser maior que zero"),

    CREDITO_PRODUCT_CANT_BE_NULL("CRED002","Defina um procucto para o credito"),
    CREDITO_CLIENT_MAST_EXIST("CRED003","Selecione um Cliente para o Credito"),
    EXPIRED_PRODUCT_CANT_BE_NORMAL("PROD001","Producto Expirado nao pode voltar ao normal") ,
    NORMAL_PRODUCT_CANT_BE_PENDING("PROD002","Productos no estado Pendentes nao podem voltar a estar Normal"),
    CAPITAL_NAO_PODE_SER_INFERIOS_AO_MIN_PRODUCOT("CPTOD001","Capital nao pode ser inferior a valor do producto associado");
    @Getter
    private final String code;
    @Getter
    private final String message;
    ErrorCatalog(final String code,final String message) {

        this.code=code;
        this.message=message;
    }

}
