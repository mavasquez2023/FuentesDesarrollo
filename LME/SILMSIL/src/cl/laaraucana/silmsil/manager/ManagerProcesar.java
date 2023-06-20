package cl.laaraucana.silmsil.manager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.ILCLM050DAO;
import cl.laaraucana.silmsil.dao.ILCLM051DAO;
import cl.laaraucana.silmsil.dao.ILCLM053DAO;
import cl.laaraucana.silmsil.dao.ILCSIL050DAO;
import cl.laaraucana.silmsil.dao.ILCSIL051DAO;
import cl.laaraucana.silmsil.dao.ILCSIL053DAO;
import cl.laaraucana.silmsil.dao.ILFJA058DAO;
import cl.laaraucana.silmsil.util.UtilProcesar;
import cl.laaraucana.silmsil.vo.EstadoEjecucion_VO;
import cl.laaraucana.silmsil.vo.ILFJA058VO;

public class ManagerProcesar {

	private Logger log = Logger.getLogger(this.getClass());
	
	ManagerArchivosPlanos manager = new ManagerArchivosPlanos();
	ILFJA058VO vo = new ILFJA058VO();
	UtilProcesar util = new UtilProcesar();
	
	private String msj = "";
	
	private boolean sil = false;
	private boolean lm = false;
	
	public ArrayList procesarSIL_ejecucion(String ahno, ArrayList lista, String usuario){
		ArrayList listaEjecucionSIL=new ArrayList();
		try{
			log.info("* * * * * [procesarSIL_ejecucion()]* * * * *");			
			EstadoEjecucion_VO ej;
			String periodo = null;
			int idE;
			String idEstado;
			String idEstadoAux;
			boolean val=false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String auxPeriodo="";
			for(int i = 0; i < lista.size(); i++){				
				//Pregunta si es re-validación.				
				String dato = String.valueOf(lista.get(i));				
				if(!dato.isEmpty()) {										
					//Setea período de trabajo.
					//periodo = ahno+util.getMesProceso(dato, 7);					
					periodo = ahno+util.getMesProceso(dato);
					//Pregunta por estado de proceso.								
					//se envia parametro 1 para consultar ultimo estado de SIL para el periodo enviado
					vo = ILFJA058DAO.consultaEstados("1", periodo);					
					idEstado = (vo == null?"0":String.valueOf(vo.getIdesta()));
					idE= Integer.parseInt(idEstado);					
					if(idE <= 5){		
						//actualiza estado iniciado.
						this.setProcesoIniciado("SIL", periodo, usuario);

						//Ejecuta AS400 a través de procedimiento de almacenado. 
						//comentado para test
						val = ILCSIL053DAO.callILCSIL053(periodo, usuario);
						
						log.info("Se ha procesado y validado el proceso SIL para el período ["+periodo+"] satisfactoriamente.");
						ej=new EstadoEjecucion_VO();
						ej.setPeriodo(periodo);
						ej.setProceso("SIL");
						ej.setKeyEjecucion(val);
						ej.setKeyReProceso(false);
						ej.setUltimoEstado("Proceso iniciado en este instante");
						listaEjecucionSIL.add(ej);
					}else{//periodo esta mayor a 6
						//recuperamos ultima validacion sobre el periodo para extraer n° errores
						vo = ILFJA058DAO.consultaEstadosValidados("1", periodo);																	
						ej=new EstadoEjecucion_VO();
						ej.setPeriodo(periodo);
						ej.setProceso("SIL");
						ej.setKeyReProceso(true);
						ej.setKeyEjecucion(false);
												
						ej.setUltimoEstado("Validado con "+vo.getNepret()+" errores, fecha: "+vo.getFepret().trim());
						listaEjecucionSIL.add(ej);				
					}
				}
			}
		}catch(Exception ex){
			log.error("Error en procesarSIL()" + ex.getMessage());
			ex.printStackTrace();
		}		
		return listaEjecucionSIL;		
	}//end  procesarSIL_ejecucion()
	
	
	public ArrayList procesarLM_ejecucion(String ahno, ArrayList lista, String usuario){
		ArrayList listaEjecucionLM=new ArrayList();
		try{
			log.info("* * * * * [procesarLM_ejecucion()]* * * * *");			
			EstadoEjecucion_VO ej;
			String periodo = null;
			int idE;
			String idEstado;
			String idEstadoAux;
			boolean val=false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String auxPeriodo="";
			for(int i = 0; i < lista.size(); i++){				
				//Pregunta si es re-validación.				
				String dato = String.valueOf(lista.get(i));				
				if(!dato.isEmpty()) {										
					//Setea período de trabajo.
					//periodo = ahno+util.getMesProceso(dato, 6);
					periodo = ahno+util.getMesProceso(dato);
					log.info("* * * * * periodo :"+periodo);
					//Pregunta por estado de proceso.								
					//se envia parametro 2 para consultar ultimo estado de LM para el periodo enviado
					vo = ILFJA058DAO.consultaEstados("2", periodo);					
					idEstado = (vo == null?"0":String.valueOf(vo.getIdesta()));
					idE= Integer.parseInt(idEstado);
					
					if(idE <= 5){	
						//se actualiza estado de proceso a iniciado, luego se llama a ejecucion de CL. 
						this.setProcesoIniciado("LM", periodo, usuario);
				//Ejecuta AS400 a través de procedimiento de almacenado.
				//comentado para test
						
				val = ILCLM053DAO.callILCLM053(periodo, usuario);						
						log.info("Se ha procesado y validado el proceso LM para el período ["+periodo+"] satisfactoriamente.");
						ej=new EstadoEjecucion_VO();
						ej.setPeriodo(periodo);
						ej.setProceso("LM");
						ej.setKeyEjecucion(val);
						ej.setKeyReProceso(false);
						ej.setUltimoEstado("Proceso iniciado en este instante");
						listaEjecucionLM.add(ej);
					}else{//periodo esta igual o mayor a 6
						//recuperamos ultima validacion sobre el periodo para extraer n° errores
						vo = ILFJA058DAO.consultaEstadosValidados("2", periodo);																	
						ej=new EstadoEjecucion_VO();
						ej.setPeriodo(periodo);
						ej.setProceso("LM");
						ej.setKeyReProceso(true);
						ej.setKeyEjecucion(false);												
						ej.setUltimoEstado("Validado con "+vo.getNepret()+" errores, fecha: "+vo.getFepret().trim());
						listaEjecucionLM.add(ej);				
					}
					
				}
			}
		}catch(Exception ex){
			log.error("Error en procesarLM()" + ex.getMessage());
			ex.printStackTrace();
		}		
		return listaEjecucionLM;		
	}//end  procesarLM_ejecucion()
	

	/**
	 * Método para validar proceso SIL de manera independiente.
	 * @param ahno
	 * @param lista
	 * @param usuario
	 * @return
	 */
	public boolean validarSIL(String ahno, ArrayList lista, String usuario){
		boolean val = false;
		try{
			log.info("Entro al procesar SIL");
			String periodo = null;
			for(int i = 0; i < lista.size(); i++){
				String dato = String.valueOf(lista.get(i));
				if(!dato.isEmpty()){
					//Setea período de trabajo.
					//periodo = ahno+util.getMesProceso(dato.substring(7), 7);
					periodo = ahno+util.getMesProceso(dato.substring(7));
					
					//Pregunta por estado de proceso.
					vo = ILFJA058DAO.consultaEstados(dato, periodo);
					
					int idEstado = (vo == null?0:vo.getIdesta());
					log.info("IdEstado = " + idEstado);
					
					if((idEstado < 1)&&(idEstado < 5)){
						//Pregunta por estado de proceso.
						log.info("Ejecuta AS400 ILSILM051");
						val = ILCSIL051DAO.callILCSIL051(periodo, usuario);
						
						log.info("Se ha validado el proceso SIL para el " +
							"período ["+periodo+"] satisfactoriamente.");
					}
				}
			}
			return val;
		}
		catch(Exception ex){
			log.error("Error, validarSIL :" + ex.getMessage());
			ex.printStackTrace();
		}
		return val;
	}
	
	/**
	 * Método para validar proceso LM de manera independiente.
	 * @param ahno
	 * @param lista
	 * @param usuario
	 * @return
	 */
	public boolean validarLM(String ahno, ArrayList lista, String usuario){
		boolean val = false;
		try{
			log.info("Entro al procesar LM");
			String periodo = null;
			for(int i = 0; i < lista.size(); i++){
				String dato = String.valueOf(lista.get(i));
				if(!dato.isEmpty()){
					//Setea período de trabajo.
					//periodo = ahno+util.getMesProceso(dato.substring(7), 7);
					periodo = ahno+util.getMesProceso(dato.substring(7));
					log.info("* * * * * periodo :"+periodo);
					//Pregunta por estado de proceso.
					vo = ILFJA058DAO.consultaEstados(dato, periodo);
					
					int idEstado = (vo == null?0:vo.getIdesta());
					log.info("IdEstado = " + idEstado);
					
					if((idEstado < 1)&&(idEstado < 5)){
						//Pregunta por estado de proceso.
						log.info("Ejecuta AS400 ILCLM051");
						val = ILCLM051DAO.callILCLM051(periodo, usuario);
						
						log.info("Se ha validado el proceso LM para el " +
							"período ["+periodo+"] satisfactoriamente.");
					}
				}
			}
			return val;
		}
		catch(Exception ex){
			log.error("Error, validarSLM :" + ex.getMessage());
			ex.printStackTrace();
		}
		return val;
	}
	
	/**
	 * metodo que se encarga de realizar el proceso de carga-validacion para
	 * los rocesos que se reprocesaran
	 * **/
	public boolean reprocesarPeriodos_SIL_LM(ArrayList listaEjecucion)throws Exception{
		log.info("* * * * * [reprocesarPeriodos_SIL_LM] * * * * *");
		boolean key=false;
		boolean val=false;
		Iterator it=listaEjecucion.iterator();
		String aux="";
		String auxProceso[];
		String periodo="";
		String usuario="UuarioJava";
		
		log.info("listaEjecucion: "+listaEjecucion.size());
		
		while(it.hasNext()){
			aux=(String) it.next();
			auxProceso=aux.split("_");
			if(auxProceso[0].equalsIgnoreCase("SIL")){
				periodo=auxProceso[1];
				log.info("reprocesando SIL, fecha: "+periodo);
				
				//se actualiza estado de proceso a iniciado, luego se llama a ejecucion de CL. 
				this.setProcesoIniciado("SIL", periodo, usuario);
				val = ILCSIL053DAO.callILCSIL053(periodo, usuario);
			}else if(auxProceso[0].equalsIgnoreCase("LM")){
				periodo=auxProceso[1];
				log.info("reprocesando LM, fecha: "+periodo);
				
				//se actualiza estado de proceso a iniciado, luego se llama a ejecucion de CL. 
				this.setProcesoIniciado("LM", periodo, usuario);
				val = ILCLM053DAO.callILCLM053(periodo, usuario);
			}
		}
		return key;		
	}//end reprocesarPeriodos_SIL_LM()
	
	/**
	 * metodo que se encarga de realizar el proceso de validacion para
	 * los rocesos que se revalidaran
	 * **/
	public boolean revalidarPeriodos_SIL_LM(ArrayList listaEjecucion)throws Exception{
		log.info("* * * * * [revalidarPeriodos_SIL_LM] * * * * *");
		boolean key=false;
		boolean val=false;
		
		Iterator it=listaEjecucion.iterator();
		String aux="";
		String auxProceso[];
		String periodo="";
		String usuario="UuarioJava";
		
		while(it.hasNext()){
			aux=(String) it.next();
			auxProceso=aux.split("_");
			if(auxProceso[0].equalsIgnoreCase("SIL")){
				periodo=auxProceso[1];
				log.info("revalidando SIL, fecha: "+periodo);
				
				val = ILCSIL051DAO.callILCSIL051(periodo, usuario);
			}else if(auxProceso[0].equalsIgnoreCase("LM")){
				periodo=auxProceso[1];
				log.info("revalidando LM, fecha: "+periodo);
				val = ILCLM051DAO.callILCLM051(periodo, usuario);
			}
		}
		return key;	
	}//end reprocesarPeriodos_SIL_LM()
	
	private void setProcesoIniciado(String proceso,String periodo, String usuario)throws Exception{
		//Se actualiza estado en archivo LIEXP.ILFJA058. a estado iniciado		
		HashMap<String, String> map = new HashMap<String, String>();
		if(proceso.equalsIgnoreCase("LM")){
			map.put("proceso", "2");
		}else if(proceso.equalsIgnoreCase("SIL")){
			map.put("proceso", "1");
		}
		map.put("fecha", periodo);
		map.put("peractual", UtilProcesar.getDate());
		map.put("hractual", UtilProcesar.getHours()); 
		map.put("usuario", usuario);		
		ILFJA058DAO.updateEstadoIniciado(map);
	}
}
