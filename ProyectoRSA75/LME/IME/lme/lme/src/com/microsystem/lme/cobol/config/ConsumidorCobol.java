package com.microsystem.lme.cobol.config;

import java.io.IOException;
import java.math.BigDecimal;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400PackedDecimal;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.ws.exception.PropertyVetoException;
import com.microsystem.lme.cobol.bo.ParametrosConexionBO;
import com.microsystem.lme.cobol.bo.ParametrosLlamadaBO;

public class ConsumidorCobol {

	private static ParametrosLlamadaBO[] parametros = null;

	//private static LoggerHelper logger = new LoggerHelper(); 

	public static ParametrosLlamadaBO[] call(ParametrosConexionBO conexion, ParametrosLlamadaBO[] _parametros) {

		parametros = _parametros;
		AS400Config.configure(conexion);
		QSYSObjectPathName programName = new QSYSObjectPathName(conexion.getPrograma());
		ProgramParameter[] parametrosCall = null;

		try {

			parametrosCall = obtenerParametros();

		} catch (PropertyVetoException e) {
			//logger.logError("Error al procesar los parametros...", e);
		}

		ProgramCall getSystemStatus = new ProgramCall(AS400Config.getAs400(), programName.getPath(), parametrosCall);

		try {

			if (getSystemStatus.run() != true) {

				//logger.logInfo("ha ocurrido un problema al ejecutar el programa "+ conexion.getPrograma()+ ",\n "+ getSystemStatus.getMessageList()[0].getText());
			} else {

				//logger.logInfo("La ejecucion del programa "+ conexion.getPrograma()+", se realizo exitosamente");

				for (int i = 0; i < parametros.length; i++) {

					if (parametros[i].getDireccion().equals("OUT")) {

						try {

							byte[] rbTextoResultado = getSystemStatus.getParameterList()[i].getOutputData();
							String result = (String) parametros[i].getTextoAS400().toObject(rbTextoResultado);
							parametros[i].setValor(result);

						}

						catch (Exception e) {

						}
					}
				}
			}

		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}

		finally {

			AS400Config.getAs400().disconnectService(AS400.COMMAND);
			//logger.logInfo("Conexion cerrada..");
		}

		return parametros;
	}

	private static ProgramParameter[] obtenerParametros() throws PropertyVetoException {
		ProgramParameter[] parametrosCall = new ProgramParameter[parametros.length];

		for (int i = 0; i < parametros.length; i++) {

			if (parametros[i].getTipo().equals("INTEGER")) {

				BigDecimal valor = new BigDecimal(0);
				parametrosCall[i] = new ProgramParameter(new AS400PackedDecimal(parametros[i].getLargo(), 0).toBytes(valor), parametros[i].getLargo());

			}

			else if (parametros[i].getTipo().equals("STRING")) {

				parametros[i].setTextoAS400(new AS400Text(parametros[i].getLargo(), AS400Config.getAs400()));
				byte[] bTextoResultado = parametros[i].getTextoAS400().toBytes(parametros[i].getValor());
				parametrosCall[i] = new ProgramParameter(bTextoResultado, parametros[i].getLargo());

			}
		}

		return parametrosCall;
	}
}
