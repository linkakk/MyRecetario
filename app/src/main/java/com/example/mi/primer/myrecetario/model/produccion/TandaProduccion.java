package com.example.mi.primer.myrecetario.model.produccion;

import com.google.firebase.database.Exclude;

/**
 * Modelo que representa una tanda individual de producción.
 *
 * Este modelo se guarda en:
 *
 * produccionDiaria/{fecha}/tandas/{tandaId}
 *
 * Responsabilidad:
 * Guardar cada evento real de producción registrado durante el día.
 *
 * Ejemplos:
 * - Buñuelo, tanda 1, 70 unidades.
 * - Buñuelo, tanda 2, 40 unidades.
 * - Pan semihojaldre, 10 libras de masa, 150 unidades.
 *
 * IMPORTANTE:
 * Esta clase NO representa el resumen diario del producto.
 * El resumen diario vive en RegistroProduccionDia.
 *
 * Esta clase NO representa configuración.
 * La configuración vive en ConfiguracionProduccionProducto.
 *
 * Regla:
 * Cada vez que se crea una tanda, el repositorio debe actualizar
 * el RegistroProduccionDia correspondiente.
 */
public class TandaProduccion {

    /**
     * Identificador único de la tanda.
     *
     * Debe coincidir con la clave pushKey generada por Firebase:
     *
     * produccionDiaria/{fecha}/tandas/{tandaId}
     *
     * Ejemplo:
     * "-OHxx_tanda1"
     */
    private String tandaId;

    /**
     * Número visual de la tanda dentro del producto y fecha.
     *
     * Ejemplo:
     * Tanda 1
     * Tanda 2
     *
     * IMPORTANTE:
     * numeroTanda es visual.
     * El orden real debe basarse en fechaCreacion.
     *
     * Esto evita errores si por concurrencia dos tandas reciben
     * el mismo numeroTanda.
     */
    private Integer numeroTanda;

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
     * Identificador del producto producido.
     *
     * Debe coincidir con:
     * configuracionProduccionProductos/{productoId}
     * produccionDiaria/{fecha}/registrosProductos/{productoId}
     */
    private String productoId;

    /**
     * Nombre visible del producto producido.
     *
     * Ejemplo:
     * "Buñuelo"
     * "Pan semihojaldre"
     */
    private String nombreProducto;

    /**
     * Identificador de categoría.
     *
     * Ejemplo:
     * "fritos"
     * "panes"
     */
    private String categoriaId;

    /**
     * Nombre visible de categoría.
     *
     * Ejemplo:
     * "Fritos"
     * "Panes"
     */
    private String categoriaNombre;

    /**
     * Cantidad producida en esta tanda.
     *
     * Ejemplo:
     * Buñuelo → 70 unidades.
     * Pan leche → 150 unidades.
     *
     * Debe ser mayor que cero para considerarse una tanda válida.
     */
    private Double cantidadProducida;

    /**
     * Unidad en la que se mide la cantidad producida.
     *
     * Ejemplos:
     * - "unidades"
     * - "bandejas"
     * - "paquetes"
     */
    private String unidadProducida;

    /**
     * Cantidad de base trabajada para esta tanda.
     *
     * Aplica cuando el producto tiene base propia.
     *
     * Ejemplo:
     * Pan semihojaldre:
     * cantidadBaseTrabajada = 10
     * unidadBase = "libras"
     *
     * Si el producto es de cantidad fija o usa base compartida de grupo,
     * este campo normalmente será null.
     */
    private Double cantidadBaseTrabajada;

    /**
     * Nombre de la base usada en la tanda.
     *
     * Ejemplo:
     * "Masa semihojaldre"
     *
     * Este nombre viene desde la configuración del administrador.
     * El empleado no debe escribir ni seleccionar este nombre.
     */
    private String nombreCantidadBase;

    /**
     * Unidad de la base usada en la tanda.
     *
     * Ejemplo:
     * "libras"
     * "gramos"
     *
     * Si no aplica base propia, debe ser null.
     */
    private String unidadBase;

    /**
     * Identificador del usuario responsable de registrar la tanda.
     */
    private String responsableId;

    /**
     * Nombre visible del usuario responsable de registrar la tanda.
     */
    private String responsableNombre;

    /**
     * Timestamp de creación de la tanda.
     *
     * Este campo es la fuente real para ordenar las tandas.
     */
    private Long fechaCreacion;

    /**
     * Timestamp de última actualización de la tanda.
     *
     * Normalmente será igual a fechaCreacion al crear.
     * Cambia si un administrador corrige la tanda.
     */
    private Long fechaActualizacion;

    /**
     * Observaciones opcionales de la tanda.
     *
     * Regla:
     * Si está vacío, debe guardarse como null.
     */
    private String observaciones;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */
    public TandaProduccion() {
    }

    /**
     * Indica si la tanda tiene una cantidad producida válida.
     *
     * @return true si cantidadProducida existe y es mayor que cero.
     */
    @Exclude
    public boolean tieneCantidadProducidaValida() {
        return cantidadProducida != null && cantidadProducida > 0;
    }

    /**
     * Indica si la tanda tiene unidad producida definida.
     *
     * @return true si unidadProducida no está vacía.
     */
    @Exclude
    public boolean tieneUnidadProducidaValida() {
        return unidadProducida != null && !unidadProducida.trim().isEmpty();
    }

    /**
     * Indica si esta tanda tiene base propia registrada.
     *
     * Esto aplica para productos variables que requieren cantidad base propia.
     *
     * @return true si cantidadBaseTrabajada existe y es mayor que cero.
     */
    @Exclude
    public boolean tieneBaseTrabajada() {
        return cantidadBaseTrabajada != null && cantidadBaseTrabajada > 0;
    }

    /**
     * Indica si la tanda tiene los datos mínimos para ser guardada
     * como producción válida.
     *
     * Regla mínima:
     * - cantidadProducida > 0
     * - unidadProducida no vacía
     *
     * La validación de cantidad base obligatoria se hará en el repositorio,
     * porque depende de la configuración del producto.
     *
     * @return true si la tanda tiene los datos mínimos.
     */
    @Exclude
    public boolean esTandaValidaBasica() {
        return tieneCantidadProducidaValida() && tieneUnidadProducidaValida();
    }

    /**
     * Indica si esta tanda tiene observaciones reales.
     *
     * @return true si observaciones no es null ni vacío.
     */
    @Exclude
    public boolean tieneObservaciones() {
        return observaciones != null && !observaciones.trim().isEmpty();
    }

    /**
     * Normaliza las observaciones.
     *
     * Regla del proyecto:
     * observaciones debe ser null si está vacío.
     *
     * Este método solo modifica el objeto en memoria.
     * El repositorio decide cuándo guardar.
     */
    @Exclude
    public void normalizarObservaciones() {
        if (observaciones != null && observaciones.trim().isEmpty()) {
            observaciones = null;
        }
    }

    public String getTandaId() {
        return tandaId;
    }

    public void setTandaId(String tandaId) {
        this.tandaId = tandaId;
    }

    public Integer getNumeroTanda() {
        return numeroTanda;
    }

    public void setNumeroTanda(Integer numeroTanda) {
        this.numeroTanda = numeroTanda;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Double getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(Double cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public String getUnidadProducida() {
        return unidadProducida;
    }

    public void setUnidadProducida(String unidadProducida) {
        this.unidadProducida = unidadProducida;
    }

    public Double getCantidadBaseTrabajada() {
        return cantidadBaseTrabajada;
    }

    public void setCantidadBaseTrabajada(Double cantidadBaseTrabajada) {
        this.cantidadBaseTrabajada = cantidadBaseTrabajada;
    }

    public String getNombreCantidadBase() {
        return nombreCantidadBase;
    }

    public void setNombreCantidadBase(String nombreCantidadBase) {
        this.nombreCantidadBase = nombreCantidadBase;
    }

    public String getUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(String unidadBase) {
        this.unidadBase = unidadBase;
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
