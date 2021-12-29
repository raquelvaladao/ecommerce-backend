package com.api.estudo.services;

import com.api.estudo.entities.Cargo;
import com.api.estudo.entities.Politico;
import com.api.estudo.enums.CargoNome;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.repositories.CargoRepository;
import com.api.estudo.repositories.PoliticoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticoService {

    private final PoliticoRepository politicoRepository;
    private final CargoRepository cargoRepository;

    public PoliticoService(PoliticoRepository politicoRepository, CargoRepository cargoRepository) {
        this.politicoRepository = politicoRepository;
        this.cargoRepository = cargoRepository;
    }

    public void salvarPolitico(Politico politico) {
        politicoRepository.save(politico);
    }

    public Page<Politico> listarPoliticos(Pageable pageable) {
        return politicoRepository.findAll(pageable);
    }

    public List<Politico> listar() {
        return politicoRepository.findAll();
    }

    public Politico getPoliticoDadoCargo(CargoNome nomeCargo, Long id) {

        return politicoRepository
                .findByCargo_NomeAndId(nomeCargo, id)
                .orElseThrow(() -> new EntityNotFoundException("Político não encontrado"));
    }


    public Politico getPolitico(Long id) {
        return politicoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Político não encontrado"));
    }

    public Politico deletarPolitico(Long id) {
        Politico politico = getPolitico(id);
        politicoRepository.deleteById(id);
        return politico;
    }

    public Page<Politico> getListaPoliticosDadoCargo(CargoNome cargoNome, Pageable pageable) {
        return politicoRepository.findAllByCargo_Nome(cargoNome, pageable);
    }

    public Page<Politico> getListaPoliticosDadoQtdeLeis(CargoNome cargoNome, Integer quantidade, Pageable pageable) {
        return politicoRepository.findAllByCargo_NomeAndProjetos(cargoNome, quantidade, pageable);
    }

    public Politico atualizarPolitico(Long id, Politico politicoInput) {

        Politico politico = getPolitico(id);
        Cargo cargo = cargoRepository
                .findByNome(politicoInput.getCargo().getNome())
                .orElseThrow(() -> new EntityNotFoundException("Esse cargo está vazio ou não foi encontrado."));

        politicoInput.setFoto(politico.getFoto());
        politicoInput.setId(politico.getId());
        politicoInput.setCpf(politico.getCpf());
        politicoInput.setCargo(cargo);
        return politicoRepository.save(politicoInput);
    }
}
