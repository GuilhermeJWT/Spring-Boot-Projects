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
@Table(name = "tb_wallet_type")
public class WalletType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    public enum Enum {

        USER(1L, "User"), // Usuario Comum
        MERCHANT(2L, "Merchant"); // Lojista

        private Long id;
        private String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public WalletType get(){
            return new WalletType(id, description);
        }
    }

}
