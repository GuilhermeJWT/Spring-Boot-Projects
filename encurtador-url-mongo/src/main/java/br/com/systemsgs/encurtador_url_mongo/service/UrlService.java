package br.com.systemsgs.encurtador_url_mongo.service;

import br.com.systemsgs.encurtador_url_mongo.dto.UrlRequest;
import br.com.systemsgs.encurtador_url_mongo.dto.UrlResponse;
import br.com.systemsgs.encurtador_url_mongo.model.ModelUrl;
import br.com.systemsgs.encurtador_url_mongo.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse encurtarURL(UrlRequest urlRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(7, 15);
        }while (urlRepository.existsById(id));

        //Salva a URL encurtada com expiração de 1 minuto
        urlRepository.save(new ModelUrl(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        UrlResponse response = new UrlResponse(id);

        return response;
    }

    public Optional<ModelUrl> redirecionaURL(String id) {
        var pesquisaUrl = urlRepository.findById(id);
        return pesquisaUrl;
    }
}
