package com.api.estudo.repositories;

import com.api.estudo.entities.Cargo;
import com.api.estudo.enums.CargoNome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Optional<Cargo> findByNome(CargoNome nome);

}
