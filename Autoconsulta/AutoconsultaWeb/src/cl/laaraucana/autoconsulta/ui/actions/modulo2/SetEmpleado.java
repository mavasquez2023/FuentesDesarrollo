package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

public class SetEmpleado extends Action
{
  private static Logger logger = Logger.getLogger(SetEmpleado.class);
  private static final String SUCCESS = "certificadoOk";
  private static final String NO_SUCCESS = "logoutpage";
  private static final String PIDEEMPLEADO = "pideEmpleado";

  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  HttpSession session = request.getSession(true);  
	  String target = PIDEEMPLEADO;
      logger.debug("Presenta pagina: " + target + "afi" + session.getAttribute("afiliado.rut"));
      return mapping.findForward(target);
  }
  
}
