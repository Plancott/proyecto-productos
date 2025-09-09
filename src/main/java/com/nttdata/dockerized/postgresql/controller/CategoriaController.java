package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.CategoriaMapper;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listAll() {
        List<CategoriaResponseDto> response =
                CategoriaMapper.INSTANCE.map(categoriaService.listAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CategoriaMapper.INSTANCE.map(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@RequestBody CategoriaRequestDto request) {
        Categoria categoria = CategoriaMapper.INSTANCE.toEntity(request);
        Categoria nueva = categoriaService.save(categoria);
        return ResponseEntity.ok(CategoriaMapper.INSTANCE.map(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> update(
            @PathVariable Long id,
            @RequestBody CategoriaRequestDto request) {

        Categoria categoria = CategoriaMapper.INSTANCE.toEntity(request);
        Categoria actualizada = categoriaService.update(id, categoria);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(CategoriaMapper.INSTANCE.map(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Categoria existente = categoriaService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}