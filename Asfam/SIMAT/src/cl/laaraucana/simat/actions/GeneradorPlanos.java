/*#################################################################*/
/*---- FECHA CREACION: 1-08-2013 ----------------------------------*/
/*---- NOMBRE: Matias Salas S. ------------------------------------*/
/*---- EMPRESA: IBM -----------------------------------------------*/
/*#################################################################*/

package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.documentos.ArchivosPlanos.CompletarArchivosPlanos;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/**
 * @version 	1.0
 * @author
 */
// clase que genera los archivos planos para simat.

public class GeneradorPlanos extends AbstractAction {

	public ActionForward generarPlano_1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		/*ruta real en donde quedan los archivos, para mostrar a usuario*/
		//String rutaMuestra=lp.getAtributoRepositorio("nombreRutaPrincipal");

		if (estadoValidacion.equals("validado")) {
			//escritura de Cuadro estadistico1			 
			try {
				//escritura de planos 1
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_1(periodo)) {
					lp = new LectorPropiedades();
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					msgEP = "Se generó correctamente el" + " plano 1 'REINTEGROS' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					rutaUbicacion = "";
					msgEP = "Problemas en la escritura del plano 1 'REINTEGROS',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 1 'REINTEGROS' <br>" + "ruta no encontrada o acceso denegado";

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

	}//end generarPlanos 1

	public ActionForward generarPlano_2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			try {
				//escritura de planos 1
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_2(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 2 'SUBPREPOSTNM' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 2 'SUBPREPOSTNM',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}
				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 2 'SUBPREPOSTNM' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 2

	public ActionForward generarPlano_3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			try {
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_3(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal										
					msgEP = "Se generó correctamente el" + " plano 3 'SUBSPARENTAL' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 3 'SUBSPARENTAL',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 3 'SUBSPARENTAL' <br>" + "ruta no encontrada o acceso denegado";
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

	}//end generarPlanos3

	public ActionForward generarPlano_4(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			try {
				//escritura de planos 1
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_4(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 4 'SUBSTSCVIG' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 4 'SUBSTSCVIG',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 4 'SUBSTSCVIG' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 4

	public ActionForward generarPlano_5(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = null;

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
			try {
				//escritura de planos 1
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_5(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 5 'CONTROLDOCU' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 5 'CONTROLDOCU',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 5 'CONTROLDOCU' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 5

	public ActionForward generarPlano_6(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = null;

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
			try {
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_6(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 6 'DOCSREVALREEM' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 6 'DOCSREVALREEM',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 6 'DOCSREVALREEM' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 6

	public ActionForward generarPlano_7(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = null;

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
			try {
				//escritura de planos 7
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_7(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 7 'DATOSLICCOB' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "No se generó el plano 7 'DATOSLICCOB'";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 7 'DATOSLICCOB' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 7

	public ActionForward generarPlano_8(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = null;

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
			try {
				//escritura de planos 8
				CompletarArchivosPlanos cap = new CompletarArchivosPlanos();
				LectorPropiedades lp = new LectorPropiedades();
				if (cap.completarPlano_8(periodo)) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					//	mensajes requeridos por el menu principal					
					msgEP = "Se generó correctamente el" + " plano 8 'DATOSLICRESOL' en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del plano 8 'DATOSLICRESOL',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de planos: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura del plano 8 'DATOSLICRESOL' <br>" + "ruta no encontrada o acceso denegado";
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
	}//end generarPlanos 8
}
