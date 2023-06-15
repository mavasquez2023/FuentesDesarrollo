/*#################################################################*/
/*---- FECHA CREACION: 1-08-2013 ----------------------------------*/
/*---- NOMBRE: Matias Salas S. ------------------------------------*/
/*---- EMPRESA: IBM -----------------------------------------------*/
/*#################################################################*/

package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.documentos.InformeFinanciero.CompletarInformeFinanciero;
import cl.laaraucana.simat.documentos.InformeFinanciero.DiferenciaInformeFinanciero;
import cl.laaraucana.simat.documentos.InformeFinanciero.EscritorInformeFinanciero;
import cl.laaraucana.simat.documentos.InformeFinanciero.ValidarInformeFinanciero;
import cl.laaraucana.simat.entidades.InformeFinancieroVO;
import cl.laaraucana.simat.entidades.InformeFinanciero_jasperVO;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */

/*
 * action que contiene metodos para mostrar el informe financiero y escribirlo.
 * 
 */

public class GeneradorInformeFinanciero extends AbstractAction {

	/**
	 * Metodo que permite grabar los valores de la columna balance general del Informe financiero,
	 * en archivo xls y xml, en la carpeta definida como repositorio.
	 * 
	 * **/
	public ActionForward generarIF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//metodo que toma los valores 
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";
		ProcesaPeriodos pp = new ProcesaPeriodos();
		System.out.println(" * * * * * [ ACTION generador IF ] * * * * *");
		//	   obtencion estado validacion periodo
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
				EscritorInformeFinanciero eif = new EscritorInformeFinanciero();

				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = eif.escribirInformeFinanciero(periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					msgEP = "Se generó correctamente el " + "Informe Financiero (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "No se generó el ";
					msgEP = "Problemas en la escritura del cuadro 'Informe Financiero',\n puede que uno de los archivos este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//			forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de Informe Financiero: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de  'Informe Financiero' <br>" + "ruta no encontrada o acceso denegado";
				forward = mapping.findForward("escrituraError");
			}
		} else {
			System.out.println("uno de los procesos no esta terminado.");
			msgEP = "El estado de los procesos no esta terminado, no puede generar el " + "'Informe Financiero' sin terminar los procesos";
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
	}//END generar IF

	/**
	 * Metodo que permite acceder al visor web del Iforme financiero,
	 * los valores iniciales se mantendran en cero.
	 * 
	 * 
	 * **/
	public ActionForward visorIF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = "";
		ProcesaPeriodos pp = new ProcesaPeriodos();
		System.out.println(" * * * * * [ ACTION visor IF ] * * * * *");
		//   obtencion estado validacion periodo
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
				InformeFinancieroVO informeVO = new InformeFinancieroVO(0, "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
				ArrayList listaIF_balanceGeneral = new ArrayList();
				ArrayList listaIF_cuadratura = new ArrayList();
				ArrayList listaIF_diferencia = new ArrayList();
				listaIF_balanceGeneral.add(informeVO);
				listaIF_cuadratura.add(informeVO);
				listaIF_diferencia.add(informeVO);
				request.setAttribute("listaIF_balanceGeneral", listaIF_balanceGeneral);
				request.setAttribute("listaIF_cuadratura", listaIF_cuadratura);
				request.setAttribute("listaIF_diferencia", listaIF_diferencia);
				forward = mapping.findForward("successVisorIF");

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error al acceder al visor de Informe Financiero: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en visor web de  'Informe Financiero' <br>";
				forward = mapping.findForward("ErrorVisorIF");
			}
		} else {
			System.out.println("estado no validado");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("ErrorVisorIF");
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
	}//end VisorIF

	/**
	 * Metodo que permite cargar los valores del Informe financiero,
	 * desde el balance general, la base datos, y mostrar la diferencia entre ellos.
	 * 
	 * **/
	public ActionForward cargarIF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String estadoValidacion = "";
		HttpSession sesionActual = null;
		String rutaUbicacion = null;
		ProcesaPeriodos pp = new ProcesaPeriodos();
		System.out.println(" * * * * * [ ACTION generador IF ] * * * * *");
		//   obtencion estado validacion periodo
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
				InformeFinancieroVO informeVO_BG = new InformeFinancieroVO(0, "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
				InformeFinancieroVO informeVO_RC = new InformeFinancieroVO(0, "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
				InformeFinancieroVO informeVO_D = new InformeFinancieroVO(0, "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);

				ArrayList listaIF_balanceGeneral = new ArrayList();
				ArrayList listaIF_cuadratura = new ArrayList();
				ArrayList listaIF_diferencia = new ArrayList();

				//			obtenemos lectura de datos desde balance general, por regla de negocio, este informe fiannciero se guardara en BD, porque debe ser exportado hacia "XLS" y "XML"
				CompletarInformeFinanciero cif = new CompletarInformeFinanciero();
				informeVO_BG = cif.obtenerDatosBalanceGeneral_IF();

				//			obtenemos valores para IF desde las tablas de BD SIMAT
				ValidarInformeFinanciero vif = new ValidarInformeFinanciero();
				informeVO_RC = vif.CuadrarInformeFinanciero(informeVO_BG);

				//			obtenemos diferencia entre ifBalance y ifCuadratura
				DiferenciaInformeFinanciero dif = new DiferenciaInformeFinanciero();
				informeVO_D = dif.obtenerDiferenciaIF(informeVO_BG, informeVO_RC);
				InformeFinanciero_jasperVO IF_jasper;
				/*
				listaIF_balanceGeneral.add(informeVO_BG);
				listaIF_cuadratura.add(informeVO_RC);
				listaIF_diferencia.add(informeVO_D);
				*/
				IF_jasper = new InformeFinanciero_jasperVO(informeVO_BG);
				listaIF_balanceGeneral.add(IF_jasper);

				IF_jasper = new InformeFinanciero_jasperVO(informeVO_RC);
				listaIF_cuadratura.add(IF_jasper);

				IF_jasper = new InformeFinanciero_jasperVO(informeVO_D);
				listaIF_diferencia.add(IF_jasper);

				request.setAttribute("listaIF_balanceGeneral", listaIF_balanceGeneral);
				request.setAttribute("listaIF_cuadratura", listaIF_cuadratura);
				request.setAttribute("listaIF_diferencia", listaIF_diferencia);

				forward = mapping.findForward("successCargarIF");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en lectura de datos para completar Informe Financiero: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en lectura de datos para completar 'Informe Financiero' <br>";
				forward = mapping.findForward("ErrorCargarIF");
			}
		} else {
			System.out.println("uno de los procesos no esta terminado.");
			msgEP = "El estado de los procesos no ha Finalizado.\n Debe esperar a que Terminen de ejecutarse.";
			request.setAttribute("msgEscrituraPlanos", msgEP);
			forward = mapping.findForward("ErrorCargarIF");
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
		//forward = mapping.findForward("ErrorCargarIF");
	} //end cargarIF

}//end generar informe financiero

