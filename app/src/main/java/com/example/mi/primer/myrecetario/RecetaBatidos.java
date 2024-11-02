package com.example.mi.primer.myrecetario;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class RecetaBatidos {
    private String nombreDelBatido;
    private String descripcionDelBatido; // Cambiado a minúscula para consistencia
    private double azucarDelBatido;
    private double medioGrasoDelBatido;
    private double esenciaDelBatido;
    private double huevosDelBatido;
    private double harinaDelBatido;
    private double polvoParaHornearDelBatido;
    private double lecheBatido;
    private double limonBatido;
    private double zanahoriaBatido; // Corregido error tipográfico
    private double ralladuraLimon;
    private double chocolateDelBatido;
    private double temperaturaBatido;
    private String preparacionBatido;
    private String almacenamientoProductoFinal;
    private String porcionadoBatido;
    private List<Ingredientes.Ingrediente> ingredientesOriginales;

    // Constructor vacío necesario para Firebase
    public RecetaBatidos() {
        ingredientesOriginales = new ArrayList<>();
    }

    // Constructor con todos los parámetros
    public RecetaBatidos(String nombreDelBatido, String descripcionDelBatido, double azucarDelBatido,
                         double medioGrasoDelBatido, double esenciaDelBatido, double huevosDelBatido,
                         double harinaDelBatido, double polvoParaHornearDelBatido, double lecheBatido,
                         double limonBatido, double zanahoriaBatido, double ralladuraLimon,
                         double chocolateDelBatido, double temperaturaBatido, String preparacionBatido,
                         String almacenamientoProductoFinal, String porcionadoBatido,
                         List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.nombreDelBatido = nombreDelBatido;
        this.descripcionDelBatido = descripcionDelBatido;
        this.azucarDelBatido = azucarDelBatido;
        this.medioGrasoDelBatido = medioGrasoDelBatido;
        this.esenciaDelBatido = esenciaDelBatido;
        this.huevosDelBatido = huevosDelBatido;
        this.harinaDelBatido = harinaDelBatido;
        this.polvoParaHornearDelBatido = polvoParaHornearDelBatido;
        this.lecheBatido = lecheBatido;
        this.limonBatido = limonBatido;
        this.zanahoriaBatido = zanahoriaBatido;
        this.ralladuraLimon = ralladuraLimon;
        this.chocolateDelBatido = chocolateDelBatido;
        this.temperaturaBatido = temperaturaBatido;
        this.preparacionBatido = preparacionBatido;
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
        this.porcionadoBatido = porcionadoBatido;
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    // Getters y setters para cada campo
    public String getNombreDelBatido() {
        return nombreDelBatido;
    }

    public void setNombreDelBatido(String nombreDelBatido) {
        this.nombreDelBatido = nombreDelBatido;
    }

    public String getDescripcionDelBatido() {
        return descripcionDelBatido;
    }

    public void setDescripcionDelBatido(String descripcionDelBatido) {
        this.descripcionDelBatido = descripcionDelBatido;
    }

    public double getAzucarDelBatido() {
        return azucarDelBatido;
    }

    public void setAzucarDelBatido(double azucarDelBatido) {
        this.azucarDelBatido = azucarDelBatido;
    }

    public double getMedioGrasoDelBatido() {
        return medioGrasoDelBatido;
    }

    public void setMedioGrasoDelBatido(double medioGrasoDelBatido) {
        this.medioGrasoDelBatido = medioGrasoDelBatido;
    }

    public double getEsenciaDelBatido() {
        return esenciaDelBatido;
    }

    public void setEsenciaDelBatido(double esenciaDelBatido) {
        this.esenciaDelBatido = esenciaDelBatido;
    }

    public double getHuevosDelBatido() {
        return huevosDelBatido;
    }

    public void setHuevosDelBatido(double huevosDelBatido) {
        this.huevosDelBatido = huevosDelBatido;
    }

    public double getHarinaDelBatido() {
        return harinaDelBatido;
    }

    public void setHarinaDelBatido(double harinaDelBatido) {
        this.harinaDelBatido = harinaDelBatido;
    }

    public double getPolvoParaHornearDelBatido() {
        return polvoParaHornearDelBatido;
    }

    public void setPolvoParaHornearDelBatido(double polvoParaHornearDelBatido) {
        this.polvoParaHornearDelBatido = polvoParaHornearDelBatido;
    }

    public double getLecheBatido() {
        return lecheBatido;
    }

    public void setLecheBatido(double lecheBatido) {
        this.lecheBatido = lecheBatido;
    }

    public double getLimonBatido() {
        return limonBatido;
    }

    public void setLimonBatido(double limonBatido) {
        this.limonBatido = limonBatido;
    }

    public double getZanahoriaBatido() {
        return zanahoriaBatido;
    }

    public void setZanahoriaBatido(double zanahoriaBatido) {
        this.zanahoriaBatido = zanahoriaBatido;
    }

    public double getRalladuraLimon() {
        return ralladuraLimon;
    }

    public void setRalladuraLimon(double ralladuraLimon) {
        this.ralladuraLimon = ralladuraLimon;
    }

    public double getChocolateDelBatido() {
        return chocolateDelBatido;
    }

    public void setChocolateDelBatido(double chocolateDelBatido) {
        this.chocolateDelBatido = chocolateDelBatido;
    }

    public double getTemperaturaBatido() {
        return temperaturaBatido;
    }

    public void setTemperaturaBatido(double temperaturaBatido) {
        this.temperaturaBatido = temperaturaBatido;
    }

    public String getPreparacionBatido() {
        return preparacionBatido;
    }

    public void setPreparacionBatido(String preparacionBatido) {
        this.preparacionBatido = preparacionBatido;
    }

    public String getAlmacenamientoProductoFinal() {
        return almacenamientoProductoFinal;
    }

    public void setAlmacenamientoProductoFinal(String almacenamientoProductoFinal) {
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
    }

    public String getPorcionadoBatido() {
        return porcionadoBatido;
    }

    public void setPorcionadoBatido(String porcionadoBatido) {
        this.porcionadoBatido = porcionadoBatido;
    }

    public List<Ingredientes.Ingrediente> getIngredientesOriginales() {
        return ingredientesOriginales;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales;
    }

    // Método para mostrar la información general
    public String getInformacion() {
        return "Nombre: " + nombreDelBatido + "\n" +
                "Descripción: " + descripcionDelBatido + "\n" +
                "Cantidad de Grasa / Aceite: " + medioGrasoDelBatido + "\n" +
                "Cantidad de Huevos: " + huevosDelBatido + "\n" +
                "Cantidad de Esencia: " + esenciaDelBatido + "\n" +
                "Cantidad de Azúcar: " + azucarDelBatido + "\n" +
                "Cantidad de Harina: " + harinaDelBatido + "\n" +
                "Cantidad de Polvo para Hornear: " + polvoParaHornearDelBatido + "\n" +
                "Cantidad de Leche: " + lecheBatido + "\n" +
                "Cantidad de Limones: " + limonBatido + "\n" +
                "Cantidad de Ralladura de Limón: " + ralladuraLimon + "\n" +
                "Cantidad de Chocolate: " + chocolateDelBatido + "\n" +
                "Cantidad de Zanahoria: " + zanahoriaBatido + "\n"+
                "Temperatura: " + temperaturaBatido + "\n" +
                "Porcionado: " + porcionadoBatido + "\n" +
                "Preparacion batido: " + preparacionBatido + "\n" +
                "Almacenamiento: " + almacenamientoProductoFinal;
    }

    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredientesAjustados = new ArrayList<>();

        if (ingredientesOriginales == null || ingredientesOriginales.isEmpty()) {
            Log.w("RecetaBatidos", "getIngredientesAjustados: Lista de ingredientes originales está vacía o nula");
            return ingredientesAjustados; // Devuelve una lista vacía si no hay ingredientes originales
        }

        for (Ingredientes.Ingrediente ingrediente : ingredientesOriginales) {
            double cantidadAjustada = (ingrediente.getCantidad() * cantidadSeleccionada);
            ingredientesAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
            Log.d("RecetaBatidos", "Ingrediente ajustado: " + ingrediente.getNombre() + " - Cantidad ajustada: " + cantidadAjustada);
        }

        return ingredientesAjustados;
    }
}
