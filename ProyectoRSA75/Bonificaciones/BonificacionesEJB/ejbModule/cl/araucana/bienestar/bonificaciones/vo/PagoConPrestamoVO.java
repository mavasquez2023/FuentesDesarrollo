package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class PagoConPrestamoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private long casoId=0;
	private String rutSocio=null;
	private String dvSocio=null;
	private String rutConvenio=null;
	private String dvConvenio=null;
	private String nombreConvenio=null;
	private long codigoConvenio=0;
	private String documento=null;
	private String cuentaDeudor=null;
	private String cuentaAcreedor=null;
	private String tipoBono=null;
	private int total=0;
	private int aporteBienestar=0;
	private int aporteSocio=0;
	private int montoPrestamo=0;
	private int montoAbono=0;
	private int numeroPrestamo=0;
	private String nombre=null;
	private String apePat=null;
	private String apeMat=null;
	
	/**
	 * Retorna el nombre completo del socio
	 * @return
	 */
	public String getFullNombreSocio() {
		
		String retorno="";
				
		if (nombre != null && !nombre.equals(""))
			if(nombre.indexOf(' ')>=0)
				retorno = nombre.substring(0,nombre.indexOf(' '));
			else
				retorno = nombre;
			
		if(apePat!=null && !apePat.equals(""))
			retorno = retorno + " "+apePat+" ";
			
		if(apeMat!=null && !apeMat.equals(""))
			retorno = retorno + " "+apeMat.charAt(0);
			
		if(retorno.length()>14)
			retorno = retorno.substring(0,15);
		
		return retorno;

	}
	
	/**
	 * Retorna el rut compuesto del Socio sin guion
	 * @return String con el rut y dv
	 */
	public String getFullRutSocioSinGuion() {
		return rutSocio + dvSocio;
	}
		
	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRutSocio() {
		return rutSocio + "-" + dvSocio;
	}
	
	/**
	 * Retorna el rut compuesto del Convenio sin guion
	 * @return String con el rut y dv
	 */
	public String getFullRutConvenioSinGuion() {
		return rutConvenio + dvConvenio;
	}
	
	/**
	 * Retorna el rut compuesto del Convenio
	 * @return String con el rut
	 */
	public String getFullRutConvenio() {
		return rutConvenio + "-" + dvConvenio;
	}

	/**
	 * @return
	 */
	public int getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @return
	 */
	public int getAporteSocio() {
		return aporteSocio;
	}

	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
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
	public String getDocumento() {
		return documento;
	}

	/**
	 * @return
	 */
	public String getDvSocio() {
		return dvSocio;
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
	public int getTotal() {
		return total;
	}

	/**
	 * @param i
	 */
	public void setAporteBienestar(int i) {
		aporteBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setAporteSocio(int i) {
		aporteSocio = i;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param string
	 */
	public void setDocumento(String string) {
		documento = string;
	}

	/**
	 * @param string
	 */
	public void setDvSocio(String string) {
		dvSocio = string;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

	/**
	 * @param i
	 */
	public void setTotal(int i) {
		total = i;
	}

	/**
	 * @return
	 */
	public String getCuentaAcreedor() {
		return cuentaAcreedor;
	}

	/**
	 * @return
	 */
	public String getCuentaDeudor() {
		return cuentaDeudor;
	}

	/**
	 * @param string
	 */
	public void setCuentaAcreedor(String string) {
		cuentaAcreedor = string;
	}

	/**
	 * @param string
	 */
	public void setCuentaDeudor(String string) {
		cuentaDeudor = string;
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
	public String getDvConvenio() {
		return dvConvenio;
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
	public String getRutConvenio() {
		return rutConvenio;
	}

	/**
	 * @param string
	 */
	public void setDvConvenio(String string) {
		dvConvenio = string;
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
	public void setRutConvenio(String string) {
		rutConvenio = string;
	}


	/**
	 * @return
	 */
	public int getMontoAbono() {
		return montoAbono;
	}

	/**
	 * @return
	 */
	public int getMontoPrestamo() {
		return montoPrestamo;
	}

	/**
	 * @param i
	 */
	public void setMontoAbono(int i) {
		montoAbono = i;
	}

	/**
	 * @param i
	 */
	public void setMontoPrestamo(int i) {
		montoPrestamo = i;
	}

	/**
	 * @return
	 */
	public int getNumeroPrestamo() {
		return numeroPrestamo;
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
	public String getApeMat() {
		return apeMat;
	}

	/**
	 * @return
	 */
	public String getApePat() {
		return apePat;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setApeMat(String string) {
		apeMat = string;
	}

	/**
	 * @param string
	 */
	public void setApePat(String string) {
		apePat = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
