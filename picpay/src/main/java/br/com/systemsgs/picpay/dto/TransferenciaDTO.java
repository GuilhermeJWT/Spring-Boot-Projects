package br.com.systemsgs.picpay.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    @DecimalMin(value = "0.01", message = "Valor Minimo da Transferência é 0.01.")
    @NotNull(message = "Informe um Valor da Transferência.")
    private BigDecimal value;


    @NotNull(message = "Informe o Pagador da Transferência.")
    private Long payer; //pagador

    @NotNull(message = "Informe um Benefeciário para a Transferência.")
    private Long payee; //recebedor

}
