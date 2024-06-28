package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.dto.CreateCarteiraDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Carteira> criarCarteira(@RequestBody @Valid CreateCarteiraDTO createCarteiraDTO){
        var wallet = carteiraService.criarCarteira(createCarteiraDTO);

        return ResponseEntity.ok(wallet);
    }
}
