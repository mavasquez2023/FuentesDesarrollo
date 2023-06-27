package cl.araucana.bienestar.bonificaciones.ui.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;

/**
 * @version 	1.0
 * @author	abilbao
 */
public class PrepareOptionAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		HttpSession sesion = request.getSession();
			
		System.out.println(mapping.getPath());
		System.out.println(mapping.getName());
		System.out.println(request.getContextPath());		
	
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		request.getSession(false).setAttribute("fechaHoy", formatter2.format(currentDate));

		calendar.add(Calendar.DATE, 1);
		currentDate = calendar.getTime();
		request.getSession(false).setAttribute("currentDate", formatter.format(currentDate));

		calendar.add(Calendar.DATE, -1);
		currentDate = calendar.getTime();
		request.getSession(false).setAttribute("currentDate2", formatter.format(currentDate));

		String  urlSistemaRoles = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		  "/application-settings/common/urlRoles");
		
		sesion.setAttribute("urlSistemaRoles", urlSistemaRoles);
		
		String  inicioAnioBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/inicio-agno-bienestar");
		
		String diaInicioBienestar = inicioAnioBienestar.substring(0, 2);
		String mesInicioBienestar = inicioAnioBienestar.substring(3, 5);
		
		GregorianCalendar hoy = new GregorianCalendar();
		int anio = calendar.get(Calendar.YEAR);
		int meshoy = hoy.get(GregorianCalendar.MONTH);
		
		if(meshoy < Integer.parseInt(mesInicioBienestar))
			anio=anio-1;		

		request.getSession(false).setAttribute(
			"fechaInicioBienestar",
			diaInicioBienestar
				+ "/"
				+ String.valueOf(Integer.parseInt(mesInicioBienestar)+1)
				+ "/"
				+ String.valueOf(anio));
		
		// Obtiene el destino solicitado
		String target=null;
		String destino=request.getParameter("destino");

		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)sesion.getAttribute("application.userinformation");
		if (userinformation==null) 
			userinformation = new cl.araucana.common.ui.UserInformation();
 
//		ServicesCasosDelegate ser = new ServicesCasosDelegate();
		String rut = userinformation.getRut().substring(0,userinformation.getRut().length()-2);
		char dv = userinformation.getRut().substring(userinformation.getRut().length()-1,userinformation.getRut().length()).toCharArray()[0];
		Socio socio = new Socio();
		socio.setRut(rut);
		socio.setDigito(dv);
		sesion.setAttribute("socio",socio);
		
		if (destino==null){

//			 int grupo =  ser.getGrupoUsuario(new Long(rut).longValue());
//			 sesion.setAttribute("grupoUsuario","1");
			
			 target="bonificaciones"; 
		}
		else if (destino.equals("socios")) {
			target="socios";
		}
		else if (destino.equals("sociosInactivos")) {
			target="sociosInactivos";
		}
		else if (destino.equals("operaciones")){
			target="operaciones"; 
		}
		else if (destino.equals("coberturas")){
			target="coberturas"; 
		}
		else if (destino.equals("convenios")){
			target="convenios"; 
		}
		else if (destino.equals("casos")){
			target="casos"; 
		}
		else if (destino.equals("listaPreCasos")){
			target="listaPreCasos"; 
		}		
		else if (destino.equals("reportes")){
			target="reportes"; 
		}
		else if (destino.equals("configuracion")){
			target="noservice"; 
		}
		else if (destino.equals("noservice")){
			target="noservice"; 
		}
		
		ServicesSociosDelegate servSocios = new ServicesSociosDelegate();
		List sociosInactivos = servSocios.getSociosInactivosConCasosAbiertos();
		request.setAttribute("sociosInactivos", sociosInactivos);
		Integer sociosInactivosSize = new Integer(sociosInactivos.size());
		request.setAttribute("sociosInactivosSize", sociosInactivosSize);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}
