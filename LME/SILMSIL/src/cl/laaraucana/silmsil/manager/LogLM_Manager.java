package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import cl.laaraucana.silmsil.dao.ILCLM051_TDAO;
import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.util.UtilProcesar;
import cl.laaraucana.silmsil.vo.LM_VistaErrores_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;
import cl.laaraucana.silmsil.vo.LM_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_VO;

public class LogLM_Manager {

	private Logger log = Logger.getLogger(this.getClass());
	Configuraciones conf=new Configuraciones();
	
	
	/**
	 *  Método para desplegar una lista con los errores para el log de errores del proceso SIL.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginaInicio(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		int cantidadPaginas=0;
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		
		log.info("Cálcula paginación con fecha = " + pag.getFechaProceso());
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(LM_DAO.count_LogLM(pag.getFechaProceso()));
		pag.setPaginaActual(1);
		log.info("Cantidad de páginas traidas de la BD = " + pag.getCantidad_BD());
		
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		log.info("* * "+cantidadPaginas);
		
		if((pag.getCantidad_BD()%10)>0){
			log.info("* * * * "+cantidadPaginas);
			cantidadPaginas++; 
		}
		log.info("* * * * "+cantidadPaginas);
		pag.setCantidadPaginas(cantidadPaginas);		
		
		log.info("Obtiene y setea listado.");
		//Obtiene y setea listado a mostrar en interfaz gráfica.
		ArrayList<LM_GlosaErrores_VO> lmList = LM_DAO.logErrorLM(pag.getFechaProceso(), cant);
		log.info("Tamaño lista = " + lmList.size());
		
		if(lmList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelab()));
			//Obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelab()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}		
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < lmList.size(); i++){
			correlab = ((pag_actual-1)*largo+count);
			lmList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea listado.
		pag.setListaActual(lmList);		
		return pag;
	}
	
	/**
	 * Método que responde al botón SIGUIENTE en interfaz gráfica para log de errores.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginarAvance(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		
		//Obtener cantidad de datos a paginar.
		pag.setCantidad_BD(LM_DAO.count_LogLM(pag.getFechaProceso()));
		
		if(pag.getCantidad_BD()>0){			
			//Obtenemos numero de paginas.					
			//Consultamos en que pagina estamos.
			if(pag.getPaginaActual()<pag.getCantidadPaginas()){
				ArrayList<LM_VO> lmList = LM_DAO.pagina_AvanzarLogLM(pag);
				
				//Obtenemos el primero de la lista consultada.
				lmVOaux=(LM_VO) lmList.get(0);						
				pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelab()));
				
				//Obtenemos ultimo de la lista consultada.
				lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
				pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelab()));
				
				//Extraemos largo de la lista actual.
				pag.setLargolistaActual(lmList.size());
				
				//Aumentamos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()+1);
				
				int pag_actual = pag.getPaginaActual();
				int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
				int count = 1;
				int correlab = 0;
				
				for(int i = 0; i < lmList.size(); i++){
					//Carga numeración a mostrar por pantalla.
					correlab = ((pag_actual-1)*largo+count);
					lmList.get(i).setCorrelab(correlab);
					count++;
				}
				
				pag.setListaActual(lmList);
				
			}else if(pag.getPaginaActual()==pag.getCantidadPaginas()){
				//Pag.setListaActual(pag.getListaActual());
				return pag;
			}else{
				//Ya estamos en ultima pagina asignamos lista vacia.
				pag.setListaActual(new ArrayList());
			}
		}else{
			//No exisaten datos para mostrar a este periodo.
			pag.setListaActual(new ArrayList());
		}				
		return pag;
	}//end paginarAvance
	
	/**
	 * Método que responde al botón ANTERIOR en interfaz gráfica para log de errores.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginarRetroceso(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		//Obtenemos cantidad de registros BD
		pag.setCantidad_BD(LM_DAO.count_LogLM(pag.getFechaProceso()));
		
		log.info("Cantidad de datos a mostrar : " + pag.getCantidad_BD());
		
		if(pag.getCantidad_BD()>0){							
			//Evaluamos pagina actual.
			if(pag.getPaginaActual()>1){
				ArrayList<LM_VO> lmList = LM_DAO.pagina_RetrocederLogLM(pag);
				
				//Obtenemos el primero de la lista consultada.
				lmVOaux=(LM_VO) lmList.get(0);						
				pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelab()));
				
				//Obtenemos ultimo de la lista consultada.
				lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
				pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelab()));
				
				//Extraemos largo de la lista actual.
				pag.setLargolistaActual(lmList.size());				
				
				//Diminuimos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()-1);
				
				int pag_actual = pag.getPaginaActual();
				int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
				int count = 1;
				int correlab = 0;
				
				for(int i = 0; i < lmList.size(); i++){
					//Carga numeración a mostrar por pantalla.
					correlab = ((pag_actual-1)*largo+count);
					lmList.get(i).setCorrelab(correlab);
					count++;
				}
				
				pag.setListaActual(lmList);
				
			}else if(pag.getPaginaActual()==1){
				return pag;
			}else{
				//Ya estamos en primera pagina asignamos lista vacia. por ultimo retroceso.
				pag.setListaActual(new ArrayList());
			}
		}else{
			//No exisaten datos para mostrar a este periodo.
			pag.setListaActual(new ArrayList());
		}
		return pag;
	}//end paginarRetroceso
		
	public Paginacion_VO paginarUltimo_LogLM(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		int cantidadPaginas=0;
				
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
				
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(LM_DAO.count_LogLM(pag.getFechaProceso()));
		
		log.info("Cantidad de páginas traidas de la BD = " + pag.getCantidad_BD());
		
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		log.info("* * "+cantidadPaginas);
		//se calcula el sobrante para la ultima pagina
		int modRes=pag.getCantidad_BD()%10;
		//se asigna una pagina adicional para el sobrante. y se carga la cantidad de registros a mostrar en la ultima pagina
		if(modRes>0){
			log.info("* * * * "+cantidadPaginas);
			//se sobre escribe cant que es "cantidad de registros por pagina" para mostrar en ultima pagina.
			cant=String.valueOf(modRes);
			cantidadPaginas++; 
		}
		pag.setPaginaActual(cantidadPaginas);
		log.info("* * * * "+cantidadPaginas);
		pag.setCantidadPaginas(cantidadPaginas);		
		
		log.info("Obtiene y setea listado.");
		//Obtiene y setea listado a mostrar en interfaz gráfica.
		ArrayList<LM_GlosaErrores_VO> lmList = LM_DAO.paginaUltima_logErrorLM(pag.getFechaProceso(), cant);
		log.info("Tamaño lista = " + lmList.size());
		
		if(lmList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelab()));
			//Obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelab()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}		
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < lmList.size(); i++){
			correlab = ((pag_actual-1)*largo+count);
			lmList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea listado.
		pag.setListaActual(lmList);		
		return pag;
	}//end paginarUltimo_LogLM

	/*START AGRUPACION*/

	/**
	 * Método para desplegar una lista con los errores agrupados por el número de licencia 
	 * en la interfaz gráfica para el log de errores del proceso SIL.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginaInicioAgrupada(Paginacion_VO pag, String usuario) throws Exception{
		LM_VO lmVOaux;
		int cantidadPaginas=0;
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(LM_DAO.count_LogLMAgrupado(pag.getFechaProceso()));
		pag.setPaginaActual(1);
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		log.info("* * "+cantidadPaginas);
		
		if((pag.getCantidad_BD()%10)>0){
			log.info("* * * * "+cantidadPaginas);
			cantidadPaginas++;
		}
		System.out.println("* * * * "+cantidadPaginas);
		pag.setCantidadPaginas(cantidadPaginas);		
				
		//Obtiene listado trabajado a mostrar en interfaz gráfica.
		//pag.setListaActual(LM_DAO.logErrorLM(pag.getFechaProceso(), cant));
		ArrayList<LM_GlosaErrores_VO> listaGlosa = this.getListaLogLM(pag, usuario,"primera",cant);
		
//		log.info("Mostrando datos listado cargado lm.");
//		for(int i = 0; i < listaGlosa.size(); i++){
//			log.info("=> " + listaGlosa.get(i).getKey());
//		}
		
		//Setea listado a mostrar en interfaz gráfica.
		pag.setListaActual(listaGlosa);
		ArrayList<LM_GlosaErrores_VO> lmList = pag.getListaActual();
		
		if(lmList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getFolio()));
			//Obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getFolio()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}		
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < lmList.size(); i++){
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			lmList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea páginación con modificaciones.
		pag.setListaActual(lmList);		
		return pag;
	}
		
	/**
	 * Obtiene datos de base de datos a traves de consulta en LM_DAO.
	 * @param lmVO
	 * @return
	 */
	public ArrayList<LM_GlosaErrores_VO> getListaLogLM(Paginacion_VO pag, String usuario, String ubicacion, String cantidadElementos){
		try{
			HashMap<String,String> map = new HashMap<String, String>();
			//String cant = conf.getAtributoPaginacion("cantidadPaginacion");
			String cant = cantidadElementos;
			String ide = null;
			boolean keyErrores = false;
			String value = null;
			
			
			//Lista que se mostrará por pantalla.
			ArrayList<LM_GlosaErrores_VO> listaDespliegue = new ArrayList<LM_GlosaErrores_VO>();
			ArrayList<LM_GlosaErrores_VO> listaGlosa = new ArrayList<LM_GlosaErrores_VO>();
			if(ubicacion.equalsIgnoreCase("primera")){
				//Obtiene listado de datos de base de datos a trabajar para proceso SIL.
				listaGlosa = LM_DAO.logErrorLMAgrupado(pag.getFechaProceso(), cant, usuario); 
			}else{
				listaGlosa = LM_DAO.logErrorLMUltimaAgrupado(pag.getFechaProceso(), cant, usuario); 
			}
			
			for(int i = 0; i < listaGlosa.size(); i++){
				if(!(listaGlosa.get(i).getKey().equalsIgnoreCase(ide))){
					//Se agrega elementos a la lista.
					listaDespliegue.add(listaGlosa.get(i));
				}
				//Seteo de variable auxiliar.
				ide = listaGlosa.get(i).getKey();
				
				
				//Se setea datos a HashMap con conjunto errores descritos por número de error como KEY.
				if(listaGlosa.get(i).getIdcampo() != null){
					if(map.get(ide)==null){
						map.put(ide, listaGlosa.get(i).getDescripcion());
						keyErrores = true;
					}else{
						String aux = map.get(ide);
						map.put(ide, "<p>"+aux+"</p>"+"<p>"+listaGlosa.get(i).getDescripcion()+"</p>");
					}
				}
			}
			
			for(int i = 0; i < listaDespliegue.size(); i++){
				//Carga listado de errores en campo descripción al listado a desplegar.
				value = map.get(listaDespliegue.get(i).getKey());
				listaDespliegue.get(i).setDescripcion(value);
			}						
			return listaDespliegue;
			
		}catch(Exception ex){
			log.error("Error, getListaLogSIL()" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
		
	/**
	 * Método que responde al botón SIGUIENTE en interfaz gráfica para log de errores al tener seleccionado 
	 * el checkbox agrupar.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginarAvanceAgrupado(Paginacion_VO pag, String usuario) {
		LM_VO lmVOaux;
		ArrayList<LM_GlosaErrores_VO> listaDespliegue = new ArrayList<LM_GlosaErrores_VO>();
		HashMap<String,String> map=new HashMap<String, String>();
		boolean keyErrores =  false;
		String value = null;
		String ide = null;
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		try{
			//Obtener cantidad de datos a paginar.
			pag.setCantidad_BD(LM_DAO.count_LogLMAgrupado(pag.getFechaProceso()));
			
			if(pag.getCantidad_BD()>0){			
				//Obtenemos numero de paginas.					
				//Consultamos en que pagina estamos.
				if(pag.getPaginaActual()<pag.getCantidadPaginas()){
					
					//obtenemos lista para cargar valores.
					ArrayList<LM_GlosaErrores_VO> lmList = LM_DAO.pagina_AvanzarLogLMAgrupados(pag, usuario); 
					
					for(int i = 0; i < lmList.size(); i++){
						if(!(lmList.get(i).getKey().equalsIgnoreCase(ide))){
							//Se agrega elementos a la lista.
							listaDespliegue.add(lmList.get(i));
						}
						//Seteo de variable auxiliar.
						ide = lmList.get(i).getKey();
						
						
						//Se setea datos a HashMap con conjunto errores descritos por número de error como KEY.
						if(lmList.get(i).getIdcampo() != null){
							if(map.get(ide)==null){
								map.put(ide, lmList.get(i).getDescripcion());
								keyErrores = true;
							}else{
								String aux = map.get(ide);
								map.put(ide, "<p>"+aux+"</p>"+"<p>"+lmList.get(i).getDescripcion()+"</p>");
							}
						}
					}
					
					//Obtenemos el primero de la lista consultada.
					lmVOaux=(LM_VO) lmList.get(0);						
					pag.setPrimeroLista(String.valueOf(lmVOaux.getFolio()));
					
					//Obtenemos ultimo de la lista consultada.
					lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
					pag.setUltimoLista(String.valueOf(lmVOaux.getFolio()));
					
					//Extraemos largo de la lista actual.
					pag.setLargolistaActual(lmList.size());
					
					//Aumentamos pagina en uno.
					pag.setPaginaActual(pag.getPaginaActual()+1);
					pag_actual=pag.getPaginaActual();
					for(int i = 0; i < listaDespliegue.size(); i++){
						//Carga listado de errores en campo descripción al listado a desplegar.
						value = map.get(listaDespliegue.get(i).getKey());
						listaDespliegue.get(i).setDescripcion(value);
						
						//Carga numeración a mostrar por pantalla.
						correlab = ((pag_actual-1)*largo+count);
						listaDespliegue.get(i).setCorrelab(correlab);
						count++;
					}
					
					//Setea listado a mostrar.
					pag.setListaActual(listaDespliegue);
					
				}else if(pag.getPaginaActual()==pag.getCantidadPaginas()){
					//Pag.setListaActual(pag.getListaActual());
					return pag;
				}else{
					
					for(int i = 0; i < listaDespliegue.size(); i++){
						//Carga listado de errores en campo descripción al listado a desplegar.
						value = map.get(listaDespliegue.get(i).getKey());
						listaDespliegue.get(i).setDescripcion(value);
						
						//Carga numeración a mostrar por pantalla.
						correlab = ((pag_actual-1)*largo+count);
						listaDespliegue.get(i).setCorrelab(correlab);
						count++;
					}
					
					pag.setListaActual(new ArrayList<LM_GlosaErrores_VO>());
				}
			}else{
				//No exisaten datos para mostrar a este periodo.
				pag.setListaActual(new ArrayList<LM_GlosaErrores_VO>());
			}
		}catch(Exception ex){
			log.error("Error, paginarAvanceAgrupado (LM) :" + ex.getMessage());
			ex.printStackTrace();
		}
						
		return pag;
	}//end paginarAvance
	
	
	/**
	 * Método que responde al botón ANTERIOR en interfaz gráfica para log de errores al tener seleccionado 
	 * el checkbox agrupar.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginarRetrocesoAgrupado(Paginacion_VO pag, String usuario) {
		LM_VO lmVOaux;
		ArrayList<LM_GlosaErrores_VO> listaDespliegue = new ArrayList<LM_GlosaErrores_VO>();
		HashMap<String,String> map = new HashMap<String, String>();
		boolean keyErrores =  false;
		String value = null;
		String ide = null;
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		try{
			//Obtenemos cantidad de registros BD
			pag.setCantidad_BD(LM_DAO.count_LogLMAgrupado(pag.getFechaProceso()));
			
			log.info("Cantidad de datos a mostrar : " + pag.getCantidad_BD());
			
			if(pag.getCantidad_BD()>0){							
				//Evaluamos pagina actual.
				if(pag.getPaginaActual()>1){
					//Obtenemos lista para cargar valores.
					ArrayList<LM_GlosaErrores_VO> lmList = LM_DAO.pagina_RetrocederLogLMAgrupados(pag, usuario);
					
					for(int i = 0; i < lmList.size(); i++){
						if(!(lmList.get(i).getKey().equalsIgnoreCase(ide))){
							//Se agrega elementos a la lista.
							listaDespliegue.add(lmList.get(i));
						}
						//Seteo de variable auxiliar.
						ide = lmList.get(i).getKey();
						
						
						//Se setea datos a HashMap con conjunto errores descritos por número de error como KEY.
						if(lmList.get(i).getIdcampo() != null){
							if(map.get(ide)==null){
								map.put(ide, lmList.get(i).getDescripcion());
								keyErrores = true;
							}else{
								String aux = map.get(ide);
								map.put(ide, "<p>"+aux+"</p>"+"<p>"+lmList.get(i).getDescripcion()+"</p>");
							}
						}
					}
					
					//Obtenemos el primero de la lista consultada.
					lmVOaux= (LM_VO) lmList.get(0);		
					log.info("Retroceder, primer de la lista :" + lmVOaux.getFolio());
					pag.setPrimeroLista(String.valueOf(lmVOaux.getFolio()));
					
					//Obtenemos ultimo de la lista consultada.
					lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
					log.info("Retroceder, último de la lista :" + lmVOaux.getFolio());
					pag.setUltimoLista(String.valueOf(lmVOaux.getFolio()));
					
					//Extraemos largo de la lista actual.
					pag.setLargolistaActual(lmList.size());				
					
					//Disminuimos pagina en uno.
					pag.setPaginaActual(pag.getPaginaActual()-1);
					pag_actual=pag.getPaginaActual();
					for(int i = 0; i < listaDespliegue.size(); i++){
						//Carga listado de errores en campo descripción al listado a desplegar.
						value = map.get(listaDespliegue.get(i).getKey());
						listaDespliegue.get(i).setDescripcion(value);
						
						//Carga numeración a mostrar por pantalla.
						correlab = ((pag_actual-1)*largo+count);
						listaDespliegue.get(i).setCorrelab(correlab);
						count++;
					}
					
					//Setea listado a mostrar.
					pag.setListaActual(listaDespliegue);
					
					return pag;
				}else if(pag.getPaginaActual()==1){
					log.info("getPaginaActual es '='.");
					return pag;
				}else{
					//Ya estamos en primera pagina asignamos lista vacia. por ultimo retroceso.
					pag.setListaActual(new ArrayList());
				}
			}else{
				//No exisaten datos para mostrar a este periodo.
				pag.setListaActual(new ArrayList());
			}
			
			for(int i = 0; i < listaDespliegue.size(); i++){
				
				//Carga listado de errores en campo descripción al listado a desplegar.
				value = map.get(listaDespliegue.get(i).getKey());
				listaDespliegue.get(i).setDescripcion(value);
				
				//Carga numeración a mostrar por pantalla.
				correlab = ((pag_actual-1)*largo+count);
				listaDespliegue.get(i).setCorrelab(correlab);
				count++;
			}
			
			//Setea listado a mostrar.
			pag.setListaActual(listaDespliegue);
			
			return pag;
		}catch(Exception ex){
			log.error("Error paginarAvanceAgrupado() SIL :" + ex.getMessage());
			ex.printStackTrace();
		}
		return pag;
	}//end paginarRetroceso
	
	public Paginacion_VO paginaUltimaAgrupada(Paginacion_VO pag, String usuario) throws Exception{
		LM_VO lmVOaux;
		int cantidadPaginas=0;
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(LM_DAO.count_LogLMAgrupado(pag.getFechaProceso()));
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		log.info("* * "+cantidadPaginas);
		//se calcula el sobrante para la ultima pagina
		int modRes=pag.getCantidad_BD()%10;
		//se asigna una pagina adicional para el sobrante. y se carga la cantidad de registros a mostrar en la ultima pagina
		if(modRes>0){
			log.info("* * * * "+cantidadPaginas);
			//se sobre escribe cant que es "cantidad de registros por pagina" para mostrar en ultima pagina.
			cant=String.valueOf(modRes);
			cantidadPaginas++; 
		}
		pag.setPaginaActual(cantidadPaginas);
		System.out.println("* * * * "+cantidadPaginas);
		pag.setCantidadPaginas(cantidadPaginas);		
				
		//Obtiene listado trabajado a mostrar en interfaz gráfica.
		//pag.setListaActual(LM_DAO.paginaUltima_logErrorLM(pag.getFechaProceso(), cant));
		ArrayList<LM_GlosaErrores_VO> listaGlosa = this.getListaLogLM(pag, usuario,"ultima",cant);
						
		//Setea listado a mostrar en interfaz gráfica.
		pag.setListaActual(listaGlosa);
		ArrayList<LM_GlosaErrores_VO> lmList = pag.getListaActual();
		
		if(lmList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getFolio()));
			//Obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getFolio()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}		
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < lmList.size(); i++){
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			lmList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea páginación con modificaciones.
		pag.setListaActual(lmList);		
		return pag;
	}
	
	/**
	 * Método para realizar búsqueda por n° licencia o rut trabajador.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<LM_VO> buscarLog_Lista(LM_VO lmVO, Paginacion_VO pag) throws Exception{
		try{
			ArrayList<LM_VO> al=LM_DAO.buscarLog_LM(lmVO);
			int pag_actual = pag.getPaginaActual();
			int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
			int count = 1;
			int correlab = 0;
			
			for(int i = 0; i < al.size(); i++){
				//Carga numeración a mostrar por pantalla.
				correlab = ((pag_actual-1)*largo+count);
				al.get(i).setCorrelab(correlab);
				count++;
			}
			return al;
		}catch(Exception ex){
			log.error("Error en buscarLog_Lista()" + ex.getMessage());
		}
		return null;
	}//end buscarLog.

	/**
	 * Método para actualizar a través de correción de error Log Sil. 
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public LM_VistaErrores_VO actualizar(LM_VO lmVO)throws Exception{
		log.info("* * * * * [MGR: LM, start actualizar]* * * * *");
		
		int vc=1;
		boolean keyErrores=false;
		ArrayList al=new ArrayList();		
		HashMap<String,String> errMap=new HashMap<String, String>();		
		LM_GlosaErrores_VO lmge;		
		/*validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
		log.info("* * * * * [MGR: LM, iterator list]* * * * *");		
		String opcion="validarErrores";
		al=validarLM(lmVO,opcion);		
		Iterator it=al.iterator();
		while(it.hasNext()){
			System.out.println("* * * * * [MGR: LM, while: ]"+(vc++)+"* * * * *");
			lmge=new LM_GlosaErrores_VO();			
			lmge=(LM_GlosaErrores_VO) it.next();
			
			log.info("* * * * * [MGR: LM, idCampo: ]"+(lmge.getIdcampo())+"* * * * *"+
				"\n * * * * * [MGR: LM, : descript]"+(lmge.getDescripcion())+"* * * * *"+"" +
				"\n * * * * * [MGR: LM, errmap: ]"+(errMap.get(lmge.getIdcampo()))+"* * * * *");
			
			//String id_campo = (lmge.getIdcampo()!=0?String.valueOf(lmge.getIdcampo()):null);
			
			if(lmge.getIdcampo()!=null){
				if(errMap.get(lmge.getIdcampo())==null){
					log.info("* * * * * [MGR: LM, while: ]* * * * *");
					errMap.put(lmge.getIdcampo(), lmge.getDescripcion());
					keyErrores=true;
				}else{
					String aux=errMap.get(lmge.getIdcampo());
					errMap.put(lmge.getIdcampo(), aux+"<br>"+lmge.getDescripcion());
				}				
			}			
		}		
		if(keyErrores==false){
			log.info("* * * * * [MGR: LM, SIN errores, actualizando]* * * * *");
			
			/*actualizar en base datos con lmVO*/
			LM_DAO.actualizar(lmVO);
			return new LM_VistaErrores_VO(false,true);
		}else{
			log.info("* * * * * [MGR: LM, CON errores, retornar observaciones]* * * * *");
			LM_VistaErrores_VO lmerr=this.asignaErrores(keyErrores, errMap);
			return lmerr;
		}		 
	}//end actualizar
	
	private LM_VistaErrores_VO asignaErrores(boolean errores,HashMap errMap){
		LM_VistaErrores_VO lmerr=new LM_VistaErrores_VO();
		String campoId="";
		Configuraciones lp=new Configuraciones();
		if(errores){
			lmerr.setKeyErrores(true);
			campoId=lp.getAtributoFormulario_LM("operador");
			if(errMap.get(campoId)!=null){
				lmerr.setOperador((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipoform");
			if(errMap.get(campoId)!=null){
				lmerr.setTipoform((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("folio");
			if(errMap.get(campoId)!=null){
				lmerr.setFolio((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("art77bis");
			if(errMap.get(campoId)!=null){
				lmerr.setArt77bis((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecinform");
			if(errMap.get(campoId)!=null){
				lmerr.setFecinform((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("ruttrabaj");
			if(errMap.get(campoId)!=null){
				lmerr.setRuttrabaj((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecemision");
			if(errMap.get(campoId)!=null){
				lmerr.setFecemision((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecinirepo");
			if(errMap.get(campoId)!=null){
				lmerr.setFecinirepo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecterrepo");
			if(errMap.get(campoId)!=null){
				lmerr.setFecterrepo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("edadtrabaj");
			if(errMap.get(campoId)!=null){
				lmerr.setEdadtrabaj((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_LM("fecnactrab");
			if(errMap.get(campoId)!=null){
				lmerr.setFecnactrab((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("gentrabaj");
			if(errMap.get(campoId)!=null){
				lmerr.setGentrabaj((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("numdiaslic");
			if(errMap.get(campoId)!=null){
				lmerr.setNumdiaslic((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("licmatsupl");
			if(errMap.get(campoId)!=null){
				lmerr.setLicmatsupl((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecnachijo");
			if(errMap.get(campoId)!=null){
				lmerr.setFecnachijo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("ruthijo");
			if(errMap.get(campoId)!=null){
				lmerr.setRuthijo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipolic");
			if(errMap.get(campoId)!=null){
				lmerr.setTipolic((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("recuplabor");
			if(errMap.get(campoId)!=null){
				lmerr.setRecuplabor((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("iniinvalid");
			if(errMap.get(campoId)!=null){
				lmerr.setIniinvalid((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecconcep");
			if(errMap.get(campoId)!=null){
				lmerr.setFecconcep((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_LM("tiporeposo");
			if(errMap.get(campoId)!=null){
				lmerr.setTiporeposo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("jorreposo");
			if(errMap.get(campoId)!=null){
				lmerr.setJorreposo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("lugreposo");
			if(errMap.get(campoId)!=null){
				lmerr.setLugreposo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("especialid");
			if(errMap.get(campoId)!=null){
				lmerr.setEspecialid((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipoprofes");
			if(errMap.get(campoId)!=null){
				lmerr.setTipoprofes((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("rutprofes");
			if(errMap.get(campoId)!=null){
				lmerr.setRutprofes((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("nomprofes");
			if(errMap.get(campoId)!=null){
				lmerr.setNomprofes((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("licmodific");
			if(errMap.get(campoId)!=null){
				lmerr.setLicmodific((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("entautoriz");
			if(errMap.get(campoId)!=null){
				lmerr.setEntautoriz((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipolmresu");
			if(errMap.get(campoId)!=null){
				lmerr.setTipolmresu((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_LM("ndiasincap");
			if(errMap.get(campoId)!=null){
				lmerr.setNdiasincap((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("diagresuel");
			if(errMap.get(campoId)!=null){
				lmerr.setDiagresuel((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("periodo");
			if(errMap.get(campoId)!=null){
				lmerr.setPeriodo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("ndiasprev");
			if(errMap.get(campoId)!=null){
				lmerr.setNdiasprev((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("estadoreso");
			if(errMap.get(campoId)!=null){
				lmerr.setEstadoreso((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tiporesolu");
			if(errMap.get(campoId)!=null){
				lmerr.setTiporesolu((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("redictamen");
			if(errMap.get(campoId)!=null){
				lmerr.setRedictamen((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("causarecha");
			if(errMap.get(campoId)!=null){
				lmerr.setCausarecha((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tiporepoau");
			if(errMap.get(campoId)!=null){
				lmerr.setTiporepoau((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_LM("jorrepoaut");
			if(errMap.get(campoId)!=null){
				lmerr.setJorrepoaut((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("desubsidio");
			if(errMap.get(campoId)!=null){
				lmerr.setDesubsidio((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecreceent");
			if(errMap.get(campoId)!=null){
				lmerr.setFecreceent((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecresoent");
			if(errMap.get(campoId)!=null){
				lmerr.setFecresoent((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("rutcontral");
			if(errMap.get(campoId)!=null){
				lmerr.setRutcontral((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("rutemplead");
			if(errMap.get(campoId)!=null){
				lmerr.setRutemplead((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecreceemp");
			if(errMap.get(campoId)!=null){
				lmerr.setFecreceemp((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("regionempl");
			if(errMap.get(campoId)!=null){
				lmerr.setRegionempl((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("comunaempl");
			if(errMap.get(campoId)!=null){
				lmerr.setComunaempl((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("actlabtrab");
			if(errMap.get(campoId)!=null){
				lmerr.setActlabtrab((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("ocupactrab");
			if(errMap.get(campoId)!=null){
				lmerr.setOcupactrab((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecrecepag");
			if(errMap.get(campoId)!=null){
				lmerr.setFecrecepag((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipregprev");
			if(errMap.get(campoId)!=null){
				lmerr.setTipregprev((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("calitrabaj");
			if(errMap.get(campoId)!=null){
				lmerr.setCalitrabaj((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("tipoentpag");
			if(errMap.get(campoId)!=null){
				lmerr.setTipoentpag((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("fecpriafil");
			if(errMap.get(campoId)!=null){
				lmerr.setFecpriafil((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_LM("feccontrab");
			if(errMap.get(campoId)!=null){
				lmerr.setFeccontrab((String)errMap.get(campoId));
			}
		}		
		return lmerr;
	}//end asignaErrores
	
	/**funcion que aplica reglas de validacion mediante ejecucion de CL,
	 * inserta en BD temporal y
	 * envia parametros mediante hashMap a cl validacion.
	 * @throws Exception 
	 * 
	 **/
	private ArrayList validarLM(LM_VO lmVO,String opcion) throws Exception{
		ArrayList al=new ArrayList();
		if(opcion.equalsIgnoreCase("mostrarErrores")){
			al=LM_DAO.obtenerErroresLM(lmVO,opcion);
		}else if(opcion.equalsIgnoreCase("validarErrores")){
			//eliminacion de registros temporales:
			LM_DAO.eliminar_T(lmVO);
			//insertar registro temporal
			LM_DAO.insertar_LM_T(lmVO);
			//ejecutar cl validacion expres
			ILCLM051_TDAO.callILCLM051_T(String.valueOf(lmVO.getFecproceso()), "USUARIO_JAVA","T");
			//consultar log errores. retornara objeto compuesto resultado de inner
			//una lista del tipo glosa_error[valor consultado mas errorDescrip mas id campo]
			al=LM_DAO.obtenerErroresLM(lmVO,opcion);
		}
		return al;
	}//end validarLM
		
	/**
	 * retornara true si el objeto buscado existe
	 **/
	private boolean getExistencia(LM_VO lmVO) throws Exception{
		boolean key=false;
		ArrayList temp=null;
		temp=LM_DAO.buscar(lmVO);
		//se solicito actualizar o eliminar, se buscara si existe una vez,
		if(temp!=null){
			if(temp.size()==1){
				key=true;
			}
		}				
		return key;
	}

	/**
	 * Método para actualizar a través de correción de error Log Sil. 
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public LM_VistaErrores_VO actualizarLog_LM(LM_VO lmVO, HashMap<String,String> map)
		throws Exception{
		log.info("* * * * * [MGR: LM, start actualizar]* * * * *");
		
		LM_VistaErrores_VO lmerr = null;
		int vc=1;
		boolean keyErrores=false;
		ArrayList<LM_GlosaErrores_VO> al=new ArrayList<LM_GlosaErrores_VO>();		
		HashMap<String,String> errMap=new HashMap<String, String>();	
		
		//Actualización de registro.
		log.info("Entro en UtilProcesar.actualizarRegistro.");
		int opc = UtilProcesar.actualizarRegistroLM(lmVO, map);
		
		if(opc == 3){
			lmerr = new LM_VistaErrores_VO();
		}else{
			String opcion="validarErrores";
			lmerr = this.marcarErrores(lmVO,opcion);
			
		}
//			/* Validamos y obtenemos formato registro mas descripcion error lista 
//			 * de todos los que contengan errores*/
//			System.out.println("* * * * * [MGR: LM, FOR list]* * * * *");				
//			al = validarLM(lmVO);		
//			
//			for(LM_GlosaErrores_VO lmge: al){
//				if(lmge.getIdcampo()!=null){
//					if(errMap.get(lmge.getIdcampo())==null){
//						System.out.println("* * * * * [MGR: LM, while: ]* * * * *");
//						errMap.put(lmge.getIdcampo(), lmge.getDescripcion());
//						keyErrores=true;
//					}else{
//						String aux=errMap.get(lmge.getIdcampo());
//						errMap.put(lmge.getIdcampo(), aux+"<br>"+lmge.getDescripcion());
//					}				
//				}	
//			}	
//			
//			System.out.println("* * * * * [MGR: LM, CON errores, retornar observaciones]* * * * *");
//			lmerr=this.asignaErrores(keyErrores, errMap);
//		}
		System.out.println("Seteo respuesta método para actualizar registro.");
		lmerr.setKeyEstado(opc);
		
		return lmerr;
	}//end actualizar
	
	
	/**
	 * Método para marcar errores y desplegarlos por pantalla en formulario.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public LM_VistaErrores_VO marcarErrores(LM_VO lmVO,String opcion)throws Exception{
		LM_VistaErrores_VO lmerr = new LM_VistaErrores_VO();
		ArrayList<LM_GlosaErrores_VO> al=new ArrayList<LM_GlosaErrores_VO>();
		HashMap<String,String> errMap=new HashMap<String, String>();
		boolean keyErrores=false;
		
		/* Validamos y obtenemos formato registro mas descripcion error lista 
		 * de todos los que contengan errores*/
		log.info("* * * * * [MGR: LM, FOR list]* * * * *");
		//String opcion="marcarErrores";
		al = validarLM(lmVO,opcion);		
		
		for(LM_GlosaErrores_VO lmge: al){
			if(lmge.getIdcampo()!=null){
				if(errMap.get(lmge.getIdcampo())==null){
					log.info("* * * * * [MGR: LM, while: ]* * * * *");
					errMap.put(lmge.getIdcampo(), lmge.getDescripcion());
					keyErrores=true;				
				}else{
					String aux=errMap.get(lmge.getIdcampo());
					errMap.put(lmge.getIdcampo(), aux+"<br>"+lmge.getDescripcion());
				}				
			}	
		}	
		
		log.info("* * * * * [MGR: LM, CON errores, retornar observaciones]* * * * *");
		lmerr=this.asignaErrores(keyErrores, errMap);
		
		return lmerr;
	}
}


