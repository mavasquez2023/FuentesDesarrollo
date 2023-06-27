package cl.araucana.autoconsulta.vo;

/**
 * @author asepulveda
 * Interfaz que debe ser implementada por los certificados generados
 * por el sistema, para poder validarlos posterioemente
 * 
 */
public interface Validable {

	/**
	 * Devuelve el objeto que se utiliza para registrar los datos que se
	 * utilizan para validar posteriormente el certificado.
	 * @param no tiene
	 * @return DatosValidacionVO
	 */
	public DatosValidacionVO getDatosValidacion() throws Exception;
	
}
