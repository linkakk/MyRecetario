package com.example.mi.primer.myrecetario.model.produccion.enums;

/**
 * Enum que representa el tipo de cantidad usada para registrar un producto
 * dentro del módulo de producción.
 *
 * Este enum permite diferenciar entre:
 *
 * - FIJA: el producto normalmente tiene una cantidad esperada.
 * - VARIABLE: la cantidad producida se ingresa cada vez que se registra.
 *
 * IMPORTANTE:
 * Firebase Realtime Database NO debe guardar este enum directamente.
 * En Firebase se guarda el valor como String:
 *
 * tipoCantidad: "FIJA"
 * tipoCantidad: "VARIABLE"
 *
 * Este enum existe para evitar textos sueltos en Java y centralizar
 * los valores permitidos.
 */
public enum TipoCantidadProduccion {

    /**
     * Producto con cantidad esperada o predeterminada.
     *
     * Ejemplo:
     * - Buñuelo: 70 unidades
     * - Pan crema: 80 unidades
     *
     * Flujo esperado:
     * El empleado puede confirmar rápidamente:
     *
     * "Sí, 70 unidades"
     *
     * Si confirma la cantidad esperada:
     * - se crea una tanda
     * - totalProducido toma la cantidad predeterminada
     * - cantidadTandas aumenta
     * - el estado pasa a LISTO
     *
     * Si la cantidad salió diferente, puede editarla si
     * permiteEditarCantidad = true.
     */
    FIJA("FIJA"),

    /**
     * Producto cuya cantidad cambia según la producción del día.
     *
     * Ejemplo:
     * - Pan leche
     * - Palotes
     * - Pan semihojaldre
     *
     * Flujo esperado:
     * El empleado debe ingresar la cantidad producida.
     *
     * Si el producto requiere cantidad base, también debe ingresar:
     * - cantidadBaseTrabajada
     *
     * Ejemplo:
     * Masa semihojaldre: 10 libras
     * Cantidad producida: 150 unidades
     *
     * Si falta un dato obligatorio, el registro queda en PENDIENTE.
     * Si está completo, queda en LISTO.
     */
    VARIABLE("VARIABLE");

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
    TipoCantidadProduccion(String value) {
        this.value = value;
    }

    /**
     * Retorna el valor String que debe guardarse en Firebase.
     *
     * @return valor textual del tipo de cantidad.
     */
    public String getValue() {
        return value;
    }

    /**
     * Convierte un String guardado en Firebase a TipoCantidadProduccion.
     *
     * Este método evita errores si Firebase devuelve:
     * - null
     * - texto vacío
     * - un valor desconocido
     *
     * Regla de seguridad:
     * Si el valor no existe o no coincide, se devuelve VARIABLE.
     *
     * Se usa VARIABLE como valor por defecto porque obliga al usuario o al
     * repositorio a registrar una cantidad explícita, evitando asumir una
     * cantidad fija incorrecta.
     *
     * @param value valor leído desde Firebase.
     * @return TipoCantidadProduccion correspondiente.
     */
    public static TipoCantidadProduccion fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return VARIABLE;
        }

        for (TipoCantidadProduccion tipo : TipoCantidadProduccion.values()) {
            if (tipo.value.equals(value)) {
                return tipo;
            }
        }

        return VARIABLE;
    }

    /**
     * Indica si el producto usa cantidad fija.
     *
     * @return true si el tipo de cantidad es FIJA.
     */
    public boolean esFija() {
        return this == FIJA;
    }

    /**
     * Indica si el producto usa cantidad variable.
     *
     * @return true si el tipo de cantidad es VARIABLE.
     */
    public boolean esVariable() {
        return this == VARIABLE;
    }
}
