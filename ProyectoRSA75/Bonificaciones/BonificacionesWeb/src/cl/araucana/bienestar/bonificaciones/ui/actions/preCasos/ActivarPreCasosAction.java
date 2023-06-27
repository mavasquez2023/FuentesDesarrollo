package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class ActivarPreCasosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();

		String target=null;
		
		String [] opcion=request.getParameterValues("indices3");
		ArrayList listaCasos=(ArrayList)request.getSession(false).getAttribute("listaPreCasosConPorLoMenosUnEgresoGenerado");
		ArrayList casosIDs=new ArrayList();		
				
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "No ha seleccionado casos para Activar");
		
		for(int t=0;t<opcion.length;t++){
			casosIDs.add(new Long(((CasoVO)listaCasos.get((int)Integer.parseInt(opcion[t]))).getCasoID()));
		}				
		
		casosDelegate.activarListaPreCasos(casosIDs);		

		target="success";
		String referer="/prepareListaPreCasos.do";
		request.getSession(false).setAttribute("referer",referer);

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
