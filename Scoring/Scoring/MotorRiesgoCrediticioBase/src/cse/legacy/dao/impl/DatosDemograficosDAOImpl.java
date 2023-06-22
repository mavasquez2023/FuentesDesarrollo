package cse.legacy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.legacy.connection.impl.AS400ProgramExecutionImpl;
import cse.legacy.dao.DatosDemograficosDAO;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.legacy.dao.cache.impl.DataBoardAgentImpl;
import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.Rut;

public class DatosDemograficosDAOImpl implements DatosDemograficosDAO {

	private static Logger logger = Logger
			.getLogger(DatosDemograficosDAOImpl.class.getName());

	public DatosDemograficos findByRut(String numero, String digitoChequeo)
			throws AS400ProgramExecutionException {
		logger.entering(this.getClass().getName(), "findByRut");
		DatosDemograficos datosDemograficos;
		Rut rut = new Rut(numero, digitoChequeo);
		DataBoardAgent dataBoardAgent = DataBoardAgentImpl.getInstance();
		String rawData = dataBoardAgent.lookupAS400StringData(rut);
		if (rawData == null) {
			AS400ProgramExecution as400ProgramCall = new AS400ProgramExecutionImpl();
			AS400ProgramExecutionResult result = as400ProgramCall.execute(
					numero, digitoChequeo);
			if (result.isSuccess()) {
				rawData = result.getResultData();
				if (rawData != null){
					dataBoardAgent.publishAS400StringData(rut, rawData);	
				} else {
					throw new AS400ProgramExecutionException("Resultado vacio para DatosDemograficos de :  "+  numero +"-"+digitoChequeo  );
				}
			} else {
				List errorMessages = result.getErrorMessages();
				if (errorMessages != null && errorMessages.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (Iterator iter = errorMessages.iterator(); iter
							.hasNext();) {						
						Object element = iter.next();
						sb.append(" - " + element);
					}
					throw new AS400ProgramExecutionException("Resultado suboptimo para DatosDemograficos de :  "+  numero +"-"+digitoChequeo +". Errores:  "+ sb.toString());
				}
				throw new AS400ProgramExecutionException("Resultado suboptimo para DatosDemograficos de :  "+  numero +"-"+digitoChequeo  );
			}		
		}
		datosDemograficos = parseData2Demograficos(rawData);
		logger.exiting(this.getClass().getName(), "findByRut",
				datosDemograficos);
		return datosDemograficos;
	}

	private DatosDemograficos parseData2Demograficos(String resultData) {
		DatosDemograficos demograficos = new DatosDemograficos();

		String creditosAnteriores = resultData.substring(39, 41);
		demograficos
				.setCreditosAnteriores(Short.parseShort(creditosAnteriores));

		String estadoCivil = resultData.substring(9, 10);
		demograficos.setEstadoCivil(estadoCivil);

		String fechaMorosidad = resultData.substring(31, 39);
		demograficos.setFechaMorosidad(fechaMorosidad);

		String fechaNacimiento = resultData.substring(1, 9);
		demograficos.setFechaNacimiento(fechaNacimiento);

		String genero = resultData.substring(0, 1);
		demograficos.setGenero(genero);

		String remuneracion = resultData.substring(10, 22);
		demograficos.setRemuneracion(Double.parseDouble(remuneracion));

		return demograficos;
	}

}
