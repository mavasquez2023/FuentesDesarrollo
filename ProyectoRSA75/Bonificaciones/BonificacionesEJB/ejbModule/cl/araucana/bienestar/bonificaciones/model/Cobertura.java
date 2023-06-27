package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Cobertura implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	//Tipos de Tope
	public static final String TOPE_ANUALBENEFICIARIO="ANUALBENEF";
	public static final String TOPE_ANUALGRUPO="ANUALFAMIL";
	public static final String TOPE_MENSUALGRUPO="MENSUALFAM";
	public static final String TOPE_EVENTO="PORPRESTA";
	//Estados
	public static final String STD_BORRADOR="BORRADOR";	
	public static final String STD_ACTIVO="ACTIVO";	
	public static final String STD_ELIMINADO="INACTIVO";
	//Tipo Cobertura
	public static final String TIPO_DIRECTA="DIRECTA";
	public static final String TIPO_ADICIONAL="ADICIONAL";
	public static final String TIPO_ESPECIAL="ESPECIAL";
	//
	public static final String TIENE_OCURRENCIAS_SI="SI";
	public static final String TIENE_OCURRENCIAS_NO="NO";
	
	private long codigo = 0;
	private long conceptoCodigo = 0;
	private String conceptoDescripcion = null;
	private double tope = 0;
	private String descripcion = null;
	private double valorReferencial = 0;
	private String tipoTope = null;
	private String estado = STD_BORRADOR; 
	private int periodoCarencia = 0; //en meses
	private long cuentaGasto=0;
	private String tipoCobertura=TIPO_DIRECTA;
	private String tieneOcurrencias=TIENE_OCURRENCIAS_NO;
	private String etiquetaOcurrencia=null;
	private ArrayList coberturaAdicional = new ArrayList(); //CoberturaAdicional
	
	private VigenciaRango rangoVigente=null;
	private ArrayList rangosHistoricos = new ArrayList(); //VigenciaRango
	private VigenciaRango rangoFuturo=null;
	
	
	/*
	 * obtiene fecha inicio vigencia para la pagina de rango vigentes
	 */
	public String getFechaInicioVigente(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicioVig = "";
		
		if (this.getRangoVigente() != null)
			fechaInicioVig = formatter.format(this.getRangoVigente().getInicioVigencia());
		else if (this.getRangosHistoricos() != null && this.getRangosHistoricos().size() != 0) {
				VigenciaRango vigenciaRango = (VigenciaRango)this.getRangosHistoricos().get(this.getRangosHistoricos().size() - 1);
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(vigenciaRango.getFinVigencia());
				cal.add(Calendar.DAY_OF_MONTH,1);
				fechaInicioVig = formatter.format(cal.getTime());
		}else {
				Calendar calendar = Calendar.getInstance();
				fechaInicioVig = formatter.format(calendar.getTime());
		}

		return fechaInicioVig;
	}

	/*
	 * obtiene fecha fin vigencia para la pagina de rango vigentes
	 */
	public String getFechaFinVigente(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFinVig = "";
		if (this.getRangoVigente() != null && this.getRangoVigente().getFinVigencia() != null)
				fechaFinVig = formatter.format(this.getRangoVigente().getFinVigencia());
		else if (this.getRangoFuturo() != null && this.getRangoFuturo().getInicioVigencia() != null) {
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(this.getRangoFuturo().getInicioVigencia());
			cal.add(Calendar.DAY_OF_MONTH,-1);
			fechaFinVig = formatter.format(cal.getTime());			
		}

		return fechaFinVig;
	}

	/*
	 * obtiene fecha inicio vigencia para la pagina de rangos futuros
	 */
	public String getFechaInicioFuturo(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicioFut = "";
		if (this.getRangoFuturo() != null && this.getRangoFuturo().getInicioVigencia() != null)
				fechaInicioFut = formatter.format(this.getRangoFuturo().getInicioVigencia());
		else if(this.getRangoVigente() != null && this.getRangoVigente().getFinVigencia() != null) {
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(this.getRangoVigente().getFinVigencia());
			cal.add(Calendar.DAY_OF_MONTH,1);
			fechaInicioFut = formatter.format(cal.getTime());
		}

		return fechaInicioFut;
	}

	/*
	 * obtiene fecha fin vigencia para la pagina de rangos futuros
	 */
	public String getFechaFinFuturo(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFinFut = "";
		if (this.getRangoFuturo() != null && this.getRangoFuturo().getFinVigencia() != null)
			fechaFinFut = formatter.format(this.getRangoFuturo().getFinVigencia());

		return fechaFinFut;
	}

	/**
	 * @return
	 */
	public ArrayList getCoberturaAdicional() {
		return coberturaAdicional;
	}

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public long getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * @return
	 */
	public String getConceptoDescripcion() {
		return conceptoDescripcion;
	}

	/**
	 * @return
	 */
	public long getCuentaGasto() {
		return cuentaGasto;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
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
	public String getEtiquetaOcurrencia() {
		return etiquetaOcurrencia;
	}

	/**
	 * @return
	 */
	public int getPeriodoCarencia() {
		return periodoCarencia;
	}

	/**
	 * @return
	 */
	public VigenciaRango getRangoFuturo() {
		return rangoFuturo;
	}

	/**
	 * @return
	 */
	public ArrayList getRangosHistoricos() {
		return rangosHistoricos;
	}

	/**
	 * @return
	 */
	public VigenciaRango getRangoVigente() {
		return rangoVigente;
	}

	/**
	 * @return
	 */
	public String getTieneOcurrencias() {
		return tieneOcurrencias;
	}

	/**
	 * @return
	 */
	public String getTipoCobertura() {
		return tipoCobertura;
	}

	/**
	 * @return
	 */
	public String getTipoTope() {
		return tipoTope;
	}

	/**
	 * @return
	 */
	public double getTope() {
		return tope;
	}

	/**
	 * @return
	 */
	public double getValorReferencial() {
		return valorReferencial;
	}

	/**
	 * @param list
	 */
	public void setCoberturaAdicional(ArrayList list) {
		coberturaAdicional = list;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param l
	 */
	public void setConceptoCodigo(long l) {
		conceptoCodigo = l;
	}

	/**
	 * @param string
	 */
	public void setConceptoDescripcion(String string) {
		conceptoDescripcion = string;
	}

	/**
	 * @param l
	 */
	public void setCuentaGasto(long l) {
		cuentaGasto = l;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param string
	 */
	public void setEtiquetaOcurrencia(String string) {
		etiquetaOcurrencia = string;
	}

	/**
	 * @param i
	 */
	public void setPeriodoCarencia(int i) {
		periodoCarencia = i;
	}

	/**
	 * @param rango
	 */
	public void setRangoFuturo(VigenciaRango rango) {
		rangoFuturo = rango;
	}

	/**
	 * @param list
	 */
	public void setRangosHistoricos(ArrayList list) {
		rangosHistoricos = list;
	}

	/**
	 * @param rango
	 */
	public void setRangoVigente(VigenciaRango rango) {
		rangoVigente = rango;
	}

	/**
	 * @param string
	 */
	public void setTieneOcurrencias(String string) {
		tieneOcurrencias = string;
	}

	/**
	 * @param string
	 */
	public void setTipoCobertura(String string) {
		tipoCobertura = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTope(String string) {
		tipoTope = string;
	}

	/**
	 * @param d
	 */
	public void setTope(double d) {
		tope = d;
	}

	/**
	 * @param d
	 */
	public void setValorReferencial(double d) {
		valorReferencial = d;
	}

}
