package cl.laaraucana.ventafullweb.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.util.ReporteUtil;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.ReporteSimulacionVo;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class ReporteServiceImpl implements ReporteService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public String generarReport(HttpServletResponse response, AfiliadoVo afiliado)
			throws Exception {
		try {
			String pdf = Configuraciones.getConfig("venta.web.report");
			String PDF = pdf + "resultadoSimulacion.pdf";
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			ReporteUtil reporte = new ReporteUtil();
			ReporteSimulacionVo rep = new ReporteSimulacionVo();
			try { rep = reporte.seteaDatos(afiliado); } catch(Exception e) { e.printStackTrace();}
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.put("fechaHora",rep.getFechaHora());
			hash.put("rut", rep.getRut());
			hash.put("sucursal", rep.getSucursal());
			hash.put("tipoAfiliado", rep.getTipoAfiliado());
			hash.put("monto", rep.getMonto());
			hash.put("numCuotas", rep.getNumCuotas());
			hash.put("incluyeSC", rep.getIncluyeSC());
			hash.put("incluyeSD", rep.getIncluyeSD());
			hash.put("seguroCesantiaAnual", rep.getSeguroCesantiaAnual());
			hash.put("seguroCesantiaMensual", rep.getSeguroCesantiaMensual());
			hash.put("seguroDesgravamenAnual", rep.getSeguroDesgravamenAnual());
			hash.put("seguroDesgravamenMensual", rep.getSeguroDesgravamenMensual());
			hash.put("impuesto", rep.getImpuesto());
			hash.put("gastoNotarial", rep.getGastoNotarial());
			hash.put("valorCuota", rep.getValorCuota());
			hash.put("valorCuotaSinSeguro", rep.getValorCuotaSinSeguro());
			hash.put("tasaInteresMensual", rep.getTasaInteresMensual());
			hash.put("tasaInteresAnual", rep.getTasaInteresAnual());
			hash.put("pagoPrimeraCuota", rep.getPagoPrimeraCuota());
			hash.put("costoTotalCredito", rep.getCostoTotalCredito());
			hash.put("cae", rep.getCae());
			hash.put("fecha", rep.getFecha());
			hash.put("unidMonetaria", rep.getUnidMonetaria());
			String ruta = pdf + "report1.jrxml";  
			JasperDesign design = JRXmlLoader.load(ruta);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, new JRBeanCollectionDataSource(rep.getDetalleCuotas()));
			jasperPrintList.add(jPrint);
			String ruta2 = pdf + "report2.jrxml";
			JasperDesign design2 = JRXmlLoader.load(ruta2);
			JasperReport jReport2 = JasperCompileManager.compileReport(design2);
			JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, null, new JREmptyDataSource());
			jasperPrintList.add(jPrint2);
			String ruta3 = pdf + "report3.jrxml";
			JasperDesign design3 = JRXmlLoader.load(ruta3);
			JasperReport jReport3 = JasperCompileManager.compileReport(design3);
			JasperPrint jPrint3 = JasperFillManager.fillReport(jReport3, hash, new JREmptyDataSource());
			jasperPrintList.add(jPrint3);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdf + "resultadoSimulacion.pdf"));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setCreatingBatchModeBookmarks(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			logger.info("Reporte generado");
			FileInputStream archivo = new FileInputStream(PDF);
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			archivo.read(datos);
			archivo.close();
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(datos);
			ouputStream.flush();
			ouputStream.close();
			logger.info("Reporte Descargado");
			return PDF;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
