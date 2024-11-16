package com.example.mi.primer.myrecetario;

public class CreaTuLista {

    // Atributos de la clase
    private long id;
    private String descripcion;
    private String fechaLimite; // Cambiado a String para facilitar la serialización con Firebase
    private String categoria;

    // Constructor vacío necesario para Firebase
    public CreaTuLista() {
        // Valores predeterminados
        this.id = System.currentTimeMillis();
        this.descripcion = "Sin descripción";
        this.fechaLimite = "Sin fecha";
        this.categoria = "Tareas Diarias";
    }

    // Constructor que establece valores iniciales
    public CreaTuLista(String descripcion, String fechaLimite, String categoria) {
        this.id = System.currentTimeMillis(); // Genera un id único basado en el timestamp actual
        this.descripcion = descripcion != null && !descripcion.trim().isEmpty() ? descripcion : "Sin descripción";
        this.fechaLimite = fechaLimite != null && !fechaLimite.trim().isEmpty() ? fechaLimite : "Sin fecha";
        this.categoria = categoria != null && !categoria.trim().isEmpty() ? categoria : "Tareas Diarias";
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion != null ? descripcion : "Sin descripción";
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null && !descripcion.trim().isEmpty() ? descripcion : "Sin descripción";
    }

    public String getFechaLimite() {
        return fechaLimite != null ? fechaLimite : "Sin fecha";
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite != null && !fechaLimite.trim().isEmpty() ? fechaLimite : "Sin fecha";
    }

    public String getCategoria() {
        return categoria != null ? categoria : "Tareas Diarias";
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria != null && !categoria.trim().isEmpty() ? categoria : "Tareas Diarias";
    }

    @Override
    public String toString() {
        return "CreaTuLista{" +
                "id=" + id +
                ", descripcion='" + getDescripcion() + '\'' +
                ", fechaLimite='" + getFechaLimite() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                '}';
    }
}
