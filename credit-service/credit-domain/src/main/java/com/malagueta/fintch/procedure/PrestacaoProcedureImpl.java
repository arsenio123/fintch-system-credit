package com.malagueta.fintch.procedure;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.port.output.repository.*;
import com.malagueta.fintch.services.prestacao.PrestacaoServiceImpl;
import com.malagueta.fintch.services.value.CreditoSatus;
import com.malagueta.fintch.services.value.Estado;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;


public class PrestacaoProcedureImpl implements PrestacaoProcedure{
    private PrestacaoRepository prestacaoRepository;
    private CreditRepository creditRepository;
    private CapitalRepository capitalRepository;
    private IntrestRepository intrestRepository;

    private ProductoRepository productoRepository;
    private LocalDate now=LocalDate.now();
    Logger log= Logger.getLogger("PrestacaoProcedureImpl");

    public PrestacaoProcedureImpl(PrestacaoRepository prestacaoRepository,
                                  CreditRepository creditRepository,
                                  CapitalRepository capitalRepository,
                                  IntrestRepository intrestRepository,
                                  ProductoRepository productoRepository){
        this.prestacaoRepository=prestacaoRepository;
        this.creditRepository=creditRepository;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.productoRepository=productoRepository;

    }
    @Override
    public void creatAutomatic() {
        this.creditRepository=creditRepository;
        this.prestacaoRepository=prestacaoRepository;
        //search for intreste on do day
        List<CreditEntity> creditEntitys=creditRepository.findCreditoByCriteria(0, CreditoSatus.VIGOR,0);


        creditEntitys.stream().forEach(creditEntity -> {
            if(creditEntity.getBeginDate().getDayOfMonth()== now.getDayOfMonth()){
                PrestacaoEntity prestacaoEntity=prestacaoRepository.findFirstByCreditoOrderByIdDesc(creditEntity);
                    //valida se a data da ultima prestacao mais o intervalo das prestacoes é inferior a data atual
                    if(isPrestacaoOverdo(prestacaoEntity)){

                        prestacaoEntity=new PrestacaoEntity();
                        prestacaoEntity.setCredito(creditEntity);
                        prestacaoEntity.setEstado(Estado.NAO_PAGA);

                        new PrestacaoServiceImpl().create( prestacaoEntity,
                                  capitalRepository,
                                  intrestRepository,
                                  prestacaoRepository,
                                  creditRepository,
                                  productoRepository
                        ) ;
                    }

            }
        });
        //log.info("creditos que estao em vigor "+creditEntityList.size());
    }

    private boolean isPrestacaoOverdo(PrestacaoEntity prestacaoEntity){
        if(prestacaoEntity==null){
            return true;
        }else{
            if(prestacaoEntity.getVencimento().isBefore(now))
                return true;
        }
        return false;
    }
}
