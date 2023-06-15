package cl.laaraucana.satelites.certificados.creditovigente.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.finiquito.utils.FiniquitoLocalUtil;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
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
    	
	    logger.debug("<< Ingreso a Action ListadoCreditosVigentes");
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		if (usuario == null) {
			request.setAttribute("title", "Error: ");
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
		
		String rutAConsultar = usuario.getRut()+"-"+usuario.getDv();
		UsuarioAfiliadoVO user = null;
		//matar este if para certificados CRM
		//Si es empresa, solicita ingresar rut empleado y valida si pertenece a empresa
//		if(usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()){
		if(false){
			//limpiar creditos almacenados en sesion
			
			if(request.getParameter("rutEmpleado") == null){
				//Redireccionar a pagina de seleccion empleado nuevamente
				return mapping.findForward("seleccionEmpresa");
			}else{
				rutAConsultar = request.getParameter("rutEmpleado").replace(".", "");
				//Consultar si el rut ingresado corresponde a empresa
				FiniquitoLocalUtil finiquitoUtil = new FiniquitoLocalUtil();
				user = UsuarioServiceUtil.obtenerAfiliado(rutAConsultar);
				DetalleEmpresaAfiliado detalleEmpresa = finiquitoUtil.obtenerDetalleEmpresa(usuario.getRut()+"-"+usuario.getDv(), user);
				if(detalleEmpresa == null){
					request.setAttribute("error","El rut ingresado no pertenece a la empresa");
					return mapping.findForward("seleccionEmpresa");
				}				
			}
		}else{
			//Si es persona, consulta por el rut de la misma
			user = UsuarioServiceUtil.obtenerAfiliado(rutAConsultar);
			System.out.println("=================================================");
			System.out.println("se ingreso el rut " + rutAConsultar + " y el nombre es "+ user.getNombreAfiliado());
			if (user == null || !user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				String mensaje = user.getMensaje();
				return new ActionForward(mapping.findForward("error").getPath()+ "?errorMsg=" + mensaje, false);
			}
		}

		sesion.setAttribute("rut",rutAConsultar);
		sesion.setAttribute("nombre", user.getNombreAfiliado().toUpperCase());
		forward = mapping.findForward(FORWARD);
		logger.debug(">> Salida ListadoCreditosVigentes");
		return (forward);

    }
    
    @SuppressWarnings("unchecked")
	public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession sesion = request.getSession();
    try {
			
        String rut = (String) sesion.getAttribute("rut");
        String nombreCompleto = (String) sesion.getAttribute("nombre");
        
        if (sesion.getAttribute("datosUsuario") == null) {
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
        
        if(rut.length() == 0 || nombreCompleto.length() == 0){
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
    	}
        
       
        List <SalidaCreditoVigenteVO> creditosVigentes = (List<SalidaCreditoVigenteVO>) sesion.getAttribute("creditosVigentes");
  
        //Setear valores para almacenar en archivo at02f2
        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
        
        ValorValidableVO valor = new ValorValidableVO();
        valor.setVariable("Cantidad de creditos vigentes");
        valor.setValor(String.valueOf(creditosVigentes.size()));
         listaValores.add(valor);
       
        for (int i = 0; i < creditosVigentes.size(); i++) {
        	valor = new ValorValidableVO();
			valor.setVariable("Folio credito "+(i+1));
			valor.setValor(creditosVigentes.get(i).getFolio());
			listaValores.add(valor);
		}     
        
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_VIGENTE);
    	
    	logger.debug("<< Entro a imprimirReporte.");
    	List<SalidaCreditoVigenteVO> datos = new ArrayList<SalidaCreditoVigenteVO>();
    	datos.add(new SalidaCreditoVigenteVO(null, 0, null, 0, null));
		
    	//Si no existen creditos vigentes se agrega una columna en blanco.
    	if(creditosVigentes!=null && creditosVigentes.size()!=0){
    		datos.addAll(creditosVigentes);
    	}else{
        	datos.add(null);
    	}
    	
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("rut", rut);
    	hash.put("nombreCompleto", nombreCompleto);
    	
    	hash.put("fechaCreacion", Utils.getFechaCompleta());
    	hash.put("codValidacion", codValidacion);
    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.firma"));
    	
		/**
		 * REQ-7962 Integración de servicios Banking con kiosco.
		 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
		 */
		if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
			//Eliminar item 0
			datos.remove(0);
			request.setAttribute("datos", datos);
			request.setAttribute("hash", hash);
			RequestDispatcher rd = request.getRequestDispatcher("imprimirCertCredito.jsp");
			rd.forward(request, response);
			return null;
		}else{
	    	String ruta=CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigente.jasper");
	    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
	    	logger.debug("Set correcto datos reporte.");
	    	
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.debug("Reporte Creado Exitosamente.");
	    	request.setAttribute("bites", bites);
	    	request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.nombre"));
		}
		// Fin modificación
    	
    	
		logger.debug(">> Salida a servlet Reporte.");
		
    } catch (Exception e) {
        logger.debug("Error al generar el reporte: "+ e.getMessage());
    	request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    	request.setAttribute("message", Constants.REPORT_DATA_ERROR);
    	return mapping.findForward("customError");
	}
		
        return mapping.findForward(SERVLET);
    }
}
