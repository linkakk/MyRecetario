package com.example.mi.primer.myrecetario.model.produccion;

import com.example.mi.primer.myrecetario.model.produccion.enums.EstadoRegistroProduccion;
import com.google.firebase.database.Exclude;

/**
 * Modelo que representa el estado diario de un grupo de producción.
 *
 * Este modelo se guarda en:
 *
 * produccionDiaria/{fecha}/gruposProduccion/{grupoId}
 *
 * Responsabilidad:
 * Guardar el estado del grupo durante una fecha específica.
 *
 * Ejemplo:
 * Grupo: Pan base leche
 * Base compartida: Masa pan base leche
 * Cantidad base compartida: 10 libras
 *
 * IMPORTANTE:
 * Esta clase NO representa un producto individual.
 * Esta clase NO representa una tanda.
 * Esta clase NO representa el cierre general del día.
 *
 * Los subproductos del grupo se guardan como registros normales en:
 *
 * produccionDiaria/{fecha}/registrosProductos/{productoId}
 */
public class GrupoProduccionDia {

    /**
     * Identificador único del grupo.
     *
     * Debe coincidir con la clave del nodo:
     * produccionDiaria/{fecha}/gruposProduccion/{grupoId}
     *
     * Ejemplo:
     * "pan_base_leche"
     */
    private String grupoId;

    /**
     * Fecha de producción.
     *
     * Formato recomendado:
     * yyyy-MM-dd
     *
     * Ejemplo:
     * "2026-05-03"
     */
    private String fecha;

    /**
     * Nombre visible del grupo.
     *
     * Ejemplo:
     * "Pan base leche"
     * "Pan al día"
     */
    private String nombreGrupo;

    /**
     * Estado del grupo en el día.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "SIN_RESPONDER"
     * - "PENDIENTE"
     * - "NO_HECHO"
     * - "LISTO"
     *
     * Normalmente un grupo no usa CERRADO, porque el cierre individual
     * aplica a productos, no al grupo operativo.
     */
    private String estado;

    /**
     * Indica si el grupo era frecuente al momento de crear el día.
     *
     * Si es true:
     * - debe ser respondido
     * - si queda SIN_RESPONDER, bloquea el cierre del día
     */
    private Boolean fabricacionFrecuente;

    /**
     * Indica si el grupo usa una base compartida.
     *
     * Ejemplo:
     * Grupo: Pan base leche
     * Base compartida: Masa pan base leche
     */
    private Boolean usaBaseCompartida;

    /**
     * Nombre de la base compartida del grupo.
     *
     * Ejemplo:
     * "Masa pan base leche"
     *
     * Este nombre viene desde la configuración del administrador.
     * El empleado no debe escribir ni seleccionar este nombre.
     */
    private String nombreBaseCompartida;

    /**
     * Unidad de la base compartida.
     *
     * Ejemplo:
     * "libras"
     * "gramos"
     */
    private String unidadBaseCompartida;

    /**
     * Indica si el grupo exige que el empleado escriba
     * la cantidad de base compartida trabajada.
     *
     * Diferencia importante:
     *
     * usaBaseCompartida:
     * ¿El grupo tiene una base común?
     *
     * requiereCantidadBaseCompartida:
     * ¿El empleado debe escribir cuánta base se trabajó?
     */
    private Boolean requiereCantidadBaseCompartida;

    /**
     * Cantidad de base compartida trabajada durante el día.
     *
     * Ejemplo:
     * 10 libras de Masa pan base leche.
     *
     * Regla:
     * - Si requiereCantidadBaseCompartida = true, este campo es obligatorio.
     * - Si requiereCantidadBaseCompartida = false, puede ser null.
     */
    private Double cantidadBaseCompartida;

    /**
     * Identificador del usuario responsable de crear o actualizar
     * el registro del grupo.
     */
    private String responsableId;

    /**
     * Nombre visible del usuario responsable.
     */
    private String responsableNombre;

    /**
     * Timestamp de creación del registro del grupo.
     */
    private Long fechaCreacion;

    /**
     * Timestamp de última actualización del registro del grupo.
     */
    private Long fechaActualizacion;

    /**
     * Observaciones opcionales del grupo.
     *
     * Regla:
     * Si está vacío, debe guardarse como null.
     */
    private String observaciones;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */
    public GrupoProduccionDia() {
    }

    /**
     * Convierte el String estado a EstadoRegistroProduccion.
     *
     * Este método no se guarda en Firebase.
     *
     * @return EstadoRegistroProduccion correspondiente.
     */
    @Exclude
    public EstadoRegistroProduccion getEstadoEnum() {
        return EstadoRegistroProduccion.fromValue(estado);
    }

    /**
     * Asigna estado usando el enum de Java.
     *
     * Este método no se guarda en Firebase.
     *
     * @param estadoEnum estado como enum.
     */
    @Exclude
    public void setEstadoEnum(EstadoRegistroProduccion estadoEnum) {
        this.estado = estadoEnum != null
                ? estadoEnum.getValue()
                : EstadoRegistroProduccion.SIN_RESPONDER.getValue();
    }

    /**
     * Indica si el grupo está SIN_RESPONDER.
     *
     * @return true si estado = SIN_RESPONDER.
     */
    @Exclude
    public boolean estaSinResponder() {
        return getEstadoEnum() == EstadoRegistroProduccion.SIN_RESPONDER;
    }

    /**
     * Indica si el grupo está PENDIENTE.
     *
     * PENDIENTE significa que el usuario empezó a llenar información,
     * pero falta un dato obligatorio.
     *
     * @return true si estado = PENDIENTE.
     */
    @Exclude
    public boolean estaPendiente() {
        return getEstadoEnum() == EstadoRegistroProduccion.PENDIENTE;
    }

    /**
     * Indica si el grupo fue marcado como NO_HECHO.
     *
     * Si el grupo pasa a NO_HECHO, el repositorio puede hacer cascada
     * a sus subproductos, siempre que no exista producción real.
     *
     * @return true si estado = NO_HECHO.
     */
    @Exclude
    public boolean estaNoHecho() {
        return getEstadoEnum() == EstadoRegistroProduccion.NO_HECHO;
    }

    /**
     * Indica si el grupo está LISTO.
     *
     * LISTO significa que el grupo ya fue respondido correctamente.
     * No significa que todos sus subproductos estén listos.
     *
     * @return true si estado = LISTO.
     */
    @Exclude
    public boolean estaListo() {
        return getEstadoEnum() == EstadoRegistroProduccion.LISTO;
    }

    /**
     * Indica si el grupo bloquea el cierre de producción del día.
     *
     * Regla oficial:
     * - SIN_RESPONDER bloquea.
     * - PENDIENTE bloquea.
     * - NO_HECHO permite.
     * - LISTO permite.
     *
     * @return true si bloquea el cierre.
     */
    @Exclude
    public boolean bloqueaCierreDelDia() {
        return getEstadoEnum().bloqueaCierreDelDia();
    }

    /**
     * Indica si el grupo permite cerrar la producción del día.
     *
     * @return true si no bloquea cierre.
     */
    @Exclude
    public boolean puedeCerrarElDia() {
        return getEstadoEnum().permiteCerrarElDia();
    }

    /**
     * Indica si el grupo era frecuente al momento de crear el día.
     *
     * @return true si fabricacionFrecuente es true.
     */
    @Exclude
    public boolean esFrecuente() {
        return Boolean.TRUE.equals(fabricacionFrecuente);
    }

    /**
     * Indica si el grupo usa base compartida.
     *
     * @return true si usaBaseCompartida es true.
     */
    @Exclude
    public boolean tieneBaseCompartida() {
        return Boolean.TRUE.equals(usaBaseCompartida);
    }

    /**
     * Indica si el grupo exige cantidad de base compartida.
     *
     * @return true si usa base compartida y requiere cantidad.
     */
    @Exclude
    public boolean exigeCantidadBaseCompartida() {
        return Boolean.TRUE.equals(usaBaseCompartida)
                && Boolean.TRUE.equals(requiereCantidadBaseCompartida);
    }

    /**
     * Indica si la cantidad base compartida registrada es válida.
     *
     * @return true si cantidadBaseCompartida existe y es mayor que cero.
     */
    @Exclude
    public boolean tieneCantidadBaseCompartidaValida() {
        return cantidadBaseCompartida != null && cantidadBaseCompartida > 0;
    }

    /**
     * Calcula si el grupo puede pasar a LISTO al marcarlo como hecho.
     *
     * Reglas:
     * - Si no exige cantidad de base compartida, puede quedar LISTO.
     * - Si exige cantidad de base compartida, debe tener cantidad válida.
     *
     * @return true si el grupo tiene datos suficientes para estar LISTO.
     */
    @Exclude
    public boolean puedeQuedarListoComoHecho() {
        if (!exigeCantidadBaseCompartida()) {
            return true;
        }

        return tieneCantidadBaseCompartidaValida();
    }

    /**
     * Marca el grupo como NO_HECHO.
     *
     * Este método solo cambia el objeto en memoria.
     *
     * IMPORTANTE:
     * Antes de guardar este cambio, el repositorio debe validar
     * que no exista producción real en los subproductos.
     */
    @Exclude
    public void marcarNoHecho() {
        this.estado = EstadoRegistroProduccion.NO_HECHO.getValue();
        this.cantidadBaseCompartida = null;
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Marca el grupo como LISTO.
     *
     * LISTO significa que el grupo fue respondido correctamente.
     * No significa que sus subproductos estén listos.
     */
    @Exclude
    public void marcarListo() {
        this.estado = EstadoRegistroProduccion.LISTO.getValue();
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Marca el grupo como PENDIENTE.
     *
     * Se usa cuando el usuario inició el registro del grupo,
     * pero falta información obligatoria.
     */
    @Exclude
    public void marcarPendiente() {
        this.estado = EstadoRegistroProduccion.PENDIENTE.getValue();
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Calcula y asigna el estado del grupo después de marcarlo como hecho.
     *
     * Reglas:
     * - Si requiere cantidad base compartida y falta cantidad válida:
     *   estado = PENDIENTE.
     *
     * - Si no requiere cantidad base compartida:
     *   estado = LISTO.
     *
     * - Si requiere cantidad base compartida y la cantidad es válida:
     *   estado = LISTO.
     *
     * Este método solo modifica el objeto en memoria.
     * El repositorio decide cuándo guardar en Firebase.
     */
    @Exclude
    public void actualizarEstadoComoHecho() {
        if (puedeQuedarListoComoHecho()) {
            marcarListo();
        } else {
            marcarPendiente();
        }
    }

    /**
     * Normaliza las observaciones.
     *
     * Regla del proyecto:
     * observaciones debe ser null si está vacío.
     *
     * Este método solo modifica el objeto en memoria.
     */
    @Exclude
    public void normalizarObservaciones() {
        if (observaciones != null && observaciones.trim().isEmpty()) {
            observaciones = null;
        }
    }

    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getFabricacionFrecuente() {
        return fabricacionFrecuente;
    }

    public void setFabricacionFrecuente(Boolean fabricacionFrecuente) {
        this.fabricacionFrecuente = fabricacionFrecuente;
    }

    public Boolean getUsaBaseCompartida() {
        return usaBaseCompartida;
    }

    public void setUsaBaseCompartida(Boolean usaBaseCompartida) {
        this.usaBaseCompartida = usaBaseCompartida;
    }

    public String getNombreBaseCompartida() {
        return nombreBaseCompartida;
    }

    public void setNombreBaseCompartida(String nombreBaseCompartida) {
        this.nombreBaseCompartida = nombreBaseCompartida;
    }

    public String getUnidadBaseCompartida() {
        return unidadBaseCompartida;
    }

    public void setUnidadBaseCompartida(String unidadBaseCompartida) {
        this.unidadBaseCompartida = unidadBaseCompartida;
    }

    public Boolean getRequiereCantidadBaseCompartida() {
        return requiereCantidadBaseCompartida;
    }

    public void setRequiereCantidadBaseCompartida(Boolean requiereCantidadBaseCompartida) {
        this.requiereCantidadBaseCompartida = requiereCantidadBaseCompartida;
    }

    public Double getCantidadBaseCompartida() {
        return cantidadBaseCompartida;
    }

    public void setCantidadBaseCompartida(Double cantidadBaseCompartida) {
        this.cantidadBaseCompartida = cantidadBaseCompartida;
    }

    public String getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(String responsableId) {
        this.responsableId = responsableId;
    }

    public String getResponsableNombre() {
        return responsableNombre;
    }

    public void setResponsableNombre(String responsableNombre) {
        this.responsableNombre = responsableNombre;
    }

    public Long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Long fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
