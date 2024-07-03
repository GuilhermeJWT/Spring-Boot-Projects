package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.dto.TransferenciaResponseDTO;
import br.com.systemsgs.picpay.entity.Transferencia;
import br.com.systemsgs.picpay.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transferencia")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> trasferencia(@RequestBody @Valid TransferenciaDTO transferenciaDTO){
        var response = transferenciaService.realizarTransferencia(transferenciaDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TransferenciaResponseDTO>> listaTransacoes(){
        return ResponseEntity.ok(transferenciaService.listarTransasoes());
    }

}
