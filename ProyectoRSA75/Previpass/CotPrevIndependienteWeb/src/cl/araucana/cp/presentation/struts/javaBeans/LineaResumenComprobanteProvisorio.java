package cl.araucana.cp.presentation.struts.javaBeans;
/*
* @(#) LineaResumenComprobanteProvisorio.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.1
 */
public class LineaResumenComprobanteProvisorio {

	private String glosa;
	private long total;
	/**
	 * glosa
	 * @return
	 */
	public String getGlosa() {
		return this.glosa;
	}
	/**
	 * glosa
	 * @param glosa
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	/**
	 * total
	 * @return
	 */
	public long getTotal() {
		return this.total;
	}
	/**
	 * total
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}
}
