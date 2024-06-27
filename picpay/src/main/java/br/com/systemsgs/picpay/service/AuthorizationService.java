package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.client.AuthorizationClient;
import br.com.systemsgs.picpay.entity.Transfer;
import br.com.systemsgs.picpay.exception.AuthorizationResponseException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transfer transfer){
        var response = authorizationClient.isAuthorized();

        if(response.getStatusCode().isError()){
            throw new AuthorizationResponseException();
        }

        return response.getBody().getAuthorized();
    }
}
