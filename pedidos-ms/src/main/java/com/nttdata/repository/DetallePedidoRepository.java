package com.nttdata.repository;

import com.nttdata.model.entity.DetallePedido;
import com.nttdata.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

}
