/**
 * 
 */
package cl.laaraucana.resultadonrp.business;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSFile;

import cl.laaraucana.resultadonrp.dao.ConsultaServicesDAO;
import cl.laaraucana.resultadonrp.dao.vo.ConceptoVO;
import cl.laaraucana.resultadonrp.dao.vo.FolioVO;
import cl.laaraucana.resultadonrp.dao.vo.ProductoVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenDisponibilizacionVO;
import cl.laaraucana.resultadonrp.util.Constantes;
import cl.laaraucana.resultadonrp.util.UtilFolder;
import cl.laaraucana.resultadonrp.util.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class GenerarDisponibilizacion {
	protected Logger logger = Logger.getLogger(this.getClass());
	private AS400 system=null;
	
	public boolean generarResumenDatabase(){
		try {
			int count=0;
			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();
			
			logger.info("Disponibilizacion, consultando conceptos resumen");
			List<String> codigo_conceptos= nrpDAO.consultaConceptos("D");
			
			String periodo= Utils.obtenerPeriodoCualquiera(0);
			
			//Borrando estadisticas periodo
			logger.info("Disponibilizacion, borrando disponibilizacion periodo: " + periodo);
			nrpDAO.deleteDisponibilizacion(periodo);
			
			//Se obtiene la cantidad de archivos disponiblizados
			logger.info("Disponibilizacion, consultando ruta archivo txts");
			
			
			String server= Constantes.CONFIG_PROPERTIES.getString("config.archivos.as400.pub.server");
			String usuario= Constantes.CONFIG_PROPERTIES.getString("config.archivos.as400.pub.user");
			String password= Constantes.CONFIG_PROPERTIES.getString("config.archivos.as400.pub.password");
			system = new AS400(server, usuario, password);
			
			String ruta_txt= Constantes.CONFIG_PROPERTIES.getString("config.archivos.as400.path.nominas");
			logger.info("Ruta carpeta txt disponiblizados: " + ruta_txt);
			
			//File carpeta = new File("C:/tmp/NRP/201907");
			IFSFile carpeta = new IFSFile(system, ruta_txt);
			UtilFolder folder= new UtilFolder();
			count= folder.contarFicherosPorCarpetaAS400(carpeta);
			
			String concepto="";
			if(codigo_conceptos.iterator().hasNext()){
				concepto = (String) codigo_conceptos.iterator().next();
			}
			ResumenDisponibilizacionVO resumen= new ResumenDisponibilizacionVO();
			resumen.setPeriodo(Integer.parseInt(periodo));
			resumen.setConcepto(concepto);
			resumen.setCantidadArchivos(count);
			resumen.setRuta("\\\\" + server + ruta_txt.trim().replaceAll("/", "\\\\") );
			System.out.println("ruta=" +  resumen.getRuta());
			//Se inserta en base de datos
			nrpDAO.insertDisponibilizacion(resumen);
			
			if(count>0){
				logger.info("Ok Disponibilización:");
				return true;
			}else{
				logger.info("Error Disponibilización:");
				return false;
			}
		} catch (Exception e) {
			logger.error("Error en insert Database Resumen Consolidacion ");
			e.printStackTrace();
			return false;
		}
		finally{
			if(system!=null){
				system.disconnectAllServices();
			}
		}
	}
}
