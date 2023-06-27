package cl.araucana.bienestar.bonificaciones.ui.actions.cobertura;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;


/**
 * @version 	1.0
 * @author
 */
public class SetFichaCoberturaAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		Logger logger = Logger.getLogger("En Action Lista Candidatos");

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		ServicesCoberturasDelegate delegateCoberturas=new ServicesCoberturasDelegate();

		String codigo=request.getParameter("codigo");
		Cobertura cobertura=null;

		if(codigo==null) codigo="";

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		String botonera=null;

		if(codigo.equals("")){
			cobertura=new Cobertura();
			if (userinformation.hasAccess("cobAgrega")) {
				opciones.add("Agregar Cobertura");
				opcionesValores.add("1");				
			}
		}
		else{
			cobertura=delegateCoberturas.getCobertura((long)Integer.parseInt(codigo));
			logger.debug(new Double(cobertura.getTope()));
			logger.debug(cobertura.getDescripcion());
			logger.debug(new Double(cobertura.getValorReferencial()));
			if(!cobertura.getEstado().equals(Cobertura.STD_ACTIVO)){
				if (userinformation.hasAccess("cobActiva")) {
					opciones.add("Activar Cobertura");
					opcionesValores.add("4");
				}
			}
			if (userinformation.hasAccess("cobActualiza")) {
				opciones.add("Modificar Cobertura");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("cobElimina")) {
				opciones.add("Eliminar Cobertura");
				opcionesValores.add("3");
			}
			botonera="si";
			
			request.getSession(false).removeAttribute("coberturaMaestra");
			// Pregunto si la cobertura es cobertura maestra de otras coberturas
			ArrayList listaAgrupacionCoberturas = delegateCoberturas.getCoberturasByCoberturaMaestra(cobertura.getCodigo());
			if(listaAgrupacionCoberturas.size()>0){
				// Si, si es cobertura maestra
				request.getSession(false).setAttribute("isCoberturaMaestra","yes");
				request.getSession(false).setAttribute("listaAgrupacionCoberturas",listaAgrupacionCoberturas);
				if(listaAgrupacionCoberturas.size()>1)
					request.getSession(false).setAttribute("variasCoberturas","yes");
				else
					request.getSession(false).removeAttribute("variasCoberturas");
			}else{
				// No, no es cobertura maestra
				request.getSession(false).removeAttribute("isCoberturaMaestra");
				// Pregunto si tiene una cobertura maestra que la defina
				AgrupacionCobertura agrupacionCobertura = delegateCoberturas.getRelacionAgrupacionCobertura(cobertura.getCodigo());
				if(agrupacionCobertura!=null){
					// Si, si tiene una cobertura maestra que la define
					ArrayList listaAgrupacionCoberturasDependientes = delegateCoberturas.getCoberturasByCoberturaMaestra(agrupacionCobertura.getCodigoCoberturaMaestra());

					ArrayList listaCoberturas = new ArrayList();
					for(int x=0;x<listaAgrupacionCoberturasDependientes.size();x++){
						Cobertura cob = (Cobertura)listaAgrupacionCoberturasDependientes.get(x);
						if(cob.getCodigo()!=cobertura.getCodigo())
							listaCoberturas.add(cob);
					}
						
					Cobertura coberturaMaestra = delegateCoberturas.getCobertura(agrupacionCobertura.getCodigoCoberturaMaestra());
					request.getSession(false).setAttribute("coberturaMaestra",coberturaMaestra);
					request.getSession(false).setAttribute("listaAgrupacionCoberturas",listaCoberturas);
					if(listaCoberturas.size()>1){
						request.getSession(false).setAttribute("variasCoberturas","yes");
					}else if(listaCoberturas.size()==1){
						request.getSession(false).removeAttribute("variasCoberturas");
					}else {
						request.getSession(false).removeAttribute("listaAgrupacionCoberturas");
					}
				}else{
					// No, no tiene una cobertura maestra que la define
					request.getSession(false).removeAttribute("listaAgrupacionCoberturas");
				}
			}
		}
		
		
		dynaValidatorActionForm.set("valorTope",String.valueOf((int)cobertura.getTope()));
		dynaValidatorActionForm.set("tipoTope",cobertura.getTipoTope());
		dynaValidatorActionForm.set("descripcion",cobertura.getDescripcion());
		dynaValidatorActionForm.set("valorReferencial",String.valueOf((int)cobertura.getValorReferencial()));
		dynaValidatorActionForm.set("concepto",String.valueOf(cobertura.getConceptoCodigo()));
		dynaValidatorActionForm.set("periodoCarencia",String.valueOf((int)cobertura.getPeriodoCarencia()));
		dynaValidatorActionForm.set("tipoCobertura",cobertura.getTipoCobertura());
		dynaValidatorActionForm.set("tieneOcurrencias",cobertura.getTieneOcurrencias());
		dynaValidatorActionForm.set("etiquetaOcurrencias",cobertura.getEtiquetaOcurrencia());
		String codigoCoberturaAdicional="0";
		ArrayList cobAdicional = cobertura.getCoberturaAdicional();
		if(cobAdicional.size()>0)
			codigoCoberturaAdicional=String.valueOf(cobAdicional.get(0)); 
		
		dynaValidatorActionForm.set("codigoCoberturaAdicional",codigoCoberturaAdicional);

		ServicesOperacionesDelegate delegateOperaciones = new ServicesOperacionesDelegate();
		ArrayList listaConceptos=delegateOperaciones.getConceptos();
		
		ArrayList listaCoberturasAdicionales=delegateCoberturas.getListaCoberturasAdicionales();

		request.getSession(false).setAttribute("cobertura",cobertura);
		request.getSession(false).setAttribute("lista.rangos",null);
		request.getSession(false).setAttribute("cobertura.opciones",opciones);
		request.getSession(false).setAttribute("cobertura.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("cobertura.botonera",botonera);
		request.getSession(false).setAttribute("lista.opciones.conceptos",listaConceptos);
		request.getSession(false).setAttribute("lista.coberturas.adicionales",listaCoberturasAdicionales);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("cobertura");
		this.saveToken(request);
		return (forward);

	}
}
