package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.MenuPpalForm;

/*Implementación de la clase MenuPpalAction*/
public class MenuPpalAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        HttpSession session = request.getSession();

        //--- INI - Validacion de usuario logueado ---

        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        //--- FIN - Validacion de usuario logueado ---

        MenuPpalForm menuPpalForm = (MenuPpalForm) form;

        int opcion;

        opcion = Integer.parseInt(menuPpalForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: return mapping.findForward("ingresoSolicitud");

        case 2: return mapping.findForward("modificaSolicitud");		

        case 3: return mapping.findForward("modificaAfiliado");

        case 4: 

            StringBuffer excelFile = new StringBuffer(""); 

            excelFile.append("/archivos/independientes/reportes/");
            excelFile.append("rep1");
            excelFile.append(".xls");

            request.setAttribute("excelFile", excelFile.toString()  );

            return mapping.findForward("repNominaSolAfi");

        case 5: return mapping.findForward("consModMasivaSol");

        case 6: return mapping.findForward("menuAporte");

        case 7: return mapping.findForward("menuIntercaja");

        case 8: return mapping.findForward("menuBenef");

        case 9: return mapping.findForward("mantenedores");

        case 10: return mapping.findForward("menuDesaf");

        case 11: return mapping.findForward("menuAfi");

        default: return mapping.findForward("menuPpal");

        }

    }
}
