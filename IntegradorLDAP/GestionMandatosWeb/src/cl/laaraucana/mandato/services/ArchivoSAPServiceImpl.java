/**
 * 
 */
package cl.laaraucana.mandato.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.mandato.ibatis.dao.MandatoAS400Dao;
import cl.laaraucana.mandato.ibatis.dao.MandatoAS400DaoImpl;
import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandato.util.Configuraciones;
import cl.laaraucana.mandato.util.Utils;

/**
 * @author J-Factory
 *
 */

@Service
public class ArchivoSAPServiceImpl implements ArchivoSAPService {
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.mandato.services.ArchivoSAPService#generaArchivo()
	 */
	private static final Logger logger = Logger.getLogger(ArchivoSAPService.class);
	
	MandatoAS400Dao dao = new MandatoAS400DaoImpl();
	
	@Override
	public String generaArchivoVigentes() throws Exception {
		List<MandatoAS400Vo> listaMandatos=  dao.consultaMandatosDiaVigentes();
		String path= Configuraciones.getConfig("mandato.sap.ruta.archivo");
		String pathfile=path + "Mandatos_transferencia_" + Utils.getAAAAMMDD()  + ".txt";
		logger.info("Generando archivo sap vigentes: " + pathfile);
		
		if(listaMandatos!=null && listaMandatos.size()>0){
			OutputStream out = new FileOutputStream(pathfile);
			PrintStream flujo= new PrintStream(out);
			
			for (Iterator iterator = listaMandatos.iterator(); iterator.hasNext();) {
				MandatoAS400Vo mandatoAS400Vo = (MandatoAS400Vo) iterator.next();
				String linea=mandatoAS400Vo.toString();
				flujo.println(linea);
			}
			flujo.close();
			out.close();
			logger.info("Archivo generado");
		}else{
			logger.info("Sin datos Vigentes");
			pathfile= null;
		}
		
		return pathfile;
	}
	@Override
	public String generaArchivoRevocados() throws Exception {
		List<MandatoAS400Vo> listaMandatos=  dao.consultaMandatosDiaRevocados();
		String path= Configuraciones.getConfig("mandato.sap.ruta.archivo");
		String pathfile=path + "MandatosRevocados_transferencia_" + Utils.getAAAAMMDD()  + ".txt";
		logger.info("Generando archivo sap revocados: " + pathfile);
		
		if(listaMandatos!=null && listaMandatos.size()>0){
			OutputStream out = new FileOutputStream(pathfile);
			PrintStream flujo= new PrintStream(out);
			
			for (Iterator iterator = listaMandatos.iterator(); iterator.hasNext();) {
				MandatoAS400Vo mandatoAS400Vo = (MandatoAS400Vo) iterator.next();
				String linea=mandatoAS400Vo.toStringRevocado();
				flujo.println(linea);
			}
			flujo.close();
			out.close();
			logger.info("Archivo generado");
		}else{
			logger.info("Sin datos Revocados");
			pathfile= null;
		}
		
		return pathfile;
	}

}
