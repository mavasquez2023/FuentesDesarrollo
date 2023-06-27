package cl.araucana.tesoreria.dao;


import cl.araucana.common.BusinessException;

/**
 * @author asepulveda
 * Metodos expuestos por TesoreriaCobolDAO
 */
public interface FolioDAO {

	/**
	 * Genera un nuevo numero de Folio en Tesoreria
	 * @param	String sistema
	 * 			String programa
	 * 			String user
	 * 			String psw
	 * @return String, numero de folio 
	 */
	public String obtenerFolio(String sys, String pgm, String user, String psw) throws Exception, BusinessException;
	
	/**
	 * Anula un comprobante de Egreso en Tesoreria
	 * @param	String sistema
	 * 			String programa
	 * 			String user
	 * 			String psw
	 *			int largoMaximoFolio
	 *			int largoMaximomensaje
	 * @return String:
	 * 		Si es blanco OK
	 * 		Si tiene info es error
	 */
	public String anularComprobanteEgreso(long folio,
										String sistema,
										String programa,
										String user,
										String psw,
										int largoMaximoFolio,
										int largoMaximomensaje) throws Exception, BusinessException;	

}
