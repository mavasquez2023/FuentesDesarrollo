package cl.araucana.tesoreria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class Comprobante implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	//Tipo de Movimiento
	public static final String TPO_MOVI_INGRESO="I";	
	public static final String TPO_MOVI_EGRESO="E";
	public static final String TPO_MOVI_DEVOLUCION="D";
	//Estado de Movimiento de Caja
	public static final String STD_MOV_CAJA_GENERADO="G";
	public static final String STD_MOV_CAJA_IMPRESO="I";
	public static final String STD_MOV_CAJA_CURSADO="C";
	public static final String STD_MOV_CAJA_ANULADO="A";
	//Forma de Pago
	public static final String FORMA_PAGO_CAJA="C";
	public static final String FORMA_PAGO_DEPOSITO="D";
	//Estado de Autorización
	public static final String STD_AUTORIZACION_AUTORIZADO="A";
	//Tipo de Pago
	public static final String TIPO_PAGO_EFECTIVO="E";
	public static final String TIPO_PAGO_CHEQUE="C";
	//Emite Factura
	public static final String EMITE_FACTURA_SI="S";
	public static final String EMITE_FACTURA_NO="N";
	
	private long folioMovimiento=0;
	private String tipoMovimiento="";
	private String estadoMovimientoCaja="";
	private Date fechaEmision=null;
	private Date horaEmision=null;
	private Date fechaRecaudacion=null;
	private Date horaRecaudacion=null;
	private String formaPago="";
	private long rut1=0;
	private String dv1="";
	private String nombreRut1="";
	private long rut2=0;
	private String dv2="";
	private String nombreRut2="";
	private String codigoBarra="";
	private int montoInformado=0;
	private int montoInteres=0;
	private int montoReajuste=0;
	private int montoEmitido=0;
	private String observaciónMovimientoCaja="";
	private int sucursal=0;
	private String estadoAutorizacion="";
	private String tipoPago="";
	private Date fechaDisponibilidad=null;
	private String emiteFactura="";
	private int codigoOficina=0;
	private int correlativoPago=0;
	private int codigoAreaNegocio=0;
	private int codigoCajero=0;
	private Date fechaApertura=null;
	private int sesion=0;
	private int serPagadoPorCodigoOficina=0;
	private Date fechaCreacion=null;
	private Date horaCreacion=null;
	private Date fechaCambio=null;
	private Date horaCambio=null;
	private String usuarioCreoRegistro="";
	private String ultimoUsuarioModifico="";
	private ArrayList datosComunes = new ArrayList();
	private ArrayList lineasDetalle = new ArrayList();  

	/**
	 * Agrega un Dato Común
	 */
	public void addDatosComunes(Detalle datComun) {
		if (datComun == null) {
			datosComunes = new ArrayList();
		}
		datosComunes.add(datComun);
	}
	
	/**
	 * Elimina un Dato Común
	 */
	public void removeDatoComun(int index) {
		if (datosComunes.size() > 0 && index < datosComunes.size())
		datosComunes.remove(index);
	}
	
	/**
	 * Remueve todos los datos comunes
	 *
	 */
	public void removeAllDatosCoomunes() {
		datosComunes.clear();
	}

	/**
	 * Agrega una Línea Dealle
	 */
	public void addLineaDetalle(Detalle linDetalle) {
		if (linDetalle == null) {
			lineasDetalle = new ArrayList();
		}
		lineasDetalle.add(linDetalle);
	}
	
	/**
	 * Elimina una Línea Detalle
	 */
	public void removeLineaDetalle(int index) {
		if (lineasDetalle.size() > 0 && index < lineasDetalle.size())
		lineasDetalle.remove(index);
	}
	
	/**
	 * Remueve todas las líneas de detalle
	 *
	 */
	public void removeAllLineasDetalle() {
		lineasDetalle.clear();
	}



	/**
	 * @return
	 */
	public int getCodigoAreaNegocio() {
		return codigoAreaNegocio;
	}

	/**
	 * @return
	 */
	public String getCodigoBarra() {
		return codigoBarra;
	}

	/**
	 * @return
	 */
	public int getCodigoCajero() {
		return codigoCajero;
	}

	/**
	 * @return
	 */
	public int getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public int getCorrelativoPago() {
		return correlativoPago;
	}

	/**
	 * @return
	 */
	public ArrayList getDatosComunes() {
		return datosComunes;
	}

	/**
	 * @return
	 */
	public String getDv1() {
		return dv1;
	}

	/**
	 * @return
	 */
	public String getDv2() {
		return dv2;
	}

	/**
	 * @return
	 */
	public String getEmiteFactura() {
		return emiteFactura;
	}

	/**
	 * @return
	 */
	public String getEstadoAutorizacion() {
		return estadoAutorizacion;
	}

	/**
	 * @return
	 */
	public String getEstadoMovimientoCaja() {
		return estadoMovimientoCaja;
	}

	/**
	 * @return
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * @return
	 */
	public Date getFechaCambio() {
		return fechaCambio;
	}

	/**
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @return
	 */
	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}

	/**
	 * @return
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @return
	 */
	public Date getFechaRecaudacion() {
		return fechaRecaudacion;
	}

	/**
	 * @return
	 */
	public long getFolioMovimiento() {
		return folioMovimiento;
	}

	/**
	 * @return
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * @return
	 */
	public Date getHoraCambio() {
		return horaCambio;
	}

	/**
	 * @return
	 */
	public Date getHoraCreacion() {
		return horaCreacion;
	}

	/**
	 * @return
	 */
	public Date getHoraEmision() {
		return horaEmision;
	}

	/**
	 * @return
	 */
	public Date getHoraRecaudacion() {
		return horaRecaudacion;
	}

	/**
	 * @return
	 */
	public ArrayList getLineasDetalle() {
		return lineasDetalle;
	}

	/**
	 * @return
	 */
	public int getMontoEmitido() {
		return montoEmitido;
	}

	/**
	 * @return
	 */
	public int getMontoInformado() {
		return montoInformado;
	}

	/**
	 * @return
	 */
	public int getMontoInteres() {
		return montoInteres;
	}

	/**
	 * @return
	 */
	public int getMontoReajuste() {
		return montoReajuste;
	}

	/**
	 * @return
	 */
	public String getNombreRut1() {
		return nombreRut1;
	}

	/**
	 * @return
	 */
	public String getNombreRut2() {
		return nombreRut2;
	}

	/**
	 * @return
	 */
	public String getObservaciónMovimientoCaja() {
		return observaciónMovimientoCaja;
	}

	/**
	 * @return
	 */
	public long getRut1() {
		return rut1;
	}

	/**
	 * @return
	 */
	public long getRut2() {
		return rut2;
	}

	/**
	 * @return
	 */
	public int getSerPagadoPorCodigoOficina() {
		return serPagadoPorCodigoOficina;
	}

	/**
	 * @return
	 */
	public int getSesion() {
		return sesion;
	}

	/**
	 * @return
	 */
	public int getSucursal() {
		return sucursal;
	}

	/**
	 * @return
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @return
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * @return
	 */
	public String getUltimoUsuarioModifico() {
		return ultimoUsuarioModifico;
	}

	/**
	 * @return
	 */
	public String getUsuarioCreoRegistro() {
		return usuarioCreoRegistro;
	}

	/**
	 * @param i
	 */
	public void setCodigoAreaNegocio(int i) {
		codigoAreaNegocio = i;
	}

	/**
	 * @param string
	 */
	public void setCodigoBarra(String string) {
		codigoBarra = string;
	}

	/**
	 * @param i
	 */
	public void setCodigoCajero(int i) {
		codigoCajero = i;
	}

	/**
	 * @param i
	 */
	public void setCodigoOficina(int i) {
		codigoOficina = i;
	}

	/**
	 * @param i
	 */
	public void setCorrelativoPago(int i) {
		correlativoPago = i;
	}

	/**
	 * @param list
	 */
	public void setDatosComunes(ArrayList list) {
		datosComunes = list;
	}

	/**
	 * @param string
	 */
	public void setDv1(String string) {
		dv1 = string;
	}

	/**
	 * @param string
	 */
	public void setDv2(String string) {
		dv2 = string;
	}

	/**
	 * @param string
	 */
	public void setEmiteFactura(String string) {
		emiteFactura = string;
	}

	/**
	 * @param string
	 */
	public void setEstadoAutorizacion(String string) {
		estadoAutorizacion = string;
	}

	/**
	 * @param string
	 */
	public void setEstadoMovimientoCaja(String string) {
		estadoMovimientoCaja = string;
	}

	/**
	 * @param date
	 */
	public void setFechaApertura(Date date) {
		fechaApertura = date;
	}

	/**
	 * @param date
	 */
	public void setFechaCambio(Date date) {
		fechaCambio = date;
	}

	/**
	 * @param date
	 */
	public void setFechaCreacion(Date date) {
		fechaCreacion = date;
	}

	/**
	 * @param date
	 */
	public void setFechaDisponibilidad(Date date) {
		fechaDisponibilidad = date;
	}

	/**
	 * @param date
	 */
	public void setFechaEmision(Date date) {
		fechaEmision = date;
	}

	/**
	 * @param date
	 */
	public void setFechaRecaudacion(Date date) {
		fechaRecaudacion = date;
	}

	/**
	 * @param l
	 */
	public void setFolioMovimiento(long l) {
		folioMovimiento = l;
	}

	/**
	 * @param string
	 */
	public void setFormaPago(String string) {
		formaPago = string;
	}

	/**
	 * @param date
	 */
	public void setHoraCambio(Date date) {
		horaCambio = date;
	}

	/**
	 * @param date
	 */
	public void setHoraCreacion(Date date) {
		horaCreacion = date;
	}

	/**
	 * @param date
	 */
	public void setHoraEmision(Date date) {
		horaEmision = date;
	}

	/**
	 * @param date
	 */
	public void setHoraRecaudacion(Date date) {
		horaRecaudacion = date;
	}

	/**
	 * @param list
	 */
	public void setLineasDetalle(ArrayList list) {
		lineasDetalle = list;
	}

	/**
	 * @param i
	 */
	public void setMontoEmitido(int i) {
		montoEmitido = i;
	}

	/**
	 * @param i
	 */
	public void setMontoInformado(int i) {
		montoInformado = i;
	}

	/**
	 * @param i
	 */
	public void setMontoInteres(int i) {
		montoInteres = i;
	}

	/**
	 * @param i
	 */
	public void setMontoReajuste(int i) {
		montoReajuste = i;
	}

	/**
	 * @param string
	 */
	public void setNombreRut1(String string) {
		nombreRut1 = string;
	}

	/**
	 * @param string
	 */
	public void setNombreRut2(String string) {
		nombreRut2 = string;
	}

	/**
	 * @param string
	 */
	public void setObservaciónMovimientoCaja(String string) {
		observaciónMovimientoCaja = string;
	}

	/**
	 * @param l
	 */
	public void setRut1(long l) {
		rut1 = l;
	}

	/**
	 * @param l
	 */
	public void setRut2(long l) {
		rut2 = l;
	}

	/**
	 * @param i
	 */
	public void setSerPagadoPorCodigoOficina(int i) {
		serPagadoPorCodigoOficina = i;
	}

	/**
	 * @param i
	 */
	public void setSesion(int i) {
		sesion = i;
	}

	/**
	 * @param i
	 */
	public void setSucursal(int i) {
		sucursal = i;
	}

	/**
	 * @param string
	 */
	public void setTipoMovimiento(String string) {
		tipoMovimiento = string;
	}

	/**
	 * @param string
	 */
	public void setTipoPago(String string) {
		tipoPago = string;
	}

	/**
	 * @param string
	 */
	public void setUltimoUsuarioModifico(String string) {
		ultimoUsuarioModifico = string;
	}

	/**
	 * @param string
	 */
	public void setUsuarioCreoRegistro(String string) {
		usuarioCreoRegistro = string;
	}

}
