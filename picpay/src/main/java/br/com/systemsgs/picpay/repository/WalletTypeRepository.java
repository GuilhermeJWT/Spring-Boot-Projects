package br.com.systemsgs.picpay.repository;

import br.com.systemsgs.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

}
