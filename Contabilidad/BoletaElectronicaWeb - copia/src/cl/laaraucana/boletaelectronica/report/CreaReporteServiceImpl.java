package cl.laaraucana.boletaelectronica.report;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;
import cl.laaraucana.boletaelectronica.reportDao.ReportDao;
import cl.laaraucana.boletaelectronica.services.BaseServices;
import cl.laaraucana.boletaelectronica.services.ParametrosService;
import cl.laaraucana.boletaelectronica.vo.ParametrosVo;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class CreaReporteServiceImpl implements CreaReporteService {

	@Autowired
	private BaseServices baseService;

	@Autowired
	private ParametrosService parametrosService;

	 

	@Autowired
	private ReportDao dao;

	private static final String TEMPLATE1 = "C:/boletaElectronica/boletaElectronica.jrxml";

	private static final String PDF = "C:/boletaElectronica/boletaElectronica.pdf";

	private static final String BARCODE = "C:/boletaElectronica/";

	public void generarReport(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		Map<String, Object> param_map = new HashMap<String, Object>();

		ParametrosVo param = new ParametrosVo();

		param.setVersion(parametrosService.getParamByCode(1000).getVALOR());
		param.setIndicServicio(parametrosService.getParamByCode(1001).getVALOR());
		param.setRutEmpresa(parametrosService.getParamByCode(1002).getVALOR());
		param.setRazonSocial(parametrosService.getParamByCode(1003).getVALOR());
		param.setGiroNegocio(parametrosService.getParamByCode(1004).getVALOR());
		param.setDireccion(parametrosService.getParamByCode(1005).getVALOR());
		param.setComuna(parametrosService.getParamByCode(1006).getVALOR());
		param.setCiudad(parametrosService.getParamByCode(1007).getVALOR());
		param.setUnidadMedida(parametrosService.getParamByCode(1008).getVALOR());

		BoletaBase boleta = baseService.getBoletaById(Long.parseLong(id));

		List<BoletaBase> datosBase = baseService.findByNumBolAndTipoDoc(boleta.getNUMBOL(), 41);
		List<BoletaBase> datosBaseAfecta = baseService.findByNumBolAndTipoDoc(boleta.getNUMBOL(), 39);

		long montoExento = 0;
		long montoNeto = 0;
		long montoIva = 0;

		if (datosBase.size() > 0) {

			for (BoletaDetalle boletaBase : datosBase.get(0).getBoletaDetalle()) {

				montoExento += boletaBase.getPRECUNIT();
			}
		}

		if (datosBaseAfecta.size() > 0) {

			for (BoletaDetalle boletaBase : datosBaseAfecta.get(0).getBoletaDetalle()) {

				montoNeto += boletaBase.getPRECUNIT();
			}

			for (BoletaDetalle boletaBase : datosBaseAfecta.get(0).getBoletaDetalle()) {

				montoIva += boletaBase.getVALLINDET();
			}
		}

	//	param_map = Utils.hoja1(boleta, param, montoExento, montoNeto, montoIva, BARCODE, id);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, dao.getConnection());

		jasperPrintList.add(jPrint);

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PDF));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();

		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);

		FileInputStream archivo = new FileInputStream(PDF);
		int longitud = archivo.available();
		byte[] datos = new byte[longitud];
		archivo.read(datos);
		archivo.close();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=boletaElectrónica.pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();

	 
	}

}
