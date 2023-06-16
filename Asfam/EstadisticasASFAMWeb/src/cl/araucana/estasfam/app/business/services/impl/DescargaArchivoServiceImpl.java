package cl.araucana.estasfam.app.business.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.services.DescargaArchivoService;
import cl.araucana.estasfam.common.util.FechaUtil;

@Service
public class DescargaArchivoServiceImpl implements DescargaArchivoService {

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	@Override
	public void descargarArchivo(HttpServletResponse response, String codArchivo) {
		Date fecHoy = new Date();
		String descMes =FechaUtil.getDescripcionMes(fecHoy);
		
		String soloNomXls = EstadisticasEnum.getEstadisticasEnum(codArchivo).getSoloNombreXls();
		String soloExt = EstadisticasEnum.getEstadisticasEnum(codArchivo).getSoloExtencion();
		try{
			File fileToDownload = new File(cnfPathXlsEstadisticas + soloNomXls + descMes + soloExt);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        OutputStream out = null;
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+soloNomXls + descMes + soloExt);
	        
		    out = response.getOutputStream();
		    int bytes = 0;
			while( (bytes = inputStream.read())!= -1){
				out.write(bytes);
			}
		    response.flushBuffer();
		} catch (IOException ex) {
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}

}
