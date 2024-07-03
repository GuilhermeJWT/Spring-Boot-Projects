package br.com.systemsgs.picpay.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransferenciaResponseDTO {

    @JsonProperty(value = "id_transferencia")
    private UUID idTransferencia;

    @JsonProperty(value = "id_pagador")
    private Long idPagador;

    @JsonProperty(value = "nome_pagador")
    private String nomePagador;

    @JsonProperty(value = "id_beneficiario")
    private Long idBeneficiario;

    @JsonProperty(value = "nome_beneficiario")
    private String nomeBeneficiario;

    @JsonProperty(value = "data_transferencia")
    private Instant dataTransacao;

    @JsonProperty(value = "valor_transferencia")
    private BigDecimal valorTransferencia;

}
