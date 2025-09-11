package com.nttdata.controller;

import com.nttdata.mapper.PedidoMapper;
import com.nttdata.model.dto.PedidoRequestDto;
import com.nttdata.model.entity.Pedido;
import com.nttdata.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listAll() {
        return pedidoService.listAll();
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    public Pedido create(@RequestBody PedidoRequestDto requestDto) {
        return pedidoService.saveFromDto(requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

}
