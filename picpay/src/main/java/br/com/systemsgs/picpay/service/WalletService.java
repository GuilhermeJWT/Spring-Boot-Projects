package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.CreateWalletDTO;
import br.com.systemsgs.picpay.entity.Wallet;
import br.com.systemsgs.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO createWalletDTO) {
        return walletRepository.save(createWalletDTO.toWallet());
    }
}
