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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Carteira> criarCarteira(@RequestBody @Valid CreateCarteiraDTO createCarteiraDTO){
        var carteira = carteiraService.criarCarteira(createCarteiraDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(carteiraService.pesquisaPorId(carteira.getId())).toUri();

        return ResponseEntity.created(uri).body(carteira);
    }
}
