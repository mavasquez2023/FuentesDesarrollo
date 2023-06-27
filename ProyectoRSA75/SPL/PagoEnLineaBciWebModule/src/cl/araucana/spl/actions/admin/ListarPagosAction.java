package cl.araucana.spl.actions.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.FiltroConcluirPago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.forms.admin.ConcluirPagoForm;
import cl.araucana.spl.mgr.ConcluirPagoManager;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.Utiles;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class ListarPagosAction extends AppAction {
	
	private static final Logger logger = Logger.getLogger(ListarPagosAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm f, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.debug("Entro a : ListarPagosAction");
		ConcluirPagoForm form = (ConcluirPagoForm) f;
		ConcluirPagoManager concluirPagoManager = new ConcluirPagoManager();
		SistemaManager sisManager = new SistemaManager();
		HttpSession session = request.getSession();
		session.removeAttribute("listConcluirPago");
		session.removeAttribute("bancoSeleccionado");
		
		try {
			DaoConfig.startTransaction();
			form.setSistemas(sisManager.getSistemas());
			form.setEstados(concluirPagoManager.getEstados());
	
			MedioPagoManager medioManager  = new MedioPagoManager();
			form.setBancos(medioManager.getMediosPago());
			
			if (Utiles.isNotEmpty(form.getBuscar())) {
				int limit = Constants.PAGE_SIZE;
				
				FiltroConcluirPago filtro = form.getFiltro();
				ConcluirPagoPageParameter params = new ConcluirPagoPageParameter(request, limit, filtro);
				filtro.setLimit(params.getLimit());
				filtro.setOffset(params.getOffset());
				List pagina = concluirPagoManager.getConcluirPago(filtro);
				form.setConcluirPago(pagina);
				
				session.setAttribute("listConcluirPago", pagina);
				session.setAttribute("bancoSeleccionado", form.getBanco());
			}
			DaoConfig.commitTransaction();
		} finally {
			DaoConfig.endTransaction();
		}
		
		return mapping.findForward("target");
	}
}
