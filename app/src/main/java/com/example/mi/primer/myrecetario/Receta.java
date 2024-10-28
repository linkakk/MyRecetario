package com.example.mi.primer.myrecetario;

import java.util.List;

public interface Receta {
    /**
     * Obtiene el nombre de la receta.
     * @return el nombre de la receta.
     */
    String getNombre();

    /**
     * Obtiene la cantidad original de la receta para fines de ajuste.
     * @return la cantidad original de la receta.
     */
    double getCantidadOriginal();

    /**
     * Ajusta la cantidad de ingredientes según la cantidad seleccionada.
     * @param cantidadSeleccionada La cantidad a la que se ajustarán los ingredientes.
     * @return una lista de ingredientes ajustados.
     */
    List<Ingredientes.Ingrediente> getIngredientesAjustados(double cantidadSeleccionada);

    /**
     * Obtiene la descripción de la receta.
     * @return la descripción de la receta.
     */
    String getDescripcion();

    /**
     * Devuelve la lista completa de ingredientes de la receta sin ajustar.
     * @return la lista de ingredientes originales.
     */
    List<Ingredientes.Ingrediente> getIngredientes();

    /**
     * Devuelve información general de la receta en formato de texto.
     * @return información de la receta.
     */
    String getInformacion();
}
