package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author
 */
public class GeneraPagoReembolsoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String target = "listaReembolso";

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

//		String [] opcion=(String [])dynaValidatorActionForm.get("indice");
		String [] opcion=request.getParameterValues("indices");
		ArrayList listaCasos=(ArrayList)request.getSession(false).getAttribute("lista.reembolsos");
		ArrayList reembolsos=new ArrayList();
		
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					   "No ha seleccionado casos por reembolsar");
		
		for(int t=0;t<opcion.length;t++){
			reembolsos.add((ReembolsoVO)listaCasos.get((int)Integer.parseInt(opcion[t])));
		}
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getCodOficina());
		user.setUsuario(userinformation.getUsuario());

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		delegate.generarReembolsos(reembolsos,user);
		String referer="/getListaCasosPorReembolsar.do";
		target="success";

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
