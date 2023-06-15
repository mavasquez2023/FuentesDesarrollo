/**
 * 
 */
package cl.araucana.wsafiliado.mgr;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.core.util.Rut;
import cl.araucana.wsafiliado.dao.AfiliacionDAO;
import cl.araucana.wsafiliado.to.SistemaVO;
import cl.araucana.wsafiliado.vo.DataAfiliadoVO;



/**
 * @author IBM Software Factory
 *
 */
public class ConsultaAfiliadoSistemas {
	private static Logger log = Logger.getLogger(ConsultaAfiliadoSistemas.class);
	public static Map<String, Integer> obtenerEstadoAfiliacion(int rutAfiliado, boolean conCargas) throws SQLException {
		int estado=0;
		Rut rutfull= new Rut(rutAfiliado);
		Map<String, Integer> salida = null;
		Map<Integer, SistemaVO> servicios= SistemasSingleton.getInstance().getListaSistemas();
		for (int i = 0; i < servicios.size(); i++) {
			SistemaVO servicio= servicios.get(new Integer(i+1));
			if(servicio!=null){
				if(servicio.getSistema().equals("DB2") && servicio.getEstado()==1){
					log.info("Consultando DB2, RUT: " + rutAfiliado);
					Map salidaDB2=null;
					if(conCargas){
						salidaDB2=AfiliacionDAO.obtenerEstadoAfiliacionDB2(rutAfiliado);
					}else{
						salidaDB2=AfiliacionDAO.obtenerEstadoAfiliacionDB2SinCargas(rutAfiliado);
					}
					if(salidaDB2!=null){
						estado= (Integer)salidaDB2.get("ESTADO");
						 int rutaux= ((BigDecimal)salidaDB2.get("RUTBENEF")).intValue();
						salida= new HashMap<String, Integer>();
						salida.put("ESTADO", new Integer(estado));
						salida.put("RUTBENEF", new Integer(rutaux));
					}else{
						estado=-1;
					}
				}else if(servicio.getSistema().equals("CRM") && servicio.getEstado()==1){
					estado=AfiliacionDAO.obtenerEstadoAfiliacionCRM(rutfull.toString().replaceAll("\\.", ""));
					if(estado!=-1){
						salida= new HashMap<String, Integer>();
						salida.put("RUTBENEF", new Integer(rutAfiliado));
						salida.put("ESTADO", estado);
					}
				}
			}
			if(estado!= -1){
				break;
			}
		}
		
		return salida;
	}
	
	public static List<DataAfiliadoVO> obtenerDataAfiliacion(int rutAfiliado, boolean conCargas) throws SQLException {
		int estado=0;
		List<DataAfiliadoVO> dataAfiliado=null;
		Rut rutfull= new Rut(rutAfiliado);
		Map<Integer, SistemaVO> servicios= SistemasSingleton.getInstance().getListaSistemasGDA();
		for (int i = 0; i < servicios.size(); i++) {
			SistemaVO servicio= servicios.get(new Integer(i+1));
			
			if(servicio!=null){
				if(servicio.getSistema().equals("DB2") && servicio.getEstado()==1){
					//Map salidaDB2=null;
					log.info("Consultando DB2, RUT: " + rutAfiliado + ", con cargas:" + conCargas);
					if(conCargas){
						dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacion(rutAfiliado);
						//salidaDB2=AfiliacionDAO.obtenerEstadoAfiliacionDB2(rutAfiliado);
					}else{
						dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacionSinCargas(rutAfiliado);
					}
				}else if(servicio.getSistema().equals("CRM") && servicio.getEstado()==1){
					dataAfiliado=AfiliacionDAO.obtenerDataAfiliacionCRM(rutfull.toString().replaceAll("\\.", ""));
				}
				if(dataAfiliado!=null){
					estado= 1;
					
				}else{
					estado=-1;
				}
			}

			if(estado!= -1){
				break;
			}
		}
		return dataAfiliado;
	}
	
	public static List<DataAfiliadoVO> obtenerDataExcepcion(int rutAfiliado) throws SQLException {
		List<DataAfiliadoVO> dataAfiliado =  AfiliacionDAO.obtenerDatosExcepcion(rutAfiliado);
		
		return dataAfiliado;
	}
}
