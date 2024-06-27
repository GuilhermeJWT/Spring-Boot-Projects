package br.com.systemsgs.picpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_wallet_type")
public class WalletType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id_gen_wallet_type",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id_gen_wallet_type", sequenceName = "wallet_type_seq", initialValue = 2, allocationSize = 1)
    private Long id;

    @Column(name = "description")
    private String description;

}
