package cl.laaraucana.capaservicios.util;

public class UtilCodigos {
	
	/**
	 * Determina si la transferencia fue fallida en base a los códigos de estado de pago erróneos
	 * definidos en STL
	 * @param codigo
	 * @param codigosEstados
	 * @return
	 * @throws Exception
	 */
	public static boolean isTransfRealizada(String codigo, String codigosRealizado) throws Exception {
		boolean salida = false;
		try {
			String[] arregloCodigos = codigosRealizado.split(";");
			for (String codigoRealizada : arregloCodigos) {
				if (codigoRealizada.equals(codigo)) {
					salida = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salida;
	}

}
