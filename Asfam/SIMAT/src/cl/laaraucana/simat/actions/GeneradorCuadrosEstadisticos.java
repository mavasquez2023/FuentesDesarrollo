package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.documentos.CuadrosEstadisticos.EscritorCuadrosEstadisticos;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
/* 
 * clase que genera los archivos planos para simat.
 */

public class GeneradorCuadrosEstadisticos extends AbstractAction {

	public ActionForward generarCuadro_1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();

				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_1(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 1 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 1,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 1 <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);

	}//end generar CE1

	public ActionForward generarCuadro_2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_2(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 2 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 2,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 2 <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);
	}//end generar CE2

	public ActionForward generarCuadro_3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_3(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 3 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 3,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 3 <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);
	}//end generar CE3

	public ActionForward generarCuadro_4(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_4(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 4 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 4,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 4 <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);

	}//end generar CE4

	public ActionForward generarCuadro_5(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_5(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 5 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 5,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 5 <br>" + "ruta no encontrada o acceso denegado.";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);
	}//end generar CE5

	public ActionForward generarCuadro_6(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";

		ProcesaPeriodos pp = new ProcesaPeriodos();
		//obtencion estado validacion periodo
		try {
			sesionActual = request.getSession();
			periodo = (String) sesionActual.getAttribute("periodo");
			if (pp.getEstadoValidacionExpress(periodo) && pp.getEstadoCargaExpress(periodo)) {
				estadoValidacion = "validado";
			}
		} catch (Exception e) {
			msgEP = "no se ha podido evaluar el estado del periodo";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1		
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			String keyFH = hfa.getKeyFechaHora();
			try {
				EscritorCuadrosEstadisticos ece = new EscritorCuadrosEstadisticos();
				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = ece.escribirCuadro_6(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					msgEP = "Se generó correctamente el" + " Cuadro estadistico n° 6  (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura de cuadro estadistico n° 6,\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Cuadros Estadisticos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro estadistico n° 6 <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("escrituraError");
		}

		request.setAttribute("msgEscrituraPlanos", msgEP);
		request.setAttribute("fechaPeriodo", periodo);
		//set estado del periodo		
		String keyProcesoCarga = null;
		String keyProcesoValidacion = null;
		//evaluamos estado validacion del periodo
		keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);

		//evaluamos estado proceso carga del periodo	
		keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);

		//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
		request.setAttribute("keyProcesoCarga", keyProcesoCarga);

		//agregar atribute para respuesta sobre estado validacion periodo
		request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
		request.setAttribute("fechaPeriodo", periodo);
		//finish whit 
		return (forward);

	}//end generar CE6

}
