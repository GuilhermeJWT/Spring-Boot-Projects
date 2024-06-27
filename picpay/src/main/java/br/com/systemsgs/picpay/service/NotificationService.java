package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.client.NotificationClient;
import br.com.systemsgs.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer){
        try{
            logger.info("Enviando a Notificação....");

            var response = notificationClient.sendNotification(transfer);

            if(response.getStatusCode().isError()){
                logger.error("Erro durante o envio da Notificação.");
            }

        }catch (Exception exception){
            logger.error("Erro ao tentar notificar.", exception.getMessage());
        }
    }

}
