package cl.araucana.fonasa.main.actions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.fonasa.business.impl.WSConsultaFonasaImpl;
import cl.araucana.fonasa.business.to.ResponseFormFonasaTO;
import cl.araucana.fonasa.dao.VO.FormularioVO;
import cl.araucana.fonasa.main.dao.ConsultaServicesDAO;
import cl.araucana.fonasa.utils.Utils;



/**
 * @version 1.0
 * @author
 */
public class ValidacionFonasaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
		
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		String usuario= (String)sesion.getAttribute("usuario");		
		request.setAttribute("menu", "fonasa");
		String accion = request.getParameter("accion");
		
		if(accion==null){
			accion="";
		}
		
		if(accion.equals("menu")){
			forward = mapping.findForward("success");
			return forward;
		}
			
		try {
			List<FormularioVO> listaTipoNumForm= new ArrayList<FormularioVO>();
			String tipoFormulario= request.getParameter("tipo");
			String folios= request.getParameter("folios");
			logger.info("Validar Tipo Form:" + tipoFormulario + ", Folios:" + folios);
			String[] listafolios= folios.split(",");
			for (int i = 0; i < listafolios.length; i++) {
				FormularioVO formu= new FormularioVO();
				formu.setTipoFormulario(Integer.parseInt(tipoFormulario));
				formu.setNumeroLicencia(Integer.parseInt(listafolios[i].trim()));
				formu.setRutAfiliado("1-9");
				listaTipoNumForm.add(formu);
			}
			WSConsultaFonasaImpl wsconsulta= new WSConsultaFonasaImpl();
			String horaInicio= Utils.getHora();
			int totalValidaciones= listaTipoNumForm.size();
			List<FormularioVO> lista_erroneos= wsconsulta.consultaListaFormulario(listaTipoNumForm);
			
			if(lista_erroneos==null){
				request.setAttribute("error", "1");
			}else if(lista_erroneos.size()==0){
				request.setAttribute("error", "9");
			}else{
				request.setAttribute("error", "0");
				request.setAttribute("lista_erroneos", lista_erroneos);
			}
			//Insert Bitácora
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			Map<String, String> param= new HashMap<String, String>();
			param.put("totalValidados", String.valueOf(totalValidaciones));
			param.put("estadono72", String.valueOf(lista_erroneos.size()-wsconsulta.getNum_timeout()));
			param.put("timeout", String.valueOf(wsconsulta.getNum_timeout()));
			param.put("observacion", "Particular");
			param.put("usuario", usuario);
			param.put("nombreArchivo", "");
			param.put("horaInicio", horaInicio);
			consultaDAO.insertBitacora(param);
			
			forward = mapping.findForward("success");
			return (forward);


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Validación Particular licencias:" + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al validar:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		sesion.removeAttribute("repetido");
		forward = mapping.findForward("success");
		return (forward);

	}
	
	
	    
	    
}
