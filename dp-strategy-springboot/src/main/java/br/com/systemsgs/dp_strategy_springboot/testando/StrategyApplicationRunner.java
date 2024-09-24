package br.com.systemsgs.dp_strategy_springboot.testando;

import br.com.systemsgs.dp_strategy_springboot.service.PaymentService;
import br.com.systemsgs.dp_strategy_springboot.strategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StrategyApplicationRunner implements CommandLineRunner {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    @Qualifier("creditCardPayment")
    private PaymentStrategy creditCardPayment;

    @Autowired
    @Qualifier("paypalPayment")
    private PaymentStrategy paypalPayment;

    @Autowired
    @Qualifier("boletoPayment")
    private PaymentStrategy boletoPayment;

    @Override
    public void run(String... args) throws Exception {
        BigDecimal amount = new BigDecimal("150.00");

        // Pagamento com Cartão de Crédito
        paymentService.setPaymentStrategy(creditCardPayment);
        paymentService.executePayment(amount);

        // Pagamento com PayPal
        paymentService.setPaymentStrategy(paypalPayment);
        paymentService.executePayment(amount);

        // Pagamento com Boleto Bancário
        paymentService.setPaymentStrategy(boletoPayment);
        paymentService.executePayment(amount);
    }
}
