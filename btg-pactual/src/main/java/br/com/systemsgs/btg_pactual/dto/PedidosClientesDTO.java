package br.com.systemsgs.btg_pactual.dto;

import java.util.List;

public record PedidosClientesDTO<T>(List<T> dados, PaginacaoResponse pagination) {}
