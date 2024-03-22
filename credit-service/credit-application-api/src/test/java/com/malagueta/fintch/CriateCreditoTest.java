package com.malagueta.fintch;

import com.malagueta.fintch.api.CreditoAPI;
import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.output.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = App.class)
public class CriateCreditoTest {
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private  CapitalRepository capitalRepository;
    @Autowired
    private IntrestRepository intrestRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CreditoAPI creditoAPI;
    @Autowired
    private AppConfig config;

    private CreditService service;


    @Test
    public  void findByCriteriaWithOneVigor(){
        CreditoSatus satus=CreditoSatus.VIGOR;
        int clientId=3;
        int records =4;
        Assert.isTrue(creditoAPI.findByCriteria(records,satus,clientId).size()==1,"Garanta que os dados devolvam 1 requisto nas condicoes pedidas");;
    }


    @Test
    public  void findByCriteriaNoCritirea(){
        CreditoSatus satus=null;
        int clientId=0;
        int records =4;
        Assert.isTrue(creditoAPI.findByCriteria(records,satus,clientId).size()==4,"Garanta que os dados devolvam 1 requisto nas condicoes pedidas");;
    }

    @Test
    public void testCreatCredito(){


       ProductoEntity producto= ProductoEntity.builder().id(1l)
                        .capitalMin(10)
                        .capitalMax(100000)
                        .descricao("testing product")
                        .intervaloPrestacao(30).build();


        ;

        ClienteEntity cliente= new ClienteEntity();
        cliente.setId(1);
        CreditEntity credit=CreditEntity.builder().jurus(13)
                        //.doDate(ZonedDateTime.now().plusDays(60))
                              //  .creatDate(ZonedDateTime.now())
                                        .estado(CreditoSatus.PENDENTE)
                                                .valor(1000)
                                                        .producto(producto).cliente_id(1).cliente(cliente).build();


        Assert.isNull(getCreditoAPI().createCredito(credit, "test token autorization dunny"),"tem que devolver null a principio");
    }

    @Test
    public void findCreditoByClientIDTest(){
        long id=1;
        CriateCreditoTest criateCreditoTest=new CriateCreditoTest();
        List<CreditEntity> creditos=getCreditoAPI().findCreditoByClientID(id,"token");
        Assert.isTrue(creditos.size()>0,"make sure that cliente with id="+id+" has loans associeted");
    }
    @Test
    public void atualizarCredito(){

    }

    @Test
    public void listCredito(){

    }

    @Test
    public void atualizar(){


    }

    @Test
    public void findByID(){

    }

    @Test
    public void findByCreditoWithUpPaginationTest(){

        Assert.isTrue(getCreditoAPI().findByCreditoWithUpPagination(1,4,null,"token").size()>0,
                "garanta que temos creditos nas tabelas");;

    }
    @Test
    public void findCreditoByClientID(){

    }

    @Test
    public void findByCreditoWithPaginationPrevies(){

    }

    private CreditoAPI getCreditoAPI(){
        creditoAPI=new CreditoAPI( creditRepository,
                capitalRepository,
                intrestRepository,
                clienteRepository,
                productoRepository,
                 eventRepository,
                config);
        return creditoAPI;
    }
}
