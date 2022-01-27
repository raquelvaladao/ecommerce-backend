package com.api.estudo.repositories;

import com.api.estudo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByCompradorId(Long id);
}
