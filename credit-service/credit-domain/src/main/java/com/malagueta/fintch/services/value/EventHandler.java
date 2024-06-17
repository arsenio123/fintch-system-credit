package com.malagueta.fintch.services.value;

import com.malagueta.fintch.services.exception.ServiceException;

public interface  EventHandler {
    public void action(Object object) throws ServiceException;
}
