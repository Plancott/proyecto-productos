package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CategoriaRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaResponseDto map(Categoria categoria);

    List<CategoriaResponseDto> map(List<Categoria> categorias);

    Categoria toEntity(CategoriaRequestDto dto);
}