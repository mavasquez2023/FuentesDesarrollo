package cse.legacy.connection.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;

import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.AS400ProgramExecutionResult;
import cse.system.helper.PropertyLoader;

public class AS400ProgramExecutionImpl implements AS400ProgramExecution {

	private static Logger logger = Logger.getLogger(AS400ProgramExecutionImpl.class.getName());

	private static String PROGRAM_NAME;

	private static String IP_SERVER;

	private static String USER;

	private static String PASSWORD;

	private static String OFICINA_PROCESO;

	private static String TIPO_AFILIADO;

	private ProgramParameter[] parameterList;

	private ProgramCall programCall;

	private AS400 targetSystem;

	public AS400ProgramExecutionImpl() {
		this.loadProperties();
		this.parameterList = new ProgramParameter[3]; // [0]Input[1]Control[2]Output
		this.targetSystem = new AS400(IP_SERVER, USER, PASSWORD);
		this.programCall = new ProgramCall(this.targetSystem);
	}

	protected void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("as400.properties");
		// get the property value and print it out
		PROGRAM_NAME = prop.getProperty("PROGRAM_NAME");
		IP_SERVER = prop.getProperty("IP_SERVER");
		USER = prop.getProperty("USER");
		PASSWORD = prop.getProperty("PASSWORD");
		OFICINA_PROCESO = prop.getProperty("OFICINA_PROCESO");
		TIPO_AFILIADO = prop.getProperty("TIPO_AFILIADO");
	}

	/*
	 * ## Parametros de Control (2 Longitud) Campo Formato Largo Observaciones
	 * Flag-1 numérico 1,0 0 = Parametros Correctos; 1 = Parametros Erróneos
	 * Flag-2 numérico 1,0 0 = Rutina Exitosa; 1 = Rutina no exitosa
	 */
	private void initControlParameters() {
		parameterList[1] = new ProgramParameter(2);
	}

	private void initOutputParameters() {
		parameterList[2] = new ProgramParameter(165);
	}

	/*
	 * Parametros de Entrada (114 Longitud) Campo Formato Largo Observaciones
	 * Rut numérico 9,0 DV caracter 1 OficinaProceso numérico 3,0 TipoAfiliado
	 * numérico 1,0 Disponibles caracter 100 (Disponible para usos futuros)
	 */
	private void setInputParameters(String rut, String digitoChequeo) {
		StringBuffer rawDataBuffer = new StringBuffer(StringUtils.leftPad(rut, 9, '0') + digitoChequeo);
		rawDataBuffer.append(StringUtils.leftPad(OFICINA_PROCESO,3, '0'));
		rawDataBuffer.append(TIPO_AFILIADO);
		String inputData = rawDataBuffer.toString();
		String rawDataString = StringUtils.rightPad(inputData, 114);
		AS400Text nametext = new AS400Text(114);
		parameterList[0] = new ProgramParameter(nametext.toBytes(rawDataString));
	}

	private String readControlParameters() {
		AS400Text controlParameters = new AS400Text(2);
		byte[] outputData = parameterList[1].getOutputData();
		String outputDataObject = (String) controlParameters.toObject(outputData);	
		return outputDataObject;
	}

	/*
	 * Parametros de Salida (165 Longitud) Campo Formato Largo Observaciones
	 * Género caracter 1 M, F Edad numérico 8,0 aaaammdd Estado civil caracter 1
	 * C, S, V Remuneración numérico 12,0 Monto nominal numérico 9,0 Días de
	 * morosidad numérico 8,0 aaaammdd Créditos anteriores numérico 2,0 Licencia
	 * médica numérico 5,0 Antigüedad laboral numérico 8,0 aaaammdd Rut
	 * empleador numérico 9,0 Clasif Riesgo empresa caracter 2 Campos
	 * disponibles caracter 100 Disponible para usos futuros
	 */
	private String readOutputParameters() {
		// Lectura del parametro de salida
		AS400Text text = new AS400Text(165);
		byte[] outputData = parameterList[2].getOutputData();
		String outputDataObject = (String) text.toObject(outputData);
		return outputDataObject;
	}

	public AS400ProgramExecutionResult execute(String rut, String digito)
			throws AS400ProgramExecutionException {
		logger.entering(this.getClass().getName(), "execute", new Object[] { rut, digito });
		try {			
			initControlParameters();
			initOutputParameters();
			setInputParameters(rut, digito);
			boolean success = callProgram();
			AS400ProgramExecutionResult result = new AS400ProgramExecutionResult();
			if (success) {				
				String control = readControlParameters();
				logger.log(Level.FINE, "Parametros de Control retornados :", control);
				if (control.endsWith("0")) {
					result.setSuccess(true);
					String output = readOutputParameters();
					result.setResultData(output);
					logger.log(Level.INFO, "Resultado optimo de consulta AS400 para " + rut +"-" +digito  );
				} else {
					List errors = new ArrayList();
					errors.add("Rutina no exitosa.");
					result.setSuccess(false);
					if (control.startsWith("0")) {						
						errors.add("Parametros correctos.");						
						AS400Message[] messagelist = programCall.getMessageList();						
						for (int i = 0; i < messagelist.length; ++i) {
							errors.add(messagelist[i].getText());
						}						
					} else {						
						errors.add("Parametros incorrectos.");						
						AS400Message[] messagelist = programCall.getMessageList();						
						for (int i = 0; i < messagelist.length; ++i) {
							errors.add(messagelist[i].getText());
						}						
					}
					logger.log(Level.WARNING, "Resultado suboptimo de consulta AS400 para " + rut +"-" +digito + ". Errores retornados :", errors);
					result.setErrorMessages(errors);
				}
			} else {				
				result.setSuccess(false);
				AS400Message[] messagelist = programCall.getMessageList();
				List errorMessages = new ArrayList();
				for (int i = 0; i < messagelist.length; ++i) {
					errorMessages.add(messagelist[i].getText());
				}
				logger.log(Level.WARNING, "Resultado fallido de consulta AS400 para " + rut +"-" +digito + ". Errores retornados :", errorMessages);				
				result.setErrorMessages(errorMessages);
			}
			logger.exiting(this.getClass().getName(), "execute", result);
			System.out.println("el mensaje =========== "+result.getResultData());
			return result;
		} finally {
			// Done with the system			
			targetSystem.disconnectAllServices();
		}

	}

	private boolean callProgram() throws AS400ProgramExecutionException {
		boolean resultExecution = false;
		try {
			logger.log(Level.FINE, "Invocando " +PROGRAM_NAME+ " con ", new Object[]{parameterList});			
			programCall.setProgram(PROGRAM_NAME, parameterList);			
			resultExecution = programCall.run();
			logger.log(Level.FINE, "Invocación exitosa.");			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Problemas en la ejecucion del programa: " + PROGRAM_NAME + " sobre el servidor: " + IP_SERVER);
			throw new AS400ProgramExecutionException("Problemas en la ejecucion del programa:  "
					+ PROGRAM_NAME + " sobre el servidor: " + IP_SERVER, exc);
		}
		return resultExecution;
	}

}
