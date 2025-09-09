package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseDto {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoriaNombre;
}
