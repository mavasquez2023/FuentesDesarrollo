package cl.araucana.autoconsulta.ui.actions.liquidacionReembolsos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.ConsultaLiquidacionesVO;
import cl.araucana.autoconsulta.vo.DatosTrabajadoresLiquidacionesVO;
import cl.araucana.autoconsulta.vo.LiquidacionesVO;
import cl.araucana.autoconsulta.vo.MovimientosLiquidacionVO;
import cl.araucana.autoconsulta.vo.PublicidadVO;
import cl.araucana.autoconsulta.vo.TotalesLiquidacionVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author
 */
public class GetLiquidacionReembolsosAction extends Action {
	
	private int totalDocumentos = 0;
	private double totalBase = 0;
	private String textRutAfiliado;
	private double totalBonificado = 0;
	private double totalPendiente = 0;
	private double totalRechazado = 0;

	private static Logger logger = Logger.getLogger(GetLiquidacionReembolsosAction.class);	
	
	//public static final String FORWARD_listaLiquidaciones = "listaLiquidaciones";
	public static final String FORWARD_muestraLiquidacion = "muestraLiquidacion";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			HttpSession session = request.getSession(true);
			DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			String target=null;
			totalDocumentos = 0;
			totalBase = 0;
			totalBonificado = 0;
			totalPendiente = 0;
			totalRechazado = 0;			
			
			
			//String dispositivo = request.getRemoteAddr();
			//logger.debug("IP: "+dispositivo);
			
			Collection listaLiquidaciones =  (Collection)session.getAttribute("lista.liquidaciones");
			
			//UsuarioVO usuario=(UsuarioVO) session.getAttribute("datosUsuario");
			UsuarioVO usuario=null;
			if(request.getSession().getAttribute("adminCOSAL")!=null && request.getSession().getAttribute("adminCOSAL").equals("true")){
				usuario = (UsuarioVO) session.getAttribute("datosPersonaLiqReembCOSAL");
			}else{
				usuario = (UsuarioVO) session.getAttribute("datosUsuario");
			}
			usuario.setIpConexion(request.getRemoteAddr());
			Collection datosTrabajador = delegate.getDatosTrabajadorLiquidaciones(usuario.getRut());
			Iterator itT = datosTrabajador.iterator();
			DatosTrabajadoresLiquidacionesVO datTra = (DatosTrabajadoresLiquidacionesVO)itT.next();
			LiquidacionesVO liq = null;
			
			logger.debug("Son: "+datosTrabajador.size() + " trabajadores");
			logger.debug("El trabajador seleccionado es: "+ datTra.getRut());
			
			String nroLiq = (String)daf.get("nroliq");
			logger.debug("numero Liquidacion: "+nroLiq);			
			Iterator itL = null;
			itL = listaLiquidaciones.iterator();
			if(listaLiquidaciones.size()>1) {
				// El usuario es empresa
				
				while(itL.hasNext()){
					liq = (LiquidacionesVO) itL.next();
					if (liq.getNroLiquidacion() == Integer.parseInt(nroLiq)){
						break;
					}
				}
			}else {
				liq = (LiquidacionesVO) itL.next();
				// El usuario es persona
	
			}
			
			target = FORWARD_muestraLiquidacion;
			logger.debug("Target : " + target);
			ConsultaLiquidacionesVO liquidacionDetallada = new ConsultaLiquidacionesVO();
					
			usuario.setRutAfiliado(usuario.getRut());
			delegate.insertarActividad(usuario, cl.araucana.autoconsulta.vo.ActividadVO.CONSULTA_LIQUIDACION_REEMBOLSO);
			Collection listaMovimientos = delegate.getMovimientosLiquidacion(usuario.getRut(),nroLiq) ;
			
			PublicidadVO publicidad  = null;
			Collection mensaje = delegate.getPublicidad();
			if (!mensaje.isEmpty()){
				Iterator itM = mensaje.iterator();
				while (itM.hasNext()){
					 publicidad = (PublicidadVO) itM.next();
				}
			}
			
			
			liquidacionDetallada.setLiquidacion(liq);
			liquidacionDetallada.setTrabajador(datTra);
			liquidacionDetallada.setMovimientos(listaMovimientos);
			
			calculaTotales(listaMovimientos);
			TotalesLiquidacionVO totales = new TotalesLiquidacionVO();
			totales.setTotalBase(totalBase);
			totales.setTotalBonificado(totalBonificado);
			totales.setTotalPendiente(totalPendiente);
			totales.setTotalRechazado(totalRechazado);
			totales.setTotalDocumentos(totalDocumentos);
			
			session.setAttribute("consulta.detallada",liquidacionDetallada);
			session.setAttribute("totales",totales);
			session.setAttribute("publicidad",publicidad);
			session.setAttribute("fechaHoy",new Date());
			
			logger.debug("**************************Full nombre : " + liquidacionDetallada.getTrabajador().getFullNombre());
			return mapping.findForward(target);
	
		}
		
		private void calculaTotales(Collection mov){
			Iterator itMov = mov.iterator();
			
			while(itMov.hasNext()){
				MovimientosLiquidacionVO reg = (MovimientosLiquidacionVO)itMov.next();
				totalBase = totalBase + reg.getMontoBase();
				totalBonificado = totalBonificado + reg.getMontoBonificado();
				totalPendiente = totalPendiente + reg.getMontoPendiente();
				totalRechazado = totalRechazado + reg.getMontoRechazado();
				totalDocumentos++; 
			}
		}

}