package cse.legacy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.legacy.connection.impl.AS400ProgramExecutionImpl;
import cse.legacy.dao.InformacionLaboralDAO;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.legacy.dao.cache.impl.DataBoardAgentImpl;
import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Rut;

public class InformacionLaboralDAOImpl implements InformacionLaboralDAO {


    private static Logger logger = Logger.getLogger(InformacionLaboralDAOImpl.class.getName());
	
	public InformacionLaboral findByRut(String numero, String digitoChequeo)
			throws AS400ProgramExecutionException {
	    
	    logger.entering(this.getClass().getName(), "findByRut" );
	    InformacionLaboral infoLaboral; 
		
	    Rut rut = new Rut(numero,digitoChequeo);
		DataBoardAgent dataBoardAgent = DataBoardAgentImpl.getInstance();
		String rawData = dataBoardAgent.lookupAS400StringData(rut);
		if (rawData == null){
		    AS400ProgramExecution as400ProgramCall = new AS400ProgramExecutionImpl();
		    AS400ProgramExecutionResult result = as400ProgramCall.execute(numero, digitoChequeo);
			if (result.isSuccess()){
			    rawData = result.getResultData();
			    if (rawData != null){
					dataBoardAgent.publishAS400StringData(rut, rawData);	
				} else {
					throw new AS400ProgramExecutionException("Resultado vacio para InformacionLaboral de :  "+  numero +"-"+digitoChequeo  );
				}
			    
			} else {
				List errorMessages = result.getErrorMessages();
				if (errorMessages != null && errorMessages.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (Iterator iter = errorMessages.iterator(); iter
							.hasNext();) {
						String element = (String) iter.next();
						sb.append(" - " + element);
					}
					throw new AS400ProgramExecutionException("Resultado suboptimo para InformacionLaboral de :  "+  numero +"-"+digitoChequeo +". Errores:  "+ sb.toString());
				}
				throw new AS400ProgramExecutionException("Resultado suboptimo para InformacionLaboral de :  "+  numero +"-"+digitoChequeo );
			}			  
		}
		infoLaboral = parseData2Laboral(rawData);
		logger.exiting(this.getClass().getName(), "findByRut", infoLaboral);	
		return infoLaboral;
	}

	private InformacionLaboral parseData2Laboral(String resultData) {
		InformacionLaboral laboral = new InformacionLaboral();
		
		String antiguedadLaboral = resultData.substring(46, 54);
		laboral.setAntiguedadLaboral(antiguedadLaboral);
		
		String licenciasMedicas = resultData.substring(41, 46);
		laboral.setLicenciasMedicas(Short.parseShort(licenciasMedicas));
		
		String rutEmpleador = resultData.substring(54, 62);
		String digitoEmpleador = resultData.substring(62, 63);
		laboral.setRutEmpleador(new Rut(rutEmpleador,digitoEmpleador));		
		
		return laboral;
	}

}
