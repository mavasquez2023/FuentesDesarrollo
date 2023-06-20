package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tivoli.pd.jasn1.boolean32;

import cl.laaraucana.silmsil.dao.ILCLM051_TDAO;
import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.EstadoVO;
import cl.laaraucana.silmsil.vo.ILFJA058VO;
import cl.laaraucana.silmsil.vo.LM_VistaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.ListadoPrincipalVO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;

/**
 * manager LM, comprende logica de mantenedor lm
 * para tabla ILFLM050.
 *
 */
public class LM_Manager {
	
	private Logger log = Logger.getLogger(this.getClass());
	Configuraciones conf=new Configuraciones();
	
	public Paginacion_VO paginaInicio(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		int cantidadPaginas=0;
		
		pag.setCantidad_BD(LM_DAO.count_LM(pag.getFechaProceso()));
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
		pag.setListaActual(LM_DAO.pagina_Inicio(pag));
		ArrayList lmList=pag.getListaActual();
		if(lmList.size()>0){			
			//obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelativ()));
			//obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
			//extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}			
		return pag;
	}
	
	public Paginacion_VO paginarAvance(Paginacion_VO pag) throws Exception{
		LM_VO lmVOaux;
		pag.setCantidad_BD(LM_DAO.count_LM(pag.getFechaProceso()));
		
		String regPorPaginas=conf.getAtributoPaginacion("cantidadPaginacion");
		pag.setRegistrosPorPagina(Integer.parseInt(regPorPaginas));
		
		if(pag.getCantidad_BD()>0){			
			//obtenemos numero de paginas.					
			//consultamos en que pagina estamos.
			if(pag.getPaginaActual()<pag.getCantidadPaginas()){
				
				//traemos lista
				pag.setListaActual(LM_DAO.pagina_Avanzar(pag));
				//obtenemos lista para cargar valores.
				ArrayList lmList=pag.getListaActual();
				//obtenemos el primero de la lista consultada.
				lmVOaux=(LM_VO) lmList.get(0);						
				pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelativ()));
				//obtenemos ultimo de la lista consultada.
				lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
				pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
				//extraemos largo de la lista actual.
				pag.setLargolistaActual(lmList.size());
				
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
		LM_VO lmVOaux;
		//cargamos cantidad a mostrar.
		String regPorPaginas=conf.getAtributoPaginacion("cantidadPaginacion");
		pag.setRegistrosPorPagina(Integer.parseInt(regPorPaginas));
		//obtenemos cantidad de registros BD
		pag.setCantidad_BD(LM_DAO.count_LM(pag.getFechaProceso()));
		if(pag.getCantidad_BD()>0){							
			//evaluamos pagina actual.
			if(pag.getPaginaActual()>1){				
				//traemos lista
				pag.setListaActual(LM_DAO.pagina_Retroceder(pag));
				//obtenemos lista para cargar valores.
				ArrayList lmList=pag.getListaActual();
				//obtenemos el primero de la lista consultada.
				lmVOaux=(LM_VO) lmList.get(0);						
				pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelativ()));
				//obtenemos ultimo de la lista consultada.
				lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
				pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
				//extraemos largo de la lista actual.
				pag.setLargolistaActual(lmList.size());				
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
		LM_VO lmVOaux;
		int cantidadPaginas=0;
		//se carga cantidad de datos en BD
		pag.setCantidad_BD(LM_DAO.count_LM(pag.getFechaProceso()));
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
		pag.setListaActual(LM_DAO.pagina_Ultima(pag));
		
		ArrayList lmList=pag.getListaActual();
		if(lmList.size()>0){			
			//obtenemos el primero de la lista consultada.
			lmVOaux=(LM_VO) lmList.get(0);						
			pag.setPrimeroLista(String.valueOf(lmVOaux.getCorrelativ()));
			//obtenemos ultimo de la lista consultada.
			lmVOaux=(LM_VO) lmList.get((lmList.size()-1));
			pag.setUltimoLista(String.valueOf(lmVOaux.getCorrelativ()));
			//extraemos largo de la lista actual.
			pag.setLargolistaActual(lmList.size());
		}			
		return pag;
	}
	
 	public ArrayList<LM_VO> buscar_ListaTabla(LM_VO lmVO, Paginacion_VO pag) throws Exception{
		ArrayList<LM_VO> al=LM_DAO.buscar(lmVO);
		
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
	
 	public ArrayList<LM_VO> buscar_ListaLog(LM_VO lmVO, Paginacion_VO pag) throws Exception{
 		
 		ArrayList<LM_VO> al=LM_DAO.buscar(lmVO);		
		/*
		  
		 for(int i = 0; i < al.size(); i++){
			LM_VO lm_vo = al.get(i);
			String opcion="mostrarErrores";
			al = this.validarLM(lm_vo,opcion);
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
 	
	public boolean eliminar(LM_VO lmVO) throws Exception{
		//eliminamos.
		LM_DAO.eliminar(lmVO);
		//consultamos si existe para cargar mensaje en action.
		return getExistencia(lmVO);
	}//end eliminar
	
	public LM_VistaErrores_VO insertar(LM_VO lmVO)throws Exception{
		log.info("* * * * * [MGR: LM, start insertar]* * * * *");
		int vc=1;
		boolean keyErrores=false;
		String keyAccion="3";
		ArrayList al=new ArrayList();	
		LM_VistaErrores_VO lmerr=new LM_VistaErrores_VO();
		HashMap<String,String> errMap=new HashMap<String, String>();		
		LM_GlosaErrores_VO lmge;	
		
		
		/*insertar en base datos con lmVO*/
		if(!this.getExistencia(lmVO)){
			LM_DAO.insertar_LM(lmVO);			
			keyAccion="1";
		}else{
			//no se inserto porque ya existe PK
			keyAccion="3";
		}	
		if(!keyAccion.equalsIgnoreCase("3")){
			/*validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
			log.info("* * * * * [MGR: LM, iterator list]* * * * *");	
			String opcion="validarErrores";
			al=validarLM(lmVO,opcion);		
			Iterator it=al.iterator();
			while(it.hasNext()){
				
				lmge=new LM_GlosaErrores_VO();			
				lmge=(LM_GlosaErrores_VO) it.next();
				//String id_campo = (lmge.getIdcampo()!=0?String.valueOf(lmge.getIdcampo()):null);
				
				if(lmge.getIdcampo()!=null){
					if(errMap.get(lmge.getIdcampo())==null){
						
						log.info("* * * * * [MGR: LM, while: ]* * * * *");
						errMap.put(lmge.getIdcampo(),lmge.getDescripcion());
						//keyErrores=true;
					}else{
						String aux=errMap.get(lmge.getIdcampo());
						errMap.put(lmge.getIdcampo(), aux+"<br>"+lmge.getDescripcion());
					}				
				}			
			}
		}else{
			//es tres, no se inserto porque ya existe. tampoco se valido.
			//errores=false; existe=true;
			lmerr= new LM_VistaErrores_VO(false,true);
		}
		if(keyAccion.equalsIgnoreCase("1")){
			//se inserto registro, y se paso por validacion.
			if(keyErrores==false){
				//validacion sin errores, se retrnara mensaje de insercion correcta. 
				log.info("* * * * * [MGR: LM, SIN observaciones, retornar mensaje]* * * * *");
				lmerr= new LM_VistaErrores_VO(false,false);
			}else{
				//validacion con errores, se retornara vo junto a errores.
				log.info("* * * * * [MGR: LM, CON observaciones, retornar descripciones]* * * * *");
				lmerr= new LM_VistaErrores_VO(true,false);
				lmerr=this.asignaErrores(keyErrores, errMap);
			}
		}	
		return lmerr;
	}//end insertar
	
	public LM_VistaErrores_VO actualizar(LM_VO lmVO,HashMap<String, String> mapDatos)throws Exception{
		log.info("* * * * * [MGR: LM, start actualizar]* * * * *");
		
		int vc=1;
		boolean keyErrores=false;
		String keyAccion="3";
		ArrayList al=new ArrayList();		
		HashMap<String,String> errMap=new HashMap<String, String>();		
		LM_GlosaErrores_VO lmge;		
		
		//obtenemos claves de registro a actualizar.
		String new_key = lmVO.getFolio().trim()+"_"+lmVO.getRuttrabaj().trim()+"_"+lmVO.getFecproceso()+"_"+lmVO.getFecterrepo();
		String old_key = mapDatos.get("nro_folio").trim()+"_"+mapDatos.get("rut_trabaj").trim()+"_"+mapDatos.get("fecproceso").trim()+"_"+mapDatos.get("fecterrepo").trim();
		
		if(new_key.equalsIgnoreCase(old_key)){
			//Update dato.			
			LM_DAO.actualizar(lmVO);
			keyAccion="1";
		}else if(!this.getExistencia(lmVO)){
			//si no existe nueva pk. insertamos nueva			
			LM_DAO.insertar_LM(lmVO);
			
			//actualizar pk54
			HashMap<String,String> mapUP =new HashMap<String, String>();
			//pk original				
			mapUP.put("old_rut", mapDatos.get("rut_trabaj"));
			mapUP.put("old_folio", mapDatos.get("nro_folio"));
			mapUP.put("old_fecTerminoLic", mapDatos.get("fecterrepo"));
			mapUP.put("old_periodo", mapDatos.get("fecproceso"));
			
			//pk nueva
			mapUP.put("new_rut", lmVO.getRuttrabaj());
			mapUP.put("new_folio", lmVO.getFolio());
			mapUP.put("new_fecTerminoLic", String.valueOf(lmVO.getFecterrepo()));
			LM_DAO.actualizar_LmPK54(mapUP);
			
			//eliminamos antigua.
			LM_DAO.eliminarDato(mapDatos);
			keyAccion="2";
		}else{			
			//retornar mensaje sin proceso de validacion.
			keyAccion="3";
		}
		
		if(!keyAccion.equalsIgnoreCase("3")){
			/*validamos y obtenemos formato registro mas descripcion error lista. de todos los que contengan errores*/
			
			log.info("* * * * * [MGR: LM, iterator list]* * * * *");	
			
			String opcion="validarErrores";
			al=validarLM(lmVO,opcion);		
			Iterator it=al.iterator();
			while(it.hasNext()){
				lmge=new LM_GlosaErrores_VO();			
				lmge=(LM_GlosaErrores_VO) it.next();			
				//String id_campo = (lmge.getIdcampo()!=0?String.valueOf(lmge.getIdcampo()):null);			
				if(lmge.getIdcampo()!=null){
					if(errMap.get(lmge.getIdcampo())==null){
						errMap.put(lmge.getIdcampo(),lmge.getDescripcion());
						keyErrores=true;
					}else{
						String aux=errMap.get(lmge.getIdcampo());
						errMap.put(lmge.getIdcampo(), aux+"<br>"+lmge.getDescripcion());
					}				
				}			
			}
		}		
		if(keyErrores==false){
			log.info("* * * * * [MGR: LM, SIN observaciones, cargando mensaje]* * * * *");
			//boolean keyErrores, boolean keyExiste
			return new LM_VistaErrores_VO(false,true);
		}else{
			log.info("* * * * * [MGR: LM, CON observaciones, retornando descripciones]* * * * *");
			
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
		log.info("* * [validarLM] ");
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
			if(temp.size()>0){
				key=true;
			}
		}				
		return key;
	}
}//end class LM_Manager
