package cl.laaraucana.botonpago.web.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat03;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;

public class Calculo {
	private static Logger logger = Logger.getLogger(Calculo.class);
	
	public static String getCuota(String capital, String intereses, String seguros, String abono, String gravamen) throws NumberFormatException, Exception {
		int cuota = 0;
		cuota += Integer.parseInt(capital);
		cuota += Integer.parseInt(intereses);
		cuota += Integer.parseInt(seguros);
		cuota += getCondGravamen(Integer.parseInt(gravamen));
		cuota -= Integer.parseInt(abono);
		
		logger.info("capital=" + capital+" + intereses=" + intereses+ " + seguros=" + seguros + " + gravamen condonado=" + getCondGravamen(Integer.parseInt(gravamen.trim()))+ " - abono=" + abono+" (gravamen sin cond="+gravamen+") TOTAL CUOTA = " + cuota);

		return String.valueOf(cuota);
	}
	
	public static String getCuotaEPO(String capital, String abono) throws NumberFormatException, Exception {
		int cuota = 0;
		cuota += Integer.parseInt(capital);
		cuota -= Integer.parseInt(abono);
		
		logger.info("capital=" + capital + " - abono=" + abono+ ", TOTAL CUOTA = " + cuota);

		return String.valueOf(cuota);
	}
	
	public static int getCondGravamen(int gravamen) throws Exception {
		Double grav = (double) gravamen;
		List<Sinat03> gravList = Constantes.getInstancia().CONDONACION_GRAVAMENES;
		int porcent = Integer.parseInt(gravList.get(0).getPorcen());
		double coef = ((100.0d - (double) porcent) / 100.0d);
		grav = (grav * coef);
		return grav.intValue();
	}

	public static int getPagoMinimo(int totalDeuda, int sumaGravPlusGCob) throws NumberFormatException, Exception {
		Double pagoMinimo = 0.0;
		double coef = (Double.parseDouble(Constantes.getInstancia().PORCENTAJE_PAGO_MINIMO)) / ((double) 100);
		pagoMinimo = totalDeuda * coef;
		if ((sumaGravPlusGCob) > pagoMinimo) {
			pagoMinimo = (double) (sumaGravPlusGCob);
		}

		return pagoMinimo.intValue();
	}

	public static Map<String, Integer> getCondGCob(int gCob, String otorgamiento) throws Exception {
		Double gcob = (double) gCob;
		int porcent = 0;
		List<Sinat04> gcobList = Constantes.getInstancia().CONDONACION_GASTOS_COBRANZA;
		int yearAux = 0;
		int year2 = Integer.parseInt(otorgamiento.trim().substring(0, 4));
		for (Sinat04 sinat04 : gcobList) {
			if (year2 <= Integer.parseInt(sinat04.getAnopro())) {
				if (yearAux < year2) {
					porcent = Integer.parseInt(sinat04.getPorcen());
					yearAux = year2;
				}
			}
		}

		double coef = ((100.0d - (double) porcent) / 100.0d);
		gcob = (gcob * coef);
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		myMap.put("gcob", gcob.intValue());
		myMap.put("porcent", porcent);

		return myMap;
	}
	// public static int getTotalDeuda(int sumaCuotasMorosas) {
	//
	// return 0;
	// }

}
