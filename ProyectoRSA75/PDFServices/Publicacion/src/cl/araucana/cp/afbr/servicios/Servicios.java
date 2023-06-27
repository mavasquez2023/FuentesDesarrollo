package cl.araucana.cp.afbr.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import utilMonth.Zippeo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import cl.araucana.cp.afbr.business.ParametrosReporteBean;
import cl.araucana.cp.afbr.dao.AfbrDAO;
import cl.araucana.cp.hibernate.beans.MesesbeanVO;

import com.onbarcode.barcode.IBarcode;
import com.onbarcode.barcode.PDF417;

public class Servicios {

	public Servicios() {

	}

	public static String toFecha(String fecha) {

		String mes, dia, año;

		dia = fecha.substring(6, 8);
		mes = fecha.substring(4, 6);
		año = fecha.substring(0, 4);

		return dia + "/" + mes + "/" + año;
	}

	public static String toPeriodo(String fecha) {

		String mes, año;

		mes = fecha.substring(4, 6);
		año = fecha.substring(0, 4);

		return mes + "/" + año;
	}

	public static String barra(String num, String rutaBarra) {

		String ruta = rutaBarra + "barcode.gif";

		try {
			PDF417 bar = new PDF417();

			bar.setData(num);

			// PDF 417 Error Correction Level
			bar.setEcl(PDF417.ECL_8);
			bar.setRowCount(30);
			bar.setColumnCount(5);
			bar.setDataMode(PDF417.M_AUTO);

			bar.setTruncated(true);

			// Set the processTilde property to true, if you want use the tilde
			// character "~" to specify special characters in the input data.
			// Default is false.
			// 1-byte character: ~ddd (character value from 0 ~ 255)
			// ASCII (with EXT): from ~000 to ~255
			// 2-byte character: ~6ddddd (character value from 0 ~ 65535)
			// Unicode: from ~600000 to ~665535
			// ECI: from ~7000000 to ~7999999
			bar.setProcessTilde(true);

			/*
			 * // for macro PDF 417 barcode.setMacro(false);
			 * barcode.setMacroSegmentIndex(0); barcode.setMacroSegmentCount(0);
			 * barcode.setMacroFileIndex(0);
			 */

			// PDF-417 unit of measure for X, Y, LeftMargin, RightMargin,
			// TopMargin, BottomMargin
			bar.setUom(IBarcode.UOM_PIXEL);
			// PDF-417 barcode module width in pixel
			bar.setX(3f);
			bar.setXtoYRatio(0.3f);

			bar.setLeftMargin(10f);
			bar.setRightMargin(10f);
			bar.setTopMargin(10f);
			bar.setBottomMargin(10f);
			// barcode image resolution in dpi
			bar.setResolution(72);

			bar.drawBarcode(ruta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ruta;
	}

	public static void generarReporte(String codigoProceso, String empag,
			String folio, String rutEmpresa, String rutaBarra,
			String rutaJasper, String rutaPDF, String pathJrxml,
			String pathPdf, HttpServletResponse response) throws JRException,
			SQLException, IOException {

		ParametrosReporteBean empresas = new ParametrosReporteBean();

		AfbrDAO dao = new AfbrDAO();

		Connection con = null;

		con = dao.getConnection();

		empresas = dao.getDatosReporte(codigoProceso, empag, folio, rutEmpresa);

		String normal = "", atrasada = "", adelantada = "";

		String tipoPago = empresas.getPWCATIPAG();

		normal = tipoPago.substring(0, 1);
		atrasada = tipoPago.substring(1, 2);
		adelantada = tipoPago.substring(2, 3);

		if (normal.equals("1")) {
			normal = "X";
		} else {
			normal = "";
		}
		if (atrasada.equals("1")) {
			atrasada = "X";
		} else {
			atrasada = "";
		}
		if (adelantada.equals("1")) {
			adelantada = "X";
		} else {
			adelantada = "";
		}

		String ruta = barra(rutEmpresa + empresas.getPWCATOPAG()
				+ empresas.getPWCACOPRO(), rutaBarra);

		Map param_map = new HashMap();

		param_map.put("folio", empresas.getPWCAFOLIO());
		param_map.put("pagina", "1");
		param_map.put("totalAporte", new Long(Long.parseLong(empresas
				.getPWCATOAPO())));
		param_map.put("razonSocial", empresas.getPWCARAZSO());
		param_map.put("rutEmpresa", empresas.getPWCARUTEM());
		param_map.put("codigoActividadEconomica", new Long(Long
				.parseLong(empresas.getPWCAACTEC())));
		param_map.put("direccion", empresas.getPWCADIREM());
		param_map.put("email", "");
		param_map.put("telefono", empresas.getPWCATELEM());
		param_map.put("representanteLegal", empresas.getPWCANOMRE());
		param_map.put("rutRepresentante", empresas.getPWCARUTRE());
		param_map.put("cambiosRepresentante", empresas.getPWCACAMRE().equals(
				"1") ? "X" : "");
		param_map.put("totalPagar", new Long(Long.parseLong(empresas
				.getPWCATOPAG())));
		param_map.put("totalRemuneracionesoGratificaciones", new Long(Long
				.parseLong(empresas.getPWCATOREM())));
		param_map.put("trabajadoresInformados", empresas.getPWCATOTRA());
		param_map.put("numeroHojasAnexas", empresas.getPWCAHOJAN());
		param_map.put("fechaPago", Servicios.toFecha(empresas.getPWCAFEPAG()));
		param_map.put("periodo", Servicios.toPeriodo(empresas.getPWCACOPRO()));
		param_map.put("desde", empresas.getPWCAFEDES().equals("0") ? ""
				: Servicios.toFecha(empresas.getPWCAFEDES()));
		param_map.put("hasta", empresas.getPWCAFEHAS().equals("0") ? ""
				: Servicios.toFecha(empresas.getPWCAFEHAS()));
		param_map.put("pagadora", empresas.getPWCAEMPAG().trim());
		param_map.put("normal", normal);
		param_map.put("remuneracionesMes", "X");
		param_map.put("retroactivo", "");
		param_map.put("atrasada", atrasada);
		param_map.put("adelantada", adelantada);
		param_map.put("efectivo", "");
		param_map.put("cheque", "");
		param_map.put("tipoProceso", empresas.getPWCATIPRO());
		param_map.put("digem", empresas.getPWCADIGEM());
		param_map.put("digre", empresas.getPWCADIGRE());
		param_map.put("logoAraucana", rutaJasper + "timbre.jpg");
		param_map.put("barcode", ruta);
		param_map.put("firma", rutaJasper + "firma.gif");
		param_map.put("logo", rutaJasper + "logo.jpg");
		
		JasperDesign design = JRXmlLoader.load(pathJrxml);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map,
				con);// new JREmptyDataSource());

		con.close();

		JasperExportManager.exportReportToPdfFile(jPrint, pathPdf);

		FileInputStream archivo = new FileInputStream(pathPdf);
		int longitud = archivo.available();
		byte[] datos = new byte[longitud];
		archivo.read(datos);
		archivo.close();

		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment;filename=reporteAfbr.pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();
	}

	public static void descargarZip(MesesbeanVO omeses,
			HttpServletResponse response, String rutaZip, String convenio,
			String rutEmpresa, String rutaBarra, String pathJrxml,
			String rutaJasper) throws JRException, IOException, SQLException {

		AfbrDAO dao = new AfbrDAO();
		Connection con = dao.getConnection();
		String normal, atrasada, adelantada;

		Date date = new Date();
		long serie = date.getTime();
		String carpeta = "Folder".concat(String.valueOf(serie));

		File folder = new File(rutaZip + carpeta);
		if (!folder.exists())
			folder.mkdir();

		convenio = convenio.trim().replaceAll(" ", "','");
		rutEmpresa = rutEmpresa.trim().replaceAll(" ", ",");

		omeses.setConvenio(convenio);
		omeses.setRutEmpresa(rutEmpresa);
		
		List empresas = dao.getDatosReporteGrilla(omeses);

		int i = 100;
		for (Iterator iter = empresas.iterator(); iter.hasNext();) {
			ParametrosReporteBean p = (ParametrosReporteBean) iter.next();

			ParametrosReporteBean obean = new ParametrosReporteBean();

			obean = dao.getDatosReporte(p.getPWCACOPRO(), p.getPWCAEMPAG(), p
					.getPWCAFOLIO(), p.getPWCARUTEM());

			String tipoPago = obean.getPWCATIPAG();

			normal = tipoPago.substring(0, 1);
			atrasada = tipoPago.substring(1, 2);
			adelantada = tipoPago.substring(2, 3);

			if (normal.equals("1")) {
				normal = "X";
			} else {
				normal = "";
			}
			if (atrasada.equals("1")) {
				atrasada = "X";
			} else {
				atrasada = "";
			}
			if (adelantada.equals("1")) {
				adelantada = "X";
			} else {
				adelantada = "";
			}

			String ruta = barra(obean.getPWCARUTEM() + obean.getPWCATOPAG()
					+ obean.getPWCACOPRO(), rutaBarra);

			Map param_map = new HashMap();

			param_map.put("folio", obean.getPWCAFOLIO());
			param_map.put("pagina", "1");
			param_map.put("totalAporte", new Long(Long.parseLong(obean
					.getPWCATOAPO())));
			param_map.put("razonSocial", obean.getPWCARAZSO());
			param_map.put("rutEmpresa", obean.getPWCARUTEM());
			param_map.put("codigoActividadEconomica", new Long(Long
					.parseLong(obean.getPWCAACTEC())));
			param_map.put("direccion", obean.getPWCADIREM());
			param_map.put("email", "");
			param_map.put("telefono", obean.getPWCATELEM());
			param_map.put("representanteLegal", obean.getPWCANOMRE());
			param_map.put("rutRepresentante", obean.getPWCARUTRE());
			param_map.put("cambiosRepresentante", obean.getPWCACAMRE().equals(
					"1") ? "X" : "");
			param_map.put("totalPagar", new Long(Long.parseLong(obean
					.getPWCATOPAG())));
			param_map.put("totalRemuneracionesoGratificaciones", new Long(Long
					.parseLong(obean.getPWCATOREM())));
			param_map.put("trabajadoresInformados", obean.getPWCATOTRA());
			param_map.put("numeroHojasAnexas", obean.getPWCAHOJAN());
			param_map.put("fechaPago", Servicios.toFecha(obean.getPWCAFEPAG()));
			param_map.put("periodo", Servicios.toPeriodo(obean.getPWCACOPRO()));
			param_map.put("desde", obean.getPWCAFEDES().equals("0") ? ""
					: Servicios.toFecha(obean.getPWCAFEDES()));
			param_map.put("hasta", obean.getPWCAFEHAS().equals("0") ? ""
					: Servicios.toFecha(obean.getPWCAFEHAS()));
			param_map.put("pagadora", obean.getPWCAEMPAG().trim());
			param_map.put("normal", normal);
			param_map.put("remuneracionesMes", "X");
			param_map.put("retroactivo", "");
			param_map.put("atrasada", atrasada);
			param_map.put("adelantada", adelantada);
			param_map.put("efectivo", "");
			param_map.put("cheque", "");
			param_map.put("tipoProceso", obean.getPWCATIPRO());
			param_map.put("digem", obean.getPWCADIGEM());
			param_map.put("digre", obean.getPWCADIGRE());
			param_map.put("logoAraucana", rutaJasper + "timbre.jpg");
			param_map.put("barcode", ruta);
			param_map.put("firma", rutaJasper + "firma.gif");
			param_map.put("logo", rutaJasper + "logo.jpg");

			JasperDesign design = JRXmlLoader.load(pathJrxml);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			JasperPrint jPrint = JasperFillManager.fillReport(jReport,
					param_map, con);

			JasperExportManager.exportReportToPdfFile(jPrint, rutaZip + carpeta
					+ "/" + "afbr" + i + ".pdf");

			i++;
		}

		String dir = rutaZip + carpeta;
		Zippeo zip = new Zippeo();
		zip.zipFolder(dir, folder.getAbsolutePath() + ".zip");

		FileInputStream archivo = new FileInputStream(rutaZip
				+ carpeta.concat(".zip"));
		int longitud = archivo.available();
		byte[] datos = new byte[longitud];
		archivo.read(datos);
		archivo.close();

		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setContentType("binary/data");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ carpeta.concat(".zip"));

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();

		con.close();

	}
}
