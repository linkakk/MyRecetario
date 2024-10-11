package com.example.mi.primer.myrecetario;

import java.util.List;

public class RecetaMasa {
    private String nombreDeLaMasa;
    private String descripcionDeLaMasa;
    private List<Ingredientes.Ingrediente> ingredientes;  // Lista de ingredientes
    private String preparacionMasa;
    private String temperaturasMasas;
    private String porcionadoMasas;
    private String almacenamientoProductoFinal;
    private int cantidadOriginal;  // Cantidad base para la receta

    // Constructor vacío necesario para Firebase
    public RecetaMasa() {
    }

    // Constructor con parámetros
    public RecetaMasa(String nombreDeLaMasa, String descripcionDeLaMasa, List<Ingredientes.Ingrediente> ingredientes,
                      String preparacionMasa, String temperaturasMasas, String porcionadoMasas,
                      String almacenamientoProductoFinal, int cantidadOriginal) {
        this.nombreDeLaMasa = nombreDeLaMasa;
        this.descripcionDeLaMasa = descripcionDeLaMasa;
        this.ingredientes = ingredientes;
        this.preparacionMasa = preparacionMasa;
        this.temperaturasMasas = temperaturasMasas;
        this.porcionadoMasas = porcionadoMasas;
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
        this.cantidadOriginal = cantidadOriginal;
    }

    // Getters y setters
    public String getNombreDeLaMasa() {
        return nombreDeLaMasa;
    }

    public void setNombreDeLaMasa(String nombreDeLaMasa) {
        this.nombreDeLaMasa = nombreDeLaMasa;
    }

    public String getDescripcionDeLaMasa() {
        return descripcionDeLaMasa;
    }

    public void setDescripcionDeLaMasa(String descripcionDeLaMasa) {
        this.descripcionDeLaMasa = descripcionDeLaMasa;
    }

    public List<Ingredientes.Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes.Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacionMasa() {
        return preparacionMasa;
    }

    public void setPreparacionMasa(String preparacionMasa) {
        this.preparacionMasa = preparacionMasa;
    }

    public String getTemperaturasMasas() {
        return temperaturasMasas;
    }

    public void setTemperaturasMasas(String temperaturasMasas) {
        this.temperaturasMasas = temperaturasMasas;
    }

    public String getPorcionadoMasas() {
        return porcionadoMasas;
    }

    public void setPorcionadoMasas(String porcionadoMasas) {
        this.porcionadoMasas = porcionadoMasas;
    }

    public String getAlmacenamientoProductoFinal() {
        return almacenamientoProductoFinal;
    }

    public void setAlmacenamientoProductoFinal(String almacenamientoProductoFinal) {
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
    }

    public int getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(int cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    // Método para ajustar las cantidades de los ingredientes
    public void ajustarIngredientes(int cantidadSeleccionada) {
        double factor = (double) cantidadSeleccionada / cantidadOriginal;
        for (Ingredientes.Ingrediente ingrediente : ingredientes) {
            ingrediente.setCantidad(ingrediente.getCantidad() * factor);
        }
    }

    // Método para obtener información completa de la receta
    public String getInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(nombreDeLaMasa).append("\n")
                .append("Descripción: ").append(descripcionDeLaMasa).append("\n");

        for (Ingredientes.Ingrediente ingrediente : ingredientes) {
            info.append(ingrediente.getNombre()).append(": ").append(ingrediente.getCantidad()).append("\n");
        }

        info.append("Preparación: ").append(preparacionMasa).append("\n")
                .append("Temperaturas: ").append(temperaturasMasas).append("\n")
                .append("Porcionado: ").append(porcionadoMasas).append("\n")
                .append("Almacenamiento: ").append(almacenamientoProductoFinal);

        return info.toString();
    }
}
