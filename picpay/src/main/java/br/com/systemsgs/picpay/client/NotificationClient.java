package br.com.systemsgs.picpay.client;

import br.com.systemsgs.picpay.entity.Transferencia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${client.notification.service.url}", name = "sendNotification", value = "sendNotification")
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transferencia transferencia);

}
