package com.example.mi.primer.myrecetario.model.produccion.enums;

/**
 * Enum que representa los estados posibles del cierre general de producción del día.
 *
 * Este estado aplica al nodo:
 *
 * produccionDiaria/{fecha}/cierreDia/estadoCierre
 *
 * IMPORTANTE:
 * Este enum NO representa el estado de cada producto.
 * Para productos, subproductos y grupos se usa EstadoRegistroProduccion.
 *
 * Firebase Realtime Database debe guardar este valor como String:
 *
 * estadoCierre: "ABIERTO"
 * estadoCierre: "BLOQUEADO"
 * estadoCierre: "CERRADO"
 *
 * El enum existe para evitar textos sueltos en el código Java.
 */
public enum EstadoCierreProduccion {

    /**
     * Estado inicial del cierre del día.
     *
     * Significa:
     * - La producción del día aún está abierta.
     * - Se pueden seguir registrando productos, grupos y tandas.
     * - Todavía no se ha ejecutado el cierre final.
     */
    ABIERTO("ABIERTO"),

    /**
     * Estado usado cuando el sistema intenta cerrar el día,
     * pero encuentra productos, grupos o subproductos bloqueantes.
     *
     * Bloquean el cierre:
     * - SIN_RESPONDER
     * - PENDIENTE
     *
     * Este estado permite dejar trazabilidad de que el cierre fue intentado,
     * pero no se pudo completar por información faltante.
     */
    BLOQUEADO("BLOQUEADO"),

    /**
     * Estado usado cuando la producción del día fue cerrada correctamente.
     *
     * Significa:
     * - No quedan productos frecuentes en SIN_RESPONDER.
     * - No quedan productos, grupos o subproductos en PENDIENTE.
     * - El cierre general fue confirmado por un usuario responsable.
     *
     * Después de este estado, la producción del día debería considerarse
     * finalizada para el MVP.
     */
    CERRADO("CERRADO");

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
    EstadoCierreProduccion(String value) {
        this.value = value;
    }

    /**
     * Retorna el valor String que debe guardarse en Firebase.
     *
     * @return valor textual del estado de cierre.
     */
    public String getValue() {
        return value;
    }

    /**
     * Convierte un String guardado en Firebase a EstadoCierreProduccion.
     *
     * Este método evita errores si Firebase devuelve:
     * - null
     * - texto vacío
     * - un valor desconocido
     *
     * Regla de seguridad:
     * Si el valor no existe o no coincide, se devuelve ABIERTO.
     *
     * @param value valor leído desde Firebase.
     * @return EstadoCierreProduccion correspondiente.
     */
    public static EstadoCierreProduccion fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return ABIERTO;
        }

        for (EstadoCierreProduccion estado : EstadoCierreProduccion.values()) {
            if (estado.value.equals(value)) {
                return estado;
            }
        }

        return ABIERTO;
    }

    /**
     * Indica si el cierre del día todavía permite modificaciones.
     *
     * En el MVP:
     * - ABIERTO permite registrar producción.
     * - BLOQUEADO también permite corregir datos.
     * - CERRADO no debería permitir modificaciones normales.
     *
     * @return true si todavía se puede modificar la producción del día.
     */
    public boolean permiteModificaciones() {
        return this == ABIERTO || this == BLOQUEADO;
    }

    /**
     * Indica si el cierre del día ya fue finalizado.
     *
     * @return true si el estado es CERRADO.
     */
    public boolean estaCerrado() {
        return this == CERRADO;
    }

    /**
     * Indica si el cierre está bloqueado por productos, grupos o subproductos
     * que todavía están en estados que no permiten cerrar el día.
     *
     * @return true si el estado es BLOQUEADO.
     */
    public boolean estaBloqueado() {
        return this == BLOQUEADO;
    }

    /**
     * Indica si el día está abierto y puede seguir recibiendo registros.
     *
     * @return true si el estado es ABIERTO.
     */
    public boolean estaAbierto() {
        return this == ABIERTO;
    }
}
