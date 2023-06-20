package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import cl.laaraucana.silmsil.dao.ILCSIL051_TDAO;
import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.SIL_VistaErrores_VO;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;

/**
 * manager LM, comprende logica de mantenedor lm
 * para tabla ILFSIL050.
 *
 */
public class SIL_Manager {
	
	private Logger log = Logger.getLogger(this.getClass());
	Configuraciones conf=new Configuraciones();
	
	public Paginacion_VO paginaInicio(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
				
		pag.setCantidad_BD(SIL_DAO.count_SIL(pag.getFechaProceso()));
		pag.setPaginaActual(1);		
		String regPorPaginas=conf.getAtributoPaginacion("cantidadPaginacion");
		cantidadPaginas=pag.getCantidad_BD()/Integer.parseInt(regPorPaginas);
		pag.setRegistrosPorPagina(Integer.parseInt(regPorPaginas));
		log.info("* * "+cantidadPaginas);
		
		if((pag.getCantidad_BD()%10)>0){
			log.info("* * * * "+cantidadPaginas);
			cantidadPaginas++;
		}
		log.info("* * * * "+cantidadPaginas);
		
		pag.setCantidadPaginas(cantidadPaginas);		
		pag.setListaActual(SIL_DAO.pagina_Inicio(pag));
		ArrayList silList=pag.getListaActual();
		if(silList.size()>0){			
			//obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelativ()));
			//obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getCorrelativ()));
			//extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}
		return pag;
	}
	
	public Paginacion_VO paginarAvance(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		//cargamos cantidad a mostrar.
		String regPorPaginas=conf.getAtributoPaginacion("cantidadPaginacion");
		pag.setRegistrosPorPagina(Integer.parseInt(regPorPaginas));
		pag.setCantidad_BD(SIL_DAO.count_SIL(pag.getFechaProceso()));
		
		if(pag.getCantidad_BD()>0){			
			//obtenemos numero de paginas.					
			//consultamos en que pagina estamos.
			if(pag.getPaginaActual()<pag.getCantidadPaginas()){
				
				//traemos lista
				pag.setListaActual(SIL_DAO.pagina_Avanzar(pag));
				//obtenemos lista para cargar valores.
				ArrayList silList=pag.getListaActual();
				//obtenemos el primero de la lista consultada.
				silVOaux=(SIL_VO) silList.get(0);						
				pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelativ()));
				//obtenemos ultimo de la lista consultada.
				silVOaux=(SIL_VO) silList.get((silList.size()-1));
				pag.setUltimoLista(String.valueOf(silVOaux.getCorrelativ()));
				//extraemos largo de la lista actual.
				pag.setLargolistaActual(silList.size());
				
				//aumentamos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()+1);
			}else if(pag.getPaginaActual()==pag.getCantidadPaginas()){
				//pag.setListaActual(pag.getListaActual());
				return pag;
			}else{
				//ya estamos en ultima pagina asignamos lista vacia.
				pag.setListaActual(new ArrayList());
			}
		}else{
			//no exisaten datos para mostrar a este periodo.
			pag.setListaActual(new ArrayList());
		}				
		return pag;
	}//end paginarAvance
	
	public Paginacion_VO paginarRetroceso(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		//cargamos cantidad a mostrar.
		String regPorPaginas=conf.getAtributoPaginacion("cantidadPaginacion");
		pag.setRegistrosPorPagina(Integer.parseInt(regPorPaginas));
		//obtenemos cantidad de registros BD
		pag.setCantidad_BD(SIL_DAO.count_SIL(pag.getFechaProceso()));
		if(pag.getCantidad_BD()>0){							
			//evaluamos pagina actual.
			if(pag.getPaginaActual()>1){				
				//traemos lista
				pag.setListaActual(SIL_DAO.pagina_Retroceder(pag));
				//obtenemos lista para cargar valores.
				ArrayList silList=pag.getListaActual();
				//obtenemos el primero de la lista consultada.
				silVOaux=(SIL_VO) silList.get(0);						
				pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelativ()));
				//obtenemos ultimo de la lista consultada.
				silVOaux=(SIL_VO) silList.get((silList.size()-1));
				pag.setUltimoLista(String.valueOf(silVOaux.getCorrelativ()));
				//extraemos largo de la lista actual.
				pag.setLargolistaActual(silList.size());				
				//diminuimos pagina en uno.
				pag.setPaginaActual(pag.getPaginaActual()-1);
			}else if(pag.getPaginaActual()==1){
				return pag;
			}else{
				//ya estamos en primera pagina asignamos lista vacia. por ultimo retroceso.
				pag.setListaActual(new ArrayList());
			}
		}else{
			//no exisaten datos para mostrar a este periodo.
			pag.setListaActual(new ArrayList());
		}				
		return pag;
	}//end paginarRetroceso
	
	public Paginacion_VO paginarUltimo(Paginacion_VO pag) throws Exception{
		SIL_VO silVOaux;
		int cantidadPaginas=0;
		//se carga cantidad de datos en BD
		pag.setCantidad_BD(SIL_DAO.count_SIL(pag.getFechaProceso()));
		//se calcula cantidad de paginas, (segun valor de registros por pagina)
		cantidadPaginas=pag.getCantidad_BD()/Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion"));
		
		//se calcula el sobrante para la ultima pagina
		int modRes=pag.getCantidad_BD()%10;
		//se asigna una pagina adicional para el sobrante. y se carga la cantidad de registros a mostrar en la ultima pagina
		if(modRes>0){
			pag.setRegistrosPorPagina(modRes);
			cantidadPaginas++;
		}
		pag.setPaginaActual(cantidadPaginas);
		log.info("* * * paginas :"+cantidadPaginas);
		log.info("* * * mod     :"+modRes);
				
		pag.setCantidadPaginas(cantidadPaginas);
		pag.setListaActual(SIL_DAO.pagina_Ultima(pag));
		
		ArrayList silList=pag.getListaActual();
		if(silList.size()>0){			
			//obtenemos el primero de la lista consultada.
			silVOaux=(SIL_VO) silList.get(0);						
			pag.setPrimeroLista(String.valueOf(silVOaux.getCorrelativ()));
			//obtenemos ultimo de la lista consultada.
			silVOaux=(SIL_VO) silList.get((silList.size()-1));
			pag.setUltimoLista(String.valueOf(silVOaux.getCorrelativ()));
			//extraemos largo de la lista actual.
			pag.setLargolistaActual(silList.size());
		}
		return pag;
	}
	
 	public ArrayList<SIL_VO> buscar_ListaTabla(SIL_VO silVO, Paginacion_VO pag) throws Exception{
		ArrayList<SIL_VO> al=SIL_DAO.buscar(silVO);		
		/*
		for(int i = 0; i < al.size(); i++){
			SIL_VO sil_vo = al.get(i);
			String opcion="mostrarErrores";
			al = this.validarSIL(sil_vo,opcion);
		}
		*/
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
	}//end buscar
	
 	public ArrayList<SIL_VO> buscar_ListaLog(SIL_VO silVO, Paginacion_VO pag) throws Exception{
 		
		ArrayList<SIL_VO> al=SIL_DAO.buscar(silVO);		
		
		
		/*
		 for(int i = 0; i < al.size(); i++){
			SIL_VO sil_vo = al.get(i);
			String opcion="mostrarErrores";
			al = this.validarSIL(sil_vo,opcion);
		}
		int pag_actual = pag.getPaginaActual();
		int largo = pag.getRegistrosPorPagina();//listaDespliegue.size();
		int count = 1;
		int correlab = 0;
		
		for(int i = 0; i < al.size(); i++){
			//Carga numeración a mostrar por pantalla.
			correlab = ((pag_actual-1)*largo+count);
			al.get(i).setCorrelab(correlab);
			count++;
		}*/
		return al;
	}//end buscar_ListaLog
 	
	public boolean eliminar(SIL_VO silVO) throws Exception{
		//eliminamos.
		SIL_DAO.eliminar(silVO);
		//consultamos si existe para cargar mensaje en action.
		return getExistencia(silVO);
	}//end eliminar
	
	public SIL_VistaErrores_VO insertar(SIL_VO silVO)throws Exception{
		log.info("* * * * * [MGR: SIL, start insertar]* * * * *");
		int vc=1;
		boolean keyErrores=false;
		String keyAccion="3";
		SIL_VistaErrores_VO silVe = new SIL_VistaErrores_VO();
		ArrayList al=new ArrayList();		
		HashMap<String,String> errMap=new HashMap<String, String>();		
		SIL_GlosaErrores_VO silge;
		
		/*insertar en base datos con silVO, independiente de sus errores*/
		if(!this.getExistencia(silVO)){
			SIL_DAO.insertar_SIL(silVO);			
			keyAccion="1";
		}else{
			//no se inserto porque ya existe PK			
			keyAccion="3";
		}
		
		if(!keyAccion.equalsIgnoreCase("3")){
			/*validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
			log.info("* * * * * [MGR: SIL, iterator list]* * * * *");
			String opcion="validarErrores";
			al=validarSIL(silVO,opcion);		
			Iterator it=al.iterator();
			while(it.hasNext()){
				log.info("* * * * * [MGR: SIL, while: ]"+(vc++)+"* * * * *");
				silge=new SIL_GlosaErrores_VO();			
				silge=(SIL_GlosaErrores_VO) it.next();
				if(silge.getIdcampo()!=null){
					if(errMap.get(silge.getIdcampo())==null){
						log.info("* * * * * [MGR: SIL, while: ]* * * * *");
						errMap.put(silge.getIdcampo(), silge.getDescripcion());
						//keyErrores=true;
					}else{
						String aux=errMap.get(silge.getIdcampo());
						errMap.put(silge.getIdcampo(), aux+"<br>"+silge.getDescripcion());
					}				
				}			
			}
		}else{
			//es tres, no se inserto porque ya existe. tampoco se valido.
			//errores=false; existe=true;
			silVe= new SIL_VistaErrores_VO(false,true);
		}
		
		if(keyAccion.equalsIgnoreCase("1")){	
			//se inserto registro, y se paso por validacion.
			if(keyErrores==false){
				//validacion sin errores, se retrnara mensaje de insercion correcta. 
				log.info("* * * * * [MGR: SIL, SIN observaciones, retornar mensaje]* * * * *");
				//errores=false; existe=false;
				silVe= new SIL_VistaErrores_VO(false,false);
			}else{
				//validacion con errores, se retornara vo junto a errores.
				log.info("* * * * * [MGR: SIL, CON observaciones, retornar descripciones]* * * * *");
				//errores=true; existe=false;
				silVe= new SIL_VistaErrores_VO(true,false);
				silVe=this.asignaErrores(keyErrores, errMap);
			}
		}		
		return silVe;
	}//end insertar
	
	public SIL_VistaErrores_VO actualizar(SIL_VO silVO,HashMap<String, String> mapDatos)throws Exception{
		log.info("* * * * * [MGR: SIL, start actualizar]* * * * *");		
		int vc=1;
		boolean keyErrores=false;
		String keyAccion="3";
		ArrayList al=new ArrayList();		
		HashMap<String,String> errMap=new HashMap<String, String>();		
		SIL_GlosaErrores_VO silge;	
		
		//String new_key = silVO.getNrofol()+"_"+silVO.getRuttrabaj()+"_"+silVO.getPerpag()+"_"+silVO.getLichasfec()+"_"+silVO.getPagfol();
		String new_key = silVO.getNrofol().trim()+"_"+silVO.getRuttrabaj().trim()+"_"+silVO.getPerpag()+"_"+silVO.getLichasfec()+"_"+silVO.getPagfol();
		//String old_key = mapDatos.get("nro_fol")+"_"+mapDatos.get("rut_trabaj")+"_"+mapDatos.get("perpag")+"_"+mapDatos.get("lichasfec")+"_"+mapDatos.get("pag_fol");
		String old_key = mapDatos.get("nro_fol").trim()+"_"+mapDatos.get("rut_trabaj").trim()+"_"+mapDatos.get("perpag").trim()+"_"+mapDatos.get("lichasfec").trim()+"_"+mapDatos.get("pag_fol").trim();
		
		log.info("old_key: "+old_key);
		log.info("correlativo: "+mapDatos.get("correlativo"));
		
		if(new_key.equalsIgnoreCase(old_key)){
			//Update dato.
			SIL_DAO.actualizar(silVO);
			keyAccion="1";
		}else if(!this.getExistencia(silVO)){
			//si no existe nueva pk. insertamos nueva
			SIL_DAO.insertar_SIL(silVO);
			//actualizar pk54			
			HashMap<String,String> mapUP =new HashMap<String, String>();
			//pk antigua
			mapUP.put("old_rut", mapDatos.get("rut_trabaj"));
			mapUP.put("old_folio", mapDatos.get("nro_fol"));
			mapUP.put("old_fecTerminoLic", mapDatos.get("lichasfec"));
			mapUP.put("old_pagfol", mapDatos.get("pag_fol"));
			mapUP.put("old_periodo", mapDatos.get("perpag"));
			
			//pk nueva
			mapUP.put("new_rut", silVO.getRuttrabaj());
			mapUP.put("new_folio", silVO.getNrofol());
			mapUP.put("new_fecTerminoLic", String.valueOf(silVO.getLichasfec()));
			mapUP.put("new_pagfol", String.valueOf(silVO.getPagfol()));
			
			SIL_DAO.actualizar_SilPK54(mapUP);
			
			//eliminamos antigua.
			SIL_DAO.eliminarDato(mapDatos);
			keyAccion="2";
		}else{
			//retornar mensaje sin proceso de validacion.
			keyAccion="3";
		}
		if(!keyAccion.equalsIgnoreCase("3")){
			/*validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
			log.info("* * * * * [MGR: SIL, iterator list]* * * * *");
			String opcion="validarErrores";
			al=this.validarSIL(silVO,opcion);		
			Iterator it=al.iterator();
			while(it.hasNext()){
				silge=new SIL_GlosaErrores_VO();			
				silge=(SIL_GlosaErrores_VO) it.next();			
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
		}
		
		if(keyErrores==false){
			log.info("* * * * * [MGR: SIL, SIN observaciones, cargando mensaje]* * * * *");
			return new SIL_VistaErrores_VO(false,true);
		}else{
			log.info("* * * * * [MGR: SIL, CON observaciones, retornando descripciones]* * * * *");
			SIL_VistaErrores_VO lmerr=this.asignaErrores(keyErrores, errMap);
			return lmerr;
		}		 
	}//end actualizar
	
	private SIL_VistaErrores_VO asignaErrores(boolean errores,HashMap errMap){
		SIL_VistaErrores_VO silerr=new SIL_VistaErrores_VO();
		String campoId="";
		Configuraciones lp=new Configuraciones();
		if(errores){
			silerr.setKeyErrores(true);
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
			campoId=lp.getAtributoFormulario_SIL("lichasfec");
			if(errMap.get(campoId)!=null){
				silerr.setLichasfec((String)errMap.get(campoId));
			}
			campoId=lp.getAtributoFormulario_SIL("pagfol");
			if(errMap.get(campoId)!=null){
				silerr.setPagfol((String)errMap.get(campoId));
			}
		}		
		return silerr;
	}//end asignaErrores
	
	/**funcion que aplica reglas de validacion mediante ejecucion de CL,
	 * inserta en BD temporal y
	 * envia parametros mediante hashMap a cl validacion.
	 * @throws Exception 
	 * 
	 **/
	private ArrayList validarSIL(SIL_VO silVO,String opcion) throws Exception{
		ArrayList al=new ArrayList();
		if(opcion.equalsIgnoreCase("mostrarErrores")){
			al=SIL_DAO.obtenerErroresSIL(silVO,opcion);
		}else if(opcion.equalsIgnoreCase("validarErrores")){
			//eliminacion de registros temporales:
			SIL_DAO.eliminar_T(silVO);		
			//insertar registro temporal
			SIL_DAO.insertar_SIL_T(silVO);
			//ejecutar cl validacion expres
			ILCSIL051_TDAO.callILCSIL051_T(String.valueOf(silVO.getPerpag()), "USUARIO_JAVA","T");
			//consultar log errores. retornara objeto compuesto resultado de inner
			//una lista del tipo glosa_error[valor consultado mas errorDescrip mas id campo]
			al=SIL_DAO.obtenerErroresSIL(silVO,opcion);
		}		
		return al;
	}//end validarSIL
		
	/**
	 * retornara true si el objeto buscado existe
	 **/
	private boolean getExistencia(SIL_VO silVO) throws Exception{
		boolean key=false;
		ArrayList temp=null;
		temp=SIL_DAO.buscar(silVO);
		//se solicito actualizar o eliminar, se buscara si existe una vez,
		if(temp!=null){
			if(temp.size()>0){
				key=true;
			}
		}				
		return key;
	}
}//end class SIL_Manager
