package com.example.mi.primer.myrecetario;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class RecetaMasa {
    // Campos existentes
    private String nombreDeLaMasa;
    private String descripcionDeLaMasa;
    private double cantidadDeAguaMasa;
    private double cantidadDeLevaduraMasa;
    private double cantidadDePrefermentoMasa;
    private double cantidadDeHuevosMasa;
    private double cantidadDeEsenciaMasa;
    private double cantidadDeAzucarMasa;
    private double cantidadDeSalMasa;
    private double cantidadDeMargarinaMasa;
    private double cantidadDeHarinaMasa;
    private String preparacionMasa;
    private String temperaturasMasas;
    private String porcionadoMasas;
    private String almacenamientoProductoFinal;
    private double cantidadOriginal;  // Cambiado a double

    // Nueva lista para almacenar los ingredientes originales
    private List<Ingredientes.Ingrediente> ingredientesOriginales;

    // Constructor vacío necesario para Firebase
    public RecetaMasa() {
        ingredientesOriginales = new ArrayList<>();
    }

    // Constructor con parámetros
    public RecetaMasa(String nombreDeLaMasa, String descripcionDeLaMasa, double cantidadDeAguaMasa, double cantidadDeLevaduraMasa,
                      double cantidadDePrefermentoMasa, double cantidadDeHuevosMasa, double cantidadDeEsenciaMasa,
                      double cantidadDeAzucarMasa, double cantidadDeSalMasa, double cantidadDeMargarinaMasa,
                      double cantidadDeHarinaMasa, String preparacionMasa, String temperaturasMasas,
                      String porcionadoMasas, String almacenamientoProductoFinal, double cantidadOriginal,
                      List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.nombreDeLaMasa = nombreDeLaMasa;
        this.descripcionDeLaMasa = descripcionDeLaMasa;
        this.cantidadDeAguaMasa = cantidadDeAguaMasa;
        this.cantidadDeLevaduraMasa = cantidadDeLevaduraMasa;
        this.cantidadDePrefermentoMasa = cantidadDePrefermentoMasa;
        this.cantidadDeHuevosMasa = cantidadDeHuevosMasa;
        this.cantidadDeEsenciaMasa = cantidadDeEsenciaMasa;
        this.cantidadDeAzucarMasa = cantidadDeAzucarMasa;
        this.cantidadDeSalMasa = cantidadDeSalMasa;
        this.cantidadDeMargarinaMasa = cantidadDeMargarinaMasa;
        this.cantidadDeHarinaMasa = cantidadDeHarinaMasa;
        this.preparacionMasa = preparacionMasa;
        this.temperaturasMasas = temperaturasMasas;
        this.porcionadoMasas = porcionadoMasas;
        this.almacenamientoProductoFinal = almacenamientoProductoFinal;
        this.cantidadOriginal = cantidadOriginal;
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    // Getter y Setter para ingredientesOriginales
    public List<Ingredientes.Ingrediente> getIngredientesOriginales() {
        return ingredientesOriginales;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    // Método para obtener la lista ajustada de ingredientes utilizando ingredientesOriginales
    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredientesAjustados = new ArrayList<>();

        for (Ingredientes.Ingrediente ingrediente : this.ingredientesOriginales) {
            // Calcular la cantidad ajustada usando la regla de tres
            double cantidadAjustada = (ingrediente.getCantidad() * cantidadSeleccionada) / this.cantidadOriginal;
            ingredientesAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
            Log.d("RecetaMasa", "getIngredientesAjustados: Ingrediente ajustado: " + ingrediente.getNombre() + " - Cantidad original: " + ingrediente.getCantidad() + " - Cantidad ajustada: " + cantidadAjustada);
        }

        return ingredientesAjustados;
    }

    // Otros getters y setters...
    public double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(double cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

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
        return ingredientesOriginales; // Retorna ingredientesOriginales si no tienes una lista separada
    }

    public void setIngredientes(List<Ingredientes.Ingrediente> ingredientes) {
        this.ingredientesOriginales = ingredientes != null ? ingredientes : new ArrayList<>();
    }

    public double getCantidadDeAguaMasa() {
        return cantidadDeAguaMasa;
    }

    public void setCantidadDeAguaMasa(double cantidadDeAguaMasa) {
        this.cantidadDeAguaMasa = cantidadDeAguaMasa;
    }

    public double getCantidadDeLevaduraMasa() {
        return cantidadDeLevaduraMasa;
    }

    public void setCantidadDeLevaduraMasa(double cantidadDeLevaduraMasa) {
        this.cantidadDeLevaduraMasa = cantidadDeLevaduraMasa;
    }

    public double getCantidadDePrefermentoMasa() {
        return cantidadDePrefermentoMasa;
    }

    public void setCantidadDePrefermentoMasa(double cantidadDePrefermentoMasa) {
        this.cantidadDePrefermentoMasa = cantidadDePrefermentoMasa;
    }

    public double getCantidadDeHuevosMasa() {
        return cantidadDeHuevosMasa;
    }

    public void setCantidadDeHuevosMasa(double cantidadDeHuevosMasa) {
        this.cantidadDeHuevosMasa = cantidadDeHuevosMasa;
    }

    public double getCantidadDeEsenciaMasa() {
        return cantidadDeEsenciaMasa;
    }

    public void setCantidadDeEsenciaMasa(double cantidadDeEsenciaMasa) {
        this.cantidadDeEsenciaMasa = cantidadDeEsenciaMasa;
    }

    public double getCantidadDeAzucarMasa() {
        return cantidadDeAzucarMasa;
    }

    public void setCantidadDeAzucarMasa(double cantidadDeAzucarMasa) {
        this.cantidadDeAzucarMasa = cantidadDeAzucarMasa;
    }

    public double getCantidadDeSalMasa() {
        return cantidadDeSalMasa;
    }

    public void setCantidadDeSalMasa(double cantidadDeSalMasa) {
        this.cantidadDeSalMasa = cantidadDeSalMasa;
    }

    public double getCantidadDeMargarinaMasa() {
        return cantidadDeMargarinaMasa;
    }

    public void setCantidadDeMargarinaMasa(double cantidadDeMargarinaMasa) {
        this.cantidadDeMargarinaMasa = cantidadDeMargarinaMasa;
    }

    public double getCantidadDeHarinaMasa() {
        return cantidadDeHarinaMasa;
    }

    public void setCantidadDeHarinaMasa(double cantidadDeHarinaMasa) {
        this.cantidadDeHarinaMasa = cantidadDeHarinaMasa;
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

    public String getInformacion() {
        return "Nombre: " + nombreDeLaMasa + "\n" +
                "Descripción: " + descripcionDeLaMasa + "\n" +
                "Cantidad de Agua: " + cantidadDeAguaMasa + "\n" +
                "Cantidad de Levadura: " + cantidadDeLevaduraMasa + "\n" +
                "Cantidad de Prefermento: " + cantidadDePrefermentoMasa + "\n" +
                "Cantidad de Huevos: " + cantidadDeHuevosMasa + "\n" +
                "Cantidad de Esencia: " + cantidadDeEsenciaMasa + "\n" +
                "Cantidad de Azúcar: " + cantidadDeAzucarMasa + "\n" +
                "Cantidad de Sal: " + cantidadDeSalMasa + "\n" +
                "Cantidad de Margarina: " + cantidadDeMargarinaMasa + "\n" +
                "Cantidad de Harina: " + cantidadDeHarinaMasa + "\n" +
                "Preparación: " + preparacionMasa + "\n" +
                "Temperaturas: " + temperaturasMasas + "\n" +
                "Porcionado: " + porcionadoMasas + "\n" +
                "Almacenamiento: " + almacenamientoProductoFinal;
    }
}
