package com.api.estudo.dto.request;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestItemCompraDTO {

    private Long produtoId;
    private Integer quantidade;

}
