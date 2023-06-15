package cl.laaraucana.botonpago.web.cobol.call;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaRecuperaVO;
import cl.laaraucana.botonpago.web.database.dao.RecuperacionAs400DAO;
import cl.laaraucana.botonpago.web.utils.Util;

public class RecuperacionCall {
	protected Logger logger = Logger.getLogger(this.getClass());

	public EntradaSalidaRecuperaVO recuperaCredito(EntradaSalidaRecuperaVO entrada) throws Exception {

		String ofipro = Util.rellenarCampos(entrada.getOfipro(), 3, "0");
		String crefol = Util.rellenarCampos(entrada.getCrefol(), 9, "0");
		String monpag = Util.rellenarCampos(entrada.getMonpag(), 9, "0");
		String tipocr = Util.rellenarCampos(entrada.getTipocr(), 1, "0");
		String foltes = Util.rellenarCampos(entrada.getFoltes(), 10, "0");
		String coderr = Util.rellenarCampos(entrada.getCoderr(), 1, "0");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("OFIPRO", ofipro);
		map.put("CREFOL", crefol);
		map.put("MONPAG", monpag);
		map.put("TIPOCR", tipocr);
		map.put("FOLTES", foltes);
		map.put("CODERR", coderr);

		RecuperacionAs400DAO proDao = new RecuperacionAs400DAO();
		EntradaSalidaRecuperaVO entradaSalida = proDao.proRecupera(map);

		int statusInt = Integer.parseInt(entradaSalida.getCoderr());

		if (statusInt == 2) {
			throw new Exception("No se logró recuperar el crédito");
		} else {
			logger.debug("Recuperacion exitosa del credito " + ofipro + crefol + " en As400");
			if (statusInt == 1) {
				logger.debug("Quedan reciduos de la recuperacion del credito" + ofipro + crefol + "por un monto = " + entradaSalida.getMonpag());
			}
		}
		return entradaSalida;

	}
}
