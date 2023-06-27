/**
 * 
 */
package cl.araucana.pdfserviceweb.administrator.presentation.struts.actions;

/**
 * @author 11648834-5
 *
 */

import cl.araucana.pdfserviceweb.Utils;
import cl.araucana.pdfserviceweb.administrator.business.FacadeScriptManager;
import cl.araucana.pdfserviceweb.administrator.business.domain.ICatalogoPdfServiceWeb;
import cl.araucana.pdfserviceweb.administrator.business.domain.Sistema;
import cl.araucana.pdfserviceweb.administrator.business.domain.Usuario;
import cl.araucana.pdfserviceweb.administrator.presentation.helpers.LoadConfUserHelper;
import java.security.Principal;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoadConfUserAction extends Action
{
  private static final String KEY_LISTA_SISTEMAS = "listaSistemasAsociados";
  private static Logger logger = Logger.getLogger(LoadConfUserAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();

    Principal user = request.getUserPrincipal();
    String username = user.getName();

    String target = "";

    ArrayList listSystemsUser = null;

    String urlResponse = "index.jsp";

    ICatalogoPdfServiceWeb catalogo = null;
    try
    {
      request.setAttribute("urlResponse", urlResponse);

      if (user == null) {
        target = "failture";
        errors.add("inf.0001", 
          new ActionError("inf.0001"));
      }
      else
      {
        HttpSession sesion = request.getSession(false);
        ServletContext context = sesion.getServletContext();

        sesion.removeAttribute("sistema");
        sesion.removeAttribute("listaSistemasAsociados");
        sesion.removeAttribute(username);
        sesion.setAttribute("username", username);

        catalogo = (ICatalogoPdfServiceWeb)context.getAttribute("catalog");

        if (catalogo == null) {
          FacadeScriptManager scriptManager = FacadeScriptManager.getInstance();
          catalogo = scriptManager.getCatalogo();
          context.setAttribute("catalog", catalogo);
        }

        LoadConfUserHelper loadHelper = new LoadConfUserHelper(user, catalogo);

        Usuario theUser = loadHelper.getUser();

        if (theUser != null)
        {
          listSystemsUser = loadHelper.getSystemsUser(theUser);

          if ("Administrador".equals(theUser.getRolAsociado())) {
            sesion.setAttribute("admin", theUser);
          }

          sesion.setAttribute("usuario", theUser);

          if (listSystemsUser.size() == 1)
          {
            Sistema theSystem = loadHelper.getUniqueSystem(username);

            if (theSystem != null)
            {
              sesion.setAttribute("sistema", theSystem);
              ArrayList scriptsList = loadHelper.getScriptsList(theSystem);
              if ((scriptsList != null) && (!scriptsList.isEmpty())) {
                sesion.setAttribute("listaScriptsAsociados", scriptsList);
              }
            }

            target = "success0";
          } else {
            target = "success1";
          }

          sesion.setAttribute("listaSistemasAsociados", listSystemsUser);
        }
        else {
          sesion.invalidate();
          target = "failture";
          errors.add("inf.0010", 
            new ActionError("inf.0010"));
        }

      }

    }
    catch (Exception e)
    {
      errors.add("err.0002", new ActionError("err.0002"));
      target = "failture";
      logger.error(Utils.getStackTrace(e));
    }

    if (!errors.isEmpty()) {
      saveErrors(request, errors);

      forward = mapping.findForward(target);
    }
    else
    {
      forward = mapping.findForward(target);
    }

    return forward;
  }
}