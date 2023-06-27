package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosPorDescontarAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		Logger logger = Logger.getLogger(GetListaCasosPorDescontarAction.class);
		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		
		//CODIGO AGREGADO, obtiene la lista de todos los casos de descuento que hay
		ArrayList listaCasos=delegate.getCasosNoBonificados("");
		delegate = null;
		boolean existeCasoDescuento = false;
		for(int i = 0; i < listaCasos.size(); i++){
			if (((Caso) listaCasos.get(i)).getTipoCaso().equals("DESCUENTO")){
				existeCasoDescuento = true;
				break;
			}
		}
		if (existeCasoDescuento){
			// Write logic determining how the user should be forwarded.
			ActionForward forward = new ActionForward();
			forward = mapping.findForward("valida");
			return (forward);
		} else {
			delegate = new ServicesCasosDelegate();
			String fechaOcurrencia = (String)dynaValidatorActionForm.get("fechaOcurrencia");
			logger.debug("FECHA OCURRENCIA HASTA " + fechaOcurrencia);
			Calendar calendar = Calendar.getInstance();
			Date fechaHasta;
			if (fechaOcurrencia.equals("")){
				logger.debug("FECHAS CASO A");
				fechaHasta = calendar.getTime();
			}
			else {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				fechaHasta = formatter.parse(fechaOcurrencia);
			}

			ParamOperacionesVO param = new ParamOperacionesVO();
			param.setFechaFin(fechaHasta);

			ArrayList listaDescuentos=delegate.getCasosPorDescontar(param);

			ArrayList opciones=new ArrayList();
			ArrayList opcionesValores=new ArrayList();
			if (userinformation.hasAccess("opeGeneraDescuento")) {
				opciones.add("Informar Descuento");
				opcionesValores.add("1");
			}
		
			// Guardar en memoria el resultado
			request.getSession(false).setAttribute("opciones",opciones);
			request.getSession(false).setAttribute("opciones.valores",opcionesValores);
			request.getSession(false).setAttribute("lista.descuentos",listaDescuentos);

			// Write logic determining how the user should be forwarded.
			ActionForward forward = new ActionForward();
			forward = mapping.findForward("listaDescuento");
			return (forward);
		}


	}
}
