package com.api.estudo.repositories;

import com.api.estudo.enums.CargoNome;
import com.api.estudo.entities.Politico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoliticoRepository extends JpaRepository<Politico, Long> {

    Optional<Politico> findByCargo_NomeAndId(CargoNome cargoId, Long id);
    Optional<Politico> findByCargoAndId(CargoNome cargoNome, Long id);



    Page<Politico> findAllByCargo_Nome(CargoNome cargo, Pageable pageable);

    Page<Politico> findAllByCargo_NomeAndProjetos(CargoNome cargoNome, Integer quantidade, Pageable pageable);
}
