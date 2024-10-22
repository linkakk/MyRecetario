package com.example.mi.primer.myrecetario;

import java.util.List;

public class RecetaBatidos {
    private String nombreDelBatido;
    private String DescripcionDelBatido;
    private double azucarDelBatido;
    private double medioGrasoDelBatido;
    private double esenciaDelBatido;
    private double huevosDelBatido;
    private double harinaDelBatido;
    private double polvoParaHornearDelBatido;
    private double liquidoDelBatido;
    private double complementoDelBatido;
    //este sera opcional
    private double chocolateDelBatido;

    public RecetaBatidos(String nombreDelBatido, String descripcionDelBatido, double azucarDelBatido, double medioGrasoDelBatido, double esenciaDelBatido, double huevosDelBatido, double harinaDelBatido, double polvoParaHornearDelBatido, double liquidoDelBatido, double complementoDelBatido, double chocolateDelBatido, double cantidadOriginal, List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.nombreDelBatido = nombreDelBatido;
        DescripcionDelBatido = descripcionDelBatido;
        this.azucarDelBatido = azucarDelBatido;
        this.medioGrasoDelBatido = medioGrasoDelBatido;
        this.esenciaDelBatido = esenciaDelBatido;
        this.huevosDelBatido = huevosDelBatido;
        this.harinaDelBatido = harinaDelBatido;
        this.polvoParaHornearDelBatido = polvoParaHornearDelBatido;
        this.liquidoDelBatido = liquidoDelBatido;
        this.complementoDelBatido = complementoDelBatido;
        this.chocolateDelBatido = chocolateDelBatido;
        this.cantidadOriginal = cantidadOriginal;
        this.ingredientesOriginales = ingredientesOriginales;
    }
//constructor vacio para que interactue con fireBase
    public RecetaBatidos() {
    }

    public String getNombreDelBatido() {
        return nombreDelBatido;
    }

    public void setNombreDelBatido(String nombreDelBatido) {
        this.nombreDelBatido = nombreDelBatido;
    }

    public String getDescripcionDelBatido() {
        return DescripcionDelBatido;
    }

    public void setDescripcionDelBatido(String descripcionDelBatido) {
        DescripcionDelBatido = descripcionDelBatido;
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

    public double getLiquidoDelBatido() {
        return liquidoDelBatido;
    }

    public void setLiquidoDelBatido(double liquidoDelBatido) {
        this.liquidoDelBatido = liquidoDelBatido;
    }

    public double getComplementoDelBatido() {
        return complementoDelBatido;
    }

    public void setComplementoDelBatido(double complementoDelBatido) {
        this.complementoDelBatido = complementoDelBatido;
    }

    public double getChocolateDelBatido() {
        return chocolateDelBatido;
    }

    public void setChocolateDelBatido(double chocolateDelBatido) {
        this.chocolateDelBatido = chocolateDelBatido;
    }

    public double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(double cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    public List<Ingredientes.Ingrediente> getIngredientesOriginales() {
        return ingredientesOriginales;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales;
    }

    private double cantidadOriginal;
    // Nueva lista para almacenar los ingredientes originales
    private List<Ingredientes.Ingrediente> ingredientesOriginales;

}
