package br.com.systemsgs.fileserver1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_file")
public class ModelFileServer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public ModelFileServer(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
