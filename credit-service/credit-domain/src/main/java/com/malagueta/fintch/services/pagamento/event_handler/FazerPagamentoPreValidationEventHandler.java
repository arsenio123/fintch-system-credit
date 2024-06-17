package com.malagueta.fintch.services.pagamento.event_handler;

import com.malagueta.fintch.entity.PagamentoEntity;
import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.services.value.EventHandler;

public class FazerPagamentoPreValidationEventHandler implements EventHandler {

    @Override
    public void action(Object object) throws ServiceException {
        try {
            PagamentoEntity pagamentoEntity= (PagamentoEntity) object;
        }catch (SecurityException ex){
            throw ex;
            //ex.printStackTrace();
        }

    }
}
