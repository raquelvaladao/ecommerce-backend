package com.api.estudo.services;


import com.api.estudo.dto.request.RequestItemCompraDTO;
import com.api.estudo.entities.Compra;
import com.api.estudo.entities.ItemCompra;
import com.api.estudo.repositories.CompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;

    public Compra pegarCompraDoUsuario(Long id) {
        return compraRepository.findByCompradorId(id);
    }

    public void adicionarItem(Compra compra, ItemCompra item) {
        compra.getItensCompra().add(item);
    }


    public ItemCompra removerItemDaCompra(Compra compra, Long produtoId) {
        ItemCompra item = compra
                .getItensCompra()
                .stream()
                .filter(itemCompra -> itemCompra.getProduto().getId().equals(produtoId))
                .collect(Collectors.toList()).get(0);
        compra.getItensCompra().remove(item);
        compra.setValorTotal(BigDecimal.ZERO);
        for (ItemCompra itemCompra : compra.getItensCompra())
            compra.setValorTotal(compra.getValorTotal().add(itemCompra.getValorTotal()));

        return item;
    }

    public Compra salvarCompra(Compra compra) {
        for (ItemCompra itemCompra : compra.getItensCompra())
            compra.setValorTotal(compra.getValorTotal().add(itemCompra.getValorTotal()));

        return compraRepository.save(compra);
    }


    public Compra persistirCompra(Compra compra) {
        compra.setValorTotal(BigDecimal.ZERO);
        for (ItemCompra itemCompra : compra.getItensCompra())
            compra.setValorTotal(compra.getValorTotal().add(itemCompra.getValorTotal()));

        return compraRepository.saveAndFlush(compra);
    }



    public boolean isProdutoNaCompra(Compra compra, Long id) {
        return compra
                .getItensCompra().stream()
                .anyMatch(item -> item.getProduto().getId().equals(id));
    }

    public ItemCompra alterarQuantidadeItem(Compra compra, RequestItemCompraDTO dto) {
        ItemCompra itemAlterado = compra
                .getItensCompra()
                .stream()
                .filter(itemCompra -> itemCompra.getProduto().getId().equals(dto.getProdutoId()))
                .collect(Collectors.toList()).get(0);
        itemAlterado.setQuantidade(dto.getQuantidade());
        itemAlterado.setValorTotal(BigDecimal.valueOf(dto.getQuantidade()).multiply(itemAlterado.getValorUnitario()));
        return itemAlterado;
    }

    public void deletarItensCarrinho(Compra compra) {
        compra.getItensCompra().clear();
        salvarCompra(compra);
    }

    public void atualizarSubtotal(Compra compra, ItemCompra item) {
        compra.setValorTotal(compra.getValorTotal().add(item.getValorTotal()));
        compraRepository.save(compra);
    }
}
