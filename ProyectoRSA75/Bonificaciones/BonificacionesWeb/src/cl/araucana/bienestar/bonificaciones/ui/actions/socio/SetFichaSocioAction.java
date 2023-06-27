package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.personal.serv.ServicesEmpleadosDelegate;
import cl.araucana.personal.vo.EmpleadoVO;

/**
 * @version 1.0
 * @author
 */
public class SetFichaSocioAction extends Action {

	Logger logger = Logger.getLogger(SetFichaSocioAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.debug("+++++En SetFichaSocioAction++++");

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale
				.getDefault());

		String rut = request.getParameter("rut");
		String source = request.getParameter("source");
		String botonera = null;
		String textoFecha = null;

		logger.debug("Source: " + source);
		Socio socio = null;
		if (source != null) {
			if (source.equals("candidato")) {
				ServicesEmpleadosDelegate delegate = new ServicesEmpleadosDelegate();
				EmpleadoVO empleado = delegate.getEmpleado(rut);
				socio = new Socio(empleado);
				logger.debug("Socio Obtenido desde Candidatos "
						+ empleado.getRut());
				textoFecha = "si";
			} else if (source.equals("socio")) {
				ServicesSociosDelegate delegate = new ServicesSociosDelegate();
				socio = delegate.getSocio(rut);
				logger.debug("Socio Obtenido desde Socios");
			}
		}
		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation) request
				.getSession(false).getAttribute("application.userinformation");
		if (userinformation == null)
			userinformation = new cl.araucana.common.ui.UserInformation();

		// Preparando la Lista de opciones validas ara la ficha
		ArrayList opciones = new ArrayList();
		ArrayList opcionesValores = new ArrayList();
		if (socio == null) {
			socio = new Socio();
		} else if (socio.getEstado().equals(Socio.STD_BORRADOR)) {
			if (userinformation.hasAccess("socActiva")) {
				opciones.add("Activar Socio");
				opcionesValores.add("1");
			}
		} else if (socio.getEstado().equals(Socio.STD_ACTIVO)) {
			logger.debug("Estado:" + socio.getEstado());
			/*
			 if (userinformation.hasAccess("socActualiza")) {
				opciones.add("Actualizar Socio");
				opcionesValores.add("5");
			}
			if (userinformation.hasAccess("socBaja")) {
				opciones.add("Dar de baja Socio");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("socActualizaCargaFam")) {
				opciones.add("Actualizar Cargas");
				opcionesValores.add("3");
			}
			if (userinformation.hasAccess("casCrea")) {
				opciones.add("Crear Caso");
				opcionesValores.add("4");
			}
			*/
			botonera = "si";
		}
		else if (socio.getEstado().equals(Socio.STD_ELIMINADO)) {
			logger.debug("Estado:" + socio.getEstado());
			/*
			if (userinformation.hasAccess("socActualiza")) {
				opciones.add("Actualizar Socio");
				opcionesValores.add("5");
			}
			if (userinformation.hasAccess("socBaja")) {
				opciones.add("Dar de baja Socio");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("socActualizaCargaFam")) {
				opciones.add("Actualizar Cargas");
				opcionesValores.add("3");
			}
			if (userinformation.hasAccess("casCrea")) {
				opciones.add("Crear Caso");
				opcionesValores.add("4");
			}
			*/
			botonera = "si";
		}

		logger.debug("fechaIngreso" + socio.getFecIng());
		
		dynaValidatorActionForm.set("fechaIngreso", formato.format(socio
				.getFecIng()));

		// pongo el objeto Socio y Opciones en memoria
		request.getSession(false).setAttribute("textoFecha", textoFecha);
		request.getSession(false).setAttribute("socio", socio);
		request.getSession(false).setAttribute("socio.opciones", opciones);
		request.getSession(false).setAttribute("socio.opciones.valores",
				opcionesValores);
		request.getSession(false).setAttribute("socio.botonera", botonera);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("socio");
		this.saveToken(request);
		return (forward);

	}
}
