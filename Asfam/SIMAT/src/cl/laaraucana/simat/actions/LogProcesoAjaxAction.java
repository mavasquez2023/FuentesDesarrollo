package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.LogProcesosVO;
import cl.laaraucana.simat.forms.LogProcesosForm;
import cl.laaraucana.simat.mannagerDB2.LogProcesosMannager;

/**
 * @version 	1.0
 * @author
 */
public class LogProcesoAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		LogProcesosForm logProcesosForm = (LogProcesosForm) form;
		LogProcesosMannager mannager = new LogProcesosMannager();
		LogProcesosVO proceso = new LogProcesosVO();
		String datosActualizar = "";

		proceso.setId_registro(logProcesosForm.getId_registro());
		proceso = mannager.BuscarByID(proceso);

		if (proceso != null) {

			datosActualizar = proceso.getTipo_log() + "," + proceso.getFecha_hora() + "," + proceso.getPeriodo() + "," + proceso.getId_usuario() + "," + proceso.getProceso_afectado() + ","
					+ proceso.getTabla() + "," + proceso.getRegistro_id() + "," + proceso.getDescripcion();

		} else {
			System.out.println("no, encontrado");
		}

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		System.out.println(" get form");

		out.println(datosActualizar);
		out.flush();
		System.out.println(" antes return");

		return null;

	}
}
