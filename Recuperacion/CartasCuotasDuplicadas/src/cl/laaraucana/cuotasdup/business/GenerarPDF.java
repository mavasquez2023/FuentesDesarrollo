/**
 * 
 */
package cl.laaraucana.cuotasdup.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.utils.ParamConfig;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class GenerarPDF {
	protected static Logger logger = Logger.getLogger(GenerarPDF.class);
	public static boolean  generaPDF(CuotaVO cuota, String filePath){
		try {
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.put("rutEmpresa", cuota.getRutEmpresa()+"-"+cuota.getDvRutEmpresa());
			hash.put("nombreEmpresa", cuota.getRazonSocial());
			hash.put("sucursal", cuota.getSucursal());
			hash.put("oficina", cuota.getNombreOficina());
			hash.put("fechaEmision", Utils.getFechaCompleta());
			hash.put("fechaCreacion", Utils.pasaFechaASaWEB(cuota.getFechaCreacion()));
   
			hash.put("titulo", ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.titulo"));
			hash.put("imgPath", ParamConfig.RES_CONFIG.getString("certificados.imgPath"));
			hash.put("firma", ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.firma"));
			
			String ruta= ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.jasper");
			List<CuotaVO> trabajadores= new ArrayList<CuotaVO>();
			trabajadores.add(new CuotaVO());
			trabajadores.add(cuota);
			logger.debug("Set correcto datos reporte.");
			ReporteUtil ru = new ReporteUtil(trabajadores, hash, ruta);
			byte[] bites = ru.exportCompilePdf();
			logger.info("Reporte Creado Exitosamente.");
			
			File newFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(bites);
			fos.flush();
			fos.close();
			logger.info("PDF Creado Exitosamente.");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
}
