package cl.araucana.wslme.integration.dao.impl;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.integration.dao.FileAbstractDao;
import cl.araucana.wslme.integration.dao.ReporteDao;

public class ReporteDaoImpl extends FileAbstractDao implements ReporteDao {
	private Logger log = Logger.getLogger(ReporteDaoImpl.class);
	
	private String nameFile;
	private String localPath;
	
	public ReporteDaoImpl(String nameFile, boolean replaceIfExist) throws WSLMEException {
		this.nameFile = nameFile + ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.extension");
		this.localPath = System.getProperty("user.dir") + ConfigUtil.getValorRecursosDeAplicacion("araucana.wslme.reportes.diretemporal");
		createFile(this.localPath, this.nameFile, replaceIfExist);
	}

	public File guardarReporte(List contenido) throws WSLMEException{
		try{
			PrintWriter writer = getPrintWriter();
			
			for(Iterator it = contenido.iterator(); it.hasNext();){
				String line = (String)it.next();
				writer.println(line);
			}
			
			writer.close();
		}catch(Exception e){
			log.error("Codigo 0021: Ocurrio un problema cuando se guardaba el reporte en el path [" + getFile().getAbsolutePath() + "]");
			throw new WSLMEException("0021","Error, Ocurrio un problema cuando se guardaba el reporte en el path [" + getFile().getAbsolutePath() + "]");
		}
		
		log.info("Se guardo el reporte en el path [" + getFile().getAbsoluteFile() + "] correctamente");
		return getFile();
	}
}
