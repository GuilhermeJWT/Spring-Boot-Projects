package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.dto.TransferenciaResponseDTO;
import br.com.systemsgs.picpay.entity.Transferencia;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.exception.CarteiraNaoEncontradaException;
import br.com.systemsgs.picpay.repository.TransferenciaRepository;
import br.com.systemsgs.picpay.repository.CarteiraRepository;
import br.com.systemsgs.picpay.exception.TransferenciaNaoPermitidaTipoCarteiraException;
import br.com.systemsgs.picpay.exception.SaldoInsuficienteException;
import br.com.systemsgs.picpay.exception.TransferenciaNaoAutorizadaException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferenciaService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferenciaRepository transferenciaRepository;
    private final CarteiraRepository carteiraRepository;
    private final ModelMapper mapper;

    public TransferenciaService(AuthorizationService authorizationService,
                                NotificationService notificationService,
                                TransferenciaRepository transferenciaRepository,
                                CarteiraRepository carteiraRepository,
                                ModelMapper mapper) {

        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferenciaRepository = transferenciaRepository;
        this.carteiraRepository = carteiraRepository;
        this.mapper = mapper;
    }


    /***
     * Método para realizar uma Transferência de Dinheiro(Similar ao mundo Real)
     * @param transferenciaDTO
     * @return Transfer
     */

    @Transactional
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO) {

        var pagador = carteiraRepository.findById(transferenciaDTO.getPagador())
                .orElseThrow(() -> new CarteiraNaoEncontradaException(transferenciaDTO.getPagador()));

        var recebedor = carteiraRepository.findById(transferenciaDTO.getRecebedor())
                .orElseThrow(() -> new CarteiraNaoEncontradaException(transferenciaDTO.getRecebedor()));

        validarTransferencia(transferenciaDTO, pagador);

        pagador.debitar(transferenciaDTO.getValor());
        recebedor.creditar(transferenciaDTO.getValor());

        var transfer = new Transferencia(pagador, recebedor, transferenciaDTO.getValor());

        carteiraRepository.save(pagador);
        carteiraRepository.save(recebedor);
        var transferResult = transferenciaRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    /***
     * Método para retornar todas as Transações
     * @return List<TransferenciaResponseDTO>
     */
    public List<TransferenciaResponseDTO> listarTransasoes() {
        var listaTransaferencia = transferenciaRepository.findAll();
        List<TransferenciaResponseDTO> listaTransferenciaResponse = new ArrayList<>();

        for (Transferencia transferencia : listaTransaferencia) {
            TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
            transferenciaResponseDTO.setIdTransferencia(transferencia.getId());
            transferenciaResponseDTO.setIdPagador(transferencia.getPagador().getId());
            transferenciaResponseDTO.setNomePagador(transferencia.getPagador().getFullName());
            transferenciaResponseDTO.setIdBeneficiario(transferencia.getRecebedor().getId());
            transferenciaResponseDTO.setNomeBeneficiario(transferencia.getRecebedor().getFullName());
            transferenciaResponseDTO.setValorTransferencia(transferencia.getValor());
            transferenciaResponseDTO.setDataTransacao(transferencia.getDataTransacao());

            listaTransferenciaResponse.add(transferenciaResponseDTO);
        }
        return listaTransferenciaResponse;
    }

    /***
     * Método que valida uma Transferência
     * @param transferenciaDTO
     * @param
     */
    private void validarTransferencia (TransferenciaDTO transferenciaDTO, Carteira pagador) {

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
