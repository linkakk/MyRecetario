package com.example.mi.primer.myrecetario.model.produccion;

import com.example.mi.primer.myrecetario.model.produccion.enums.TipoCantidadProduccion;
import com.google.firebase.database.Exclude;

/**
 * Modelo resumen de un subproducto dentro de un grupo de producción.
 *
 * Este modelo se guarda dentro de:
 *
 * configuracionProduccionProductos/{grupoId}/subproductos/{productoId}
 *
 * Su función es permitir que la pantalla de producción pueda mostrar
 * los subproductos de un grupo sin hacer una consulta adicional por cada uno.
 *
 * IMPORTANTE:
 * Este modelo NO reemplaza a ConfiguracionProduccionProducto.
 * La configuración completa del producto sigue viviendo en:
 *
 * configuracionProduccionProductos/{productoId}
 *
 * Este resumen solo guarda los datos mínimos necesarios para pintar
 * y operar rápidamente el subproducto dentro de un grupo.
 */
public class SubproductoResumen {

    /**
     * Identificador único del subproducto.
     *
     * Debe coincidir con la clave del nodo:
     * configuracionProduccionProductos/{productoId}
     *
     * Ejemplo:
     * "pan_crema"
     */
    private String productoId;

    /**
     * Nombre visible del subproducto.
     *
     * Ejemplo:
     * "Pan crema"
     */
    private String nombre;

    /**
     * Unidad en la que se cuenta el producto final.
     *
     * Ejemplos:
     * - "unidades"
     * - "bandejas"
     * - "paquetes"
     *
     * Esta unidad aparece en producción al registrar la cantidad producida.
     */
    private String unidadProducidaPredeterminada;

    /**
     * Orden en que el subproducto aparece dentro del grupo.
     *
     * Número menor = aparece primero.
     *
     * Ejemplo:
     * 1 = Pan crema
     * 2 = Pan leche
     * 3 = Palotes
     */
    private Integer ordenProduccion;

    /**
     * Indica si el subproducto está activo dentro del grupo.
     *
     * Si es false, el subproducto no debería mostrarse en la pantalla diaria
     * de producción dentro de este grupo.
     */
    private Boolean activo;

    /**
     * Tipo de cantidad del subproducto.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "FIJA"
     * - "VARIABLE"
     *
     * No se guarda el enum directamente para evitar problemas de lectura
     * con Firebase Realtime Database.
     */
    private String tipoCantidad;

    /**
     * Cantidad esperada del subproducto cuando su tipoCantidad es FIJA.
     *
     * Ejemplo:
     * Pan crema → 80 unidades.
     *
     * Si tipoCantidad es VARIABLE, este campo debe ser null.
     */
    private Double cantidadPredeterminada;

    /**
     * Indica si el empleado puede editar la cantidad predeterminada
     * durante el registro de producción.
     *
     * Ejemplo:
     * Si Pan crema normalmente da 80 unidades pero hoy salieron 75,
     * permiteEditarCantidad define si se puede corregir.
     */
    private Boolean permiteEditarCantidad;

    /**
     * Indica si este subproducto requiere una cantidad base propia.
     *
     * Ejemplo:
     * Un producto variable individual podría requerir:
     * - Masa semihojaldre
     * - Harina trabajada
     *
     * Si el subproducto usa base compartida del grupo, normalmente este campo
     * será false, porque la base se registra en el grupo y no en cada subproducto.
     */
    private Boolean requiereCantidadBase;

    /**
     * Nombre de la base propia del subproducto.
     *
     * Aplica cuando requiereCantidadBase = true.
     *
     * Ejemplo:
     * "Masa pan leche"
     *
     * Si el subproducto usa base compartida del grupo,
     * normalmente este campo debe ser null.
     */
    private String nombreCantidadBase;

    /**
     * Unidad de la base propia del subproducto.
     *
     * Aplica cuando requiereCantidadBase = true.
     *
     * Ejemplo:
     * "libras"
     * "gramos"
     *
     * Si el subproducto usa base compartida del grupo,
     * normalmente este campo debe ser null.
     */
    private String unidadBasePredeterminada;


    /**
     * Indica si el subproducto usa la base compartida del grupo.
     *
     * Ejemplo:
     * Grupo: Pan base leche
     * Base compartida: Masa pan base leche
     *
     * Subproductos:
     * - Pan crema
     * - Pan leche
     * - Palotes
     *
     * Si usaBaseCompartida = true, el subproducto no debe pedir nuevamente
     * cantidadBaseTrabajada. Solo debe pedir cantidad producida si aplica.
     */
    private Boolean usaBaseCompartida;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */

    public SubproductoResumen() {
    }

    /**
     * Convierte el String tipoCantidad a TipoCantidadProduccion.
     *
     * Este método no se guarda en Firebase.
     *
     * @return TipoCantidadProduccion correspondiente al String guardado.
     */
    @Exclude
    public TipoCantidadProduccion getTipoCantidadEnum() {
        return TipoCantidadProduccion.fromValue(tipoCantidad);
    }

    /**
     * Asigna el tipoCantidad usando el enum de Java.
     *
     * Este método no se guarda en Firebase.
     *
     * @param tipoCantidadEnum tipo de cantidad como enum.
     */
    @Exclude
    public void setTipoCantidadEnum(TipoCantidadProduccion tipoCantidadEnum) {
        this.tipoCantidad = tipoCantidadEnum != null
                ? tipoCantidadEnum.getValue()
                : TipoCantidadProduccion.VARIABLE.getValue();
    }

    /**
     * Indica si el subproducto usa cantidad fija.
     *
     * @return true si tipoCantidad = FIJA.
     */
    @Exclude
    public boolean esCantidadFija() {
        return getTipoCantidadEnum().esFija();
    }

    /**
     * Indica si el subproducto usa cantidad variable.
     *
     * @return true si tipoCantidad = VARIABLE.
     */
    @Exclude
    public boolean esCantidadVariable() {
        return getTipoCantidadEnum().esVariable();
    }

    /**
     * Indica si el subproducto debe mostrarse dentro del grupo.
     *
     * @return true si activo es true.
     */
    @Exclude
    public boolean estaActivo() {
        return Boolean.TRUE.equals(activo);
    }

    /**
     * Indica si el subproducto tiene cantidad predeterminada válida.
     *
     * Aplica principalmente para productos de cantidad fija.
     *
     * @return true si cantidadPredeterminada existe y es mayor que cero.
     */
    @Exclude
    public boolean tieneCantidadPredeterminadaValida() {
        return cantidadPredeterminada != null && cantidadPredeterminada > 0;
    }

    /**
     * Indica si el subproducto puede usar el botón rápido:
     *
     * "Sí, X unidades"
     *
     * Reglas:
     * - Debe ser cantidad fija.
     * - Debe tener cantidadPredeterminada válida.
     * - Debe tener unidadProducidaPredeterminada.
     *
     * @return true si puede mostrarse como acción rápida.
     */
    @Exclude
    public boolean puedeConfirmarseRapido() {
        return esCantidadFija()
                && tieneCantidadPredeterminadaValida()
                && unidadProducidaPredeterminada != null
                && !unidadProducidaPredeterminada.trim().isEmpty();
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadProducidaPredeterminada() {
        return unidadProducidaPredeterminada;
    }

    public void setUnidadProducidaPredeterminada(String unidadProducidaPredeterminada) {
        this.unidadProducidaPredeterminada = unidadProducidaPredeterminada;
    }

    public Integer getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(Integer ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTipoCantidad() {
        return tipoCantidad;
    }

    public void setTipoCantidad(String tipoCantidad) {
        this.tipoCantidad = tipoCantidad;
    }

    public Double getCantidadPredeterminada() {
        return cantidadPredeterminada;
    }

    public void setCantidadPredeterminada(Double cantidadPredeterminada) {
        this.cantidadPredeterminada = cantidadPredeterminada;
    }

    public Boolean getPermiteEditarCantidad() {
        return permiteEditarCantidad;
    }

    public void setPermiteEditarCantidad(Boolean permiteEditarCantidad) {
        this.permiteEditarCantidad = permiteEditarCantidad;
    }

    public Boolean getRequiereCantidadBase() {
        return requiereCantidadBase;
    }

    public void setRequiereCantidadBase(Boolean requiereCantidadBase) {
        this.requiereCantidadBase = requiereCantidadBase;
    }
    public String getNombreCantidadBase() {
        return nombreCantidadBase;
    }

    public void setNombreCantidadBase(String nombreCantidadBase) {
        this.nombreCantidadBase = nombreCantidadBase;
    }

    public String getUnidadBasePredeterminada() {
        return unidadBasePredeterminada;
    }

    public void setUnidadBasePredeterminada(String unidadBasePredeterminada) {
        this.unidadBasePredeterminada = unidadBasePredeterminada;
    }

    public Boolean getUsaBaseCompartida() {
        return usaBaseCompartida;
    }

    public void setUsaBaseCompartida(Boolean usaBaseCompartida) {
        this.usaBaseCompartida = usaBaseCompartida;
    }

}
