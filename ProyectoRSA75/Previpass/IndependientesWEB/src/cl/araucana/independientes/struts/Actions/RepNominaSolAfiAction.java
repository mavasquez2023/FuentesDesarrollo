package cl.araucana.independientes.struts.Actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import cl.araucana.independientes.helper.ManipuladorArchivo;
import cl.araucana.independientes.struts.Forms.RepNominaSolAfiForm;

public class RepNominaSolAfiAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        int opcion;

        HttpSession session = request.getSession();

        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }


        RepNominaSolAfiForm repNominaSolAfiForm = (RepNominaSolAfiForm) form;

        opcion = Integer.parseInt(repNominaSolAfiForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1:

            return mapping.findForward("menuPpal");

        case 2:

            ManipuladorArchivo bajar = new ManipuladorArchivo();

            try{
                String archivoEntrada = repNominaSolAfiForm.getArchivo();

                String ruta = archivoEntrada;
                String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

                bajar.download(request, response, ruta, archivo);

            }catch(IOException e){
                e.printStackTrace();
            }
            catch(ServletException f){
                f.printStackTrace();
            }


        default:

            return mapping.findForward("menuPpal");
        }
    }
}
