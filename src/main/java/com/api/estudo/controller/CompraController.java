package com.api.estudo.controller;

import com.api.estudo.dto.request.RequestItemCompraDTO;
import com.api.estudo.dto.mappers.CompraMapper;
import com.api.estudo.dto.mappers.ItemCompraMapper;
import com.api.estudo.dto.response.ResponseCompraDTO;
import com.api.estudo.entities.Compra;
import com.api.estudo.entities.ItemCompra;
import com.api.estudo.entities.Usuario;
import com.api.estudo.services.CompraService;
import com.api.estudo.services.ItemCompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comprar")
@Api(tags = "Compra")
@CrossOrigin(origins = "http://localhost:4200/**")
@Slf4j
@AllArgsConstructor
public class CompraController {

    private final ItemCompraMapper mapper;
    private final CompraMapper compraMapper;

    private final CompraService compraService;
    private final ItemCompraService itemCompraService;

    @PostMapping
    @ApiOperation(value = "Salvar compra", nickname = "salvarCompra", response = Compra.class)
    public ResponseCompraDTO adicionarAoCarrinho(@RequestBody RequestItemCompraDTO dto) {
        ItemCompra item = mapper.fromDTO(dto);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = compraService.pegarCompraDoUsuario(usuario.getId());

        try {
            if (compra == null) {
                compra = Compra.builder()
                        .itensCompra(List.of(item))
                        .comprador(usuario).build();
            } else {
                if(compraService.isProdutoNaCompra(compra, dto.getProdutoId())) {
                    item = compraService.alterarQuantidadeItem(compra, dto);
                    itemCompraService.salvarItem(item);
                    return compraMapper.fromEntity(compra);
                }
                itemCompraService.salvarItem(item);
                compraService.adicionarItem(compra, item);
            }
            compraService.salvarCompra(compra);
            return compraMapper.fromEntity(compra);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseCompraDTO();
        }
    }


    @ApiOperation(value = "Ver carrinho", nickname = "verCarrinho", response = Compra.class)
    @GetMapping("carrinho")
    public ResponseEntity<ResponseCompraDTO> verCarrinho() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = compraService.pegarCompraDoUsuario(usuario.getId());
        try {
            if(compra == null) {
                compra = Compra.builder()
                        .comprador(usuario)
                        .itensCompra(new ArrayList<>())
                        .build();
                compraService.persistirCompra(compra);
            }
            return ResponseEntity.ok(compraMapper.fromEntity(compra));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Remover item do carrinho", nickname = "removerItem", response = Compra.class)
    @DeleteMapping("carrinho/remover")
    public ResponseEntity<ResponseCompraDTO> removerItem(@RequestBody Long produtoId) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = compraService.pegarCompraDoUsuario(usuario.getId());
        ItemCompra item;
        try {
            if(compraService.isProdutoNaCompra(compra, produtoId)) {
                item = compraService.removerItemDaCompra(compra, produtoId);
                itemCompraService.removerItemDoBanco(item);
                return ResponseEntity.ok(compraMapper.fromEntity(compra));
            }
        Compra response = compraService.salvarCompra(compra);
            return ResponseEntity.ok(compraMapper.fromEntity(compra));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Remover itens do carrinho", nickname = "removerTudo", response = Compra.class)
    @DeleteMapping("carrinho/remover/tudo")
    public ResponseEntity<ResponseCompraDTO> removerTudo() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Compra compra = compraService.pegarCompraDoUsuario(usuario.getId());
        try {
            if(compra != null) {
                compraService.deletarItensCarrinho(compra);
                return ResponseEntity.ok(compraMapper.fromEntity(compra));
            }
            return ResponseEntity.ok(new ResponseCompraDTO());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
