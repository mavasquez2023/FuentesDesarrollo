package cse.legacy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.legacy.connection.impl.AS400ProgramExecutionMock;
import cse.legacy.dao.ClasificacionRiesgoDAO;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.legacy.dao.cache.impl.DataBoardAgentImpl;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;
import cse.model.businessobject.Rut;


public class ClasificacionRiesgoDAOMock implements ClasificacionRiesgoDAO {

	private static Logger logger = Logger.getLogger(ClasificacionRiesgoDAOMock.class.getName());
	
	public ClasificacionRiesgoEmpleador findByRut(String numero, String digitoChequeo, int tipoAfiliado)
			throws AS400ProgramExecutionException {
	    logger.entering(this.getClass().getName(), "findByRut" );    
	    ClasificacionRiesgoEmpleador riesgoEmpleador;
	    
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
		riesgoEmpleador = parseData2RiesgoEmpleador(rawData);
		logger.exiting(this.getClass().getName(), "findByRut", riesgoEmpleador);	
		return riesgoEmpleador;
		
	}

	private ClasificacionRiesgoEmpleador parseData2RiesgoEmpleador(String resultData) {
		ClasificacionRiesgoEmpleador riesgo = new ClasificacionRiesgoEmpleador();
		riesgo.setNombre("NB");
		riesgo.setDescripcion("Descripcion Riesgo Mock");
		return riesgo;
	}

}
