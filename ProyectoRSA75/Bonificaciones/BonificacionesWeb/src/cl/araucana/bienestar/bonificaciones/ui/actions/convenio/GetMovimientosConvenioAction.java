package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosConvenioVO;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;

/**
 * @version 	1.0
 * @author
 */
public class GetMovimientosConvenioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger("GetMovimientosConvenioAction");

		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");

		String target="resumenMovimientos";

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		ResumenMovimientosConvenioVO resumen = new ResumenMovimientosConvenioVO();
		
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());			
		
		String  inicioAnioBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/inicio-agno-bienestar");
		
		String diaInicioBienestar = inicioAnioBienestar.substring(0, 2);
		String mesInicioBienestar = inicioAnioBienestar.substring(3, 5);
		
		GregorianCalendar hoy = new GregorianCalendar();			
		int anio=hoy.get(Calendar.YEAR);
		
		ParamResumenMovimientosVO parametros = new ParamResumenMovimientosVO();
		String fechaInicio = (String)dynaValidatorActionForm.get("fechaInicio");
		String fechaFin = (String)dynaValidatorActionForm.get("fechaFin");
		logger.debug("FECHAS " + fechaInicio + "\t" + fechaFin);
		if (fechaInicio.equals("") && fechaFin.equals("")){
			logger.debug("FECHAS CASO A");
			parametros.setFechaInicio(formato.parse(diaInicioBienestar+"/"+mesInicioBienestar+"/"+String.valueOf(anio)));
			parametros.setFechaFin(formato.parse(diaInicioBienestar+"/"+mesInicioBienestar+"/"+String.valueOf((anio+1))));
		}
		else {
			logger.debug("FECHAS CASO B");
			parametros.setFechaInicio(formato.parse(fechaInicio));
			parametros.setFechaFin(formato.parse(fechaFin));
		}

		resumen.setNombreProducto((String)dynaValidatorActionForm.get("producto"));
		if(((String)dynaValidatorActionForm.get("codigo")).trim().equals("")){
			resumen.setCodigoProducto(0);
		}
		else{
			resumen.setCodigoProducto((long)Long.parseLong((String)dynaValidatorActionForm.get("codigo")));
		}
		
		parametros.setCodigoConvenio(convenio.getCodigo());
		ArrayList listaMovimientos=delegate.resumenMovimientosConvenio(parametros,resumen);
 
		request.getSession(false).setAttribute("lista.movimientos",listaMovimientos);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
