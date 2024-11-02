package com.example.mi.primer.myrecetario;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class RecetaPastel {
    // Atributos de la clase
    private String nombrePastel;
    private String descripcionPastel;
    private double cantidadDeAgua;
    private double cantidadEsencia;
    private double cantidadGramosAzucar;
    private double cantidadGramosSal;
    private double cantidadGramosMargarina;
    private double cantidadGramosHarina;
    private double cantidadGramosVitina;
    private String temperaturaPastel;
    private String preparacionPastel;
    private String porcionadoPastel;
    private String almacenamientoPastel;
    private double cantidadOriginal;
    private List<Ingredientes.Ingrediente> ingredientesOriginales;

    // Constructor completo
    public RecetaPastel(String nombrePastel, String descripcionPastel, double cantidadDeAgua, double cantidadEsencia,
                        double cantidadGramosAzucar, double cantidadGramosSal, double cantidadGramosMargarina,
                        double cantidadGramosHarina, double cantidadGramosVitina, String temperaturaPastel,
                        String preparacionPastel, String porcionadoPastel, String almacenamientoPastel,
                        List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.nombrePastel = nombrePastel;
        this.descripcionPastel = descripcionPastel;
        this.cantidadDeAgua = cantidadDeAgua;
        this.cantidadEsencia = cantidadEsencia;
        this.cantidadGramosAzucar = cantidadGramosAzucar;
        this.cantidadGramosSal = cantidadGramosSal;
        this.cantidadGramosMargarina = cantidadGramosMargarina;
        this.cantidadGramosHarina = cantidadGramosHarina;
        this.cantidadGramosVitina = cantidadGramosVitina;
        this.temperaturaPastel = temperaturaPastel;
        this.preparacionPastel = preparacionPastel;
        this.porcionadoPastel = porcionadoPastel;
        this.almacenamientoPastel = almacenamientoPastel;
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    public RecetaPastel() {
    }

    // Getters y Setters
    public String getNombrePastel() { return nombrePastel; }
    public void setNombrePastel(String nombrePastel) { this.nombrePastel = nombrePastel; }
    public String getDescripcionPastel() { return descripcionPastel; }
    public void setDescripcionPastel(String descripcionPastel) { this.descripcionPastel = descripcionPastel; }
    public double getCantidadDeAgua() { return cantidadDeAgua; }
    public void setCantidadDeAgua(double cantidadDeAgua) { this.cantidadDeAgua = cantidadDeAgua; }
    public double getCantidadEsencia() { return cantidadEsencia; }
    public void setCantidadEsencia(double cantidadEsencia) { this.cantidadEsencia = cantidadEsencia; }
    public double getCantidadGramosAzucar() { return cantidadGramosAzucar; }
    public void setCantidadGramosAzucar(double cantidadGramosAzucar) { this.cantidadGramosAzucar = cantidadGramosAzucar; }
    public double getCantidadGramosSal() { return cantidadGramosSal; }
    public void setCantidadGramosSal(double cantidadGramosSal) { this.cantidadGramosSal = cantidadGramosSal; }
    public double getCantidadGramosMargarina() { return cantidadGramosMargarina; }
    public void setCantidadGramosMargarina(double cantidadGramosMargarina) { this.cantidadGramosMargarina = cantidadGramosMargarina; }
    public double getCantidadGramosHarina() { return cantidadGramosHarina; }
    public void setCantidadGramosHarina(double cantidadGramosHarina) { this.cantidadGramosHarina = cantidadGramosHarina; }
    public double getCantidadGramosVitina() { return cantidadGramosVitina; }
    public void setCantidadGramosVitina(double cantidadGramosVitina) { this.cantidadGramosVitina = cantidadGramosVitina; }
    public String getTemperaturaPastel() { return temperaturaPastel; }
    public void setTemperaturaPastel(String temperaturaPastel) { this.temperaturaPastel = temperaturaPastel; }
    public String getPreparacionPastel() { return preparacionPastel; }
    public void setPreparacionPastel(String preparacionPastel) { this.preparacionPastel = preparacionPastel; }
    public String getPorcionadoPastel() { return porcionadoPastel; }
    public void setPorcionadoPastel(String porcionadoPastel) { this.porcionadoPastel = porcionadoPastel; }
    public String getAlmacenamientoPastel() { return almacenamientoPastel; }
    public void setAlmacenamientoPastel(String almacenamientoPastel) { this.almacenamientoPastel = almacenamientoPastel; }
    public double getCantidadOriginal() { return cantidadOriginal; }
    public void setCantidadOriginal(double cantidadOriginal) { this.cantidadOriginal = cantidadOriginal; }
    public List<Ingredientes.Ingrediente> getIngredientesOriginales() { return ingredientesOriginales; }
    public void setIngredientesOriginales(List<Ingredientes.Ingrediente> ingredientesOriginales) {
        this.ingredientesOriginales = ingredientesOriginales != null ? ingredientesOriginales : new ArrayList<>();
    }

    // Método para obtener la lista ajustada de ingredientes
    public List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada) {
        List<Ingredientes.Ingrediente> ingredienteAjustados = new ArrayList<>();
        for (Ingredientes.Ingrediente ingrediente : this.ingredientesOriginales) {
            double cantidadAjustada = this.cantidadOriginal != 0 ?
                    (ingrediente.getCantidad() * cantidadSeleccionada) / this.cantidadOriginal : 0;
            ingredienteAjustados.add(new Ingredientes.Ingrediente(ingrediente.getNombre(), cantidadAjustada));
            Log.d("RecetaPastel", "getIngredientesAjustados: Ingrediente ajustado: " + ingrediente.getNombre() +
                    " - Cantidad original: " + ingrediente.getCantidad() + " - Cantidad ajustada: " + cantidadAjustada);
        }
        return ingredienteAjustados;
    }

    // Método para obtener la información de la receta
    public String getInformacion() {
        return "Nombre: " + nombrePastel + "\n" +
                "Descripción: " + descripcionPastel + "\n" +
                "Cantidad de Agua: " + cantidadDeAgua + "\n" +
                "Cantidad de Esencia: " + cantidadEsencia + "\n" +
                "Cantidad de Azúcar: " + cantidadGramosAzucar + "\n" +
                "Cantidad de Sal: " + cantidadGramosSal + "\n" +
                "Cantidad de Margarina: " + cantidadGramosMargarina + "\n" +
                "Cantidad de Harina: " + cantidadGramosHarina + "\n" +
                "Cantidad de Vitina: " + cantidadGramosVitina + "\n" +
                "Temperatura: " + temperaturaPastel + "\n" +
                "Preparación: " + preparacionPastel + "\n" +
                "Porcionado: " + porcionadoPastel + "\n" +
                "Almacenamiento: " + almacenamientoPastel;
    }
}
