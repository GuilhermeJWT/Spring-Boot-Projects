package br.com.systemsgs.clean_architecture.controllers;

import br.com.systemsgs.clean_architecture.application.usercases.CreateUsuarioInterector;
import br.com.systemsgs.clean_architecture.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserController {

    private final CreateUsuarioInterector createUsuarioInterector;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUsuarioInterector createUsuarioInterector, UserDTOMapper userDTOMapper) {
        this.createUsuarioInterector = createUsuarioInterector;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping(value = "/salvar")
    CreateUserResponse salvarUsuario(@RequestBody CreateUserRequest createUserRequest){
        User userNegocio = userDTOMapper.toUser(createUserRequest);

        User user = createUsuarioInterector.salvarUsuario(userNegocio);

        return userDTOMapper.toResponse(user);
    }

}
