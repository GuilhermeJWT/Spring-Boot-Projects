package br.com.systemsgs.picpay.dto;

import br.com.systemsgs.picpay.entity.CarteiraTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraResponseDTO {

    private Long id;
    private String fullName;
    private String cpfCnpj;
    private String email;
    private CarteiraTipo carteiraTipo;

}
