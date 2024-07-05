package br.com.systemsgs.btg_pactual.service;

import br.com.systemsgs.btg_pactual.dto.OrderEventoDTO;
import br.com.systemsgs.btg_pactual.model.ModelOrder;
import br.com.systemsgs.btg_pactual.model.ModelOrderItens;
import br.com.systemsgs.btg_pactual.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void salvarOrderPayloadRabbitMQ(OrderEventoDTO orderEventoDTO){
        ModelOrder modelOrder = new ModelOrder();

        modelOrder.setId(orderEventoDTO.codigoPedido());
        modelOrder.setClienteId(orderEventoDTO.codigoCliente());
        modelOrder.setTotal(calculaTotalPedido(orderEventoDTO));

        /*Pegando os Itens do Payload, converte para a entidade de Itens*/
        modelOrder.setItems(orderEventoDTO.itens().stream()
                .map(itens -> new ModelOrderItens(
                        itens.produto(),
                        itens.quantidade(),
                        itens.preco()))
                .toList());

        orderRepository.save(modelOrder);
    }

    /*Multiplicando o Preço com a Quantidade para gerar o Valor Total do Pedido - Caso não tenha retorna 0*/
    private BigDecimal calculaTotalPedido(OrderEventoDTO orderEventoDTO) {
        return orderEventoDTO.itens().stream()
                .map(itens -> itens.preco().multiply(BigDecimal.valueOf(itens.quantidade())))
                .reduce(BigDecimal:: add)
                .orElse(BigDecimal.ZERO);
    }
}
