
package cl.laaraucana.satelites.certificados.prepago.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;

/**
 * @version 	1.0
 * @author
 */
public class ListadoCreditosAction extends Action

{
	protected Logger logger1 = Logger.getLogger(this.getClass());
	
	private final static String SUCCESS = "success";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionForward forward = new ActionForward(); // return value
	
	HttpSession sesion = request.getSession();
	UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");

	if (usuario == null) {
		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
		request.setAttribute("message", Constants.SESION_EXPIRED);
		return mapping.findForward("customError");
	}
//	String rut = (String) sesion.getAttribute("rut");
//	EntradaCreditoPrepagoVO entradaVO = new EntradaCreditoPrepagoVO();
//	SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
//	List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
//	List<SalidaCreditoPrepagoVO> listaCreditoSocial = new ArrayList<SalidaCreditoPrepagoVO>();
//	List<SalidaCreditoPrepagoVO> listaCreditoCes = new ArrayList<SalidaCreditoPrepagoVO>();
//	List<SalidaCreditoPrepagoVO> listaCreditoEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
//	PrepagoUtil prepagoUtil = new PrepagoUtil();
//	rut="10601236-9";
//	String rut2 = CompletaUtil.llenaConCeros(rut, 11, true);
//	logger1.debug("Rut: "+rut);
//	logger1.debug("Rut arreglado: "+rut2);
//	String fechaHoy = Utils.fechaSAP();
//	
//	SalidaListaAfiliadoVO salidaBPStatus = ServicioQueryBPStatus.obtenerAfiliadoByRutSap("13723448-3"); //ServicioQueryBPStatus.obtenerAfiliado(rut2);
//	
//	if(sesion.getAttribute("creditosVigentes")==null){
//		
//		try {
//			logger1.debug("Ingreso a try getCreditosVigentes AS400");
//
//			//TODO crear metodo para validar RUT AS400
//			//entradaVO.setFlagTipoCredito("vigente");
//			entradaVO.setRut(rut2.trim());
//			
//			ClienteCreditoPrepagoAs400 clientePrepagoWs = new ClienteCreditoPrepagoAs400();
//			
//			salidaVO = (SalidaListaCreditoPrepagoVO) clientePrepagoWs.call(entradaVO);
//			
//			if(Integer.parseInt(salidaVO.getCodigoError())>1 ){//Si la salida no es correcta
//				throw new RuntimeException("Excepcion cod:"+salidaVO.getCodigoError()+", mensaje:"+salidaVO.getMensaje());
//			}
//			
//			if(salidaVO.getListaCreditos()!=null){
//				listaCreditos.addAll(salidaVO.getListaCreditos());
//			}
//			
//		}catch (Exception e) {
//			forward = Utils.returnErrorForward(mapping, e, this.getClass());
//		}
//	
//		
//		
//		
//		try {//Banking
//			logger1.debug("Ingreso a try getCreditosVigentes Banking");
//			rut="13020551-8";
//			SalidaListaCreditoPrepagoVO listaBanking = ServiciosPrepago.obtenerCreditosVigentesBanking(rut, fechaHoy);
//			logger1.debug("Sale del getCreditosVigentes Banking MENSAJE: "+listaBanking.getCodigoError());
//			
//			if(listaBanking.getListaCreditos()!=null){
//				logger1.debug("Pasa lista de creditos a lista general getCreditosVigentes Banking");
//				listaCreditos.addAll(listaBanking.getListaCreditos());
//				logger1.debug("Datos pasados a Lista Final");
//			}
//			
//		}catch (Exception e) {
//			forward = Utils.returnErrorForward(mapping, e, this.getClass());
//		}
//	    
//	}else{
//		request.setAttribute("codError", "0");
//		request.setAttribute("listaCreditosVigentes", sesion.getAttribute("creditosVigentes"));
//	}
//	
////	Faltan datos desde SAP
//	listaCreditoSocial = prepagoUtil.getCreditosPrepagoSocialesList(listaCreditos);
//	if(listaCreditoSocial.isEmpty())
//		listaCreditoSocial = null;
//	
//	listaCreditoCes = prepagoUtil.getCreditosPrepagoCesList(listaCreditos);
//	if(listaCreditoCes.isEmpty())
//		listaCreditoCes = null;
//	
//	listaCreditoEspecial = prepagoUtil.getCreditosPrepagoEspecialList(listaCreditos);
//	if(listaCreditoEspecial.isEmpty())
//		listaCreditoEspecial = null;
//
//	logger1.debug("Ingresa datos al objeto CertificadoPrepagoVO");
//	CertificadoPrepagoVO certificadoPrepago = new CertificadoPrepagoVO();
//	certificadoPrepago.setFolio("001"); 	// Cambiar una vez que se obtenga dato
//	certificadoPrepago.setLogo(CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.logo"));
//	certificadoPrepago.setNombreCompleto(salidaBPStatus.getListaAfiliado().get(0).getNombreCompleto());
//	certificadoPrepago.setRut(rut);
//	certificadoPrepago.setFechaMMYY(PrepagoUtil.getMesYAno());
//	certificadoPrepago.setListaCreditos(listaCreditos);
//	certificadoPrepago.setFechaDDMM(PrepagoUtil.getUltimoDiaYMes());
//	certificadoPrepago.setFechaCompleta(Utils.getFechaCompleta());
//	certificadoPrepago.setFirma(CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.firma"));
//	logger1.debug("Termina de ingresa datos al objeto CertificadoPrepagoVO");
//
//	sesion.setAttribute("certificadoPrepago", certificadoPrepago);
//	
//    request.setAttribute("codError", salidaVO.getCodigoError());
//    
//    request.setAttribute("listaCreditosVigentes", listaCreditos);
//    request.setAttribute("listaCreditoSocial", listaCreditoSocial);
//    request.setAttribute("listaCreditoCes", listaCreditoCes);
//    request.setAttribute("listaCreditoEspecial", listaCreditoEspecial);
//    request.setAttribute("certificadoPrepago", certificadoPrepago);
//	
//	request.setAttribute("opcion", "0");
	forward = mapping.findForward(SUCCESS);
	// Finish with
	return (forward);

    }
}
