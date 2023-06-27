/**
 * 
 */
package cl.araucana.cheque.thread;

import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.cheque.dao.EstadisticasDAO;
import cl.araucana.cheque.dao.TesoDAO;
import cl.araucana.cheque.report.Report;

/**
 * @author usist199
 *
 */
public class EstadisticasThread extends Thread{
	private static Logger log = Logger.getLogger(EstadisticasThread.class);
	private int folio;
	private String estado, mensaje;
	private Map param;
	private TesoDAO tesodao= null;
	public EstadisticasThread(int folio, String estado, String mensaje){
		this.folio= folio;
		this.estado= estado;
		this.mensaje= mensaje;
	}
	public EstadisticasThread(Map param){
		this.param= param;
	}
	
	public void run(){
		int resultado;
		try {
			tesodao= new TesoDAO();
			log.info("Guardando Estadisticas");
			EstadisticasDAO estadao= new EstadisticasDAO(tesodao.getConnection());
			if(param!=null){
				resultado= estadao.insert(param);
			}else{
				if(mensaje!=null && mensaje.length()>100){
					mensaje= mensaje.substring(0, 100);
				}
				resultado= estadao.update(folio, estado, mensaje);
			}
			log.debug("Resultado operación: " + resultado);
		}catch (Exception e) {
			log.error("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(tesodao!=null){
				tesodao.closeConnectionDAO();
			}
		}
	}
}
