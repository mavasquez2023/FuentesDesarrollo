package cse.legacy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.legacy.connection.impl.AS400ProgramExecutionMock;
import cse.legacy.dao.DatosDemograficosDAO;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.legacy.dao.cache.impl.DataBoardAgentImpl;
import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.Rut;


public class DatosDemograficosDAOMock implements DatosDemograficosDAO {

    private static Logger logger = Logger.getLogger(DatosDemograficosDAOMock.class.getName());
    
	public DatosDemograficos findByRut(String numero, String digitoChequeo) throws AS400ProgramExecutionException {
	    logger.entering(this.getClass().getName(), "findByRut" );
	    
	    DatosDemograficos datosDemograficos; 
		
		Rut rut = new Rut(numero,digitoChequeo);
		DataBoardAgent dataBoardAgent = DataBoardAgentImpl.getInstance();
		String rawData = dataBoardAgent.lookupAS400StringData(rut);
		if (rawData == null){
		    AS400ProgramExecution as400ProgramCall = new AS400ProgramExecutionMock();
		    AS400ProgramExecutionResult result = as400ProgramCall.execute(numero, digitoChequeo);
			if (result.isSuccess()){
			    rawData = result.getResultData();
			    dataBoardAgent.publishAS400StringData(rut,rawData);
			} else {
				List errorMessages = result.getErrorMessages();
				if (errorMessages != null && errorMessages.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (Iterator iter = errorMessages.iterator(); iter
							.hasNext();) {
						String element = (String) iter.next();
						sb.append(element);
					}
					throw new AS400ProgramExecutionException(sb.toString());
				}
			}			  
		} 
		datosDemograficos = parseData2Demograficos(rawData);
		logger.exiting(this.getClass().getName(), "findByRut", datosDemograficos);	
		return datosDemograficos;
	}

	private DatosDemograficos parseData2Demograficos(String resultData) {
		DatosDemograficos demograficos = new DatosDemograficos();
//		creditosAnteriores = resultData.substring(beginIndex, endIndex);
		demograficos.setCreditosAnteriores((short) 1);
//		estadoCivil = resultData.substring(beginIndex, endIndex);
		demograficos.setEstadoCivil(cse.model.util.Constants.CASADO);
//		fechaMorosidad = resultData.substring(beginIndex, endIndex);
		demograficos.setFechaMorosidad("20111224");
//		fechaNacimiento = resultData.substring(beginIndex, endIndex);
		demograficos.setFechaNacimiento("19590302");
//		String genero = resultData.substring(0, 1);
		demograficos.setGenero("1");
//		remuneracion = resultData.substring(beginIndex, endIndex);
		demograficos.setRemuneracion(545454);
		return demograficos;
	}

}
