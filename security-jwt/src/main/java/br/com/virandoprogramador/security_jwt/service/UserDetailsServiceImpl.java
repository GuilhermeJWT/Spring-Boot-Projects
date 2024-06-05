package br.com.virandoprogramador.security_jwt.service;

import br.com.virandoprogramador.security_jwt.model.ModelUser;
import br.com.virandoprogramador.security_jwt.model.ModelUserDetailsImpl;
import br.com.virandoprogramador.security_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelUser modelUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return new ModelUserDetailsImpl(modelUser);
    }
}
