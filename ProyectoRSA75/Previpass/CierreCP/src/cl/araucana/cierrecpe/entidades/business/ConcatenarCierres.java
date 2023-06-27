

/*
 * @(#) ArchivoEntidades.java    1.0 13/04/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.business;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import lotus.domino.*;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.entidades.dao.ConcatenarCierresDAO;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 10/04/2015 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo007@gmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ConcatenarCierres {
	private static Logger logger = LogManager.getLogger();
	private String ip, usuario, password;
	private String bibliotecaCierre;
	private String mensajeError;
	protected Properties properties;
	private CPDAO cpDAO=null;
	
	public ConcatenarCierres()throws Exception{
			loadProperties("/etc/pwf.properties");
			ip= properties.getProperty("IPPublicador");
			usuario = properties.getProperty("UsuarioAS400");
			password = properties.getProperty("PasswordAS400");
			bibliotecaCierre= properties.getProperty("BibliotecaCierre");
			/*
			Context ctx = new InitialContext();			
			//Se rescata DataSource de conexión a BD
			ip = (String)ctx.lookup("java:comp/env/IPPublicador");
			usuario = (String)ctx.lookup("java:comp/env/UsuarioAS400");
			password = (String)ctx.lookup("java:comp/env/PasswordAS400");
			pgmINP = (String)ctx.lookup("java:comp/env/PgmINP");
			pgmAFP = (String)ctx.lookup("java:comp/env/PgmAFP");
			pgmAPV = (String)ctx.lookup("java:comp/env/PgmAPV");
			pgmCCAF = (String)ctx.lookup("java:comp/env/PgmCCAF");
			bibliotecaCierre= (String) ctx.lookup("java:comp/env/BibliotecaCierre");
			bibliotecaArchivos= (String) ctx.lookup("java:comp/env/BibliotecaArchivos");
			carpetaDestino= (String) ctx.lookup("java:comp/env/CarpetaDestino");
			*/
	}
	
	public List getCierresDomino(String periodo){
		String ipDomino, usuarioDomino, passwordDomino, vistaDomino;
		List cierres= null;
		try {
			loadProperties("/etc/domino.properties");
			ipDomino= properties.getProperty("IPDomino");
			usuarioDomino= properties.getProperty("UsuarioDomino");
			passwordDomino= properties.getProperty("PasswordDomino");
			vistaDomino= "Cierres";
			logger.fine("Abriendo sesión Notes ip:" + ipDomino + ", usuario: " + usuarioDomino);
			Session s = NotesFactory.createSession(ipDomino, usuarioDomino, passwordDomino);
			logger.fine("Abriendo DB Cotiza/Cotiza.nsf:");
			Database db	= 	s.getDatabase(s.getServerName(), "Cotiza/Cotiza.nsf");
			ConcatenarCierresDAO cierresDAO= new ConcatenarCierresDAO(db);
			cierres= cierresDAO.selectDomino(vistaDomino, periodo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return cierres;
	}
	
	public List getCierresPrevipass(String periodo){
		List cierres= null;
		try {
			cpDAO= new CPDAO();
			ConcatenarCierresDAO cierresDAO= new ConcatenarCierresDAO(cpDAO.getConnection());
			cierres= cierresDAO.selectPrevipass(periodo);
			cpDAO.closeConnectionDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cierres;
	}
	
	public boolean concatenarCierres(int periodo, String[] cierres) {

		String comando="";
		int numrun=0;
		StringBuffer resultado= new StringBuffer();
		boolean fin= true;
		try {
			boolean ejecuta=false;
			logger.info("Se crea sesión en AS400 para invocar comandos");
			//Se crea sesión en el AS400
			AS400 system = new AS400(ip, usuario, password);
			CommandCall command = new CommandCall(system);
			String ssID= command.getServerJob().getNumber();
			
			String libtarget=nombreRecurso(bibliotecaCierre, periodo, 999);
			
			
			if(cierres[0].equals("1")){
				//Eliminando biblioteca de Cierre Concatenado en caso incluya cierre 1, sino se asume que existe y se concatena
				comando= ("DLTLIB LIB(" + libtarget + ")");
				logger.info("comando= " + comando);
				ejecuta = command.run(comando);
				if (ejecuta) {
					logger.info("Operación Exitosa para Eliminar biblioteca: " + libtarget);
				}else{
					logger.info("Operación Erronea para Eliminar biblioteca: " + libtarget);
				}
				
				//Se copia libreria
				comando= ("CPYLIB FROMLIB(PWDTADIA) TOLIB(" + libtarget + ")");
				logger.info("comando= " + comando);
				ejecuta = command.run(comando);
				if (ejecuta) {
					logger.info("Operación Exitosa al Copiar biblioteca: " + libtarget);
				}else{
					logger.info("Operación Erronea al Copiar biblioteca: " + libtarget);
				}
			}

			for (int i = 0; i < cierres.length; i++) {
				String cierre= cierres[i];
				String libsource= nombreRecurso(bibliotecaCierre, periodo, Integer.parseInt(cierre));
				
				comando= ("CALL PWOBJD/CPYSIGMAPX ('" + libsource + "' '" + libtarget + "')");
				logger.info("comando= " + comando);
				ejecuta = command.run(comando);
				if (ejecuta) {
					logger.info("Operación Exitosa para Concatenar Cierre: " + cierre);
					resultado.append("Cierre " + cierre + ": OK <br>");
				}else{
					logger.info("Operación Erronea para Concatenar Cierre: " + cierre);
					resultado.append("Cierre " + cierre + ": Con Observación <br>");
					AS400Message[] messageList = command.getMessageList();
					for (int j = 0; j < messageList.length; ++j)
					{
						//Show each message
						logger.info(messageList[j].getText());
						resultado.append("Detalle Error: " + messageList[j].getText() + "<br>");
						//Load additional message information.
						messageList[j].load();
						//Show help text.
						logger.info(messageList[j].getHelp());
					}
					/*if(Integer.parseInt(cierre)==1){
						fin= false;
						break;
					}*/
				}
			}
			
		} catch(Exception e) {
			logger.severe("ERROR invocando comando AS400, error:" + e.getMessage());
			e.printStackTrace();
			setMensajeError(e.getMessage());
			fin= false;
		}
		setMensajeError(resultado.toString());
		return fin;
	}
		
		
		  public String rellena( String valor, int largo,  char l , char car) {
				// valor rellena con 0(car) a la izquierda(l='i'), ejemplo : valor =8 largo =5 -> 00008
				String aux, aux1, aux2;
				int i;
				aux	=	 valor.trim();
				aux1	=	"";
				if ( aux.length() > 0 )
					aux2	= valor.substring(0,1);
				if ( aux.length() > largo ) { // trunco si es mayor
					return aux.substring(0,largo);			
				}
				
				for (i=1;i<=largo -aux.length();i++)
					aux1= aux1+car;
				if ( l=='i' )
					return aux1.concat(aux);
				else
					return aux.concat(aux1);
	     }

		  public String nombreRecurso(String esquemaFormato, int periodo, int cierre){
			  String esquemaDestino= esquemaFormato.replaceAll("AAMM", String.valueOf(periodo).substring(2, 6));
			  esquemaDestino= esquemaDestino.replaceAll("XXX", Formato.padding(cierre, 3));
			  return esquemaDestino;
		  }
		  


		/**
		 * @return el mensajeError
		 */
		public String getMensajeError() {
			return mensajeError;
		}


		/**
		 * @param mensajeError el mensajeError a establecer
		 */
		public void setMensajeError(String mensajeError) {
			this.mensajeError = mensajeError;
		}
		 
		private void loadProperties(String fileproperties){
			PropertiesLoader propertiesloader = new PropertiesLoader();
			try
			{
				properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.entidades.business.ConcatenarCierres.class);
			}
			catch(Exception eproperties)
			{
				logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
				eproperties.printStackTrace();
			}
		}
}

