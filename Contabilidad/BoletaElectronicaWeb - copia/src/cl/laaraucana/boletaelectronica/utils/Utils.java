package cl.laaraucana.boletaelectronica.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import com.ibm.jvm.util.ByteArrayOutputStream;
import com.itextpdf.text.pdf.BarcodePDF417;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;
import cl.laaraucana.boletaelectronica.vo.ParametrosVo;

public class Utils {

	/*
	 * public static Map<String, Object> hoja1(BoletaBase boleta, ParametrosVo
	 * param, long montoExcento, long montoNeto, long montoIva, String barcodePath,
	 * String id) throws Exception {
	 * 
	 * SimpleDateFormat fecha = new SimpleDateFormat("dd-MMM-yyyy", new
	 * Locale("Cl")); SimpleDateFormat fechaImage = new
	 * SimpleDateFormat("ddMMyyyyHHmmss", new Locale("Cl")); DecimalFormat df = new
	 * DecimalFormat("###,###");
	 * 
	 * Map<String, Object> param_map = new HashMap<String, Object>();
	 * 
	 * String imageName = "barcode" + fechaImage.format(new Date()) + "_" +
	 * boleta.getNUMBOL() + ".png";
	 * 
	 * BarcodePDF417 barcode = new BarcodePDF417();
	 * barcode.setText(Configuraciones.getConfig("barcode.key")); java.awt.Image img
	 * = barcode.createAwtImage(Color.BLACK, Color.WHITE); BufferedImage outImage =
	 * new BufferedImage(img.getWidth(null), img.getHeight(null),
	 * BufferedImage.TYPE_INT_RGB); outImage.getGraphics().drawImage(img, 0, 0,
	 * null); ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
	 * ImageIO.write(outImage, "png", bytesOut); bytesOut.flush(); byte[]
	 * pngImageData = bytesOut.toByteArray(); FileOutputStream fos = new
	 * FileOutputStream(barcodePath + imageName); fos.write(pngImageData);
	 * fos.flush(); fos.close();
	 * 
	 * param_map.put("emitido", fecha.format(new Date())); param_map.put("cliente",
	 * boleta.getNOMREC()); param_map.put("rut", boleta.getRUTREC());
	 * param_map.put("vence", fecha.format(new Date())); param_map.put("pago",
	 * fecha.format(new Date())); param_map.put("giro", ""); param_map.put("idBase",
	 * id); param_map.put("barra417", barcodePath + imageName);
	 * param_map.put("neto", df.format(montoNeto)); param_map.put("montoExcento",
	 * df.format(montoExcento)); param_map.put("iva", df.format(montoIva));
	 * param_map.put("total", df.format(boleta.getMONTOTAL()));
	 * param_map.put("direccion", ""); param_map.put("comuna", "");
	 * param_map.put("ciudad", ""); param_map.put("RazonSocialEmisor",
	 * param.getRazonSocial()); param_map.put("giroNegocioEmisor",
	 * param.getGiroNegocio()); param_map.put("direccionEmisor",
	 * param.getDireccion()); param_map.put("ciudadEmisor", param.getCiudad());
	 * param_map.put("rutEmisor", param.getRutEmpresa());
	 * param_map.put("tipoBoleta", boleta.getTIPDOC() == 39 ? "BOLETA ELECTRÓNICA" :
	 * "BOLETA EXENTA ELECTRÓNICA"); param_map.put("numeroBoleta",
	 * String.valueOf(boleta.getNUMBOL()));
	 * 
	 * return param_map; }
	 */
	public static List<OrigenBoletaVo> processList(List<OrigenBoletaVo> list) {

		Collections.sort(list, new Comparator<OrigenBoletaVo>() {
			@Override
			public int compare(OrigenBoletaVo origen1, OrigenBoletaVo origen2) {

				return String.valueOf(origen1.getFOLIO()).compareTo(String.valueOf(origen2.getFOLIO()));
			}
		});

		int i = 0;
		int j = 0;
		while (j < list.size() - 1) {

			String folio = list.get(i).getFOLIO();

			if (list.get(i + 1).getFOLIO().contains(folio)) {

				list.remove(list.get(i + 1));

			} else {

				i++;
			}

			j++;
		}
		 

		return list;

	}

}
