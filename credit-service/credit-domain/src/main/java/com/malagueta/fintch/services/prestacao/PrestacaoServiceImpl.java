package com.malagueta.fintch.services.prestacao;

//import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.services.capital.CapitalServiceDomain;
import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.services.intrest.IntrestServiceDomain;
import com.malagueta.fintch.services.value.CreditoSatus;
import com.malagueta.fintch.services.value.ErrorCatalog;
import com.malagueta.fintch.services.value.Estado;
import com.malagueta.fintch.entity.*;
import com.malagueta.fintch.port.input.services.PrestacaoService;
import com.malagueta.fintch.port.output.repository.*;
import org.jetbrains.annotations.NotNull;
//import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.List;



public class PrestacaoServiceImpl implements PrestacaoService {

    //private Logger log= FintechLogg.getLogger(PrestacaoServiceImpl.class);

    @Override
    public List<PrestacaoEntity> listar(Long creditID,
                                        Estado status,
                                        LocalDate begin,
                                        LocalDate end,
                                        @NotNull PrestacaoRepository prestacaoRepository) {
        List<PrestacaoEntity> prestacaos= prestacaoRepository.findByCreditoStatusDates(creditID, status, begin,end);
        prestacaos.stream().forEach(
                prestacao -> {
                    //log.info(prestacao.toString());
                }
        );
        return prestacaos;
    }


    public PrestacaoEntity atualizar(PrestacaoEntity prestacaoEntity, @NotNull PrestacaoRepository prestacaoRepository) {
        PrestacaoEntity newPrestacao=prestacaoRepository.criarAtulalizar(prestacaoEntity);

        //createdPrestacaoEvent.setEvent(newPrestacao);
        //createdPrestacaoEvent.emitte();
        return newPrestacao;
    }

    @Override
    public PrestacaoEntity create(PrestacaoEntity prestacaoEntity,
                                  @NotNull CapitalRepository capitalRepository,
                                  @NotNull IntrestRepository intrestRepository,
                                  @NotNull PrestacaoRepository prestacaoRepository,
                                  @NotNull CreditRepository creditRepository,
                                  @NotNull ProductoRepository productoRepository
                                  ) throws ServiceException {
        CapitalServiceDomain capitalServiceDomain= new CapitalServiceDomain(capitalRepository);
        IntrestServiceDomain intrestServiceDomain=new IntrestServiceDomain(intrestRepository);

        CapitalEntity capital=capitalServiceDomain.getLast(prestacaoEntity.getCredito());
        Double taxa=prestacaoEntity.getCredito().getJurus();

        validaPrestacao(prestacaoEntity);

        ProductoEntity productoEntity=productoRepository.findProductoById(
                creditRepository.findById(prestacaoEntity.getCredito().getId())
                        .getProducto().getId()
        );

        prestacaoEntity.getCredito().setProducto(productoEntity);
        prestacaoEntity.setVencimento(LocalDate.now().plusDays(productoEntity.getIntervaloPrestacao()));
        if(prestacaoEntity.getCredito().getJurus()<=0){
            taxa=Double.valueOf(productoEntity.getTaxa());
        }

        Double jurosSimples=jurosSimples(capital.getValor(),1,taxa);
        //calculando a prestacao
        IntrestEntity intrest= intrestServiceDomain.addMoney(prestacaoEntity.getCredito(),
                jurosSimples,"Criacao de prestacao");
        prestacaoEntity.setJurusPago(jurosSimples);
        prestacaoEntity.setIntrest(intrest);

        if(prestacaoEntity.getVencimento()!=null){
            LocalDate vencido=getUltimaPrestacao(prestacaoEntity.getCredito(),
                    prestacaoRepository,
                    creditRepository);
            vencido.plusDays(prestacaoEntity.getCredito().getProducto().getIntervaloPrestacao());
            prestacaoEntity.setVencimento(vencido);
        }

        //nao mexe no capital
        capitalServiceDomain.addMoney(prestacaoEntity.getCredito(),0.0,"saldo apos prestacao");
        prestacaoEntity.setCapital(capital);
        prestacaoEntity.setId(0l);
        PrestacaoEntity newPrestacao=prestacaoRepository.criarAtulalizar(prestacaoEntity);

        //createdPrestacaoEvent.setEvent(newPrestacao);
        //createdPrestacaoEvent.emitte();
        return newPrestacao;
    }

    private void validaPrestacao(PrestacaoEntity prestacaoEntity) throws SecurityException, ServiceException {
        if(!prestacaoEntity.getCredito().getEstado().equals(CreditoSatus.VIGOR)){
            throw new ServiceException(ErrorCatalog.NAO_PODE_CRIAR_PRESTACAO_PARA_CREDITOS_NAO_EM_VIGOR.toString());
        }
    }

    private LocalDate getUltimaPrestacao(CreditEntity creditEntity,
                                         @NotNull PrestacaoRepository prestacaoRepository,
                                         @NotNull CreditRepository creditRepository) {
        PrestacaoEntity prestacaoEntity=prestacaoRepository.findFirstByCreditoOrderByIdDesc(creditEntity);
        if(prestacaoEntity!=null){
            return prestacaoEntity.getVencimento();
        }else{
            return creditRepository.findById(creditEntity.getId()).getBeginDate();
        }
    }


    public PrestacaoEntity listarPorID(long id,
                                       @NotNull PrestacaoRepository prestacaoRepository) {
        return prestacaoRepository.getById(id);

    }

    public Double jurosSimples(Double capital,int tempo, Double taxa){
        return capital*taxa/100;
    }

}
