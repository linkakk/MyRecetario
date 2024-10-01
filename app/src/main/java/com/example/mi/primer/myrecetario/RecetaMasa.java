package com.example.mi.primer.myrecetario;

public class RecetaMasa {
    private String nombreDeLaMasa;
    private String descripcionDeLaMasa;
    private String cantidadDeAguaMasa;
    private String cantidadDeLevaduraMasa;
    private String cantidadDePrefermentoMasa;
    private String cantidadDeHuevosMasa;
    private String cantidadDeEsenciaMasa;
    private String cantidadDeAzucarMasa;
    private String cantidadDeSalMasa;
    private String cantidadDeMargarinaMasa;
    private String cantidadDeHarinaMasa;
    private String preparacionMasa;
    private String temperaturasMasas;
    private String porcionadoMasas;
    private String almacenamientoProductoFinal;

    // Constructor vacío necesario para Firebase
    public RecetaMasa() {
    }

    // Constructor con parámetros
    public RecetaMasa(String nombreDeLaMasa, String descripcionDeLaMasa, String cantidadDeAguaMasa, String cantidadDeLevaduraMasa,
                      String cantidadDePrefermentoMasa, String cantidadDeHuevosMasa, String cantidadDeEsenciaMasa,
                      String cantidadDeAzucarMasa, String cantidadDeSalMasa, String cantidadDeMargarinaMasa,
                      String cantidadDeHarinaMasa, String preparacionMasa, String temperaturasMasas,
                      String porcionadoMasas, String almacenamientoProductoFinal) {
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
    }

    // Getters y setters para cada campo
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

    public String getCantidadDeAguaMasa() {
        return cantidadDeAguaMasa;
    }

    public void setCantidadDeAguaMasa(String cantidadDeAguaMasa) {
        this.cantidadDeAguaMasa = cantidadDeAguaMasa;
    }

    public String getCantidadDeLevaduraMasa() {
        return cantidadDeLevaduraMasa;
    }

    public void setCantidadDeLevaduraMasa(String cantidadDeLevaduraMasa) {
        this.cantidadDeLevaduraMasa = cantidadDeLevaduraMasa;
    }

    public String getCantidadDePrefermentoMasa() {
        return cantidadDePrefermentoMasa;
    }

    public void setCantidadDePrefermentoMasa(String cantidadDePrefermentoMasa) {
        this.cantidadDePrefermentoMasa = cantidadDePrefermentoMasa;
    }

    public String getCantidadDeHuevosMasa() {
        return cantidadDeHuevosMasa;
    }

    public void setCantidadDeHuevosMasa(String cantidadDeHuevosMasa) {
        this.cantidadDeHuevosMasa = cantidadDeHuevosMasa;
    }

    public String getCantidadDeEsenciaMasa() {
        return cantidadDeEsenciaMasa;
    }

    public void setCantidadDeEsenciaMasa(String cantidadDeEsenciaMasa) {
        this.cantidadDeEsenciaMasa = cantidadDeEsenciaMasa;
    }

    public String getCantidadDeAzucarMasa() {
        return cantidadDeAzucarMasa;
    }

    public void setCantidadDeAzucarMasa(String cantidadDeAzucarMasa) {
        this.cantidadDeAzucarMasa = cantidadDeAzucarMasa;
    }

    public String getCantidadDeSalMasa() {
        return cantidadDeSalMasa;
    }

    public void setCantidadDeSalMasa(String cantidadDeSalMasa) {
        this.cantidadDeSalMasa = cantidadDeSalMasa;
    }

    public String getCantidadDeMargarinaMasa() {
        return cantidadDeMargarinaMasa;
    }

    public void setCantidadDeMargarinaMasa(String cantidadDeMargarinaMasa) {
        this.cantidadDeMargarinaMasa = cantidadDeMargarinaMasa;
    }

    public String getCantidadDeHarinaMasa() {
        return cantidadDeHarinaMasa;
    }

    public void setCantidadDeHarinaMasa(String cantidadDeHarinaMasa) {
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
}
