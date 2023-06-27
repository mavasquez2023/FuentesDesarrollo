package cl.araucana.cp.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400FTP;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.IFSJavaFile;

import cl.araucana.core.business.BusinessException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;
import cl.araucana.pdf.publishers.Scope;

public class PublicadorPDF
{
	static Logger logger = Logger.getLogger(PublicadorPDF.class);

	/*
	 * se recibe una lista de indices y una lista de rutas para acceder a archivos a publicar.
 	 * se necesitan los parametros:
	 *  - publisherName,
	 *  - docTypeName,
	 *  - idLogPDF,
	 *  - periodo,
	 */
	public static void publica(ParametrosHash paramHash, List indices, List pathNames) throws Exception
	{
		
		PDFPublisher pdfPublisher = null;
		int result = 1;
		boolean commit = false;

		if (indices == null || pathNames == null || indices.size() != pathNames.size())
			throw new Exception("parametros indices/pathNames invalidos");
		int ndate = Scope.convertToNDate(paramHash.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE) + "01");
		Scope replaceModeScope = new Scope("Periodo = " + ndate);
		logger.debug("periodo:" + paramHash.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE) + "::");
		System.out.println("publisher:" + paramHash.get("" + Constants.PARAM_PUBLISHER_NAME) + "::");

		try
		{
			PublisherManager publisherManager = PublisherManager.getInstance();
			Publisher publisher = publisherManager.getPublisher(paramHash.get("" + Constants.PARAM_PUBLISHER_NAME));
			
			if (publisher == null)
				throw new Exception("publicador desconocido:'" + paramHash.get("" + Constants.PARAM_PUBLISHER_NAME) + "'");

			PublishOptions publishOptions = new PublishOptions();
			publishOptions.setPublisher(publisher);
			publishOptions.setBatchMode(true);
			publishOptions.setPartitioned(false);
			publishOptions.setCompressed(true);
			publishOptions.setReplaceMode(PublishOptions.MODE_REPLACE);
			publishOptions.setLogged(true);
			publishOptions.setLogID(paramHash.get("" + Constants.PARAM_ID_LOG_PDF));

			pdfPublisher = PDFPublisher.newPDFPublisher(paramHash.get("" + Constants.PARAM_DOC_TYPE_NAME_PDF), 0, publishOptions);

			/*
			 * Defines replace mode scope to control duplicated docIDs.
			 */
			pdfPublisher.setReplaceModeScope(replaceModeScope);

			/*
			 * Initiates a new publication transaction.
			 */
			pdfPublisher.reset();

			for (int i = 0; i < indices.size(); i++)
			{
				String docIndex = (String)indices.get(i);
				String docPathName = (String)pathNames.get(i);

				logger.debug("Publishing " + "\"" + docIndex + "\" -> " + docPathName + " ...\n");
				pdfPublisher.publish(docIndex, docPathName);
			}

			commit = true;
		} catch (Exception e)
		{
			logger.error("error publicando PDF", e);
		} finally
		{
			if (pdfPublisher != null)
			{
				try
				{
					if (commit)
					{
						pdfPublisher.flush();
						logger.info("Publication OK.");
					} else
					{
						pdfPublisher.discard();
						logger.error("Publication FAILED.");
						result = -1;
					}
					pdfPublisher.close();
				} catch (PDFPublisherException e)
				{
					logger.error("error publicando PDF", e);
					result = -1;
				}
			}
			if (result != 1)
				throw new Exception("error durante la publicacion");
		}
	}
	
	 /**
	 * envia a OnDemand
	 * 
	 * Registro de Versiones:<ul>
	 * <li>20/02/2012 [gmallea - schema ltda.]: Versiï¿½n Inicial</li>
	 * </ul><p> 
	 * @param  nominasPorPagarVO
	 * @param String rutEmpresa 
	 * @param String periodo
	 * @param String razonSocial 
	 * @param String codigoBarra
	 * @throws BusinessException
	 * @throws Exception
	 */
	 public void generarEnvioOnDemand(String rutEmpresa ,String periodo, String razonSocial , String codigoBarra) throws Exception {
		
		 	logger.debug("generarEnvioOnDemand -> 	Generar Envio OnDemand");
	        boolean estado = false;
	         estado = this.generarArchivoIndices(rutEmpresa,periodo,razonSocial,codigoBarra); 
	
	        if (estado){
	            String rutaRemota = Constants.REMOTE_FOLDER_ONDEMAND;
	            estado = this.cargarArchivosOnDemand(rutaRemota + Constants.INDICE_ONDEMAND);
	        }
	        
	 }
	 
	 /**
	     * Genera Archivo de ï¿½ndices para la carga de pdf
	     *
	     *  Registro de Versiones:<ul>
		 * <li>20/02/2012 [gmallea - schema ltda.]: Versiï¿½n Inicial</li>
		 * </ul><p> 
		 * 
	     * @param String rutEmpresa
	     * @param String periodo
	     * @param String razonSocial
	     * @param String codigoBarra
	     * @return estado 
	 	 * @throws IOException 
	 	 * @throws AS400SecurityException 
	     */
	    public boolean generarArchivoIndices(String rutEmpresa ,String periodo, String razonSocial, String codigoBarra) throws AS400SecurityException, IOException {
	    	
	        boolean estado = true;
	        String rutaRemota = Constants.REMOTE_FOLDER_ONDEMAND;
	        
	        String buffer = "";
	        buffer += "FIELD NAMES BEGIN:";
	        buffer += System.getProperty("line.separator");
	        buffer += "Periodo";
	        buffer += System.getProperty("line.separator");
	        buffer += "CodigoBarra";
	        buffer += System.getProperty("line.separator");
	        buffer += "RutEmpresa";
	        buffer += System.getProperty("line.separator");
	        buffer += "RazonSocial";
	        buffer += System.getProperty("line.separator");
	        buffer += "Holding";
	        buffer += System.getProperty("line.separator");
	        buffer += "FIELD NAMES END:";
	        buffer += System.getProperty("line.separator");
	        	
	        		buffer += periodo;
		            buffer += System.getProperty("line.separator");
		            buffer += codigoBarra;
		            buffer += System.getProperty("line.separator");	            
		            buffer += rutEmpresa;
		            buffer += System.getProperty("line.separator");
		            buffer += razonSocial.trim();
		            buffer += System.getProperty("line.separator");
		            buffer += "Holding";
		            buffer += System.getProperty("line.separator");
		            buffer += rutaRemota +"Formulario_" + rutEmpresa + "-" + Utils.generaDV(new Integer(rutEmpresa).intValue()) + "_" + "IPS" + "_" + "Independiente"+".pdf";
		            buffer += System.getProperty("line.separator");
		            buffer += "0";
		            buffer += System.getProperty("line.separator");
		            buffer += "0";
		            buffer += System.getProperty("line.separator");
	        
	        
	        FileOutputStream fileoutstream = null;

	        try {
	            fileoutstream = new FileOutputStream(Constants.INDICE_ONDEMAND);
	        } catch (FileNotFoundException e) {
	            estado = false;
	            logger.debug("generarArchivoIndices ->  FileNotFoundException:"+ e.getMessage());
	            
	        }
	        DataOutputStream dos = new DataOutputStream(fileoutstream);
	        try {
	            dos.writeBytes(buffer);
	            dos.flush();
	            dos.close(); 
	        
	        } catch (IOException e1) {
	            estado = false;
	            logger.debug("generarArchivoIndices ->  IOException: "+ e1.getMessage());
	        }
	        
	        if (estado){
		        
	        	estado = this.prepararOndemand(Constants.INDICE_ONDEMAND, "", rutaRemota);
	        }

	        return estado;
	    }
	    
	    /**
		 * Metodo para subir archivos a Ondemand
		 *
		 * Registro de Versiones:<ul>
		 * <li>20/02/2012 [gmallea - schema ltda.]: Versiï¿½n Inicial</li>
		 * </ul><p>
		 * 
		 * @param String nombreArchivo
		 * @param String rutaLocal 
		 * @param String rutaRemota
		 * @return boolean
		 * @throws AS400SecurityException
		 * @throws IOException
		 *
		*/ 
		
		private boolean prepararOndemand(String nombreArchivo,String rutaLocal,String rutaRemota)throws AS400SecurityException,IOException{
		
			boolean estado =false;
			
				ConexionAS400VO conexion = new ConexionAS400VO();
	            conexion.setSistema( Constants.SERVIDOR_ONDEMAND );
	            conexion.setUser( Constants.USUARIO_ONDEMAND);
	            conexion.setPsw( Constants.PASSWORD_ONDEMAND) ;            
	            estado = this.cargarArchivoIFS(nombreArchivo,rutaLocal,rutaRemota,conexion);
	      
	        return estado;
	    }
		
		/**
	     * @see ServiciosDAO#cargarArchivoIFS(String file,String rutaOrigen,String rutaDestino, ConexionAS400VO conexion)
	     */
	    public boolean cargarArchivoIFS(String file,String rutaOrigen,String rutaDestino, ConexionAS400VO conexion) throws AS400SecurityException, IOException {
	        boolean estado = false;
	        logger.debug("cargarArchivoIFS -> cargando archivo vía FTP, " + "Nombre: " + file + " Ruta Origen: " + rutaOrigen + " Ruta Destino: " + rutaDestino + " Sistema: " +conexion.getSistema());
	        AS400 as400 = new AS400(conexion.getSistema(),conexion.getUser(),conexion.getPsw());
	        AS400FTP ftp = new AS400FTP(as400);
	        File fileObject = new File(file);
	        
	        estado = ftp.put(fileObject, rutaDestino + file);
	        logger.debug("cargarArchivoIFS -> cargando archivo vía FTP estado = " + estado); 
	        return estado;
	    }
	    
	    /**
	     * @see ServiciosDAO#cargarArchivosOnDemand(String pathArchivoIndice)
	     */
	    public boolean cargarArchivosOnDemand(String pathArchivoIndice) throws Exception, BusinessException {
	   	 AS400 as400 = new AS400();
	 	
	 	try {
	         String sistema = Constants.SERVIDOR_ONDEMAND;
	         String usuario = Constants.USUARIO_ONDEMAND;
	         String password = Constants.PASSWORD_ONDEMAND;
		        String grupoAplicaciones = Constants.GRUPO_APLICACIONES;
		        String aplicacion = Constants.APLICACION;
	         //   Se define variable de archivo indice
	         String pathIndice = pathArchivoIndice.substring(0, pathArchivoIndice.lastIndexOf("."));
	         AS400 as4002 = new AS400(sistema, usuario, password);
	         CommandCall command = new CommandCall(as4002);
	         String cmd = "QSH CMD('arsload -h " + sistema + " -u " + usuario + " -p " + password + " -g \""
	         + grupoAplicaciones + "\" -a \"" + aplicacion + "\"" + " " + pathIndice + "')";
	         logger.debug("cargarArchivosOnDemand -> cmd: " +  cmd);
	         boolean ejecuta = command.run(cmd);
					
	         if (!ejecuta || existFile(as4002, pathArchivoIndice)) {
	             AS400Message[] messagelist = command.getMessageList();
	             for (int i = 0; i < messagelist.length; i++) {
	             	System.out.println();
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].toString());
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].getText());
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].getSeverity());
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].getHelp());
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].getFileName());
	             	System.out.println("cargarArchivosOnDemand ->  : " + messagelist[i].getType());
	             }
	             as400.disconnectService(AS400.COMMAND);
	             return false;
	         } else {
	             AS400Message[] messages = command.getMessageList();
	             for (int i = 0; i < messages.length; i++) {
	                 logger.debug("cargarArchivosOnDemand -> ejecución correcta : " + messages[i].toString());
	             }
	         }

	         as400.disconnectService(AS400.COMMAND);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return false;
	     }
	     return true;
	    }
	    
	    /**
	     * Comprueba que exista el archivo en el sistema IFS.
	     * 
	     * @param system
	     * @param pathfile
	     * @return estado
	     */
	    private boolean existFile(AS400 system, String pathfile) {
	        File file;
	        if (system == null) {
	            file = new File(pathfile);
	        } else {
	            file = new IFSJavaFile(system, pathfile);
	        }
	        return file.exists();
	    }


}
