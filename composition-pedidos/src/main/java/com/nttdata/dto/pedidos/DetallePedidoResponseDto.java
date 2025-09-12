package com.nttdata.dto.pedidos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePedidoResponseDto {
    private Long id;
    private Integer cantidad;
    private Double precioUnitario;
    private Long productoId;
    private String productoNombre;
}
