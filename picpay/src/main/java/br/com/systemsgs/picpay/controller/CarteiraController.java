package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.dto.CarteiraResponseDTO;
import br.com.systemsgs.picpay.dto.CreateCarteiraDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.service.CarteiraService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    private final ModelMapper mapper;

    public CarteiraController(CarteiraService carteiraService, ModelMapper mapper) {
        this.carteiraService = carteiraService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<CarteiraResponseDTO> criarCarteira(@RequestBody @Valid CreateCarteiraDTO createCarteiraDTO){
        Carteira carteiraSalva = carteiraService.criarCarteira(createCarteiraDTO);
        CarteiraResponseDTO carteiraResponseDTO = mapper.map(carteiraSalva, CarteiraResponseDTO.class);

        return ResponseEntity.ok(carteiraResponseDTO);
    }
}
