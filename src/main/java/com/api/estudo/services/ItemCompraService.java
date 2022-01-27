package com.api.estudo.services;


import com.api.estudo.entities.ItemCompra;
import com.api.estudo.repositories.ItemCompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ItemCompraService {

    private final ItemCompraRepository itemCompraRepository;

    public void salvarItem(ItemCompra item) {
        item.setValorTotal(BigDecimal.valueOf(item.getQuantidade()).multiply(item.getValorUnitario()));
        itemCompraRepository.saveAndFlush(item);
    }

    public void removerItemDoBanco(ItemCompra item) {
        itemCompraRepository.delete(item);
    }
}
