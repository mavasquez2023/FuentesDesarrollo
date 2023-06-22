package cl.araucana.cotcarserv.main.actions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.servlets.EmpresasLDAP;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.servicios.extincionSIAGF.CredentialWSTGR;
import cl.laaraucana.servicios.extincionSIAGF.DataResponseWS;
import cl.laaraucana.servicios.extincionSIAGF.DataWS;
import cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFImplProxy;
import cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFPortBindingStub;
import cl.laaraucana.servicios.extincionSIAGF.ResponseWS;

import cl.recursos.EnviarMail;


/**
 * @version 1.0
 * @author
 */
public class CesacionTrabajadorAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward cesarTrabajador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();
		String usuario= (String)sesion.getAttribute("usuario");
		String cesacion = request.getParameter("desvinculacion");
		
		CotizacionesVO trabajadorVO= (CotizacionesVO)sesion.getAttribute("trabajador");
		if(trabajadorVO==null){
			forward = mapping.findForward("init");
			return forward;
		}
		trabajadorVO.setFechaDesvinculacion(cesacion);
		
		logger.info("Cesando trabajador " + trabajadorVO.getRutTrabajador());
		try {
		
			int rutEmpresa= trabajadorVO.getRutEmpresa();
			int rutTrabajador= trabajadorVO.getRutTrabajador();
			Map param= new HashMap();
			param.put("rutEmpresa",rutEmpresa);
			param.put("rutTrabajador", rutTrabajador);
			param.put("fechaDesvinculacion", Utils.pasaFechaWEBaSAP(trabajadorVO.getFechaDesvinculacion()));
	
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			logger.info("Update Estado Trabaajador");
			int resultado= consultaDAO.updateTrabajador(param);
			
			if(resultado >0){
				logger.info("Update Estado Cargas");
				param.put("fechaDesvinculacion", Utils.stringToDate(trabajadorVO.getFechaDesvinculacion()));
				int resultado_cargas= consultaDAO.updateCargas(param);
				
				request.setAttribute("error", "0");
				//Insert Bitácora
				insertBitacora(usuario, trabajadorVO);
				
				//if(resultado_cargas>0){
				//Cesar en SIAGF
				cesarTrabajadorWS(trabajadorVO);
				//}
				
				List<CotizacionesVO> lista= new ArrayList<CotizacionesVO>();
				lista.add(new CotizacionesVO());
				lista.add(trabajadorVO);
				sesion.setAttribute("certificado_PDF", lista);
				
			}else{
				request.setAttribute("error", "-1");
				request.setAttribute("mensaje", rutTrabajador);
				forward = mapping.findForward("success");
			}			


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en update Rut: " + trabajadorVO.getRutTrabajador());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		request.setAttribute("menu", "cesacion");
		sesion.removeAttribute("repetido");
		forward = mapping.findForward("success");
		return (forward);

	}
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;		
		HttpSession sesion = request.getSession();
		
		request.setAttribute("menu", "cesacion");
		if(sesion.getAttribute("empresas")==null){
			Principal principal = request.getUserPrincipal();
			String username= principal.getName();
			listamap= EmpresasLDAP.getEmpresasLDAP(username);
			sesion.setAttribute("empresas", listamap);
			sesion.setAttribute("usuario", username);
			forward = mapping.findForward("seleccion");
			return forward;
		}
		String desvinculacion = request.getParameter("desvinculacion");
		String accion = request.getParameter("accion");
		String rutEmpresa = request.getParameter("rutEmpresa");
		String rutTrabajador = request.getParameter("rutTrabajador");
		
		
		if(accion!= null && accion.equals("cesarTrabajador")){
			return cesarTrabajador(mapping, form, request, response);
		}
		
		if(accion!= null && accion.equals("menu")){
			forward = mapping.findForward("seleccion");
			return forward;
		}
		
		if(rutTrabajador!=null && !rutTrabajador.equals("")){
			rutEmpresa= (String)sesion.getAttribute("rutEmpresa");
		}
		
		if(rutEmpresa==null || rutEmpresa.equals("")){
			forward = mapping.findForward("seleccion");
			return forward;
		}
		Map<String, String> indiceEmpresas= (TreeMap<String, String>)sesion.getAttribute("empresas");
		String razonSocial= indiceEmpresas.get(rutEmpresa);
		sesion.setAttribute("rutEmpresa", rutEmpresa);
		sesion.setAttribute("razonSocial", razonSocial);
		
		if(accion!= null && accion.equals("volver")){
			rutTrabajador=null;
		}
		
		if(rutTrabajador==null){
			sesion.setAttribute("trabajador", new CotizacionesVO());
			forward = mapping.findForward("success");
			return forward;
		}
		rutTrabajador= rutTrabajador.replaceAll("\\.", "");
		RutUtil rutentrada= new RutUtil();
		if(!rutentrada.IsRutValido(rutTrabajador)){
			request.setAttribute("error", "2");
			forward = mapping.findForward("success");
			return (forward);
		}
		
		String rutEmpresa_aux= rutEmpresa.substring(0, rutEmpresa.length()-2);
		String rutTrabajador_aux= rutTrabajador.substring(0, rutTrabajador.length()-2);
		
		logger.info("Ingreso a consulta info trabajador " + rutTrabajador);

		try {
		
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			logger.info("Consultando Cargas Trabaajador");
			ParamVO param= new ParamVO();
			param.setRutEmpresa(Integer.parseInt(rutEmpresa_aux));
			param.setRutTrabajador(Integer.parseInt(rutTrabajador_aux));
			
			CotizacionesVO dataTra= consultaDAO.consultaTrabajador(param);
			dataTra.setFechaAfiliacion(Utils.pasaFechaSAPaWEB(dataTra.getFechaAfiliacion()));

			if(dataTra !=null){
				dataTra.setRutEmpresa(Integer.parseInt(rutEmpresa.split("-")[0]));
				dataTra.setDvEmpresa(rutEmpresa.split("-")[1]);
				dataTra.setRazonSocial(razonSocial);
				sesion.setAttribute("trabajador", dataTra);
			}else{
				request.setAttribute("error", "-1");
				request.setAttribute("mensaje", rutTrabajador);
				//sesion.setAttribute("trabajador", "");
				forward = mapping.findForward("success");
				
			}			

			//Insert Bitácora
			//insertBitacora(idCertificado, rutEmpresa);

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Rut: " + rutTrabajador);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	 public void insertBitacora(String usuario, CotizacionesVO traVO){
	    	try {
				ConsultaServicesDAO dao= new ConsultaServicesDAO();
				Map param= new HashMap();
				param.put("usuario", usuario);
				param.put("accion", "CESACION");
				param.put("rutTrabajador", traVO.getRutTrabajador());
				param.put("dvTrabajador", traVO.getDvTrabajador());
				param.put("rutEmpresa", traVO.getRutEmpresa());
				param.put("dvEmpresa", traVO.getDvEmpresa());
				//param.put("fechaAfiliacion", Utils.stringToDate(Utils.pasaFechaWEBaSAP(traVO.getFechaAfiliacion())));
				param.put("fechaAfiliacion", Utils.stringToDate(traVO.getFechaAfiliacion()));
				//param.put("fechaDesvinculacion", Utils.stringToDate(Utils.pasaFechaWEBaSAP(traVO.getFechaDesvinculacion())));
				param.put("fechaDesvinculacion", Utils.stringToDate(traVO.getFechaDesvinculacion()));
				param.put("origen", "E");
				
				int ok_bita= dao.insertBitacora(param);
				logger.info("Registrado en bitácora: " + ok_bita);
			} catch (Exception e) {
				logger.warn(">>Error al registrar en bitácora consulta informe");
			}
	    }
	    
	    public void cesarTrabajadorWS(CotizacionesVO trabajadorVO){
	    	final int RESPUESTA_OK= 1;
	    	
	    	
	    	try {
	    		String ep=CertificadoConst.RES_CERTIFICADOS.getString("ep.extincion.tupla");
	    		ExtincionSIAGFPortBindingStub stub= new ExtincionSIAGFPortBindingStub();
		    	stub._setProperty(ExtincionSIAGFPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		    	
		    	CredentialWSTGR credential= new CredentialWSTGR();
		    	credential.setUser(CertificadoConst.RES_CERTIFICADOS.getString("user.ws.ext.siagf"));
		    	credential.setPassword(CertificadoConst.RES_CERTIFICADOS.getString("password.ws.ext.siagf"));
		    	
		    	String token=stub.autenticacionWS(credential);
				if(!token.equals("")){
					DataWS[] data= new DataWS[1];
					data[0]= new DataWS();
					data[0].setFECHA_EMISION(Utils.fechaSAP());
					data[0].setFECHA_EXTINCION(Utils.pasaFechaWEBaSAP(trabajadorVO.getFechaDesvinculacion()));
					data[0].setRUT_EMPRESA(trabajadorVO.getRutEmpresa()+ "-" + trabajadorVO.getDvEmpresa());
					data[0].setRUT_TRABAJADOR(trabajadorVO.getRutTrabajador() + "-" + trabajadorVO.getDvTrabajador());
					logger.info("Extinguiendo causantes trabajador:" + trabajadorVO.getRutTrabajador());

					ResponseWS responseWS= stub.extingueCausantes(token, data);
					logger.info(responseWS.getCODIGO());
					logger.info(responseWS.getDESCRIPCION());
					if(responseWS.getCODIGO()== RESPUESTA_OK){
						ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
						for (int i = 0; i < responseWS.getRESPUESTA().length; i++) {
							Map param= new HashMap();
							DataResponseWS dataResponse= responseWS.getRESPUESTA(i);
							String rutempaux= dataResponse.getRUT_EMPRESA().split("-")[0];
							String ruttraaux= dataResponse.getRUT_TRABAJADOR().split("-")[0];
	    					param.put("rutEmpresa", rutempaux);
	    					param.put("rutTrabajador", ruttraaux);
	    					param.put("estado", dataResponse.getESTADO());
							consultaDAO.updateEstadoTrabajadorSIAGF(param);
						}
					}
					
				}
				System.out.println("Token=" + token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
}
