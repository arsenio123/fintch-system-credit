package com.malagueta.fintch.services.pagamento.obsevals;


import com.malagueta.fintch.services.pagamento.event_handler.FazerPagamentoPreValidationEventHandler;
import com.malagueta.fintch.services.value.EventObserver;


public class FazerPagamentoEntryPintObserval extends EventObserver {

    private static void subsribers(){
        OBSERVALS.add(new FazerPagamentoPreValidationEventHandler());


    }
}
