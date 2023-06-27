

package cl.araucana.cierrecpe.empresas.planillas.mutual;


import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.recursos.Formato;

/**
 * @author Jorge Marcelo González Covili - jmgonzalezcovili@hotmail.com.
 *
 */
public class PlanillaMutualDocumentModel extends PlanillaBase implements Constants {
	
	//Para el Tipo de Declaración y Pago de la Planilla a producir.
	private int filter=1;
	
	//cabecera
	private int numeroAdherente=0;			
	private long totalRemuneracionImponible=0;
	
	//totales
	private double porcTasaCotizacion=0.0;		
	private long totalCotizacion=0;
//	private String porcReajustes;			//Nuevo.
//	private String reajustes;
//	private String porcIntereses;			//Nuevo.
//	private String intereses;
//	private String porcMultas;				//Nuevo.
//	private String multas;					//Nuevo.
//	private String difCotizacion;			//Nuevo.
	
	
	public PlanillaMutualDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
	}
	/**
	 * @return el tipoRemuneracion
	 */
	public String getTipoRemuneracion() {
		if (!super.getTipoProceso().equals("G")){
			return "1";
		}else{
			return "2";
		}
	}
	/**
	 * @return el numeroAdherente
	 */
	public int getNumeroAdherente() {
		return numeroAdherente;
	}
	/**
	 * @param numeroAdherente el numeroAdherente a establecer
	 */
	public void setNumeroAdherente(int numeroAdherente) {
		this.numeroAdherente = numeroAdherente;
	}
	/**
	 * @return el porcTasaCotizacion
	 */
	public double getPorcTasaCotizacion() {
		return porcTasaCotizacion;
	}
	/**
	 * @return el porcTasaCotizacion
	 */
	public String getTasaMutual() {
		String tasa= String.valueOf(getPorcTasaCotizacion());
		tasa= tasa.replace('.', ',');
		tasa= Formato.paddingRigth(tasa, 4, '0');
		return tasa;
	}
	/**
	 * @param porcTasaCotizacion el porcTasaCotizacion a establecer
	 */
	public void setPorcTasaCotizacion(double porcTasaCotizacion) {
		this.porcTasaCotizacion = porcTasaCotizacion;
	}
	
	/**
	 * @return el totalCotizacion
	 */
	public long getTotalCotizacion() {
		return totalCotizacion;
	}
	/**
	 * @param totalCotizacion el totalCotizacion a establecer
	 */
	public void setTotalCotizacion(long totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	/**
	 * @return el totalRemuneracionImponible
	 */
	public long getTotalRemuneracionImponible() {
		return totalRemuneracionImponible;
	}
	/**
	 * @param totalRemuneracionImponible el totalRemuneracionImponible a establecer
	 */
	public void setTotalRemuneracionImponible(long totalRemuneracionImponible) {
		this.totalRemuneracionImponible = totalRemuneracionImponible;
	}
	/**
	 * @return el filter
	 */
	public int getFilter() {
		return filter;
	}
	/**
	 * @param filter el filter a establecer
	 */
	public void setFilter(int filter) {
		this.filter = filter;
	}

	

}
