package cl.araucana.bienestar.bonificaciones.ui.actions.socio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosBeneficiarioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Socio socio=(Socio)request.getSession(false).getAttribute("socio");

		String tipo=request.getParameter("tipo");
		String target=null;
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		String headerCarga=null;
		
		//Busco la información del Socio, ya que no está completa aún en el objeto que está en memoria
		ServicesSociosDelegate delegateSocios = new ServicesSociosDelegate();
		socio = delegateSocios.getSocio(socio.getRut());
		request.getSession(false).setAttribute("socio", socio);
		
		ArrayList listaCasos=null;
		if(tipo==null) tipo="";
		if(tipo.equals("bono")){
			listaCasos=delegate.getBonos(socio.getRut());
			target="listaBonos";
		}
		else if(tipo.equals("reembolso")){
			listaCasos=delegate.getReembolsos(socio.getRut());
			target="listaReembolsos";
		}
		else if(tipo.equals("caso")){
			Caso caso=new Caso();
			caso.setRutSocio(socio.getRut());
			caso.setEstado("");
			listaCasos=delegate.getListaCasos(caso);
			target="listaCasos";
		}
		else if(tipo.equals("carga")){
			Carga carga=(Carga)request.getSession(false).getAttribute("carga");
			Caso caso=new Caso();
			caso.setRutCarga(carga.getRutCarga());
			caso.setEstado("");
			listaCasos=delegate.getListaCasos(caso);
			target="listaCasos";
			headerCarga="si";
		}

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.casos",listaCasos);
		request.getSession(false).setAttribute("carga.header",headerCarga);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
