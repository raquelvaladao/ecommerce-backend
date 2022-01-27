package com.api.estudo.services;

import com.api.estudo.entities.Produto;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto buscarProduto(Long produtoId){
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);
        return produtoOptional.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id) throws EntityNotFoundException{
        produtoRepository.deleteById(id);

    }

    public Produto atualizarProduto(Produto atualizacao, Long id){
        Produto produto = buscarProduto(id);
        produto.setNome(atualizacao.getNome());
        produto.setDescricao(atualizacao.getDescricao());
        produto.setMedida(atualizacao.getMedida());
        produtoRepository.save(produto);
        return produto;
    }
}
