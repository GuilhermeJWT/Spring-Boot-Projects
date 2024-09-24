package br.com.systemsgs.dp_strategy_springboot.service;

import br.com.systemsgs.dp_strategy_springboot.strategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(@Qualifier("creditCardPayment") PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Executa o pagamento de acordo com a estratégia definida
    public void executePayment(BigDecimal amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Estratégia de pagamento não definida!");
        }
        paymentStrategy.pay(amount);
    }
}
