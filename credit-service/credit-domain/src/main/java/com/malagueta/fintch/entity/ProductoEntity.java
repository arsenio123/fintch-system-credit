package com.malagueta.fintch.entity;


import com.malagueta.fintch.domain_service.value.ProductoEstados;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ProductoEntity  {

    private Long id;
    /**
     * taxa de juros a que o credito esta sugeito
     */
    private Double taxa;
    /**
     * valor minimo que se pode pedir no producto
     */
    private long capitalMin;
    /**
     * valor maximo que se pode pedir no producto
     */
    private long capitalMax;
    /**
     * Descricao do producto
     */
    private String descricao;
    /**
     * estado do producto
     */
    private ProductoEstados estado;

    /**
     * Intervalo em dias das prestacoes a pagar
     */
    private int intervaloPrestacao;

}
