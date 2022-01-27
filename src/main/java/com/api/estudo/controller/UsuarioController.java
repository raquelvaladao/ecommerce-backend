package com.api.estudo.controller;


import com.api.estudo.dto.mappers.UsuMapper;
import com.api.estudo.dto.request.RequestLoginDTO;
import com.api.estudo.dto.request.RequestUsuarioDTO;
import com.api.estudo.dto.response.ResponseTokenDTO;
import com.api.estudo.dto.response.ResponseUsuarioDetalhesDTO;
import com.api.estudo.dto.response.ResponseUsuarioDTO;
import com.api.estudo.entities.Usuario;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.exceptions.InputInvalidoException;
import com.api.estudo.services.AutenticacaoService;
import com.api.estudo.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Usuário")
@RequestMapping("/api/usuario")
@Validated
@Slf4j
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AutenticacaoService autenticacaoService;
    private final UsuMapper mapper;

    @PostMapping
    @ApiOperation(value = "Cadastrar usuário", nickname = "registrarUsuario", response = ResponseUsuarioDTO.class)
    public ResponseEntity<ResponseTokenDTO> registrarUsuario(@Valid @RequestBody RequestUsuarioDTO dto) {

        try{
            verSeLoginUnico(dto.getLogin());
            Usuario toSave = usuarioService.salvarUsuario(mapper.fromDTO(dto));
            ResponseTokenDTO token = autenticacaoService
                    .autenticar(
                            RequestLoginDTO.builder()
                                .login(dto.getLogin())
                                .senha(dto.getSenha())
                                .build()
            );
            return ResponseEntity.ok(token);
        } catch (Exception e){
            throw new InputInvalidoException(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "Ver todos os usuários", nickname = "listarTodos", response = ResponseUsuarioDetalhesDTO.class)
    public ResponseEntity<Page<ResponseUsuarioDetalhesDTO>> listarTodos(@PageableDefault Pageable pageable) {

        try {
            Page<ResponseUsuarioDetalhesDTO> usuarios = usuarioService.listarTodos(pageable).map(mapper::toResponseDetalhes);
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar usuário", nickname = "deletarUsuario", response = ResponseUsuarioDTO.class)
    public ResponseEntity<ResponseUsuarioDTO> deletarUsuario(@PathVariable(name = "id")Long id) {
        try {
            Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
                ResponseUsuarioDTO usuarioDTO = mapper.fromEntity(usuarioService.buscarUsuarioPorId(id));
                usuarioService.deletarUsuario(id);
                return ResponseEntity.ok(usuarioDTO);
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verSeLoginUnico(String login){
        usuarioService.listar().forEach(usuario -> {
            if(usuario.getLogin().equals(login)){
                throw new InputInvalidoException("Esse login já está sendo usado.");
            }
        });
    }
}
