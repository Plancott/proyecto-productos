package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listAll();
    Producto findById(Long id);
    Producto save(Producto producto, Long categoriaId);
    Producto update(Long id, Producto producto, Long categoriaId);
    void delete(Long id);
    List<Producto> findByCategoria(Long categoriaId);
}
