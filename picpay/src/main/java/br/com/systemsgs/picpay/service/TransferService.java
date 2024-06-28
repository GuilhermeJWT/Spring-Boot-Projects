package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.TransferDTO;
import br.com.systemsgs.picpay.entity.Transfer;
import br.com.systemsgs.picpay.entity.Wallet;
import br.com.systemsgs.picpay.exception.WalletNotFoundException;
import br.com.systemsgs.picpay.repository.TransferRepository;
import br.com.systemsgs.picpay.repository.WalletRepository;
import br.com.systemsgs.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.systemsgs.picpay.exception.SaldoInsuficienteException;
import br.com.systemsgs.picpay.exception.TransferenciaNaoAutorizadaException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(AuthorizationService authorizationService,
                           NotificationService notificationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {

        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }


    /***
     * Método para realizar uma Transferência de Dinheiro(Similar ao mundo Real)
     * @param transferDTO
     * @return Transfer
     */

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {

        var pagador = walletRepository.findById(transferDTO.getPayer())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.getPayer()));

        var recebedor = walletRepository.findById(transferDTO.getPayee())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.getPayee()));

        validateTransfer(transferDTO, pagador);

        pagador.debitar(transferDTO.getValue());
        recebedor.creditar(transferDTO.getValue());

        var transfer = new Transfer(pagador, recebedor, transferDTO.getValue());

        walletRepository.save(pagador);
        walletRepository.save(recebedor);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    /***
     * Método que valida uma Transferência
     * @param transferDTO
     * @param
     */
    private void validateTransfer(TransferDTO transferDTO, Wallet pagador) {

        if(!pagador.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!pagador.isValidaSaldoPagador(transferDTO.getValue())){
            throw new SaldoInsuficienteException();
        }

        if(!authorizationService.isAuthorized(transferDTO)){
            throw new TransferenciaNaoAutorizadaException();
        }

    }
}
