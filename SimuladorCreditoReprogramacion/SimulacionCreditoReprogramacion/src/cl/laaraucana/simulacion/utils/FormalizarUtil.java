package cl.laaraucana.simulacion.utils;

import java.util.Calendar;
import java.util.Date;

public class FormalizarUtil {

	/**
	 * Permite calcular la primera fecha de vencimiento del trabajador
	 * 
	 * @param tipoAfiliado
	 * @return La fecha de primer vencimiento segpun tipo de afiliado
	 */
	public static Date getFechaPrimerVenc(String tipoAfiliado) {
		Calendar f = Calendar.getInstance();
		// Afiliado e independiente
		if ((tipoAfiliado.equalsIgnoreCase(ConstantesFormalizar.COD_AFILIADO)) || (tipoAfiliado.equalsIgnoreCase(ConstantesFormalizar.COD_INDEPENDIENTE))) {
			f.add(Calendar.MONTH, 1);
			f.set(Calendar.DAY_OF_MONTH,
					f.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (tipoAfiliado.equalsIgnoreCase(ConstantesFormalizar.COD_PENSIONADO)) {
			// Pensionado
			f.add(Calendar.MONTH, 2);
			f.set(Calendar.DAY_OF_MONTH,
			f.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		Date fecha = new Date();
		fecha = f.getTime();

		return fecha;
	}

}
