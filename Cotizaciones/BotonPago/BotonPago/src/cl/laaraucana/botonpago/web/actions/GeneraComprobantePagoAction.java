package cl.laaraucana.botonpago.web.actions;

import java.util.HashMap;
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
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.forms.ComprobantePagadoForm;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.ReporteUtil;
import cl.laaraucana.botonpago.web.utils.Util;

public class GeneraComprobantePagoAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		//		User user = (User) session.getAttribute("user");

		ComprobantePagadoForm form = (ComprobantePagadoForm) _form;

		try {
			// rescata datos
			String rutDeudor = ((String) session.getAttribute("rutDeudor")).trim().toUpperCase();
			String nombre = ((String) session.getAttribute("nombreDeudor")).trim().toUpperCase();

			Map<String, Object> hash = new HashMap<String, Object>();

			// obtener datos tabla
			DatosDeudorDAO dao = new DatosDeudorDAO();
			String rut = rutDeudor.split("-")[0];
			String dvRut = rutDeudor.split("-")[1];
			Bpagf001 datos = null;
			try {
				datos = dao.getDatoDeudorByRut(rut, dvRut);
				if (datos == null) {
					logger.error("Usuario no existe");
					throw new Exception("Error al obtener datos del deudor");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al obtener los datos del deudor: " + rutDeudor);
				throw new Exception("Error al obtener los datos del deudor");
			}
			BpagF002 cupon = null;
			try {
				cupon = ManagerCupon.cuponByEstadoAndFolioAndRut(rutDeudor, Constantes.getInstancia().ESTADO_CURSADO, form.getFolio());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al obtener los datos del cupon: " + rutDeudor + " " + form.getFolio());
				throw new Exception("Error al obtener los datos del cupon");
			}

			// setea los datos en el map
			hash.put("rut", rutDeudor);
			hash.put("nombre", nombre);
			hash.put("fechaPago", cupon.getFechaPago());
			hash.put("comuna", datos.getComudeu().trim());
			hash.put("region", datos.getRegdeu().trim());
			hash.put("telefono", datos.getFono1().trim());
			hash.put("folio", cupon.getOfiPro().trim() + "-" + cupon.getCreFol().trim());
			hash.put("gravamen", Integer.parseInt(cupon.getValCondGra().trim()));// integer
			hash.put("gastoCobranza", Integer.parseInt(cupon.getValCondGas().trim()));// integer
			hash.put("totalPagado", Integer.parseInt(cupon.getMtoPagar().trim()));// integer
			hash.put("codeBar", cupon.getPrefijo() + cupon.getCodBarra());
			hash.put("fechaHora", Util.getFechayHora());
			hash.put("logo", ResourceBundle.getBundle("resources.application").getString("path.logo.img"));
			hash.put("timbre", ResourceBundle.getBundle("resources.application").getString("path.timbrePagado.img"));

			// ruta para obtener el archivo .jasper
			String ruta = ResourceBundle.getBundle("resources.application").getString("path.cupon.pagado.jasper");
			// nombre con el que se genera el archivo
			String nombreArchivo = "Comprobante de pago";
			ReporteUtil ru = new ReporteUtil(null, hash, ruta);
			byte[] bites = ru.exportCompilePdf();

			logger.debug("Reporte Creado Exitosamente");

			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", nombreArchivo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al generar pdf: " + e.getMessage());
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("MensajeForward");
		}

		return mapping.findForward("ExportPDF");

	}

}
