package com.nttdata.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePedidoRequestDto {
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
}
