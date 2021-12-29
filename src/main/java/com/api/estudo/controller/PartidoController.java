package com.api.estudo.controller;

import com.api.estudo.dto.PartidoDTO;
import com.api.estudo.dto.mappers.PartidoMapper;
import com.api.estudo.entities.Partido;
import com.api.estudo.exceptions.InputInvalidoException;
import com.api.estudo.services.PartidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/partidos")
@Api(tags = "Partidos")
public class PartidoController {

    private final PartidoService partidoService;
    private final PartidoMapper mapper;

    public PartidoController(PartidoService partidoService, PartidoMapper mapper) {
        this.partidoService = partidoService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar partido", nickname = "registrarPartido", response = PartidoDTO.class)
    public ResponseEntity<PartidoDTO> registrarPartido(@Valid @RequestBody PartidoDTO dto) {

        try {
            Partido toSave = partidoService.salvarPartido(mapper.fromDTO(dto));
            PartidoDTO response = mapper.fromEntity(toSave);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new InputInvalidoException(e.getMessage());
        }
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Ver todos os partidos", nickname = "listarTodos", response = PartidoDTO.class)
    public ResponseEntity<List<PartidoDTO>> listarTodos() {

        try {
            List<PartidoDTO> partidoDTOS = partidoService
                    .listarTodos()
                    .stream()
                    .map(mapper::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(partidoDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar partido", nickname = "alterarPartido", response = PartidoDTO.class)
    public ResponseEntity<PartidoDTO> alterarPartido(@PathVariable(name = "id") Long id, @Valid @RequestBody PartidoDTO partidoDTO) {
        Partido partido = mapper.fromDTO(partidoDTO);
        partidoService.atualizarPartido(partido, id);
        return ResponseEntity.ok(mapper.fromEntity(partido));

    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar partido", nickname = "deletarPartido", response = PartidoDTO.class)
    public ResponseEntity<PartidoDTO> deletarPartido(@PathVariable(name = "id") Long id) {
        try {
            PartidoDTO partido = mapper.fromEntity(partidoService.deletarPartido(id));
            return ResponseEntity.ok(partido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
