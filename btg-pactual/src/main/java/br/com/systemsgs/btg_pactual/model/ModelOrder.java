package br.com.systemsgs.btg_pactual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tb_orders")
public class ModelOrder implements Serializable {

    @MongoId
    private Long id;

    @Indexed(name = "cliente_id_index")
    private Long clienteId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    private List<ModelOrderItens> items;
}
