/*
 * @(#) SimulaBanco.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.pagobes.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.util.Renderer;

import com.bh.talon.User;


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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Clase utilizada para simular la recepción y respuesta del Banco Estado
 *            Se utiliza solo para desarrollo y se configura en la tabla convenio.url_cgi
 *            </TD>
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
 * @author Alejandro Sepúlveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class SimulaBanco extends AppAction{
    
	private static final Logger logger = Logger.getLogger(SimulaBanco.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
						
		Renderer render = new Renderer();

		String mensaje="";
		mensaje = parseParameter("ent", mensaje);
		
		logger.debug("mensaje ent: " + mensaje);
		
		mensaje = request.getParameter("ent");
		
		logger.debug("mensaje ent1: " + mensaje);
		
		String aux = mensaje.substring(0, mensaje.length()-21);
		
		String mensajeAdicional =
			
				"<RESULTADO>"+			 	
					"<GLOSA_ERR/>"+
					"<RESULT_MPAGO>OK</RESULT_MPAGO>"+
					"<TRX_MPAGO>74025653</TRX_MPAGO>"+
					"<FEC_MPAGO>" + render.formatDateMsg(new java.util.Date()) + "</FEC_MPAGO>"+
					"<HORA_MPAGO>" + render.formatHourMsg(new java.util.Date()) + "</HORA_MPAGO>"+
					"<FEC_CNTBL_MPAGO>" + render.formatDateMsg(new java.util.Date()) + "</FEC_CNTBL_MPAGO>"+
				"</RESULTADO>" +
			"</MULTIPAGO>" +
		"</INICIO>";
				
		request.setAttribute("mensaje_inicial", aux+mensajeAdicional);
		
		return mapping.findForward("envia");
	}

}