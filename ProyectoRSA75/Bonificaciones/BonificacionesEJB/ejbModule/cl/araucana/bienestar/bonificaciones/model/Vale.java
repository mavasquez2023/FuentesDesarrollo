package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Vale implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;


	public static final String ANULADO="S";
	public static final String NO_ANULADO="N";

	private long codigoVale = 0;
	private long codigoTalonario = 0;
	private long caso_id = 0;
	private Date fechaEntrega = null;
	private Date fechaRecepcion = null;
	private double monto = 0;
	private String valeAnulado = NO_ANULADO;
	private long codigoConvenio = 0;
	private String nombreConvenio = null;
	private String rutSocio = null;
	private String rutSocioDv = null;
	
	
	public Vale() {
	}

	public Vale(TalonarioVO talonarioVo) {		
		codigoTalonario = talonarioVo.getCodigoTalonario();
		codigoVale = talonarioVo.getValeDisponible();
		codigoConvenio = talonarioVo.getCodigoConvenio();
		nombreConvenio = talonarioVo.getNombreConvenio();	
	}
	
	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rutSocio+"-"+rutSocioDv;
	}
	
	/**
	 * @return
	 */
	public long getCaso_id() {
		return caso_id;
	}

	/**
	 * @return
	 */
	public long getCodigoTalonario() {
		return codigoTalonario;
	}

	/**
	 * @return
	 */
	public long getCodigoVale() {
		return codigoVale;
	}

	/**
	 * @return
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @return
	 */
	public Date getFechaRecepcion() {
		return fechaRecepcion;
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
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public String getValeAnulado() {
		return valeAnulado;
	}

	/**
	 * @param l
	 */
	public void setCaso_id(long l) {
		caso_id = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoTalonario(long l) {
		codigoTalonario = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoVale(long l) {
		codigoVale = l;
	}

	/**
	 * @param date
	 */
	public void setFechaEntrega(Date date) {
		fechaEntrega = date;
	}

	/**
	 * @param date
	 */
	public void setFechaRecepcion(Date date) {
		fechaRecepcion = date;
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
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setValeAnulado(String string) {
		valeAnulado = string;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
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
	public String getRutSocioDv() {
		return rutSocioDv;
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
	public void setRutSocioDv(String string) {
		rutSocioDv = string;
	}

}
