package cl.laaraucana.silmsil.actions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.ws.objectManager.Iterator;

import cl.araucana.core.registry.User;
import cl.laaraucana.silmsil.forms.ProcesarForm;
import cl.laaraucana.silmsil.manager.ManagerInicio;
import cl.laaraucana.silmsil.manager.Mes;
import cl.laaraucana.silmsil.manager.Trimestre;
import cl.laaraucana.silmsil.vo.EstadoVO;

/**
 * @version 1.0
 * @author
 */
public class ListadoPrincipalAction extends Action {

	private Logger log = Logger.getLogger(this.getClass());
	
	private String msj = "";
	
	
	/**
	 * Carga listado inicial en interfaz gráfica index.jsp.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward(); // return value
		log.info("Ingreso al ListadoPrincipalAction");
		ProcesarForm forms = (ProcesarForm)form;
		try {
			String ahno = getDateAhno();
			log.info("Anno en actions = " + ahno);
			
			ArrayList<Trimestre> listaTabla = new ArrayList<Trimestre>();
			ManagerInicio manager = new ManagerInicio();
			listaTabla = manager.cargarListadoInterfaz(ahno);
			
			HttpSession sesion = request.getSession(true);
			Principal userPrincipal = request.getUserPrincipal();
			String user =  userPrincipal.getName();
			
			sesion.setAttribute("login", user);
			request.setAttribute("msj", msj);
			request.setAttribute("selAhno", ahno);
			request.setAttribute("listaTabla", listaTabla);
			forward = mapping.findForward("cargarListadoInicial");
			
		} catch (Exception e) {
			forward = mapping.findForward("cargarError");
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}
	
	/**
	 * Método para generar fecha actual.
	 * @return
	 */
	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String date = sdf.format(new Date());
		return date;
	}
	
	/**
	 * Método para generar año actual.
	 * @return
	 */
	private static String getDateAhno(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(new Date());
		return date;
	}
	
}
