package cl.araucana.tesoreria.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400PackedDecimal;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.AS400ZonedDecimal;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;

/**
 * @author asepulveda
 *
 */
public class CobolFolioDAO implements FolioDAO {
	Logger logger = Logger.getLogger(CobolFolioDAO.class);
	private final static String PREFIX="COBOL-";
	
//	private String tesoreriaCobolJNDIDataSource;

	
	HashMap as400list;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public CobolFolioDAO(){
		as400list = new HashMap();
	}
	
	/**
	 * 
	 * @param sistema
	 * @param user
	 * @param psw
	 * @return
	 */
	private AS400 creaConexionAS400(String sistema, String user,String psw) {
		
		logger.debug("Obteniendo conexion AS400 a sistema: " + sistema);
		AS400 as400 = (AS400)as400list.get(sistema);
		if (as400 == null) {
			logger.debug("Creando nueva conexion AS400 a sistema: " + sistema);
			as400 = new AS400(sistema, user, psw);
			as400list.put(sistema, as400);
		}else{
			logger.debug("Usando cache conexion AS400 a sistema: " + sistema);
		}
		return as400;
	}
	 
	/**
	 * Genera un nuevo numero de Folio en Tesoreria
	 * @param	String sistema
	 * 			String programa
	 * 			String user
	 * 			String psw
	 * @return String, numero de folio
	 */
	public String obtenerFolio(String sistema, String programa,String user,String psw) throws Exception, BusinessException {
		String folioResultado=null;
		
		AS400 as400 = this.creaConexionAS400(sistema, user, psw);
		
		BigDecimal packedReturn = new BigDecimal(9.0);

		ProgramParameter[] pList = new ProgramParameter[4];

		BigDecimal status = new BigDecimal(0.0);
		BigDecimal codigo = new BigDecimal(1.0);
		BigDecimal folio = new BigDecimal(0.0);

		pList[0] = new ProgramParameter(new AS400ZonedDecimal(7, 0).toBytes(status), 0);

		pList[1] =
			new ProgramParameter(new AS400PackedDecimal(3, 0).toBytes(codigo), 0);

		pList[2] =
			new ProgramParameter(new AS400PackedDecimal(3, 0).toBytes(codigo), 0);

		pList[3] = new ProgramParameter(new AS400PackedDecimal(9, 0).toBytes(folio), 9);

		QSYSObjectPathName programName = new QSYSObjectPathName(programa);

		// Create the program call object
		ProgramCall inv0011 = new ProgramCall(as400, programName.getPath(), pList);

		// Execute the program

		try {
			if (inv0011.run() == false) { // Handle any errors of the program call failed
				StringBuffer msg = new StringBuffer();
				AS400Message[] msgList = inv0011.getMessageList();
				for (int i = 0; i < msgList.length; i++) {
					msg.append(msgList[i].getText().toString());
				}
				return null;
			} else {
				packedReturn =
					(BigDecimal) new AS400PackedDecimal(9, 0).toObject(pList[3].getOutputData());

			}
			folioResultado=packedReturn.toString();
			return folioResultado;
		} catch (ObjectDoesNotExistException ex) {
		  	ex.printStackTrace();
			int code=ex.getReturnCode();
			throw new BusinessException(PREFIX+code);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
            as400.disconnectService(AS400.COMMAND);
        }
 
		return folioResultado;
	}
	
	/**
	 * Anula un comprobante de Egreso en Tesoreria
	 * @param	String sistema
	 * 			String programa
	 * 			String user
	 * 			String psw
	 *			int largoMaximoFolio
	 *			int largoMaximomensaje
	 * @return String:
	 * 		Si es blanco OK
	 * 		Si tiene info es error
	 */
	public String anularComprobanteEgreso(long folio,
										String sistema,
										String programa,
										String user,
										String psw,
										int largoMaximoFolio,
										int largoMaximomensaje) throws Exception, BusinessException {
		
		AS400 as400 = this.creaConexionAS400(sistema, user, psw);
		
		String resultadoFinal="error";
			
		logger.debug("Folio: " + folio);
		
		//Preparo variable de resultado con "largoMaximomensaje" blancos
		String stringResultado="";
		for(int x=0;x<largoMaximomensaje;x++)
			stringResultado=stringResultado+" ";
		
		try{	
			
			QSYSObjectPathName programName = new QSYSObjectPathName(programa);
	
			ProgramParameter[] parmlist = new ProgramParameter[2];
	
			//Seteo el folio
			BigDecimal bFolio = new BigDecimal(folio);
			parmlist[0] = new ProgramParameter(new AS400PackedDecimal(largoMaximoFolio, 0).toBytes(bFolio), largoMaximoFolio);
	
			//Seteo el resultado
			AS400Text textoResultado = new AS400Text(largoMaximomensaje, as400);
			byte[] bTextoResultado = textoResultado.toBytes(stringResultado);
			parmlist[1] = new ProgramParameter(bTextoResultado, largoMaximomensaje);
			
			// Create the program call object
			ProgramCall getSystemStatus = new ProgramCall(as400, programName.getPath(), parmlist);
	
			// Execute the program
	
			if (getSystemStatus.run() != true) {
				AS400Message[] msgList = getSystemStatus.getMessageList();
				logger.debug("The program did not run.  Server messages: "); 
				for (int i = 0; i < msgList.length; i++) {
					logger.debug(msgList[i].getText());
				}
				as400.disconnectService(AS400.COMMAND);
				resultadoFinal="The program did not run";
	
			} else {
	
				byte[] rbTextoResultado = parmlist[1].getOutputData();
	
				resultadoFinal = (String) textoResultado.toObject(rbTextoResultado);
				
				logger.debug("Respuesta: "+resultadoFinal);
				
				as400.disconnectService(AS400.COMMAND);
				
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
            as400.disconnectService(AS400.COMMAND);
        }
 
		return resultadoFinal;
	}	

}
