package com.nttdata.model.entity;

import com.nttdata.model.dto.ProductoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private Double precioUnitario;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    private String productoNombre;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
