package cl.araucana.cp.utils;

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
import cl.araucana.cp.hibernate.beans.SPLPagoVO;
import cl.araucana.cp.presentation.struts.javaBeans.Folio;
import cl.araucana.cp.presentation.struts.javaBeans.PagoEnLinea;

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


public class ProxyAS400
{
	private Logger logger = Logger.getLogger(ProxyAS400.class);
	private SimpleDateFormat dateFormatAll = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyyMMdd");
	private AS400 as400;
	private QSYSObjectPathName programName;

	public ProxyAS400()
	{
		super();
	}

	/* se necesitan los parametros:
	 *  - urlSistema,
	 *  - userConn,
	 *  - passConn,
	 *  - libCreaFolio,
	 */
	public ProxyAS400(ParametrosHash paramHash)
	{
		super();
		this.as400 = new AS400(paramHash.get("" + Constants.PARAM_INT_TES_URL_SISTEMA), paramHash.get("" + Constants.PARAM_INT_TES_USER_CONN), 
				paramHash.get("" + Constants.PARAM_INT_TES_PASS_CONN));
		this.programName = new QSYSObjectPathName(paramHash.get("" + Constants.PARAM_INT_TES_LIB_CREA_FOLIO));
	}

	/* se necesitan los parametros:
	 *  - sucursalEmpLCF,
	 *  - codOficinaLCF,
	 *  - codAreaNegLCF,
	 *  - codSist,
	 */
	public PagoEnLinea getFolio(EmpresaVO empresa, ComprobanteVO comprobante, HashMap totalCodigo, ParametrosHash paramHash, PagoEnLinea pago) throws NumberFormatException, AS400SecurityException, ErrorCompletingRequestException, IOException, InterruptedException, ObjectDoesNotExistException
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
			this.logger.debug("cod:" + idConceptoTes + "::" + monto + "::");
			if (monto > 0)
			{
				nDetalles++;
				detalles += Utils.rellena(6, "0", idConceptoTes); // codigo concepto tesoreria
				detalles += "0"; // siempre son pagos por realizar => siempre positivo
				detalles += Utils.rellena(12, "0", monto);// ds.getM(mapeo.getIdMontoDetSeccion())); // monto del detalle
				detalles += Utils.rellena(75, " ", " ");
				totalCmp += monto;
			}
		}

		param = this.dateFormatAll.format(new Date());// fecha y hora
		param += "C";// forma de pago
		param += "C";// tipo pago (cheque)
		param += this.dateFormatYear.format(new Date());// fecha solo valida para egreso, aca no aplica
		param += "N";// no se emite factura
		param += Utils.rellena(3, "0", paramHash.get("" + Constants.PARAM_INT_TES_SUC_EMP_LCF));// sucursal empresa (desde tabla parametros)
		param += Utils.rellena(3, "0", paramHash.get("" + Constants.PARAM_INT_TES_COD_OF_LCF));// codigo oficina (desde tabla parametros)
		param += Utils.rellena(12, "0", totalCmp);// monto total
		param += Utils.rellena(true, 3, "0", paramHash.get("" + Constants.PARAM_INT_TES_COD_AREA_NEG_LCF));// codigo area de negocio (desde tabla parametros)
		param += Utils.rellena(true, 10, "0", Utils.completaRut(empresa.getIdEmpresa()));// RUT empresa asociada a convenio
		param += Utils.rellena(50, " ", empresa.getRazonSocial()); // nombre de la empresa
		param += Utils.rellena(10, " ", paramHash.get("" + Constants.PARAM_USER_GENERA_FOLIO));
		param += Utils.rellena(3, "0", nDetalles);// cantidad de lineas
		//param += Utils.rellena(15, " ", comprobante.getIdCodigoBarra()); // codigo de barras del comprobante
		param += Utils.rellena(false, 15, " ", comprobante.getIdCodigoBarra()); // codigo de barras del comprobante
		param += detalles; // detalles

		for (int i = nDetalles; i < 16; i++)//rellena hasta completar 16 detalles
		{
			param += "      0000000000000" + Utils.rellena(75, " ", " ");
		}
		this.logger.info("param:" + param + "::");

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
			this.logger.info("The program did not run. Server messages: ");
			for (int i = 0; i < msgList.length; i++)
				this.logger.info(msgList[i].getText());
			this.as400.disconnectService(AS400.COMMAND);
			pago.setResult(Constants.AS400_NOT_RUN);
			return pago;
		}
		{
			byte[] rbTextoResultado = parmlist[1].getOutputData();
			byte[] rbTextoResultado2 = parmlist[2].getOutputData();
			String result1 = (String) textoResultado.toObject(rbTextoResultado);

			this.logger.error("Respuesta: " + result1 + "::");

			if (result1.equals("000"))
			{
				result = (String) textoResultado2.toObject(rbTextoResultado2);
				pago.setCorrelativo(paramHash.get("" + Constants.PARAM_COD_SISTEMA_SPL) + result);
				this.logger.error("\n\nFolio asignado:" + result + "::");
				Folio folio = new Folio();
				folio.setMonto(comprobante.getTotal());
				folio.setDetalle("Nomina para Comprobante " + comprobante.getIdCodigoBarra());
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
		}
		return pago;
	}

	/* se necesitan los parametros:
	 *  - urlSistema,
	 *  - userConn,
	 *  - passConn,
	 *  - libUpLibroBco,
	 */
	public String updateLibroBanco(SPLPagoVO pago, ParametrosHash paramHash)
	{
		String result1 = "1";
		try
		{
			this.as400 = new AS400(paramHash.get("" + Constants.PARAM_INT_TES_URL_SISTEMA), paramHash.get("" + Constants.PARAM_INT_TES_USER_CONN), 
					paramHash.get("" + Constants.PARAM_INT_TES_PASS_CONN));
			this.programName = new QSYSObjectPathName(paramHash.get("" + Constants.PARAM_INT_TES_LIB_UP_LIBRO_BCO));
			ProgramParameter[] parmlist = new ProgramParameter[9];

			AS400Text textoConsulta = new AS400Text(3, this.as400);
			byte[] bTextoConsulta = textoConsulta.toBytes(Utils.rellena(3, "0", pago.getCodBanco()));
			parmlist[0] = new ProgramParameter(bTextoConsulta, 3);

			textoConsulta = new AS400Text(15, this.as400);
			bTextoConsulta = textoConsulta.toBytes(Utils.rellena(15, " ", pago.getCtaCte()));
			parmlist[1] = new ProgramParameter(bTextoConsulta, 15);

			textoConsulta = new AS400Text(8, this.as400);
			bTextoConsulta = textoConsulta.toBytes(this.dateFormatYear.format(pago.getFechaContable()));
			parmlist[2] = new ProgramParameter(bTextoConsulta, 8);

			textoConsulta = new AS400Text(13, this.as400);
			bTextoConsulta = textoConsulta.toBytes(Utils.rellena(13, "0", pago.getMonto()));
			parmlist[3] = new ProgramParameter(bTextoConsulta, 13);

			textoConsulta = new AS400Text(1, this.as400);
			bTextoConsulta = textoConsulta.toBytes("A");
			parmlist[4] = new ProgramParameter(bTextoConsulta, 1);

			textoConsulta = new AS400Text(11, this.as400);
			bTextoConsulta = textoConsulta.toBytes(Utils.rellena(11, "0", pago.getIdTrx()));
			parmlist[5] = new ProgramParameter(bTextoConsulta, 11);

			textoConsulta = new AS400Text(5, this.as400);
			bTextoConsulta = textoConsulta.toBytes(Utils.rellena(5, "0", 90));
			parmlist[6] = new ProgramParameter(bTextoConsulta, 5);

			textoConsulta = new AS400Text(78, this.as400);
			bTextoConsulta = textoConsulta.toBytes(Utils.rellena(78, " ", "Pago de nominas CPE"));
			parmlist[7] = new ProgramParameter(bTextoConsulta, 78);

			textoConsulta = new AS400Text(1, this.as400);
			bTextoConsulta = textoConsulta.toBytes(" ");
			parmlist[8] = new ProgramParameter(bTextoConsulta, 1);

			ProgramCall getSystemStatus = new ProgramCall(this.as400, this.programName.getPath(), parmlist); // Execute the program
			if (getSystemStatus.run() != true)
			{
				AS400Message[] msgList = getSystemStatus.getMessageList();
				this.logger.info("The program did not run. Server messages: ");
				for (int i = 0; i < msgList.length; i++)
					this.logger.info(msgList[i].getText());
			} else
			{
				AS400Text textoResultado = new AS400Text(1, this.as400);
				byte[] rbTextoResultado = parmlist[8].getOutputData();
				result1 = (String) textoResultado.toObject(rbTextoResultado);
				this.logger.info("resultado libro banco:" + result1 + "::");
			}
			this.as400.disconnectService(AS400.COMMAND);
		} catch (Exception error)
		{
			this.logger.error("\n\nComprobanteMgr: ERROR en updateLibroBanco:", error);
		}
		return result1;
	}

	/** 
	* Anula un comprobante en Tesoreria 
	* @param       
	* 			String sistema 
	*           String programa 
	*           String user 
	*           String psw 
	* @return String: 
	*           Si es blanco OK 
	*           Si tiene info es error 
	*/
	public String anularComprobante (long folio, ParametrosHash paramHash) throws Exception
	{
		String resultadoFinal="error"; 
		this.logger.info("\n\nanulando Folio: " + folio + "::"); 
		//Preparo variable de resultado con "largoMaximomensaje" blancos 
		String stringResultado="";
		int largoMaximoFolio = 10, largoMaximomensaje = 70;
		for(int x = 0; x < largoMaximomensaje; x++) 
			stringResultado = stringResultado+" "; 
		try
		{  
			this.as400 = new AS400(paramHash.get("" + Constants.PARAM_INT_TES_URL_SISTEMA), paramHash.get("" + Constants.PARAM_INT_TES_USER_CONN), 
					paramHash.get("" + Constants.PARAM_INT_TES_PASS_CONN));
			//Seteo programa
			QSYSObjectPathName nombrePrograma = new QSYSObjectPathName(paramHash.get("" + Constants.PARAM_LIB_ANULA_FOLIO));

			ProgramParameter[] parmlist = new ProgramParameter[2];

			//Seteo el folio 
			//BigDecimal bFolio = new BigDecimal(folio);
			BigDecimal bFolio = BigDecimal.valueOf(folio);
			parmlist[0] = new ProgramParameter(new AS400PackedDecimal(largoMaximoFolio, 0).toBytes(bFolio), largoMaximoFolio);            

			AS400Text textoResultado = new AS400Text(largoMaximomensaje, this.as400);
			byte[] bTextoResultado = textoResultado.toBytes(stringResultado);
			parmlist[1] = new ProgramParameter(bTextoResultado,largoMaximomensaje);

			//Create the program call object
			ProgramCall getSystemStatus =  new ProgramCall(this.as400, nombrePrograma.getPath(), parmlist);

			//Execute the program
			if(getSystemStatus.run() != true)
			{
				AS400Message [] msgList =getSystemStatus.getMessageList();
				this.logger.info("El programa no se ejecuta. Server messages: ");
				for (int i = 0; i < msgList.length; i++)
					this.logger.info(msgList[i].getText());
				this.as400.disconnectService(AS400.COMMAND);
				resultadoFinal = "El programa no se ejecuto";
			} else
			{
				byte [] rbTextoResultado = parmlist[1].getOutputData();
				resultadoFinal = (String) textoResultado.toObject(rbTextoResultado);
				this.logger.info("Respuesta:" + resultadoFinal + ":: (si vacio = OK)");
				this.as400.disconnectService(AS400.COMMAND);
			}			
		}catch(Exception error)
		{
			this.logger.error("problemas anulando folio", error);
		}			
		return resultadoFinal;
	}

	public static void main(String[] args) throws BusinessException, Exception 
	{
		ParametrosHash paramSPL = new ParametrosHash();
		paramSPL.add("urlSistema", "146.83.1.2");
		paramSPL.add("userConn", "Desacpe");
		paramSPL.add("passConn", "Desacpe");
		paramSPL.add("libAnulaFolio", "/QSYS.LIB/TEOBJ.LIB/TEC037.PGM");

		BasicConfigurator.configure(); 
		ProxyAS400 p = new ProxyAS400();
		p.anularComprobante (17800865, paramSPL);
	}
}	

