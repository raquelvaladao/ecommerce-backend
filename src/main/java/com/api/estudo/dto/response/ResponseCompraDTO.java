package com.api.estudo.dto.response;


import com.api.estudo.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompraDTO {

    private Usuario comprador;
    private List<ResponseItemCompraDTO> itensCompra;
    private BigDecimal valorTotal;
}
