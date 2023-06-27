package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class RegistrarIngresoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		Logger logger = Logger.getLogger(RegistrarEgresoAction.class);
		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
	
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;		
		String target=null;
	
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getCodOficina());
		user.setUsuario(userinformation.getUsuario());		

		logger.debug("Cod. Ofi: "+userinformation.getCodOficina());
		logger.debug("Oficina: "+userinformation.getOficina());

		String pagarA = (String)dynaValidatorActionForm.get("pagarA");
		String formaPago = (String)dynaValidatorActionForm.get("formaPago");
		String montoIngreso = (String)dynaValidatorActionForm.get("montoIngreso");
		logger.debug("pagarA: "+pagarA);
		logger.debug("formaPago: "+formaPago);
		logger.debug("montoIngreso: "+montoIngreso);
		
		ArrayList listaMovimientos=new ArrayList();
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
		logger.debug("tipoIngreso: "+datosMovimientoTesoreriaVO.getTipoMovimiento());
		datosMovimientoTesoreriaVO.setTipoPago(formaPago);	
		listaMovimientos.add(datosMovimientoTesoreriaVO);
		if(datosMovimientoTesoreriaVO.getMonto()==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "El monto del Ingreso debe ser mayor que cero");
		
		if(Double.parseDouble(montoIngreso)>datosMovimientoTesoreriaVO.getMonto())
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "El monto del Ingreso es superor al monto máximo: " + String.valueOf((int)datosMovimientoTesoreriaVO.getMonto()));
		
					   
		datosMovimientoTesoreriaVO.setMonto(Double.parseDouble(montoIngreso));
						   			
		casosDelegate.registrarIngresoTesoreriaPreCaso(listaMovimientos, user);		

		target="success";
		String referer="/prepareListaPreCasos.do";
		request.getSession(false).setAttribute("referer",referer);

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
