package br.com.systemsgs.btg_pactual.dto;

import br.com.systemsgs.btg_pactual.model.ModelOrder;

import java.math.BigDecimal;

public record OrderResponse(Long orderId, Long codigoCliente, BigDecimal total){

    public static OrderResponse converteOrder(ModelOrder modelOrder){
        return new OrderResponse(modelOrder.getId(), modelOrder.getClienteId(), modelOrder.getTotal());
    }
}
