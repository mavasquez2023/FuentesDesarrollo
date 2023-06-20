package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.ILCSIL051_TDAO;
import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.util.UtilProcesar;
import cl.laaraucana.silmsil.vo.LM_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;
import cl.laaraucana.silmsil.vo.SIL_VistaErrores_VO;

public class LogSIL_Manager {

	private Logger log = Logger.getLogger(this.getClass());
	Configuraciones conf=new Configuraciones();
		
	/**
	 *  Método para desplegar una lista con los errores para el log de errores del proceso SIL.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginaInicio(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		
		log.info("Cálcula paginación con fecha = " + pag.getFechaProceso());
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(SIL_DAO.count_LogSIL(pag.getFechaProceso()));
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
		ArrayList<SIL_GlosaErrores_VO> silList = SIL_DAO.logErrorSil(pag.getFechaProceso(), cant);
		
		log.info("Tamaño lista = " + silList.size());
		if(silList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelab()));
			//Obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getCorrelab()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}			
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0; 
		
		for(int i = 0; i < silList.size(); i++){
			String descrip = silList.get(i).getDescripcion();
			
			String partes[] = descrip.split(",");
			
			if(partes[0].equalsIgnoreCase("Calidad del trabajador incorrecta")){
				descrip = "<p>Calidad del trabajador incorrecta,</p>" +
						"<p>debe estar en el rango 1-4.</p>";
				
				silList.get(i).setDescripcion(descrip);
			}
						
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			silList.get(i).setCorrelab(correlab);
			count++;
		}
		
		//Setea listado.
		pag.setListaActual(silList);
		
		return pag;
	}
	
	/**
	 * Método que responde al botón SIGUIENTE en interfaz gráfica para log de errores.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginarAvance(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		
		//Obtener cantidad de datos a paginar.
		pag.setCantidad_BD(SIL_DAO.count_LogSIL(pag.getFechaProceso()));
		
		if(pag.getCantidad_BD()>0){			
			//Obtenemos numero de paginas.					
			//Consultamos en que pagina estamos.
			if(pag.getPaginaActual()<pag.getCantidadPaginas()){
				ArrayList<SIL_VO> silList = SIL_DAO.pagina_AvanzarLogSil(pag);
				
				//Obtenemos el primero de la lista consultada.
				silVOaux=(SIL_VO) silList.get(0);						
				pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelab()));
				
				//Obtenemos ultimo de la lista consultada.
				silVOaux=(SIL_VO) silList.get((silList.size()-1));
				pag.setUltimoLista(String.valueOf(silVOaux.getCorrelab()));
				
				//Extraemos largo de la lista actual.
				pag.setLargolistaActual(silList.size());
				
				//Aumentamos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()+1);
				
				int pag_actual = pag.getPaginaActual();
				int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
				int count = 1;
				int correlab = 0;
				
				for(int i = 0; i < silList.size(); i++){
					//Carga numeración a mostrar por pantalla.
					correlab = ((pag_actual-1)*largo+count);
					silList.get(i).setCorrelab(correlab);
					count++;
				}
				
				pag.setListaActual(silList);
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
		SIL_VO silVOaux;
		//Obtenemos cantidad de registros BD
		pag.setCantidad_BD(SIL_DAO.count_LogSIL(pag.getFechaProceso()));
		
		log.info("Cantidad de datos a mostrar : " + pag.getCantidad_BD());
		
		if(pag.getCantidad_BD()>0){							
			//Evaluamos pagina actual.
			if(pag.getPaginaActual()>1){
				ArrayList<SIL_VO> silList = SIL_DAO.pagina_RetrocederLogSil(pag);
				
				//Obtenemos el primero de la lista consultada.
				silVOaux=(SIL_VO) silList.get(0);						
				pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelab()));
				
				//Obtenemos ultimo de la lista consultada.
				silVOaux=(SIL_VO) silList.get((silList.size()-1));
				pag.setUltimoLista(String.valueOf(silVOaux.getCorrelab()));
				
				//Extraemos largo de la lista actual.
				pag.setLargolistaActual(silList.size());				
				
				//Diminuimos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()-1);
				
				int pag_actual = pag.getPaginaActual();
				int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
				int count = 1;
				int correlab = 0;
				
				for(int i = 0; i < silList.size(); i++){
					//Carga numeración a mostrar por pantalla.
					correlab = ((pag_actual-1)*largo+count);
					silList.get(i).setCorrelab(correlab);
					count++;
				}
				
				pag.setListaActual(silList);
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
	
	public Paginacion_VO paginarUltimo_LogSIL(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		
		log.info("Cálcula paginación con fecha = " + pag.getFechaProceso());
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(SIL_DAO.count_LogSIL(pag.getFechaProceso()));
		
		log.info("Cantidad de páginas traidas de la BD = " + pag.getCantidad_BD());		
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		log.info("* * "+cantidadPaginas);
		int modRes=pag.getCantidad_BD()%10;
		if(modRes>0){
			log.info("* * * * "+cantidadPaginas);
			cant=String.valueOf(modRes);
			cantidadPaginas++; 
		}
		pag.setPaginaActual(cantidadPaginas);
		log.info("* * * * "+cantidadPaginas);
		pag.setCantidadPaginas(cantidadPaginas);		
		
		log.info("Obtiene y setea listado.");
		//Obtiene y setea listado a mostrar en interfaz gráfica.
		ArrayList<SIL_GlosaErrores_VO> silList = SIL_DAO.paginaUltima_logErrorSil(pag.getFechaProceso(), cant);
		
		log.info("Tamaño lista = " + silList.size());
		if(silList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelab()));
			//Obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getCorrelab()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}			
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0; 
		
		for(int i = 0; i < silList.size(); i++){
			String descrip = silList.get(i).getDescripcion();
			
			String partes[] = descrip.split(",");
			
			if(partes[0].equalsIgnoreCase("Calidad del trabajador incorrecta")){
				descrip = "<p>Calidad del trabajador incorrecta,</p>" +
						"<p>debe estar en el rango 1-4.</p>";
				
				silList.get(i).setDescripcion(descrip);
			}
						
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			silList.get(i).setCorrelab(correlab);
			count++;
		}
		
		//Setea listado.
		pag.setListaActual(silList);
		
		return pag;
	}
	
	
	/*START AGRUPACION*/
	
	/**
	 * Método para desplegar una lista con los errores agrupados por el número de licencia 
	 * en la interfaz gráfica para el log de errores del proceso SIL.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public Paginacion_VO paginaInicioAgrupada(Paginacion_VO pag, String usuario) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
		
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(SIL_DAO.count_LogSILAgrupado(pag.getFechaProceso()));
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		pag.setPaginaActual(1);
		log.info("* * "+cantidadPaginas);
		
		if((pag.getCantidad_BD()%10)>0){
			cantidadPaginas++;
		}
		log.info("* * * * cant: "+cant);
		pag.setCantidadPaginas(cantidadPaginas);		
				
		//Obtiene listado trabajado a mostrar en interfaz gráfica.
		//pag.setListaActual(SIL_DAO.logErrorSil(pag.getFechaProceso(), cant));
		ArrayList<SIL_GlosaErrores_VO> listaGlosa = this.getListaLogSIL(pag, usuario,"primera",cant);
		
//		log.info("Mostrando datos listado cargado sil.");
//		
//		for(int i = 0; i < listaGlosa.size(); i++){
//			log.info("=> " + listaGlosa.get(i).getKey());
//		}
//		
		//Setea listado a mostrar en interfaz gráfica.
		pag.setListaActual(listaGlosa);
		ArrayList<SIL_GlosaErrores_VO> silList = pag.getListaActual();
		
		if(silList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getNrofol()));
			//Obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getNrofol()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}	
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < silList.size(); i++){
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			silList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea páginación con modificaciones.
		pag.setListaActual(silList);
		return pag;
	}
	
	/**
	 * Obtiene datos de base de datos a traves de consulta en SIL_DAO.
	 * @param silVO
	 * @return
	 */
	public ArrayList<SIL_GlosaErrores_VO> getListaLogSIL(Paginacion_VO pag, String usuario,String ubicacion, String cantidadElementos){
		try{
			HashMap<String,String> map=new HashMap<String, String>();
			//String cant = conf.getAtributoPaginacion("cantidadPaginacion");
			String cant = cantidadElementos;
			String ide = null;
			String value = null;
			boolean keyErrores = false;
			
			//Lista que se mostrará por pantalla.
			ArrayList<SIL_GlosaErrores_VO> listaDespliegue = new ArrayList<SIL_GlosaErrores_VO>();
			
			//Obtiene listado de datos de base de datos a trabajar para proceso SIL.
			ArrayList<SIL_GlosaErrores_VO> listaGlosa=new ArrayList<SIL_GlosaErrores_VO>();
			if(ubicacion.equalsIgnoreCase("primera")){
				listaGlosa = SIL_DAO.logErrorSilAgrupado(pag.getFechaProceso(), usuario, cant);
			}else{
				listaGlosa = SIL_DAO.logErrorSilUltimaAgrupado(pag.getFechaProceso(), usuario, cant);
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
	public Paginacion_VO paginarAvanceAgrupado(Paginacion_VO pag, String usuario){
		SIL_VO silVOaux;
		ArrayList<SIL_GlosaErrores_VO> listaDespliegue = new ArrayList<SIL_GlosaErrores_VO>();
		HashMap<String,String> map=new HashMap<String, String>();
		boolean keyErrores =  false;
		String value = null;
		String ide = null;
		
		int pag_actual = 0;//pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		try{
			//Obtener cantidad de datos a paginar.
			pag.setCantidad_BD(SIL_DAO.count_LogSILAgrupado(pag.getFechaProceso()));
			
			log.info("Número de páginas totales :" + pag.getCantidad_BD());
			
			if(pag.getCantidad_BD()>0){			
				//Obtenemos número de páginas.					
				//Consultamos en que pagina estamos.
				if(pag.getPaginaActual()<pag.getCantidadPaginas()){
					
					//Obtenemos lista para cargar valores.
					ArrayList<SIL_GlosaErrores_VO> silList = SIL_DAO.pagina_AvanzarLogSilAgrupados(pag, usuario);
					
					for(int i = 0; i < silList.size(); i++){
						if(!(silList.get(i).getKey().equalsIgnoreCase(ide))){
							//Se agrega elementos a la lista.
							listaDespliegue.add(silList.get(i));
						}
						//Seteo de variable auxiliar.
						ide = silList.get(i).getKey();
						
						
						//Se setea datos a HashMap con conjunto errores descritos por número de error como KEY.
						if(silList.get(i).getIdcampo() != null){
							if(map.get(ide)==null){
								map.put(ide, silList.get(i).getDescripcion());
								keyErrores = true;
							}else{
								String aux = map.get(ide);
								map.put(ide, "<p>"+aux+"</p>"+"<p>"+silList.get(i).getDescripcion()+"</p>");
							}
						}
					}
					
					//Obtenemos el primero elemento de la lista consultada.
					silVOaux=(SIL_VO) silList.get(0);		
					log.info("Primero = " + silVOaux.getNrofol());
					pag.setPrimeroLista(String.valueOf(silVOaux.getNrofol()));
					
					//Obtenemos último de la lista consultada.
					silVOaux=(SIL_VO) silList.get((silList.size()-1));
					log.info("Último = " + silVOaux.getNrofol());
					pag.setUltimoLista(String.valueOf(silVOaux.getNrofol()));
					
					//Extraemos largo de la lista actual.
					pag.setLargolistaActual(silList.size());
					
					//Aumentamos página en uno.
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
					log.info("Entro al == para avanzar");
					
					return pag;
				}else{
					log.info("Entro al > para avanzar");
					
					for(int i = 0; i < listaDespliegue.size(); i++){
						//Carga listado de errores en campo descripción al listado a desplegar.
						value = map.get(listaDespliegue.get(i).getKey());
						listaDespliegue.get(i).setDescripcion(value);
						
						//Carga numeración a mostrar por pantalla.
						correlab = ((pag_actual-1)*largo+count);
						listaDespliegue.get(i).setCorrelab(correlab);
						count++;
					}
					
					pag.setListaActual(new ArrayList<SIL_GlosaErrores_VO>());
				}
			}
			else{
				//No existen datos para mostrar a este período.
				pag.setListaActual(new ArrayList<SIL_GlosaErrores_VO>());
			}
			return pag;
		}catch(Exception ex){
			log.error("Error paginarAvanceAgrupado() SIL :" + ex.getMessage());
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
	public Paginacion_VO paginarRetrocesoAgrupado(Paginacion_VO pag, String usuario) throws Exception{
		SIL_VO silVOaux;
		ArrayList<SIL_GlosaErrores_VO> listaDespliegue = new ArrayList<SIL_GlosaErrores_VO>();
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
			pag.setCantidad_BD(SIL_DAO.count_LogSILAgrupado(pag.getFechaProceso()));
			
			log.info("Cantidad de datos a mostrar : " + pag.getCantidad_BD());
			
			if(pag.getCantidad_BD()>0){							
				//Evaluamos página actual.
				if(pag.getPaginaActual()>1){
					//Obtenemos lista para cargar valores.
					ArrayList<SIL_GlosaErrores_VO> silList = SIL_DAO.pagina_RetrocederLogSilAgrupados(pag, usuario);
					
					for(int i = 0; i < silList.size(); i++){
						if(!(silList.get(i).getKey().equalsIgnoreCase(ide))){
							//Se agrega elementos a la lista.
							listaDespliegue.add(silList.get(i));
						}
						//Seteo de variable auxiliar.
						ide = silList.get(i).getKey();
						
						//Se setea datos a HashMap con conjunto errores descritos por número de error como KEY.
						if(silList.get(i).getIdcampo() != null){
							if(map.get(ide)==null){
								map.put(ide, silList.get(i).getDescripcion());
								keyErrores = true;
							}else{
								String aux = map.get(ide);
								map.put(ide, "<p>"+aux+"</p>"+"<p>"+silList.get(i).getDescripcion()+"</p>");
							}
						}
					}
					
					//Obtenemos el primero de la lista consultada.
					silVOaux=(SIL_VO) silList.get(0);		
					log.info("Retroceder, primer de la lista :" + silVOaux.getNrofol());
					pag.setPrimeroLista(String.valueOf(silVOaux.getNrofol()));
					
					//Obtenemos último de la lista consultada.
					silVOaux=(SIL_VO) silList.get((silList.size()-1));
					log.info("Retroceder, último de la lista :" + silVOaux.getNrofol());
					pag.setUltimoLista(String.valueOf(silVOaux.getNrofol()));
					
					//Extraemos largo de la lista actual.
					pag.setLargolistaActual(silList.size());				
					
					//Disminuimos página en uno.
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
					log.info("getPaginaActual =");
					return pag;
				}else{
					log.info("getPaginaActual <");
					pag.setListaActual(new ArrayList());
				}
			}else{
				//No existen datos para mostrar a este período.
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
			log.error("Error paginarRetrocesoAgrupado() SIL :" + ex.getMessage());
			ex.printStackTrace();
		}
		return pag;
	}//end paginarRetroceso
	
	public Paginacion_VO paginaUltimaAgrupada(Paginacion_VO pag, String usuario) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
		
		//Cálcula cantidad de páginas que serán al inicio.
		pag.setCantidad_BD(SIL_DAO.count_LogSILAgrupado(pag.getFechaProceso()));
		
		String cant = conf.getAtributoPaginacion("cantidadPaginacion");
		cantidadPaginas = pag.getCantidad_BD()/Integer.parseInt(cant);
		
		log.info("* * "+cantidadPaginas);
		int modRes=pag.getCantidad_BD()%10;
		//se asigna una pagina adicional para el sobrante. y se carga la cantidad de registros a mostrar en la ultima pagina
		if(modRes>0){			
			//se sobre escribe cant que es "cantidad de registros por pagina" para mostrar en ultima pagina.
			cant=String.valueOf(modRes);
			cantidadPaginas++;
		}
		pag.setPaginaActual(cantidadPaginas);
		log.info("* * * * cant: "+cant);
		pag.setCantidadPaginas(cantidadPaginas);		
				
		//Obtiene listado trabajado a mostrar en interfaz gráfica.
		//pag.setListaActual(SIL_DAO.logErrorSil(pag.getFechaProceso(), cant));
		ArrayList<SIL_GlosaErrores_VO> listaGlosa = this.getListaLogSIL(pag, usuario,"ultima",cant);
		
//		log.info("Mostrando datos listado cargado sil.");
//		
//		for(int i = 0; i < listaGlosa.size(); i++){
//			log.info("=> " + listaGlosa.get(i).getKey());
//		}
//		
		//Setea listado a mostrar en interfaz gráfica.
		pag.setListaActual(listaGlosa);
		ArrayList<SIL_GlosaErrores_VO> silList = pag.getListaActual();
		
		if(silList.size()>0){			
			//Obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getNrofol()));
			//Obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getNrofol()));
			//Extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}	
		
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < silList.size(); i++){
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			silList.get(i).setCorrelab(correlab);
			count++;
		}		
		//Setea páginación con modificaciones.
		pag.setListaActual(silList);
		return pag;
	}
		
	/*FIN AGRUPACION*/	
	
	/**
	 * Método para realizar búsqueda por n° licencia o rut trabajador.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SIL_GlosaErrores_VO> buscarLog_Lista(SIL_GlosaErrores_VO silVO, Paginacion_VO pag){
		try{
			ArrayList<SIL_GlosaErrores_VO> al = SIL_DAO.buscarLog(silVO);
			
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
			ex.printStackTrace();
		}
		return null;
	}//end buscarLog.
	
	/**
	 * Método para actualizar a través de correción de error Log Sil. 
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public SIL_VistaErrores_VO actualizar(SIL_VO silVO)throws Exception{
		
		int vc = 1;
		boolean keyErrores = false;
		ArrayList<SIL_GlosaErrores_VO> al = new ArrayList<SIL_GlosaErrores_VO>();		
		HashMap<String,String> errMap = new HashMap<String, String>();		
		
		/*Validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
		log.info("* * * * * [MGR: SIL, iterator list]* * * * *");
		String opcion="validarErrores";
		al = this.validarSIL(silVO,opcion);	
		
		for(SIL_GlosaErrores_VO silge: al){
			if(silge.getIdcampo()!=null){
				if(errMap.get(silge.getIdcampo())==null){
					log.info("* * * * * [MGR: SIL, while: ]* * * * *");
					errMap.put(silge.getIdcampo(), silge.getDescripcion());
					keyErrores=true;
				}else{
					String aux=errMap.get(silge.getIdcampo());
					errMap.put(silge.getIdcampo(), aux+"<br>"+silge.getDescripcion());
				}				
			}			
		}		
		if(keyErrores==false){
			log.info("* * * * * [MGR: SIL, SIN errores, actualizando]* * * * *");
			
			/*actualizar en base datos con lmVO*/
			SIL_DAO.actualizar(silVO);
			return new SIL_VistaErrores_VO(false,true);
		}else{
			log.info("* * * * * [MGR: SIL, CON errores, retornar observaciones]* * * * *");
			SIL_VistaErrores_VO silerr = this.asignaErrores(keyErrores, errMap);
			return silerr;
		}		 
	}//end actualizar
	
	/**
	 * Método de validación rápida para proceso SIL.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	private ArrayList<SIL_GlosaErrores_VO> validarSIL(SIL_VO silVO,String opcion) throws Exception{
		ArrayList<SIL_GlosaErrores_VO> al=new ArrayList<SIL_GlosaErrores_VO>();
		
		if(opcion.equalsIgnoreCase("mostrarErrores")){
			al = SIL_DAO.obtenerErroresSIL(silVO,opcion);
		}else if(opcion.equalsIgnoreCase("validarErrores")){
			//Eliminación de registros temporales.
			SIL_DAO.eliminar_T(silVO);
			//Insertar registro temporal.
			SIL_DAO.insertar_SIL_T(silVO);
			//Ejecutar cl validación express.
			ILCSIL051_TDAO.callILCSIL051_T(String.valueOf(silVO.getPerpag()), "USUARIO_JAVA","T");
			/*Consultar log errores. Retorna objeto compuesto, resultado de inner join
				una lista del tipo glosa_error[valor consultado mas errorDescrip mas id campo]*/			
			al = SIL_DAO.obtenerErroresSIL(silVO,opcion);
		}
		return al;
	}//end validarSIL
	
	/**
	 * Método para asignar errores al formulario SIL ha corregir.
	 * @param errores
	 * @param errMap
	 * @return
	 */
	private SIL_VistaErrores_VO asignaErrores(boolean errores,HashMap errMap){
		SIL_VistaErrores_VO silerr=new SIL_VistaErrores_VO();
		String campoId="";
		Configuraciones lp=new Configuraciones();
		if(errores){
			silerr.setKeyErrores(true);
			
			campoId=lp.getAtributoFormulario_SIL("lichasfec");
			if(errMap.get(campoId)!=null){
				silerr.setLichasfec((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("pagfol");
			if(errMap.get(campoId)!=null){
				silerr.setPagfol((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("codope");
			if(errMap.get(campoId)!=null){
				silerr.setCodope((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("tpofor");
			if(errMap.get(campoId)!=null){
				silerr.setTpofor((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("nrofol");
			if(errMap.get(campoId)!=null){
				silerr.setNrofol((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ruttrabaj");
			if(errMap.get(campoId)!=null){
				silerr.setRuttrabaj((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("fecemi");
			if(errMap.get(campoId)!=null){
				silerr.setFecemi((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("diasub");
			if(errMap.get(campoId)!=null){
				silerr.setDiasub((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("mtoliq");
			if(errMap.get(campoId)!=null){
				silerr.setMtoliq((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("mtocot");
			if(errMap.get(campoId)!=null){
				silerr.setMtocot((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("codint");
			if(errMap.get(campoId)!=null){
				silerr.setCodint((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("finipa");
			if(errMap.get(campoId)!=null){
				silerr.setFinipa((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_SIL("mocope");
			if(errMap.get(campoId)!=null){
				silerr.setMocope((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("baseca");
			if(errMap.get(campoId)!=null){
				silerr.setBaseca((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("idlice");
			if(errMap.get(campoId)!=null){
				silerr.setIdlice((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("inimes");
			if(errMap.get(campoId)!=null){
				silerr.setInimes((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("tpolic");
			if(errMap.get(campoId)!=null){
				silerr.setTpolic((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ndicot");
			if(errMap.get(campoId)!=null){
				silerr.setNdicot((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ndiinc");
			if(errMap.get(campoId)!=null){
				silerr.setNdiinc((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ndipag");
			if(errMap.get(campoId)!=null){
				silerr.setNdipag((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("mtsbpa");
			if(errMap.get(campoId)!=null){
				silerr.setMtsbpa((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("mtsbdi");
			if(errMap.get(campoId)!=null){
				silerr.setMtsbdi((String)errMap.get(campoId));
			}
			
			campoId=lp.getAtributoFormulario_SIL("mcsegc");
			if(errMap.get(campoId)!=null){
				silerr.setMcsegc((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("motcot");
			if(errMap.get(campoId)!=null){
				silerr.setMotcot((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ofipgo");
			if(errMap.get(campoId)!=null){
				silerr.setOfipgo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("ccopgo");
			if(errMap.get(campoId)!=null){
				silerr.setCcopgo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("inssal");
			if(errMap.get(campoId)!=null){
				silerr.setInssal((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("submat");
			if(errMap.get(campoId)!=null){
				silerr.setSubmat((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("tpoliq");
			if(errMap.get(campoId)!=null){
				silerr.setTpoliq((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("fecpgo");
			if(errMap.get(campoId)!=null){
				silerr.setFecpgo((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("mliqpa");
			if(errMap.get(campoId)!=null){
				silerr.setMliqpa((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("rimpms");
			if(errMap.get(campoId)!=null){
				silerr.setRimpms((String)errMap.get(campoId));
			}
		}		
		return silerr;
	}//end asignaErrores
	
	/**
	 * Método para actualizar lOG a través de correción de error Log Sil. 
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public SIL_VistaErrores_VO actualizarLog_SIL(SIL_VO silVO, HashMap<String,String> map)
		throws Exception{
		
		SIL_VistaErrores_VO silerr = null;
		int vc = 1;
		boolean keyErrores = false;
		ArrayList<SIL_GlosaErrores_VO> al = new ArrayList<SIL_GlosaErrores_VO>();		
		HashMap<String,String> errMap = new HashMap<String, String>();		
		
		//Actualización de registro.
		log.info("Entro en UtilProcesar.actualizarRegistro.");
		int opc = UtilProcesar.actualizarRegistroSIL(silVO, map);
		
		if(opc == 3){
			silerr = new SIL_VistaErrores_VO();	
		}else{
			String opcion="validarErrores";
			silerr = this.marcarErrores(silVO,opcion);			
		}
		
		//Seteo respuesta método para actualizar registro.
		log.info("Seteo respuesta método para actualizar registro.");
		silerr.setKeyEstado(opc);
		
		return silerr;
	}//end actualizar
	
	/**
	 * Método para marcar errores y desplegarlos por pantalla en formulario.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public SIL_VistaErrores_VO marcarErrores(SIL_VO silVO,String opcion)throws Exception{
		SIL_VistaErrores_VO silerr = new SIL_VistaErrores_VO();
		boolean keyErrores = false;
		ArrayList<SIL_GlosaErrores_VO> al = new ArrayList<SIL_GlosaErrores_VO>();		
		HashMap<String,String> errMap = new HashMap<String, String>();		
		
		//Validar datos ingresados.
		al = this.validarSIL(silVO,opcion);
		
		//Agrupar errores.
		for(SIL_GlosaErrores_VO silge: al){
			if(silge.getIdcampo()!=null){
				if(errMap.get(silge.getIdcampo())==null){
					log.info("* * * * * [MGR: SIL, while: ]* * * * *");
					errMap.put(silge.getIdcampo(), silge.getDescripcion());
					keyErrores=true;
				}else{
					String aux=errMap.get(silge.getIdcampo());
					errMap.put(silge.getIdcampo(), aux+"<br>"+silge.getDescripcion());
				}				
			}		
		}
		//Asigna errores.
		log.info("* * * * * [MGR: SIL, CON errores, retornar observaciones]* * * * *");
		silerr = this.asignaErrores(keyErrores, errMap);
		
		return silerr;
	}
	
}//end logSil_Manager
