package com.example.mi.primer.myrecetario.model.produccion.enums;

/**
 * Enum que representa el tipo de elemento dentro del módulo de producción.
 *
 * Este enum permite diferenciar entre:
 *
 * - PRODUCTO: producto individual que puede registrarse directamente.
 * - GRUPO: bloque operativo que agrupa varios subproductos.
 *
 * IMPORTANTE:
 * Firebase Realtime Database NO debe guardar este enum directamente.
 * En Firebase se guarda el valor como String:
 *
 * tipoElemento: "PRODUCTO"
 * tipoElemento: "GRUPO"
 *
 * Este enum existe para evitar textos sueltos en Java y centralizar
 * los valores permitidos.
 */
public enum TipoElementoProduccion {

    /**
     * Representa un producto individual.
     *
     * Ejemplos:
     * - Buñuelo
     * - Pan crema
     * - Pan leche
     * - Palotes
     *
     * Un producto puede:
     * - ser de cantidad fija
     * - ser de cantidad variable
     * - pertenecer a un grupo
     * - usar base compartida del grupo
     */
    PRODUCTO("PRODUCTO"),

    /**
     * Representa un grupo de producción.
     *
     * Ejemplos:
     * - Pan base leche
     * - Pan al día
     * - Masa dulce
     *
     * Un grupo puede:
     * - agrupar subproductos
     * - usar base compartida
     * - requerir o no cantidad de base compartida
     *
     * Un grupo NO representa directamente una tanda de producto final.
     * Sirve para organizar el flujo operativo de producción.
     */
    GRUPO("GRUPO");

    /**
     * Valor exacto que se guarda en Firebase Realtime Database.
     *
     * No se debe cambiar este texto sin considerar migración de datos,
     * porque los registros antiguos seguirán guardados con estos valores.
     */
    private final String value;

    /**
     * Constructor interno del enum.
     *
     * @param value texto que se guarda en Firebase.
     */
    TipoElementoProduccion(String value) {
        this.value = value;
    }

    /**
     * Retorna el valor String que debe guardarse en Firebase.
     *
     * @return valor textual del tipo de elemento.
     */
    public String getValue() {
        return value;
    }

    /**
     * Convierte un String guardado en Firebase a TipoElementoProduccion.
     *
     * Este método evita errores si Firebase devuelve:
     * - null
     * - texto vacío
     * - un valor desconocido
     *
     * Regla de seguridad:
     * Si el valor no existe o no coincide, se devuelve PRODUCTO.
     *
     * PRODUCTO es el valor por defecto porque es el caso más común y menos
     * complejo que GRUPO.
     *
     * @param value valor leído desde Firebase.
     * @return TipoElementoProduccion correspondiente.
     */
    public static TipoElementoProduccion fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return PRODUCTO;
        }

        for (TipoElementoProduccion tipo : TipoElementoProduccion.values()) {
            if (tipo.value.equals(value)) {
                return tipo;
            }
        }

        return PRODUCTO;
    }

    /**
     * Indica si el elemento es un producto individual.
     *
     * @return true si es PRODUCTO.
     */
    public boolean esProducto() {
        return this == PRODUCTO;
    }

    /**
     * Indica si el elemento es un grupo de producción.
     *
     * @return true si es GRUPO.
     */
    public boolean esGrupo() {
        return this == GRUPO;
    }
}
