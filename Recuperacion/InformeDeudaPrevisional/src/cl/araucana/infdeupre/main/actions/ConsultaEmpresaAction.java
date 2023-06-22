package cl.araucana.infdeupre.main.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.infdeupre.dao.VO.DeudaVO;
import cl.araucana.infdeupre.dao.VO.DeudaVO;
import cl.araucana.infdeupre.dao.VO.ParamVO;
import cl.araucana.infdeupre.dao.VO.SalidaVO;
import cl.araucana.infdeupre.main.dao.ConsultaDeudaDAO;
import cl.araucana.infdeupre.main.forms.ConsultaDeudaForm;
import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.FormUtils;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class ConsultaEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value

		/*ConsultaDeudaForm formCRM = (ConsultaDeudaForm) form;
			if(formCRM!=null){
				String rut = formCRM.getRut();
			}
		 */
		HttpSession sesion = request.getSession();
		sesion = request.getSession();
		SalidaVO salida=null;
		//se instancia e invoca DAO para obtener deuda empresa
		ConsultaDeudaDAO deudaDAO= new ConsultaDeudaDAO();
		int rutemp=0;
		int idParam = Integer.parseInt(request.getParameter("id"));
		List<ParamVO> params= (List<ParamVO>)sesion.getAttribute("empresas");
		ParamVO paramVO=null;
		if(params== null){
			rutemp= Integer.parseInt(request.getParameter("rutEmpresa"));
			int oficina= Integer.parseInt(request.getParameter("oficina"));
			int sucursal= Integer.parseInt(request.getParameter("sucursal"));
			String _periodo= request.getParameter("periodo");
			ParamVO paramConsulta= new ParamVO();
			paramConsulta.setRutEmpresa(rutemp);
			paramConsulta.setOficina(oficina);
			paramConsulta.setSucursal(sucursal);
			paramVO= deudaDAO.consultaSucursalDeudaEmpresa(paramConsulta);
		}else{
			logger.info("lista empresas" + params.size());
			paramVO= params.get(idParam);
			rutemp = paramVO.getRutEmpresa();
		}
		
		int oficina = paramVO.getOficina();
		int sucursal = paramVO.getSucursal();
		String idCertificado="";

		logger.info("Informe Deuda Previsional, RutEmpresa:" + rutemp+"-" + paramVO.getDvEmpresa() + ", Oficina:" + oficina + ", Sucursal:" + sucursal);

		try {
			//Se arman parámetros para consulta
			ParamVO llave= new ParamVO();
			llave.setRutEmpresa(rutemp);
			llave.setOficina(oficina);
			llave.setSucursal(sucursal);

			//se instancia e invoca DAO para obtener deuda empresa
			logger.info("Consultando Deuda Empresa");
			List<DeudaVO> listaDeudas= deudaDAO.consultaDeudaEmpresa(llave);

			logger.info("Cantidad registros retornados:" + listaDeudas.size());
			
			if(listaDeudas.size()>0){
				request.setAttribute("error", "0");
			}else{
				request.setAttribute("error", "-1");
			}
			
			salida= new SalidaVO();
			listaDeudas.add(0, new DeudaVO());
			salida.setParam(paramVO);
			salida.setDeuda(listaDeudas);
			
			sesion.setAttribute("salida", salida);
			//sesion.setAttribute("deuda", listaDeudas);
			sesion.setAttribute("fechaEmision", Utils.getFechaCompleta());
			//sesion.setAttribute("id", id);
			

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Rut: " + rutemp);
			RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
			rq.forward(request, response);					
		}

		forward = mapping.findForward("success");
		return (forward);

	}
}
