package com.nttdata.dto.pedidos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class PedidoResponseDto {
    private Long id;
    private LocalDateTime fechaPedido;
    private String estado;
    private Long userId;
    private List<DetallePedidoResponseDto> detalles;
}
