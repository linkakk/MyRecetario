package com.example.mi.primer.myrecetario.model.produccion;

import com.example.mi.primer.myrecetario.model.produccion.enums.EstadoCierreProduccion;
import com.google.firebase.database.Exclude;

/**
 * Modelo que representa el cierre general de producción del día.
 *
 * Este modelo se guarda en:
 *
 * produccionDiaria/{fecha}/cierreDia
 *
 * Responsabilidad:
 * Guardar el estado general del cierre diario de producción.
 *
 * IMPORTANTE:
 * Esta clase NO representa el estado individual de un producto.
 * Para productos, subproductos y grupos se usa:
 * - RegistroProduccionDia
 * - GrupoProduccionDia
 *
 * Esta clase resume:
 * - si el día está abierto, bloqueado o cerrado
 * - cuántos productos frecuentes fueron respondidos
 * - cuántos siguen pendientes o sin responder
 * - quién cerró la producción del día
 * - cuándo se cerró
 */
public class CierreProduccionDia {

    /**
     * Fecha del cierre de producción.
     *
     * Formato recomendado:
     * yyyy-MM-dd
     *
     * Ejemplo:
     * "2026-05-03"
     */
    private String fecha;

    /**
     * Estado general del cierre del día.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "ABIERTO"
     * - "BLOQUEADO"
     * - "CERRADO"
     */
    private String estadoCierre;

    /**
     * Total de productos, grupos o subproductos frecuentes
     * que deben ser respondidos en el día.
     *
     * Este valor lo calcula el repositorio.
     */
    private Integer totalProductosFrecuentes;

    /**
     * Cantidad de productos frecuentes que ya fueron respondidos.
     *
     * Estados considerados respondidos:
     * - NO_HECHO
     * - LISTO
     * - CERRADO
     */
    private Integer productosFrecuentesRespondidos;

    /**
     * Cantidad de productos frecuentes que siguen sin responder.
     *
     * Normalmente incluye:
     * - SIN_RESPONDER
     */
    private Integer productosFrecuentesSinResponder;

    /**
     * Total de productos que sí tuvieron producción registrada.
     *
     * Normalmente cuenta registros en:
     * - LISTO
     * - CERRADO
     */
    private Integer totalProductosHechos;

    /**
     * Total de productos marcados como NO_HECHO.
     */
    private Integer totalProductosNoHechos;

    /**
     * Total de productos, grupos o subproductos que están pendientes.
     *
     * Normalmente incluye:
     * - PENDIENTE
     *
     * Puede usarse junto con productosFrecuentesSinResponder para saber
     * por qué el cierre del día está bloqueado.
     */
    private Integer totalProductosPendientes;

    /**
     * Total de productos ocasionales agregados durante el día.
     *
     * Los productos ocasionales no bloquean el cierre del día.
     */
    private Integer totalProductosOcasionalesAgregados;

    /**
     * Identificador del usuario que cerró la producción del día.
     *
     * Debe ser null mientras el estadoCierre no sea CERRADO.
     */
    private String cerradoPorId;

    /**
     * Nombre visible del usuario que cerró la producción del día.
     *
     * Debe ser null mientras el estadoCierre no sea CERRADO.
     */
    private String cerradoPorNombre;

    /**
     * Timestamp del cierre general del día.
     *
     * Regla:
     * - null mientras estadoCierre != CERRADO
     * - timestamp real cuando se ejecuta cerrarProduccionDelDia()
     */
    private Long fechaCierre;

    /**
     * Timestamp de última actualización del cierre del día.
     */
    private Long fechaActualizacion;

    /**
     * Observaciones opcionales del cierre general.
     *
     * Regla:
     * Si está vacío, debe guardarse como null.
     */
    private String observaciones;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */
    public CierreProduccionDia() {
    }

    /**
     * Convierte el String estadoCierre a EstadoCierreProduccion.
     *
     * Este método no se guarda en Firebase.
     *
     * @return EstadoCierreProduccion correspondiente.
     */
    @Exclude
    public EstadoCierreProduccion getEstadoCierreEnum() {
        return EstadoCierreProduccion.fromValue(estadoCierre);
    }

    /**
     * Asigna estadoCierre usando el enum de Java.
     *
     * Este método no se guarda en Firebase.
     *
     * @param estadoCierreEnum estado de cierre como enum.
     */
    @Exclude
    public void setEstadoCierreEnum(EstadoCierreProduccion estadoCierreEnum) {
        this.estadoCierre = estadoCierreEnum != null
                ? estadoCierreEnum.getValue()
                : EstadoCierreProduccion.ABIERTO.getValue();
    }

    /**
     * Indica si el cierre del día está ABIERTO.
     *
     * @return true si estadoCierre = ABIERTO.
     */
    @Exclude
    public boolean estaAbierto() {
        return getEstadoCierreEnum() == EstadoCierreProduccion.ABIERTO;
    }

    /**
     * Indica si el cierre del día está BLOQUEADO.
     *
     * BLOQUEADO significa que se intentó cerrar, pero todavía existen
     * productos, grupos o subproductos en estados que bloquean el cierre.
     *
     * @return true si estadoCierre = BLOQUEADO.
     */
    @Exclude
    public boolean estaBloqueado() {
        return getEstadoCierreEnum() == EstadoCierreProduccion.BLOQUEADO;
    }

    /**
     * Indica si el cierre del día está CERRADO.
     *
     * @return true si estadoCierre = CERRADO.
     */
    @Exclude
    public boolean estaCerrado() {
        return getEstadoCierreEnum() == EstadoCierreProduccion.CERRADO;
    }

    /**
     * Indica si todavía se pueden modificar registros de producción.
     *
     * En el MVP:
     * - ABIERTO permite modificaciones.
     * - BLOQUEADO permite correcciones.
     * - CERRADO no debería permitir modificaciones normales.
     *
     * @return true si se permiten modificaciones.
     */
    @Exclude
    public boolean permiteModificaciones() {
        return getEstadoCierreEnum().permiteModificaciones();
    }

    /**
     * Indica si hay productos sin responder.
     *
     * @return true si productosFrecuentesSinResponder > 0.
     */
    @Exclude
    public boolean tieneProductosSinResponder() {
        return productosFrecuentesSinResponder != null
                && productosFrecuentesSinResponder > 0;
    }

    /**
     * Indica si hay productos pendientes.
     *
     * @return true si totalProductosPendientes > 0.
     */
    @Exclude
    public boolean tieneProductosPendientes() {
        return totalProductosPendientes != null
                && totalProductosPendientes > 0;
    }

    /**
     * Indica si el cierre tiene bloqueantes.
     *
     * Un cierre tiene bloqueantes si existen:
     * - productos frecuentes sin responder
     * - productos, grupos o subproductos pendientes
     *
     * @return true si hay datos que impiden cerrar el día.
     */
    @Exclude
    public boolean tieneBloqueantes() {
        return tieneProductosSinResponder() || tieneProductosPendientes();
    }

    /**
     * Marca el cierre como ABIERTO.
     *
     * Se usa al iniciar o reabrir el flujo de producción diaria.
     *
     * Este método solo modifica el objeto en memoria.
     * El repositorio decide cuándo guardar en Firebase.
     */
    @Exclude
    public void marcarAbierto() {
        this.estadoCierre = EstadoCierreProduccion.ABIERTO.getValue();
        this.fechaCierre = null;
        this.cerradoPorId = null;
        this.cerradoPorNombre = null;
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Marca el cierre como BLOQUEADO.
     *
     * Se usa cuando el usuario intenta cerrar el día,
     * pero existen elementos bloqueantes.
     *
     * Este método solo modifica el objeto en memoria.
     */
    @Exclude
    public void marcarBloqueado() {
        this.estadoCierre = EstadoCierreProduccion.BLOQUEADO.getValue();
        this.fechaCierre = null;
        this.cerradoPorId = null;
        this.cerradoPorNombre = null;
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Marca el cierre como CERRADO.
     *
     * Este método debe usarse solo después de validar que no existan
     * productos, grupos o subproductos bloqueantes.
     *
     * @param usuarioId identificador del usuario que cierra el día.
     * @param usuarioNombre nombre visible del usuario que cierra el día.
     */
    @Exclude
    public void marcarCerrado(String usuarioId, String usuarioNombre) {
        this.estadoCierre = EstadoCierreProduccion.CERRADO.getValue();
        this.cerradoPorId = usuarioId;
        this.cerradoPorNombre = usuarioNombre;
        this.fechaCierre = System.currentTimeMillis();
        this.fechaActualizacion = System.currentTimeMillis();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoCierre() {
        return estadoCierre;
    }

    public void setEstadoCierre(String estadoCierre) {
        this.estadoCierre = estadoCierre;
    }

    public Integer getTotalProductosFrecuentes() {
        return totalProductosFrecuentes;
    }

    public void setTotalProductosFrecuentes(Integer totalProductosFrecuentes) {
        this.totalProductosFrecuentes = totalProductosFrecuentes;
    }

    public Integer getProductosFrecuentesRespondidos() {
        return productosFrecuentesRespondidos;
    }

    public void setProductosFrecuentesRespondidos(Integer productosFrecuentesRespondidos) {
        this.productosFrecuentesRespondidos = productosFrecuentesRespondidos;
    }

    public Integer getProductosFrecuentesSinResponder() {
        return productosFrecuentesSinResponder;
    }

    public void setProductosFrecuentesSinResponder(Integer productosFrecuentesSinResponder) {
        this.productosFrecuentesSinResponder = productosFrecuentesSinResponder;
    }

    public Integer getTotalProductosHechos() {
        return totalProductosHechos;
    }

    public void setTotalProductosHechos(Integer totalProductosHechos) {
        this.totalProductosHechos = totalProductosHechos;
    }

    public Integer getTotalProductosNoHechos() {
        return totalProductosNoHechos;
    }

    public void setTotalProductosNoHechos(Integer totalProductosNoHechos) {
        this.totalProductosNoHechos = totalProductosNoHechos;
    }

    public Integer getTotalProductosPendientes() {
        return totalProductosPendientes;
    }

    public void setTotalProductosPendientes(Integer totalProductosPendientes) {
        this.totalProductosPendientes = totalProductosPendientes;
    }

    public Integer getTotalProductosOcasionalesAgregados() {
        return totalProductosOcasionalesAgregados;
    }

    public void setTotalProductosOcasionalesAgregados(Integer totalProductosOcasionalesAgregados) {
        this.totalProductosOcasionalesAgregados = totalProductosOcasionalesAgregados;
    }

    public String getCerradoPorId() {
        return cerradoPorId;
    }

    public void setCerradoPorId(String cerradoPorId) {
        this.cerradoPorId = cerradoPorId;
    }

    public String getCerradoPorNombre() {
        return cerradoPorNombre;
    }

    public void setCerradoPorNombre(String cerradoPorNombre) {
        this.cerradoPorNombre = cerradoPorNombre;
    }

    public Long getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Long fechaCierre) {
        this.fechaCierre = fechaCierre;
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
