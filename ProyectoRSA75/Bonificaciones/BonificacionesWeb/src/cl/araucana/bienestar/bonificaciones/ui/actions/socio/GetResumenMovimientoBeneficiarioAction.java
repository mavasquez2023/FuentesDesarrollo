package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosBeneficiarioVO;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;

/**
 * @version 	1.0
 * @author
 */
public class GetResumenMovimientoBeneficiarioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger("GetResumenMovimientoBeneficiarioAction");

		Socio socio=(Socio)request.getSession(false).getAttribute("socio");

		String source=request.getParameter("source");
		String target="resumenMovimientos";

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		ResumenMovimientosBeneficiarioVO resumen = new ResumenMovimientosBeneficiarioVO();
		
		resumen.setNombreCobertura((String)dynaValidatorActionForm.get("producto"));
		resumen.setTipoTope((String)dynaValidatorActionForm.get("tipoTope"));
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		
		String  inicioAnioBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/inicio-agno-bienestar");
		
		String diaInicioBienestar = inicioAnioBienestar.substring(0, 2);
		String mesInicioBienestar = inicioAnioBienestar.substring(3, 5);
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		int meshoy = hoy.get(GregorianCalendar.MONTH);
		
		int anio=hoy.get(Calendar.YEAR);
		
		logger.debug("meshoy: " + meshoy);
		logger.debug("mesInicioBienestar: " + mesInicioBienestar);		
		
		if(meshoy < Integer.parseInt(mesInicioBienestar))
			anio=hoy.get(Calendar.YEAR)-1;
		
		logger.debug("anio: " + anio);
		
		ParamResumenMovimientosVO parametros = new ParamResumenMovimientosVO();
		String fechaInicio = (String)dynaValidatorActionForm.get("fechaInicio");
		String fechaFin = (String)dynaValidatorActionForm.get("fechaFin");
		logger.debug("FECHAS " + fechaInicio + "\t" + fechaFin);
		if (fechaInicio.equals("") && fechaFin.equals("")){
			logger.debug("FECHAS CASO A");
			parametros.setFechaInicio(formato.parse(diaInicioBienestar+"/"+
													String.valueOf(Integer.parseInt(mesInicioBienestar)+1)+"/"+
													String.valueOf(anio)));
			parametros.setFechaFin(new Date());
		}
		else {
			logger.debug("FECHAS CASO B");
			parametros.setFechaInicio(formato.parse(fechaInicio));
			parametros.setFechaFin(formato.parse(fechaFin));
		}

		if(source==null) source=""; 
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		ArrayList listaMovimientos=null;
		String headerCarga=null;
		if(source.equals("socio")){
			parametros.setRutSocio(socio.getRut());
			listaMovimientos=delegate.resumenMovimientosBeneficiario(parametros,resumen);
		}
		else if(source.equals("carga")){
			Carga carga=(Carga)request.getSession(false).getAttribute("carga");
			parametros.setRutSocio(socio.getRut());
			parametros.setRutCarga(carga.getRutCarga());			
			listaMovimientos=delegate.resumenMovimientosBeneficiario(parametros,resumen);
			headerCarga="si";
		}

		request.getSession(false).setAttribute("lista.movimientos",listaMovimientos);
		request.getSession(false).setAttribute("header.carga",headerCarga);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
