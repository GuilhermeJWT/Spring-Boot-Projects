package br.com.systemsgs.picpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_carteira")
public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "carteira_tipo_id")
    private CarteiraTipo carteiraTipo;

    public Carteira(String fullName, String cpfCnpj, String email, String password, CarteiraTipo carteiraTipo) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.carteiraTipo = carteiraTipo;
    }

    public boolean isTransferAllowedForWalletType() {
        return this.carteiraTipo.equals(CarteiraTipo.Enum.USER.get());
    }

    public boolean isValidaSaldoPagador(BigDecimal value) {
        return this.balance.doubleValue() > value.doubleValue();
    }

    public void debitar(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void creditar(BigDecimal value) {
        this.balance = this.balance.add(value);
    }
}
