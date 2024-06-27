package br.com.systemsgs.picpay.dto;

import br.com.systemsgs.picpay.entity.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


public class CreateWalletDTO {

    @NotBlank(message = "Nome deve ser Informado.")
    private String fullName;

    @NotBlank(message = "Cpf/Cnpj deve ser Informado.")
    @CPF(message = "Formato de CPF Inválido.")
    private String cpfCnpj;

    @NotBlank(message = "E-mail deve ser Informado.")
    @Email(message = "Formato de E-mail Inválido.")
    private String email;

    @NotBlank(message = "Password deve ser Informada.")
    private String password;

    @NotBlank(message = "Tipo da Carteira deve ser Informado.")
    private WalletType.Enum walletType;

}
