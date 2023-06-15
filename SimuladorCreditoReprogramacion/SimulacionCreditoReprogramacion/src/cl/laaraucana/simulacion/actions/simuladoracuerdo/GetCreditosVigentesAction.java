package cl.laaraucana.simulacion.actions.simuladoracuerdo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
//import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.SalidaCampañaVO;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.VO.AnexoAfiliadoVO;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.VO.SalidaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.simulacion.webservices.model.UsuarioAfiliadoVO;


/**
 * @version 	1.0
 * @author
 */
public class GetCreditosVigentesAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	private final static String FORWARD = "ingresarParametros";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		HttpSession sesion = request.getSession();
		String rut = request.getParameter("rutConsulta");
		rut=rut.replaceAll("\\.", "");
		
		//Obtener datos de cliente
		request.setAttribute("nombreCliente", "");
		StringBuffer listaRoles= new StringBuffer();
		SalidaAfiliadoVO bp = DataServiceUtil.obtenerInfoAfiliado(rut);
		if(bp.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			request.setAttribute("nombreCliente", bp.getNombreCompleto());
			listaRoles= new StringBuffer();
			if(bp.isTrabajador()){
				listaRoles.append(ConstantesFormalizar.COD_AFILIADO);
			}
			if(bp.isPensionado()){
				listaRoles.append(ConstantesFormalizar.COD_PENSIONADO);
			}
			if(bp.isDeudordirecto()){
				listaRoles.append(ConstantesFormalizar.COD_DEUDOR);
			}
		}
				
		//Obtener estados afiliación
		String filtro=listaRoles.toString();
		Map listaAfiliacion= DataServiceUtil.getTipoAfiliacion(filtro);
		request.setAttribute("afiliadosMap", listaAfiliacion);
		request.setAttribute("numAfiliacion", listaAfiliacion.size());
				
		//Se obtiene lista de Creditos
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		//Se recuperan créditos castigados
		SalidaListaCreditoVigenteVO salidaSAP = ServicioCreditosVigentes.obtenerCreditosVigentesBanking(rut, true);
		String mensajeCampaña="";
				
		// Si falla el servicio SAP
		if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			sesion.removeAttribute("creditosVigentes");
			request.setAttribute("codError", "1");
			request.setAttribute("msg", salidaSAP.getMensaje());
		}else{
			// Si no hubo error al obtener los datos se dejan en request
			if (salidaSAP.getListaCreditos() != null) {
				listaCreditos.addAll(salidaSAP.getListaCreditos());
				Map<String, SalidaCampañaVO> listaCampañas= new HashMap<String, SalidaCampañaVO>();
				SalidaCampañaVO salidaCamp=null;
				for (Iterator iterator = listaCreditos.iterator(); iterator
						.hasNext();) {
					SalidaCreditoVigenteVO salidaCreditoVigenteVO = (SalidaCreditoVigenteVO) iterator
							.next();
					salidaCamp= DataServiceUtil.obtenerCampañasAP(rut, salidaCreditoVigenteVO.getFolio());
					listaCampañas.put(salidaCreditoVigenteVO.getFolio(), salidaCamp);
				}
				sesion.setAttribute("campañas", listaCampañas);
				if(listaCampañas.size()==1){
					mensajeCampaña= DataServiceUtil.obtenerTextoCampañasAP(salidaCamp, "");
				}
			}
			sesion.setAttribute("creditosVigentes", listaCreditos);
			request.setAttribute("mensajeCampanha", mensajeCampaña);
			request.setAttribute("codError", "0");					
		}
		request.setAttribute("numContratos", listaCreditos.size());
		request.setAttribute("listaCreditosVigentes", listaCreditos);

		request.setAttribute("opcion", "0");
		return (mapping.findForward(FORWARD));
	}
}