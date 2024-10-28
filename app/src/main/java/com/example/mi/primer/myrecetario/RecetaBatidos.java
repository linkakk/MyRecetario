package com.example.mi.primer.myrecetario;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class RecetaBatidos implements Receta {
    private String nombreDelBatido;
    private String descripcionDelBatido;
    private double azucarDelBatido;
    private double medioGrasoDelBatido;
    private double esenciaDelBatido;
    private double huevosDelBatido;
    private double harinaDelBatido;
    private double polvoParaHornearDelBatido;
    private double lecheBatido;
    private double limonBatido;
    private double zanahoriaBatido;
    private double ralladuraLimon;
    private double chocolateDelBatido;
    private double temperaturaBatido;
    private String preparacionBatido;
    private String almacenamientoProductoFinal;
    private String porcionadoBatido;
    private double cantidadOriginal;
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
                         double cantidadOriginal, List<Ingredientes.Ingrediente> ingredientesOriginales) {
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
        this.cantidadOriginal = cantidadOriginal;
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    public RecetaBatidos(String nombre, String descripcion, double cantidadAzucar, double cantidadMargarina, double cantidadEsencia, double cantidadHuevos, double cantidadHarina, double cantidadPolvoParaHornear, double cantidadLeche, double cantidadLimones, double cantidadZanahoria, double cantidadLimonesRallados, double cantidadChocolate, double temperaturaHorno, String preparacion, String almacenamiento, String porcionado, List<Ingredientes.Ingrediente> listaIngredientes) {
    }

    // Implementación de métodos de la interfaz Receta
    @Override
    public String getNombre() {
        Log.d("RecetaBatidos", "getNombre: " + nombreDelBatido);
        return nombreDelBatido != null ? nombreDelBatido : "Sin nombre";
    }

    @Override
    public String getDescripcion() {
        Log.d("RecetaBatidos", "getDescripcion: " + descripcionDelBatido);
        return descripcionDelBatido != null ? descripcionDelBatido : "Sin descripción";
    }

    @Override
    public double getCantidadOriginal() {
        Log.d("RecetaBatidos", "getCantidadOriginal: " + cantidadOriginal);
        return cantidadOriginal > 0 ? cantidadOriginal : 1.0;
    }

    @Override
    public List<Ingredientes.Ingrediente> getIngredientes() {
        return ingredientesOriginales;
    }

    @Override
    public String getInformacion() {
        return "Nombre: " + getNombre() + "\n" +
                "Descripción: " + getDescripcion() + "\n" +
                "Cantidad de Grasa / Aceite: " + medioGrasoDelBatido + "\n" +
                "Cantidad de Huevos: " + huevosDelBatido + "\n" +
                "Cantidad de Esencia: " + esenciaDelBatido + "\n" +
                "Cantidad de Azúcar: " + azucarDelBatido + "\n" +
                "Cantidad de Harina: " + harinaDelBatido + "\n" +
                "Cantidad de Polvo para Hornear: " + polvoParaHornearDelBatido + "\n" +
                "Cantidad de Leche: " + lecheBatido + "\n" +
                "Cantidad de Limón: " + limonBatido + "\n" +
                "Cantidad de Zanahoria: " + zanahoriaBatido + "\n" +
                "Cantidad de Ralladura de Limón: " + ralladuraLimon + "\n" +
                "Cantidad de Chocolate: " + chocolateDelBatido + "\n" +
                "Temperatura: " + temperaturaBatido + "\n" +
                "Preparación: " + preparacionBatido + "\n" +
                "Porcionado: " + porcionadoBatido + "\n" +
                "Almacenamiento: " + almacenamientoProductoFinal;
    }

    @Override
    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredientesAjustados = new ArrayList<>();

        if (cantidadOriginal == 0) {
            Log.w("RecetaBatidos", "Cantidad original es cero, ajuste no es posible.");
            return ingredientesAjustados;
        }

        for (Ingredientes.Ingrediente ingrediente : ingredientesOriginales) {
            double cantidadAjustada = (ingrediente.getCantidad() * cantidadSeleccionada) / cantidadOriginal;
            ingredientesAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
            Log.d("RecetaBatidos", "Ingrediente ajustado: " + ingrediente.getNombre() + " - Cantidad ajustada: " + cantidadAjustada);
        }

        return ingredientesAjustados;
    }

    // Getters y Setters
    public void setNombreDelBatido(String nombreDelBatido) {
        this.nombreDelBatido = nombreDelBatido;
    }

    public void setDescripcionDelBatido(String descripcionDelBatido) {
        this.descripcionDelBatido = descripcionDelBatido;
    }

    public void setAzucarDelBatido(double azucarDelBatido) {
        this.azucarDelBatido = azucarDelBatido;
    }

    public void setMedioGrasoDelBatido(double medioGrasoDelBatido) {
        this.medioGrasoDelBatido = medioGrasoDelBatido;
    }

    public void setEsenciaDelBatido(double esenciaDelBatido) {
        this.esenciaDelBatido = esenciaDelBatido;
    }

    public void setHuevosDelBatido(double huevosDelBatido) {
        this.huevosDelBatido = huevosDelBatido;
    }

    public void setHarinaDelBatido(double harinaDelBatido) {
        this.harinaDelBatido = harinaDelBatido;
    }

    public void setPolvoParaHornearDelBatido(double polvoParaHornearDelBatido) {
        this.polvoParaHornearDelBatido = polvoParaHornearDelBatido;
    }

    public void setLecheBatido(double lecheBatido) {
        this.lecheBatido = lecheBatido;
    }

    public void setLimonBatido(double limonBatido) {
        this.limonBatido = limonBatido;
    }

    public void setZanahoriaBatido(double zanahoriaBatido) {
        this.zanahoriaBatido = zanahoriaBatido;
    }

    public void setRalladuraLimon(double ralladuraLimon) {
        this.ralladuraLimon = ralladuraLimon;
    }

    public void setChocolateDelBatido(double chocolateDelBatido) {
        this.chocolateDelBatido = chocolateDelBatido;
    }

    public void setTemperaturaBatido(double temperaturaBatido) {
        this.temperaturaBatido = temperaturaBatido;
    }

    public void setPreparacionBatido(String preparacionBatido) {
        this.preparacionBatido = preparacionBatido;
    }

    public void setAlmacenamientoProductoFinal(String almacenamientoProductoFinal) {
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
    }

    public void setPorcionadoBatido(String porcionadoBatido) {
        this.porcionadoBatido = porcionadoBatido;
    }

    public void setCantidadOriginal(double cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }
}
