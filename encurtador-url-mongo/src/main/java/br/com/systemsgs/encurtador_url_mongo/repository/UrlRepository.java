package br.com.systemsgs.encurtador_url_mongo.repository;

import br.com.systemsgs.encurtador_url_mongo.model.ModelUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UrlRepository extends MongoRepository<ModelUrl, String> {

}
