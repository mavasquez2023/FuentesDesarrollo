/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.business;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.entidades.dao.ChequeTesoreriaDAO;
import cl.araucana.cierrecpe.entidades.dao.InformeContableDAO;
import cl.araucana.cierrecpe.entidades.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
import cl.recursos.GeneratorXLS;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ChequeEntidades {
static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
private TesoDAO tesoDAO=null;
private CPDAO cpDAO=null;
private int ErrorCode=-1;
private String mensajeError="";
private static Logger logger = LogManager.getLogger();
private int numChequesGenerados=0;
private long totalMontoCheques=0;
//private Context ctx;

	public ChequeEntidades() throws SQLException{
		tesoDAO= new TesoDAO();
		tesoDAO.setAutoCommit(false);
		cpDAO= new CPDAO();
		cpDAO.setAutoCommit(false);
	}
		
	
	public boolean generarCheques(int periodo, int cierre, String option){
    	int folio;
    	final int correlativo=1;
    	try{
    		logger.info("Iniciando generación de cheques en Tesorería, periodo: " + periodo + ", cierre: " + cierre);
    		PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
    		ChequeTesoreriaDAO chequeDAO = new ChequeTesoreriaDAO(tesoDAO.getConnection());
    		Collection list_cheques= (List)propuestaDAO.selectNoCargados(periodo, cierre, option);
    		logger.info("Numero de cheques a generar:" + list_cheques.size());
    		//System.out.println("Numero de cheques a generar:" + list_cheques.size());
    		//Se borra tabla de Recaudación
    		if(option.equalsIgnoreCase("del")){
    			logger.info("Borrando tabla Recaudación RE50F1");
    			chequeDAO.deleteRecaudacion();
    		}
    		for (Iterator itercheque = list_cheques.iterator(); itercheque.hasNext();) {
    			PropuestaPagoTO chequeTO = (PropuestaPagoTO) itercheque.next();
    			//Se genera Cheque según parámetro en tabla ENTPAGAD cuando es SPL.
    			//No se genera cheque para la Caja en ningún caso.
    			if(chequeTO.isGeneraEgreso() && chequeTO.getMontoTotal()>0){
    				if(!option.equalsIgnoreCase("paso")){
    					//Se solicita un folio para asignar el folio de egreso del cheque
    					Folio tesoreria= Folio.getInstance();
    					folio=Integer.parseInt(tesoreria.getFolio());
    					logger.info("PropuestaCheques.cargarCheques, folio retornado:" + folio);
    					chequeTO.setFolioTemporal(chequeTO.getFolioEgreso());
    					chequeTO.setFolioEgreso(folio);

    					//Se inserta cheque en Tesorería
    					chequeDAO.insert(chequeTO);
    					logger.info("Cabecera te07f1 ok");
    					chequeDAO.insertDetalle(chequeTO, correlativo);
    					logger.info("Cabecera te07f2 ok");
    				}
    				//Se inserta cheque en recaudación en REDTA.RE50F1
    				chequeDAO.insertRecaudacion(chequeTO);
    				
    				logger.info("Recaudación re50f1 ok");
    				//Se actualiza el folio de egreso en propuesta de Cheques en CP
    				chequeTO.setEstado('C');
    				chequeTO.setDeposito(Constants.DEPOSITO_CHEQUE);
    				propuestaDAO.update(chequeTO);
    				logger.info("Update estado Propuesta 'C'");
    				this.totalMontoCheques+= chequeTO.getMontoTotal();
    				this.numChequesGenerados++;
    			}else if(chequeTO.getMontoTotal()>0){
    				propuestaDAO.updateEstado(chequeTO, 'T', Constants.DEPOSITO_TRANSFERENCIA);
    				logger.info("Update Estado Propuesta 'T', no genera egreso en Tesorería");
    			}
    		}    		
    		ErrorCode = 0;
    		return true;
    	}catch(Exception ex){
    		logger.severe("Error mensaje=" + ex.getMessage());
    		mensajeError= ex.getMessage();
    		ErrorCode = 1;
    		ex.printStackTrace();
    		return false;
    	}
}
	public int countRegistrosBalance(){
		int count=0;
		try {
			InformeContableDAO informeDAO= new InformeContableDAO(tesoDAO.getConnection());
			count= informeDAO.select();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return count;
	}
	
	public int generarInformeContable(int periodo, String option){
		PropuestaPagoEntidades propuesta=null;
		int countInsert=0;
		int numtransferencias=0;
    	try{
    		logger.info("Iniciando Informe Contable, periodo: " + periodo + ", opcion registros:" + option);
    		InformeContableDAO informeDAO= new InformeContableDAO(tesoDAO.getConnection());
    		propuesta= new PropuestaPagoEntidades(true);
    		Collection list_cheques= (List)propuesta.getObjPropuestaPago(periodo, 0);
    		propuesta.close();
    		    		
    		//Se borra tabla de Informe Contable
    		if(option.equalsIgnoreCase("del")){
    			logger.fine("Limpiando tabla Informe COntable");
    			informeDAO.delete(null);
    		}
    		logger.fine("Insertando solo los cheques....( de un total de:" + list_cheques.size() + ")");
    		for (Iterator itercheque = list_cheques.iterator(); itercheque.hasNext();) {
    			PropuestaPagoTO chequeTO = (PropuestaPagoTO) itercheque.next();
    			if(chequeTO.getDeposito().equals(Constants.DEPOSITO_CHEQUE)){
    				//Se inserta cheque en Informe Contable
    				countInsert+=informeDAO.insert(chequeTO);
    			}else{
    				//Se cuantifica si hay transferencias
    				numtransferencias++;
    			}
    		}
    		logger.info("Total cheques cargados:" + countInsert);
    		//Se cambia el estado de los cheques a Informado(I)
    		if(countInsert>0){
    			cambiarEstadoPropuestaPago(list_cheques, 'I');
    		}
    		if(numtransferencias>0){
    			enviarInformeContablexCorreo(periodo);
    		}
    		ErrorCode = 0;
    		
    	}
    	catch(Exception ex){
    		logger.severe("Error mensaje=" + ex.getMessage());
    		ErrorCode = 1;
    		ex.printStackTrace();
    		setMensajeError(ex.getMessage());
    		countInsert=0;
    	}
    	
    	return countInsert;
    }
	
	public void enviarInformeContablexCorreo(int periodo){
		
		logger.fine("Seleccionando Transferencias para Enviar por Correo");

		//Generando la salida
		
		String emails;
		String filename= "Informe Contable Transferencias " + periodo + ".xls";
		String pathfile= "/cp/cierre/" + filename;
		try {
			//Obteniendo los datos de Transferencias
			PropuestaPagoDAO informeDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			List transferencias= (List)informeDAO.select(periodo);
			
			logger.fine("Se generará salida IC en: " + pathfile);
			OutputStream out = new FileOutputStream(pathfile);
			PrintStream flujo= new PrintStream(out);

			GeneratorXLS xls= new GeneratorXLS(flujo);

			//Configurando columnas a desplegar y titulos de estas.
			String[] columnas={"periodo", "clave", "conceptoTesoreria", "rutEntidad", "folioEgreso", "tipoNomina", "montoEgreso", "rutEmpresa", "folioIngreso", "montoIngreso"};
			String[] titulos={"Período", "Clave", "Concepto Tesorería", "Rut Entidad", "Folio Egreso", "Tipo Nómina", "Monto Egreso", "Rut Empresa", "Folio Ingreso", "Monto Ingreso"};
			xls.generarXLSfromCollection(transferencias, columnas, titulos, "006777");
			logger.fine("Archivo " + pathfile + " ha sido generado.");
			//Cerrando salida
			out.flush();
			out.close();
			
			//Enviar Correo con excel adjunto
			logger.fine("Enviando Propuesta por correo.");
			//rescatando parametros correo de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			emails = paramTO.getEmailUsuario();
			enviarMail(Formato.split(emails, ";"), periodo, pathfile);
			logger.fine("Correo de IC enviado con exito");
			
		}catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		

	}
	
	public void cambiarEstadoPropuestaPago(Collection list_cheques, char estado) throws SQLException {
		PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
		for (Iterator itercheque = list_cheques.iterator(); itercheque.hasNext();) {
			PropuestaPagoTO chequeTO = (PropuestaPagoTO) itercheque.next();
			propuestaDAO.updateEstado(chequeTO, estado);
		}	
			
	}
	 
	 public void commit() throws SQLException{
	 	switch (ErrorCode) {
		case 0:
			logger.info("PropustaCheques, haciendo commit de la transaccion");
			tesoDAO.commit();
			cpDAO.commit();
			break;
		case 1:
			logger.warning("PropustaCheques, haciendo rollback de la transaccion");
			tesoDAO.rollback();
			cpDAO.rollback();
			break;
		default:
			break;
		}
	 }
	 
	 public void close() throws SQLException{
		 tesoDAO.closeConnectionDAO();
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
	
//	Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail( String[] mailEncargados, int periodo, String adjunto) {
    	String subject="";
		try {			

			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
			StringBuffer body= new  StringBuffer();
			subject= "Informe Contable TRANSFERENCIAS Previpass" ;
			body.append("Señor Usuario: : se adjunta archivo con Informe Contable de las <b>Transferencias</b> realizadas para periodo:<b>" + periodo+ "</b><BR>");
			body.append("<br><br>");
			body.append("Saluda atte. a Ud. "+"<BR>");
			body.append("Previpass - Cotización Electrónica.");
			body.append("La Araucana - Soluciones Sociales.");
			mail.attach(adjunto);
			mail.mailTo("aplica@laaraucana.cl", mailEncargados, null, null , subject, body.toString());

			System.out.println(".............. EMAIL GENERADO .................... " );

			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }


	/**
	 * @return el numChequesGenerados
	 */
	public int getNumChequesGenerados() {
		return numChequesGenerados;
	}


	/**
	 * @param numChequesGenerados el numChequesGenerados a establecer
	 */
	public void setNumChequesGenerados(int numChequesGenerados) {
		this.numChequesGenerados = numChequesGenerados;
	}

	/**
	 * @return el totalMontoCheques
	 */
	public long getTotalMontoCheques() {
		return totalMontoCheques;
	}


	/**
	 * @param totalMontoCheques el totalMontoCheques a establecer
	 */
	public void setTotalMontoCheques(long totalMontoCheques) {
		this.totalMontoCheques = totalMontoCheques;
	}

}
