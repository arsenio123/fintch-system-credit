package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.entity.PagamentoEntity;
import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.port.input.services.PagamentoService;
import com.malagueta.fintch.port.output.repository.*;
//import lombok.extern.slf4j.Slf4j;

import java.util.List;

//@Slf4j
public class PagamentoServiceImpl implements PagamentoService {
    private PagamentoRepository pagamentoRepository;
    private CreditRepository creditoRepository;
    private PrestacaoRepository prestacaoRepository;
    private IntrestServiceDomain intrestServiceDomain;
    private CapitalServiceDomain capitalServiceDomain;

    private IntrestRepository intrestRepository;
    private CapitalRepository capitalRepository;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository,
                                CreditRepository creditoRepository,
                                PrestacaoRepository prestacaoRepository,
                                IntrestRepository intrestRepository,
                                CapitalRepository capitalRepository
    ){
        this.pagamentoRepository=pagamentoRepository;
        this.creditoRepository=creditoRepository;
        this. prestacaoRepository=prestacaoRepository;
        this. intrestRepository=intrestRepository;
        this.capitalRepository=capitalRepository;
        this.intrestServiceDomain=new IntrestServiceDomain(intrestRepository);
        this.capitalServiceDomain= new CapitalServiceDomain(capitalRepository);

    }
    @Override
    public PagamentoEntity fazerPagameto(PagamentoEntity pagamentoEntity) {
        //log.debug(pagamentoEntity.toString());

        long prestacaID=pagamentoEntity.getPrestacao().getId();
        //carrega a prestacao
        PrestacaoEntity prestacao=prestacaoRepository.getById(prestacaID);//find da prestacao desejada

        //atulaiza a prestacao do cliente
        IntrestEntity lastIntrest=intrestServiceDomain.getLast(prestacao.getCredito().getId());

        CapitalEntity lastCapital= capitalServiceDomain.getLast(prestacao.getCredito());
        // se os juros forem menos que o valor pago liquida os juros e amortiza o capital

        IntrestEntity newLastIntrest = IntrestEntity.builder().build();
        if(lastIntrest.getValor()<pagamentoEntity.getValorPago()){

            newLastIntrest=intrestServiceDomain.addMoney(prestacao.getCredito(),-lastIntrest.getValor(),"Liquidacao do juros do credito ate a data");

            CapitalEntity newLastCapital =newLastCapital=capitalServiceDomain.addMoney(prestacao.getCredito(),lastIntrest.getValor()-pagamentoEntity.getValorPago(),"Amortizacao do capital");


            pagamentoEntity.setCapital(newLastCapital);
        }else{
            newLastIntrest=intrestServiceDomain.addMoney(prestacao.getCredito(),-pagamentoEntity.getValorPago(),"Amortizacao do credito");
            pagamentoEntity.setCapital(lastCapital);
        }
        pagamentoEntity.setIntrest(newLastIntrest);


        pagamentoEntity=pagamentoRepository.fazerPagameto(pagamentoEntity);
        return pagamentoEntity;
    }

    @Override
    public List<PagamentoEntity> getPayBYprestacao(long id_prestacao) {
        return pagamentoRepository.getPayBYprestacao(id_prestacao);
    }
}
