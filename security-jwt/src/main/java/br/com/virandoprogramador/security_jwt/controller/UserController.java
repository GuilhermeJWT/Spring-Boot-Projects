package br.com.virandoprogramador.security_jwt.controller;

import br.com.virandoprogramador.security_jwt.dto.CreateUserDTO;
import br.com.virandoprogramador.security_jwt.dto.JwtTokenDTO;
import br.com.virandoprogramador.security_jwt.dto.LoginUserDTO;
import br.com.virandoprogramador.security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginUsuario(@RequestBody LoginUserDTO loginUserDto) {
        JwtTokenDTO token = userService.autenticarUsuario(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody CreateUserDTO createUserDto) {
        userService.salvarUsuario(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
