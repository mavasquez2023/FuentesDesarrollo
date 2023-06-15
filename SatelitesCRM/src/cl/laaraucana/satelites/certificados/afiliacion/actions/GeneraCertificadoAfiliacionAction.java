package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.afiliacion.forms.GeneraCertificadoForm;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

/**
 * @version 1.0
 * @author
 */
public class GeneraCertificadoAfiliacionAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		GeneraCertificadoForm form = (GeneraCertificadoForm) _form;

		String rutEmpresa = form.getRutemp();
		String numero = form.getNumero();

		try {
			UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
			List<DetalleEmpresaAfiliado> empresas = (ArrayList<DetalleEmpresaAfiliado>) sesion.getAttribute("empresasList");
			forward = mapping.findForward("generaCertificado");

			if (empresas == null) {
				request.setAttribute("error", "2");
				return forward;
			}
			
			
			boolean encontrado = false;
			int numLista = 0;
			for (DetalleEmpresaAfiliado empresa : empresas) {
				// Busca el rut de la empresa seleccionada en la sesion
				if (empresa.getRutEmpresa().equals(rutEmpresa) && numero.equals(String.valueOf(numLista))) {

					//System.out.println("entro a la cuestión");
					sesion.setAttribute("fechaAfiliacion", empresa.getFechaAfiliacion());
					request.setAttribute("fechaEmision", Utils.getFechaCompleta());
					sesion.setAttribute("nombreEmpresa", empresa.getNombreEmpresa());
					if(ServiciosConst.RES_SERVICIOS.getString("estado.afiliacion.inactivo").trim().equalsIgnoreCase(empresa.getEstadoAfiliacion())){
						forward = mapping.findForward("generaCertificadoInv");
					}
					request.setAttribute("error", "0");
					encontrado = true;
					break;
				}
				numLista++;
			}
			String rut = (String) sesion.getAttribute("rut");
			String nAfiliado = (String) sesion.getAttribute("nombreAfiliado");
			String fAfiliacion = (String) sesion.getAttribute("fechaAfiliacion");
			
			if(rut == null || nAfiliado == null || fAfiliacion == null || rut.length()==0 || nAfiliado.length()==0 || fAfiliacion.length()==0){
				//Cuando faltan datos para llenar el certificado
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
			}
			// Si el rut consultado, no esta en la lista de ruts de empresas del
			// usuario
			if (encontrado == false) {
				request.setAttribute("error", "1");
			}

		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		logger.debug(">>Salida CertificadoAfiliacionAction");
		return forward;

	}

}
