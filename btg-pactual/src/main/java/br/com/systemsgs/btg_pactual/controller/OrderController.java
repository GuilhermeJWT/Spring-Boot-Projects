package br.com.systemsgs.btg_pactual.controller;

import br.com.systemsgs.btg_pactual.dto.OrderResponse;
import br.com.systemsgs.btg_pactual.dto.PaginacaoResponse;
import br.com.systemsgs.btg_pactual.dto.PedidosClientesDTO;
import br.com.systemsgs.btg_pactual.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/cliente/{clienteId}/orders")
    public ResponseEntity<PedidosClientesDTO<OrderResponse>> listaOrdersClientes(
            @PathVariable("clienteId") Long clienteId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){

        var pageResponse = orderService.pesquisaClientePeloId(clienteId, PageRequest.of(page, pageSize));

        return ResponseEntity.ok(new PedidosClientesDTO<>(
                pageResponse.getContent(),
                PaginacaoResponse.fromPage(pageResponse)
        ));
    }
}
