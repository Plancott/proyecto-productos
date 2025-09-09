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
    public ResponseEntity<List<ProductoResponseDto>> listAll() {
        List<ProductoResponseDto> response =
                ProductoMapper.INSTANCE.map(productoService.listAll());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> getById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(producto));
    }


    @PostMapping
    public ResponseEntity<ProductoResponseDto> create(@RequestBody ProductoRequestDto request) {
        Producto producto = ProductoMapper.INSTANCE.toEntity(request);
        Producto nuevo = productoService.save(producto, request.getCategoriaId());

        if (nuevo == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(nuevo));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductoRequestDto request) {

        Producto producto = ProductoMapper.INSTANCE.toEntity(request);
        Producto actualizado = productoService.update(id, producto, request.getCategoriaId());

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(actualizado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Producto existente = productoService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDto>> getByCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.findByCategoria(categoriaId);

        if (productos == null || productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ProductoMapper.INSTANCE.map(productos));
    }
}