package com.api.estudo.repositories;


import com.api.estudo.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    Compra findByCompradorId(Long id);

}
