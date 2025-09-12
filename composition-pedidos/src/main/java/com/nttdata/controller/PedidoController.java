package com.nttdata.controller;

import com.nttdata.dto.pedidos.PedidoResponseDto;
import com.nttdata.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/composition/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public List<PedidoResponseDto> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDto getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

}
