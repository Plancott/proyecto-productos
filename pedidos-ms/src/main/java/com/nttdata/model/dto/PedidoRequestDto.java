package com.nttdata.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequestDto {
    private Long userId;
    private List<DetallePedidoRequestDto> detalles;
}
