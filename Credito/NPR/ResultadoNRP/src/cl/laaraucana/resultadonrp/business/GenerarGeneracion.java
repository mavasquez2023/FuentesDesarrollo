/**
 * 
 */
package cl.laaraucana.resultadonrp.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import cl.laaraucana.resultadonrp.dao.ConsultaServicesDAO;
import cl.laaraucana.resultadonrp.dao.vo.ArchivosVO;
import cl.laaraucana.resultadonrp.dao.vo.ConceptoGenVO;
import cl.laaraucana.resultadonrp.dao.vo.ConceptoVO;
import cl.laaraucana.resultadonrp.dao.vo.FolioVO;
import cl.laaraucana.resultadonrp.dao.vo.ProductoVO;
import cl.laaraucana.resultadonrp.util.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class GenerarGeneracion {
	protected Logger logger = Logger.getLogger(this.getClass());
	public boolean generarResumenDatabase(){
		try {

			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();
			
			logger.info("Generación, consultando conceptos resumen");
			List<String> codigo_conceptos= nrpDAO.consultaConceptos("G"); //G=Generación
			
			String periodo= Utils.obtenerPeriodoCualquiera(0);
			
			//Borrando detalle y estadisticas periodo
			logger.info("Generación, borrando periodo: " + periodo);
			nrpDAO.deleteGeneracion(periodo);
			
			//Insertando Detalle Generación
			logger.info("Generación, insertando detalle periodo: " + periodo);
			int resultado_generacion= nrpDAO.insertGeneracion(periodo);

			if(resultado_generacion>0){
				//Se obtiene la suma de archivos generados por tipo afiliado y tipo archivo
				logger.info("Generación, consultando detalle archivos periodo " + periodo);
				List<ArchivosVO> productos= nrpDAO.consultaArchivosGeneracion(periodo);


				//Se crea una lista de todos los conceptos que deben generarse para el resumen
				Map<String, ConceptoGenVO> mapeoConceptos= new HashMap<String, ConceptoGenVO>();
				for (Iterator iterator = codigo_conceptos.iterator(); iterator.hasNext();) {
					String codigo = (String) iterator.next();
					mapeoConceptos.put(codigo, new ConceptoGenVO(codigo, Integer.parseInt(periodo)));
				}
				//PRODUCTOS
				//Se itera sobre los archivos encontrados en NRP120F1 y se asocia con lista mapeada de conceptos para llenar los valores
				for (Iterator iterator = productos.iterator(); iterator.hasNext();) {
					ArchivosVO archivoVO = (ArchivosVO) iterator.next();
					//Se arma concepto producto
					String concepto= concepto=archivoVO.getTipo_afiliado()+ archivoVO.getTipo_archivo();
					if(archivoVO.getTipo_archivo().trim().equals("")){
						concepto= "NOMNOG";
					}
					ConceptoGenVO concepto_mapa= mapeoConceptos.get(concepto);

					try {
						concepto_mapa.addMontos(archivoVO);
					} catch (Exception e) {
						logger.warn("No se ha definido en tabla NRP115 el concepto: " + concepto + ", asociado a " + archivoVO.getTipo_afiliado()+archivoVO.getTipo_archivo());
					}


					if(concepto_mapa!=null){

						//Se arma concepto subtotales por tipo_afiliado
						concepto= "TOT" + archivoVO.getTipo_afiliado();
						concepto_mapa= mapeoConceptos.get(concepto);
						concepto_mapa.addMontos(archivoVO);

						//Se arma concepto totales generales
						concepto= "TOTNOM";
						concepto_mapa= mapeoConceptos.get(concepto);
						concepto_mapa.addMontos(archivoVO);
					}
				}


				List<ConceptoGenVO> listaConceptos= new ArrayList<ConceptoGenVO>();
				for (Iterator iterator = mapeoConceptos.values().iterator(); iterator.hasNext();) {
					ConceptoGenVO concepto_fulled = (ConceptoGenVO) iterator.next();
					listaConceptos.add(concepto_fulled);
				}
				logger.info("Cantidad registros a insertar:" + listaConceptos.size());
				int resultado= nrpDAO.insertConceptosGeneracion(listaConceptos);


				logger.info("Ok Consolidación:");
				return true;
			}
		} catch (Exception e) {
			logger.error("Error en insert Database Resumen Consolidacion ");
			e.printStackTrace();				
		}
		return false;
	}
}
