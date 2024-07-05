package br.com.systemsgs.btg_pactual.repository;

import br.com.systemsgs.btg_pactual.model.ModelOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<ModelOrder, Long> {

    Page<ModelOrder> findAllByClienteId(Long clienteId, PageRequest pageRequest);

}
