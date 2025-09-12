package com.nttdata.service;

import com.nttdata.model.dto.PedidoRequestDto;
import com.nttdata.model.entity.Pedido;

import java.util.List;

public interface PedidoService {
    public List<Pedido> listAll();

    public Pedido findById(Long id);

    Pedido saveFromDto(PedidoRequestDto dto);

    public void delete(Long id);
}
