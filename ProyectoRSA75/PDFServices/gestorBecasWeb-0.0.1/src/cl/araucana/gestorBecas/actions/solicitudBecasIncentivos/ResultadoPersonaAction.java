// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 28-01-2022 21:27:53
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultadoPersonaAction.java

package cl.araucana.gestorBecas.actions.solicitudBecasIncentivos;

import cl.araucana.gestorBecas.ejb.GestorBecas;
import cl.araucana.gestorBecas.model.bo.SolicitudBO;
import cl.araucana.gestorBecas.model.bo.dvo.NivelEducacionalDVO;
import cl.araucana.gestorBecas.model.bo.vo.PersonaVO;
import cl.araucana.gestorBecas.ui.WebUtils;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class ResultadoPersonaAction extends Action
{

    public ResultadoPersonaAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        HttpSession session = request.getSession(true);
        String cmd = request.getParameter("_cmd");
        logger.debug("Comando Recibido --> " + cmd);
        GestorBecas gestorBecasDelegate = WebUtils.getGestorBecasDelegate(request);
        SolicitudBO solicitudRecibidaBO = (SolicitudBO)session.getAttribute("solicitudBO");
        String target = "";
        if(cmd.equalsIgnoreCase("resultado"))
        {
            target = "resultado";
            String idNivelEducacional = request.getParameter("nivelEducacional");
            String idCurso = request.getParameter("curso");
            String calificacion = request.getParameter("calificacion");
            String puntajeLenguaje = request.getParameter("puntajeLenguaje");
            String puntajeMatematica = request.getParameter("puntajeMatematica");
            String puntajePromedio = request.getParameter("puntajePromedioHidden");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String celular = request.getParameter("celular");
            String indexSeleccionado = request.getParameter("index");
            String tipoCalificacion = request.getParameter("tipoCalificacion");
            cl.araucana.gestorBecas.model.bo.dvo.CursoDVO cursoDVO = gestorBecasDelegate.getCursoById(Long.valueOf(idCurso).longValue());
            NivelEducacionalDVO nivelEducacional = gestorBecasDelegate.getNivelEducacionalById(Long.valueOf(idNivelEducacional).longValue());
            boolean telefonoEsValido = false;
            boolean celularEsValido = false;
            try
            {
                Integer.parseInt(telefono);
                if(telefono.length() >= 7 && telefono.length() <= 9)
                {
                    telefonoEsValido = true;
                } else
                {
                    WebUtils.setError(request, "message.telefonoFueraRango");
                    target = "inicio";
                }
            }
            catch(Exception exception) { }
            try
            {
                Integer.parseInt(celular);
                if(celular.length() == 9)
                {
                    celularEsValido = true;
                } else
                {
                    WebUtils.setError(request, "message.celularFueraRango");
                    target = "inicio";
                }
            }
            catch(Exception exception1) { }
            if(!telefonoEsValido && !celularEsValido)
            {
                WebUtils.setError(request, "message.telefonosInvalidos");
                target = "inicio";
            }
            try
            {
                (new InternetAddress(email)).validate();
                if(email.length() < 5 || email.length() > 250)
                    throw new Exception();
            }
            catch(Exception e)
            {
                WebUtils.setError(request, "message.emailInvalido");
                target = "inicio";
            }
            solicitudRecibidaBO.setCurso(cursoDVO);
            solicitudRecibidaBO.getPosiblesBeneficiarios()[Integer.parseInt(indexSeleccionado)].setBeneficiario(true);
            solicitudRecibidaBO.setNivelEducacional(nivelEducacional);
            solicitudRecibidaBO.setTipoCalificacion(Integer.parseInt(tipoCalificacion));
            if(!calificacion.equals("") && !idNivelEducacional.equals("6"))
            {
                if(Integer.parseInt(tipoCalificacion) == 1)
                {
                    if(Double.parseDouble(calificacion) < 1.0D || Double.parseDouble(calificacion) > 7D)
                    {
                        WebUtils.setError(request, "message.calificacionFueraRango", "1 y 7");
                        target = "inicio";
                    }
                } else
                if(Integer.parseInt(tipoCalificacion) == 2)
                {
                    if(Double.parseDouble(calificacion) < 0.0D || Double.parseDouble(calificacion) > 100D)
                    {
                        WebUtils.setError(request, "message.calificacionFueraRango", "0 y 100");
                        target = "inicio";
                    }
                } else
                if(Integer.parseInt(tipoCalificacion) == 3 && (Long.parseLong(calificacion) < nivelEducacional.getPuntajeMinimo() || Long.parseLong(calificacion) > nivelEducacional.getPuntajeMaximo()))
                {
                    WebUtils.setError(request, "message.calificacionFueraRango", nivelEducacional.getPuntajeMinimo() + " y " + nivelEducacional.getPuntajeMaximo());
                    target = "inicio";
                }
                solicitudRecibidaBO.setCalificacion(Double.parseDouble(calificacion));
            } else
            {
                try
                {
                    solicitudRecibidaBO.setCalificacion(Double.parseDouble(puntajePromedio));
                    solicitudRecibidaBO.setPuntajeLenguaje(Integer.parseInt(puntajeLenguaje));
                    solicitudRecibidaBO.setPuntajeMatematica(Integer.parseInt(puntajeMatematica));
                    solicitudRecibidaBO.setPuntajePromedio(Integer.parseInt(puntajePromedio));
                }
                catch(Exception e)
                {
                    WebUtils.setError(request, "message.puntajeInvalido");
                    target = "inicio";
                }
            }
            solicitudRecibidaBO.setEmail(email);
            solicitudRecibidaBO.setTelefono(telefono);
            solicitudRecibidaBO.setCelular(celular);
            SolicitudBO solicitudBO = gestorBecasDelegate.getAlternativas(solicitudRecibidaBO);
            session.setAttribute("solicitudBO", solicitudBO);
        } else
        if(cmd.equalsIgnoreCase("volver"))
            target = "volver";
        logger.debug("Despachando a --> " + target);
        return mapping.findForward(target);
    }

    static Logger logger;

    static 
    {
        logger = Logger.getLogger(cl.araucana.gestorBecas.actions.solicitudBecasIncentivos.ResultadoPersonaAction.class);
    }
}
