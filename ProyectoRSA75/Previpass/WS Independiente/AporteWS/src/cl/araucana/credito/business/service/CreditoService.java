/**
 * 
 */
package cl.araucana.credito.business.service;


/**
 * @author usist199
 *
 */
public interface CreditoService {
	public Object getContratosByRutDeudor(String rut, int tipo) throws Exception;
}
