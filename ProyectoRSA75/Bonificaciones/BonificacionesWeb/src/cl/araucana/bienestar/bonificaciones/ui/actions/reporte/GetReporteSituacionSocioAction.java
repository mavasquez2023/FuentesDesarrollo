package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.exception.UFNoEncontradaLeasingException;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.InformeSocioVO;

/**
 * @version 	1.0
 * @author
 */
public class GetReporteSituacionSocioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
									
		String rut=request.getParameter("rut");
		InformeSocioVO reporte = null;
		
		Calendar cal = new GregorianCalendar();
		
		if(rut==null) rut="";
		if(rut.equals("")){
			reporte = new InformeSocioVO();
		}
		else{
			ServicesSociosDelegate delegate = new ServicesSociosDelegate();
			
			String fechaUF = (String) dynaValidatorActionForm.get("fechaUF");

			
			if(!fechaUF.equals("")){
				int dia = new Integer(fechaUF.substring(0,2)).intValue();
				int mes = new Integer(fechaUF.substring(3,5)).intValue();
				int anio = new Integer(fechaUF.substring(6,10)).intValue();
			
				cal.set(Calendar.YEAR,anio);
				cal.set(Calendar.MONTH,mes - 1);
				cal.set(Calendar.DATE,dia);
			}
			
			try{
			
				reporte = delegate.getInformeSocio(rut,new java.sql.Date(cal.getTime().getTime()));
			
				
			}catch(UFNoEncontradaLeasingException ufEx){
				request.setAttribute("error.usermessage","El valor de la UF para la fecha seleccionada no existe en el sistema de Leasing.");
			}
		}
									
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());

		//Calendar calendar = Calendar.getInstance();
		//String fechaHoy = formato2.format(calendar.getTime());	
		dynaValidatorActionForm.set("fechaUF",formato.format(cal.getTime()));
		request.setAttribute("valorUF","1234");
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("reporte",reporte);
		request.getSession(false).setAttribute("socio",reporte.getSocio());

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("reporteSocio");
		return (forward);

	}
}
