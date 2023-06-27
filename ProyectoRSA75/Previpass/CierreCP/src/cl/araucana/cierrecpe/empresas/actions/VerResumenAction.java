

/*
 * @(#) GenerarPlanillasAction.java    1.0 13-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.actions;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.GenerarPlanillas;
import cl.araucana.cierrecpe.empresas.business.ResumenCierreCP;
import cl.araucana.cierrecpe.empresas.to.ResumenCierreComprobantesTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;

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
 *            <TD> 13-08-2009 </TD>
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
public class VerResumenAction extends Action {
	private ResumenCierreCP generar;
	private static Logger logger = LogManager.getLogger();
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		try{
			forward = mapping.findForward("NOTOK");
			HttpSession sesion= request.getSession();
			
			int periodoSistema= Parametros.getInstance().getParam().getPeriodoSistema();
			int periodoSistemaIndependiente= Parametros.getInstance().getParam().getPeriodoSistemaIndependiente();
			String cierre= (String)request.getParameter("cierre");
			String periodo= (String)request.getParameter("periodo");
			String cambiaPeriodo= (String)request.getParameter("cambiaPeriodo");
	
			logger.info("Iniciando VerResumenAction, cierre =" + cierre + ", periodo=" + periodo);
			generar= new ResumenCierreCP();
			request.setAttribute("ocultarB1", "true");
			request.setAttribute("ocultarB2", "true");
			//request.setAttribute("ocultarB3", "true");
			request.setAttribute("ocultarB4", "true");
			request.setAttribute("periodoSistema", String.valueOf(periodoSistema));
			request.setAttribute("periodoSistemaIndependiente", String.valueOf(periodoSistemaIndependiente));
			request.setAttribute("fechaPago", new AbsoluteDate());
			if (periodo==null){ //la primera vez
				List periodos= (List)generar.getListPeriodos();
				int ultimoPeriodo= Integer.parseInt(periodos.get(0).toString());
				logger.fine("Ultimo Periodo con información= " + ultimoPeriodo);
				List cierres= (List)generar.getListCierresPlanillas(ultimoPeriodo);
				sesion.setAttribute("listPeriodos", periodos);
				sesion.setAttribute("listCierres", cierres);
				sesion.setAttribute("periodoSistema", String.valueOf(periodoSistema));
				//sesion.setAttribute("numcierre", "1"); 
				sesion.setAttribute("listPlanillas", generar.getResumenCierreHistorico(ultimoPeriodo));
				request.setAttribute("cierre", "0");
			}else if (cambiaPeriodo.equals("1")){
				List cierres= (List)generar.getListCierresPlanillas(Integer.parseInt(periodo));
				sesion.setAttribute("listCierres", cierres);
				sesion.setAttribute("listPlanillas", generar.getResumenCierreHistorico(Integer.parseInt(periodo)));
				request.setAttribute("cierre", "0");
				request.setAttribute("periodo", periodo);
			}else{
				request.setAttribute("cierre", cierre);
				request.setAttribute("periodo", periodo);
				Collection list=(Collection)sesion.getAttribute("listPlanillas");
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					ResumenCierreComprobantesTO resumen = (ResumenCierreComprobantesTO) iter.next();
					if(resumen.getCierre()== Integer.parseInt(cierre)){
						request.setAttribute("formapago", new Integer(resumen.getFormaPago()));
						//Tablas de Publicación Planillas
						if(resumen.getSinPlanillas()>0){
							request.setAttribute("ocultarB1", "false");
						}
						//Tesorería General de la Republica
						if(resumen.getSinTGR()>0){
							request.setAttribute("ocultarB2", "false");
						}
						//Archivos Impresión ASICOM (No se utiliza en 2013)
						/*if(resumen.getSinAIA()>0 && resumen.getSinPlanillas()==0){
							request.setAttribute("ocultarB3", "false");
						}*/
						//Centralización
						if(resumen.getSinCEN()>0 && resumen.getSinPlanillas()==0){
							request.setAttribute("ocultarB4", "false");
						}
					}
				}
			}
			forward = mapping.findForward("PARAM");
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(generar!=null){
				generar.close();
			}
		}
		return forward;
	}
}

