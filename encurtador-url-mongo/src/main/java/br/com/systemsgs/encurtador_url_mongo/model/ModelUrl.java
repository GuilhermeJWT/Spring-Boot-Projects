package br.com.systemsgs.encurtador_url_mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "urls")
public class ModelUrl implements Serializable {

    @Id
    private String id;

    private String urlCompleta;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiracao;
}
