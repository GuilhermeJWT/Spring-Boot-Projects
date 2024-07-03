package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.dto.CreateCarteiraDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.exception.CamposCpfEmailDuplicadosException;
import br.com.systemsgs.picpay.exception.CarteiraNaoEncontradaException;
import br.com.systemsgs.picpay.repository.CarteiraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final ModelMapper mapper;

    public CarteiraService(CarteiraRepository carteiraRepository, ModelMapper mapper) {
        this.carteiraRepository = carteiraRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Carteira criarCarteira(CreateCarteiraDTO createCarteiraDTO) {
        validaCarteira(createCarteiraDTO);

        Carteira carteiraConvertida = mapper.map(createCarteiraDTO, Carteira.class);
        carteiraConvertida.setCarteiraTipo(createCarteiraDTO.getCarteiraTipo().get());

        var carteiraSalvar = carteiraRepository.save(carteiraConvertida);

        return carteiraSalvar;
    }

    public Optional<Carteira> pesquisaPorId(Long id){
        Optional<Carteira> carteira = Optional.ofNullable(carteiraRepository.findById(id)
                .orElseThrow(() -> new CarteiraNaoEncontradaException(id)));

        return carteira;
    }

    private void validaCarteira(CreateCarteiraDTO createCarteiraDTO){
        var carteira = carteiraRepository.
                findByCpfCnpjOrEmail(createCarteiraDTO.getCpfCnpj(),
                        createCarteiraDTO.getEmail());

        if(carteira.isPresent()){
            throw new CamposCpfEmailDuplicadosException();
        }
    }
}
