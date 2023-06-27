package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

public class GetCartolaAhorro extends Action
{
  private static Logger logger = Logger.getLogger(GetCartolaAhorro.class);
  public static final String GLOBAL_FORWARD_exito = "cartola";
  public static final String GLOBAL_FORWARD_listaCuentasAhorro = "listaCuentasAhorro";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
		HttpSession session = request.getSession(true);
		UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		String dispositivo = request.getRemoteAddr();
		long lRutAfiliado = 0;
		String target=GLOBAL_FORWARD_exito;
		String nombreConsulta = "";
		String rutConsulta= "";

	
		DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
		String cuentaAhorro="";
		try	{
			cuentaAhorro = (String)daf.get("cuenta");
		} catch (Exception ex) {
		
		}

		lRutAfiliado = usuario.getRut();	

		nombreConsulta = usuario.getNombre();
		rutConsulta = usuario.getFullRut();

	    session.removeAttribute("cartola");
	    session.setAttribute("cartola", "");

	    if (usuario.isEsAhorrante())
	    {
	      if ((cuentaAhorro == null) || (cuentaAhorro.length() == 0)) {
	        CuentasAhorroRutVO cuentasAhorroRut = delegate.getCuentasAhorroByRut(lRutAfiliado);

	        if (cuentasAhorroRut.getCantidadCuentas() == 0) {
		      session.setAttribute("validation.message", "label.cartola.sin.cuentas");
		      session.setAttribute("validation.info","");
	        }
	        else if (cuentasAhorroRut.getCantidadCuentasActivas() == 1) {
	          Collection cuentas = cuentasAhorroRut.getCuentas();
	          Iterator icuentas = cuentas.iterator();
	          CuentaAhorroVO cuenta = (CuentaAhorroVO)icuentas.next();
	          cuentaAhorro = cuenta.getNumeroCuenta();
	          session.setAttribute("nombre", nombreConsulta);
	          session.setAttribute("rut", rutConsulta);
	          session.setAttribute("cartola", delegate.getCartolaAhorro(lRutAfiliado, cuentaAhorro, dispositivo));
	        }
	        else if (cuentasAhorroRut.getCantidadCuentasActivas() > 1) {
	          session.setAttribute("lista.cuentas", cuentasAhorroRut.getCuentas());
	          // listar
	        }
	        else if (cuentasAhorroRut.getCantidadCuentasInactivas() > 0) {
		      request.setAttribute("validation.message", "label.cartola.sin.cuentas.activas");
		      request.setAttribute("validation.info","");
	        }
	      }
	      else {
	        session.setAttribute("cartola", delegate.getCartolaAhorro(lRutAfiliado, cuentaAhorro, dispositivo));
	      }
	    }
	    else {
	      request.setAttribute("validation.message", "label.cartola.sin.cuentas");
	      request.setAttribute("validation.info","");
	    }

	    logger.debug("Presenta pagina: " + target);
	    return mapping.findForward(target);

  }
}


