package cl.laaraucana.botonpago.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.forms.SelectCreditosForm;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.ReporteUtil;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;

/**
 * @version 1.0
 * @author
 */
public class PagoEnCajaAction extends Action {

	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		SelectCreditosForm datosform = (SelectCreditosForm) form;

		try {
			// rescata datos
			String op = datosform.getOp();
			String rutDeudor = ((String) session.getAttribute("rutDeudor")).trim().toUpperCase();
			String nombre = ((String) session.getAttribute("nombreDeudor")).trim().toUpperCase();

			//			System.out.println(rutDeudor);

			String total = "";
			Map<String, Object> hash = new HashMap<String, Object>();

			// obtener datos tabla
			Bpagf001 datos = null;
			try {
				DatosDeudorDAO dao = new DatosDeudorDAO();
				datos = dao.getDatoDeudorByRut(rutDeudor.split("-")[0], rutDeudor.split("-")[1]);
				if (datos == null) {
					logger.error("Usuario no existe");
					throw new Exception("Error al obtener datos del deudor");
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al obtener datos del deudor");
				throw new Exception("Error al obtener datos del deudor");
			}

			String paramFolio = datosform.getFolio();// folio
			@SuppressWarnings("unchecked")
			List<CreditoVO> listaCreditos = (List<CreditoVO>) session.getAttribute("listaCreditos");
			for (CreditoVO creditoVO : listaCreditos) {
				if (creditoVO.getOperacion().equals(paramFolio)) {

					if (op.equals("directo")) {
						total = creditoVO.getTotalPagar().trim().replace(".", "").replace(",", ".");// obtengo monto
					} else if (op.equals("monto")) {
						total = datosform.getMonto().trim().replace(".", "").replace(",", ".");// obtengo monto
					}
					int monto = Integer.parseInt(total);
					int min = Integer.parseInt(creditoVO.getPagoMinimo());
					int max = Integer.parseInt(creditoVO.getTotalDeuda());
					if (monto < min || monto > max) {
						//error
						request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
						request.setAttribute("titulo", "Mensaje");
						request.setAttribute("mensaje", "El monto ingresado debe ser mayor o igual al \"Pago mínimo\" y menor o igual al \"Total deuda\"");
						return mapping.findForward("SoloMensajeForward");
					}
					if (monto == 0) {
						request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
						request.setAttribute("titulo", "Mensaje");
						request.setAttribute("mensaje", "El monto a pagar no puede ser igual a cero");
						return mapping.findForward("SoloMensajeForward");

					}
					//valida el pago d el dia
					if(creditoVO.getMontoPagadoDia()!=null){
						if (!creditoVO.getMontoPagadoDia().trim().isEmpty()) {
							request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
							request.setAttribute("titulo", "Mensaje");
							request.setAttribute("mensaje", "No es posible realizar más de un pago al día por cada crédito.");
							return mapping.findForward("SoloMensajeForward");
						}
					}					
					// crear cupon de pago
					String codebar = null;
					String codigoBarra = null;
					try {
						codebar = ManagerCupon.crearCuponDePago(creditoVO, rutDeudor, Constantes.getInstancia().TIPO_PAGO_CAJA, total).get("codigoBarra");
						codigoBarra = Constantes.getInstancia().PREFIJO_CODIGO_BARRA + codebar;
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Error al generar cupon de pago:" + creditoVO + " " + rutDeudor + " " + Constantes.getInstancia().TIPO_PAGO_CAJA + " " + total);
						throw new Exception("Error al generar cupón de pago en caja");
					}

					// setea los datos en el map para el jasperreport
					hash.put("rut", rutDeudor);
					hash.put("nombre", nombre);
					hash.put("fechaEmision", Util.getFecha());
					hash.put("comuna", datos.getComudeu().trim());
					hash.put("region", datos.getRegdeu().trim());
					hash.put("telefono", datos.getFono1().trim());
					hash.put("folio", creditoVO.getOperacion());
					hash.put("colocacion", creditoVO.getFechaColocacion());
					hash.put("plazo", creditoVO.getCuotasTotales());
					hash.put("producto", creditoVO.getProducto());
					hash.put("cuotaMensual", Integer.parseInt(creditoVO.getMontoCuota().trim()));//integer
					hash.put("gravamen", Integer.parseInt(creditoVO.getSumaGravamenConCond().trim()));//integer
					hash.put("gastoCobranza", Integer.parseInt(creditoVO.getGastoCobranzaConCond().trim()));//integer
					hash.put("totalPagar", Integer.parseInt(total.trim()));//integer
					hash.put("codeBar", codigoBarra);
					hash.put("fechaHora", Util.getFechayHora());
					hash.put("logo", ResourceBundle.getBundle("resources.application").getString("path.logo.img"));
					hash.put("timbre", ResourceBundle.getBundle("resources.application").getString("path.timbreAgua.img"));
					break;
				}
			}

			// ruta para obtener el archivo .jasper
			String ruta = ResourceBundle.getBundle("resources.application").getString("path.cupon.jasper");
			// nombre con el que se genera el archivo
			String nombreArchivo = "Cupon de pago";
			ReporteUtil ru = new ReporteUtil(null, hash, ruta);
			byte[] bites = ru.exportCompilePdf();

			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", nombreArchivo);
			logger.debug("Reporte Creado Exitosamente");

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al generar pdf: " + e.getMessage());
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "Ocurrió un error al generar el cupón: " + e.getMessage());
			return mapping.findForward("MensajeForward");
		}

		return mapping.findForward("ExportPDF");

	}

}
