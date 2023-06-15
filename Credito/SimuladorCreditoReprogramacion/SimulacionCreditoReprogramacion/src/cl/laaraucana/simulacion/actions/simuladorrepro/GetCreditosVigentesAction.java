package cl.laaraucana.simulacion.actions.simuladorrepro;

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

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
//import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;

import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;

import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;
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
		request.setAttribute("rutAfiliado", rut);
		
		//Obtener datos de cliente
		request.setAttribute("nombreCliente", "");
		StringBuffer listaRoles= new StringBuffer();
		SalidaAfiliadoVO bp = DataServiceUtil.obtenerInfoAfiliado(rut);
		
		//Map<String, String> anexos= new HashMap<String, String>();
		String anexos="";
		if(bp.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			request.setAttribute("nombreCliente", bp.getNombreCompleto());
			listaRoles= new StringBuffer();
			if(bp.isTrabajador()){
				listaRoles.append(ConstantesFormalizar.COD_AFILIADO);
				//anexos.put("", "Seleccione");
			}
			if(bp.isPensionado()){
				listaRoles.append(ConstantesFormalizar.COD_PENSIONADO);
				//anexos.put("", "Seleccione");
			}
			if(bp.isDeudordirecto()){
				listaRoles.append(ConstantesFormalizar.COD_DEUDOR);
				//anexos.put("", "No Aplica");
			}
			//Setear Anexos
			for (Iterator iterator = bp.getAnexos().iterator(); iterator.hasNext();) {
				AnexoAfiliadoVO anexo = (AnexoAfiliadoVO) iterator.next();
				anexos= anexo.getCodigoAnexo();
				break;
				//anexos.put(anexo.getCodigoAnexo(), anexo.getNombreAnexo());
			}
		}
		//request.setAttribute("listaAnexos", anexos);
		request.setAttribute("anexos", anexos);
		//request.setAttribute("numanexos", anexos.size()-1);
		
		//Calcular Edad Cliente y Setear mensajes
		String fechaNacimiento= null;
		if(bp.getFechaNacimiento()!=null){
			fechaNacimiento=Utils.dateToStringSAP(bp.getFechaNacimiento());
			int edad= Utils.getEdad(fechaNacimiento);
			String edadminima= ConstantesFormalizar.EDAD_MINIMA_REPRO;
			String edadmaxima= ConstantesFormalizar.EDAD_MAXIMA_REPRO;
			if(edad<Integer.parseInt(edadminima)){
				request.setAttribute("mensaje_edad", ConstantesFormalizar.MENSAJE_EDAD_MINIMA_REPRO);
			}else if(edad>Integer.parseInt(edadmaxima)){
				request.setAttribute("mensaje_edad", ConstantesFormalizar.MENSAJE_EDAD_MAXIMA_REPRO);
			}
			request.setAttribute("edad", edad);
		}else{
			request.setAttribute("mensaje_edad", ConstantesFormalizar.MENSAJE_SIN_FECHA_NAC_REPRO);
			request.setAttribute("edad", -1);
		}
		
		//Obtener estados afiliación
		String filtro=listaRoles.toString();
		Map listaAfiliacion= DataServiceUtil.getTipoAfiliacion(filtro);
		request.setAttribute("afiliadosMap", listaAfiliacion);
		request.setAttribute("numAfiliacion", listaAfiliacion.size());
		
		//Se obtiene lista de Creditos
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		//Se recuperan créditos no castigados
		SalidaListaCreditoVigenteVO salidaSAP = ServicioCreditosVigentes.obtenerCreditosVigentesBanking(rut, false);
		
		//Se obtiene Insolvencia, Cesantia y Desgravamen
		Map<String, SalidaCreditoVigenteVO> cabeceras = new HashMap<String, SalidaCreditoVigenteVO>();
		for (Iterator iterator = salidaSAP.getListaCreditos().iterator(); iterator.hasNext();) {
			SalidaCreditoVigenteVO salidaCreditoVigenteVO = (SalidaCreditoVigenteVO) iterator.next();
			cabeceras.put(salidaCreditoVigenteVO.getFolio(), salidaCreditoVigenteVO);
		}
		sesion.setAttribute("cabeceras", cabeceras);
		boolean tieneInsolvencia= false;
		if(cabeceras.size()==1){
			SalidaCreditoVigenteVO salida= (SalidaCreditoVigenteVO)cabeceras.get(salidaSAP.getListaCreditos().get(0).getFolio());
			if(salida.getInsolvencia()!= null && salida.getInsolvencia().equals("X")){
				tieneInsolvencia=true;
			}
		}
		
		//Obtener Productos
		List<String> productos=null;
		if(listaAfiliacion.size()==1){
			String tipoAfiliado= (String)listaAfiliacion.keySet().iterator().next();
			productos=DataServiceUtil.obtenerListaProductosSap(tipoAfiliado, tieneInsolvencia);
			request.setAttribute("listaProductos", productos);
		}
				
				
		// Si falla el servicio SAP
		if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			sesion.removeAttribute("creditosVigentes");
			request.setAttribute("codError", "1");
			request.setAttribute("msg", salidaSAP.getMensaje());
		}else{
			// Si no hubo error al obtener los datos se dejan en request
			if (salidaSAP.getListaCreditos() != null) {
				listaCreditos.addAll(salidaSAP.getListaCreditos());
			}
			sesion.setAttribute("creditosVigentes", listaCreditos);
			
			request.setAttribute("codError", "0");					
		}
		request.setAttribute("numContratos", listaCreditos.size());
		request.setAttribute("listaCreditosVigentes", listaCreditos);

		request.setAttribute("opcion", "0");
		return (mapping.findForward(FORWARD));
	}
}