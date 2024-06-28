package br.com.systemsgs.picpay.repository;

import br.com.systemsgs.picpay.entity.CarteiraTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraTipoRepository extends JpaRepository<CarteiraTipo, Long> {

}
