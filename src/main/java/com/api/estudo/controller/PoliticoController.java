package com.api.estudo.controller;


import com.api.estudo.dto.mappers.PoliticoMapper;
import com.api.estudo.dto.request.RequestPoliticoDTO;
import com.api.estudo.dto.request.RequestPutPoliticoDTO;
import com.api.estudo.dto.response.ResponsePoliticoDTO;
import com.api.estudo.entities.Politico;
import com.api.estudo.exceptions.InputInvalidoException;
import com.api.estudo.services.PoliticoService;
import com.api.estudo.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/politicos")
@Api(tags = "Políticos")
public class PoliticoController {

    private static final int SORT_RANGE = 5;
    private final PoliticoService politicoService;
    private final PoliticoMapper mapper;

    public PoliticoController(PoliticoService politicoService, PoliticoMapper mapper) {
        this.politicoService = politicoService;
        this.mapper = mapper;
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Cadastrar politico", nickname = "registrarPolitico", response = ResponsePoliticoDTO.class)
    public ResponseEntity<ResponsePoliticoDTO> registrarPolitico(@Valid RequestPoliticoDTO dto,
                                                                 @RequestParam(name = "image") MultipartFile multipartFile) throws IOException {
        verSeFotoValida(multipartFile);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Politico politico = mapper.fromDTO(dto);
        politico.setFoto(fileName);
        verSeCPFValido(politico);
        verSeLiderValido(politico);

        politicoService.salvarPolitico(politico);
        String uploadDir = "politicos-photos/" + politico.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return ResponseEntity.ok(mapper.fromEntity(politico));

    }

    @GetMapping
    @ApiOperation(value = "Listar politicos", nickname = "listarPoliticos", response = ResponsePoliticoDTO.class)
    public ResponseEntity<Page<ResponsePoliticoDTO>> listarPoliticos(
            @PageableDefault(value = SORT_RANGE) final Pageable pageable) {
        try {
            Page<ResponsePoliticoDTO> politicos = politicoService
                    .listarPoliticos(pageable)
                    .map(mapper::fromEntity);

            return ResponseEntity.ok(politicos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar politico", nickname = "deletarPolitico", response = ResponsePoliticoDTO.class)
    public ResponseEntity<ResponsePoliticoDTO> deletarPolitico(@PathVariable(name = "id") Long id) throws IOException {

        Politico politico = politicoService.getPolitico(id);
        FileUtils.deleteDirectory(new File("politicos-photos/" + politico.getId()));
        ResponsePoliticoDTO politicoResponse = mapper.fromEntity(politicoService.deletarPolitico(id));


        return ResponseEntity.ok(politicoResponse);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Editar politico", nickname = "editarPolitico", response = ResponsePoliticoDTO.class)
    public ResponseEntity<ResponsePoliticoDTO> editarPolitico(@PathVariable(name = "id") Long id,
                                                              @Valid RequestPutPoliticoDTO dto) {
        Politico update = mapper.toUpdatedEntity(dto);
        ResponsePoliticoDTO politico = mapper.fromEntity(politicoService.atualizarPolitico(id, update));

        return ResponseEntity.ok(politico);
    }

    private void verSeCPFValido(Politico politicoInput) {
        politicoService.listar()
                .stream()
                .map(Politico::getCpf)
                .forEach(cpf -> {
                    if (politicoInput.getCpf().equals(cpf))
                        throw new InputInvalidoException("CPF deve ser único");
                });
    }

    private void verSeLiderValido(Politico politicoInput) {
        politicoService.listar()
                .forEach(politico -> {
                    if (politicoInput.getLider().equals(true)
                            && politico.getLider().equals(true)
                            && politicoInput.getPartido().equals(politico.getPartido()))
                        throw new InputInvalidoException
                                ("Já existe um líder nesse partido, então esse político não pode ser líder");
                });
    }

    private void verSeFotoValida(@RequestParam(name = "image") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) throw new InputInvalidoException("Foto não pode ser vazia.");
    }

}
