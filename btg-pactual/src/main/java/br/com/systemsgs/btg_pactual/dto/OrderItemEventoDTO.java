package br.com.systemsgs.btg_pactual.dto;

import java.math.BigDecimal;

public record OrderItemEventoDTO(String produto, Integer quantidade, BigDecimal preco){}
