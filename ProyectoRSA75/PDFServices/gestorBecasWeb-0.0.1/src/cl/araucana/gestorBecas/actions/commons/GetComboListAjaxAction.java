// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 31-01-2022 15:52:28
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   GetComboListAjaxAction.java

package cl.araucana.gestorBecas.actions.commons;

import cl.araucana.gestorBecas.ejb.GestorBecas;
import cl.araucana.gestorBecas.model.bo.SolicitudBO;
import cl.araucana.gestorBecas.model.bo.dvo.*;
import cl.araucana.gestorBecas.model.bo.vo.PersonaVO;
import cl.araucana.gestorBecas.ui.WebUtils;
import com.schema.util.XmlUtils;
import java.util.Locale;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;

public class GetComboListAjaxAction extends Action
{

    public GetComboListAjaxAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        StringBuffer buffer = new StringBuffer("");
        XmlUtils xu = new XmlUtils(WebUtils.FORMAT_NUMBER, WebUtils.FORMAT_DATE);
        Locale locale = new Locale("es_CL");
        xu.setDefaultLocale(locale);
        MessageResources mr = getResources(request);
        GestorBecas gestorBecasDelegate = WebUtils.getGestorBecasDelegate(request);
        try
        {
            HttpSession session = request.getSession();
            String cmd = WebUtils.getCleanData(request.getParameter("_cmd"));
            String idStr = WebUtils.getCleanData(request.getParameter("id"));
            long key = idStr != null ? Long.parseLong(idStr) : -1L;
            logger.debug("Obtiene Combo [" + cmd + "] para key: " + key);
            String items = "";
            int total = 0;
            if("nivelEducacional".equalsIgnoreCase(cmd))
            {
                SolicitudBO solicitudRecibidaBO = (SolicitudBO)session.getAttribute("solicitudBO");
                solicitudRecibidaBO.setBeneficiarioSeleccionado(Integer.parseInt(idStr));
                PersonaVO personaVO = solicitudRecibidaBO.getBeneficiarioSeleccionado();
                NivelEducacionalDVO nivelEducacionaDVO[] = gestorBecasDelegate.getNivelesEduByBeneficiario(personaVO.getRut(), solicitudRecibidaBO.getSegmentoDVO().getIdSegmento());
                for(int i = 0; i < nivelEducacionaDVO.length; i++)
                {
                    String item = xu.formatXmlString("value", String.valueOf(nivelEducacionaDVO[i].getIdNivelEducacional())) + xu.formatXmlString("label", nivelEducacionaDVO[i].getNivelEducacional());
                    logger.debug("Id Nivel Educacional : " + nivelEducacionaDVO[i].getIdNivelEducacional());
                    logger.debug("Nivel Educacional : " + nivelEducacionaDVO[i].getNivelEducacional());
                    items = items + xu.formatXmlString("item", item);
                    total++;
                }

            }
            if("curso".equalsIgnoreCase(cmd))
            {
                SolicitudBO solicitudRecibidaBO = (SolicitudBO)session.getAttribute("solicitudBO");
                PersonaVO personaVO = solicitudRecibidaBO.getBeneficiarioSeleccionado();
                CursoDVO cursoDVO[] = gestorBecasDelegate.getCursosByBeneficiario(personaVO, key);
                for(int i = 0; i < cursoDVO.length; i++)
                {
                    String item = xu.formatXmlString("value", String.valueOf(cursoDVO[i].getIdCurso())) + xu.formatXmlString("label", cursoDVO[i].getCurso());
                    logger.debug("Id Curso : " + cursoDVO[i].getIdCurso());
                    logger.debug("Curso : " + cursoDVO[i].getCurso());
                    items = items + xu.formatXmlString("item", item);
                    total++;
                }

            }
            if("tipoCalificacion".equalsIgnoreCase(cmd))
            {
                NivelEducacionalDVO nivelEducacionalDVO = gestorBecasDelegate.getNivelEducacionalById(key);
                GenericDVO generic[] = nivelEducacionalDVO.getTiposDeCalificacion();
                for(int j = 0; j < generic.length; j++)
                {
                    String item = xu.formatXmlString("value", String.valueOf(generic[j].getNumber())) + xu.formatXmlString("label", generic[j].getString());
                    logger.debug("Nivel Educacional ID: " + generic[j].getNumber());
                    logger.debug("Nivel Educacional: " + generic[j].getString());
                    items = items + xu.formatXmlString("item", item);
                    total++;
                }

            }
            if(key != 6L && !"tipoCalificacion".equalsIgnoreCase(cmd))
            {
                String seleccione = xu.formatXmlString("value", "") + xu.formatXmlString("label", mr.getMessage("label.common.seleccione.text"));
                items = xu.formatXmlString("item", seleccione) + items;
                total++;
            }
            buffer.append(xu.formatXmlString("error", "false"));
            buffer.append(xu.formatXmlString("totalItems", String.valueOf(total)));
            buffer.append(xu.formatXmlString("items", items));
        }
        catch(Exception ex)
        {
            logger.debug("No se pudo dar respuesta a la operaci\303\263n!!", ex);
            buffer.append(xu.formatXmlString("error", "true"));
            buffer.append(xu.formatXmlString("messages", xu.formatXmlString("message", mr.getMessage("exceptions.critical"))));
        }
        String xml = XmlUtils.toXmlString("return", buffer.toString(), false);
        logger.debug("Respuesta --> " + xml);
        WebUtils.sendAjaxResponse(response, xml);
        return null;
    }

    static Logger logger;

    static 
    {
        logger = Logger.getLogger(cl.araucana.gestorBecas.actions.commons.GetComboListAjaxAction.class);
    }
}
