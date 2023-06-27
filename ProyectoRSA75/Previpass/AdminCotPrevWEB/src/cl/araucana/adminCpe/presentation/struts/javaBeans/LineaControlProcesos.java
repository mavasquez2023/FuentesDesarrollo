package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) LineaControlProcesos.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.2
 */
public class LineaControlProcesos implements Serializable, Comparable
{
	private static final long serialVersionUID = -1;
	
	private String categoriaPago;
	private String grupo;
	private String rutEmpresa;
	private String razonSocial;
	private String convenio;
	private String tipoProceso;
	private String idCodigoBarra;
	private String modoPago;
	private double totalPagado;
	private String totalPagadoMonto;
	private int totalTrabajadores;
	
	/**
	 * categoria pago
	 * @return
	 */
	public String getCategoriaPago() {
		return this.categoriaPago;
	}
	/**
	 * categoria pago
	 * @param categoriaPago
	 */
	public void setCategoriaPago(String categoriaPago) {
		this.categoriaPago = categoriaPago;
	}
	/**
	 * convenio
	 * @return
	 */
	public String getConvenio() {
		return this.convenio;
	}
	/**
	 * convenio
	 * @param convenio
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	/**
	 * grupo
	 * @return
	 */
	public String getGrupo() {
		return this.grupo;
	}
	/**
	 * grupo
	 * @param grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * modo pago
	 * @return
	 */
	public String getModoPago() {
		return this.modoPago;
	}
	/**
	 * modo pago
	 * @param modoPago
	 */
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}
	/**
	 * id codigo barra
	 * @return
	 */
	public String getIdCodigoBarra() {
		return this.idCodigoBarra;
	}
	/**
	 * codigo barra
	 * @param idCodigoBarra
	 */
	public void setIdCodigoBarra(String idCodigoBarra) {
		this.idCodigoBarra = idCodigoBarra;
	}
	/**
	 * razon social
	 * @return
	 */
	public String getRazonSocial() {
		return this.razonSocial;
	}
	/**
	 * razon social
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa() {
		return this.rutEmpresa;
	}
	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * tipo proceso
	 * @return
	 */
	public String getTipoProceso() {
		return this.tipoProceso;
	}
	/**
	 * tipo proceso
	 * @param tipoProceso
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * total pagado
	 * @return
	 */
	public double getTotalPagado() {
		return this.totalPagado;
	}
	/**
	 * total pagado
	 * @param totalPagado
	 */
	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
	/**
	 * total trabajadores
	 * @return
	 */
	public int getTotalTrabajadores() {
		return this.totalTrabajadores;
	}
	/**
	 * total trabajadores
	 * @param totalTrabajadores
	 */
	public void setTotalTrabajadores(int totalTrabajadores) {
		this.totalTrabajadores = totalTrabajadores;
	}

	public LineaControlProcesos()
	{
		super();
	}
	/**
	 * total pagado monto
	 * @return
	 */
	public String getTotalPagadoMonto() {
		return this.totalPagadoMonto;
	}
	/**
	 * total pagado monto
	 * @param totalPagadoMonto
	 */
	public void setTotalPagadoMonto(String totalPagadoMonto) {
		this.totalPagadoMonto = totalPagadoMonto;
	}
	public int compareTo(Object o)
	{
		LineaControlProcesos otro = (LineaControlProcesos)o;
		if (this.grupo.compareTo(otro.getGrupo()) == 0)
		{
			if (this.rutEmpresa.compareTo(otro.getRutEmpresa()) == 0)
				return this.convenio.compareTo(otro.getConvenio());
			return this.rutEmpresa.compareTo(otro.getRutEmpresa());
		}
		return this.grupo.compareTo(otro.getGrupo());
	}
	

}
