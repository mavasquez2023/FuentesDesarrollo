package cl.araucana.ctasfam.batch.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ConfiguracionUtil;
import cl.araucana.ctasfam.batch.dao.PeticionProcesamientoDao;
import cl.araucana.ctasfam.batch.dao.db2.impl.PeticionProcesamientoDaoImpl;
import cl.araucana.ctasfam.batch.thread.AdministradorHebrasThread;

public class MonitorPeticionesProcesamientoJob implements Job {

	private PeticionProcesamientoDao petProDao = new PeticionProcesamientoDaoImpl();

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try{
//			Calcula cantidad disponible en cola de procesamiento  
			int maxColaAsignacion = Integer.parseInt(ConfiguracionUtil.getValor("BATCH_MAX_COLA_PROCESAMIENTO"));
			int disponiblesEnCola = maxColaAsignacion - AdministradorHebrasThread.getColaProcesamiento().size();
//			Maximo de intentos de procesamiento  
			int maxIntentos = Integer.parseInt(ConfiguracionUtil.getValor("BATCH_MAX_COLA_PROCESAMIENTO"));
			
			if(disponiblesEnCola > 0){
//				Recorre la cola para identificar las empresas que tienen peticiones en cola
				List<Integer> listRutsEmpEnCola = new ArrayList<Integer>();
				String empresasEnCola = "";
				for(PeticionProcesamientoDto petPro : AdministradorHebrasThread.getColaProcesamiento()){
					if(!empresasEnCola.contains(String.valueOf(petPro.getRutEmpresa()))){
						empresasEnCola += empresasEnCola + ",";
						listRutsEmpEnCola.add(petPro.getRutEmpresa());
					}
				}
				
//				Obtiene peticiones de procesamiento de propuesta pendientes
				List<PeticionProcesamientoDto> listPendientes = petProDao.getPeticionProcesamientoPorEstado("P", disponiblesEnCola, listRutsEmpEnCola, maxIntentos);
				
//				Asigna peticiones a la cola de procesamiento 
				for(PeticionProcesamientoDto obj : listPendientes){
					obj.setEstado("C");
					try{
						if(petProDao.updatePeticionProcesamiento(obj)){
							AdministradorHebrasThread.getColaProcesamiento().add(obj);
						}else{
//							Log de error aqui
						}
					}catch(TechnicalException te){
						te.printStackTrace();
					}
					
				}
			}
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}