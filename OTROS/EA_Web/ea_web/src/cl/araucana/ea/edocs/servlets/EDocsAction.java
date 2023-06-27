
/*
 * @(#) EDocsAction.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.ea.edocs.servlets;


import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.ea.edocs.DocumentController;
import cl.araucana.ea.edocs.Encargado;
import cl.araucana.ea.edocs.PublishedPeriodsBean;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
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
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class EDocsAction extends HttpServlet implements Servlet {

	private ServletContext servletContext;
	private DocumentController documentController;
	
	public void init() throws ServletException {
		
		servletContext = getServletContext();
		documentController = DocumentController.getInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String forwardPage = "/WEB-INF/edocs/nominas_anexos.jsp";
		Encargado encargado = null;
		boolean invalidatedSession = false;
		HttpSession session = request.getSession();
		Principal principal = (Principal) session.getAttribute("userPrincipal");

		/*
		 * Checks if user principal is authenticated.
		 */
		if (principal == null || principal.getName() == null) {
			session.invalidate();
			
			invalidatedSession = true;
			forwardPage = "/logon.jsp";
		} else {
			encargado =	(Encargado) session.getAttribute("edocs_encargado");

			if (encargado == null) {
				Profile profile =
						(Profile) session.getAttribute("ea_user_profile");
						
				if (profile != null) {
					String rut = (String) profile.getId();
	
					String fullName =
							(String) profile.getAttribute("nombreCompleto");
				
					Collection empresas =
							(Collection) profile.getAttribute("empresas");
	
					encargado = new Encargado(rut, fullName, empresas);
					session.setAttribute("edocs_encargado", encargado);
				} else {
					session.invalidate();
					invalidatedSession = true;
					forwardPage = "/logon.jsp";
				}
			}
		}

		if (!invalidatedSession) {

			/*
			 * Prepares contents to JSP page.
			 */
			int[] publishedPeriods = documentController.getPublishedPeriods();
			
			if (publishedPeriods.length == 0) {
				forwardPage = "/WEB-INF/edocs/userNotify.jsp";
			} else {

				/*
				 * Injects parameter "source" to JSP page.
				 */
				String source = request.getParameter("source");

				if (source != null) {
					source = source.trim();
			
					if (source.length() == 0) {
						source = "EA";
					}
				} else {
					source = "EA";
				}

				forwardPage += "?source=" + source;

				String requestedPeriodParam = request.getParameter("period");
				int selectedPeriodIndex = -1;
				int requestedPeriod = publishedPeriods[0];
				
				try {
					requestedPeriod = Integer.parseInt(requestedPeriodParam);
				} catch (NumberFormatException e) {}
				
				for (int i = 0; i < publishedPeriods.length; i++) {
					if (requestedPeriod == publishedPeriods[i]) {
						selectedPeriodIndex = i;
						
						break;
					}
				}

				if (selectedPeriodIndex == -1) {
					requestedPeriod = publishedPeriods[0];
					selectedPeriodIndex = 0;
				}
				
				encargado.setDocumentTypesCountForAllEmpresas(requestedPeriod);
					
				request.setAttribute(
						"publishedPeriods",
						new PublishedPeriodsBean(
								publishedPeriods, selectedPeriodIndex));
			}
		}
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPage);

		dispatcher.forward(request, response);
	}
}
