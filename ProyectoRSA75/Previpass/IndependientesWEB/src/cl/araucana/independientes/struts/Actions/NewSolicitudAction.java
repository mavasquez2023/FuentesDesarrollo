package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.impl.NewSolicitudImpl;
import cl.araucana.independientes.struts.Forms.NewSolicitudForm;
import cl.araucana.independientes.vo.RespuestaVO;

public class NewSolicitudAction extends Action{

    /* Función que representa el destino al cual se debe enviar el control una vez que una Action 
     * se ha completado, en este caso despues del insert de la nueva solicitud.*/	
    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        /*Variables usadas en el sistema.*/	
        int opcion;
        RespuestaVO resp = new RespuestaVO();

        /*variable de tipo sesion. Dichas variables de este tipo pueden ser usadas en el sistema en cualquier parte del mismo.*/
        HttpSession session = request.getSession();

        //variable que contiene al usuario que se registró en la aplicación.
        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        NewSolicitudForm newSolicitudForm = (NewSolicitudForm) form;

        opcion = Integer.parseInt(newSolicitudForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: 			

            resp = NewSolicitudImpl.insertarNuevaSolicitud(request);

            request.setAttribute("resultado", Integer.toString(resp.getCodRespuesta()));

            if(resp.getCodRespuesta() == 0)
            {
                session.setAttribute("msgRespuesta", "Solicitud Folio N° " + newSolicitudForm.getTxt_Folio() + " ingresada con exito");
            }else
            {
                session.setAttribute("msgRespuesta", resp.getMsgRespuesta());

                //informacion de encabezado
                session.setAttribute("Oficina", newSolicitudForm.getDbx_Oficina());
                request.setAttribute("txt_Fecha", newSolicitudForm.getTxt_Fecha());

                //informacion particular del formulario de ingreso de solicitud.
                request.setAttribute("txt_Folio", newSolicitudForm.getTxt_Folio());
                request.setAttribute("txt_Rut", newSolicitudForm.getTxt_Rut());
                request.setAttribute("txt_NumVerif", newSolicitudForm.getTxt_NumVerif());
                request.setAttribute("txt_ApePat", newSolicitudForm.getTxt_ApePat());
                request.setAttribute("txt_ApeMat", newSolicitudForm.getTxt_ApeMat());
                request.setAttribute("txt_Nombres", newSolicitudForm.getTxt_Nombres());
                session.setAttribute("Nacionalidad", newSolicitudForm.getDbx_Nacionalidad());
                request.setAttribute("txt_FecNac", newSolicitudForm.getTxt_FecNac());
                session.setAttribute("Sexo", newSolicitudForm.getDbx_Sexo());
                session.setAttribute("EstCivil", newSolicitudForm.getDbx_EstCivil());			
                request.setAttribute("txt_codAreaCelular", newSolicitudForm.getTxt_codAreaCelular());
                request.setAttribute("txt_TelCelular", newSolicitudForm.getTxt_TelCelular());
                request.setAttribute("txt_codAreaContacto", newSolicitudForm.getTxt_codAreaContacto());
                request.setAttribute("txt_TelContacto", newSolicitudForm.getTxt_TelContacto());
                request.setAttribute("txt_Email", newSolicitudForm.getTxt_Email());
                request.setAttribute("txt_Calle", newSolicitudForm.getTxt_Calle());
                request.setAttribute("txt_Numero", newSolicitudForm.getTxt_Numero());
                request.setAttribute("txt_PoblVilla", newSolicitudForm.getTxt_PoblVilla());
                request.setAttribute("txt_Departamento", newSolicitudForm.getTxt_Departamento());
                session.setAttribute("Region", newSolicitudForm.getDbx_Region());
                session.setAttribute("Provincia", newSolicitudForm.getDbx_Provincia());
                session.setAttribute("Comuna", newSolicitudForm.getDbx_Comuna());

                session.setAttribute("NivEstudios", newSolicitudForm.getDbx_NivEstudios());
                session.setAttribute("TitAcademico", newSolicitudForm.getDbx_TitAcademico());

                session.setAttribute("RegPrevisional", newSolicitudForm.getDbx_RegPrevisional());
                session.setAttribute("RegSalud", newSolicitudForm.getDbx_RegSalud());
                session.setAttribute("Conyugue", newSolicitudForm.getRbt_Conyugue());
                //request.setAttribute("rbt_Conyugue", newSolicitudForm.getRbt_Conyugue());
                request.setAttribute("txt_Hijos", newSolicitudForm.getTxt_Hijos());

                //informacion comercial
                request.setAttribute("txt_Actividad", newSolicitudForm.getTxt_Actividad());
                session.setAttribute("Honorarios", newSolicitudForm.getRbt_Honorarios());

                request.setAttribute("txt_CalleComerc", newSolicitudForm.getTxt_CalleComerc());
                request.setAttribute("txt_NumeroComerc", newSolicitudForm.getTxt_NumeroComerc());
                request.setAttribute("txt_PoblVillaComerc", newSolicitudForm.getTxt_PoblVillaComerc());
                request.setAttribute("txt_DptoComerc", newSolicitudForm.getTxt_DptoComerc());

                request.setAttribute("txt_codAreaComerc", newSolicitudForm.getTxt_codAreaComerc());
                request.setAttribute("txt_TelComerc", newSolicitudForm.getTxt_TelComerc());
                session.setAttribute("RegComerc", newSolicitudForm.getDbx_RegComerc());
                session.setAttribute("ProvinciaComerc", newSolicitudForm.getDbx_ProvinciaComerc());
                session.setAttribute("ComunaComerc", newSolicitudForm.getDbx_ComunaComerc());

                //informacion de renta
                request.setAttribute("txt_RentaImp", newSolicitudForm.getTxt_RentaImp());
                request.setAttribute("txt_RentaCot", newSolicitudForm.getTxt_RentaCot());
                request.setAttribute("txt_MontoUltimaCotizacion", newSolicitudForm.getTxt_MontoUltimaCotizacion());				
                request.setAttribute("txt_Hora", newSolicitudForm.getTxt_Hora());				
                request.setAttribute("txt_ValorACot", newSolicitudForm.getTxt_ValorACot());
                session.setAttribute("CajaCompensacion", newSolicitudForm.getDbx_CajaCompensacion());

                request.setAttribute("txt_FecVigAfil", newSolicitudForm.getTxt_FecVigAfil());
                request.setAttribute("txt_FecFirma", newSolicitudForm.getTxt_FecFirma());

                //Se agregan campos para analista comercial con idCaptador.
                request.setAttribute("txt_RutEjec", newSolicitudForm.getTxt_RutEjec());
                request.setAttribute("txt_ApePatEjec", newSolicitudForm.getTxt_ApePatEjec());
                request.setAttribute("txt_ApeMatEjec", newSolicitudForm.getTxt_ApeMatEjec());
                request.setAttribute("txt_NombreEjec", newSolicitudForm.getTxt_NombreEjec());
                request.setAttribute("txt_EmailComercial", newSolicitudForm.getTxt_EmailComercial());				
            }

            return mapping.findForward("ingresoSolicitud");

        case 2: 

            return mapping.findForward("menuPpal");		

        default: 

            return mapping.findForward("menuPpal"); //TODO Deberia ir a "error"?

        }	
    }
}
