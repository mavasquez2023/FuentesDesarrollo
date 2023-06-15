package cl.araucana.ctasfam.batch.thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ConfiguracionUtil;
import cl.araucana.ctasfam.batch.dao.BasicDb2Connection;

public class AdministradorHebrasThread extends Thread {
	
	final static Logger logger = Logger.getLogger(AdministradorHebrasThread.class);
	
	private static List<PeticionProcesamientoDto> colaProcesamiento;
	private static ProcesadorPeticionesThread [] poolHebras;
	private static BasicDb2Connection [] poolConexiones;
	private static Boolean running = false;

	public AdministradorHebrasThread(){}
	
	public AdministradorHebrasThread(Integer qty) {
		logger.info("Creando administrador de hebras del procesamiento de peticiones");
		
		AdministradorHebrasThread.setPoolConexiones(new BasicDb2Connection[qty]);
		AdministradorHebrasThread.setPoolHebras(new ProcesadorPeticionesThread[qty]);
		AdministradorHebrasThread.setColaProcesamiento(new ArrayList<PeticionProcesamientoDto>());
		
		for(int i = 0; i < qty; i++){
			AdministradorHebrasThread.getPoolConexiones()[i] = new BasicDb2Connection();
			AdministradorHebrasThread.getPoolHebras()[i] = new ProcesadorPeticionesThread();
		}
	}

	public static List<PeticionProcesamientoDto> getColaProcesamiento() {
		return colaProcesamiento;
	}

	public static void setColaProcesamiento(
			List<PeticionProcesamientoDto> colaProcesamiento) {
		AdministradorHebrasThread.colaProcesamiento = colaProcesamiento;
	}

	public static ProcesadorPeticionesThread[] getPoolHebras() {
		return poolHebras;
	}

	public static void setPoolHebras(ProcesadorPeticionesThread[] poolHebras) {
		AdministradorHebrasThread.poolHebras = poolHebras;
	}

	public static BasicDb2Connection[] getPoolConexiones() {
		return poolConexiones;
	}

	public static void setPoolConexiones(BasicDb2Connection[] poolConexiones) {
		AdministradorHebrasThread.poolConexiones = poolConexiones;
	}
	
	public static Boolean getRunning() {
		return AdministradorHebrasThread.running;
	}

	public static void setRunning(Boolean running) {
		AdministradorHebrasThread.running = running;
	}

	public static Integer getQtyThreadsDisponibles(){
		Integer count = 0;
		for (int i = 0; i < poolHebras.length; i++) {
			if (poolHebras[i] == null || !poolHebras[i].isAlive()) {
				count++;
			}
		}
		return count;
	}

	public void run(){
		logger.info("Iniciando Procesamiento de Peticiones");
		AdministradorHebrasThread.setRunning(true);
		
		while(AdministradorHebrasThread.getRunning()){
			if(!AdministradorHebrasThread.getColaProcesamiento().isEmpty()){
				
				try {
					PeticionProcesamientoDto nextObj = AdministradorHebrasThread.getColaProcesamiento().get(0);
					
					boolean asig = false;
					do {
						for (int i = 0; i < AdministradorHebrasThread.getPoolHebras().length; i++) {
							if (AdministradorHebrasThread.getPoolHebras()[i] == null || !AdministradorHebrasThread.getPoolHebras()[i].isAlive()) {

								AdministradorHebrasThread.getPoolHebras()[i] = new ProcesadorPeticionesThread(i, nextObj);
								AdministradorHebrasThread.getPoolHebras()[i].start();
								
								AdministradorHebrasThread.getColaProcesamiento().remove(0);
								asig = true;
								break;
							}
						}
						if(!asig){
							try {
								Integer segundos = Integer.parseInt(ConfiguracionUtil.getValor("BATCH_SEGUNDOS_ESPERA_HEBRAS_OCUPADAS"));
								Thread.sleep(segundos*1000);
							} catch (InterruptedException e) {
								throw new TechnicalException("0201", "Ocurrio un error al esperar por hebras desocupadas", e);
							} catch(Exception e){
								throw new TechnicalException("0202", "Ocurrio un error al esperar por hebras desocupadas", e);
							}
						}
					} while (!asig);
				} catch (TechnicalException te) {
					logger.error("Upss!! Ocurrio un error en el administrador de hebras");
					logger.error(te.getCode() + ": " + te.getDescription(), te);
				} catch(Exception e){
					logger.error("Upss!! Ocurrio un error en el administrador de hebras", e);
				}
			}else{
				try {
					int segundos = Integer.parseInt(ConfiguracionUtil.getValor("BATCH_SEGUNDOS_ESPERA_COLA_VACIA"));
					Thread.sleep(segundos*1000);
				} catch (InterruptedException e) {
					logger.error("Upss!! Ocurrio al esperar por peticiones de procesamientos pendientes", e);
				} catch(Exception e){
					logger.error("Upss!! Ocurrio al esperar por peticiones de procesamientos pendientes", e);
				}
			}
		}
	}
}
