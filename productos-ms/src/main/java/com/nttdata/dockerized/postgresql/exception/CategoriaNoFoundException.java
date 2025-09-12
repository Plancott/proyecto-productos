package com.nttdata.dockerized.postgresql.exception;

public class CategoriaNoFoundException extends RuntimeException {
    private final Long categoriaId;

    public CategoriaNoFoundException(Long categoriaId) {
        super("Categoria de id: (" + categoriaId + ") no encontrada");
        this.categoriaId = categoriaId;
    }
    public Long getCategoriaId() {
        return categoriaId;
    }
}
