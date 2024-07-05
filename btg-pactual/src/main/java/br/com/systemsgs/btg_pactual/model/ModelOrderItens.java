package br.com.systemsgs.btg_pactual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelOrderItens implements Serializable {

    private String produtos;

    private Integer quantidade;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal preco;
}
