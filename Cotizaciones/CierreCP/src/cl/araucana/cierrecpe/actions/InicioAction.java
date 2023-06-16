

/*
 * @(#) Inicio.java    1.0 23/09/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.actions;

import java.security.Principal;
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
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.ParametrosCPDAO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
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
 *            <TD> 23/09/2010 </TD>
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
public class InicioAction extends Action {
	private CPDAO cpDAO=null;
	private static Logger logger = LogManager.getLogger();
	private ParametrosCPTO paramTO=null;
	
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
			Principal principal = request.getUserPrincipal();
			if (principal != null && principal.getName() != null) {
				String username= principal.getName();
				int rutuser= Integer.parseInt(username.substring(0, username.indexOf("-")));
				cpDAO= new CPDAO();
				ParametrosCPDAO paramDAO= new ParametrosCPDAO(cpDAO.getConnection());
				boolean isadmin= paramDAO.isAdmin(rutuser);
				if(isadmin){
					forward = mapping.findForward("OK");
					sesion.setAttribute("admin", "true");
					//Se rescata parámetros del negocio.
					paramTO= (ParametrosCPTO) paramDAO.select(null);
					logger.config("Param periodo:" + paramTO.getPeriodoSistema());
					logger.config("Param fecha cierre:" + paramTO.getFechaCierre());
					//Se carga los parámetros
					Parametros param= Parametros.getInstance();
			    	param.setParam(paramTO);
			    	sesion.setAttribute("urlPDFServices", paramTO.getUrlPDFServices());
				}else{
					forward = mapping.findForward("NOTAUTH");
				}
			}
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if (cpDAO!= null){
				cpDAO.closeConnectionDAO();
			}

		}
		
		return forward;
	}
}

