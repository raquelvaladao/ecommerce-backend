package com.api.estudo.controller;


import com.api.estudo.dto.mappers.ProdutoMapper;
import com.api.estudo.dto.request.RequestProdutoDTO;
import com.api.estudo.dto.response.ResponseProdutoDTO;
import com.api.estudo.entities.Produto;
import com.api.estudo.entities.Usuario;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.exceptions.InputInvalidoException;
import com.api.estudo.services.ProdutoService;
import com.api.estudo.util.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
@Api(tags = "Produtos")
@CrossOrigin(origins = "http://localhost:4200/**")
@Slf4j
@AllArgsConstructor
public class ProdutoController {


    private final ProdutoService produtoService;
    private final ProdutoMapper mapper;

    @PostMapping(consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    @ApiOperation(value = "Cadastrar produto", nickname = "salvarProduto", response = ResponseProdutoDTO.class)
    public ResponseEntity<ResponseProdutoDTO> salvarProduto(@RequestPart(name = "body") String dto,
                                                            @RequestPart(name = "image") MultipartFile multipartFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestProdutoDTO dtoProduto = objectMapper.readValue(dto, RequestProdutoDTO.class);
        Usuario donoProduto = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        verSeFotoValida(multipartFile);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Produto produto = mapper.fromDTO(dtoProduto);
        produto.setFoto(fileName);
        produto.setDono(donoProduto);

        produtoService.salvarProduto(produto);
        String uploadDir = "produtos-photos/" + produto.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return ResponseEntity.ok(mapper.fromEntity(produto));
    }

    @GetMapping
    @ApiOperation(value = "Listar politicos", nickname = "listarPoliticos", response = ResponseProdutoDTO.class)
    public ResponseEntity<List<ResponseProdutoDTO>> listarProdutos() {
        try {
            List<ResponseProdutoDTO> produtos = produtoService
                    .listarTodosProdutos().stream()
                    .map(mapper::fromEntity)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Achar produto por id", nickname = "buscarProduto", response = ResponseProdutoDTO.class)
    @GetMapping("{id}")
    public ResponseEntity<ResponseProdutoDTO> buscarProduto(@PathVariable(name = "id") Long id) {

        Produto produto = produtoService.buscarProduto(id);
        return ResponseEntity.ok(mapper.fromEntity(produto));

    }

    @ApiOperation(value = "Atualizar produto por id", nickname = "atualizarProduto", response = ResponseProdutoDTO.class)
    @PutMapping("{id}")
    public ResponseEntity<ResponseProdutoDTO> atualizarProduto(@RequestBody RequestProdutoDTO dto,
                                                               @PathVariable(name = "id") Long id) {
        try {
            Produto produto = produtoService.atualizarProduto(mapper.fromDTO(dto), id);
            return ResponseEntity.ok(mapper.fromEntity(produto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Deletar produto por id", nickname = "deletarProduto", response = ResponseProdutoDTO.class)
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseProdutoDTO> deletarProduto(@PathVariable(value = "id") Long id) {

        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
    }

    private void verSeFotoValida(@RequestParam(name = "image") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) throw new InputInvalidoException("Foto não pode ser vazia.");
    }
}
