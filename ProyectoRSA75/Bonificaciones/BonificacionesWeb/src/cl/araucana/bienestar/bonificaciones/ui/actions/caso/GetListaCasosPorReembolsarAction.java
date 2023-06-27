package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoTotalVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosPorReembolsarAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger("GetListaCasosPorReembolsarAction");

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		ServicesOperacionesDelegate delegateOperaciones = new ServicesOperacionesDelegate();
		
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

		long totalReembolso = 0;
		ArrayList listaTotales=delegate.getCasosPorReembolsar(param);
		ArrayList listaReembolsos = new ArrayList();
		ArrayList listaSinBanco = new ArrayList();
		for(int i=0;i<listaTotales.size();i++){
			ReembolsoVO reembolsoVO = (ReembolsoVO)listaTotales.get(i);
			if(reembolsoVO.getBanco() != null && !reembolsoVO.getBanco().equals("")){
				totalReembolso = totalReembolso + (long)reembolsoVO.getMontoReembolso();
				listaReembolsos.add(listaTotales.get(i));
			}else{
				listaSinBanco.add(listaTotales.get(i));
			}
		}
		
		request.getSession(false).setAttribute("totalReembolso",new Long(totalReembolso));
		
		ReembolsoTotalVO reembolsoTotalVO = delegateOperaciones.getUltimoReembolsoTotal();
		
		SimpleDateFormat formato = new SimpleDateFormat ("yyyyMMdd", Locale.getDefault());
		String ultimoReembolso = formato.format(reembolsoTotalVO.getFecha());
		String hoy = formato.format(new Date());

		if(ultimoReembolso.equals(hoy)){
			request.getSession(false).removeAttribute("opciones");
			request.getSession(false).removeAttribute("opciones.valores");			
		}else{
			ArrayList opciones=new ArrayList();
			ArrayList opcionesValores=new ArrayList();
			if (userinformation.hasAccess("opeGeneraReembolso")) {
				opciones.add("Generar Reembolso");
				opcionesValores.add("1");
			}
			request.getSession(false).setAttribute("opciones",opciones);
			request.getSession(false).setAttribute("opciones.valores",opcionesValores);			
		}
		
		// Guardar en memoria el resultado
		String target = "";
		if (listaSinBanco.size()==0){
			target = "listaReembolso";
		}else{
			target = "verificaReembolso";
			request.getSession(false).setAttribute("lista.sinBanco",listaSinBanco);
		}
		request.getSession(false).setAttribute("lista.reembolsos",listaReembolsos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
