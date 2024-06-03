package br.com.virandoprogramador.security_jwt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelProdutosDTO {

    private Long id;

    @NotBlank(message = "Nome deve ser Informado!")
    private String nome;

    @NotNull(message = "Valor deve ser Informado!")
    private BigDecimal valor;

    @NotNull(message = "Quantidade deve ser Informada!")
    private Integer quantidade;

}
