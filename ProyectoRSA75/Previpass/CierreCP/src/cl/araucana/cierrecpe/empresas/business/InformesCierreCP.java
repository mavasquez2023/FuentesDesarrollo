

/*
 * @(#) GenerarPlanillas.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.PubDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.empresas.dao.InformesCierreCPDAO;
import cl.araucana.cierrecpe.empresas.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.cierrecpe.empresas.to.InformeTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;

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
 *            <TD> 02-03-2015 </TD>
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
public class InformesCierreCP implements Constants{
	private PubDAO pubDAO=null;
	private TesoDAO tesoDAO=null;
	private static Logger logger = LogManager.getLogger();
	
	public InformesCierreCP() throws SQLException{
		pubDAO= new PubDAO();
	}
	
	public Collection generarInformeEmpresasNuevas(int periodo) {
		int periodoant=1;
		try {
			logger.finer("Se solicita Descargar Informe Empresas Nuevas Periodo: " + periodo);
			InformesCierreCPDAO informeDAO= new InformesCierreCPDAO(pubDAO.getConnection());
			return (List)informeDAO.selectEmpresasNuevas(periodo, getPeriodoAnterior(periodo));
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection generarInformeEmpresasNoPagaron(int periodo) {
		int periodoant=1;
		try {
			logger.finer("Se solicita Descargar Informe Empresas No Pagaron Periodo: " + periodo);
			InformesCierreCPDAO informeDAO= new InformesCierreCPDAO(pubDAO.getConnection());
			return (List)informeDAO.selectEmpresasNoPagaron(periodo, getPeriodoAnterior(periodo));
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection generarInformeEmpresasActivas(int periodo) {
	
		try {
			logger.finer("Se solicita Descargar Informe Empresas Activas Periodo: " + periodo);
			InformesCierreCPDAO informeDAO= new InformesCierreCPDAO(pubDAO.getConnection());
			return (List)informeDAO.selectEmpresasActivasPrevipass(periodo);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection generarInformeFormasDePago(int periodo) {

		try {
			tesoDAO=new TesoDAO();
			logger.info("Descargar Informe 'Formas De Pago' Periodo: " + periodo);
			InformesCierreCPDAO informeDAO= new InformesCierreCPDAO(pubDAO.getConnection());
			//Se rescata Concepyos Caja de Detalle de Comprobante
			Map conceptosCaja= informeDAO.selectConceptosCaja(periodo);
			//Se rescata comprobantes y sus detalles pagado
			Map pagos= informeDAO.selectComprobantesFormasDePago(periodo, conceptosCaja);
			logger.info("Numero Pagos Encontrados: " + pagos.size());
			InformesCierreCPDAO cruceSPLDAO= new InformesCierreCPDAO(tesoDAO.getConnection());
			//cruce de información con SPL
			List idPagos= cruceSPLDAO.selectEmpresasconSPL(pagos);
			//cruce de información con Boletas
			List idBoletas= informeDAO.selectEmpresasconPAC(pagos);
			//cruce de información con histórico Tesorería
			//List idPagos_hist= cruceSPLDAO.selectEmpresasconSPL(empresas, "tedta.te44f1");
			//Se unifican lista de id_pagos
			//for (Iterator iter = idPagos_hist.iterator(); iter.hasNext();) {
			//	Integer id_pago = (Integer) iter.next();
			//	idPagos.add(id_pago);
			//}
			Map montosxfechas= cruceSPLDAO.selectPagosxFechaContable(idPagos);
			List lista_empresas= new ArrayList();
			for (Iterator iter = pagos.values().iterator(); iter.hasNext();) {
				InformeTO empresa = (InformeTO) iter.next();
				//String fechaaux= empresa.getFechaPago();
				//logger.info("Fecha Pago:"+ fechaaux);
				//if(fechaaux!= null){
				//	int fechaupd= Integer.parseInt(fechaaux.substring(0,4)+ fechaaux.substring(5, 7) + fechaaux.substring(8, 10));
					//logger.info("Fecha integer:"+ fechaupd);
				//	informeDAO.updateFechaspago(empresa.getFolio(), fechaupd, empresa.getFormaPago());
				//}
				if(empresa.getIdPago()>0){
					if(empresa.getMontoConsolidado()==0){
						empresa.setMontoConsolidado(empresa.getTotal());
					}
					if(empresa.getTipoRegistro()==2){
						Long monto_consolidado= (Long)montosxfechas.get(empresa.getMedioPago()+"_" + empresa.getFechaContable());
						if(monto_consolidado!=null){
							empresa.setMontoConsolidado(monto_consolidado.longValue());
						}
					}
					
				}
				
				lista_empresas.add(empresa);
			}
			logger.info("Total Pagos a Informar:" + lista_empresas.size());
			return lista_empresas;
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally{
			if(tesoDAO!=null){
				tesoDAO.closeConnectionDAO();
			}
		}
	}
	
	public int getPeriodoAnterior(int periodo){
		String periodostr= String.valueOf(periodo);
		int año= Integer.parseInt(periodostr.substring(0, 4));
		int mes= Integer.parseInt(periodostr.substring(4)) - 1;
		if(mes==0){
			mes= 12;
			año--; 
		}
		int resultado= año*100 + mes;
		return resultado;
	}
	
	 public void close(){
		pubDAO.closeConnectionDAO();
	 }
	
}

