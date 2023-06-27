package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.impl.NewSolDesafiliacionImpl;
import cl.araucana.independientes.impl.NewSolicitudImpl;
import cl.araucana.independientes.struts.Forms.NewSolDesafiliacionForm;
import cl.araucana.independientes.vo.RespuestaVO;

public class NewSolDesafiliacionAction extends Action{

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


        NewSolDesafiliacionForm newSolDesafiliacionForm = (NewSolDesafiliacionForm) form;

        opcion = Integer.parseInt(newSolDesafiliacionForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: 			

            resp = NewSolDesafiliacionImpl.insertarNuevaSolicitud(request);

            request.setAttribute("resultado", Integer.toString(resp.getCodRespuesta()));

            if(resp.getCodRespuesta() == 0)
            {
                session.setAttribute("msgRespuesta", "Solicitud de Desafiliacion ingresada con exito");
            }else
            {
                session.setAttribute("msgRespuesta", resp.getMsgRespuesta());

                //0. Informacion de encabezado
                request.setAttribute("txt_Fecha", newSolDesafiliacionForm.getTxt_Fecha());


                //1. Identificación del Trabajador Independiente
                request.setAttribute("txt_Folio", newSolDesafiliacionForm.getTxt_Folio());
                request.setAttribute("txt_Oficina", newSolDesafiliacionForm.getTxt_Oficina());
                request.setAttribute("txt_Rut", newSolDesafiliacionForm.getTxt_Rut());
                request.setAttribute("txt_NumVerif", newSolDesafiliacionForm.getTxt_NumVerif());
                request.setAttribute("txt_ApePat", newSolDesafiliacionForm.getTxt_ApePat());
                request.setAttribute("txt_ApeMat", newSolDesafiliacionForm.getTxt_ApeMat());
                request.setAttribute("txt_Nombres", newSolDesafiliacionForm.getTxt_Nombres());

                request.setAttribute("txt_codAreaCelular", newSolDesafiliacionForm.getTxt_codAreaCelular());
                request.setAttribute("txt_TelCelular", newSolDesafiliacionForm.getTxt_TelCelular());
                request.setAttribute("txt_codAreaContacto", newSolDesafiliacionForm.getTxt_codAreaContacto());
                request.setAttribute("txt_TelContacto", newSolDesafiliacionForm.getTxt_TelContacto());

                request.setAttribute("txt_Email", newSolDesafiliacionForm.getTxt_Email());
                request.setAttribute("txt_Calle", newSolDesafiliacionForm.getTxt_Calle());
                request.setAttribute("txt_Numero", newSolDesafiliacionForm.getTxt_Numero());
                request.setAttribute("txt_PoblVilla", newSolDesafiliacionForm.getTxt_PoblVilla());
                request.setAttribute("txt_Departamento", newSolDesafiliacionForm.getTxt_Departamento());
                session.setAttribute("Region", newSolDesafiliacionForm.getDbx_Region());
                session.setAttribute("Provincia", newSolDesafiliacionForm.getDbx_Provincia());
                session.setAttribute("Comuna", newSolDesafiliacionForm.getDbx_Comuna());

                //2.Desafiliación a CCAF La Araucana
                session.setAttribute("CajaCompensacion", newSolDesafiliacionForm.getDbx_CajaCompensacion());
                request.setAttribute("txt_FecVigAfil", newSolDesafiliacionForm.getTxt_FecVigAfil());
                request.setAttribute("txt_FecUltApo", newSolDesafiliacionForm.getTxt_FecUltApo());

                //3. Antecedentes Proceso de Desafiliación
                session.setAttribute("Oficina", newSolDesafiliacionForm.getDbx_Oficina());
                request.setAttribute("txt_FecIngr", newSolDesafiliacionForm.getTxt_FecIngr());
                request.setAttribute("txt_FecSolDesaf", newSolDesafiliacionForm.getTxt_FecSolDesaf());
                request.setAttribute("txt_Hora", newSolDesafiliacionForm.getTxt_Hora());
            }

            return mapping.findForward("solDesafiliacion");

        case 2: 

            return mapping.findForward("menuDesaf");		

        default: 

            return mapping.findForward("menuDesaf"); //TODO Deberia ir a "error"?

        }	
    }
}
