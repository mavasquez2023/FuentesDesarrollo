package cse.legacy.connection.impl;

import java.util.logging.Logger;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;

public class AS400ProgramExecutionMock implements AS400ProgramExecution {

	private static Logger logger = Logger.getLogger(AS400ProgramExecutionMock.class.getName());
	
	public AS400ProgramExecutionResult execute(String rut, String digito)
			throws AS400ProgramExecutionException {
		logger.entering(this.getClass().getName(), "execute", new Object[]{rut, digito});
		AS400ProgramExecutionResult result = new AS400ProgramExecutionResult();
		result.setSuccess(true);
		String data = "M19790721C0000000000000000000000000000010000000000000000000000   0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		result.setResultData(data);
		logger.exiting(this.getClass().getName(), "execute", data);
		return result;
	}

}
