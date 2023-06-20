/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.utils;

/**
 * @author IBM Software Factory
 *
 */
public class Estados {
	public static final int NOMINA_PENDIENTE= 1;
	public static final int NOMINA_ENVIADA= 2;
	public static final int NOMINA_RECHAZADA= 3;
	public static final int NOMINA_ENPROCESO= 4;
	public static final int NOMINA_CERRADA= 5;
	
	public static final int PAGO_PAGADO= 1;
	public static final int PAGO_PENDIENTE_PROCESO= 2;
	public static final int PAGO_PROCESO_PAGO= 3;
	public static final int PAGO_RECHAZADO= 4;
	public static final int PAGO_ANULADO= 5;
	public static final int PAGO_DEVUELTO= 6;
	
	public static final int PAGO_RECHAZADO_BENEFICIOS = 3;
	public static final int PAGO_PAGADO_BENEFICIOS = 4;
	
}
