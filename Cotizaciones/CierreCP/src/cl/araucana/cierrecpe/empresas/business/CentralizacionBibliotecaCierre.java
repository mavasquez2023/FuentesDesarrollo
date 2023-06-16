

/*
 * @(#) ArchivoEntidades.java    1.0 13/04/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;


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
public class CentralizacionBibliotecaCierre extends Thread {
	private CPDAO cpDAO=null;
	private static Logger logger = LogManager.getLogger();
	private String ip, usuario, password;
	private String pgmCEN_REMU, pgmCEN_GRATI;
	private String bibliotecaCopy, bibliotecaCentralizacion;
	private boolean running;
	private String mensajeError="";
	protected Properties properties;
	
	public CentralizacionBibliotecaCierre()throws Exception{
			cpDAO= new CPDAO();			
			loadProperties("/etc/pwf.properties");
			ip= properties.getProperty("IPPublicador");
			usuario = properties.getProperty("UsuarioAS400");
			password = properties.getProperty("PasswordAS400");
			pgmCEN_REMU = properties.getProperty("PgmCEN_REMU");
			pgmCEN_GRATI = properties.getProperty("PgmCEN_GRATI");
			bibliotecaCopy= properties.getProperty("BibliotecaCierre");
			bibliotecaCentralizacion= properties.getProperty("BibliotecaCentralizacion");
			/*
			Context ctx = new InitialContext();			
			//Se rescata DataSource de conexión a BD
			ip = (String)ctx.lookup("java:comp/env/IPPublicador");
			usuario = (String)ctx.lookup("java:comp/env/UsuarioAS400");
			password = (String)ctx.lookup("java:comp/env/PasswordAS400");
			pgmCEN_REMU = (String)ctx.lookup("java:comp/env/PgmCEN_REMU");
			pgmCEN_GRATI = (String)ctx.lookup("java:comp/env/PgmCEN_GRATI");
			bibliotecaCopy= (String) ctx.lookup("java:comp/env/BibliotecaCierre");
			bibliotecaCentralizacion= (String) ctx.lookup("java:comp/env/BibliotecaCentralizacion");
			 */
	}
	
	
	public boolean centralizar(int periodo, int cierre, int rutEmpresa) {
		String comando="";
		boolean ejecuta=false;
		DuplicarEsquemaPW centralizar=null;
		try {
			logger.info("Se crea sesión en AS400 para invocar comandos");
			String rutformat= Formato.paddingLeft(String.valueOf(rutEmpresa), 9, ' ');
			//S traspasa información de biblioteca de cierre a biblioteca centralizada
			centralizar= new DuplicarEsquemaPW(ip, usuario, password);
			String bibliotecaCierre= nombreRecurso(bibliotecaCopy, periodo, cierre);
			ejecuta= centralizar.copyFiles(bibliotecaCierre, bibliotecaCentralizacion, "REPLACE", "CP");
			//Se ejecutan comandos para centralizar Remu y Grati
			if (ejecuta){
				//Centralización Remu
				comando = ("CALL PWOBJD/" + pgmCEN_REMU + " PARM('" + rutformat + "' '" + periodo + "' '" + bibliotecaCierre + "')");
				logger.fine("comando= " + comando);
				int estadoproc=0;
				ejecuta = centralizar.runCommand(comando);

				if (ejecuta) {
					estadoproc=1;
					logger.info("Operación Exitosa para Centralización Remuneraciones");
				}else{
					logger.info("Operación Erronea para Centralización Remuneraciones");
				}
				//se actualiza tabla de resumen ed procesos
				updateCentralización(periodo, cierre, 'R', estadoproc);
				
				//centralización Grati.
				comando = ("CALL PWOBJD/" + pgmCEN_GRATI + " PARM('" + rutformat + "' '" + periodo + "' '" + bibliotecaCierre + "')");
				logger.fine("comando= " + comando);
				estadoproc=0;
				ejecuta = centralizar.runCommand(comando);

				if (ejecuta) {
					estadoproc=1;
					logger.info("Operación Exitosa para Centralización Gratificaciones");
				}else{
					logger.info("Operación Erronea para Centralización Gratificaciones");
				}
				//se actualiza tabla de resumen de procesos
				updateCentralización(periodo, cierre, 'G', estadoproc);
			}
					
		} catch(Exception e) {
			logger.severe("ERROR invocando comando AS400, error:" + e.getMessage());
			e.printStackTrace();
			ejecuta=false;
			setMensajeError(e.getMessage());
		}
		finally{
			if (centralizar!=null){
				centralizar.close();
			}
		}
		return ejecuta;
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
		  
		  public void updateCentralización(int periodo, int cierre, char tipoNomina, int estado ){
				try {
					ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
					resumenDAO.updateCentralizacion(periodo, cierre, tipoNomina, estado);
					logger.fine("Actualizanzo campo 'tgr' de resumen cierre, campo Nuevo Convenio Caducado, por defecto va -1");
					resumenDAO.updateTGR(periodo, cierre, tipoNomina, estado, -1);
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
		 * @return el running
		 */
		public boolean isRunning() {
			return running;
		}

		/**
		 * @param running el running a establecer
		 */
		public void setRunning(boolean running) {
			this.running = running;
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
				properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.CentralizacionBibliotecaCierre.class);
			}
			catch(Exception eproperties)
			{
				logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
				eproperties.printStackTrace();
			}
		}
}

