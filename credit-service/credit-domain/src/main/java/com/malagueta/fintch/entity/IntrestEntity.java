package com.malagueta.fintch.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Builder
@Getter
public class IntrestEntity {
    private Long id;
    private CreditEntity credito;
    private Double valor;
    private String descricao;
    private LocalDateTime enventDate;
}
