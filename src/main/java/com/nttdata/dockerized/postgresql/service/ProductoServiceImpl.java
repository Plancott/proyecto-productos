package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exception.CategoriaNoFoundException;
import com.nttdata.dockerized.postgresql.exception.ProductoNoFoundException;
import com.nttdata.dockerized.postgresql.exception.RangeInvalidException;
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

    public ProductoServiceImpl(ProductoRepository productoRepository,
                               CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoFoundException(id));
    }

    @Override
    public Producto save(Producto producto, Long categoriaId) {
        if (categoriaId != null) {
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new CategoriaNoFoundException(categoriaId));
            producto.setCategoria(categoria);
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto, Long categoriaId) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoFoundException(id));

        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());

        if (categoriaId != null) {
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new CategoriaNoFoundException(categoriaId));
            existente.setCategoria(categoria);
        }

        return productoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNoFoundException(id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new CategoriaNoFoundException(categoriaId);
        }
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public List<Producto> findByPrecioBetween(Double min, Double max) {
        if (min > max) {
            throw new RangeInvalidException(min, max);
        }
        return productoRepository.findByPrecioBetween(min, max);
    }
}
