package br.com.systemsgs.picpay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_carteira_tipo")
public class CarteiraTipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    public enum Enum {

        USER(1L, "User"),
        LOJISTA(2L, "Lojista");

        private Long id;
        private String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public CarteiraTipo get(){
            return new CarteiraTipo(id, description);
        }
    }
}
