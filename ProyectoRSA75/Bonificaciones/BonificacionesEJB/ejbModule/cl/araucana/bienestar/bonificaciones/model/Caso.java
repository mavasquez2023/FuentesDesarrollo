package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Caso implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	//Estados de los casos
	// Flujo Nuevo Nov 2005 Pre-Casos
	public static final String STD_PRECASO="PRECASO";
	// Flujo normal
	public static final String STD_BORRADOR="BORRADOR";	
	public static final String STD_ACTIVO="ACTIVO";	
	public static final String STD_ELIMINADO="ELIMINADO";
	public static final String STD_CERRADO="CERRADO";
	
	//Tipos
	public static final String TIPO_DESCUENTO = "DESCUENTO"; 
	public static final String TIPO_REEMBOLSO = "REEMBOLSO";
	//Estados Indicadores
	public static final String ESTADOINDICADOR_SI = "SI";
	public static final String ESTADOINDICADOR_NO = "NO";
	public static final String ESTADOINDICADOR_PROCESO = "PROCESO";
	//Tipo Bono
	public static final String TIPOBONO_SOCIO = "SOCIO"; //girado a nombre del socio
	public static final String TIPOBONO_CONVENIO = "CONVENIO"; //girado a nombre del convenio o institucion
	public static final String TIPOBONO_NO = "NO";
	//Tipo de documento
	public static final String TIPO_DOCUMENTO_BOLETA = "1";
	public static final String TIPO_DOCUMENTO_FACTURA = "2";
	public static final String TIPO_DOCUMENTO_BONO = "3";
	
	private long casoID = 0;
	private String rutSocio = null;
	private String rutCarga = null;
	private long codigoConvenio = 0;
	private double monto = 0;
	private double montoDescuento = 0;
	private double aporteIsapre = 0;
	private double aporteBienestar = 0;
	private double aporteSocio = 0;
	private double aporteConvenio = 0;
	private Date fechaIngreso = null;
	private Date fechaEstado = null;
	private Date fechaDeOcurrencia = null;
	private int cuotasConvenio = 0;
	private int cuotasBienestar = 0;
	private String tipoCaso = null;
	private String estado = STD_BORRADOR;
	private String tipoDocumento= null;
	private String numeroDocumento = null;
	private String tipoBono = TIPOBONO_NO;
	private double prestamo = 0;
	private String indicadorReembolso = ESTADOINDICADOR_NO;
	private String indicadorBonificacion = ESTADOINDICADOR_NO;
	private String indicadorPago = ESTADOINDICADOR_NO;
	private String indicadorDescontado = ESTADOINDICADOR_NO;
	private String indicadorPagoAnticipado = ESTADOINDICADOR_NO;
	private String indicadorPreCaso = ESTADOINDICADOR_NO;
	private String indicadorPreCasoEgresoGenerado= ESTADOINDICADOR_NO;
	private String indicadorPreCasoIngresoGenerado = ESTADOINDICADOR_NO;
	private double abono = 0;
	private int numeroPrestamo = 0;
	private Vale vale = null;
	private ArrayList detalleCaso = new ArrayList();
	private ArrayList evento = new ArrayList();
	private ArrayList cuota = new ArrayList();
	private String usuario = null;
	
	/**
	 * Devuelve el total del caso, sin aplicar los descuentos ni los
	 * aportes de la isapre
	 * @return
	 */
	public double getTotalSinDescontarAportesIsapre() {
		return monto - montoDescuento;
	}

	
	public double getTotal() {
		return monto - montoDescuento - aporteIsapre;
	}
	
	/**
	 * Agrega un detalle de Caso
	 */
	public void addDetalleCaso(DetalleCaso detCaso) {
		if (detalleCaso == null) {
			detalleCaso = new ArrayList();
		}
		detalleCaso.add(detCaso);
	}
	
	/**
	 * Elimina un detalle de Caso
	 */
	public void removeDetalleCaso(int index) {
		if (detalleCaso.size() > 0 && index < detalleCaso.size())
		detalleCaso.remove(index);
	}
	
	public void removeAllDetalleCaso() {
		detalleCaso.clear();
	}
	
	public void removeAllCuotas() {
		cuota.clear();
	}

	/**
	 * Agrega un evento
	 */
	public void addEvento(Evento eve) {
		if (evento == null) {
			evento = new ArrayList();
		}
		evento.add(eve);
	}
	
	/**
	 * @return
	 */
	public double getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @return
	 */
	public double getAporteIsapre() {
		return aporteIsapre;
	}

	/**
	 * @return
	 */
	public double getAporteSocio() {
		return aporteSocio;
	}

	/**
	 * @return
	 */
	public long getCasoID() {
		return casoID;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public int getCuotasBienestar() {
		return cuotasBienestar;
	}

	/**
	 * @return
	 */
	public int getCuotasConvenio() {
		return cuotasConvenio;
	}

	/**
	 * @return
	 */
	public double getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Date getFechaDeOcurrencia() {
		return fechaDeOcurrencia;
	}

	/**
	 * @return
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @return
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @return
	 */
	public String getIndicadorBonificacion() {
		return indicadorBonificacion;
	}

	/**
	 * @return
	 */
	public String getIndicadorDescontado() {
		return indicadorDescontado;
	}

	/**
	 * @return
	 */
	public String getIndicadorPago() {
		return indicadorPago;
	}

	/**
	 * @return
	 */
	public String getIndicadorReembolso() {
		return indicadorReembolso;
	}

	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @return
	 */
	public double getPrestamo() {
		return prestamo;
	}

	/**
	 * @return
	 */
	public String getRutCarga() {
		return rutCarga;
	}

	/**
	 * @return
	 */
	public String getRutSocio() {
		return rutSocio;
	}

	/**
	 * @return
	 */
	public String getTipoCaso() {
		return tipoCaso;
	}

	/**
	 * @return
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param d
	 */
	public void setAporteBienestar(double d) {
		aporteBienestar = d;
	}

	/**
	 * @param d
	 */
	public void setAporteIsapre(double d) {
		aporteIsapre = d;
	}

	/**
	 * @param d
	 */
	public void setAporteSocio(double d) {
		aporteSocio = d;
	}

	/**
	 * @param l
	 */
	public void setCasoID(long l) {
		casoID = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param i
	 */
	public void setCuotasBienestar(int i) {
		cuotasBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setCuotasConvenio(int i) {
		cuotasConvenio = i;
	}

	/**
	 * @param d
	 */
	public void setMontoDescuento(double d) {
		montoDescuento = d;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param date
	 */
	public void setFechaDeOcurrencia(Date date) {
		fechaDeOcurrencia = date;
	}

	/**
	 * @param date
	 */
	public void setFechaEstado(Date date) {
		fechaEstado = date;
	}

	/**
	 * @param date
	 */
	public void setFechaIngreso(Date date) {
		fechaIngreso = date;
	}

	/**
	 * @param string
	 */
	public void setIndicadorBonificacion(String string) {
		indicadorBonificacion = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorDescontado(String string) {
		indicadorDescontado = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPago(String string) {
		indicadorPago = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorReembolso(String string) {
		indicadorReembolso = string;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

	/**
	 * @param string
	 */
	public void setNumeroDocumento(String string) {
		numeroDocumento = string;
	}

	/**
	 * @param d
	 */
	public void setPrestamo(double d) {
		prestamo = d;
	}

	/**
	 * @param string
	 */
	public void setRutCarga(String string) {
		rutCarga = string;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

	/**
	 * @param string
	 */
	public void setTipoCaso(String string) {
		tipoCaso = string;
	}

	/**
	 * @param string
	 */
	public void setTipoDocumento(String string) {
		tipoDocumento = string;
	}

	/**
	 * @return
	 */
	public ArrayList getCuota() {
		return cuota;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalleCaso() {
		return detalleCaso;
	}

	/**
	 * @return
	 */
	public ArrayList getEvento() {
		return evento;
	}

	/**
	 * @param list
	 */
	public void setCuota(ArrayList list) {
		cuota = list;
	}

	/**
	 * @param list
	 */
	public void setDetalleCaso(ArrayList list) {
		detalleCaso = list;
	}

	/**
	 * @param list
	 */
	public void setEvento(ArrayList list) {
		evento = list;
	}

	/**
	 * @return
	 */
	public Vale getVale() {
		return vale;
	}

	/**
	 * @param vale
	 */
	public void setVale(Vale vale) {
		this.vale = vale;
	}

	/**
	 * @return
	 */
	public String getTipoBono() {
		return tipoBono;
	}

	/**
	 * @param string
	 */
	public void setTipoBono(String string) {
		tipoBono = string;
	}

	/**
	 * @return
	 */
	public double getAbono() {
		return abono;
	}

	/**
	 * @return
	 */
	public String getIndicadorPagoAnticipado() {
		return indicadorPagoAnticipado;
	}

	/**
	 * @return
	 */
	public int getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * @param d
	 */
	public void setAbono(double d) {
		abono = d;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPagoAnticipado(String string) {
		indicadorPagoAnticipado = string;
	}

	/**
	 * @param i
	 */
	public void setNumeroPrestamo(int i) {
		numeroPrestamo = i;
	}

	/**
	 * @return
	 */
	public double getAporteConvenio() {
		return aporteConvenio;
	}

	/**
	 * @param d
	 */
	public void setAporteConvenio(double d) {
		aporteConvenio = d;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public String getIndicadorPreCaso() {
		return indicadorPreCaso;
	}

	/**
	 * @return
	 */
	public String getIndicadorPreCasoEgresoGenerado() {
		return indicadorPreCasoEgresoGenerado;
	}

	/**
	 * @return
	 */
	public String getIndicadorPreCasoIngresoGenerado() {
		return indicadorPreCasoIngresoGenerado;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPreCaso(String string) {
		indicadorPreCaso = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPreCasoEgresoGenerado(String string) {
		indicadorPreCasoEgresoGenerado = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPreCasoIngresoGenerado(String string) {
		indicadorPreCasoIngresoGenerado = string;
	}

}
