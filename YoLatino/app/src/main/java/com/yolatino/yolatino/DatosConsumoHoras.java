package com.yolatino.yolatino;

public class DatosConsumoHoras {

    private String fechaConsumo;
    private String claseBaile;
    private String horasConsumo;

    public DatosConsumoHoras(String fechaConsumo, String claseBaile, String horasConsumo) {
        this.fechaConsumo = fechaConsumo;
        this.claseBaile = claseBaile;
        this.horasConsumo = horasConsumo;
    }

    public String getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(String fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getClaseBaile() {
        return claseBaile;
    }

    public void setClaseBaile(String claseBaile) {
        this.claseBaile = claseBaile;
    }

    public String getHorasConsumo() {
        return horasConsumo;
    }

    public void setHorasConsumo(String horasConsumo) {
        this.horasConsumo = horasConsumo;
    }

}
