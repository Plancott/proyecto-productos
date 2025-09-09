package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listAll();
    Categoria findById(Long id);
    Categoria save(Categoria categoria);
    Categoria update(Long id, Categoria categoria);
    void delete(Long id);
}
