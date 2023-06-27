/*
 * Creado el 14-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosVO;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class GetInfoDsctoSocioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetInfoDsctoSocioAction.class);
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
	
		StringTokenizer st = new StringTokenizer(userinformation.getRut(),"-");				
		String rut=st.nextToken();   								
		String codigo=request.getParameter("codigo");
		
		int cod=0;
		if(codigo==null) codigo="";
		if(!codigo.equals("")){
			cod=Integer.parseInt(codigo);
		}
		
		ArrayList listaDescuentos=(ArrayList)request.getSession(false).getAttribute("lista");
		//InformeDescuentosVO reporte = delegate.getInformeDescuentos((InformeDescuentosVO)listaDescuentos.get(cod));
		InformeDescuentosVO informeDescuentosVO = (InformeDescuentosVO)listaDescuentos.get(cod);

		logger.debug("RUT : "+rut);
		logger.debug("COD : "+cod);
						
		DetalleDescuentosSocioVO reporte = delegate.getDetalleDescuentoSocio(rut,informeDescuentosVO.getCodigoDescuento());
							
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("reporte",reporte);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("infoDsctoSocio");
		return (forward);

	}
}

