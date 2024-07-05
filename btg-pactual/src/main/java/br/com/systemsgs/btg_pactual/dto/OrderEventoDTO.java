package br.com.systemsgs.btg_pactual.dto;

import java.util.List;

public record OrderEventoDTO(Long codigoPedido, Long codigoCliente, List<OrderItemEventoDTO> itens) {}
