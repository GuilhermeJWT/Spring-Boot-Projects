package br.com.systemsgs.btg_pactual.dto;

import org.springframework.data.domain.Page;

public record PaginacaoResponse(Integer page, Integer pageSize, Long totalItens, Integer totalPages) {

    public static PaginacaoResponse fromPage(Page<?> page){

        return new PaginacaoResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

}
