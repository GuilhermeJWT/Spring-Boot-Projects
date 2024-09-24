package br.com.systemsgs.dp_strategy_springboot.strategy.impl;

import br.com.systemsgs.dp_strategy_springboot.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("paypalPayment")
public class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Pagamento de " + amount + " realizado com PayPal.");
    }
}
