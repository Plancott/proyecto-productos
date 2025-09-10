package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exception.CategoriaNoFoundException;
import com.nttdata.dockerized.postgresql.exception.ProductoNoFoundException;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoFoundException(id));
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoFoundException(id));
        existente.setNombre(categoria.getNombre());
        return categoriaRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNoFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }

}