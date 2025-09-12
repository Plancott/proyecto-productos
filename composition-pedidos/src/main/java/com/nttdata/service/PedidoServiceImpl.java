package com.nttdata.service;

import com.nttdata.dto.pedidos.PedidoResponseDto;
import com.nttdata.feign.PedidoFeign;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{
    private final PedidoFeign pedidoFeign;

    public PedidoServiceImpl(PedidoFeign pedidoFeign) {
        this.pedidoFeign = pedidoFeign;
    }

    @Override
    public PedidoResponseDto getPedidoById(Long id) {
        return pedidoFeign.getPedidoById(id);
    }

    @Override
    public List<PedidoResponseDto> getAllPedidos() {
        return pedidoFeign.getAllPedidos();
    }
}
