package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.dto.CreateWalletDTO;
import br.com.systemsgs.picpay.entity.Wallet;
import br.com.systemsgs.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO createWalletDTO){
        var wallet = walletService.createWallet(createWalletDTO);

        return ResponseEntity.ok(wallet);
    }
}
