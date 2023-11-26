package com.malagueta.fintch.port.output.logging;

public interface CreditLogging {
    public void debug(String message);
    public void info(String message);
    public void exception(Exception ex,String message);
}
