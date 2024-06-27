package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.CreateWalletDTO;
import br.com.systemsgs.picpay.entity.Wallet;
import br.com.systemsgs.picpay.exception.CamposCpfEmailDuplicadosException;
import br.com.systemsgs.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO createWalletDTO) {

        var walletdb = walletRepository.findByCpfCnpjOrEmail(createWalletDTO.getCpfCnpj(), createWalletDTO.getEmail());

        if(walletdb.isPresent()){
            throw new CamposCpfEmailDuplicadosException();
        }

        return walletRepository.save(createWalletDTO.toWallet());
    }
}
