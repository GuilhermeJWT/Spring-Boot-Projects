package br.com.systemsgs.btg_pactual.listener;

import br.com.systemsgs.btg_pactual.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import br.com.systemsgs.btg_pactual.dto.OrderEventoDTO;

import static br.com.systemsgs.btg_pactual.config.RabbitMqConfiguration.BTG_PACTUAL_QUEUE;

@Slf4j
@Component
public class OrderListener {

    private final OrderService orderService;

    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = BTG_PACTUAL_QUEUE)
    public void listener(Message<OrderEventoDTO> mensagem){
        log.info("Mensagem consumida {}" ,mensagem);

        orderService.salvarOrderPayloadRabbitMQ(mensagem.getPayload());
    }
}
