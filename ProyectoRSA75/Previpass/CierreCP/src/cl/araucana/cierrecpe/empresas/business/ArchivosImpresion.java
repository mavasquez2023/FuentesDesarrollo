

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

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;
import com.ibm.as400.access.AS400;
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
public class ArchivosImpresion {
	private CPDAO cpDAO=null;
	private static Logger logger = LogManager.getLogger();
	private String ip, usuario, password;
	private String pgmASICOM;
	private String bibliotecaCierre, bibliotecaArchivos, carpetaDestino;
	private String mensajeError="";
	protected Properties properties;
	
	public ArchivosImpresion() throws Exception{
			cpDAO= new CPDAO();
			loadProperties("/etc/pwf.properties");
			ip= properties.getProperty("IPPublicador");
			usuario = properties.getProperty("UsuarioAS400");
			password = properties.getProperty("PasswordAS400");
			pgmASICOM = properties.getProperty("PgmASICOM");
			bibliotecaCierre= properties.getProperty("BibliotecaCierre");
			bibliotecaArchivos= properties.getProperty("BibliotecaArchivos");
			carpetaDestino= properties.getProperty("CarpetaDestino");
	}

	public boolean generaArchivoImpresion(int periodo, int cierre, String deposito) {
		String comando="";
		String formaPago="M";
		boolean ejecuta=false;
		try {
			//logger.info("<<<<<<COMIENZA generaArchivosImpresión>>>>>>");
			logger.info("Se crea sesión en AS400 para invocar comandos");
			//Se crea sesión en el AS400
			AS400 system = new AS400(ip, usuario, password);
			CommandCall command = new CommandCall(system);

			String ssID= command.getServerJob().getNumber();
			String biblioteca= nombreRecurso(bibliotecaCierre, periodo, cierre);
			String bibliotecaArch= nombreRecurso(bibliotecaArchivos, periodo, cierre);
			String carpeta= nombreRecurso(carpetaDestino, periodo, cierre);
			if(deposito.equals(Constants.DEPOSITO_TRANSFERENCIA)){
				formaPago="F";
			}
			
			comando = ("call PWOBJD/" + pgmASICOM + " ('" + biblioteca + "' '" + bibliotecaArch + "' '" + carpeta +  "' '" + formaPago + "') ");
			logger.fine("comando= " + comando);
			int estadoproc=0;
			ejecuta = command.run(comando);
			
			if (ejecuta) {
				logger.info("Operación Exitosa para ASICOM");
				estadoproc=1;
				
			}else{
				logger.info("Operación Erronea para ASICOM");
			}			
			//se actualiza tabla de resumen ed procesos
			updateArchivosImpresion(periodo, cierre, estadoproc);
			
					
		} catch(Exception e) {
			logger.severe("ERROR invocando comando AS400, error:" + e.getMessage());
			e.printStackTrace();
			ejecuta=false;
			setMensajeError(e.getMessage());
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
		  
		  public void updateArchivosImpresion(int periodo, int cierre, int estado) throws SQLException{
				
					ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
					resumenDAO.updateArchivosImpresion(periodo, cierre, estado);
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
				properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.ArchivosImpresion.class);
			}
			catch(Exception eproperties)
			{
				logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
				eproperties.printStackTrace();
			}
		}
}

