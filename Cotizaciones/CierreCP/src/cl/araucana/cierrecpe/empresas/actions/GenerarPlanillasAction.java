

/*
 * @(#) GenerarPlanillasAction.java    1.0 13-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.actions;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.DuplicarEsquemaPW;
import cl.araucana.cierrecpe.empresas.business.GenerarPlanillas;
import cl.araucana.cierrecpe.empresas.threads.GenerarPlanillasThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;

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
public class GenerarPlanillasAction extends Action {
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
		String cierre="";
		try{
			forward = mapping.findForward("NOTOK");
			HttpSession sesion= request.getSession();
			
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			//int periodo= paramTO.getPeriodoSistema();
			String periodo= request.getParameter("periodo");
			cierre= (String)request.getParameter("cierre");
			String fechaPago= (String)request.getParameter("fechaPago");
			String optionCentralizacion= (String)request.getParameter("optioncentral");
			if(fechaPago.equals("") || fechaPago.length()!=10){
				fechaPago=new AbsoluteDate().toString();
			}
			 
			String emails= paramTO.getEmailUsuario();
			logger.info("Iniciando GenerarPlanillasAction, cierre =" + cierre + ", periodo=" + periodo);
			
			if(!paramTO.getProcesosActivos().containsKey("PLANILLA:" + cierre)){
				GenerarPlanillasThreads planillaThreads= new GenerarPlanillasThreads(Integer.parseInt(periodo), Integer.parseInt(cierre), fechaPago, optionCentralizacion, emails);
				paramTO.addProceso("PLANILLA:" + cierre, Today.getFecha_Hora());
				paramTO.addThread("PLANILLA:" + cierre, planillaThreads);
				planillaThreads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			request.setAttribute("accion", "Planillas PWF, cierre " + cierre);
			request.setAttribute("origen", "EMP");
		}
		return forward;
	}
}

