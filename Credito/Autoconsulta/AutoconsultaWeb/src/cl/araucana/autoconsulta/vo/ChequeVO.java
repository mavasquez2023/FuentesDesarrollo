package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class ChequeVO implements Serializable {

	//Estado cheque	
	public static final String ESTD_CADUCADO="C";
	public static final String ESTD_EMITIDO="I";
	public static final String ESTD_PAGADO="P";
	public static final String ESTD_ANULADO="A";
	public static final String ESTD_RETIRADO="R";
	
	//Forma de Pago
	public static final String FORMA_PAGO_PORCAJA="C";
	public static final String FORMA_PAGO_PORCORREO="G";
	public static final String FORMA_PAGO_RECAUDACIONELECTRONICA="R";
	public static final String FORMA_PAGO_CREDITO="E";
	
	private long folio=0;
	private int monto=0;
	private Date periodo=null;
	private String codigoEstadoCheque=null;
	private String codigoFormaPago=null;
	private Date fechaPago=null;
	private String sucursal=null;
	private int numeroRegistros=0;
	private ConceptoChequeVO conceptoCheque=null;
	
	/**
	 * Obtiene el concepto del cheque
	 * @return String
	 */
	public String getConceptoDespliegue() {
		
		String concepto=conceptoCheque.getObservacionDetalle();
		
		if(numeroRegistros==1){
			if(conceptoCheque.getObservacionDetalle().trim().equals("") || conceptoCheque.getObservacionDetalle()==null){
				concepto=conceptoCheque.getItemGasto();
			}				
		}else{
			if(conceptoCheque.getCodigoConcepto()!=1){
				concepto=conceptoCheque.getItemGasto();
			}
		}
		
		if(concepto.length()>21){
			if(concepto.substring(0,21).compareTo("SALDO A FAVOR EMPRESA")==0)
			concepto = concepto.substring(0,21);
		}
		
		return concepto;
		
	}

	/**
	 * Se encarga de traducir algunos códigos utilizados
	 * @return
	 */
	public String getCodigoEstadoCheque() {
		String retorno=codigoEstadoCheque;
		
		if((codigoFormaPago.equals(FORMA_PAGO_PORCORREO)) && (codigoEstadoCheque.equals(ESTD_PAGADO))){
			retorno = "100";
		}
		
		if((codigoFormaPago.equals(FORMA_PAGO_PORCAJA)) && (codigoEstadoCheque.equals(ESTD_PAGADO))){
			retorno= "100";
		}	
					
		if((codigoFormaPago.equals(FORMA_PAGO_PORCORREO)) && (codigoEstadoCheque.equals(ESTD_EMITIDO))){
			retorno = "101";
		}					
		
		return retorno;
	}

	/**
	 * Se encarga de traducir algunos códigos utilizados
	 * @return
	 */
	public String getCodigoFormaPago() {
		
		String retorno=codigoFormaPago;
		
		if((codigoFormaPago.equals(FORMA_PAGO_PORCORREO)) && (codigoEstadoCheque.equals(ESTD_PAGADO))){
			retorno = "101";
		}
		
		if((codigoFormaPago.equals(FORMA_PAGO_PORCAJA)) && (codigoEstadoCheque.equals(ESTD_PAGADO))){
			retorno = "101";
		}	
					
		return retorno;
	}
	
	/**
	 * Si código de sucursal es 0 entonces devuelve ""
	 * @return
	 */
	public String getSucursal() {
		if(sucursal.equals("0"))
			sucursal="";
		return sucursal;
	}

	/**
	 * @return
	 */
	public Date getFechaPago() {
		return fechaPago;
	}

	/**
	 * @return
	 */
	public long getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstadoCheque(String string) {
		codigoEstadoCheque = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoFormaPago(String string) {
		codigoFormaPago = string;
	}

	/**
	 * @param date
	 */
	public void setFechaPago(Date date) {
		fechaPago = date;
	}

	/**
	 * @param l
	 */
	public void setFolio(long l) {
		folio = l;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param string
	 */
	public void setSucursal(String string) {
		sucursal = string;
	}

	/**
	 * @return
	 */
	public ConceptoChequeVO getConceptoCheque() {
		return conceptoCheque;
	}

	/**
	 * @param chequeVO
	 */
	public void setConceptoCheque(ConceptoChequeVO chequeVO) {
		conceptoCheque = chequeVO;
	}

	/**
	 * @return
	 */
	public int getNumeroRegistros() {
		return numeroRegistros;
	}

	/**
	 * @param i
	 */
	public void setNumeroRegistros(int i) {
		numeroRegistros = i;
	}

	/**
	 * @return
	 */
	public Date getPeriodo() {
		return periodo;
	}

	/**
	 * @param date
	 */
	public void setPeriodo(Date date) {
		periodo = date;
	}

}
