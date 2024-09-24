package br.com.systemsgs.dp_strategy_springboot.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {

    void pay(BigDecimal amount);

}
