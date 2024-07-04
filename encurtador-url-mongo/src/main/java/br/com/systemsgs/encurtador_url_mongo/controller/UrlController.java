package br.com.systemsgs.encurtador_url_mongo.controller;

import br.com.systemsgs.encurtador_url_mongo.dto.UrlRequest;
import br.com.systemsgs.encurtador_url_mongo.dto.UrlResponse;
import br.com.systemsgs.encurtador_url_mongo.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/encurtador")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> encurtadorURL(@RequestBody UrlRequest urlRequest, HttpServletRequest servletRequest){
        var response = urlService.encurtarURL(urlRequest);
        var redirecionaUrl = servletRequest.getRequestURL().toString().replace("/api/v1/encurtador", response.urlEncurtada());

        return ResponseEntity.ok(new UrlResponse(redirecionaUrl));
    }
}
