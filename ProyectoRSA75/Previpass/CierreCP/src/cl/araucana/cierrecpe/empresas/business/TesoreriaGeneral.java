

/*
 * @(#) GenerarPlanillas.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.empresas.dao.tgr.ComprobantesCPDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesPensionDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.cierrecpe.empresas.dao.RecaudacionTesoreriaGeneralDAO;
import cl.araucana.cierrecpe.empresas.business.NuevoConvenio;
import cl.araucana.cierrecpe.empresas.business.TrabajadorTGR;
import cl.araucana.cierrecpe.empresas.to.tgr.ComprobantesTO;
import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;


/*
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
 *            <TD> 21-07-2009 </TD>
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
 * Clase CADUCADA por REQ-7435 Nuevo Formato Servicio TGR que apunta directo a tablas de Centralización
 */
public class TesoreriaGeneral{
	private CPDAO cpDAO=null;
	private TesoDAO tesoDAO=null;
	private static Logger logger = LogManager.getLogger();
	private int numeroComprobantesConTG=0;
	private int totalTrabajadoresConTG=0;
	private int numeroConveniosNuevos=0;
	private String mensajeError="";
	
	public TesoreriaGeneral() throws SQLException{
		cpDAO= new CPDAO();
		cpDAO.setAutoCommit(false);
		tesoDAO= new TesoDAO();
		tesoDAO.setAutoCommit(false);
	}
	
	
	public boolean generarTesoriaGeneral(int periodo, int cierre){
		boolean termino=true;
		Collection comprobantes= null;
		
		int numeroComprobantesErroneos=0;
		try {
			//se abre una conexión a la BD
			ComprobantesCPDAO comprobantesDAO= new ComprobantesCPDAO(cpDAO.getConnection());
			ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
			//se buscan los comprobantes pagados de Remuneraciones y Gratificaciones asociados al cierre
			logger.info("Comienza generación  Tesoreria General de la Republica periodo:" + periodo + ", cierre:" + cierre);
			comprobantes= (List) comprobantesDAO.select(periodo, cierre);
			logger.info("Número comprobantes a procesar: " + comprobantes.size());
			Map codigosTGR= comprobantesDAO.selectMapeoTGR();
			//Se itera sobre los comprobantes
			//Si no se puede procesar un comprobante se continúa con siguiente comprobante.
			for (Iterator listacomp = comprobantes.iterator(); listacomp.hasNext();) {
				int numtra=0;
				int estadoproc=0;
				int estadoconv=0;
				ComprobantesTO comprobante = (ComprobantesTO) listacomp.next();
				logger.fine("Procesando comprobante cod. barra: " + comprobante.getIdCodigoBarra());
				try{
					//buscando y seteando las entidades asociadas a Pago del comprobante
					Collection entidades= (Collection)comprobantesDAO.selectDetalleSeccion(comprobante);
					comprobante.setEntidades(entidades);
					logger.fine("Entidades con Pago: " +  entidades.size());
					//buscando los trabajadores con pensión en entidades pagadas asociados al comprobante.
					if(entidades.size()>0){
						CotizantesPensionDAO cotizantes= new CotizantesPensionDAO(cpDAO.getConnection());
						Collection cotizantesTesGen= (Collection)cotizantes.select(comprobante);
						logger.fine("Numero Trabajadores asociados a entidades pagadas:" + cotizantesTesGen.size());
						//Se instancia objeto para insertar en Tesorería General
						RecaudacionTesoreriaGeneralDAO tesoreriaGeneralDAO= new RecaudacionTesoreriaGeneralDAO(tesoDAO.getConnection(), codigosTGR);
						logger.fine("Insertando trabajadores en tabla REDTA.RE72F1");
						for (Iterator iterTG = cotizantesTesGen.iterator(); iterTG.hasNext();) {
							TrabajadorTGR trabTO = (TrabajadorTGR) iterTG.next();
							logger.finer("Insertando trabajador rut: " + trabTO.getRutTrabajador());
							numtra+= tesoreriaGeneralDAO.insert(trabTO);
						}
						if(numtra>0){
							estadoproc=1;
							totalTrabajadoresConTG+= numtra;
						}
						//Verificando si existe convenio en Base de Datos de Recaudación
						logger.fine("Verificando si existe convenio asociado al comprobante");
						if(!tesoreriaGeneralDAO.existeConvenio(comprobante)){
							logger.fine("No existe convenio, se buscan los datos de la empresa:" + comprobante.getRutEmpresa());
							NuevoConvenio nuevo= (NuevoConvenio) comprobantesDAO.selectDatosEmpresa(comprobante);
							logger.fine("Se registra nuevo Convenio :" + nuevo.getConvenio()); 
							tesoreriaGeneralDAO.insertConvenio(nuevo);
							estadoconv=1;
							numeroConveniosNuevos++;
						}
						numeroComprobantesConTG++;
					}else{
						estadoproc=-1;
						estadoconv=-1;
					}
					//Actualizanzo campo TGR de Resumen Cierre Proceso para indicar estado.
					logger.fine("Actualizanzo campo 'tgr' de resumen cierre");
					resumenDAO.updateTGR(comprobante.getFolioTesoreria(), estadoproc, estadoconv);

					//Haciendo commit si no hubo errores después de actualizar comprobante procesado
					tesoDAO.commit();
					cpDAO.commit();
					logger.fine("Commit TGR comprobante:" + comprobante.getIdCodigoBarra() + "estado proceso= "  + estadoproc);
				} catch (Exception e) {
					logger.severe("ERROR al generar Planillas comprobante Codigo Barra:" + comprobante.getIdCodigoBarra());
					numeroComprobantesErroneos++;
					cpDAO.rollback();
					tesoDAO.rollback();
					e.printStackTrace();
				}
				
			} //next comprobante
			logger.info("Numero comprobantes procesados/total con TGR= " + getNumeroComprobantesConTG() + "/" + comprobantes.size() + ", total trabajadores insertados= " + getTotalTrabajadoresConTG());
			setMensajeError("Numero comprobantes procesados/total con TGR= " + getNumeroComprobantesConTG() + "/" + comprobantes.size() + ", total trabajadores insertados= " + getTotalTrabajadoresConTG() + "\n" + "Convenios nuevos registrados=" + numeroConveniosNuevos);
		} catch (Exception e) {
			logger.severe("Error, caida inesperada en generación TGR, mensaje error" + e.getMessage());
			e.printStackTrace();
			termino= false;
			setMensajeError(e.getMessage());
		}
		finally{
			if(numeroComprobantesErroneos>0){
				logger.warning("Número de comprobantes sin procesar:" + numeroComprobantesErroneos);
			}
			logger.info(">>Fin generación TGR");
		}
		return termino;
	}
	
	
	 public void close(){
		cpDAO.closeConnectionDAO();
		tesoDAO.closeConnectionDAO();
	 }


	/**
	 * @return el numeroComprobantesConTG
	 */
	public int getNumeroComprobantesConTG() {
		return numeroComprobantesConTG;
	}


	/**
	 * @param numeroComprobantesConTG el numeroComprobantesConTG a establecer
	 */
	public void setNumeroComprobantesConTG(int numeroComprobantesConTG) {
		this.numeroComprobantesConTG = numeroComprobantesConTG;
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


	/**
	 * @return el totalTrabajadoresConTG
	 */
	public int getTotalTrabajadoresConTG() {
		return totalTrabajadoresConTG;
	}


	/**
	 * @param totalTrabajadoresConTG el totalTrabajadoresConTG a establecer
	 */
	public void setTotalTrabajadoresConTG(int totalTrabajadoresConTG) {
		this.totalTrabajadoresConTG = totalTrabajadoresConTG;
	}


	/**
	 * @return el numeroConveniosNuevos
	 */
	public int getNumeroConveniosNuevos() {
		return numeroConveniosNuevos;
	}


	/**
	 * @param numeroConveniosNuevos el numeroConveniosNuevos a establecer
	 */
	public void setNumeroConveniosNuevos(int numeroConveniosNuevos) {
		this.numeroConveniosNuevos = numeroConveniosNuevos;
	}
	
}

