package com.example.mi.primer.myrecetario.model.produccion;

import com.example.mi.primer.myrecetario.model.produccion.enums.EstadoRegistroProduccion;
import com.example.mi.primer.myrecetario.model.produccion.enums.TipoCantidadProduccion;
import com.example.mi.primer.myrecetario.model.produccion.enums.TipoElementoProduccion;
import com.google.firebase.database.Exclude;

/**
 * Modelo que representa el resumen diario de producción de un producto o subproducto.
 *
 * Este modelo se guarda en:
 *
 * produccionDiaria/{fecha}/registrosProductos/{productoId}
 *
 * Responsabilidad:
 * Guardar el estado diario de un producto dentro de una fecha específica.
 *
 * Este modelo resume:
 * - estado del producto en el día
 * - total producido acumulado
 * - cantidad de tandas
 * - cierre individual del producto si aplica
 *
 * IMPORTANTE:
 * Esta clase NO representa una tanda individual.
 * Las tandas se guardan en TandaProduccion.
 *
 * Esta clase NO representa la configuración del producto.
 * La configuración vive en ConfiguracionProduccionProducto.
 */
public class RegistroProduccionDia {

    /**
     * Identificador del producto.
     *
     * También es la clave del nodo:
     * produccionDiaria/{fecha}/registrosProductos/{productoId}
     *
     * No usamos registroId porque duplicaría productoId.
     */
    private String productoId;

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
     * Nombre visible del producto.
     *
     * Ejemplo:
     * "Buñuelo"
     * "Pan crema"
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
     * Estado del producto en el día.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "SIN_RESPONDER"
     * - "PENDIENTE"
     * - "NO_HECHO"
     * - "LISTO"
     * - "CERRADO"
     */
    private String estado;

    /**
     * Indica si el producto era frecuente al momento de crear el registro del día.
     *
     * Se copia desde ConfiguracionProduccionProducto para conservar histórico.
     */
    private Boolean fabricacionFrecuente;

    /**
     * Tipo de elemento copiado desde la configuración.
     *
     * Valores:
     * - "PRODUCTO"
     * - "GRUPO"
     *
     * En registrosProductos normalmente se registran productos y subproductos.
     */
    private String tipoElemento;

    /**
     * Tipo de cantidad copiado desde la configuración.
     *
     * Valores:
     * - "FIJA"
     * - "VARIABLE"
     */
    private String tipoCantidad;

    /**
     * Total producido acumulado del producto en el día.
     *
     * Regla por estado:
     * - SIN_RESPONDER → null
     * - PENDIENTE → null
     * - NO_HECHO → 0
     * - LISTO → > 0
     * - CERRADO → >= 0
     */
    private Double totalProducido;

    /**
     * Unidad en la que se mide el producto final.
     *
     * Ejemplo:
     * "unidades"
     * "bandejas"
     */
    private String unidadProducida;

    /**
     * Cantidad de tandas registradas para este producto en el día.
     *
     * El repositorio actualiza este valor.
     * No lo calcula la pantalla.
     */
    private Integer cantidadTandas;

    /**
     * Cantidad esperada copiada desde la configuración.
     *
     * Aplica principalmente para productos de cantidad fija.
     */
    private Double cantidadPredeterminada;

    /**
     * Indica si la cantidad fija fue editada por el usuario.
     *
     * Ejemplo:
     * cantidad predeterminada = 80
     * cantidad real = 75
     * cantidadEditada = true
     */
    private Boolean cantidadEditada;

    /**
     * Cantidad sobrante registrada en el cierre individual del producto.
     *
     * Regla:
     * - null mientras estado != CERRADO
     * - valor real cuando estado = CERRADO
     *
     * Puede ser 0 solo si el cierre individual realmente se hizo.
     */
    private Double sobrante;

    /**
     * Cantidad de merma registrada en el cierre individual del producto.
     *
     * Regla:
     * - null mientras estado != CERRADO
     * - valor real cuando estado = CERRADO
     *
     * Puede ser 0 solo si el cierre individual realmente se hizo.
     */
    private Double merma;

    /**
     * Venta estimada del producto.
     *
     * Fórmula:
     * vendidoEstimado = totalProducido - sobrante - merma
     *
     * Regla:
     * - null mientras estado != CERRADO
     * - valor calculado cuando estado = CERRADO
     */
    private Double vendidoEstimado;

    /**
     * Timestamp del cierre individual del producto.
     *
     * Regla:
     * - null mientras estado != CERRADO
     * - timestamp real cuando se ejecuta cerrarProducto()
     */
    private Long fechaCierre;

    /**
     * Identificador del grupo padre si el producto pertenece a un grupo.
     *
     * Ejemplo:
     * Pan crema pertenece a pan_base_leche.
     *
     * Si es producto independiente, debe ser null.
     */
    private String grupoPadreId;

    /**
     * Indica si este producto usa base compartida del grupo.
     *
     * Si es true, el producto no debe pedir cantidad base propia.
     */
    private Boolean usaBaseCompartida;

    /**
     * Identificador del usuario que creó o actualizó el registro.
     */
    private String responsableId;

    /**
     * Nombre visible del usuario que creó o actualizó el registro.
     */
    private String responsableNombre;

    /**
     * Timestamp de creación del registro.
     */
    private Long fechaCreacion;

    /**
     * Timestamp de última actualización del registro.
     */
    private Long fechaActualizacion;

    /**
     * Observaciones opcionales.
     *
     * Regla:
     * Si está vacío, debe guardarse como null.
     */
    private String observaciones;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */
    public RegistroProduccionDia() {
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
     * Convierte el String tipoElemento a TipoElementoProduccion.
     *
     * Este método no se guarda en Firebase.
     *
     * @return TipoElementoProduccion correspondiente.
     */
    @Exclude
    public TipoElementoProduccion getTipoElementoEnum() {
        return TipoElementoProduccion.fromValue(tipoElemento);
    }

    /**
     * Asigna tipoElemento usando el enum de Java.
     *
     * Este método no se guarda en Firebase.
     *
     * @param tipoElementoEnum tipo de elemento como enum.
     */
    @Exclude
    public void setTipoElementoEnum(TipoElementoProduccion tipoElementoEnum) {
        this.tipoElemento = tipoElementoEnum != null
                ? tipoElementoEnum.getValue()
                : TipoElementoProduccion.PRODUCTO.getValue();
    }

    /**
     * Convierte el String tipoCantidad a TipoCantidadProduccion.
     *
     * Este método no se guarda en Firebase.
     *
     * @return TipoCantidadProduccion correspondiente.
     */
    @Exclude
    public TipoCantidadProduccion getTipoCantidadEnum() {
        return TipoCantidadProduccion.fromValue(tipoCantidad);
    }

    /**
     * Asigna tipoCantidad usando el enum de Java.
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
     * Indica si el registro está SIN_RESPONDER.
     *
     * @return true si estado = SIN_RESPONDER.
     */
    @Exclude
    public boolean estaSinResponder() {
        return getEstadoEnum() == EstadoRegistroProduccion.SIN_RESPONDER;
    }

    /**
     * Indica si el registro está PENDIENTE.
     *
     * @return true si estado = PENDIENTE.
     */
    @Exclude
    public boolean estaPendiente() {
        return getEstadoEnum() == EstadoRegistroProduccion.PENDIENTE;
    }

    /**
     * Indica si el producto fue marcado como NO_HECHO.
     *
     * @return true si estado = NO_HECHO.
     */
    @Exclude
    public boolean estaNoHecho() {
        return getEstadoEnum() == EstadoRegistroProduccion.NO_HECHO;
    }

    /**
     * Indica si el producto está LISTO.
     *
     * LISTO significa respondido correctamente, no cierre individual.
     *
     * @return true si estado = LISTO.
     */
    @Exclude
    public boolean estaListo() {
        return getEstadoEnum() == EstadoRegistroProduccion.LISTO;
    }

    /**
     * Indica si el producto tiene cierre individual.
     *
     * @return true si estado = CERRADO.
     */
    @Exclude
    public boolean estaCerrado() {
        return getEstadoEnum() == EstadoRegistroProduccion.CERRADO;
    }

    /**
     * Indica si este registro bloquea el cierre de producción del día.
     *
     * Regla oficial:
     * - SIN_RESPONDER bloquea.
     * - PENDIENTE bloquea.
     * - NO_HECHO permite.
     * - LISTO permite.
     * - CERRADO permite.
     *
     * @return true si bloquea el cierre.
     */
    @Exclude
    public boolean bloqueaCierreDelDia() {
        return getEstadoEnum().bloqueaCierreDelDia();
    }

    /**
     * Indica si el estado permite cerrar la producción del día.
     *
     * @return true si no bloquea cierre.
     */
    @Exclude
    public boolean puedeCerrarElDia() {
        return getEstadoEnum().permiteCerrarElDia();
    }

    /**
     * Indica si este registro pertenece a un subproducto dentro de un grupo.
     *
     * @return true si grupoPadreId tiene valor.
     */
    @Exclude
    public boolean perteneceAGrupo() {
        return grupoPadreId != null && !grupoPadreId.trim().isEmpty();
    }

    /**
     * Indica si el registro corresponde a un producto frecuente.
     *
     * @return true si fabricacionFrecuente es true.
     */
    @Exclude
    public boolean esFrecuente() {
        return Boolean.TRUE.equals(fabricacionFrecuente);
    }

    /**
     * Indica si tiene producción real registrada.
     *
     * Se usa para proteger trazabilidad.
     *
     * @return true si hay tandas o totalProducido mayor que cero.
     */
    @Exclude
    public boolean tieneProduccionReal() {
        return (cantidadTandas != null && cantidadTandas > 0)
                || (totalProducido != null && totalProducido > 0);
    }

    /**
     * Marca el grupo como NO_HECHO.
     *
     * Este método solo cambia el objeto en memoria.
     *
     * IMPORTANTE:
     * El repositorio NO debe permitir guardar este estado si existe
     * producción real en los subproductos.
     *
     * Producción real significa:
     * - subproductos con tandas registradas
     * - subproductos con totalProducido > 0
     * - subproductos en estado LISTO o CERRADO
     *
     * Si no existe producción real, el repositorio puede hacer cascada
     * y marcar los subproductos como NO_HECHO.
     */
    @Exclude
    public void marcarNoHecho() {
        this.estado = EstadoRegistroProduccion.NO_HECHO.getValue();
        this.totalProducido = 0.0;
        this.cantidadTandas = 0;
        limpiarCierreIndividual();
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Marca el producto como LISTO.
     *
     * LISTO significa que el producto fue respondido correctamente.
     *
     * Este método limpia el cierre individual porque LISTO no representa
     * sobrante, merma ni vendido estimado.
     */
    @Exclude
    public void marcarListo() {
        this.estado = EstadoRegistroProduccion.LISTO.getValue();
        limpiarCierreIndividual();
        this.fechaActualizacion = System.currentTimeMillis();
    }

    /**
     * Limpia los campos del cierre individual.
     *
     * Se usa cuando:
     * - el producto está LISTO
     * - el producto vuelve de CERRADO a LISTO por una nueva tanda
     * - el producto se marca NO_HECHO
     *
     * Este método no se guarda en Firebase.
     */
    @Exclude
    public void limpiarCierreIndividual() {
        this.sobrante = null;
        this.merma = null;
        this.vendidoEstimado = null;
        this.fechaCierre = null;
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

    /**
     * Cierra individualmente el producto.
     *
     * Este es el único método del modelo que debe asignar:
     * - sobrante
     * - merma
     * - vendidoEstimado
     * - fechaCierre
     *
     * Validaciones:
     * - sobrante no puede ser negativo.
     * - merma no puede ser negativa.
     * - sobrante + merma no puede superar totalProducido.
     *
     * Este método solo cambia el objeto en memoria.
     * El repositorio es responsable de persistir en Firebase.
     *
     * @param sobrante cantidad sobrante real.
     * @param merma cantidad perdida o dañada.
     */
    @Exclude
    public void cerrarProducto(double sobrante, double merma) {
        if (sobrante < 0 || merma < 0) {
            throw new IllegalArgumentException("Sobrante y merma no pueden ser negativos.");
        }

        double total = totalProducido != null ? totalProducido : 0.0;

        if (sobrante + merma > total) {
            throw new IllegalArgumentException("Sobrante + merma no puede superar el total producido.");
        }

        double vendido = total - sobrante - merma;

        this.sobrante = sobrante;
        this.merma = merma;
        this.vendidoEstimado = vendido;
        this.estado = EstadoRegistroProduccion.CERRADO.getValue();
        this.fechaCierre = System.currentTimeMillis();
        this.fechaActualizacion = System.currentTimeMillis();
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public String getTipoCantidad() {
        return tipoCantidad;
    }

    public void setTipoCantidad(String tipoCantidad) {
        this.tipoCantidad = tipoCantidad;
    }

    public Double getTotalProducido() {
        return totalProducido;
    }

    public void setTotalProducido(Double totalProducido) {
        this.totalProducido = totalProducido;
    }

    public String getUnidadProducida() {
        return unidadProducida;
    }

    public void setUnidadProducida(String unidadProducida) {
        this.unidadProducida = unidadProducida;
    }

    public Integer getCantidadTandas() {
        return cantidadTandas;
    }

    public void setCantidadTandas(Integer cantidadTandas) {
        this.cantidadTandas = cantidadTandas;
    }

    public Double getCantidadPredeterminada() {
        return cantidadPredeterminada;
    }

    public void setCantidadPredeterminada(Double cantidadPredeterminada) {
        this.cantidadPredeterminada = cantidadPredeterminada;
    }

    public Boolean getCantidadEditada() {
        return cantidadEditada;
    }

    public void setCantidadEditada(Boolean cantidadEditada) {
        this.cantidadEditada = cantidadEditada;
    }

    public Double getSobrante() {
        return sobrante;
    }

    public void setSobrante(Double sobrante) {
        this.sobrante = sobrante;
    }

    public Double getMerma() {
        return merma;
    }

    public void setMerma(Double merma) {
        this.merma = merma;
    }

    public Double getVendidoEstimado() {
        return vendidoEstimado;
    }

    public void setVendidoEstimado(Double vendidoEstimado) {
        this.vendidoEstimado = vendidoEstimado;
    }

    public Long getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Long fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getGrupoPadreId() {
        return grupoPadreId;
    }

    public void setGrupoPadreId(String grupoPadreId) {
        this.grupoPadreId = grupoPadreId;
    }

    public Boolean getUsaBaseCompartida() {
        return usaBaseCompartida;
    }

    public void setUsaBaseCompartida(Boolean usaBaseCompartida) {
        this.usaBaseCompartida = usaBaseCompartida;
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
