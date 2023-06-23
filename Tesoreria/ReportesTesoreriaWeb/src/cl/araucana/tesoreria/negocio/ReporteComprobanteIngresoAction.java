package cl.araucana.tesoreria.negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;
import cl.araucana.tesoreria.dao.ReporteTesoreriaDB2DAOImpl;
import cl.araucana.tesoreria.dao.dvo.ComprobanteIngresoDVO;
import cl.araucana.tesoreria.dao.dvo.ReporteComprobantesDVO;
import cl.araucana.tesoreria.modelo.ReporteComprobantes;
import cl.araucana.tesoreria.util.WebUtils;

public class ReporteComprobanteIngresoAction extends org.apache.struts.action.Action {
	static Logger logger = Logger.getLogger(ReporteComprobanteIngresoAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		String target = "inicio";
		String rolUsuario = (String) session.getAttribute("rolUsuario");
		if (rolUsuario == null || (!rolUsuario.equals("ADMINISTRADOR") && !rolUsuario.equals("EJECUTIVO"))) {
			WebUtils.setError(request, "message.rol.invalido");
			target = "inicio";
		} else {
			String cmd = (String) request.getParameter("_cmd");
			String oficina = (String) request.getParameter("oficina");
			String rutUsuario = (String) session.getAttribute("rutUsuario");
			String rutUsuarioNumero = rutUsuario.substring(0, rutUsuario.length() - 2);
			String oficinasSeleccionadas = (String) request.getParameter("oficinasSeleccionadas");
			if (cmd != null && cmd.equals("inicio")) {
				// valores por defecto para inicio
				Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60 * 24L);
				//SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
				String fechaDesde = sdf.format(yesterday);
				String fechaHasta = sdf.format(new Date());
				ReporteTesoreriaDB2DAOImpl dao = new ReporteTesoreriaDB2DAOImpl();
				ReporteComprobantesDVO[] reporte=null;
				daf.set("fechaDesde", fechaDesde);
				daf.set("fechaHasta", fechaHasta);
				
				if (rolUsuario.equals("ADMINISTRADOR")) {
					reporte = dao.obtenerReporteComprobantes(fechaDesde, fechaHasta);
					daf.set("fechaDesde", "");
					daf.set("fechaHasta", "");
				}	
				
				//daf.set("oficina", "");
				if (reporte != null && reporte.length > 0) {
					ArrayList<ReporteComprobantes> consulta = new ArrayList<ReporteComprobantes>();
					String oficinas = "";
					for (int i = 0; i < reporte.length; i++) {
						ReporteComprobantesDVO reporteDVO = (ReporteComprobantesDVO) reporte[i];
						ReporteComprobantes reporteComprobantes = new ReporteComprobantes();
						reporteComprobantes.setOficinaCodigo(reporteDVO.getOficinaCodigo());
						reporteComprobantes.setOficinaNombre(reporteDVO.getOficinaNombre());
						reporteComprobantes.setComprobantesNumero(reporteDVO.getComprobantesNumero());
						reporteComprobantes.setComprobantesMonto(reporteDVO.getComprobantesMonto());
						consulta.add(reporteComprobantes);
						oficinas += reporteDVO.getOficinaCodigo() + ",";
					}
					session.setAttribute("oficinas", oficinas);
					session.setAttribute("ReporteComprobantes", consulta);
					session.setAttribute("fechaDesdeSession", fechaDesde);
					session.setAttribute("fechaHastaSession", fechaHasta);
					session.setAttribute("oficinaDesdeSession", oficina);
				} else {
					session.setAttribute("ReporteComprobantes", "");
					//WebUtils.setAlert(request, "message.busqueda.resultado.nok");
				}
				session.setAttribute("rolUsuario", rolUsuario);
				target = "inicio";
			} else if (cmd.equals("resultado")) {
				if (oficinasSeleccionadas != null) {
					String fechaDesde = (String) session.getAttribute("fechaDesdeSession");
					String fechaHasta = (String) session.getAttribute("fechaHastaSession");
					String rut = (String) session.getAttribute("rutUsuario");
					try {
						ReporteTesoreriaDB2DAOImpl dao = new ReporteTesoreriaDB2DAOImpl();
						String charset = "ISO-8859-1";
						response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "attachment; filename=Comprobantes.xls");
						WorkbookSettings ws = new WorkbookSettings();
						ws.setEncoding(charset);
						WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream(), ws);
						WritableSheet s = w.createSheet("Comprobantes", 0);
						WritableFont fontBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
						s.addCell(new Label(0, 0, "Oficina", new WritableCellFormat(fontBold)));
						s.addCell(new Label(1, 0, "Folio", new WritableCellFormat(fontBold)));
						s.addCell(new Label(2, 0, "Codigo Barra", new WritableCellFormat(fontBold)));
						s.addCell(new Label(3, 0, "Monto", new WritableCellFormat(fontBold)));
						s.addCell(new Label(4, 0, "Area Negocio", new WritableCellFormat(fontBold)));
						s.addCell(new Label(5, 0, "Fecha", new WritableCellFormat(fontBold)));
						s.addCell(new Label(6, 0, "Nombre", new WritableCellFormat(fontBold)));
						s.addCell(new Label(7, 0, "Rut", new WritableCellFormat(fontBold)));
						s.addCell(new Label(8, 0, "DV", new WritableCellFormat(fontBold)));
						s.setColumnView(0, 20);
						s.setColumnView(3, 15);
						String[] oficinas = oficinasSeleccionadas.split(",");
						ArrayList<ComprobanteIngresoDVO[]> ci = new ArrayList<ComprobanteIngresoDVO[]>();
						for (int i = 0; i < oficinas.length; i++) {
							ComprobanteIngresoDVO[] c = dao.obtenerComprobantesIngresoPorOficina(fechaDesde, fechaHasta, oficinas[i]);
							ci.add(c);
						}
						int position = 1;
						for (Iterator<ComprobanteIngresoDVO[]> iterator = ci.iterator(); iterator.hasNext();) {
							ComprobanteIngresoDVO[] comprobantes = (ComprobanteIngresoDVO[]) iterator.next();
							for (int i = 0; i < comprobantes.length; i++) {
								s.addCell(new Label(0, position, (comprobantes[i].getOficinaCodigo()) != null ? comprobantes[i].getOficinaCodigo() : ""));
								s.addCell(new Label(1, position, (comprobantes[i].getFolio() != null) ? comprobantes[i].getFolio() : ""));
								s.addCell(new Label(2, position, (comprobantes[i].getCodigoBarra() != null) ? comprobantes[i].getCodigoBarra() : ""));
								s.addCell(new Label(3, position, (comprobantes[i].getMonto() != null) ? comprobantes[i].getMonto() : ""));
								s.addCell(new Label(4, position, (comprobantes[i].getAreaNegocio() != null) ? comprobantes[i].getAreaNegocio() : ""));
								s.addCell(new Label(5, position, (comprobantes[i].getFecha() != null) ? comprobantes[i].getFecha() : ""));
								s.addCell(new Label(6, position, (comprobantes[i].getNombre() != null) ? comprobantes[i].getNombre() : ""));
								s.addCell(new Label(7, position, (comprobantes[i].getRut() != null) ? comprobantes[i].getRut() : ""));
								s.addCell(new Label(8, position, (comprobantes[i].getDV() != null) ? comprobantes[i].getDV() : ""));
								position++;
							}
						}
						w.write();
						w.close();
						response.flushBuffer();
					} catch (Exception e) {
						WebUtils.setError(request, "message.excel.error");
						e.printStackTrace();
					}
				} else {
					String fechaDesde = daf.getString("fechaDesde");
					String fechaHasta = daf.getString("fechaHasta");
					ReporteTesoreriaDB2DAOImpl dao = new ReporteTesoreriaDB2DAOImpl();
					ReporteComprobantesDVO[] reporte;
					if (rolUsuario.equals("ADMINISTRADOR") && (oficina== null || oficina.equals(""))) {
						reporte = dao.obtenerReporteComprobantes(fechaDesde, fechaHasta);
						oficina="Todas";
					} else {
						reporte = dao.obtenerReporteComprobantesPorOficina(fechaDesde, fechaHasta, oficina);
					}
					daf.set("fechaDesde", "");
					daf.set("fechaHasta", "");
					//daf.set("oficina", "");
					if (reporte != null && reporte.length > 0) {
						ArrayList<ReporteComprobantes> consulta = new ArrayList<ReporteComprobantes>();
						String oficinas = "";
						for (int i = 0; i < reporte.length; i++) {
							ReporteComprobantesDVO reporteDVO = (ReporteComprobantesDVO) reporte[i];
							ReporteComprobantes reporteComprobantes = new ReporteComprobantes();
							reporteComprobantes.setOficinaCodigo(reporteDVO.getOficinaCodigo());
							reporteComprobantes.setOficinaNombre(reporteDVO.getOficinaNombre());
							reporteComprobantes.setComprobantesNumero(reporteDVO.getComprobantesNumero());
							reporteComprobantes.setComprobantesMonto(reporteDVO.getComprobantesMonto());
							consulta.add(reporteComprobantes);
							oficinas += reporteDVO.getOficinaCodigo() + ",";
						}
						session.setAttribute("oficinas", oficinas);
						session.setAttribute("ReporteComprobantes", consulta);
						session.setAttribute("fechaDesdeSession", fechaDesde);
						session.setAttribute("fechaHastaSession", fechaHasta);
						session.setAttribute("oficinaDesdeSession", oficina);
					} else {
						session.setAttribute("ReporteComprobantes", "");
						daf.set("fechaDesde", fechaDesde);
						daf.set("fechaHasta", fechaHasta);
						WebUtils.setAlert(request, "message.busqueda.resultado.nok");
					}
				}
				target = "inicio";
			} else if (cmd != null && cmd.equals("excel") && oficina != null) {
				String fechaDesde = (String) session.getAttribute("fechaDesdeSession");
				String fechaHasta = (String) session.getAttribute("fechaHastaSession");
				String rut = (String) session.getAttribute("rutUsuario");
				try {
					ReporteTesoreriaDB2DAOImpl dao = new ReporteTesoreriaDB2DAOImpl();
					String charset = "ISO-8859-1";
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=Comprobantes.xls");
					WorkbookSettings ws = new WorkbookSettings();
					ws.setEncoding(charset);
					WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream(), ws);
					WritableSheet s = w.createSheet("Comprobantes", 0);
					WritableFont fontBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
					s.addCell(new Label(0, 0, "Oficina", new WritableCellFormat(fontBold)));
					s.addCell(new Label(1, 0, "Folio", new WritableCellFormat(fontBold)));
					s.addCell(new Label(2, 0, "Codigo Barra", new WritableCellFormat(fontBold)));
					s.addCell(new Label(3, 0, "Monto", new WritableCellFormat(fontBold)));
					s.addCell(new Label(4, 0, "Area Negocio", new WritableCellFormat(fontBold)));
					s.addCell(new Label(5, 0, "Fecha", new WritableCellFormat(fontBold)));
					s.addCell(new Label(6, 0, "Nombre", new WritableCellFormat(fontBold)));
					s.addCell(new Label(7, 0, "Rut", new WritableCellFormat(fontBold)));
					s.addCell(new Label(8, 0, "DV", new WritableCellFormat(fontBold)));
					s.setColumnView(0, 20);
					s.setColumnView(3, 15);
					ComprobanteIngresoDVO[] comprobantes = dao.obtenerComprobantesIngresoPorOficina(fechaDesde, fechaHasta, oficina);
					for (int i = 0; i < comprobantes.length; i++) {
						int position = i + 1;
						s.addCell(new Label(0, position, (comprobantes[i].getOficinaCodigo()) != null ? comprobantes[i].getOficinaCodigo() : ""));
						s.addCell(new Label(1, position, (comprobantes[i].getFolio() != null) ? comprobantes[i].getFolio() : ""));
						s.addCell(new Label(2, position, (comprobantes[i].getCodigoBarra() != null) ? comprobantes[i].getCodigoBarra() : ""));
						s.addCell(new Label(3, position, (comprobantes[i].getMonto() != null) ? comprobantes[i].getMonto() : ""));
						s.addCell(new Label(4, position, (comprobantes[i].getAreaNegocio() != null) ? comprobantes[i].getAreaNegocio() : ""));
						s.addCell(new Label(5, position, (comprobantes[i].getFecha() != null) ? comprobantes[i].getFecha() : ""));
						s.addCell(new Label(6, position, (comprobantes[i].getNombre() != null) ? comprobantes[i].getNombre() : ""));
						s.addCell(new Label(7, position, (comprobantes[i].getRut() != null) ? comprobantes[i].getRut() : ""));
						s.addCell(new Label(8, position, (comprobantes[i].getDV() != null) ? comprobantes[i].getDV() : ""));
					}
					w.write();
					w.close();
					response.flushBuffer();
				} catch (Exception e) {
					WebUtils.setError(request, "message.excel.error");
					e.printStackTrace();
				}
			}
		}
		return mapping.findForward(target);
	}
}
