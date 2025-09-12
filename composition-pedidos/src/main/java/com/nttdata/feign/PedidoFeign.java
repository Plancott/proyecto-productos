package com.nttdata.feign;

import com.nttdata.dto.pedidos.PedidoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pedidos-ms", url = "http://localhost:8082")
public interface PedidoFeign {

    @GetMapping("/api/pedidos/{id}")
    PedidoResponseDto getPedidoById(@PathVariable("id") Long id);

    @GetMapping("/api/pedidos")
    List<PedidoResponseDto> getAllPedidos();
}
