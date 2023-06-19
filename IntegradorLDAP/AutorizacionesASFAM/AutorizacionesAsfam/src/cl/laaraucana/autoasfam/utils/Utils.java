package cl.laaraucana.autoasfam.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.autoasfam.dto.TotalesAUTDto;
import cl.laaraucana.autoasfam.vo.ModificacionesVO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class Utils {
	
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
			strFileName = carpeta + "\\BarCode_" + strCode + ".PNG";
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

}
