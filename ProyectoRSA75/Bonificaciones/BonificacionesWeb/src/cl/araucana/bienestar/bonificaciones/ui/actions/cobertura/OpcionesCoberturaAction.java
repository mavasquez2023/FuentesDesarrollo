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
import cl.araucana.bienestar.bonificaciones.model.CoberturaAdicional;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesCoberturaAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		Logger logger = Logger.getLogger(OpcionesCoberturaAction.class);
		logger.debug("Valor Tope="+(String)dynaValidatorActionForm.get("valorTope"));
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		Cobertura cobertura=(Cobertura)request.getSession(false).getAttribute("cobertura");
		if(opcion==1 || opcion==2){
			cobertura.setDescripcion((String)dynaValidatorActionForm.get("descripcion"));
			cobertura.setConceptoCodigo((long)Long.parseLong((String)dynaValidatorActionForm.get("concepto")));
			cobertura.setTipoTope((String)dynaValidatorActionForm.get("tipoTope"));
			cobertura.setTope((double)Double.parseDouble((String)dynaValidatorActionForm.get("valorTope")));
			cobertura.setValorReferencial((double)Double.parseDouble((String)dynaValidatorActionForm.get("valorReferencial")));
			cobertura.setPeriodoCarencia((int)Integer.parseInt((String)dynaValidatorActionForm.get("periodoCarencia")));
			cobertura.setTipoCobertura((String)dynaValidatorActionForm.get("tipoCobertura"));
			cobertura.setTieneOcurrencias((String)dynaValidatorActionForm.get("tieneOcurrencias"));
			cobertura.setEtiquetaOcurrencia((String)dynaValidatorActionForm.get("etiquetaOcurrencias"));
			//cobertura.setCodigoCoberturaAdicional((long)Long.parseLong((String)dynaValidatorActionForm.get("codigoCoberturaAdicional")));
			long codigoCoberturaAdicional = (long)Long.parseLong((String)dynaValidatorActionForm.get("codigoCoberturaAdicional"));
			if(codigoCoberturaAdicional>0) {
				ArrayList coberturasAdicionales = new ArrayList();
				CoberturaAdicional coberturaAdicional = new CoberturaAdicional();
				coberturaAdicional.setCodigo(cobertura.getCodigo());
				coberturaAdicional.setCodigoCoberturaAdicional(codigoCoberturaAdicional);
				coberturaAdicional.setOrden(1);
				coberturasAdicionales.add(coberturaAdicional);
				cobertura.setCoberturaAdicional(coberturasAdicionales);
			}
		}
		String target=null;

		ServicesCoberturasDelegate delegate = new ServicesCoberturasDelegate();
		switch(opcion){
			case 1:		delegate.registraCobertura(cobertura);	
							target="success";
							break;
			case 2:		delegate.actualizaCobertura(cobertura);		
							target="success";
							break;
			case 3:		delegate.eliminaCobertura(cobertura);		
							target="success";
							break;
			case 4:		delegate.activaCobertura(cobertura);		
							target="success";
							break;
			default:	target="noservice";
						break;
		}
			//dynaValidatorActionForm.reset(mapping,request);
			// Guardar en memoria el resultado
			String referer="/getListaCoberturas.do";
			request.getSession(false).setAttribute("referer",referer);

			// Write logic determining how the user should be forwarded.
			ActionForward forward = new ActionForward();
			forward = mapping.findForward(target);
			this.resetToken(request);
			return (forward);

	}
}
