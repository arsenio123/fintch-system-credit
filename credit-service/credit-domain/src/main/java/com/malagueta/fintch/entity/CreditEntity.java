package com.malagueta.fintch.entity;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@ToString
@Getter
@Setter
@Builder
public class CreditEntity {
    private Long id;
    private LocalDateTime creatDate;
    private LocalDateTime updateDate;

    private Long createdBy;

    private Long aprovadoPOr;

    private ClienteEntity cliente;

    private long valor;

    private LocalDate doDate;

    private LocalDate beginDate;

    private CreditoSatus estado;

    private double jurus;

    //private Set<Garantia> garanias;

    @ToString.Exclude
    private ProductoEntity producto;
    /**
     * Data da proxima prestacao a ser paga
     */
    private LocalDate proxima_Prestacao;


    private long cliente_id;

}
