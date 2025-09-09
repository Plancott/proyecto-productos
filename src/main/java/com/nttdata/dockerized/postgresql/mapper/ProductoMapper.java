package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.ProductoRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductoResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoResponseDto map(Producto producto);

    List<ProductoResponseDto> map(List<Producto> productos);

    Producto toEntity(ProductoRequestDto requestDto);

    @AfterMapping
    default void setCategoriaNombre(Producto producto, @MappingTarget ProductoResponseDto responseDto) {
        if (producto.getCategoria() != null) {
            responseDto.setCategoriaNombre(producto.getCategoria().getNombre());
        } else {
            responseDto.setCategoriaNombre("Sin categoría");
        }
    }
}