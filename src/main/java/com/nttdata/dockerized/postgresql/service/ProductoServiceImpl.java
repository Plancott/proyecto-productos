package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.mapper.ProductoMapper;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto save(Producto producto, Long categoriaId) {
        if (categoriaId != null) {
            Optional<Categoria> categoriaAux = categoriaRepository.findById(categoriaId);
            if (categoriaAux.isEmpty()) {
                return null;
            }
            producto.setCategoria(categoriaAux.get());
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto, Long categoriaId) {
        Optional<Producto> existenteAux = productoRepository.findById(id);
        if (existenteAux.isEmpty()) {
            return null;
        }

        Producto existente = existenteAux.get();
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());

        if (categoriaId != null) {
            Optional<Categoria> categoriaAux = categoriaRepository.findById(categoriaId);
            if (categoriaAux.isEmpty()) {
                return null;
            }
            existente.setCategoria(categoriaAux.get());
        }

        return productoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
}
