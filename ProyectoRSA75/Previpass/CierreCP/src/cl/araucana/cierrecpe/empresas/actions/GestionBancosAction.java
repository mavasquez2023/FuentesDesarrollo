/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.actions;

import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.Boletas;
import cl.araucana.cierrecpe.empresas.to.BancoTO;
import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GestionBancosAction extends Action {
	private static Logger logger = LogManager.getLogger();
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		Boletas boleta=null;
		try{
			HttpSession sesion= request.getSession();
			logger.finer("Entrando Action GestionBancos");
			String accion= request.getParameter("accion");
			String idBanco= request.getParameter("idbanco");
			System.out.println("Gestion Bancos, Accion=" + accion);
			boleta= new Boletas();
			if(accion.equals("DELETE")){
				boleta.eliminarBanco(Integer.parseInt(idBanco));
				forward = mapping.findForward("OK");
			}else if(accion.equals("NEW")){
				forward = mapping.findForward("NEW");
				request.setAttribute("accion", "SAVE");
			}else if(accion.equals("EDIT")){
				BancoTO bancoTO= (BancoTO)boleta.getBanco(Integer.parseInt(idBanco));
				request.setAttribute("banco", bancoTO);
				request.setAttribute("accion", "UPDATE");
				forward = mapping.findForward("NEW");
			}else if(accion.equals("SAVE") || accion.equals("UPDATE")){
				String idCuenta= request.getParameter("idcuenta");
				String idCuentaOld= request.getParameter("idcuentaold");
				String nombre= request.getParameter("nombre");
				BancoTO bancoTO= new BancoTO();
				bancoTO.setIdBanco(Integer.parseInt(idBanco));
				bancoTO.setIdCuenta(idCuenta);
				bancoTO.setIdCuentaOld(idCuentaOld);
				bancoTO.setNombre(nombre);
				int resultado=0;
				if(accion.equals("SAVE")){
					resultado= boleta.guardarBanco(bancoTO);
					forward = mapping.findForward("OK");
				}else{
					resultado=boleta.updateBanco(bancoTO);
				}
				if(resultado==0){
					request.setAttribute("mensaje", "Código Banco y Cuenta Ya Existe!");
					forward = mapping.findForward("NEW");
					request.setAttribute("banco", bancoTO);
				}else if(resultado==-1){
					request.setAttribute("mensaje", "Error al Grabar!");
					forward = mapping.findForward("NEW");
					request.setAttribute("banco", bancoTO);
				}else{
					forward = mapping.findForward("OK");
				}
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
			if(boleta!=null){
				boleta.close();
			}
		}
		return forward;
	}
}
