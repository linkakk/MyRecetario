package com.example.mi.primer.myrecetario.model.produccion;

import com.example.mi.primer.myrecetario.model.produccion.enums.TipoCantidadProduccion;
import com.example.mi.primer.myrecetario.model.produccion.enums.TipoElementoProduccion;
import com.google.firebase.database.Exclude;

import java.util.Map;

/**
 * Modelo de configuración de producción de un producto o grupo.
 *
 * Este modelo se guarda en:
 *
 * configuracionProduccionProductos/{productoId}
 *
 * Responsabilidad:
 * Define cómo debe comportarse un producto dentro del módulo Producción.
 *
 * Este modelo lo configura el administrador.
 *
 * IMPORTANTE:
 * Esta clase NO representa producción diaria.
 * Esta clase NO representa una tanda.
 * Esta clase NO representa el cierre del día.
 *
 * Ejemplos de productos:
 * - Buñuelo
 * - Pan crema
 * - Pan leche
 *
 * Ejemplos de grupos:
 * - Pan base leche
 * - Pan al día
 *
 * Firebase guarda los enums como String:
 * - tipoElemento: "PRODUCTO" o "GRUPO"
 * - tipoCantidad: "FIJA" o "VARIABLE"
 */
public class ConfiguracionProduccionProducto {

    /**
     * Identificador único del producto o grupo.
     *
     * Debe coincidir con la clave del nodo:
     * configuracionProduccionProductos/{productoId}
     *
     * Ejemplo:
     * "bunuelo"
     * "pan_base_leche"
     */
    private String productoId;

    /**
     * Nombre visible del producto o grupo.
     *
     * Ejemplo:
     * "Buñuelo"
     * "Pan base leche"
     */
    private String nombre;

    /**
     * Identificador de la categoría.
     *
     * Ejemplo:
     * "fritos"
     * "panes"
     */
    private String categoriaId;

    /**
     * Nombre visible de la categoría.
     *
     * Ejemplo:
     * "Fritos"
     * "Panes"
     */
    private String categoriaNombre;

    /**
     * Indica si este producto o grupo aparece en el módulo Producción.
     *
     * Si es false:
     * - no aparece en producción diaria
     * - no bloquea el cierre del día
     * - no se exige respuesta
     */
    private Boolean apareceEnProduccion;

    /**
     * Indica si el producto o grupo se fabrica frecuentemente.
     *
     * Si es true:
     * - aparece en la lista diaria
     * - debe marcarse como Sí se hizo o No se hizo
     * - si queda SIN_RESPONDER, bloquea el cierre del día
     *
     * Si es false:
     * - se trata como producto ocasional
     * - no bloquea el cierre del día
     */
    private Boolean fabricacionFrecuente;

    /**
     * Define si el elemento es PRODUCTO o GRUPO.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "PRODUCTO"
     * - "GRUPO"
     */
    private String tipoElemento;

    /**
     * Define si el producto usa cantidad FIJA o VARIABLE.
     *
     * Se guarda como String para Firebase.
     *
     * Valores esperados:
     * - "FIJA"
     * - "VARIABLE"
     *
     * Para grupos puede usarse VARIABLE como referencia operativa,
     * pero el grupo no representa una tanda de producto final.
     */
    private String tipoCantidad;

    /**
     * Orden en que aparece en la pantalla diaria de producción.
     *
     * Número menor = aparece primero.
     */
    private Integer ordenProduccion;

    /**
     * Unidad en la que se cuenta el producto final.
     *
     * Ejemplos:
     * - "unidades"
     * - "bandejas"
     * - "paquetes"
     *
     * Para grupos puede ser null si el grupo no representa producto final.
     */
    private String unidadProducidaPredeterminada;

    /**
     * Cantidad esperada cuando tipoCantidad = FIJA.
     *
     * Ejemplo:
     * Buñuelo → 70 unidades.
     *
     * Si tipoCantidad = VARIABLE, este campo debe ser null.
     */
    private Double cantidadPredeterminada;

    /**
     * Indica si el empleado puede editar la cantidad predeterminada.
     *
     * Ejemplo:
     * Pan crema normalmente da 80 unidades, pero hoy salieron 75.
     */
    private Boolean permiteEditarCantidad;

    /**
     * Indica si un producto variable requiere una cantidad base propia.
     *
     * Ejemplo:
     * Pan semihojaldre:
     * - Masa semihojaldre
     * - 10 libras
     *
     * Si el producto usa base compartida del grupo, normalmente este campo
     * será false porque la base se registra en el grupo.
     */
    private Boolean requiereCantidadBase;

    /**
     * Nombre de la base propia del producto.
     *
     * Ejemplo:
     * "Masa semihojaldre"
     *
     * Este nombre lo define el administrador.
     * El empleado no selecciona la base.
     */
    private String nombreCantidadBase;

    /**
     * Unidad de la base propia del producto.
     *
     * Ejemplo:
     * "libras"
     * "gramos"
     */
    private String unidadBasePredeterminada;

    /**
     * Indica si el producto o subproducto usa la base compartida de un grupo.
     *
     * Ejemplo:
     * Grupo: Pan base leche
     * Base compartida: Masa pan base leche
     *
     * Subproductos:
     * - Pan crema
     * - Pan leche
     * - Palotes
     */
    private Boolean usaBaseCompartida;

    /**
     * Nombre de la base compartida cuando este elemento es un grupo.
     *
     * Ejemplo:
     * "Masa pan base leche"
     *
     * Aplica principalmente si tipoElemento = GRUPO.
     */
    private String nombreBaseCompartida;

    /**
     * Unidad de la base compartida cuando este elemento es un grupo.
     *
     * Ejemplo:
     * "libras"
     */
    private String unidadBaseCompartida;

    /**
     * Indica si el grupo exige que el empleado escriba la cantidad
     * de base compartida trabajada.
     *
     * Diferencia importante:
     *
     * usaBaseCompartida:
     * ¿El grupo tiene una base común?
     *
     * requiereCantidadBaseCompartida:
     * ¿El empleado debe escribir cuánta base se trabajó?
     *
     * Si es true y el empleado marca el grupo como hecho,
     * cantidadBaseCompartida será obligatoria en GrupoProduccionDia.
     */
    private Boolean requiereCantidadBaseCompartida;

    /**
     * Identificador del grupo padre si este producto pertenece a un grupo.
     *
     * Ejemplo:
     * Pan crema pertenece a pan_base_leche.
     *
     * Si este elemento es un grupo, normalmente debe ser null.
     */
    private String grupoPadreId;

    /**
     * Mapa de subproductos cuando este elemento es un grupo.
     *
     * Se usa Map y no List para que Firebase permita acceder directamente
     * por productoId:
     *
     * subproductos/{productoId}
     *
     * Cada subproducto usa SubproductoResumen enriquecido para que la pantalla
     * pueda renderizar si es fijo, variable, editable o usa base compartida.
     */
    private Map<String, SubproductoResumen> subproductos;

    /**
     * Constructor vacío requerido por Firebase Realtime Database.
     */
    public ConfiguracionProduccionProducto() {
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
     * Indica si el elemento aparece en producción.
     *
     * @return true si apareceEnProduccion es true.
     */
    @Exclude
    public boolean apareceEnProduccionActiva() {
        return Boolean.TRUE.equals(apareceEnProduccion);
    }

    /**
     * Indica si el elemento es frecuente.
     *
     * @return true si fabricacionFrecuente es true.
     */
    @Exclude
    public boolean esFrecuente() {
        return Boolean.TRUE.equals(fabricacionFrecuente);
    }

    /**
     * Indica si el elemento es ocasional.
     *
     * @return true si aparece en producción, pero no es frecuente.
     */
    @Exclude
    public boolean esOcasional() {
        return apareceEnProduccionActiva() && !esFrecuente();
    }

    /**
     * Indica si el elemento es obligatorio en el cierre del día.
     *
     * Regla:
     * Un elemento frecuente y visible en producción debe responderse.
     *
     * @return true si debe ser respondido en la producción diaria.
     */
    @Exclude
    public boolean esObligatorioEnCierre() {
        return apareceEnProduccionActiva() && esFrecuente();
    }

    /**
     * Indica si el elemento es un producto individual.
     *
     * @return true si tipoElemento = PRODUCTO.
     */
    @Exclude
    public boolean esProducto() {
        return getTipoElementoEnum().esProducto();
    }

    /**
     * Indica si el elemento es un grupo de producción.
     *
     * @return true si tipoElemento = GRUPO.
     */
    @Exclude
    public boolean esGrupo() {
        return getTipoElementoEnum().esGrupo();
    }

    /**
     * Indica si el elemento usa cantidad fija.
     *
     * @return true si tipoCantidad = FIJA.
     */
    @Exclude
    public boolean esCantidadFija() {
        return getTipoCantidadEnum().esFija();
    }

    /**
     * Indica si el elemento usa cantidad variable.
     *
     * @return true si tipoCantidad = VARIABLE.
     */
    @Exclude
    public boolean esCantidadVariable() {
        return getTipoCantidadEnum().esVariable();
    }

    /**
     * Indica si tiene cantidad predeterminada válida.
     *
     * @return true si cantidadPredeterminada existe y es mayor que cero.
     */
    @Exclude
    public boolean tieneCantidadPredeterminadaValida() {
        return cantidadPredeterminada != null && cantidadPredeterminada > 0;
    }

    /**
     * Indica si puede mostrarse con botón rápido:
     *
     * "Sí, X unidades"
     *
     * Reglas:
     * - debe ser producto
     * - debe ser cantidad fija
     * - debe tener cantidad predeterminada válida
     * - debe tener unidad producida
     *
     * @return true si puede confirmarse rápidamente.
     */
    @Exclude
    public boolean puedeConfirmarseRapido() {
        return esProducto()
                && esCantidadFija()
                && tieneCantidadPredeterminadaValida()
                && unidadProducidaPredeterminada != null
                && !unidadProducidaPredeterminada.trim().isEmpty();
    }

    /**
     * Indica si el grupo exige cantidad de base compartida.
     *
     * Aplica principalmente cuando tipoElemento = GRUPO.
     *
     * @return true si usa base compartida y requiere cantidad.
     */
    @Exclude
    public boolean requiereCantidadDeBaseCompartida() {
        return Boolean.TRUE.equals(usaBaseCompartida)
                && Boolean.TRUE.equals(requiereCantidadBaseCompartida);
    }

    /**
     * Indica si tiene subproductos configurados.
     *
     * @return true si el mapa de subproductos existe y no está vacío.
     */
    @Exclude
    public boolean tieneSubproductos() {
        return subproductos != null && !subproductos.isEmpty();
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

    public Boolean getApareceEnProduccion() {
        return apareceEnProduccion;
    }

    public void setApareceEnProduccion(Boolean apareceEnProduccion) {
        this.apareceEnProduccion = apareceEnProduccion;
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

    public Integer getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(Integer ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public String getUnidadProducidaPredeterminada() {
        return unidadProducidaPredeterminada;
    }

    public void setUnidadProducidaPredeterminada(String unidadProducidaPredeterminada) {
        this.unidadProducidaPredeterminada = unidadProducidaPredeterminada;
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

    public String getGrupoPadreId() {
        return grupoPadreId;
    }

    public void setGrupoPadreId(String grupoPadreId) {
        this.grupoPadreId = grupoPadreId;
    }

    public Map<String, SubproductoResumen> getSubproductos() {
        return subproductos;
    }

    public void setSubproductos(Map<String, SubproductoResumen> subproductos) {
        this.subproductos = subproductos;
    }
}
