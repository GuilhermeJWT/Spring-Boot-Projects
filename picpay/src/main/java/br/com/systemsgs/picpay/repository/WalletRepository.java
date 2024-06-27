package br.com.systemsgs.picpay.repository;

import br.com.systemsgs.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
