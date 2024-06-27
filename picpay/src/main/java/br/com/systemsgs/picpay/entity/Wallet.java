package br.com.systemsgs.picpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome deve ser Informado.")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Cpf/Cnpj deve ser Informado.")
    @CPF(message = "Formato de CPF Inválido.")
    @Column(unique = true, name = "cpf_cnpj")
    private String cpfCnpj;

    @NotBlank(message = "E-mail deve ser Informado.")
    @Email(message = "Formato de E-mail Inválido.")
    @Column(unique = true, name = "email")
    private String email;

    @NotBlank(message = "Password deve ser Informada.")
    @Column(name = "password")
    private String password;

    @DecimalMin(value = "0.1", message = "Informe um Valor Válido")
    @NotNull(message = "Valor deve ser Informado.")
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }
}
