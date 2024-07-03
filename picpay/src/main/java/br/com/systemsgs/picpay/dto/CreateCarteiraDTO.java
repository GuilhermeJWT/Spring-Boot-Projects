package br.com.systemsgs.picpay.dto;

import br.com.systemsgs.picpay.entity.CarteiraTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarteiraDTO {

    private Long id;

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

    private CarteiraTipo.Enum carteiraTipo;

}
