package com.nttdata.mapper;

import com.nttdata.model.dto.DetallePedidoRequestDto;
import com.nttdata.model.dto.PedidoRequestDto;
import com.nttdata.model.entity.DetallePedido;
import com.nttdata.model.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaPedido", expression = "java(now())")
    @Mapping(target = "estado", expression = "java(com.nttdata.model.entity.EstadoPedido.PENDIENTE)")
    @Mapping(target = "detalles", ignore = true)
    Pedido toEntity(PedidoRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    DetallePedido toEntity(DetallePedidoRequestDto dto);

    List<DetallePedido> toEntityList(List<DetallePedidoRequestDto> dtos);

    default java.time.LocalDateTime now() {
        return java.time.LocalDateTime.now();
    }
}
