package com.example.mi.primer.myrecetario;

import java.util.ArrayList;
import java.util.List;

public class RecetaMasa {
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
    private int cantidadOriginal;
    private List<Ingredientes.Ingrediente> ingredientes;



    public void setIngredientes(List<Ingredientes.Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    // Constructor vacío necesario para Firebase
    public RecetaMasa() {
    }

    public int getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(int cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    // Constructor con parámetros
    public RecetaMasa(String nombreDeLaMasa, String descripcionDeLaMasa, double cantidadDeAguaMasa, double cantidadDeLevaduraMasa,
                      double cantidadDePrefermentoMasa, double cantidadDeHuevosMasa, double cantidadDeEsenciaMasa,
                      double cantidadDeAzucarMasa, double cantidadDeSalMasa, double cantidadDeMargarinaMasa,
                      double cantidadDeHarinaMasa, String preparacionMasa, String temperaturasMasas,
                      String porcionadoMasas, String almacenamientoProductoFinal,int cantidadOriginal,
                      List<Ingredientes.Ingrediente> ingredientes) {
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
        this.ingredientes = ingredientes;
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
    // Agregar el método getter para ingredientes
    public List<Ingredientes.Ingrediente> getIngredientes() {
        return ingredientes;
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

    // Método para obtener información completa de la receta
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
    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredientesAjustados = new ArrayList<>();

        for (Ingredientes.Ingrediente ingrediente : this.ingredientes) {
            // Calcular la cantidad ajustada usando la regla de tres
            double cantidadAjustada = (ingrediente.getCantidad() / this.cantidadOriginal) * cantidadSeleccionada;
            ingredientesAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
        }

        return ingredientesAjustados;
    }



}
