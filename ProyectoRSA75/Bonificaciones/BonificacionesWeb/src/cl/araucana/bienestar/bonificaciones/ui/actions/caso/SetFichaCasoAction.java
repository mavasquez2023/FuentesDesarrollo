package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaCasoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {


		/**
		 * INICIO NUEVO
		 */
		request.getSession(false).removeAttribute("caso.botonera.descuento");
		/**
		 * FIN NUEVO
		 */
		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		Logger logger = Logger.getLogger(SetFichaCasoAction.class);
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat formato2 = new SimpleDateFormat ("MM/dd/yyyy", Locale.getDefault());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH,1);
		String fechaHoy = formato2.format(calendar.getTime());
		request.getSession(false).setAttribute("currentDate",fechaHoy);
		

		String codigo=request.getParameter("codigo");
		String source=request.getParameter("source");
		String convenio=request.getParameter("convenio");
		String rutSocio=request.getParameter("socio");
		String rutCarga=request.getParameter("carga");
		String vale=request.getParameter("vale");
		


		String botoneraDescuento=null;
		String botoneraComun=null;
		CasoVO caso = null;
		
		caso=(CasoVO)request.getSession(false).getAttribute("caso");

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();		
		ServicesSociosDelegate delegateSocio=new ServicesSociosDelegate();
		ServicesConveniosDelegate delegateConvenio=new ServicesConveniosDelegate();
		ServicesCasosDelegate delegateCaso=new ServicesCasosDelegate();

		HashMap parametros=new HashMap();
		parametros.put("source","caso");

		if(codigo==null) codigo="";
		if(convenio==null) convenio="";
		if(rutSocio==null) rutSocio="";
		if(source==null) source="";
		if(vale==null) vale="";

		
		/**
		 * INICIO NUEVO
		 */
//		HttpSession sesion = request.getSession();
//		Long grupoUsuario = (Long) sesion.getAttribute("grupoUsuario");
		
		//if(grupoUsuario!=null && grupoUsuario.longValue() == Constants.TIPO_USUARIO_SOCIO){
		//La siguiente linea reemplaza la anterior
		if ( !userinformation.hasAccess("casVerInfoEspecial")) {
			
			if(caso == null){
			
				caso=new CasoVO();
				caso.setFechaIngreso(new Date());
				
				if(userinformation.getRut() != null){
					caso.setRutSocio(userinformation.getRut().substring(0,userinformation.getRut().length()-2));
					caso.setDvRutSocio(userinformation.getRut().substring(userinformation.getRut().length()-1,userinformation.getRut().length()));
					caso.setNombreSocio(userinformation.getNombreCompleto());	
				}	
			}
		}		
		/**
		 * FIN NUEVO
		 */

		if(!convenio.equals("") && caso!=null){
			Convenio conv=delegateConvenio.getConvenio((long)Long.parseLong(convenio));
			caso.setNombreConvenio(conv.getNombre());
			caso.setCodigoConvenio(conv.getCodigo());	
			caso.setNumeroMaximoCuotas(conv.getNumeroMaximoCuotas());
			caso.setVale(null);			
		}
		else if(rutCarga!=null && caso!=null){
			if(rutCarga.equals("")){
				caso.setRutCarga("");
				caso.setNombreCarga("");
			}
			else{
				Carga carga=delegateSocio.getCarga(rutCarga,rutSocio);
				caso.setNombreCarga(carga.getNombreCarga()+" "+carga.getApePatCarga()+" "+carga.getApeMatCarga());
				caso.setRutCarga(carga.getRutCarga());
				caso.setDvRutCarga(String.valueOf(carga.getDvCarga()));				
				caso.setNombreSocio(carga.getNombreSocio());
				caso.setRutSocio(carga.getRutSocio());
				caso.setDvRutSocio(String.valueOf(carga.getDvSocio()));				
				Socio socio=delegateSocio.getSocio(carga.getRutSocio());
				request.getSession(false).setAttribute("socio",socio);
			}
		} else if(!vale.equals("") && caso!=null){
			Vale val=new Vale();
			val.setCodigoVale((long)Long.parseLong(vale));
			caso.setVale(val);				
		} else if(!rutSocio.equals("") && caso!=null){
			if(!rutSocio.equals(caso.getRutSocio())){
				Socio socio=delegateSocio.getSocio(rutSocio);
				caso.setNombreSocio(socio.getNombre()+" "+socio.getApePat()+" "+socio.getApeMat());
				caso.setRutSocio(socio.getRut());
				caso.setDvRutSocio(String.valueOf(socio.getDigito()));				
				caso.setNombreCarga(null);
				caso.setRutCarga(null);
				caso.setVale(null);
				request.getSession(false).setAttribute("socio",socio);
			}
		}
		else if(codigo.equals("")){
			//INICIO NUEVO
			//if(grupoUsuario == null  || (grupoUsuario!=null && grupoUsuario.longValue() != Constants.TIPO_USUARIO_SOCIO)){
			//La siguiente linea reemplaza la anterior
			if (userinformation.hasAccess("casVerInfoEspecial")) {
			//FIN NUEVO
				caso=new CasoVO();
				caso.setFechaIngreso(new Date());
				if(source.equals("preCaso")){
					caso.setTipoBono(Caso.TIPOBONO_SOCIO);
					caso.setEstado(Caso.STD_PRECASO);
					caso.setIndicadorPreCaso(Caso.ESTADOINDICADOR_SI);	
				}			
				if(source.equals("socio")){
					Socio socio=(Socio)request.getSession(false).getAttribute("socio");				
					caso.setRutSocio(socio.getRut());
					caso.setNombreSocio(socio.getNombre()+" "+socio.getApePat()+" "+socio.getApeMat());
					caso.setDvRutSocio(String.valueOf(socio.getDigito()));
				}
			}//nuevo	
		}
		else{
			caso=delegateCaso.getCasoVO((long)Long.parseLong(codigo));
			Socio socio=delegateSocio.getSocio(caso.getRutSocio());
			request.getSession(false).setAttribute("socio",socio);
			Convenio conv=delegateConvenio.getConvenio(caso.getCodigoConvenio());
			request.getSession(false).setAttribute("convenio",conv);
		} 
		logger.debug("caso:"+caso);
		logger.debug("caso:"+caso.getFechaDeOcurrencia());
		logger.debug("caso:"+caso.getTipoCaso());
		logger.debug("caso:"+caso.getCuotasBienestar());
		if(caso.getFechaDeOcurrencia()!=null){
			dynaValidatorActionForm.set("fechaDeOcurrencia",formato.format(caso.getFechaDeOcurrencia()));
		}
		else{
			dynaValidatorActionForm.set("fechaDeOcurrencia","");
		}
		
		//opciones
		if(caso.getCasoID()>0){
			if(caso.getEstado().equals(Caso.STD_BORRADOR)){
				if (userinformation.hasAccess("casActiva")) {
					opciones.add("Activar Caso");
					opcionesValores.add("2");	
				}
			}
			
			if((caso.getEstado().equals(Caso.STD_ACTIVO)
				|| caso.getEstado().equals(Caso.STD_BORRADOR)
				|| caso.getEstado().equals(Caso.STD_PRECASO))
				&& caso.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO) 
				&& caso.getIndicadorPago().equals(Caso.ESTADOINDICADOR_NO)
				&& caso.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO)){
//					if (userinformation.hasAccess("casActualiza") 
//					|| (grupoUsuario!=null && grupoUsuario.longValue() == Constants.TIPO_USUARIO_SOCIO) /*NUEVO*/) {
				    //El siguiente if reemplaza el anterior
					if (userinformation.hasAccess("casActualiza")) {						
						opciones.add("Actualizar Caso");
						opcionesValores.add("3");	
					}
					
					if(caso.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_NO)){
						if (userinformation.hasAccess("casElimina")) {
							opciones.add("Eliminar Caso");
							opcionesValores.add("7");	
						}
					}
					if(caso.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_NO)){
						if (userinformation.hasAccess("casSimulaBonificacion")) {
							opciones.add("Simular Bonificación");
							opcionesValores.add("4");
						}
						if(caso.getEstado().equals(Caso.STD_ACTIVO)){
							if (userinformation.hasAccess("casBonificar")) {
								opciones.add("Bonificar Caso");
								opcionesValores.add("5");
							}
						}
					}
			}
			if (caso.getTipoCaso().equals(Caso.TIPO_DESCUENTO) &&
				caso.getEstado().equals(Caso.STD_ACTIVO) &&
				caso.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_SI) &&
				caso.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO)){
					if (userinformation.hasAccess("casCierra")) {
						opciones.add("Cerrar Caso");
						opcionesValores.add("8");	
					}					
			}
			
			if (caso.getEstado().equals(Caso.STD_ACTIVO)
				&& caso.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO)
				&& caso.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO)
				&& caso.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_NO)	
			) {
				if (userinformation.hasAccess("casAporteEspecial")) {
					opciones.add("Bonificación Especial FEC");
					opcionesValores.add("9");
					
					opciones.add("Bonificación Especial");
					opcionesValores.add("12");
				}
			}
					
			if (userinformation.hasAccess("casCrea")) {
				opciones.add("Crear Nuevo Caso");
				opcionesValores.add("6");	
			}
			
			if(caso.getTipoCaso().equals(Caso.TIPO_DESCUENTO) && caso.getEstado().equals(Caso.STD_ACTIVO) && (caso.getCuotasConvenio() > 1 || caso.getCuotasBienestar() > 1)){
				if (userinformation.hasAccess("casAporteEspecial")) {
					opciones.add("Saldar deuda total del caso");
					opcionesValores.add("10");
				}
			}
			
			List casoLiberadoPreviamente = delegateCaso.getBitacoraElimCobertura(caso.getCasoID());
			
			if(caso.getTipoCaso().equals(Caso.TIPO_REEMBOLSO) && 
				(caso.getEstado().equals(Caso.STD_ACTIVO) || caso.getEstado().equals(Caso.STD_CERRADO)) &&
				caso.getIndicadorBonificacion().equals("SI") && casoLiberadoPreviamente.size() == 0
			){
				if (userinformation.hasAccess("casAporteEspecial")) {
					opciones.add("Liberar tope cobertura");
					opcionesValores.add("11");
				}
			}
		}
		else{
			if (userinformation.hasAccess("casCrea")) {
				
				if(source.equals("preCaso") || caso.getEstado().equals(Caso.STD_PRECASO))
					opciones.add("Guardar como PreCaso");	
				else
					opciones.add("Guardar como Borrador");
				opcionesValores.add("1");	
			}
		}
		dynaValidatorActionForm.set("tipoCaso",caso.getTipoCaso());
		dynaValidatorActionForm.set("cuotasBienestar",String.valueOf(caso.getCuotasBienestar()));
		dynaValidatorActionForm.set("cuotasConvenio",String.valueOf(caso.getCuotasConvenio()));
		dynaValidatorActionForm.set("numeroDocumento",caso.getNumeroDocumento());
		dynaValidatorActionForm.set("tipoDocumento",caso.getTipoDocumento());
		dynaValidatorActionForm.set("compraBono",caso.getTipoBono());

		if(caso.getTipoCaso()!=null){
			if(caso.getTipoCaso().equals(Caso.TIPO_REEMBOLSO)) botoneraComun="si";
			else if(caso.getTipoCaso().equals(Caso.TIPO_DESCUENTO)) {
				botoneraDescuento="si";
				botoneraComun="si";
			} 
		}

		logger.debug("antes del if convenio="+caso.getCodigoConvenio()+", rutSocio="+caso.getRutSocio());
		String muestraVale=null;
		if(caso.getRutSocio()!=null && caso.getCodigoConvenio()>0){
			parametros.put("convenio",String.valueOf(caso.getCodigoConvenio()));
			parametros.put("socio",caso.getRutSocio());
			logger.debug("entre al if convenio="+caso.getCodigoConvenio()+", rutSocio="+caso.getRutSocio());
			muestraVale="si";
		}
		// pongo el objeto Concepto y Opciones en memoria
		request.getSession(false).setAttribute("caso",caso);
		request.getSession(false).setAttribute("caso.opciones",opciones);
		request.getSession(false).setAttribute("caso.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("caso.botonera.comun",botoneraComun);
		request.getSession(false).setAttribute("caso.botonera.descuento",botoneraDescuento);
		request.getSession(false).setAttribute("caso.muestraVale",muestraVale);
		request.getSession(false).setAttribute("caso.parametros",parametros);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("caso");
		this.saveToken(request);
		return (forward);
	}
	
	
}
