package cl.araucana.bienestar.bonificaciones.ui.actions.cobertura;
import java.text.SimpleDateFormat;
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
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesRangosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		Logger logger = Logger.getLogger(OpcionesRangosAction.class);


		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;

		Cobertura cobertura=(Cobertura)request.getSession(false).getAttribute("cobertura");

		logger.debug("indice:"+(String)dynaValidatorActionForm.get("indice"));
		logger.debug("opcion:"+(String)dynaValidatorActionForm.get("opcion"));
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		int indice=0;
		String index=(String)dynaValidatorActionForm.get("indice");
		Rango rango=new Rango();

		String inicioVig = (String)dynaValidatorActionForm.get("fechaInicioVig");
		String finVig = (String)dynaValidatorActionForm.get("fechaFinVig");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (cobertura.getRangoVigente() == null)
			cobertura.setRangoVigente(new VigenciaRango());
		cobertura.getRangoVigente().setInicioVigencia(formatter.parse(inicioVig));
		if (!finVig.equals(""))
			cobertura.getRangoVigente().setFinVigencia(formatter.parse(finVig));

		if(index==null) index="";
		if(!index.equals(""))	
		{
			indice=(int)Integer.parseInt(index);			
		}
		else{
			if(opcion!=1 && opcion!=4) opcion=0;
		}
		if(opcion==1 || opcion==2){		
			if(((String)dynaValidatorActionForm.get("inicioRango")).trim().equals("")){
				rango.setRangoInicio(0);
			}
			else{
				rango.setRangoInicio((double)Double.parseDouble((String)dynaValidatorActionForm.get("inicioRango")));
			}
			if(((String)dynaValidatorActionForm.get("finRango")).trim().equals("")){
				rango.setRangoFin(0);
			}
			else{
				rango.setRangoFin((double)Double.parseDouble((String)dynaValidatorActionForm.get("finRango")));
				dynaValidatorActionForm.set("inicioRango",(String)dynaValidatorActionForm.get("finRango"));
				dynaValidatorActionForm.set("finRango","0");
			}
			if(((String)dynaValidatorActionForm.get("porcentajeCobertura")).trim().equals("")){
				rango.setRangoPorcentajeCobertura(0);
				dynaValidatorActionForm.set("porcentajeCobertura","");
			}
			else{
				rango.setRangoPorcentajeCobertura((double)Double.parseDouble((String)dynaValidatorActionForm.get("porcentajeCobertura")));
			}
		}
		long codigo=Long.parseLong((String)dynaValidatorActionForm.get("cuentaGasto"));
		cobertura.setCuentaGasto(codigo);
		String target="listaRangos";
		logger.debug("opcion final:"+opcion);
		boolean actualizo=false;
		switch(opcion){			
			case 1:		cobertura.getRangoVigente().addRango(rango,cobertura.getTipoCobertura());	
						break;
			case 2:		cobertura.getRangoVigente().removeRango(indice);
						cobertura.getRangoVigente().addRango(rango,cobertura.getTipoCobertura());
						break;
			case 3:		cobertura.getRangoVigente().removeRango(indice);		
						break;
			case 4:		ServicesCoberturasDelegate delegate=new ServicesCoberturasDelegate();
						delegate.actualizaRangosVigentesCobertura(cobertura);
						actualizo=true;			
						target="success";
						break;
			default:	break;
		}

		//dynaValidatorActionForm.reset(mapping,request);
		// Guardar en memoria el resultado
		String referer="/getListaRangos.do";
		request.getSession(false).setAttribute("referer",referer);
		ArrayList listaRangos=cobertura.getRangoVigente().getRangos();
		if(actualizo)
			listaRangos=null;
		request.getSession(false).setAttribute("lista.rangos",listaRangos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);
	}

}
