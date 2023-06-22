package cse.legacy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.legacy.connection.impl.AS400ProgramExecutionMock;
import cse.legacy.dao.InformacionLaboralDAO;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.legacy.dao.cache.impl.DataBoardAgentImpl;
import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Rut;


public class InformacionLaboralDAOMock implements InformacionLaboralDAO {
    
    private static Logger logger = Logger.getLogger(InformacionLaboralDAOMock.class.getName());

	public InformacionLaboral findByRut(String numero, String digitoChequeo)
			throws AS400ProgramExecutionException {
	    
	    logger.entering(this.getClass().getName(), "findByRut" );
	    InformacionLaboral infoLaboral; 
		
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
		infoLaboral = parseData2Laboral(rawData);
		logger.exiting(this.getClass().getName(), "findByRut", infoLaboral);	
		return infoLaboral;
	}

	private InformacionLaboral parseData2Laboral(String resultData) {
		InformacionLaboral laboral = new InformacionLaboral();
//		antiguedadLaboral = resultData.substring(beginIndex, endIndex);
		laboral.setAntiguedadLaboral("20110921");
//		licenciasMedicas = resultData.substring(beginIndex, endIndex);
		laboral.setLicenciasMedicas((short) 12345);
//		rutEmpleador = resultData.substring(beginIndex, endIndex);
		laboral.setRutEmpleador(new Rut("12345678","9"));//		
		return laboral;
	}

}
