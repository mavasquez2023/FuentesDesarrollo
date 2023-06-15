package cl.laaraucana.compromisototal.compTotal.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.compromisototal.VO.ContratoIntercajaVO;
import cl.laaraucana.compromisototal.dao.intercaja.IntercajasDAO;
import cl.laaraucana.compromisototal.dao.intercaja.domain.SalidaListaIntercajaVO;
import cl.laaraucana.compromisototal.dao.intercaja.domain.Sinaf20h;
import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class GetContratoIntercajaAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();

		String rut = null;
		rut= request.getParameter("rut");
		if(rut==null || rut.equals("")){
			rut = (String) sesion.getAttribute("rut");
		}
		logger.debug("Entro a getContratoIntercaja con rut: " + rut);

		String mensajeDAO = "0";
		String codErrorDao = "";
		String codErrorIntercaja = null;
		ArrayList<ContratoIntercajaVO> listaIntercaja = new ArrayList<ContratoIntercajaVO>();

		if (rut == null) {
			codErrorIntercaja = Codigo.SESSION;
		} else {
			if (sesion.getAttribute("listaIntercaja") == null) {

				// -------------------------------------------------
				try {
					IntercajasDAO dao = new IntercajasDAO();
					SalidaListaIntercajaVO salidaDao = dao.getCreditosIntercaja(rut);
					codErrorDao = salidaDao.getCodError();

					if (codErrorDao.equals(Codigo.OK)) {
						codErrorIntercaja = Codigo.OK;
						ArrayList<Sinaf20h> listaDao = salidaDao.getListaIntercaja();

						for (Sinaf20h credito : listaDao) {
							ContratoIntercajaVO contrato = new ContratoIntercajaVO();
							//mapeo
							contrato.setPeriodo(credito.getSinperi().trim());
							contrato.setCodigoCajaOrigen(Utils.replaceIdentificacionCaja(credito.getSincajo().trim()));
							contrato.setCodigoCajaDestino(Utils.replaceIdentificacionCaja(credito.getSincajd().trim()));
							contrato.setNumeroPagare(credito.getSinnpag().trim());
							contrato.setIdentEmpresa(credito.getSinidem().trim());
							contrato.setIdentDeudor(credito.getSinided().trim());
							contrato.setNombreDeudor(credito.getSinnomd().trim());
							contrato.setIdentAval(credito.getSinidea().trim());
							contrato.setSujetoDescuento(Utils.replaceSujetoDcto(credito.getSinsujd().trim()));
							contrato.setMontoOfertaCompra(credito.getSinmcob().trim());
							contrato.setMontoCuotaDeudor(credito.getSinmcob06().trim());

							listaIntercaja.add(contrato);
						}

					} else if (codErrorDao.equals(Codigo.ERROR)) {
						mensajeDAO = "Error al consultar la base de datos.";
					}

				} catch (Exception e) {
					codErrorDao = Codigo.ERROR;
					mensajeDAO = "Error inesperado al consultar Intercajas ";
				}
				// -------------------------------------------------
				if (codErrorDao.equals(Codigo.VACIO)) {
					codErrorIntercaja = Codigo.VACIO;
				}
				if (!codErrorDao.equals(Codigo.ERROR)) {
					// guarda en sesion solo si no existen errores
					sesion.setAttribute("listaIntercaja", listaIntercaja);
					sesion.setAttribute("codErrorIntercaja", codErrorIntercaja);
				}
			} else {
				listaIntercaja = (ArrayList<ContratoIntercajaVO>) sesion.getAttribute("listaIntercaja");
				codErrorIntercaja = (String) sesion.getAttribute("codErrorIntercaja");
				logger.debug("obtiene datos de la  sesion!!!");
			}

		}
		request.setAttribute("opcion", "intercaja");
		request.setAttribute("rut", rut);
		request.setAttribute("listaIntercaja", listaIntercaja);
		request.setAttribute("error", codErrorIntercaja);
		request.setAttribute("mensajeDAO", mensajeDAO);

		forward = mapping.findForward("datosTablas");
		return (forward);

	}
}
