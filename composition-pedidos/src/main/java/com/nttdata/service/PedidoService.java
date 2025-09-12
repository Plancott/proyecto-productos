package com.nttdata.service;

import com.nttdata.dto.pedidos.PedidoResponseDto;

import java.util.List;

public interface PedidoService {
    PedidoResponseDto getPedidoById(Long id);
    List<PedidoResponseDto> getAllPedidos();
}
