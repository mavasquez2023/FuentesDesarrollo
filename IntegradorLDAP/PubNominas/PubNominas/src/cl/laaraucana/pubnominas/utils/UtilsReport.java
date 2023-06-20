package cl.laaraucana.pubnominas.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


import cl.laaraucana.pubnominas.dto.asfam.TotalesAUTDto;
import cl.laaraucana.pubnominas.dto.cotizacion.NormalDto;
import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.vo.ModificacionesVO;


import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class UtilsReport {
	
	public static Map<String, Object> parametrosAUT(TotalesAUTDto dato) {

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("mes", dato.getMesProceso());
		param_map.put("ano", dato.getAnoProceso());
		param_map.put("oficina", dato.getOficina());
		param_map.put("sucursal", dato.getSucursal());
		param_map.put("anexo", dato.getAnexo());
		param_map.put("rutAfiliado", dato.getRutAfiliado()+"-" + dato.getDvAfiliado());
		param_map.put("nombreAfiliado", dato.getNombreAfiliado());
		param_map.put("nombreEmpleador", dato.getRazonSocial());
		param_map.put("rutEmpleador", dato.getRutEmpresa()+"-" + dato.getDvEmpresa());
		param_map.put("sumatorioRetroactivo", dato.getMontoRetroactivo());
		param_map.put("montoDelMes", dato.getMontoMes());
		param_map.put("montoTotalAPagar", dato.getMontoTotal());
		param_map.put("cantidadAsignacionesPorTramo", dato.getCantidadCargas());
		param_map.put("montoAsignacionesPorTramo", dato.getValorTramo());
		param_map.put("imgPath", Configuraciones.getConfig("carpeta.jasper") + "img//");
		return param_map;

	}
	
	public static Map<String, Object> parametrosMOD(ModificacionesVO dato) {

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("mesActual", dato.getCabecera().getMesActual());
		param_map.put("anoActual", dato.getCabecera().getAnoActual());
		param_map.put("oficina", dato.getCabecera().getOficina());
		param_map.put("sucursal", dato.getCabecera().getSucursal());
		param_map.put("rutEmpresa", dato.getCabecera().getRutEmpresa()+ "-" + dato.getCabecera().getDvEmpresa());
		param_map.put("nombreEmpresa", dato.getCabecera().getRazonSocial());
		param_map.put("cantidadRegistrosAutori", String.valueOf(dato.getCantAutorizaciones()));
		param_map.put("pagDesdeAutori", String.valueOf(dato.getDesdeAutorizaciones()));
		param_map.put("pagHastaAutori", String.valueOf(dato.getHastaAutorizaciones()));
		param_map.put("cantidadRegistroCarga", String.valueOf(dato.getCantSuspensiones()));
		param_map.put("pagDesdeCarga", String.valueOf(dato.getDesdeSuspensiones()));
		param_map.put("pagHastaCarga", String.valueOf(dato.getHastaSuspensiones()));
		param_map.put("cantidadRegistrosPendientes", String.valueOf(dato.getCantPendientes()));
		param_map.put("paginaDesdePen", String.valueOf(dato.getDesdePendientes()));
		param_map.put("paginaHastaPen", String.valueOf(dato.getHastaPendientes()));
		param_map.put("imgPath", Configuraciones.getConfig("carpeta.jasper") + "img//");
		return param_map;

	}
	
	public static Map<String, Object> parametrosPex(CuotaPEXDto dato) throws ParseException {

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("rutEmpresa", dato.getRutEmpresa()+"-"+dato.getDvRutEmpresa());
		param_map.put("nombreEmpresa", dato.getRazonSocial());
		param_map.put("sucursal", dato.getSucursal());
		param_map.put("oficina", dato.getNombreOficina());
		param_map.put("fechaEmision", Utils.fechaWebSlash());
		param_map.put("fechaCreacion", Utils.pasaFechaInforme(dato.getFechaCreacion()));
   
		param_map.put("imgPath", Configuraciones.getConfig("carpeta.jasper") + "img//");
		return param_map;

	}
	
	public static Map<String, Object> parametrosCOT(NormalDto dato) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		// imagen 128
		String ruta = UtilsReport.generar128(dato.getFolio());

		param_map.put("imagen", ruta);
		param_map.put("imgPath", Configuraciones.getConfig("carpeta.jasper") + "img//");
		param_map.put("oficina", dato.getOficina());
		param_map.put("rutEmpleador", dato.getRutEmpleador());
		param_map.put("sucursal", dato.getSucursal());
		param_map.put("mes", dato.getMes());
		param_map.put("year", dato.getYear());
		param_map.put("codBarra", dato.getFolio());
		param_map.put("razonSocial", dato.getRazonSocial());
		param_map.put("codActividadEconomica", dato.getCodActividadEconomica());
		param_map.put("representanteLegal", dato.getRepresentanteLegal());
		param_map.put("rutRepresentante", dato.getRutRepresentante());
		param_map.put("si", dato.getAfiliadoSi());
		param_map.put("no", dato.getAfiliadoNo());
		param_map.put("correo", dato.getCorreo());
		param_map.put("tipoDeclaracion", dato.getTipoDeclaracion());
		/* Asignaciones familiares */
		param_map.put("131", dato.getF131());
		param_map.put("132", dato.getF132());
		param_map.put("133", dato.getF133());
		param_map.put("134", dato.getF134());
		param_map.put("135", dato.getF135());
		param_map.put("136", dato.getF136());
		param_map.put("141", dato.getF141());
		param_map.put("142", dato.getF142());
		param_map.put("143", dato.getF143());
		param_map.put("144", dato.getF144());
		param_map.put("145", dato.getF145());
		param_map.put("146", dato.getF146());
		param_map.put("151", dato.getF151());
		param_map.put("152", dato.getF152());
		param_map.put("153", dato.getF153());
		param_map.put("154", dato.getF154());
		param_map.put("155", dato.getF155());
		param_map.put("156", dato.getF156());
		param_map.put("161", dato.getF161());
		param_map.put("162", dato.getF162());
		param_map.put("163", dato.getF163());
		param_map.put("164", dato.getF164());
		param_map.put("165", dato.getF165());
		param_map.put("166", dato.getF166());
		param_map.put("reajustes", dato.getReajustes());
		param_map.put("intereses", dato.getIntereses());
		param_map.put("multas", dato.getMultas());
		param_map.put("totalGravamenes", dato.getTotalGravamenes());
		param_map.put("compensada", dato.getCompensada());
		param_map.put("empleador", dato.getEmpleador());
		param_map.put("ccaf", dato.getCcaf());
		/* Retroactivos */
		param_map.put("171", dato.getF171());
		param_map.put("172", dato.getF172());
		param_map.put("173", dato.getF173());
		param_map.put("174", dato.getF174());
		param_map.put("175", dato.getF175());
		param_map.put("tasaCotizacion", dato.getTasaCotizacion());
		param_map.put("montoCotizaciones", dato.getMontoCotizaciones());
		/* cotizaciones */
		param_map.put("101", dato.getF101());
		param_map.put("102", dato.getF102());
		param_map.put("103", dato.getF103());
		param_map.put("111", dato.getF111());
		param_map.put("112", dato.getF112());
		param_map.put("113", dato.getF113());
		param_map.put("121", dato.getF121());
		param_map.put("122", dato.getF122());
		param_map.put("123", dato.getF123());
		param_map.put("direccion", dato.getDireccion());
		param_map.put("oficinaRazon", dato.getOficinaRazon());

		return param_map;

	}

	public static String generar128(String strCode) {

		String carpeta = Configuraciones.getConfig("carpeta.jasper");

		// Guardar Codigo de barras como imagen
		Barcode barcode = null;
		String strFileName = "";
		try {
			barcode = BarcodeFactory.createCode128(strCode);// Reemplazar esto por el valor que deseen
		} catch (BarcodeException e) {

			e.printStackTrace();
		}
		barcode.setDrawingText(true);// determina si se agrega o no el número codificado debajo del código de barras
		// tamaño de la barra
		barcode.setBarWidth(2);
		barcode.setBarHeight(80);

		try {
			// Ruta y nombre del archivo PNG a crear
			strFileName = carpeta + "BarCode//BarCode_" + strCode + ".PNG";
			File file = new File(strFileName);
			FileOutputStream fos = new FileOutputStream(file);
			BarcodeImageHandler.writePNG(barcode, fos);// formato de ejemplo PNG
			System.out.println("Archivo creado: " + strFileName);
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return strFileName;
	}

	public static String getCeros(String value) {

		String ceros = "000000000";

		String ret = ceros + value;

		return ret.substring(ret.length() - 3, ret.length());
	}
	
	static public String formatear(String rut) {
		int cont = 0;
		String format;
		if (rut.length() == 0) {
			return "";
		} else {
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			format = "-" + rut.substring(rut.length() - 1);
			for (int i = rut.length() - 2; i >= 0; i--) {
				format = rut.substring(i, i + 1) + format;
				cont++;
				if (cont == 3 && i != 0) {
					format = "." + format;
					cont = 0;
				}
			}
			return format;
		}
	}

}
