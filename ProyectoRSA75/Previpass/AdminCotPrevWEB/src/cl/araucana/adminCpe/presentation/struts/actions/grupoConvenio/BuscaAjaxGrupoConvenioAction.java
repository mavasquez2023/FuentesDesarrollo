package cl.araucana.adminCpe.presentation.struts.actions.grupoConvenio;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

import com.bh.talon.User;
/*
* @(#)DetalleGrupoConvenioAction.java 1.9 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author csanchez
 * 
 * @version 1.0
 */
public class BuscaAjaxGrupoConvenioAction extends AppAction
{

	private static Logger logger = Logger.getLogger(BuscaAjaxGrupoConvenioAction.class);

	protected ActionForward doTask( User usuario
								  , ActionMapping mapping
								  , ActionForm form
								  , HttpServletRequest request
								  , HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml");
		response.setContentType("application/xml");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",	"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		String id = request.getParameter("id");

		try {
			
			Writer writer = response.getWriter();
			StringBuffer respuesta = new StringBuffer("<respuesta>");
			
			Session session = HibernateUtil.getSession();

			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(Integer.parseInt(id)); 

			if (grupoConvenio == null) {
				respuesta.append("<existe>0</existe>");
			} else {
				respuesta.append("<existe>1</existe>");
			}
			respuesta.append("</respuesta>");
			writer.write(respuesta.toString());

		} catch (Exception e) {
			logger.error("BuscaAjaxGrupoConvenioAction::" + e.getMessage(), e);
		}

		return null;
	}
}
