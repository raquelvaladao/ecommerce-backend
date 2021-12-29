package com.api.estudo.controller;


import com.api.estudo.dto.request.RequestLoginDTO;
import com.api.estudo.dto.response.ResponseTokenDTO;
import com.api.estudo.services.AutenticacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "Autenticação")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @ApiOperation(nickname = "autenticar", response = ResponseTokenDTO.class, value = "Autenticar")
    @PostMapping
    public ResponseEntity<ResponseTokenDTO> autenticar(@RequestBody RequestLoginDTO dto) {
        try {
            ResponseTokenDTO token = autenticacaoService.autenticar(dto);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
