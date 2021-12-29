package com.api.estudo.controller;


import com.api.estudo.converters.CargoConverter;
import com.api.estudo.dto.mappers.PoliticoMapper;
import com.api.estudo.dto.response.ResponsePoliticoDTO;
import com.api.estudo.enums.CargoNome;
import com.api.estudo.services.PoliticoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Cargos")
public class CargosController {

    public static final int SORT_RANGE = 5;
    public static final String SORT_PARAMETRO = "nome";

    private final PoliticoService politicoService;
    private final PoliticoMapper mapper;
    private final CargoConverter converter;

    public CargosController(PoliticoService politicoService, PoliticoMapper mapper, CargoConverter converter) {
        this.politicoService = politicoService;
        this.mapper = mapper;
        this.converter = converter;
    }

    @GetMapping("/{cargo}")
    @ApiOperation(value = "Buscar cargo por id", nickname = "buscarPolitico", response = ResponsePoliticoDTO.class)
    public ResponseEntity<ResponsePoliticoDTO> buscarPolitico(@PathVariable(name = "cargo") String cargoString,
                                                              @RequestParam(name = "id") Long id) {
        CargoNome cargoEnum = converter.convert(cargoString);
        ResponsePoliticoDTO politico = mapper.fromEntity(politicoService.getPoliticoDadoCargo(cargoEnum, id));
        return ResponseEntity.ok(politico);
    }

    @GetMapping("{cargo}/asc")
    @ApiOperation(value = "Listar cargos ordem crescente", nickname = "listarOrdemAlfaCrescente", response = ResponsePoliticoDTO.class)
    public ResponseEntity<Page<ResponsePoliticoDTO>> listarOrdemAlfaCrescente(
            @PathVariable(name = "cargo") String cargoString,
            @PageableDefault(sort = {SORT_PARAMETRO}, direction = Sort.Direction.ASC, value = SORT_RANGE) final Pageable pageable) {
        try {
            CargoNome cargoEnum = converter.convert(cargoString);

            Page<ResponsePoliticoDTO> politicos = politicoService
                    .getListaPoliticosDadoCargo(cargoEnum, pageable)
                    .map(mapper::fromEntity);

            return ResponseEntity.ok(politicos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{cargo}/desc")
    @ApiOperation(value = "Listar cargos ordem decrescente", nickname = "listarOrdemAlfaDecrescente", response = ResponsePoliticoDTO.class)
    public ResponseEntity<Page<ResponsePoliticoDTO>> listarOrdemAlfaDecrescente(
            @PathVariable(name = "cargo") String cargoString,
            @PageableDefault(sort = {SORT_PARAMETRO}, direction = Sort.Direction.DESC, value = SORT_RANGE) final Pageable pageable) {

        try {
            CargoNome cargoEnum = converter.convert(cargoString);
            Page<ResponsePoliticoDTO> politicos = politicoService
                    .getListaPoliticosDadoCargo(cargoEnum, pageable)
                    .map(mapper::fromEntity);
            return ResponseEntity.ok(politicos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{cargo}/leis")
    @ApiOperation(value = "Listar cargos numero de leis", nickname = "getSenadoresPorNumeroDeLeis", response = ResponsePoliticoDTO.class)
    public ResponseEntity<Page<ResponsePoliticoDTO>> getSenadoresPorNumeroDeLeis(
            @PathVariable(name = "cargo") String cargoString,
            @RequestParam(name = "qtd") Integer quantidade,
            @PageableDefault Pageable pageable) {

        CargoNome cargoEnum = converter.convert(cargoString);
        Page<ResponsePoliticoDTO> politicos = politicoService
                .getListaPoliticosDadoQtdeLeis(cargoEnum, quantidade, pageable)
                .map(mapper::fromEntity);
        return ResponseEntity.ok(politicos);

    }


}
