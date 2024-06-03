package br.com.virandoprogramador.security_jwt.repository;

import br.com.virandoprogramador.security_jwt.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ModelUser, Long> {

    Optional<ModelUser> findByEmail(String email);

}
