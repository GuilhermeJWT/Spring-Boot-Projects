package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.dto.TransferDTO;
import br.com.systemsgs.picpay.entity.Transfer;
import br.com.systemsgs.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> trasfer(@RequestBody @Valid TransferDTO transferDTO){
        var response = transferService.transfer(transferDTO);

        return ResponseEntity.ok(response);
    }

}
