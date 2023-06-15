package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.EstadoProcesamiento;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.Parametros;

public class EstadoProcesamientoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Encargado encargado = (Encargado) request.getSession().getAttribute("edocs_encargado");
		Mejoras2016DaoImpl estProDao = new Mejoras2016DaoImpl();
		List<EstadoProcesamiento> listEstadosProcesamiento = new ArrayList<EstadoProcesamiento>();
		String periodo= Parametros.getInstance().getParam().getPeriodoProceso();
		for (Iterator iter = encargado.getEmpresas().iterator(); iter.hasNext();) {
			Empresa empresa = (Empresa) iter.next();
			PeticionProcesamientoDto petPro = estProDao.selectPeticionProcesamiento(empresa.getRut(), Integer.parseInt(periodo));
			
			int totalProcesados = 0;
			int totalinformados= 0;
			int totalNoDeclarados= 0;
			String estado = "No informada";
			//estado = "En proceso";
			if(petPro!=null && "F".equalsIgnoreCase(petPro.getEstado())){
				totalProcesados=petPro.getTotalRegProcesados();
				totalinformados=petPro.getTotalRegInformados();
				totalNoDeclarados= petPro.getTotalRegNoDeclarados();
				estado= "Procesada";
			}

			
			EstadoProcesamiento estPro = new EstadoProcesamiento();
			estPro.setEmpresa(empresa);
			estPro.setEstado(estado);
			estPro.setTotalProcesados(totalProcesados);
			estPro.setTotalInformados(totalinformados);
			estPro.setTotalNoDeclarados(totalNoDeclarados);
			listEstadosProcesamiento.add(estPro);
		}
		
		request.setAttribute("listEstadosProcesamiento", listEstadosProcesamiento);
		ActionForward forward = mapping.findForward("estadoprocesamiento");
		return forward;
	}
	
}
