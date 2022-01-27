package com.api.estudo.services;

import com.api.estudo.entities.Compra;
import com.api.estudo.entities.Endereco;
import com.api.estudo.entities.Pedido;
import com.api.estudo.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido){
        return pedidoRepository.saveAndFlush(pedido);
    }

    public Pedido getPedido(Long id) {
        return pedidoRepository.findByCompradorId(id);
    }

    public void alterarParaPedidoNovo(Pedido pedido, BigDecimal total, Endereco endereco) {
        pedido.setEndereco(endereco);
        pedido.setDataVenda(LocalDateTime.now());
        pedido.setTotal(total);
    }
}
