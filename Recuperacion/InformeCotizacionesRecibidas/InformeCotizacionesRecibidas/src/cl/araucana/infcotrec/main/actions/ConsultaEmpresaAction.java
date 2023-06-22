package cl.araucana.infcotrec.main.actions;

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

import cl.araucana.infcotrec.dao.VO.PagosVO;
import cl.araucana.infcotrec.dao.VO.ParamVO;
import cl.araucana.infcotrec.dao.VO.SalidaVO;
import cl.araucana.infcotrec.main.dao.ConsultaPagosDAO;
import cl.araucana.infcotrec.utils.CertificadoConst;
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
		int rutemp=0;
		int idParam=0;
		try {
			HttpSession sesion = request.getSession();
			sesion = request.getSession();
			SalidaVO salida=null;
			//se instancia e invoca DAO para obtener deuda empresa
			ConsultaPagosDAO deudaDAO= new ConsultaPagosDAO();
			
			idParam = Integer.parseInt(request.getParameter("id"));
			logger.info("Id Seleccionado:" + idParam);

			
			//Rescatando lista empresas de sesión o de request en caso de venir null
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
				paramConsulta.setPeriodo(_periodo);
				paramVO= deudaDAO.consultaSucursalPagosEmpresa(paramConsulta);
			}else{
				logger.info("lista empresas" + params.size());
				paramVO= params.get(idParam);
				rutemp = paramVO.getRutEmpresa();
			}
			
			//Calculando periodo y cantidad meses
			int cantMeses= Integer.parseInt(CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.meses"));
			int mesesParam=cantMeses;
			if(Utils.obtenerDiaActual()<15){
				cantMeses++;
			}
			Integer maxPeriodo= (Integer)request.getSession().getAttribute("maxperiodo");
			if(maxPeriodo== null || maxPeriodo.equals(0)){
				maxPeriodo= deudaDAO.consultaMaxPeriodoCotizacion(rutemp);
			}

			String periodo= Utils.obtenerPeriodoCualquiera(String.valueOf(maxPeriodo), cantMeses*-1);
			
			//Consultando Pagos Empresa
			logger.info("Consultando Pagos Empresa");
			paramVO.setPeriodo(periodo);
			List<PagosVO> listaPagos= deudaDAO.consultaPagosEmpresa(paramVO);

			logger.info("Cantidad registros retornados:" + listaPagos.size());
			if(listaPagos.size()>0){
				//listaPagos.add(0, new PagosVO());
				salida= new SalidaVO();
				salida.setParam(paramVO);
				salida.setPagos(listaPagos);
			}

			sesion.setAttribute("salida", salida);
			sesion.setAttribute("meses", mesesParam);
			sesion.setAttribute("fechaEmision", Utils.getFechaCompleta());
			//sesion.setAttribute("id", id);
			request.setAttribute("error", "0");


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Rut: " + rutemp + "id:" + idParam);
			e.printStackTrace();
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
