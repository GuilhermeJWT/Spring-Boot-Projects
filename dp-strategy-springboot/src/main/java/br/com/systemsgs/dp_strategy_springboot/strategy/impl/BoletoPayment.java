package br.com.systemsgs.dp_strategy_springboot.strategy.impl;

import br.com.systemsgs.dp_strategy_springboot.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("boletoPayment")
public class BoletoPayment implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Pagamento de " + amount + " realizado com Boleto Bancário.");
    }
}
