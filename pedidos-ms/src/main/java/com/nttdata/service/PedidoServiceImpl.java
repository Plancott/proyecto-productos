package com.nttdata.service;

import com.nttdata.mapper.PedidoMapper;
import com.nttdata.model.dto.PedidoRequestDto;
import com.nttdata.model.entity.DetallePedido;
import com.nttdata.model.entity.EstadoPedido;
import com.nttdata.model.entity.Pedido;
import com.nttdata.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    @Override
    public List<Pedido> listAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    public Pedido saveFromDto(PedidoRequestDto requestDto) {
        Pedido pedido = PedidoMapper.INSTANCE.toEntity(requestDto);

        if (requestDto.getDetalles() != null) {
            var detalles = PedidoMapper.INSTANCE.toEntityList(requestDto.getDetalles());
            detalles.forEach(detalle -> detalle.setPedido(pedido));
            pedido.setDetalles(detalles);
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
