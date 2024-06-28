package br.com.systemsgs.picpay.client;

import br.com.systemsgs.picpay.dto.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${client.authorization.service.url}", name = "isAuthorized", value = "isAuthorized")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponse> isAuthorized();

}
