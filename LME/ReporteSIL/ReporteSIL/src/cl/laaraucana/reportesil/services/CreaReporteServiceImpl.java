package cl.laaraucana.reportesil.services;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;

import net.sf.jasperreports.engine.JREmptyDataSource;
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

import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.ReportDao;
import cl.laaraucana.reportesil.dao.ReportDaoImpl;
import cl.laaraucana.reportesil.dao.vo.CotizacionesPagadasVO;
import cl.laaraucana.reportesil.dao.vo.FormularioCalculoSILVO;
import cl.laaraucana.reportesil.dao.vo.ImpuestoVO;
import cl.laaraucana.reportesil.dao.vo.MontoSubsidioDiarioVO;
import cl.laaraucana.reportesil.dao.vo.PeriodosRentaVO;
import cl.laaraucana.reportesil.dao.vo.RemuneracionesBCSIL;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;
import cl.laaraucana.reportesil.dao.vo.TasaSISVO;
import cl.laaraucana.reportesil.dao.vo.TopeSubsidioDiarioVO;
import cl.laaraucana.reportesil.params.TasasPrevisionales;
import cl.laaraucana.reportesil.utils.Configuraciones;
import cl.laaraucana.reportesil.utils.Utils;
import cl.laaraucana.reportesil.utils.HojasPDF;



public class CreaReporteServiceImpl implements CreaReporteService {

	protected Logger logger = Logger.getLogger(this.getClass());
	private ReportDao dao;

	private static final String TEMPLATEH1A = Configuraciones.getConfig("certificado.reportesil.hoja1A");
	private static final String TEMPLATEH2A = Configuraciones.getConfig("certificado.reportesil.hoja2A");
	private static final String TEMPLATEH3A = Configuraciones.getConfig("certificado.reportesil.hoja3A");
	private static final String TEMPLATEH1NA = Configuraciones.getConfig("certificado.reportesil.hoja1NA");
	private static String PDF = Configuraciones.getConfig("certificado.reportesil.output");

	public String generarReport(HttpServletRequest request, HttpServletResponse response, List<FormularioCalculoSILVO> formularios) throws Exception {
		
		dao= new ReportDaoImpl();
		FormularioCalculoSILVO form1= formularios.get(0);
		PDF= PDF.replaceAll("<rutAfiliado>", form1.getCabeceraLicencia().getRutAfiliado() + "-" + form1.getCabeceraLicencia().getDvAfiliado()).replaceAll("<licencia>", form1.getCabeceraLicencia().getLicencia()).replaceAll("<fechaHasta>", String.valueOf(form1.getCabeceraLicencia().getFechaHastaInt()));
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		for (Iterator iterator = formularios.iterator(); iterator.hasNext();) {
			FormularioCalculoSILVO formulario = (FormularioCalculoSILVO) iterator.next();
			
			if(formulario.getCabeceraLicencia().getEstado().equals("AUTORIZADA")){
				//HOJA 1
				Map<String, Object> param_map = new HashMap<String, Object>();
				
				param_map = HojasPDF.hoja1_Autorizada(formulario);

				JasperDesign design = JRXmlLoader.load(TEMPLATEH1A);
				JasperReport jReport = JasperCompileManager.compileReport(design);
				JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

				jasperPrintList.add(jPrint);
				
				//HOJA 2
				Map<String, Object> param_map2 = new HashMap<String, Object>();

				param_map2 = HojasPDF.hoja2_Autorizada(formulario);

				JasperDesign design2 = JRXmlLoader.load(TEMPLATEH2A);
				JasperReport jReport2 = JasperCompileManager.compileReport(design2);
				JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, dao.getConnection());

				jasperPrintList.add(jPrint2);
				
				//HOJA 3
				Map<String, Object> param_map3 = new HashMap<String, Object>();

				param_map3 = HojasPDF.hoja3_Autorizada(formulario);

				JasperDesign design3 = JRXmlLoader.load(TEMPLATEH3A);
				JasperReport jReport3 = JasperCompileManager.compileReport(design3);
				JasperPrint jPrint3 = JasperFillManager.fillReport(jReport3, param_map3, dao.getConnection());

				jasperPrintList.add(jPrint3);
			}else{
				//Hoja 1 No Autorizada
				Map<String, Object> param_map = new HashMap<String, Object>();
				
				param_map = HojasPDF.hoja1_NoAutorizada(form1.getCabeceraLicencia());

				JasperDesign design = JRXmlLoader.load(TEMPLATEH1NA);
				JasperReport jReport = JasperCompileManager.compileReport(design);
				JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

				jasperPrintList.add(jPrint);
			}
		}
		
		
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
		response.setHeader("Content-Disposition", "attachment;filename=" + PDF.substring(PDF.indexOf("sil_")));

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();
		
		return PDF;
	}
	
	public List<FormularioCalculoSILVO> completarFormulario(ResumenLicenciaVO resumen){
		List<FormularioCalculoSILVO> formularios= new ArrayList<FormularioCalculoSILVO>();
		try {
			//Se completa cuadro B formulario
			
			//Se busca las remuneraciones x periodo para cada folio pago
			ConsultaServicesDAO dao= new ConsultaServicesDAO();
			Map<String, String> param= new HashMap<String, String>();
			param.put("licencia", String.valueOf(resumen.getNuminterno()));
			param.put("rutAfiliado", String.valueOf(resumen.getRutAfiliado()));
			param.put("fechaHasta", Utils.dateToStringAS400(resumen.getFechaHasta()));
			List<HashMap> listaRemu= dao.remuneracionesxPeriodo(param);
			
			//Se genera una lista de pagos folios y sus periodos ya sea existan o no
			//Se genera un Map con todos los pago folios válidos (con peiodos)
			//se determina el menor  y el mayor pago folio 
			int pag_menor=9999999;
			int pag_mayor=0;
			Map<Integer, RemuneracionesBCSIL> foliosPago= new HashMap<Integer, RemuneracionesBCSIL>();
			
			//Se busca CodigoPago para determinar si debe llemarse completo cuadro C o E
			//Se rescata valor IPC para uso en cuadro E
			Map codigoPago_IPC= dao.codigoPago_IPC(param);
			int codigoPago=1;
			double ipc=0.0;
			if(codigoPago_IPC!=null){
				codigoPago= Utils.decimal2int((BigDecimal)codigoPago_IPC.get("LICCODPAG"));
				ipc= ((BigDecimal)codigoPago_IPC.get("LICIPCVAL")).doubleValue();
			}
			
			//Se itera por cada folio pago 
			for (Iterator iterator = listaRemu.iterator(); iterator.hasNext();) {
				HashMap pago = (HashMap) iterator.next();
				ResumenLicenciaVO cabecera= (ResumenLicenciaVO)SerializationUtils.clone(resumen);
				String tipopago= (String)pago.get("TIPOPAGO");
				if(tipopago.equals("Reliquidada")){
					cabecera.setReliquidada("SI");
				}else if(tipopago.equals("Normal")){
					cabecera.setReliquidada("NO");
				}
				cabecera.setFolioPago(((BigDecimal)pago.get("PAGFOL")).toString());
				
				//por cada folio pago da origen a un formulario cálculo SIL
				FormularioCalculoSILVO formulario= new FormularioCalculoSILVO();
				formulario.setCabeceraLicencia(cabecera);
				
				if (Utils.decimal2int(pago.get("PAGFOL"))< pag_menor && pago.get("PERIODO1")!=null) {
					pag_menor= Utils.decimal2int(pago.get("PAGFOL"));
				}
				if (Utils.decimal2int(pago.get("PAGFOL"))> pag_mayor && pago.get("PERIODO1")!=null) {
					pag_mayor= Utils.decimal2int(pago.get("PAGFOL"));
				}
				
				RemuneracionesBCSIL remuBC= new RemuneracionesBCSIL();
				List<PeriodosRentaVO> periodos= new ArrayList<PeriodosRentaVO>();
				List<PeriodosRentaVO> periodosMaternales= new ArrayList<PeriodosRentaVO>();
				if(pago.get("PERIODO1")!=null){
					//PERIODO1
					PeriodosRentaVO periodo= new PeriodosRentaVO();
					periodo.setPeriodo(((BigDecimal)pago.get("PERIODO1")).toString());
					periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA1")));
					periodo.setSubsidio(Utils.decimal2int(pago.get("SIL1")));
					periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE1")));
					periodos.add(periodo);
					//PERIODO2
					periodo= new PeriodosRentaVO();
					periodo.setPeriodo(((BigDecimal)pago.get("PERIODO2")).toString());
					periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA2")));
					periodo.setSubsidio(Utils.decimal2int(pago.get("SIL2")));
					periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE2")));
					periodos.add(periodo);
					//PERIODO3
					periodo= new PeriodosRentaVO();
					periodo.setPeriodo(((BigDecimal)pago.get("PERIODO3")).toString());
					periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA3")));
					periodo.setSubsidio(Utils.decimal2int(pago.get("SIL3")));
					periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE3")));
					periodos.add(periodo);
					if(pago.get("PERIODO4")!=null && Utils.decimal2int(pago.get("PERIODO4"))!=0){
						//PERIODO4
						periodo= new PeriodosRentaVO();
						periodo.setPeriodo(((BigDecimal)pago.get("PERIODO4")).toString());
						periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA4")));
						periodo.setSubsidio(Utils.decimal2int(pago.get("SIL4")));
						periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE4")));
						periodosMaternales.add(periodo);
						//PERIODO5
						periodo= new PeriodosRentaVO();
						periodo.setPeriodo(((BigDecimal)pago.get("PERIODO5")).toString());
						periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA5")));
						periodo.setSubsidio(Utils.decimal2int(pago.get("SIL5")));
						periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE5")));
						periodosMaternales.add(periodo);
						//PERIODO6
						periodo= new PeriodosRentaVO();
						periodo.setPeriodo(((BigDecimal)pago.get("PERIODO6")).toString());
						periodo.setRemuneracionImponible(Utils.decimal2int(pago.get("RENTA6")));
						periodo.setSubsidio(Utils.decimal2int(pago.get("SIL6")));
						periodo.setEntidad(Utils.decimal2int(pago.get("INSPRE6")));
						periodosMaternales.add(periodo);
					}
				}
				remuBC.setPeriodos(periodos);
				remuBC.setPeriodosMaternales(periodosMaternales);
				if(periodos.size()>0){
					foliosPago.put(Utils.decimal2int(pago.get("PAGFOL")), remuBC);
				}
				
				//se guarda en formulario las tablas de periodos (y remuneraciones)
				formulario.setRemuneraciones(remuBC);
				
				//Se fuerza que si LICDIAMON=0 ==> PAGSUBMON=0 y SUBTOTAL=0
				if(Utils.decimal2double(pago.get("LICDIAMON"))==0){
					pago.put("PAGSUBMON", "0");
					pago.put("SUBTOTAL", "0");
				}
				
				//************************Se llena cuadro C y E formulario
				MontoSubsidioDiarioVO montoDiario= new MontoSubsidioDiarioVO();
				TopeSubsidioDiarioVO topeDiario= new TopeSubsidioDiarioVO();
				
				if(codigoPago==1 && Utils.decimal2double(pago.get("LICDIAMON"))> TasasPrevisionales.getInstance().getMontoDiarioMinimo() || resumen.getTipoLicencia()==0){
					//para determinar si finalmente se uso base 1 o 2 (asociada a codigo pago y renta base sil)
					formulario.setBaseCalculo(1); 
					//Se completa cuadro C completo, monto subsidio diario
					montoDiario.setMontoDiario(Utils.decimal2double(pago.get("LICDIAMON")));
					montoDiario.setNumeroDias(Utils.decimal2int(pago.get("PAGRESDIA")));
					montoDiario.setMontoxDias(Utils.decimal2int(pago.get("PAGSUBMON")));
					montoDiario.setSeguroCesantia(Utils.decimal2int(pago.get("SEGLIC")));
					montoDiario.setMontoaPagar(Utils.decimal2int(pago.get("SUBTOTAL")));
					montoDiario.setRemuneracionMesAnterior(Utils.decimal2int(pago.get("RENTAIMPANT")));
					montoDiario.setBaseCotizacionDiaria(Utils.decimal2int(pago.get("BASECOTDIA")));
					montoDiario.setEntidad(Utils.decimal2int(pago.get("LICINSPRE")));
					
					//Se calcula Cotizaciones Pagadas
					CotizacionesPagadasVO cotizaciones= setCotizaciones(montoDiario.getEntidad(), pago);
					montoDiario.setCotizaciones(cotizaciones);
					
					CotizacionesPagadasVO cotizacionestope= new CotizacionesPagadasVO();
					topeDiario.setCotizaciones(cotizacionestope);
					
				} else if(codigoPago==2 || Utils.decimal2double(pago.get("LICDIAMON"))<= TasasPrevisionales.getInstance().getMontoDiarioMinimo()){
					formulario.setBaseCalculo(2);
					//Se llena cuadro E completo
					topeDiario.setMontoDiario(Utils.decimal2double(pago.get("LICDIAMON")));
					topeDiario.setNumeroDias(Utils.decimal2int(pago.get("PAGRESDIA")));
					topeDiario.setMontoxDias(Utils.decimal2int(pago.get("PAGSUBMON")));
					topeDiario.setSeguroCesantia(Utils.decimal2int(pago.get("SEGLIC")));
					topeDiario.setMontoaPagar(Utils.decimal2int(pago.get("SUBTOTAL")));
					topeDiario.setRemuneracionMesAnterior(Utils.decimal2int(pago.get("RENTAIMPANT")));
					topeDiario.setBaseCotizacionDiaria(Utils.decimal2int(pago.get("BASECOTDIA")));
					topeDiario.setEntidad(Utils.decimal2int(pago.get("LICINSPRE")));

					//Se calcula Cotizaciones Pagadas
					CotizacionesPagadasVO cotizaciones= setCotizaciones(topeDiario.getEntidad(), pago);
					topeDiario.setCotizaciones(cotizaciones);

					CotizacionesPagadasVO cotizacionesdiario= new CotizacionesPagadasVO();
					montoDiario.setCotizaciones(cotizacionesdiario);
				}
				
				formulario.setMontoDiario(montoDiario);
				formulario.setTopeDiario(topeDiario);
								
				formularios.add(formulario);
			}
			//Se recorre lista de pagos folios y se asignan periodos en caso de no existir, 
			//si es nromal se asigna la menor, si es reliquidada la mayor
			//Se calcula ponderados y se completa cuadro C y E asociado a los montos ponderados
			for (Iterator iterator = formularios.iterator(); iterator
					.hasNext();) {
				FormularioCalculoSILVO formuSIL = (FormularioCalculoSILVO) iterator
						.next();
				if(formuSIL.getRemuneraciones().getPeriodos().size()==0 && foliosPago.size()>0){
					if(formuSIL.getCabeceraLicencia().getReliquidada().equals("NO")){
						formuSIL.setRemuneraciones((RemuneracionesBCSIL)SerializationUtils.clone(foliosPago.get(pag_menor)));
					}else if(formuSIL.getCabeceraLicencia().getReliquidada().equals("SI")){
						formuSIL.setRemuneraciones((RemuneracionesBCSIL)SerializationUtils.clone(foliosPago.get(pag_mayor)));
					}
				}
				
				for (Iterator iterator2 = formuSIL.getRemuneraciones().getPeriodos().iterator(); iterator2
						.hasNext();) {
					PeriodosRentaVO periodoRentaVO = (PeriodosRentaVO) iterator2
							.next();
					
					//Cálculo Descuentos Previsionales
					periodoRentaVO.setDescuentosPrevisionales(getDescuentosPrevisionales(periodoRentaVO));
					
					//Se calcula remuneración Neta (Remu Imp. - Dctos Prev)
					periodoRentaVO.setRemuneracionNeta(periodoRentaVO.getRemuneracionImponible()-periodoRentaVO.getDescuentosPrevisionales());
					
					//calculo impuesto
					int impuesto= getImpuestos(periodoRentaVO.getPeriodo(), periodoRentaVO.getRemuneracionNeta());
					periodoRentaVO.setImpuesto(impuesto);
					
					//Se calcula Total (Remu Neta + Subsidio - Impuesto)
					periodoRentaVO.setTotal(periodoRentaVO.getRemuneracionNeta()+periodoRentaVO.getSubsidio()-periodoRentaVO.getImpuesto());
				}
				
				//Si es normal y montodiario < base sil se limpia tabla periodo maternales
				if(formuSIL.getCabeceraLicencia().getReliquidada().equals("NO")){
					if(formuSIL.getBaseCalculo()==1){
						if(formuSIL.getMontoDiario().getMontoDiario()<= TasasPrevisionales.getInstance().getMontoDiarioMinimo()){
							formuSIL.getRemuneraciones().setPeriodosMaternales(new ArrayList<PeriodosRentaVO>());
							formuSIL.getTopeDiario().setIpc(0.0);
						}
					}else if(formuSIL.getBaseCalculo()==2){
						if(formuSIL.getTopeDiario().getMontoDiario()<= TasasPrevisionales.getInstance().getMontoDiarioMinimo()){
							formuSIL.getRemuneraciones().setPeriodosMaternales(new ArrayList<PeriodosRentaVO>());
							formuSIL.getTopeDiario().setIpc(0.0);
						}
					}
				}
				for (Iterator iterator3 = formuSIL.getRemuneraciones().getPeriodosMaternales().iterator(); iterator3
						.hasNext();) {
					PeriodosRentaVO periodoRentaVO = (PeriodosRentaVO) iterator3
							.next();
					//Cálculo Descuentos Previsionales
					periodoRentaVO.setDescuentosPrevisionales(getDescuentosPrevisionales(periodoRentaVO));
					
					//Se calcula remuneración Neta (Remu Imp. - Dctos Prev)
					periodoRentaVO.setRemuneracionNeta(periodoRentaVO.getRemuneracionImponible()-periodoRentaVO.getDescuentosPrevisionales());
					
					//calculo impuesto
					int impuesto= getImpuestos(periodoRentaVO.getPeriodo(), periodoRentaVO.getRemuneracionNeta());
					periodoRentaVO.setImpuesto(impuesto);
					
					//Se calcula Total (Remu Neta + Subsidio - Impuesto)
					periodoRentaVO.setTotal(periodoRentaVO.getRemuneracionNeta()+periodoRentaVO.getSubsidio()-periodoRentaVO.getImpuesto());
				}
				
				//Se calcula ponderados
				int montoDiarioPonderado= getMontoSubsidioDiarioPonderado(formuSIL.getRemuneraciones().getPeriodos());
				int topeDiarioPonderado= getMontoSubsidioDiarioPonderado(formuSIL.getRemuneraciones().getPeriodosMaternales());
				
				//Monto actualizado y reajustado cuadroE
				formuSIL.getTopeDiario().setMontoDiarioActualizado((int)Math.round(topeDiarioPonderado + topeDiarioPonderado*ipc/100));
				formuSIL.getTopeDiario().setMontoDiarioReajustado((int)Math.round(formuSIL.getTopeDiario().getMontoDiarioActualizado()*1.1));
				
				//Caudro Monto Tope del subsidio Diario
				formuSIL.getTopeDiario().setMontoTopeSubsidioDiario(topeDiarioPonderado);
				
				if(formuSIL.getBaseCalculo()==2){
					//Monto Diario Cuadro C en base a promedio ponderado
					formuSIL.getMontoDiario().setMontoDiario(montoDiarioPonderado);
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formularios;
	}
	
	public double getTasaPrevision(String periodo, int entidad) {
		
		return TasasPrevisionales.getInstance().getTasas().get(periodo + entidad);
	}
	
	public double getTasaSIS(String periodo){
		int periodoint= Integer.parseInt(periodo);
		List<TasaSISVO> tasasSIS= TasasPrevisionales.getInstance().getTasasSIS();
		double respuesta=0.0;
		for (Iterator iterator = tasasSIS.iterator(); iterator.hasNext();) {
			TasaSISVO tasaSIS = (TasaSISVO) iterator.next();
			if(tasaSIS.getPeriodoInicial()>periodoint && tasaSIS.getPeriodoFinal()>periodoint){
				respuesta= tasaSIS.getTasa();
				break;
			}
		}
		logger.info("tasa Sis= " + respuesta);
		return respuesta;
	}
	
	public int getImpuestos(String periodo, int renta) {
		int resultado=0;
		ImpuestoVO data_impuesto= TasasPrevisionales.getInstance().getImpuestos().get(periodo);
		if(renta< data_impuesto.getTope1()){
			resultado= 0;
		}else if(renta<= data_impuesto.getTope2()){
			resultado= (int)Math.round((renta*(data_impuesto.getTasa1()/100)) - data_impuesto.getRebaja1());
		}else if(renta> data_impuesto.getTope2()){
			resultado= (int)Math.round((renta*(data_impuesto.getTasa2()/100)) - data_impuesto.getRebaja2());
		}
		logger.info("Impuestos= " + resultado);
		return resultado;
	}
	
	public int getMontoSubsidioDiarioPonderado(List<PeriodosRentaVO> periodos){
		try {
			int montoDiario=0;
			int totalRenta=0;
			int numrentas=0;
			for (Iterator iterator = periodos.iterator(); iterator.hasNext();) {
				PeriodosRentaVO periodosRentaVO = (PeriodosRentaVO) iterator
						.next();
				if(periodosRentaVO.getTotal()>0){
					numrentas++;
					totalRenta+=periodosRentaVO.getTotal();
				}
			}
			//Se calcula Monto Diario
			if(numrentas>0){
				montoDiario= Math.round(totalRenta/(numrentas*30));
			}
			logger.info("Monto Diario Ponderado= " + montoDiario);
			return montoDiario;
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public CotizacionesPagadasVO setCotizaciones(int entidad, HashMap pago){
		//Se calcula Cotizaciones Pagadas
		CotizacionesPagadasVO cotizaciones= new CotizacionesPagadasVO();
		int renta_Imponible = (int)Math.round(Utils.decimal2int(pago.get("PAGCOTMON")) * 100 /Utils.decimal2double(pago.get("PAGCOTPOR")));
		if(entidad==2000){
			cotizaciones.setSalud(Utils.decimal2int(pago.get("PAGCOTMON")));
			cotizaciones.setPensiones(0);
		}else{
			//cotizaciones.setSalud((int)Math.round(Utils.decimal2int(pago.get("BASECOTDIA"))* Utils.decimal2int(pago.get("PAGLIQDIA")) * 0.07));
			cotizaciones.setSalud((int)Math.round(renta_Imponible*0.07));
			cotizaciones.setPensiones(Utils.decimal2int(pago.get("PAGCOTMON")) - cotizaciones.getSalud());
		}
		return cotizaciones;
	}
	
	public int getDescuentosPrevisionales(PeriodosRentaVO periodoRentaVO){
		
		//Cálculo Descuentos Previsionales
		double tasaDescuento=7.0;
		try {
			if(periodoRentaVO.getEntidad()!=2000){
				double tasaPrevision= getTasaPrevision(periodoRentaVO.getPeriodo() , periodoRentaVO.getEntidad());
				double tasaSIS=getTasaSIS(periodoRentaVO.getPeriodo());
				//double tasaCesantia= dao.tasaCesantia(param);
				double tasaCesantia=0.0;
				tasaDescuento= tasaPrevision - tasaSIS + tasaCesantia;
			}
		} catch (Exception e) {
			logger.error("No se encontró tasa previsional asociado al periodo");
			tasaDescuento=0.0;
			e.printStackTrace();
		}
		int descuento= (int)Math.round(periodoRentaVO.getRemuneracionImponible()*tasaDescuento/100);
		logger.info("tasa dcto. previsional= " + tasaDescuento);
		return descuento;
	}
}
