package com.example.mi.primer.myrecetario.model.produccion.enums;

/**
 * Enum que representa los estados posibles de un registro de producción diaria.
 *
 * Este estado aplica para:
 * - productos frecuentes
 * - productos ocasionales registrados
 * - subproductos dentro de grupos
 * - registros individuales dentro de produccionDiaria/{fecha}/registrosProductos
 *
 * IMPORTANTE:
 * Firebase Realtime Database NO debe guardar este enum directamente.
 * En Firebase se guarda el valor como String:
 *
 * estado: "SIN_RESPONDER"
 * estado: "LISTO"
 * estado: "CERRADO"
 *
 * Este enum existe para evitar errores internos en Java y centralizar
 * los valores permitidos.
 */
public enum EstadoRegistroProduccion {

    /**
     * Estado inicial de un producto o grupo frecuente al abrir el día.
     *
     * Significa:
     * - El usuario aún no marcó si se hizo o no se hizo.
     * - Bloquea el cierre de producción del día.
     *
     * En Firebase:
     * totalProducido = null
     * sobrante = null
     * merma = null
     * vendidoEstimado = null
     */
    SIN_RESPONDER("SIN_RESPONDER"),

    /**
     * Estado usado cuando el usuario empezó a registrar información,
     * pero todavía falta un dato obligatorio.
     *
     * Ejemplos:
     * - Producto variable sin cantidad producida.
     * - Grupo con base obligatoria pero sin cantidadBaseCompartida.
     *
     * Bloquea el cierre de producción del día.
     */
    PENDIENTE("PENDIENTE"),

    /**
     * Estado usado cuando el usuario confirma que el producto o grupo
     * no se hizo durante el día.
     *
     * No bloquea el cierre de producción del día.
     *
     * En Firebase:
     * totalProducido = 0
     * cantidadTandas = 0
     */
    NO_HECHO("NO_HECHO"),

    /**
     * Estado usado cuando el producto ya fue respondido correctamente.
     *
     * LISTO NO significa cierre individual.
     * Significa que el producto ya tiene una respuesta válida para el día.
     *
     * Puede recibir más tandas durante la jornada.
     *
     * No bloquea el cierre de producción del día.
     *
     * En Firebase:
     * totalProducido > 0
     * sobrante = null
     * merma = null
     * vendidoEstimado = null
     */
    LISTO("LISTO"),

    /**
     * Estado usado cuando el producto tiene cierre individual.
     *
     * CERRADO significa que el usuario registró:
     * - sobrante
     * - merma
     * - vendidoEstimado
     *
     * No bloquea el cierre de producción del día.
     *
     * En Firebase:
     * vendidoEstimado = totalProducido - sobrante - merma
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
    EstadoRegistroProduccion(String value) {
        this.value = value;
    }

    /**
     * Retorna el valor String que debe guardarse en Firebase.
     *
     * @return valor textual del estado.
     */
    public String getValue() {
        return value;
    }

    /**
     * Convierte un String guardado en Firebase a EstadoRegistroProduccion.
     *
     * Este método evita que la app se rompa si Firebase devuelve:
     * - null
     * - un texto vacío
     * - un valor desconocido
     *
     * Regla de seguridad:
     * Si el valor no existe o no coincide, se devuelve SIN_RESPONDER.
     *
     * @param value valor leído desde Firebase.
     * @return EstadoRegistroProduccion correspondiente.
     */
    public static EstadoRegistroProduccion fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return SIN_RESPONDER;
        }

        for (EstadoRegistroProduccion estado : EstadoRegistroProduccion.values()) {
            if (estado.value.equals(value)) {
                return estado;
            }
        }

        return SIN_RESPONDER;
    }

    /**
     * Indica si este estado bloquea el cierre de producción del día.
     *
     * Regla oficial:
     * - SIN_RESPONDER bloquea.
     * - PENDIENTE bloquea.
     * - NO_HECHO permite cerrar.
     * - LISTO permite cerrar.
     * - CERRADO permite cerrar.
     *
     * @return true si bloquea el cierre del día.
     */
    public boolean bloqueaCierreDelDia() {
        return this == SIN_RESPONDER || this == PENDIENTE;
    }

    /**
     * Indica si este estado permite cerrar la producción del día.
     *
     * @return true si permite cerrar el día.
     */
    public boolean permiteCerrarElDia() {
        return !bloqueaCierreDelDia();
    }

    /**
     * Indica si el estado representa una respuesta válida del usuario.
     *
     * Se considera respondido cuando el usuario ya tomó una decisión:
     * - No se hizo.
     * - Se registró producción.
     * - Se cerró individualmente el producto.
     *
     * @return true si el registro ya fue respondido.
     */
    public boolean estaRespondido() {
        return this == NO_HECHO || this == LISTO || this == CERRADO;
    }
}
