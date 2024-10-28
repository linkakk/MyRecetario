package com.example.mi.primer.myrecetario;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class RecetaMasa implements Receta {
    // Campos de clase
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
    private double cantidadOriginal;  // Cantidad de referencia para ajustes

    // Lista para almacenar los ingredientes originales
    private List<Ingredientes.Ingrediente> ingredientesOriginales;

    // Constructor vacío necesario para Firebase
    public RecetaMasa() {
        ingredientesOriginales = new ArrayList<>();
        Log.d("RecetaMasa", "Constructor vacío de RecetaMasa llamado.");
    }

    // Constructor completo
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

        Log.d("RecetaMasa", "Constructor de RecetaMasa llamado con nombre: " + nombreDeLaMasa);
    }

    // Implementación de métodos de la interfaz
    @Override
    public String getNombre() {
        Log.d("RecetaMasa", "getNombre: " + nombreDeLaMasa);
        return nombreDeLaMasa != null ? nombreDeLaMasa : "Sin nombre";
    }

    @Override
    public String getDescripcion() {
        Log.d("RecetaMasa", "getDescripcion: " + descripcionDeLaMasa);
        return descripcionDeLaMasa != null ? descripcionDeLaMasa : "Sin descripción";
    }

    @Override
    public double getCantidadOriginal() {
        Log.d("RecetaMasa", "getCantidadOriginal: " + cantidadOriginal);
        return cantidadOriginal > 0 ? cantidadOriginal : 1.0; // Asegura un valor no cero para evitar divisiones por cero
    }

    @Override
    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredientesAjustados = new ArrayList<>();
        if (cantidadOriginal == 0) {
            Log.w("RecetaMasa", "Cantidad original es cero, ajuste no es posible.");
            return ingredientesAjustados;
        }

        for (Ingredientes.Ingrediente ingrediente : ingredientesOriginales) {
            double cantidadAjustada = (ingrediente.getCantidad() * cantidadSeleccionada) / cantidadOriginal;
            ingredientesAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
            Log.d("RecetaMasa", "Ingrediente ajustado: " + ingrediente.getNombre() + " - Cantidad original: " + ingrediente.getCantidad() + " - Cantidad ajustada: " + cantidadAjustada);
        }

        return ingredientesAjustados;
    }

    public String getNombreDeLaMasa() {
        return nombreDeLaMasa;
    }

    public String getDescripcionDeLaMasa() {
        return descripcionDeLaMasa;
    }

    public List<Ingredientes.Ingrediente> getIngredientesOriginales() {
        return ingredientesOriginales;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales;
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

    @Override
    public List<Ingredientes.Ingrediente> getIngredientes() {
        Log.d("RecetaMasa", "getIngredientes: " + ingredientesOriginales.size() + " ingredientes.");
        return ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    @Override
    public String getInformacion() {
        Log.d("RecetaMasa", "getInformacion: Generando información completa de la receta.");
        return "Nombre: " + getNombre() + "\n" +
                "Descripción: " + getDescripcion() + "\n" +
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

    // Otros getters y setters con Log.d adicional para asegurar el correcto flujo de datos
    public void setNombreDeLaMasa(String nombreDeLaMasa) {
        this.nombreDeLaMasa = nombreDeLaMasa;
        Log.d("RecetaMasa", "setNombreDeLaMasa: Nombre establecido a " + nombreDeLaMasa);
    }

    public void setDescripcionDeLaMasa(String descripcionDeLaMasa) {
        this.descripcionDeLaMasa = descripcionDeLaMasa;
        Log.d("RecetaMasa", "setDescripcionDeLaMasa: Descripción establecida a " + descripcionDeLaMasa);
    }

    public void setCantidadOriginal(double cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
        Log.d("RecetaMasa", "setCantidadOriginal: Cantidad original establecida a " + cantidadOriginal);
    }

    // Otros métodos se mantienen igual
}
