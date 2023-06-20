package cl.laaraucana.reportesil.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.laaraucana.reportesil.dao.vo.CotizacionesPagadasVO;
import cl.laaraucana.reportesil.dao.vo.FormularioCalculoSILVO;
import cl.laaraucana.reportesil.dao.vo.MontoSubsidioDiarioVO;
import cl.laaraucana.reportesil.dao.vo.PeriodosRentaVO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;
import cl.laaraucana.reportesil.dao.vo.TopeSubsidioDiarioVO;

public class HojasPDF {

	public static Map<String, Object> hoja1_NoAutorizada(ResumenLicenciaVO resumen) {

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("nombreTrabajador", resumen.getRutAfiliado() + "-" + resumen.getDvAfiliado() + "  "  + resumen.getNombre());
		param_map.put("trabajadorDependiente", "SI");
		param_map.put("numeroLicencia", resumen.getLicencia());
		String reliquidado= resumen.getReliquidada().equals("SI")?"X":"";
		param_map.put("reliquidado", reliquidado);
		String original= resumen.getReliquidada().equals("NO")?"X":"";
		param_map.put("original", original);
		String primLiSI= resumen.getPrimeraLicencia().equals("SI")?"X":"";
		param_map.put("primLiSI", primLiSI);
		String primLiNO= resumen.getPrimeraLicencia().equals("NO")?"X":"";
		param_map.put("primLiNO", primLiNO);
		//param_map.put("diagnostico", resumen.getDiagnostico());
		param_map.put("diagnostico", "");
		param_map.put("fechaInicioLicencia", resumen.getFechaDesdeStr());
		param_map.put("numDiasLicencia", String.valueOf(resumen.getDias()));
		param_map.put("observacion", resumen.getObservacion());
		param_map.put("estadoLicencia", resumen.getEstado());
		param_map.put("fechaobs", Utils.dateToStringFull(new Date()));

		 

		return param_map;
	}
	
	public static Map<String, Object> hoja1_Autorizada(FormularioCalculoSILVO formulario ) {
		
		ResumenLicenciaVO resumen= formulario.getCabeceraLicencia();
		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("nombreTrabajador", resumen.getRutAfiliado() + "-" + resumen.getDvAfiliado() + "  "  + resumen.getNombre());
		param_map.put("trabajadorDependiente", "SI");
		param_map.put("numeroLicencia", resumen.getLicencia());
		String reliquidado= resumen.getReliquidada().equals("SI")?"X":"";
		param_map.put("reliquidado", reliquidado);
		String original= resumen.getReliquidada().equals("NO")?"X":"";
		param_map.put("original", original);
		String primLiSI= resumen.getPrimeraLicencia().equals("SI")?"X":"";
		param_map.put("primLiSI", primLiSI);
		String primLiNO= resumen.getPrimeraLicencia().equals("NO")?"X":"";
		param_map.put("primLiNO", primLiNO);
		param_map.put("diagnostico", "");
		param_map.put("fechaInicioLicencia", resumen.getFechaDesdeStr());
		param_map.put("numDiasLicencia", String.valueOf(resumen.getDias()));
		param_map.put("estadoLicencia", resumen.getEstado() + " (Folio Pago: " + resumen.getFolioPago() + ")");
		
		List<PeriodosRentaVO> tablaPeriodos= formulario.getRemuneraciones().getPeriodos();
		int fila=1;
		//tabla B. REMUNERACIONES BASE CÁLCULO DEL SUBSIDIO
		for (int i = 1; i <= 3; i++) {
			param_map.put("mes" + i, "");
			param_map.put("m" + i + "1", "");
			param_map.put("m" + i + "2", "");
			param_map.put("m" + i + "3", "");
			param_map.put("m" + i + "4", "");
			param_map.put("m" + i + "5", "");
			param_map.put("m" + i + "6", "");
		}
		
		for (Iterator iterator = tablaPeriodos.iterator(); iterator.hasNext();) {
			PeriodosRentaVO periodosRentaVO = (PeriodosRentaVO) iterator.next();
			
			param_map.put("mes" + fila, periodosRentaVO.getPeriodo());
			param_map.put("m" + fila + "1", Utils.formateaMiles(periodosRentaVO.getRemuneracionImponible()));
			param_map.put("m" + fila + "2", Utils.formateaMiles(periodosRentaVO.getDescuentosPrevisionales()));
			param_map.put("m" + fila + "3", Utils.formateaMiles(periodosRentaVO.getImpuesto()));
			param_map.put("m" + fila + "4", Utils.formateaMiles(periodosRentaVO.getRemuneracionNeta()));
			param_map.put("m" + fila + "5", Utils.formateaMiles(periodosRentaVO.getSubsidio()));
			param_map.put("m" + fila + "6", Utils.formateaMiles(periodosRentaVO.getTotal()));
			fila++;
		}
		
		//tabla C. DETERMINACIÓN DEL MONTO DEL SUBSIDIO DIARIO Y MONTO A PAGAR
		MontoSubsidioDiarioVO montoDiario= formulario.getMontoDiario();
		param_map.put("oo1", "");
		param_map.put("oo2", "");
		param_map.put("oo3", "");
		param_map.put("oo4", "");
		param_map.put("oo5", "");
		param_map.put("oo6", "");
		param_map.put("oo7", "");
		
		param_map.put("mm1", Utils.formateaMiles((int)Math.round(montoDiario.getMontoDiario())));
		param_map.put("mm2", Utils.formateaMiles(montoDiario.getNumeroDias()));
		param_map.put("mm3", Utils.formateaMiles(montoDiario.getMontoxDias()));
		param_map.put("mm4", Utils.formateaMiles(montoDiario.getSeguroCesantia()));
		param_map.put("mm5", Utils.formateaMiles(montoDiario.getMontoaPagar()));
		param_map.put("mm6", Utils.formateaMiles(montoDiario.getRemuneracionMesAnterior()));
		param_map.put("mm7", Utils.formateaMiles(montoDiario.getBaseCotizacionDiaria()));

		return param_map;
	}

	public static Map<String, Object> hoja2_Autorizada(FormularioCalculoSILVO formulario) {

		Map<String, Object> param_map = new HashMap<String, Object>();
		
		//tabla Cotizaciones pagadas por período de subsidio
		CotizacionesPagadasVO cotizaciones= formulario.getMontoDiario().getCotizaciones();
		param_map.put("o1", "");
		param_map.put("o2", "");
		param_map.put("o3", "");
		
		param_map.put("m1", Utils.formateaMiles(cotizaciones.getSalud()));
		param_map.put("m2", Utils.formateaMiles(cotizaciones.getPensiones()));
		param_map.put("m3", Utils.formateaMiles(cotizaciones.getDesahucioIndeminizaciones()));
		
		int fila=1;
		// tabla E. CALCULO DE TOPE AL SUBSIDIO DIARIO
		for (int i = 1; i <= 3; i++) {
			param_map.put("mes" + i, "");
			param_map.put("m" + i + "1", "");
			param_map.put("m" + i + "2", "");
			param_map.put("m" + i + "3", "");
			param_map.put("m" + i + "4", "");
			param_map.put("m" + i + "5", "");
			param_map.put("m" + i + "6", "");
		}
		List<PeriodosRentaVO> tablaPeriodosMaternales= formulario.getRemuneraciones().getPeriodosMaternales();
		
		for (Iterator iterator = tablaPeriodosMaternales.iterator(); iterator.hasNext();) {
			PeriodosRentaVO periodosRentaVO = (PeriodosRentaVO) iterator.next();
			
			param_map.put("mes" + fila, periodosRentaVO.getPeriodo());
			param_map.put("m" + fila + "1", Utils.formateaMiles(periodosRentaVO.getRemuneracionImponible()));
			param_map.put("m" + fila + "2", Utils.formateaMiles(periodosRentaVO.getDescuentosPrevisionales()));
			param_map.put("m" + fila + "3", Utils.formateaMiles(periodosRentaVO.getImpuesto()));
			param_map.put("m" + fila + "4", Utils.formateaMiles(periodosRentaVO.getRemuneracionNeta()));
			param_map.put("m" + fila + "5", Utils.formateaMiles(periodosRentaVO.getSubsidio()));
			param_map.put("m" + fila + "6", Utils.formateaMiles(periodosRentaVO.getTotal()));
			fila++;
		}
		
		TopeSubsidioDiarioVO topeDiario= formulario.getTopeDiario();
		param_map.put("oMontoTope", "");
		param_map.put("mMontoTope", Utils.formateaMiles(topeDiario.getMontoTopeSubsidioDiario()));
		
		param_map.put("oIpc", "");
		param_map.put("mIpc", String.valueOf(topeDiario.getIpc()).replaceAll("\\.", ","));
		
		ResumenLicenciaVO resumen= formulario.getCabeceraLicencia();
		param_map.put("rutAfiliado", resumen.getRutAfiliado());
		param_map.put("licencia", resumen.getNuminterno());
		 
		return param_map;

	}

	public static Map<String, Object> hoja3_Autorizada(FormularioCalculoSILVO formulario) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		TopeSubsidioDiarioVO topeDiario= formulario.getTopeDiario();
		param_map.put("o1", "");
		param_map.put("o2", "");
		param_map.put("o3", "");
		param_map.put("o4", "");
		param_map.put("o5", "");
		param_map.put("o6", "");
		param_map.put("o7", "");
		param_map.put("o8", "");
		param_map.put("o9", "");
		param_map.put("m1", Utils.formateaMiles(topeDiario.getMontoDiarioActualizado()));
		param_map.put("m2", Utils.formateaMiles(topeDiario.getMontoDiarioReajustado()));
		param_map.put("m3", Utils.formateaMiles((int)Math.round(topeDiario.getMontoDiario())));
		param_map.put("m4", Utils.formateaMiles(topeDiario.getNumeroDias()));
		param_map.put("m5", Utils.formateaMiles(topeDiario.getMontoxDias()));
		param_map.put("m6", Utils.formateaMiles(topeDiario.getSeguroCesantia()));
		param_map.put("m7", Utils.formateaMiles(topeDiario.getMontoaPagar()));
		param_map.put("m8", Utils.formateaMiles(topeDiario.getRemuneracionMesAnterior()));
		param_map.put("m9", Utils.formateaMiles(topeDiario.getBaseCotizacionDiaria()));
		
		//param_map.put("mes1", "201905");
		//param_map.put("mes2", "201906");
		//param_map.put("mes3", "201907");
		
		//tabla Cotizaciones pagadas por período de subsidio
		CotizacionesPagadasVO cotizaciones= formulario.getTopeDiario().getCotizaciones();
		
		param_map.put("osalud", "");
		param_map.put("msalud", Utils.formateaMiles(cotizaciones.getSalud()));
		param_map.put("opensiones", "");
		param_map.put("mpensiones", Utils.formateaMiles(cotizaciones.getPensiones()));
		
		ResumenLicenciaVO resumen= formulario.getCabeceraLicencia();
		param_map.put("rutAfiliado", resumen.getRutAfiliado());
		param_map.put("licencia", resumen.getNuminterno());
		
		param_map.put("observacion", resumen.getObservacion());
		param_map.put("fechaobs", Utils.dateToStringFull(new Date()));
		
		param_map.put("pagfolexception", topeDiario.getPagfol_exception());
		
		return param_map;
	}
	/*
	public static Map<String, Object> hoja2() {

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map.put("m11", "23.000");
		param_map.put("m12", "45.000");
		param_map.put("m13", "24.000");
		param_map.put("m14", "34.000");
		param_map.put("m15", "56.000");
		param_map.put("m16", "13.000");
		param_map.put("m21", "23.000");
		param_map.put("m22", "24.000");
		param_map.put("m23", "14.000");
		param_map.put("m24", "23.500");
		param_map.put("m25", "24.000");
		param_map.put("m26", "10.000");
		param_map.put("m31", "24.000");
		param_map.put("m32", "25.000");
		param_map.put("m33", "14.000");
		param_map.put("m34", "23.000");
		param_map.put("m35", "14.000");
		param_map.put("m36", "34.000");

		param_map.put("oo1", "blablalblalba");
		param_map.put("oo2", "blablalblalba");
		param_map.put("oo3", "blablalblalba");
		param_map.put("oo4", "blablalblalba");
		param_map.put("oo5", "blablalblalba");
		param_map.put("oo6", "blablalblalba");
		param_map.put("oo7", "blablalblalba");
		
		param_map.put("mm1", "25.000");
		param_map.put("mm2", "14.500");
		param_map.put("mm3", "30.000");
		param_map.put("mm4", "45.000");
		param_map.put("mm5", "15.000");
		param_map.put("mm6", "52.000");
		param_map.put("mm7", "23.000");

		param_map.put("o1", "blablalblalba");
		param_map.put("o2", "blablalblalba");
		param_map.put("o3", "blablalblalba");
		
		param_map.put("m1", "23.000");
		param_map.put("m2", "45.000");
		param_map.put("m3", "34.000");

	

		param_map.put("mes1", "201905");
		param_map.put("mes2", "201906");
		param_map.put("mes3", "201907");
		
		param_map.put("rutAfiliado", "13819548");
		param_map.put("licencia", "9335896");
		 


		return param_map;

	}

	public static Map<String, Object> hoja3() {

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map.put("m11", "23.000");
		param_map.put("m12", "45.000");
		param_map.put("m13", "24.000");
		param_map.put("m14", "34.000");
		param_map.put("m15", "56.000");
		param_map.put("m16", "13.000");
		param_map.put("m21", "23.000");
		param_map.put("m22", "24.000");
		param_map.put("m23", "14.000");
		param_map.put("m24", "23.500");
		param_map.put("m25", "24.000");
		param_map.put("m26", "10.000");
		param_map.put("m31", "24.000");
		param_map.put("m32", "25.000");
		param_map.put("m33", "14.000");
		param_map.put("m34", "23.000");
		param_map.put("m35", "14.000");
		param_map.put("m36", "34.000");

		param_map.put("o1", "blablalblalba");
		param_map.put("o2", "blablalblalba");
		param_map.put("o3", "blablalblalba");
		param_map.put("o4", "blablalblalba");
		param_map.put("o5", "blablalblalba");
		param_map.put("o6", "blablalblalba");
		param_map.put("o7", "blablalblalba");
		param_map.put("o8", "blablalblalba");
		param_map.put("o9", "blablalblalba");
		param_map.put("m1", "23.000");
		param_map.put("m2", "45.000");
		param_map.put("m3", "34.000");
		param_map.put("m4", "23.000");
		param_map.put("m5", "25.000");
		param_map.put("m6", "11.000");
		param_map.put("m7", "23.000");
		param_map.put("m8", "23.000");
		param_map.put("m9", "23.000");
		
		param_map.put("mes1", "201905");
		param_map.put("mes2", "201906");
		param_map.put("mes3", "201907");
		
		param_map.put("oMontoTope", "blablalblalba");
		param_map.put("mMontoTope", "23.000");
		param_map.put("oIpc", "");
		param_map.put("mIpc", "34.000");
		
		param_map.put("osalud", "blablalblalba");
		param_map.put("msalud", "23.000");
		param_map.put("opensiones", "");
		param_map.put("mpensiones", "34.000");
		
		param_map.put("rutAfiliado", "13819548");
		param_map.put("licencia", "9335896");

		return param_map;
	}*/
}
