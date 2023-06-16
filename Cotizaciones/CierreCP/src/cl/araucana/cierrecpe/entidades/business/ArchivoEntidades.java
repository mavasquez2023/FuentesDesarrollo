

/*
 * @(#) ArchivoEntidades.java    1.0 13/04/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.business;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.entidades.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.entidades.to.ArchivoEntidadTO;
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
 *            <TD> 13/04/2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
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
public class ArchivoEntidades {
	private CPDAO cpDAO=null;
	private static Logger logger = LogManager.getLogger();
	private String ip, usuario, password;
	private String pgmINP, pgmAFP, pgmAPV, pgmCCAF, pgmISAPRE, pgmMUTUAL, pgmINP_DYNP;
	private String bibliotecaCierre, carpetaDestino;
	private String mensajeError;
	protected Properties properties;
	
	public ArchivoEntidades()throws Exception{
			cpDAO= new CPDAO();
			loadProperties("/etc/pwf.properties");
			ip= properties.getProperty("IPPublicador");
			usuario = properties.getProperty("UsuarioAS400");
			password = properties.getProperty("PasswordAS400");
			pgmINP = properties.getProperty("PgmINP");
			pgmINP_DYNP = properties.getProperty("PgmINP_DYNP");
			pgmAFP = properties.getProperty("PgmAFP");
			pgmAPV = properties.getProperty("PgmAPV");
			pgmCCAF = properties.getProperty("PgmCCAF");
			pgmISAPRE = properties.getProperty("PgmISAPRE");
			pgmMUTUAL = properties.getProperty("PgmMUTUAL");
			bibliotecaCierre= properties.getProperty("BibliotecaCierre");
			carpetaDestino= properties.getProperty("CarpetaDestino");
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
	
	
	public int generaArchivoEntidades(int periodo, int cierre, String deposito, String fechaDeposito, Collection secciones) {
		//fechaPago formato ddmmaaaa
		String comando="";
		String formaPago="M"; //Mixta (Cheque)
		String entidadesGeneradas="";
		int numrun=0;
		try {
			boolean ejecuta=false;
			logger.info("Se crea sesión en AS400 para invocar comandos");
			//Se crea sesión en el AS400
			AS400 system = new AS400(ip, usuario, password);
			CommandCall command = new CommandCall(system);
			
			String ssID= command.getServerJob().getNumber();
			String biblioteca= nombreRecurso(bibliotecaCierre, periodo, cierre);
			String bibliotecaDYNP= nombreRecurso(bibliotecaCierre, periodo, 200+cierre);
			String carpeta= nombreRecurso(carpetaDestino, periodo, cierre);
			if(deposito.equals(Constants.DEPOSITO_TRANSFERENCIA) || deposito.equals(Constants.DEPOSITO_TRANSFERENCIA_ABREV)){
				//Full (SPL)
				formaPago="F";
			}
			logger.info("Se invocará la generación de = " + secciones.size() + " archivo entidades");
			for (Iterator seccion = secciones.iterator(); seccion.hasNext();) {
				ArchivoEntidadTO archivoTO = (ArchivoEntidadTO) seccion.next();
				//Se ejecuta comando AS400
				if(archivoTO.getTipoSeccion().equals("INP")){
					//String correlativo= Formato.padding(cierre, 11);
					String param1= Formato.padding(cierre, 2);
					String param2= Formato.padding(cierre, 11);
					//Se ejecuta INP-DYNP
					{
						String param3= bibliotecaDYNP;
						comando = ("call PWOBJD/" + pgmINP_DYNP + " ('" + param1 + "' '" + param2 + "' '" + param3 + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
					}
					if (ejecuta) {
						logger.info("Operación Exitosa para DYNP " + archivoTO.getTipoSeccion());
					}else{
						logger.info("Operación Erronea para DYNP " + archivoTO.getTipoSeccion());
					}
//					Se ejecuta INP
					{
						String param3= biblioteca;
						comando = ("call PWOBJD/" + pgmINP + " ('" + param1 + "' '" + param2 + "' '" + param3 + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
					}
					if (ejecuta) {
						logger.info("Operación Exitosa para " + archivoTO.getTipoSeccion());
					}else{
						logger.info("Operación Erronea para " + archivoTO.getTipoSeccion());
					}
					entidadesGeneradas+= "INP<BR>";
					numrun++;
				}
				else if(archivoTO.getTipoSeccion().equals("CAJA")){
					int i=0;
					for (Iterator entidad = archivoTO.getTipoDetalle().iterator(); entidad.hasNext();) {
						String entidadCCAF = (String) entidad.next();
						comando = ("call PWOBJD/" + pgmCCAF + " ('" + rellena(entidadCCAF,40,'d',' ')  + "' '" + periodo + "' '" + cierre + "' '" + biblioteca + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
						i++;
						if(i==1){
							numrun++;
						}
					}
					entidadesGeneradas+= "CAJA<BR>";
				}	 
				else if(archivoTO.getTipoSeccion().equals("APV")){
					int i=0;
					for (Iterator entidad = archivoTO.getTipoDetalle().iterator(); entidad.hasNext();) {
						String entidadAPV = (String) entidad.next();
						String formato= archivoTO.getFormato();
						if (formato==null){
							formato="TXT";
						}
						//Generando en formato TXT
						formato="TXT";
						comando = ("call PWOBJD/" + pgmAPV + " ('" +  rellena(entidadAPV,40,'d',' ')  + "' '" + periodo + "' '" + cierre + "' '" + biblioteca + "' '" + formato + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
						//Generando en formato CSV
						formato="CSV";
						comando = ("call PWOBJD/" + pgmAPV + " ('" +  rellena(entidadAPV,40,'d',' ')  + "' '" + periodo + "' '" + cierre + "' '" + biblioteca + "' '" + formato + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
						//Generando en formato XLS
						formato="XLS";
						comando = ("call PWOBJD/" + pgmAPV + " ('" +  rellena(entidadAPV,40,'d',' ')  + "' '" + periodo + "' '" + cierre + "' '" + biblioteca + "' '" + formato + "')");
						logger.fine("comando= " + comando);
						ejecuta = command.run(comando);
						i++;
						if(i==1){
							numrun++;
						}
					}
					entidadesGeneradas+= "APV<BR>";
				}
				else if(archivoTO.getTipoSeccion().equals("AFP")){
					comando = ("call PWOBJD/" + pgmAFP + " ('" + biblioteca + "' '" + carpeta + "' '" + formaPago + "')");
					logger.fine("comando= " + comando);
					ejecuta = command.run(comando);
					entidadesGeneradas+= "AFP<BR>";
					numrun++;
				}
				else if(archivoTO.getTipoSeccion().equals("ISAPRE")){
					//Formato FechaDeposito: ddmmaaaa 
					comando = ("call PWOBJD/" + pgmISAPRE + " ('" + biblioteca + "' '" + carpeta + "' '" + fechaDeposito + "')");
					logger.fine("comando= " + comando);
					ejecuta = command.run(comando);
					entidadesGeneradas+= "ISAPRE<BR>";
					numrun++;
				}
				else if(archivoTO.getTipoSeccion().equals("MUTUAL")){
					comando = ("call PWOBJD/" + pgmMUTUAL + " ('" + biblioteca + "' '" + carpeta + "' '" + fechaDeposito + "')");
					logger.fine("comando= " + comando);
					ejecuta = command.run(comando);
					entidadesGeneradas+= "MUTUAL<BR>";
					numrun++;
				}

				if (ejecuta) {
					logger.info("Operación Exitosa para " + archivoTO.getTipoSeccion());
					updatePropuesta(periodo, cierre, archivoTO.getTipoSeccion());
				}else{
					logger.info("Operación Erronea para" + archivoTO.getTipoSeccion());
					//Show the messages (returned whether or not there was an error)
					AS400Message[] messageList = command.getMessageList();
					for (int i = 0; i < messageList.length; ++i)
					{
						//Show each message
						logger.severe(messageList[i].getText());
						//Load additional message information.
						messageList[i].load();
						//Show help text.
						logger.info(messageList[i].getHelp());
					}
				}
			}//fin del for secciones	
			//Se setea las entidades que si generaron el archivo, se utiliza misma variable que los errores.
			setMensajeError(entidadesGeneradas);
					
		} catch(Exception e) {
			logger.severe("ERROR invocando comando AS400, error:" + e.getMessage());
			e.printStackTrace();
			setMensajeError(e.getMessage());
		}
		return numrun;
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
			  esquemaDestino= esquemaDestino.replaceAll("#", String.valueOf(cierre));
			  return esquemaDestino;
		  }
		  
		  public void updatePropuesta(int periodo, int cierre, String tipoSeccion){
				try {
					PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
					propuestaDAO.updateEstado(periodo, cierre, tipoSeccion, 'A');
				}
				catch (SQLException e) {
					logger.severe("Error mensaje=" + e.getMessage());
					e.printStackTrace();
				}
			}
		  
		  public void close(){
				cpDAO.closeConnectionDAO();
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
				properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.entidades.business.ArchivoEntidades.class);
			}
			catch(Exception eproperties)
			{
				logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
				eproperties.printStackTrace();
			}
		}
}

