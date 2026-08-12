package com.yolatino.yolatino;

public class DatosCompraHoras {

    private String fechaCompra;
    private String horasCompra;

    public DatosCompraHoras(String fechaCompra, String horasCompra) {
        this.fechaCompra = fechaCompra;
        this.horasCompra = horasCompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getHorasCompra() {
        return horasCompra;
    }

    public void setHorasCompra(String horasCompra) {
        this.horasCompra = horasCompra;
    }

}
