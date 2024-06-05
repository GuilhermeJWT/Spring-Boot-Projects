package br.com.virandoprogramador.security_jwt.service;

import br.com.virandoprogramador.security_jwt.dto.CreateUserDTO;
import br.com.virandoprogramador.security_jwt.dto.JwtTokenDTO;
import br.com.virandoprogramador.security_jwt.dto.LoginUserDTO;
import br.com.virandoprogramador.security_jwt.model.ModelRole;
import br.com.virandoprogramador.security_jwt.model.ModelUser;
import br.com.virandoprogramador.security_jwt.model.ModelUserDetailsImpl;
import br.com.virandoprogramador.security_jwt.repository.UserRepository;
import br.com.virandoprogramador.security_jwt.security.JwtTokenService;
import br.com.virandoprogramador.security_jwt.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    public void salvarUsuario(CreateUserDTO createUserDto) {
        ModelUser newUser = ModelUser.builder()
                .email(createUserDto.email())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .roles(List.of(ModelRole.builder().name(createUserDto.role()).build()))
                .build();

        userRepository.save(newUser);
    }

    public JwtTokenDTO autenticarUsuario(LoginUserDTO loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        ModelUserDetailsImpl modelUserDetails = (ModelUserDetailsImpl) authentication.getPrincipal();
        return new JwtTokenDTO(jwtTokenService.generateToken(modelUserDetails));
    }
}
