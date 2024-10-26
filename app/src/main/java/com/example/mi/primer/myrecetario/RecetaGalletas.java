package com.example.mi.primer.myrecetario;

import java.util.ArrayList;
import java.util.List;

public class RecetaGalletas {
    private String nombreGalleta;
    private String descripcionGalleta;
    private double cantidadHuevosGalleta;
    private double cantidadESenciaGalleta;
    private double cantidadAzucarGalleta;
    private double cantidadMargarinaGalleta;
    private double cantidadHarinaGalleta;
    private double cantidadLimonesGalleta;
    private double cantidadChocolateGalleta;
    private String preparacionGalletas;
    private double temperaturaGalletas;
    private double porcionadoGalletas;
    private double feculaDeMaiz;
    private double polvoParaHornear;
    private String almacenamientoGalletas;
    private List<Ingredientes.Ingrediente> ingredientesOriginales;


    public RecetaGalletas(String nombre, String descripcion, double cantidadHuevos, double cantidadEsencia, double cantidadAzucar, double cantidadMargarina, double cantidadHarina, double feculaMaiz, double cantidadChocolatepp, String preparacion, double temperatura, double porcionado, String almacenamiento, List<Ingredientes.Ingrediente> listaIngredientes) {
    }

    public RecetaGalletas(String nombreGalleta, String descripcionGalleta, double cantidadHuevosGalleta, double cantidadESenciaGalleta, double cantidadAzucarGalleta, double cantidadMargarinaGalleta, double cantidadHarinaGalleta, double cantidadLimonesGalleta, double cantidadChocolateGalleta, String preparacionGalletas, double temperaturaGalletas, double porcionadoGalletas, double feculaDeMaiz, double polvoParaHornear, String almacenamientoGalletas, List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.nombreGalleta = nombreGalleta;
        this.descripcionGalleta = descripcionGalleta;
        this.cantidadHuevosGalleta = cantidadHuevosGalleta;
        this.cantidadESenciaGalleta = cantidadESenciaGalleta;
        this.cantidadAzucarGalleta = cantidadAzucarGalleta;
        this.cantidadMargarinaGalleta = cantidadMargarinaGalleta;
        this.cantidadHarinaGalleta = cantidadHarinaGalleta;
        this.cantidadLimonesGalleta = cantidadLimonesGalleta;
        this.cantidadChocolateGalleta = cantidadChocolateGalleta;
        this.preparacionGalletas = preparacionGalletas;
        this.temperaturaGalletas = temperaturaGalletas;
        this.porcionadoGalletas = porcionadoGalletas;
        this.feculaDeMaiz = feculaDeMaiz;
        this.polvoParaHornear = polvoParaHornear;
        this.almacenamientoGalletas = almacenamientoGalletas;
        this.ingredientesOriginales = ingredientesOriginales;
    }

    public String getNombreGalleta() {
        return nombreGalleta;
    }

    public void setNombreGalleta(String nombreGalleta) {
        this.nombreGalleta = nombreGalleta;
    }

    public String getDescripcionGalleta() {
        return descripcionGalleta;
    }

    public void setDescripcionGalleta(String descripcionGalleta) {
        this.descripcionGalleta = descripcionGalleta;
    }

    public double getCantidadHuevosGalleta() {
        return cantidadHuevosGalleta;
    }

    public void setCantidadHuevosGalleta(double cantidadHuevosGalleta) {
        this.cantidadHuevosGalleta = cantidadHuevosGalleta;
    }

    public double getCantidadESenciaGalleta() {
        return cantidadESenciaGalleta;
    }

    public void setCantidadESenciaGalleta(double cantidadESenciaGalleta) {
        this.cantidadESenciaGalleta = cantidadESenciaGalleta;
    }

    public double getCantidadAzucarGalleta() {
        return cantidadAzucarGalleta;
    }

    public void setCantidadAzucarGalleta(double cantidadAzucarGalleta) {
        this.cantidadAzucarGalleta = cantidadAzucarGalleta;
    }

    public double getCantidadMargarinaGalleta() {
        return cantidadMargarinaGalleta;
    }

    public void setCantidadMargarinaGalleta(double cantidadMargarinaGalleta) {
        this.cantidadMargarinaGalleta = cantidadMargarinaGalleta;
    }

    public double getCantidadHarinaGalleta() {
        return cantidadHarinaGalleta;
    }

    public void setCantidadHarinaGalleta(double cantidadHarinaGalleta) {
        this.cantidadHarinaGalleta = cantidadHarinaGalleta;
    }

    public double getCantidadLimonesGalleta() {
        return cantidadLimonesGalleta;
    }

    public void setCantidadLimonesGalleta(double cantidadLimonesGalleta) {
        this.cantidadLimonesGalleta = cantidadLimonesGalleta;
    }

    public double getCantidadChocolateGalleta() {
        return cantidadChocolateGalleta;
    }

    public void setCantidadChocolateGalleta(double cantidadChocolateGalleta) {
        this.cantidadChocolateGalleta = cantidadChocolateGalleta;
    }

    public String getPreparacionGalletas() {
        return preparacionGalletas;
    }

    public void setPreparacionGalletas(String preparacionGalletas) {
        this.preparacionGalletas = preparacionGalletas;
    }

    public double getTemperaturaGalletas() {
        return temperaturaGalletas;
    }

    public void setTemperaturaGalletas(double temperaturaGalletas) {
        this.temperaturaGalletas = temperaturaGalletas;
    }

    public double getPorcionadoGalletas() {
        return porcionadoGalletas;
    }

    public void setPorcionadoGalletas(double porcionadoGalletas) {
        this.porcionadoGalletas = porcionadoGalletas;
    }

    public double getFeculaDeMaiz() {
        return feculaDeMaiz;
    }

    public void setFeculaDeMaiz(double feculaDeMaiz) {
        this.feculaDeMaiz = feculaDeMaiz;
    }

    public double getPolvoParaHornear() {
        return polvoParaHornear;
    }

    public void setPolvoParaHornear(double polvoParaHornear) {
        this.polvoParaHornear = polvoParaHornear;
    }

    public String getAlmacenamientoGalletas() {
        return almacenamientoGalletas;
    }

    public void setAlmacenamientoGalletas(String almacenamientoGalletas) {
        this.almacenamientoGalletas = almacenamientoGalletas;
    }

    public List<Ingredientes.Ingrediente> getIngredientesOriginales() {
        return ingredientesOriginales;
    }

    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();;
    }
    public String getInformacion() {
        return "Nombre: " + nombreGalleta + "\n" +
                "Descripción: " + descripcionGalleta + "\n" +
                "Cantidad de Huevos: " + cantidadHuevosGalleta + "\n" +
                "Cantidad de Esencia: " + cantidadESenciaGalleta + "\n" +
                "Cantidad de Azúcar: " + cantidadAzucarGalleta + "\n" +
                "Cantidad de Margarina: " + cantidadMargarinaGalleta + "\n" +
                "Cantidad de Harina: " + cantidadHarinaGalleta + "\n" +
                "Cantidad de Fecula de Maiz: " + feculaDeMaiz + "\n" +
                "Cantidad de Polvo para hornear: " + polvoParaHornear + "\n" +
                "Cantidad de Limones: " + cantidadLimonesGalleta + "\n" +
                "Cantidad de Chocolate: " + cantidadChocolateGalleta + "\n" +
                "Preparación: " + preparacionGalletas + "\n" +
                "Temperaturas: " + temperaturaGalletas + "\n" +
                "Porcionado: " + porcionadoGalletas + "\n" +
                "Almacenamiento: " + almacenamientoGalletas;
    }
}
