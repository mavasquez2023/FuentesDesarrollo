package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author
 */
public class CalcularBonificacionAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String target = "listaCasosNoBonificados";

		String filtrar = null;
		Iterator it = request.getParameterMap().keySet().iterator();
		while ( it.hasNext() ) {
			String campo = ( String )it.next();
			if ( campo.startsWith( "_filtrar" ) ) {
				filtrar = campo.substring( 0, campo.length() - 2 );
				break;
			}
		}
		if (filtrar != null){
			ActionForward forward = new ActionForward();
			forward = mapping.findForward(target);
			return (forward);
		}

		String [] opcion=request.getParameterValues("indices");
		ArrayList listaCasos=(ArrayList)request.getSession(false).getAttribute("lista.casos");
		ArrayList casosIDs=new ArrayList();
		
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "No ha seleccionado casos por reembolsar");
		
		for(int t=0;t<opcion.length;t++){
			casosIDs.add(new Long(((Caso)listaCasos.get((int)Integer.parseInt(opcion[t]))).getCasoID()));
		}
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		delegate.bonificar(casosIDs);
		String referer="/getListaCasosAbiertos.do";
		target="success";
	
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);
	
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
