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
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author
 */
public class GeneraDescuentoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String target = "listaDescuento";

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

		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getOficina());
		user.setUsuario(userinformation.getUsuario());


		String [] opcion=request.getParameterValues("indices");
		ArrayList listaCasos=(ArrayList)request.getSession(false).getAttribute("lista.descuentos");
		ArrayList descuentos=new ArrayList();
		
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					   "No ha seleccionado casos por descontar");
		
		for(int t=0;t<opcion.length;t++){
			descuentos.add((DescuentosVO)listaCasos.get((int)Integer.parseInt(opcion[t])));
		}
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		delegate.generarDescuentos(descuentos,user);
		String referer="/getListaCasosPorDescontar.do";
		target="success";

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
