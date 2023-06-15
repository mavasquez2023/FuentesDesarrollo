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

public class EstadoProcesamientoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Encargado encargado = (Encargado) request.getSession().getAttribute("edocs_encargado");
		Mejoras2016DaoImpl estProDao = new Mejoras2016DaoImpl();
		List<EstadoProcesamiento> listEstadosProcesamiento = new ArrayList<EstadoProcesamiento>();
		for (Iterator iter = encargado.getEmpresas().iterator(); iter.hasNext();) {
			Empresa empresa = (Empresa) iter.next();
			List<PeticionProcesamientoDto> listPetPro = estProDao.selectPeticionProcesamiento(empresa.getRut());
			
			int totalProcesados = 0;
			int counPetFinalizadas = 0;
			for(PeticionProcesamientoDto petPro : listPetPro){
				if("F".equalsIgnoreCase(petPro.getEstado())){
					totalProcesados+=petPro.getTotalRegProcesados();
					counPetFinalizadas++;
				}
			}
			String estado = "No informada";
			if(listPetPro.size() > 0){
				estado = (counPetFinalizadas == listPetPro.size())?"Procesada":"En proceso";
			}
			
			EstadoProcesamiento estPro = new EstadoProcesamiento();
			estPro.setEmpresa(empresa);
			estPro.setEstado(estado);
			estPro.setTotalProcesados(totalProcesados);
			listEstadosProcesamiento.add(estPro);
		}
		
		request.setAttribute("listEstadosProcesamiento", listEstadosProcesamiento);
		ActionForward forward = mapping.findForward("estadoprocesamiento");
		return forward;
	}

}
