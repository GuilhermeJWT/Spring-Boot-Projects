package br.com.systemsgs.picpay.client;

import br.com.systemsgs.picpay.entity.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${client.notification.service.url}")
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);

}
