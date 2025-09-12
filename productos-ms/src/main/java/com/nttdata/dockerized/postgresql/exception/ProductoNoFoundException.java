package com.nttdata.dockerized.postgresql.exception;

public class ProductoNoFoundException extends RuntimeException {
    private final Long productoId;

    public ProductoNoFoundException(Long productoId) {
        super("Producto de id: (" + productoId + ") no encontrado");
        this.productoId = productoId;
    }

    public Long getProductoId() {
        return productoId;
    }

}
