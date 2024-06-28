package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.CreateCarteiraDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.exception.CamposCpfEmailDuplicadosException;
import br.com.systemsgs.picpay.repository.CarteiraRepository;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public Carteira criarCarteira(CreateCarteiraDTO createCarteiraDTO) {

        var walletdb = carteiraRepository.findByCpfCnpjOrEmail(createCarteiraDTO.getCpfCnpj(), createCarteiraDTO.getEmail());

        if(walletdb.isPresent()){
            throw new CamposCpfEmailDuplicadosException();
        }

        return carteiraRepository.save(createCarteiraDTO.toCarteira());
    }
}
