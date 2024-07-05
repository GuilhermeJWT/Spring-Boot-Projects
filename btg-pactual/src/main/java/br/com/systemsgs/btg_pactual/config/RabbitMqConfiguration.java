package br.com.systemsgs.btg_pactual.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    public static final String BTG_PACTUAL_QUEUE = "btg-pactual-queue";

    /* Criação da fila no RabbitMq - ver no painel: Queues and Streams*/
    @Bean
    public Declarable orderQueue(){
        return new Queue(BTG_PACTUAL_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
