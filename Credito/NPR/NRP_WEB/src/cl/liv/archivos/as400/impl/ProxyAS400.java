package cl.liv.archivos.as400.impl;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;

import cl.liv.comun.utiles.PropertiesUtil;



public class ProxyAS400
{
	private Logger logger = Logger.getLogger(ProxyAS400.class);
	private SimpleDateFormat dateFormatAll = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyyMMdd");
	private AS400 as400 = null;
	private QSYSObjectPathName programNameFolio;
	private QSYSObjectPathName programNameCodigoBarra;

	private static  ProxyAS400 instancia = null;
	
	public ProxyAS400()
	{
		super();
		this.as400 = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"),
				PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.user"), 
				PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.password"));
		this.programNameFolio = new QSYSObjectPathName(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.programa.folio"));
		this.programNameCodigoBarra = new QSYSObjectPathName(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.programa.codigo.barra"));
	}
	


	public AS400 getAs400() {
		return as400;
	}

	public void setAs400(AS400 as400) {
		this.as400 = as400;
	}

	public static ProxyAS400 getInstancia(){
		if(instancia == null || ( instancia != null && instancia.getAs400() == null )){
			instancia = new ProxyAS400();
			
		}
		
		return instancia;
	}
	
	public Integer obtenerFolio () 
	{
		
		
		String resultadoFinal="error"; 
		String stringResultado="         ";
		int largoMaximomensaje = 9;
		
		try
		{  
			
			QSYSObjectPathName nombrePrograma = this.programNameFolio;

			ProgramParameter[] parmlist = new ProgramParameter[1];

			AS400Text textoResultado = new AS400Text(largoMaximomensaje, this.as400);
			byte[] bTextoResultado = textoResultado.toBytes(stringResultado);
			parmlist[0] = new ProgramParameter(bTextoResultado,largoMaximomensaje);

			//Create the program call object
			ProgramCall getSystemStatus =  new ProgramCall(this.as400, nombrePrograma.getPath(), parmlist);
			//Execute the program
			if(getSystemStatus.run() != true)
			{
				AS400Message [] msgList =getSystemStatus.getMessageList();
				for (int i = 0; i < msgList.length; i++)
					this.logger.info(msgList[i].getText());
				this.as400.disconnectService(AS400.COMMAND);
				resultadoFinal = "El programa no se ejecuto";
			} else
			{
				byte [] rbTextoResultado = parmlist[0].getOutputData();
				resultadoFinal = (String) textoResultado.toObject(rbTextoResultado);
				this.as400.disconnectService(AS400.COMMAND);
				return new Integer(resultadoFinal);
			}			
		}catch(Exception error)
		{
			this.logger.error("problemas anulando folio", error);
			error.printStackTrace();
		}			
		return null;
	
	
}

	
	public String obtenerCodigoBarra () 
	{
		String resultadoFinal="error"; 
		
		try
		{  
			QSYSObjectPathName nombrePrograma = this.programNameCodigoBarra;
			ProgramParameter[] parmlist = new ProgramParameter[2];
			AS400Text textoResultado = new AS400Text(3, this.as400);
			byte[] bTextoResultado = textoResultado.toBytes("200");
			parmlist[0] = new ProgramParameter(bTextoResultado,3);
			

			AS400Text codigoBarra = new AS400Text(13, this.as400);
			byte[] bCodigoBarra = codigoBarra.toBytes("             ");
			parmlist[1] = new ProgramParameter(bCodigoBarra,14);

			//Create the program call object
			ProgramCall getSystemStatus =  new ProgramCall(this.as400, nombrePrograma.getPath(), parmlist);

			//Execute the program
			if(getSystemStatus.run() != true)
			{
				AS400Message [] msgList =getSystemStatus.getMessageList();
				for (int i = 0; i < msgList.length; i++)
					this.logger.info(msgList[i].getText());
				this.as400.disconnectService(AS400.COMMAND);
				resultadoFinal = "El programa no se ejecuto";
			} else
			{
				byte [] rbTextoResultado = parmlist[1].getOutputData();
				resultadoFinal = (String) codigoBarra.toObject(rbTextoResultado);
				this.as400.disconnectService(AS400.COMMAND);
				return (resultadoFinal);
			}			
		}catch(Exception error)
		{
			this.logger.error("problemas anulando folio", error);
			error.printStackTrace();
		}			
		return null;
	
	
}
	
	
	
}