package br.com.systemsgs.picpay.repository;

import br.com.systemsgs.picpay.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
