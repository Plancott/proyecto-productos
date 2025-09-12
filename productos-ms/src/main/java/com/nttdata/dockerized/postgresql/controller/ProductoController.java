package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.ProductoMapper;
import com.nttdata.dockerized.postgresql.model.dto.ProductoRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductoResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping
    public List<ProductoResponseDto> listAll() {
        return ProductoMapper.INSTANCE.map(productoService.listAll());
    }


    @GetMapping("/{id}")
    public ProductoResponseDto getById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ProductoMapper.INSTANCE.map(producto);
    }


    @PostMapping
    public ProductoResponseDto create(@RequestBody ProductoRequestDto request) {
        Producto producto = ProductoMapper.INSTANCE.toEntity(request);
        Producto nuevo = productoService.save(producto, request.getCategoriaId());
        return ProductoMapper.INSTANCE.map(nuevo);
    }


    @PutMapping("/{id}")
    public ProductoResponseDto update(
            @PathVariable Long id,
            @RequestBody ProductoRequestDto request) {

        Producto producto = ProductoMapper.INSTANCE.toEntity(request);
        Producto actualizado = productoService.update(id, producto, request.getCategoriaId());

        return ProductoMapper.INSTANCE.map(actualizado);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.delete(id);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDto>> getByCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.findByCategoria(categoriaId);
        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(productos));
    }

    @GetMapping("/precio")
    public ResponseEntity<List<ProductoResponseDto>> getByPrecioBetween(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Producto> productos = productoService.findByPrecioBetween(min, max);
        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(productos));
    }
}