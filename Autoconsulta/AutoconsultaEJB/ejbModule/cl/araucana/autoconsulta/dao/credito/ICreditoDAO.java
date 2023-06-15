package cl.araucana.autoconsulta.dao.credito;

import java.util.Collection;

public interface ICreditoDAO {
	/**
	 * Objeto: Consulta todos los creditos de un titular
	 * @param rut de afiliado
	 * @return coleccion de CreditoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCreditosTitular(long rutAfiliado);
	
	/**
	 * Objeto: Consulta todos los creditos de los empleados de una empresa
	 * @param rut de empresa
	 * @return coleccion de CreditoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCreditosEmpresa(long rutEmpresa);
	
	
	/**
	 * Objeto: Consulta todos los creditos de un empleado asociado a una empresa
	 * @param rut de empresa y rut afiliado
	 * @return coleccion de CreditoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCreditosEmpresaAfiliado(long rutEmpresa, long rutAfiliado);
	
	
	/**
	 * Objeto: Consulta todos los creditos en los cuales el rut es aval
	 * @param rut aval
	 * @return coleccion de CreditoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCreditosAval(long rutAfiliado);
	
	
	/**
	 * Objeto: Consulta la informacion del credito 
	 * @param oficina y folio
	 * @return coleccion de CreditoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCreditosFolio(String oficina, String folio);
	
	
	/**
	 * Objeto: Consulta las cuotas de un credito
	 * @param String oficina, String folio
	 * @return coleccion de CuotaTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getCuotas(String oficina, String folio);
	
	/**
	 * Objeto: Consulta los pagos de un credito
	 * @param String oficina, String folio
	 * @return coleccion de PagoCuotaTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getPagos(String oficina, String folio);

	/**
	 * Objeto: Consulta de seguros comprometidos
	 * @param String oficina, String folio
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getSegurosCredito(String oficina, String folio);

}
