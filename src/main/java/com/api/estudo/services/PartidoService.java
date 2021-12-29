package com.api.estudo.services;

import com.api.estudo.entities.Partido;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.repositories.PartidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    public Partido salvarPartido(Partido fromDTO) {
        return partidoRepository.save(fromDTO);
    }


    public List<Partido> listarTodos() {
        return partidoRepository.findAll();
    }

    public Partido buscarPartidoPorId(Long id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partido não encontrado"));
    }

    public Partido deletarPartido(Long id) {
        Partido partido = buscarPartidoPorId(id);
        partidoRepository.delete(partido);
        return  partido;
    }

    public Partido atualizarPartido(Partido partidoInput, Long id) {
        Partido partido = buscarPartidoPorId(id);
        partidoInput.setId(partido.getId());
        partidoInput.setParticipantes(partido.getParticipantes());
        partidoRepository.save(partidoInput);
        return partidoInput;
    }
}
