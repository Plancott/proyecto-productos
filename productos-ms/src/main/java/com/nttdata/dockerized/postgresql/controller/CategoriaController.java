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
    public List<CategoriaResponseDto> listAll() {
        return CategoriaMapper.INSTANCE.map(categoriaService.listAll());
    }

    @GetMapping("/{id}")
    public CategoriaResponseDto getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return CategoriaMapper.INSTANCE.map(categoria);
    }

    @PostMapping
    public CategoriaResponseDto create(@RequestBody CategoriaRequestDto request) {
        Categoria categoria = CategoriaMapper.INSTANCE.toEntity(request);
        Categoria nueva = categoriaService.save(categoria);
        return CategoriaMapper.INSTANCE.map(nueva);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDto update(
            @PathVariable Long id,
            @RequestBody CategoriaRequestDto request) {

        Categoria categoria = CategoriaMapper.INSTANCE.toEntity(request);
        Categoria actualizada = categoriaService.update(id, categoria);

        return CategoriaMapper.INSTANCE.map(actualizada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoriaService.delete(id);
    }
}