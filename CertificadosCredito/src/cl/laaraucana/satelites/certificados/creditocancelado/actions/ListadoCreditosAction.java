package cl.laaraucana.satelites.certificados.creditocancelado.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditocancelado.ServicioCreditosCancelados;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaListaCreditosCanceladosVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;

/**
 * @version 	1.0
 * @author
 */
public class ListadoCreditosAction extends DispatchAction

{
	protected Logger logger = Logger.getLogger(this.getClass());	
	private final static String FORWARD = "cargaListaCredito";
	private final static String SERVLET = "ExportPDF";

	public ActionForward cargarCreditos(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		logger.debug("<< Ingreso a Action ListadoCreditosCancelados");
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");

		if (usuario == null) {
			request.setAttribute("title", "Error: ");
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
		
		String rutAConsultar = usuario.getRut()+"-"+usuario.getDv();
		sesion.setAttribute("rut", rutAConsultar);
		sesion.setAttribute("nombre", usuario.getNombre());
		
		String uc = request.getParameter("uc");
		if(uc!=null){
			sesion.setAttribute("uc", uc);
		}
		
		//query bp status y datos afiliados as400
		//usar rut 13723448-3 para que funcione
		/*UsuarioAfiliadoVO user = UsuarioServiceUtil.obtenerAfiliado(usuario.getRut()+"-"+usuario.getDv());
		System.out.println("=================================================");
		System.out.println("se ingreso el rut "+usuario.getRut()+"-"+usuario.getDv()+" y el nombre es "+user.getNombreAfiliado());
		if(user == null || !user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			String mensaje = user.getMensaje();
			return new ActionForward(mapping.findForward("error").getPath()+"?errorMsg="+mensaje, false);
		}
		
		sesion.setAttribute("rut",usuario.getRut()+"-"+usuario.getDv());
		sesion.setAttribute("nombre", (user.getNombreAfiliado()).toUpperCase());*/
		forward = mapping.findForward(FORWARD);

		logger.debug(">> Salida ListadoCreditosCancelados");
		return (forward);

	}
		    
	 @SuppressWarnings("unchecked")
	 public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) 
					 throws Exception {
		    	
		HttpSession sesion = request.getSession();    	
    	String rut = (String) sesion.getAttribute("rut");
    	   	
        List <SalidaCreditoCanceladoVO> creditosCancelados = (List<SalidaCreditoCanceladoVO>) sesion.getAttribute("creditosCancelados");
        if(creditosCancelados==null){
        	creditosCancelados = new ArrayList<SalidaCreditoCanceladoVO>();
        	List<SalidaCreditoCanceladoVO> salidaSAP = ServicioCreditosCancelados.obtenerCreditosCanceladosSAP(rut, ServiciosConst.CREDITOS_DISUELTOS).getListaCreditos();
        	creditosCancelados.addAll(salidaSAP);
        }
 
        UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		if (usuario == null) {
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
        String nombreCompleto = usuario.getNombre(); 
        
    	if(rut.length() == 0 || nombreCompleto.length() == 0){
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
    	}
    	
    	try {
        //Setear valores para almacenar en archivo at02f2
        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
        
        ValorValidableVO valor = new ValorValidableVO();
        valor.setVariable("Cantidad de creditos cancelados");
        valor.setValor(String.valueOf(creditosCancelados.size()));
        listaValores.add(valor);
       
        for (int i = 0; i < creditosCancelados.size(); i++) {
        	valor = new ValorValidableVO();
			valor.setVariable("Folio credito "+(i+1));
			valor.setValor(creditosCancelados.get(i).getFolio());
			listaValores.add(valor);
		}
        
      //Se setea datos ejecutivo gener� certificado
        User userInfo = (User) sesion.getAttribute("userInfo");
        if(userInfo!=null){
        	valor = new ValorValidableVO();
        	valor.setVariable("Ejecutivo");
        	valor.setValor(userInfo.getFirstName() + " " + userInfo.getLastName() + ", RUT: " + userInfo.getID());
        	listaValores.add(valor);
        }
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_CANCELADO);

    	logger.debug("<< Entro a imprimirReporte.");
		List<SalidaCreditoCanceladoVO> datos = new ArrayList<SalidaCreditoCanceladoVO>();
		datos.add(new SalidaCreditoCanceladoVO(null, 0, null, null, 0, null));
		
		//Si no existen creditos vigentes se agrega una columna en blanco.
    	if(creditosCancelados!=null && creditosCancelados.size()!=0){
    		datos.addAll(creditosCancelados);
    	}else{
        	datos.add(null);
    	}
    	
    	String folio="";
    	if (creditosCancelados.size()==1){
    		folio= creditosCancelados.get(0).getFolio();
    	}
    	//Guardar certificado en SQLServer
    	for (int i = 0; i < creditosCancelados.size(); i++) {
    		BitacoraVO bitacora= new BitacoraVO();
    		bitacora.setIdCertificado(codValidacion);
    		bitacora.setFolio(creditosCancelados.get(i).getFolio());
    		bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_CRED_CANCELADO));
    		bitacora.setRutDeudor(rut);
    		bitacora.setNombreDeudor(nombreCompleto);
    		bitacora.setTotalPagar(0);
    		bitacora.setRutUsuario("");
    		bitacora.setNombreUsuario("");
    		if(userInfo!=null){
    			bitacora.setRutUsuario(userInfo.getID());
    			bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
    		}
    		BitacoraDAO.insertaBitacora(bitacora);
    	}
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("rut", rut);
		hash.put("nombreCompleto", nombreCompleto);

		hash.put("fechaCreacion", Utils.getFechaCompleta());
		hash.put("codValidacion", codValidacion);
		hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
		hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.firma"));

		String ruta = CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.jasper");
		ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
		logger.debug("Set correcto datos reporte.");

		byte[] bites = ru.exportCompilePdf();
		logger.debug("Reporte Creado Exitosamente.");

		request.setAttribute("bites", bites);
		request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.nombre"));

    	} catch (Exception e) {
    		logger.debug(">> Error al generar el reporte Reporte.");
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR_TITLE);
			return mapping.findForward("customError");
		}
		
		return mapping.findForward(SERVLET);
	 }
}
