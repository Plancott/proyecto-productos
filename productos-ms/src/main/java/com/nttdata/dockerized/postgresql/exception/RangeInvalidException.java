package com.nttdata.dockerized.postgresql.exception;

public class RangeInvalidException  extends  RuntimeException{
    private final Double min;
    private final Double max;
    public RangeInvalidException(Double min, Double max) {
        super("Rango inválido: el valor mínimo: (" + min + ") no puede ser mayor que máximod: (" + max + ")");
        this.min = min;
        this.max = max;
    }
    public Double getMin() {
        return min;
    }
    public Double getMax() {
        return max;
    }
}
