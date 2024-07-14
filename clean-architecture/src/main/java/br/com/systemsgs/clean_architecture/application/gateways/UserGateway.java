package br.com.systemsgs.clean_architecture.application.gateways;

import br.com.systemsgs.clean_architecture.domain.entity.User;

public interface UserGateway {

    User createUser(User user);

}
