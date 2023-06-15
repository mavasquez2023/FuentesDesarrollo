package cl.laaraucana.botonpago.web.cobol.call;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaFolioVO;
import cl.laaraucana.botonpago.web.database.dao.ProcedimientosDao;
import cl.laaraucana.botonpago.web.utils.Util;

public class ConsultaFolioCall {
	private Logger logger = Logger.getLogger(this.getClass());

	public EntradaSalidaFolioVO consultarFolio(EntradaSalidaFolioVO entrada) throws Exception {

		HashMap<String, String> params = new HashMap<String, String>();

		String status = Util.rellenarCampos(entrada.getStatus(), 7, "0");
		String codigo = Util.rellenarCampos(entrada.getCodigo(), 3, "0"); //deberia venir con un 1
		String codigo2 = Util.rellenarCampos(entrada.getCodigo2(), 3, "0");
		String folio = Util.rellenarCampos(entrada.getFolio(), 9, "0");

		params.put("status", status);
		params.put("codigo", codigo);
		params.put("codigo2", codigo2);
		params.put("folio", folio);

		ProcedimientosDao proDao = new ProcedimientosDao();
		EntradaSalidaFolioVO entradaSalidaFolio = proDao.proFOLIO(params);

		int statusInt = Integer.parseInt(entradaSalidaFolio.getStatus());

		if (statusInt == 99) {
			logger.error("al consultar nuevo folio de tesoreria");
			throw new Exception("Error al obtener el folio");
		}

		return entradaSalidaFolio;

	}

}
