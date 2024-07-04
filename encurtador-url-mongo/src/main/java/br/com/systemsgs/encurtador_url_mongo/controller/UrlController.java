package br.com.systemsgs.encurtador_url_mongo.controller;

import br.com.systemsgs.encurtador_url_mongo.dto.UrlRequest;
import br.com.systemsgs.encurtador_url_mongo.dto.UrlResponse;
import br.com.systemsgs.encurtador_url_mongo.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/encurtador")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /***
     * Encurta uma URL
     * @param urlRequest
     * @param servletRequest
     * @return urlResponse
     */
    @PostMapping
    public ResponseEntity<UrlResponse> encurtadorURL(@RequestBody UrlRequest urlRequest, HttpServletRequest servletRequest){
        var response = urlService.encurtarURL(urlRequest);
        var redirecionaUrl = servletRequest.getRequestURL().toString().replace("/api/v1/encurtador", response.urlEncurtada());

        return ResponseEntity.ok(new UrlResponse(redirecionaUrl));
    }

    /***
     *  redireciona a URL
     * @param id
     * @return urlCompleta
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> redirecionaURL(@PathVariable("id") String id){
        var url = urlService.redirecionaURL(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(url.get().getUrlCompleta()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
    }

}
