package com.api.estudo.controller;

import com.api.estudo.entities.*;
import com.api.estudo.services.CompraService;
import com.api.estudo.services.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pedido")
@Api(tags = "Pedido")
@CrossOrigin(origins = "http://localhost:4200/**")
@Slf4j
@AllArgsConstructor
public class PedidoController {

    private final CompraService compraService;
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> enviarEnderecoAoPedido(@RequestBody Endereco endereco){
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = compraService.pegarCompraDoUsuario(usuario.getId());
        Pedido pedido = pedidoService.getPedido(usuario.getId());
        try{
            BigDecimal total = BigDecimal.ZERO;
            for(ItemCompra item : compra.getItensCompra()){
                total = total.add(item.getValorTotal());
            }
            if(pedido == null){
                pedido = Pedido.builder()
                        .endereco(endereco)
                        .total(total)
                        .comprador(usuario)
                        .dataVenda(LocalDateTime.now())
                        .build();
            } else {
                pedidoService.alterarParaPedidoNovo(pedido, total, endereco);
            }

            pedidoService.salvarPedido(pedido);
            compraService.deletarItensCarrinho(compra);
            return ResponseEntity.ok(pedido);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new Pedido());
        }
    }

    @ApiOperation(value = "Ver pedido", nickname = "verPedido", response = Pedido.class)
    @GetMapping
    public ResponseEntity<Pedido> verPedido() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pedido pedido = pedidoService.getPedido(usuario.getId());
        try {

            return ResponseEntity.ok(pedido);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
