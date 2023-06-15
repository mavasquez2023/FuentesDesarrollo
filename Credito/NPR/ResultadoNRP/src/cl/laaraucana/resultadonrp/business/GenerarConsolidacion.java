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
import cl.laaraucana.resultadonrp.dao.vo.ConceptoVO;
import cl.laaraucana.resultadonrp.dao.vo.FolioVO;
import cl.laaraucana.resultadonrp.dao.vo.ProductoVO;
import cl.laaraucana.resultadonrp.util.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class GenerarConsolidacion {
	protected Logger logger = Logger.getLogger(this.getClass());
	public boolean generarResumenDatabase(){
		try {

			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();
			
			logger.info("Consolidación, consultando conceptos resumen");
			List<String> codigo_conceptos= nrpDAO.consultaConceptos("C"); //C=Consolidación
			
			String periodo= Utils.obtenerPeriodoCualquiera(0);
			
			//Borrando estadisticas periodo
			logger.info("Consolidación, borrando consolidacióm periodo: " + periodo);
			nrpDAO.deleteConsolidacion(periodo);
			
			//Se obtiene la lista de productos asociados a SAP
			logger.info("Consolidación, consultando productos periodo " + periodo);
			List<ProductoVO> productos= nrpDAO.consultaProductos(null);
			
			//Se obtiene la lista de folios generados
			logger.info("Consolidación, consultando folios periodo " + periodo);
			List<FolioVO> folios= nrpDAO.consultaFolios(null);
			
			//Se crea una lista de todos los conceptos que deben generarse para el resumen
			Map<String, ConceptoVO> mapeoConceptos= new HashMap<String, ConceptoVO>();
			for (Iterator iterator = codigo_conceptos.iterator(); iterator.hasNext();) {
				String codigo = (String) iterator.next();
				mapeoConceptos.put(codigo, new ConceptoVO(codigo, Integer.parseInt(periodo)));
			}
			//PRODUCTOS
			//Se itera sobre los productos encontrados en NRP15F1 y se asocia con lista mapeada de conceptos para llenar los valores
			for (Iterator iterator = productos.iterator(); iterator.hasNext();) {
				ProductoVO productoVO = (ProductoVO) iterator.next();
				//Se arma concepto producto
				String concepto= productoVO.getTipo_afiliado().substring(0, 3)+ productoVO.getProducto();
				ConceptoVO concepto_mapa= mapeoConceptos.get(concepto);

				try {
					concepto_mapa.addMontos(productoVO);
				} catch (Exception e) {
					logger.warn("No se ha definido en tabla NRP110 el producto: " + concepto + ", asociado a " + productoVO.getOrigen());
				}

				//Se arma concepto subtotales por origen y tipo_afiliado, si es Sinacaff se usa concepto previo
				if(productoVO.getOrigen().substring(0, 3).equals("SAP")){
					concepto= productoVO.getOrigen().substring(0, 3) + productoVO.getTipo_afiliado().substring(0, 3);
				}
				
				concepto_mapa= mapeoConceptos.get(concepto);
				if(concepto_mapa!=null ){
					if(productoVO.getOrigen().substring(0, 3).equals("SAP")){
						concepto_mapa.addMontos(productoVO);
					}
					
					//Se arma concepto subtotales por tipo_afiliado
					concepto= "TOT" + productoVO.getTipo_afiliado().substring(0, 3);
					concepto_mapa= mapeoConceptos.get(concepto);
					concepto_mapa.addMontos(productoVO);

					//Se arma concepto totales generales
					concepto= "SUBGEN";
					concepto_mapa= mapeoConceptos.get(concepto);
					concepto_mapa.addMontos(productoVO);
				}
			}
			//FOLIOS
			//Se itera sobre los folios encontrados en NRP15F1 y se asocia con lista mapeada de conceptos para llenar los valores
			for (Iterator iterator = folios.iterator(); iterator.hasNext();) {
				FolioVO folioVO = (FolioVO) iterator.next();

				//Se arma concepto subtotales por origen y tipo_afiliado
				String concepto= "FOL" + folioVO.getTipo_afiliado().substring(0, 3);
				ConceptoVO concepto_mapa= mapeoConceptos.get(concepto);
				if(concepto_mapa!=null){
					concepto_mapa.addMontos(folioVO);

					//Se arma concepto subtotales folios
					concepto= "SUBFOL";
					concepto_mapa= mapeoConceptos.get(concepto);
					concepto_mapa.addMontos(folioVO);
				}
			}
			//TOTALES
			//Se busca los totales generales por registros y por folios
			//Se arma concepto subtotales por origen y tipo_afiliado
			//registros
			HashMap totales= nrpDAO.consultaTotalesGenerales();
			String concepto= "TOTGEN";
			ConceptoVO concepto_mapa= mapeoConceptos.get(concepto);
			concepto_mapa.setRegistros((Integer)totales.get("REGISTROS"));
			concepto_mapa.setMonto(((BigDecimal)totales.get("MONTO")).longValue());
			//folios
			totales= nrpDAO.consultaTotalesFolios();
			concepto= "FOLTOT";
			concepto_mapa= mapeoConceptos.get(concepto);
			concepto_mapa.setRegistros((Integer)totales.get("REGISTROS"));
			concepto_mapa.setMonto(((BigDecimal)totales.get("MONTO")).longValue());
			
			
			List<ConceptoVO> listaConceptos= new ArrayList<ConceptoVO>();
			for (Iterator iterator = mapeoConceptos.values().iterator(); iterator.hasNext();) {
				ConceptoVO concepto_fulled = (ConceptoVO) iterator.next();
				listaConceptos.add(concepto_fulled);
			}
			logger.info("Cantidad registros a insertar:" + listaConceptos.size());
			int resultado= nrpDAO.insertConceptos(listaConceptos);
			
			
			logger.info("Ok Consolidación:");
			return true;
		} catch (Exception e) {
			logger.error("Error en insert Database Resumen Consolidacion ");
			e.printStackTrace();
			return false;				
		}
	}
}
