package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.entity.Transferencia;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.exception.CarteiraNaoEncontradaException;
import br.com.systemsgs.picpay.repository.TransferenciaRepository;
import br.com.systemsgs.picpay.repository.CarteiraRepository;
import br.com.systemsgs.picpay.exception.TransferenciaNaoPermitidaTipoCarteiraException;
import br.com.systemsgs.picpay.exception.SaldoInsuficienteException;
import br.com.systemsgs.picpay.exception.TransferenciaNaoAutorizadaException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferenciaService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferenciaRepository transferenciaRepository;
    private final CarteiraRepository walletRepository;

    public TransferenciaService(AuthorizationService authorizationService,
                                NotificationService notificationService,
                                TransferenciaRepository transferenciaRepository,
                                CarteiraRepository walletRepository) {

        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferenciaRepository = transferenciaRepository;
        this.walletRepository = walletRepository;
    }


    /***
     * Método para realizar uma Transferência de Dinheiro(Similar ao mundo Real)
     * @param transferenciaDTO
     * @return Transfer
     */

    @Transactional
    public Transferencia transfer(TransferenciaDTO transferenciaDTO) {

        var pagador = walletRepository.findById(transferenciaDTO.getPagador())
                .orElseThrow(() -> new CarteiraNaoEncontradaException(transferenciaDTO.getPagador()));

        var recebedor = walletRepository.findById(transferenciaDTO.getRecebedor())
                .orElseThrow(() -> new CarteiraNaoEncontradaException(transferenciaDTO.getRecebedor()));

        validateTransfer(transferenciaDTO, pagador);

        pagador.debitar(transferenciaDTO.getValor());
        recebedor.creditar(transferenciaDTO.getValor());

        var transfer = new Transferencia(pagador, recebedor, transferenciaDTO.getValor());

        walletRepository.save(pagador);
        walletRepository.save(recebedor);
        var transferResult = transferenciaRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    /***
     * Método que valida uma Transferência
     * @param transferenciaDTO
     * @param
     */
    private void validateTransfer(TransferenciaDTO transferenciaDTO, Carteira pagador) {

        if(!pagador.isTransferAllowedForWalletType()){
            throw new TransferenciaNaoPermitidaTipoCarteiraException();
        }

        if(!pagador.isValidaSaldoPagador(transferenciaDTO.getValor())){
            throw new SaldoInsuficienteException();
        }

        if(!authorizationService.isAuthorized(transferenciaDTO)){
            throw new TransferenciaNaoAutorizadaException();
        }
    }
}
