package com.api.estudo.dto.mappers;

import com.api.estudo.dto.request.RequestItemCompraDTO;
import com.api.estudo.dto.response.ResponseItemCompraDTO;
import com.api.estudo.entities.ItemCompra;
import com.api.estudo.repositories.ProdutoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class ItemCompraMapper {


    @Autowired
    ProdutoRepository produtoRepository;

    public abstract ResponseItemCompraDTO fromEntity(ItemCompra entity);

    public ItemCompra fromDTO(RequestItemCompraDTO dto){
        if ( dto == null ) {
            return null;
        }

        ItemCompra itemCompra = new ItemCompra();

        itemCompra.setQuantidade( dto.getQuantidade() );

        itemCompra.setProduto( produtoRepository.findById(dto.getProdutoId()).get() );
        itemCompra.setValorUnitario( produtoRepository.findById(dto.getProdutoId()).get().getPreco() );
        itemCompra.setValorTotal( produtoRepository.findById(dto.getProdutoId()).get().getPreco().multiply(BigDecimal.valueOf(dto.getQuantidade())) );

        return itemCompra;
    }
}
