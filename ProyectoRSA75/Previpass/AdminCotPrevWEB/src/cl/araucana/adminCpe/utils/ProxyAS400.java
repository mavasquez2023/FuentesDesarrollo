package cl.araucana.adminCpe.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import cl.araucana.core.business.BusinessException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.util.vo.Folio;
import cl.araucana.cp.util.vo.PagoEnLinea;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400PackedDecimal;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;

/*
 * @(#) ProxyAS400.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.7
 */
public class ProxyAS400
{
	private static Logger log = Logger.getLogger(ProxyAS400.class);

	private SimpleDateFormat dateFormatAll = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyyMMdd");
	private AS400 as400;
	private QSYSObjectPathName programName;

	public ProxyAS400()
	{
		super();
	}

	/**
	 * Anula un comprobante en Tesoreria
	 * 
	 * @param       String sistema           String programa           String user           String psw
	 * @return String:           Si es blanco OK           Si tiene info es error
	 */
	public static String anularComprobante(long folio, ParametrosHash paramHash) throws Exception
	{
		String resultadoFinal = "error";
		log.info("\n\nanulando Folio: " + folio + "::");
		// Preparo variable de resultado con "largoMaximomensaje" blancos
		String stringResultado = "";
		int largoMaximoFolio = 10, largoMaximomensaje = 70;
		for (int x = 0; x < largoMaximomensaje; x++)
			stringResultado = stringResultado + " ";
		try
		{
			AS400 as400 = new AS400(paramHash.get("" + Constants.PARAM_INT_TES_URL_SISTEMA), paramHash.get("" + Constants.PARAM_INT_TES_USER_CONN), 
					paramHash.get("" + Constants.PARAM_INT_TES_PASS_CONN));
			// Seteo programa
			QSYSObjectPathName programName = new QSYSObjectPathName(paramHash.get("" + Constants.PARAM_LIB_ANULA_FOLIO));

			ProgramParameter[] parmlist = new ProgramParameter[2];

			// Seteo el folio
			//BigDecimal bFolio = new BigDecimal(folio);
			BigDecimal bFolio = BigDecimal.valueOf(folio); 
			parmlist[0] = new ProgramParameter(new AS400PackedDecimal(largoMaximoFolio, 0).toBytes(bFolio), largoMaximoFolio);

			AS400Text textoResultado = new AS400Text(largoMaximomensaje, as400);
			byte[] bTextoResultado = textoResultado.toBytes(stringResultado);
			parmlist[1] = new ProgramParameter(bTextoResultado, largoMaximomensaje);

			// Create the program call object
			ProgramCall getSystemStatus = new ProgramCall(as400, programName.getPath(), parmlist);

			// Execute the program
			if (getSystemStatus.run() != true)
			{
				AS400Message[] msgList = getSystemStatus.getMessageList();
				log.info("El programa no se ejecuta. Server messages: ");
				for (int i = 0; i < msgList.length; i++)
					log.info(msgList[i].getText());
				as400.disconnectService(AS400.COMMAND);
				resultadoFinal = "El programa no se ejecuto";
			} else
			{
				byte[] rbTextoResultado = parmlist[1].getOutputData();
				resultadoFinal = (String) textoResultado.toObject(rbTextoResultado);
				log.info("Respuesta:" + resultadoFinal + ":: (si vacio = OK)");
				as400.disconnectService(AS400.COMMAND);
			}
		} catch (Exception error)
		{
			log.error("error en anulacion comprobante:", error);
		}
		return resultadoFinal;
	}

	/**
	 * main
	 * 
	 * @param args
	 * @throws BusinessException
	 * @throws Exception
	 */
	public static void main(String[] args) throws BusinessException, Exception
	{
		ParametrosHash paramSPL = new ParametrosHash();
		paramSPL.add("urlSistema", "146.83.1.2");
		paramSPL.add("userConn", "Desacpe");
		paramSPL.add("passConn", "Desacpe");
		paramSPL.add("libAnulaFolio", "/QSYS.LIB/TEOBJ.LIB/TEC037.PGM");

		BasicConfigurator.configure();
		ProxyAS400.anularComprobante(17800865, paramSPL);
	}

	/**
	 * pago linea
	 * 
	 * @param empresa
	 * @param comprobante
	 * @param totalCodigo
	 * @param paramSPL
	 * @param pago
	 * @return
	 * @throws NumberFormatException
	 * @throws AS400SecurityException
	 * @throws ErrorCompletingRequestException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ObjectDoesNotExistException
	 */
	public PagoEnLinea getFolio(EmpresaVO empresa, ComprobanteVO comprobante, HashMap totalCodigo, ParametrosHash paramSPL, PagoEnLinea pago) throws NumberFormatException, AS400SecurityException,
			ErrorCompletingRequestException, IOException, InterruptedException, ObjectDoesNotExistException
	{
		String result = "";
		String param = "";

		int nDetalles = 0;
		String detalles = "";
		long totalCmp = 0;
		for (Iterator tt = totalCodigo.keySet().iterator(); tt.hasNext();)
		{
			int idConceptoTes = (new Integer((String) tt.next())).intValue();
			long monto = ((Long) totalCodigo.get("" + idConceptoTes)).longValue();
			log.debug("cod:" + idConceptoTes + "::" + monto + "::");
			if (monto > 0)
			{
				nDetalles++;
				detalles += Utils.rellena(6, "0", idConceptoTes); // codigo concepto tesoreria
				detalles += "0"; // siempre son pagos por realizar => siempre positivo
				detalles += Utils.rellena(12, "0", monto);// ds.getM(mapeo.getIdMontoDetSeccion())); // monto del detalle
				detalles += Utils.rellena(75, " ", "declarando concepto:" + idConceptoTes + "::");
				totalCmp += monto;
			}
		}

		param = this.dateFormatAll.format(new Date());// fecha y hora
		param += "C";// forma de pago
		param += "C";// tipo pago (cheque)
		param += this.dateFormatYear.format(new Date());// fecha solo valida para egreso, aca no aplica
		param += "N";// no se emite factura
		param += Utils.rellena(3, "0", paramSPL.get("sucursalEmpLCF"));// sucursal empresa (desde tabla parametros)
		param += Utils.rellena(3, "0", paramSPL.get("codOficinaLCF"));// codigo oficina (desde tabla parametros)
		param += Utils.rellena(12, "0", totalCmp);// comprobante.getTotal());// monto total
		param += Utils.rellena(true, 3, "0", paramSPL.get("codAreaNegLCF"));// codigo area de negocio (desde tabla parametros)
		param += Utils.rellena(true, 10, "0", Utils.completaRut(empresa.getIdEmpresa()));// RUT empresa asociada a convenio
		param += Utils.rellena(50, " ", empresa.getRazonSocial()); // nombre de la empresa
		param += Utils.rellena(10, " ", paramSPL.get("userGeneraFolio"));
		param += Utils.rellena(3, "0", nDetalles);// cantidad de lineas
		//param += Utils.rellena(15, "0", comprobante.getIdCodigoBarra()); // codigo de barras del comprobante
		param += Utils.rellena(false, 15, " ", comprobante.getIdCodigoBarra()); // codigo de barras del comprobante
		param += detalles; // detalles

		for (int i = nDetalles; i < 16; i++)// rellena hasta completar 16 detalles
		{
			param += "      0000000000000" + Utils.rellena(75, " ", " ");
		}
		log.info("param:" + param + "::");

		ProgramParameter[] parmlist = new ProgramParameter[3];
		AS400Text textoConsulta = new AS400Text(param.length(), this.as400);
		byte[] bTextoConsulta = textoConsulta.toBytes(param);
		parmlist[0] = new ProgramParameter(bTextoConsulta, param.length());
		// Seteo el resultado
		AS400Text textoResultado = new AS400Text(3, this.as400);
		AS400Text textoResultado2 = new AS400Text(10, this.as400);
		String x = "   ";
		String xx = "          ";
		byte[] bTextoResultado = textoResultado.toBytes(x);
		parmlist[1] = new ProgramParameter(bTextoResultado, 3);
		byte[] bTextoResultado2 = textoResultado2.toBytes(xx);
		parmlist[2] = new ProgramParameter(bTextoResultado2, 10);

		// Create the program call object
		ProgramCall getSystemStatus = new ProgramCall(this.as400, this.programName.getPath(), parmlist);
		// Execute the program
		if (getSystemStatus.run() != true)
		{
			AS400Message[] msgList = getSystemStatus.getMessageList();
			log.info("The program did not run. Server messages: ");
			for (int i = 0; i < msgList.length; i++)
				log.info(msgList[i].getText());
			this.as400.disconnectService(AS400.COMMAND);
			pago.setResult(Constants.AS400_NOT_RUN);
			return pago;
		}
		byte[] rbTextoResultado = parmlist[1].getOutputData();
		byte[] rbTextoResultado2 = parmlist[2].getOutputData();
		String result1 = (String) textoResultado.toObject(rbTextoResultado);

		log.error("Respuesta: " + result1 + "::");

		if (result1.equals("000"))
		{
			result = (String) textoResultado2.toObject(rbTextoResultado2);
			pago.setCorrelativo(paramSPL.get("codSist") + result);
			log.error("\n\nFolio asignado:" + result + "::");
			Folio folio = new Folio();
			folio.setMonto(comprobante.getTotal());
			folio.setDetalle("Nomina para Convenio " + comprobante.getIdCodigoBarra());
			long numFolio = new Long(result).longValue();
			folio.setNumero(numFolio);
			pago.addFolio(folio);
			comprobante.setFolioTesoreria(numFolio);
			pago.setResult("");
		} else
		{
			if (result1.charAt(0) == '1')
				result = Constants.AS400_BAD_PARAMS;
			else if (result1.charAt(1) == '1')
				result = Constants.AS400_BAD_PROCESS;
			pago.setResult(result);
		}
		this.as400.disconnectService(AS400.COMMAND);
		return pago;
	}
}
