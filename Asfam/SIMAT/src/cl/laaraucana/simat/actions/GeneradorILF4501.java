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

import cl.laaraucana.simat.documentos.ILF4501.EscritorILF4501;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
// clase que genera el archivo: Resumen de cotizaciones previsionales

public class GeneradorILF4501 extends AbstractAction {

	public ActionForward generarILF4501(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
				//llamar a generadorILF4501
				EscritorILF4501 eILF = new EscritorILF4501();

				LectorPropiedades lp = new LectorPropiedades();
				RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
				reVO = eILF.escribirILF4501(keyFH, periodo);
				if (reVO.isEstado()) {
					/*ruta real en donde quedan los archivos, para mostrar a usuario*/
					rutaUbicacion = lp.getAtributoRepositorio("nombreRutaPrincipal");
					rutaUbicacion += "\\" + periodo;
					msgEP = "Se generó correctamente el " + "cuadro ILF4501 (" + reVO.getNombreArchivo() + ") en la siguiente direccion: <br> <br>" + rutaUbicacion + "";
					forward = mapping.findForward("escrituraOK");
				} else {
					msgEP = "Problemas en la escritura del cuadro 'ILF4501',\n puede que el archivo este siendo usado.";
					forward = mapping.findForward("escrituraError");
				}

				//		forward = mapping.findForward("errorGeneracionPlanos");
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				System.out.println("\n[* * *]\n Error en la escritura de ILF4501: \n" + e + "\n[* * *]\n");
				msgEP = "Problemas en la escritura de cuadro 'ILF4501' <br>" + "ruta no encontrada o acceso denegado";
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

	}//end generarPlanos

}//end class
