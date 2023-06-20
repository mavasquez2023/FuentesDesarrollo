package cl.laaraucana.silmsil.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.silmsil.forms.LM_Form;
import cl.laaraucana.silmsil.manager.LM_Manager;
import cl.laaraucana.silmsil.manager.LogLM_Manager;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.LM_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.LM_VistaErrores_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;


/**
 * @version 1.0
 * @author
 */
public class LM_Action extends DispatchAction
{
	private Logger log = Logger.getLogger(this.getClass());
	
	private String mensaje = null;
	
	/**
	 * obtenerTodo, DispatchAction para mantenedor de base intermedia LM
	 * **/
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		log.info("* * * * * [ACTION: LM, unspecified(), obtenerTodo]* * * * *");
		try {
			Configuraciones conf=new Configuraciones();
			LM_Manager lm_MGR=new LM_Manager();
			//inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			//asignamos periodo por mantenedor.
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			//pag.setFechaProceso((String)request.getParameter("periodo"));
			LM_Form lmForm= (LM_Form)form;
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			log.info("* * * * * [ACTION: LM, unspecified(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos lista de pagina.
			//pag=lm_MGR.paginarAvance(pag);
			pag=lm_MGR.paginaInicio(pag);
			HttpSession sesion = request.getSession();
			//cargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			
			String flag = (pag.getListaActual().size()==0?"0":String.valueOf(pag.getListaActual()));
			
			request.setAttribute("flag", flag);
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_LM");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
			
	/**
	 * buscar, DispatchAction para mantenedor de base intermedia LM
	 * ILFLM050
	 */
	public ActionForward buscar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, buscar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<LM_VO> lmList=new ArrayList<LM_VO>();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
			LM_VO lmVO=new LM_VO();
			if(lmForm!=null){
				lmVO.setFolio(lmForm.getFolio());
				lmVO.setRuttrabaj(lmForm.getRuttrabaj());
				lmVO.setFecproceso(lmForm.getFecproceso());
				if(lmVO.getFecproceso()!=0){
					log.info("* * * * * rut: "+lmVO.getRuttrabaj()+"* * * * * folio: "+lmVO.getFolio()+
						"* * * * * fechaPrceso: "+lmVO.getFecproceso());
					//lmList=lm_MGR.buscar_Lista(lmVO, pag);
					lmList=lm_MGR.buscar_ListaTabla(lmVO, pag);
				}			
			}
			request.setAttribute("lmList", lmList);
			request.setAttribute("Keybusqueda", true);
			request.setAttribute("mensajeBusqueda", "* La búsqueda retorna máximo " + Configuraciones.getAtributoPaginacion("cantidadPaginacion") + " registros.");
			forward = mapping.findForward("success_buscar_LM");			
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_LM
	
/*paginacion, primero, avance, retroceso, ultimo */	
	/**
	 * obtener pagina inicio
	 * **/
	public ActionForward paginarInicio_LM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("* * * * * [ACTION: LM, paginarInicio_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
						
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			log.info("* * * * * [ACTION: LM, paginarInicio_LM(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			//pag=lm_MGR.paginarAvance(pag);
			pag=lm_MGR.paginaInicio(pag);
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_LM");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	public ActionForward paginarAvance_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarAvance_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
			
			
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			log.info("* * * * * [ACTION: LM, paginarAvance_LM(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			pag=lm_MGR.paginarAvance(pag);
			
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_LM");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_LM
	
	public ActionForward paginarRetroceso_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarRetroceso_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;			
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			log.info("* * * * * [ACTION: LM, paginarRetroceso_LM(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			pag=lm_MGR.paginarRetroceso(pag);
			
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_LM");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_LM
	
	/**
	 * obtener ultima pagina 
	 * **/
	public ActionForward paginarUltimo_LM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("* * * * * [ACTION: LM, paginarUltimo_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
						
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			log.info("* * * * * [ACTION: LM, paginarInicio_LM(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			//pag=lm_MGR.paginarAvance(pag);
			pag=lm_MGR.paginarUltimo(pag);
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_LM");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	/**
	 * insertar, DispatchAction para mantenedor de base intermedia LM
	 * ILFLM050
	 */
	public ActionForward insertar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, insertar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			String flag =  "0";
			LM_Form lmForm= (LM_Form)form;			
			LM_VO lmVO=new LM_VO();
		//campos suseso
			lmVO.setOperador(lmForm.getOperador());	
			lmVO.setTipoform(lmForm.getTipoform());
			lmVO.setFolio(lmForm.getFolio());
			lmVO.setArt77bis(lmForm.getArt77bis());		
			lmVO.setFecinform(lmForm.getFecinform());
			lmVO.setRuttrabaj(lmForm.getRuttrabaj());
			lmVO.setFecemision(lmForm.getFecemision());
			lmVO.setFecinirepo(lmForm.getFecinirepo());
			lmVO.setFecterrepo(lmForm.getFecterrepo());
			lmVO.setEdadtrabaj(lmForm.getEdadtrabaj());
			
			lmVO.setFecnactrab(lmForm.getFecnactrab());
			lmVO.setGentrabaj(lmForm.getGentrabaj());
			lmVO.setNumdiaslic(lmForm.getNumdiaslic());
			lmVO.setLicmatsupl(lmForm.getLicmatsupl());
			lmVO.setFecnachijo(lmForm.getFecnachijo());
			lmVO.setRuthijo(lmForm.getRuthijo());
			lmVO.setTipolic(lmForm.getTipolic());
			lmVO.setRecuplabor(lmForm.getRecuplabor());
			lmVO.setIniinvalid(lmForm.getIniinvalid());
			lmVO.setFecconcep(lmForm.getFecconcep());
			
			lmVO.setTiporeposo(lmForm.getTiporeposo());
			lmVO.setJorreposo(lmForm.getJorreposo());
			lmVO.setLugreposo(lmForm.getLugreposo());
			lmVO.setEspecialid(lmForm.getEspecialid());
			lmVO.setTipoprofes(lmForm.getTipoprofes());
			lmVO.setRutprofes(lmForm.getRutprofes());
			lmVO.setNomprofes(lmForm.getNomprofes());
			lmVO.setLicmodific(lmForm.getLicmodific());
			lmVO.setEntautoriz(lmForm.getEntautoriz());
			lmVO.setTipolmresu(lmForm.getTipolmresu());
			
			lmVO.setNdiasincap(lmForm.getNdiasincap());
			lmVO.setDiagresuel(lmForm.getDiagresuel());
			lmVO.setPeriodo(lmForm.getPeriodo());
			lmVO.setNdiasprev(lmForm.getNdiasprev());
			lmVO.setEstadoreso(lmForm.getEstadoreso());
			lmVO.setTiporesolu(lmForm.getTiporesolu());
			lmVO.setRedictamen(lmForm.getRedictamen());
			lmVO.setCausarecha(lmForm.getCausarecha());
			lmVO.setTiporepoau(lmForm.getTiporepoau());	
			lmVO.setJorrepoaut(lmForm.getJorrepoaut());
			
			lmVO.setDesubsidio(lmForm.getDesubsidio());
			lmVO.setFecreceent(lmForm.getFecreceent());
			lmVO.setFecresoent(lmForm.getFecresoent());
			lmVO.setRutcontral(lmForm.getRutcontral());
			lmVO.setRutemplead(lmForm.getRutemplead());
			lmVO.setFecreceemp(lmForm.getFecreceemp());
			lmVO.setRegionempl(lmForm.getRegionempl());
			lmVO.setComunaempl(lmForm.getComunaempl());
			lmVO.setActlabtrab(lmForm.getActlabtrab());
			lmVO.setOcupactrab(lmForm.getOcupactrab());
			
			lmVO.setFecrecepag(lmForm.getFecrecepag());
			lmVO.setTipregprev(lmForm.getTipregprev());
			lmVO.setCalitrabaj(lmForm.getCalitrabaj());
			lmVO.setTipoentpag(lmForm.getTipoentpag());	
			lmVO.setFecpriafil(lmForm.getFecpriafil());		
			lmVO.setFeccontrab(lmForm.getFeccontrab());
		//adicionales, completar segun  equipo AS			
			lmVO.setFecproceso(lmForm.getFecproceso());
			
			Configuraciones conf=new Configuraciones();
			lmVO.setArchivo(conf.getMainConfig("archivo"));
			lmVO.setLicrechaz(conf.getMainConfig("licrechaz"));
			
			//lmVO.setEstadolic();
			log.info("fecProceso: "+lmVO.getFecproceso());
			LM_Manager lm_MGR=new LM_Manager();
			LM_VistaErrores_VO lmerr=lm_MGR.insertar(lmVO);
			String msgMantenedor="";
			
			if(lmerr.isKeyErrores()==false){
				if(lmerr.isKeyExiste()){
					flag = "0";
					msgMantenedor="No se inserto el registro porque ya existe.";
				}else{
					flag = "1";
					msgMantenedor="Se ha Insertado Correctamente el registro";					
				}				
			}
			
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_LM", lmVO);
			request.setAttribute("glosaError_LM", lmerr);	
			request.setAttribute("KeyErrores", lmerr.isKeyErrores());
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "LM");
			forward = mapping.findForward("success_insertar_LM");
		} catch (Exception e) {			
			log.error("* * * * * [ACTION: LM, CATCH: ]"+e+"* * * * *");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end insertar_LM
	
	/**
	 * actualizar , DispatchAction para mantenedor de base intermedia LM
	 * ILFLM050
	 */
	public ActionForward actualizar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, actualizar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			String flag = "1";
			LM_Form lmForm= (LM_Form)form;			
			LM_VO lmVO=new LM_VO();
		//campos suseso
			lmVO.setOperador(lmForm.getOperador());	
			lmVO.setTipoform(lmForm.getTipoform());
			lmVO.setFolio(lmForm.getFolio());
			lmVO.setArt77bis(lmForm.getArt77bis());		
			lmVO.setFecinform(lmForm.getFecinform());
			lmVO.setRuttrabaj(lmForm.getRuttrabaj());
			lmVO.setFecemision(lmForm.getFecemision());
			lmVO.setFecinirepo(lmForm.getFecinirepo());
			lmVO.setFecterrepo(lmForm.getFecterrepo());
			lmVO.setEdadtrabaj(lmForm.getEdadtrabaj());
			
			lmVO.setFecnactrab(lmForm.getFecnactrab());
			lmVO.setGentrabaj(lmForm.getGentrabaj());
			lmVO.setNumdiaslic(lmForm.getNumdiaslic());
			lmVO.setLicmatsupl(lmForm.getLicmatsupl());
			lmVO.setFecnachijo(lmForm.getFecnachijo());
			lmVO.setRuthijo(lmForm.getRuthijo());
			lmVO.setTipolic(lmForm.getTipolic());
			lmVO.setRecuplabor(lmForm.getRecuplabor());
			lmVO.setIniinvalid(lmForm.getIniinvalid());
			lmVO.setFecconcep(lmForm.getFecconcep());
			
			lmVO.setTiporeposo(lmForm.getTiporeposo());
			lmVO.setJorreposo(lmForm.getJorreposo());
			lmVO.setLugreposo(lmForm.getLugreposo());
			lmVO.setEspecialid(lmForm.getEspecialid());
			lmVO.setTipoprofes(lmForm.getTipoprofes());
			lmVO.setRutprofes(lmForm.getRutprofes());
			lmVO.setNomprofes(lmForm.getNomprofes());
			lmVO.setLicmodific(lmForm.getLicmodific());
			lmVO.setEntautoriz(lmForm.getEntautoriz());
			lmVO.setTipolmresu(lmForm.getTipolmresu());
			
			lmVO.setNdiasincap(lmForm.getNdiasincap());
			lmVO.setDiagresuel(lmForm.getDiagresuel());
			lmVO.setPeriodo(lmForm.getPeriodo());
			lmVO.setNdiasprev(lmForm.getNdiasprev());
			lmVO.setEstadoreso(lmForm.getEstadoreso());
			lmVO.setTiporesolu(lmForm.getTiporesolu());
			lmVO.setRedictamen(lmForm.getRedictamen());
			lmVO.setCausarecha(lmForm.getCausarecha());
			lmVO.setTiporepoau(lmForm.getTiporepoau());	
			lmVO.setJorrepoaut(lmForm.getJorrepoaut());
			
			lmVO.setDesubsidio(lmForm.getDesubsidio());
			lmVO.setFecreceent(lmForm.getFecreceent());
			lmVO.setFecresoent(lmForm.getFecresoent());
			lmVO.setRutcontral(lmForm.getRutcontral());
			lmVO.setRutemplead(lmForm.getRutemplead());
			lmVO.setFecreceemp(lmForm.getFecreceemp());
			lmVO.setRegionempl(lmForm.getRegionempl());
			lmVO.setComunaempl(lmForm.getComunaempl());
			lmVO.setActlabtrab(lmForm.getActlabtrab());
			lmVO.setOcupactrab(lmForm.getOcupactrab());
			
			lmVO.setFecrecepag(lmForm.getFecrecepag());
			lmVO.setTipregprev(lmForm.getTipregprev());
			lmVO.setCalitrabaj(lmForm.getCalitrabaj());
			lmVO.setTipoentpag(lmForm.getTipoentpag());	
			lmVO.setFecpriafil(lmForm.getFecpriafil());		
			lmVO.setFeccontrab(lmForm.getFeccontrab());
			//Adicionales, completar según respeta equipo AS400.			
			lmVO.setFecproceso(lmForm.getFecproceso());
			lmVO.setArchivo(lmForm.getArchivo());
			lmVO.setLicrechaz(lmForm.getLicrechaz());
			//lmVO.setEstadolic();
			
			log.info("* * * * * rut: "+lmVO.getRuttrabaj()+"* * * * * folio: "+lmVO.getFolio()+
				"* * * * * fechaPrceso: "+lmVO.getFecproceso()+"* * * * * Fecterrepo: "+lmVO.getFecterrepo());
			
			LM_Manager lm_MGR = new LM_Manager();
			HttpSession session = request.getSession();
			HashMap<String, String> mapDatos = (HashMap<String, String>)session.getAttribute("mapa_datos_LM");
			LM_VistaErrores_VO lmerr=lm_MGR.actualizar(lmVO,mapDatos);
			String msgMantenedor="";
			
			System.out.println("---> lmerr.isKeyErrores() = " + lmerr.isKeyErrores());
			
			if(!lmerr.isKeyErrores()){
				flag = "1";
				msgMantenedor="La actualización se realizo correctamente.";
			}
			
			System.out.println("Flag = " + flag);
			
			//proceso en "1" por secuencia de mantenedor.
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_LM", lmVO);
			request.setAttribute("glosaError_LM", lmerr);	
			request.setAttribute("KeyErrores", true);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "LM");
			forward = mapping.findForward("success_actualizar_LM");
		} catch (Exception e) {			
			log.error("* * * * * [ACTION: LM, CATCH: ]"+e+"* * * * *");
			request.setAttribute("msgError", "Error al intentar actualizar el registro");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end actualizar_LM
	
	/**
	 * eliminar, DispatchAction para mantenedor de base intermedia LM
	 * ILFLM050
	 */
	public ActionForward eliminar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, eliminar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			String flag = "0";
			String msgMantenedor="";
			boolean respDel=false;
			ArrayList<LM_VO> lmList=new ArrayList<LM_VO>();
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
			LM_VO lmVO=new LM_VO();
			if(lmForm!=null){
				lmVO.setFolio(lmForm.getFolio());
				lmVO.setRuttrabaj(lmForm.getRuttrabaj());
				lmVO.setFecproceso(lmForm.getFecproceso());
				lmVO.setFecterrepo(lmForm.getFecterrepo());
				lmVO.setCorrelativ(lmForm.getCorrelativ());
				
				log.info("* * * * * rut: "+lmVO.getRuttrabaj()+"* * * * * folio: "+lmVO.getFolio()+
					"* * * * * fechaPrceso: "+lmVO.getFecproceso()+"* * * * * Fecterrepo: "+lmVO.getFecterrepo());
				
				//if(lmVO.getFolio()!=null && lmVO.getFolio()!="" && lmVO.getRuttrabaj()!=null && lmVO.getRuttrabaj()!="" && lmVO.getFecproceso()!=0 && lmVO.getFecterrepo()!=0){
				if(lmVO.getFolio()!=null && lmVO.getFolio()!="" && lmVO.getRuttrabaj()!=null && lmVO.getRuttrabaj()!="" && lmVO.getFecproceso()!=0 && lmVO.getFecterrepo()!=0){
					respDel=lm_MGR.eliminar(lmVO);					
				}
				if(respDel){//si es true, entoces registro sigue existiendo.
					msgMantenedor="Eliminación no realizada.";
					log.info("* * * * * [ACTION: LM, eliminar: "+msgMantenedor);
				}else{
					//actualizar variables de sesion infpag.
					flag = "1";
					msgMantenedor="Se elimino correctamente el registro seleccionado";
					log.info("* * * * * [ACTION: LM, eliminar: "+msgMantenedor);
				}
			}
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("KeyErrores", false);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "LM");			
			request.setAttribute("registro_LM", lmList);
			
			forward = mapping.findForward("success_eliminar_LM");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_LM
	
	public ActionForward mostrar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, mostrar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		LM_VistaErrores_VO lmerr=new LM_VistaErrores_VO();
		
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<LM_VO> lmList;
			LM_Manager lm_MGR=new LM_Manager();
			LM_Form lmForm= (LM_Form)form;
			LM_VO lmVO=new LM_VO();
			String msgMantenedor="";
			if(lmForm!=null){
				lmVO.setFolio(lmForm.getFolio());
				lmVO.setRuttrabaj(lmForm.getRuttrabaj());
				lmVO.setFecproceso(lmForm.getFecproceso());
				lmVO.setFecterrepo(lmForm.getFecterrepo());
				if(lmVO.getFolio()!=null && lmVO.getFolio()!="" && lmVO.getRuttrabaj()!=null && lmVO.getRuttrabaj()!="" && lmVO.getFecproceso()!=0 && lmVO.getFecterrepo()!=0){
					
					log.info("* * * * * rut: "+lmVO.getRuttrabaj()+"* * * * * folio: "+lmVO.getFolio()+
						"* * * * * fechaPrceso: "+lmVO.getFecproceso());
					
					HashMap<String, String> mapDatos = new HashMap<String, String>();
					mapDatos.put("nro_folio", lmForm.getFolio()); 
					mapDatos.put("rut_trabaj", lmForm.getRuttrabaj());
					mapDatos.put("fecproceso", String.valueOf(lmForm.getFecproceso())); 
					mapDatos.put("fecterrepo", String.valueOf(lmForm.getFecterrepo()));
					//mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelab()));
					mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelativ()));
				
					log.info("SESSION SET");
					HttpSession session = request.getSession();
					session.setAttribute("mapa_datos_LM", mapDatos);
					
					lmList=lm_MGR.buscar_ListaLog(lmVO, pag);
					if(lmList!=null && lmList.size()>0){
						lmVO=lmList.get(0);
					}else{
						msgMantenedor="registro no encontrado.";
						request.setAttribute("msgMantenedor",msgMantenedor );
						request.setAttribute("codPlano", "LM");
						request.setAttribute("KeyErrores", false);
					}
				}
			}
			
			request.setAttribute("registro_LM", lmVO);
			//Enviar glosa error en blanco
			request.setAttribute("glosaError_LM", lmerr);
			forward = mapping.findForward("success_mostrar_LM");			
		} catch (Exception e) {
			log.error("Error mostrar_LM :" + e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_LM
	
	public ActionForward mostrar_Insertar_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, mostrar_Insertar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		//mostrara formulario en blanco.
		try {
			LM_VistaErrores_VO lmerr=new LM_VistaErrores_VO();
			request.setAttribute("KeyErrores", true);
			request.setAttribute("registro_LM", lmerr);
			//enviar glosa error en blanco
			request.setAttribute("flagLog", "0");
			request.setAttribute("glosaError_LM", lmerr);
			forward = mapping.findForward("success_mostrar_Insertar_LM");			
		} catch (Exception e) {
			log.error("Error mostrar_Insertar_LM :" + e.getMessage());
			e.printStackTrace();
		}
	// Finish with
	return (forward);
	}
		
	/* ************************************************************************************************************************* */
	/* **************************************Proceso LOG LM ***************************************** */
	/* ************************************************************************************************************************* */
	
	/**
	 * Obtener todos los campos del listado para log de errores del proceso LM.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward allListLM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		try {
			log.info("* * * * * [ACTION: LM, allListLM()]* * * * *");	
			LM_Form lmform = (LM_Form)form;
			Configuraciones conf=new Configuraciones();
			LogLM_Manager lmLogMGR = new LogLM_Manager();
			
			//Inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			
			//Seteo de datos.
			String agrupar = lmform.getChk_agrupar_LM();
			String usuario = "JAVA_WEB_W8";
			pag.setFechaProceso(String.valueOf(lmform.getFecproceso()));
						
			//Solicitamos lista de pagina.
			if(agrupar.length() == 0){
				log.info("Sin agrupar.");
				pag = lmLogMGR.paginaInicio(pag);
			}else{
				log.info("Agrupando.");
				pag = lmLogMGR.paginaInicioAgrupada(pag, usuario);
			}
			
			//Cargar variables para informacion de pantalla para paginar.
			HttpSession sesion = request.getSession();
			sesion.setAttribute("pagInfo", pag);
						
			log.info("lmList = " + pag.getListaActual().size());
			log.info("cantidadPaginas = " + pag.getCantidadPaginas());
			log.info("paginaActual = " + pag.getPaginaActual());
			
			String flag = (pag.getListaActual().size()==0?"0":String.valueOf(pag.getListaActual()));

			//Lista a reemplazar en tabla.
			request.setAttribute("flag", flag);
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorLM");
		} catch (Exception e) {
			log.error("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return (forward);
	}//end allListLM
	
	/*obtiene registros de primera pagina de Log errores LM*/
	public ActionForward paginarInicio_LogLM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarInicio_LogLM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogLM_Manager lmLogMGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			
			//Seteo de datos.
			String agrupar = lmForm.getChk_agrupar_LM();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			
			//Solicitamos pagina.
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = lmLogMGR.paginaInicio(pag);
			}else{
				log.info("Con Agrupar....");
				pag = lmLogMGR.paginaInicioAgrupada(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorLM");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarInicio_LogLM
	
	/**
	 * Actions para obtener la siguiente tanda de datos a desplegar por paginación.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward paginarAvance_LogLM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarAvance_LogLM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogLM_Manager lmLogMGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			
			//Seteo de datos.
			String agrupar = lmForm.getChk_agrupar_LM();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			
			//Solicitamos pagina.
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = lmLogMGR.paginarAvance(pag);
			}else{
				log.info("Con Agrupar....");
				pag = lmLogMGR.paginarAvanceAgrupado(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorLM_Avanzar");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_LogLM
		
	/**
	 * Actions para obtener la anterior tanda de datos a desplegar por paginación.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward paginarRetroceso_LogLM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarRetroceso_LogLM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogLM_Manager lmLogMGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			
			//Seteo de datos.
			String agrupar = lmForm.getChk_agrupar_LM();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			
			//Solicitamos pagina
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = lmLogMGR.paginarRetroceso(pag);
			}else{
				log.info("Con Agrupar....");
				pag = lmLogMGR.paginarRetrocesoAgrupado(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			
			//Lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorLM_Retroceder");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarRetroceso_LogLM
	
	public ActionForward paginarUltimo_LogLM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, paginarUltimo_LogLM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogLM_Manager lmLogMGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			
			//Seteo de datos.
			String agrupar = lmForm.getChk_agrupar_LM();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(lmForm.getFecproceso()));
			
			//Solicitamos pagina.
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = lmLogMGR.paginarUltimo_LogLM(pag);
			}else{
				log.info("Con Agrupar....");
				pag = lmLogMGR.paginaUltimaAgrupada(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorLM_Avanzar");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia LM (ILFLM050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_LogLM
	
	/**
	 * Buscar, DispatchAction para log error proceso SIL.
	 * ILFSIL050 y ILFSIL054.
	 */
	public ActionForward buscarLog_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, buscarLog_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag = new Paginacion_VO();
			LogLM_Manager lmLogMGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			LM_GlosaErrores_VO glosa = new LM_GlosaErrores_VO();
			 
			//Obtención de datos de interfaz.
			String agrupar = lmForm.getChk_agrupar_LM();
			//String nro_folio = (lmForm.getFolio().length()==0?null:lmForm.getFolio());
			//String rut_trab = (lmForm.getRuttrabaj().length()==0?null:lmForm.getRuttrabaj());
			//String usuario = "JAVA_v01(2014/05)";
			
			glosa.setFolio(lmForm.getFolio());
			glosa.setRuttrabaj(lmForm.getRuttrabaj());
			glosa.setFecproceso(lmForm.getFecproceso());
			
			log.info("Buscador común para LogLM.");
			pag.setListaActual(lmLogMGR.buscarLog_Lista(glosa, pag));
			
			request.setAttribute("lmList", pag.getListaActual());
			request.setAttribute("Keybusqueda", true);
			request.setAttribute("mensajeBusqueda", "* La búsqueda retorna máximo " + Configuraciones.getAtributoPaginacion("cantidadPaginacion") + " registros.");
			forward = mapping.findForward("success_buscarLog_LM");			
		} catch(Exception e) {
			log.error("Error buscarLog_LM :"+ e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscarLog_LM
	
	/**
	 * Actions para mostrar errores en formulario LM.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mostrarLog_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, mostrarLog_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		LM_VistaErrores_VO lmerr=new LM_VistaErrores_VO();
		
		String flag = "0";
		
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<LM_VO> lmList;
			LM_Manager lm_MGR=new LM_Manager();
			LogLM_Manager lmLog_MGR=new LogLM_Manager();
			LM_Form lmForm= (LM_Form)form;
			LM_VO lmVO=new LM_VO();
			String msgMantenedor="";
			if(lmForm!=null){
				lmVO.setFolio(lmForm.getFolio());
				lmVO.setRuttrabaj(lmForm.getRuttrabaj());
				lmVO.setFecproceso(lmForm.getFecproceso());
				lmVO.setFecterrepo(lmForm.getFecterrepo());
				
				HashMap<String, String> mapDatos = new HashMap<String, String>();
				mapDatos.put("nro_folio", lmForm.getFolio()); 
				mapDatos.put("rut_trabaj", lmForm.getRuttrabaj());
				mapDatos.put("fecproceso", String.valueOf(lmForm.getFecproceso())); 
				mapDatos.put("fecterrepo", String.valueOf(lmForm.getFecterrepo()));
				//mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelab()));
				mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelativ()));
			
				log.info("SESSION SET");
				HttpSession session = request.getSession();
				session.setAttribute("mapa_datos_LM", mapDatos);
				
				if(lmVO.getFolio()!=null && lmVO.getFolio()!="" && lmVO.getRuttrabaj()!=null && 
					lmVO.getRuttrabaj()!="" && lmVO.getFecproceso()!=0 && lmVO.getFecterrepo()!=0){
					
					log.info("* * * * * rut: "+lmVO.getRuttrabaj()+"* * * * * folio: "+lmVO.getFolio()+
						"* * * * * fechaPrceso: "+lmVO.getFecproceso());
					
					//lmList=lm_MGR.buscar_Lista(lmVO, pag);
					lmList=lm_MGR.buscar_ListaLog(lmVO, pag);
					if(lmList.size()!=0){
						lmVO=lmList.get(0);
						
						//Mostrar datos error.
						String opcion="mostrarErrores";
						lmerr = lmLog_MGR.marcarErrores(lmVO,opcion); 
						
						lmerr.setKeyErrores(true);
						
						request.setAttribute("KeyErrores", lmerr.isKeyErrores());
					}else{
						msgMantenedor="registro no encontrado.";
						request.setAttribute("msgMantenedor",msgMantenedor );
						request.setAttribute("codPlano", "LM");
						request.setAttribute("KeyErrores", false);
					}
				}
			}
			request.setAttribute("flagLog", flag);
			request.setAttribute("registro_LM", lmVO);
			request.setAttribute("glosaError_LM", lmerr);
			request.setAttribute("proceso", "2");
			
			forward = mapping.findForward("success_mostrar_LM");			
		} catch (Exception e) {
			log.error("Error mostrarLog_LM :"+e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_LM
		
	/**
	 * actualizar , DispatchAction para mantenedor de base intermedia LM
	 * ILFLM050
	 */
	public ActionForward actualizarLog_LM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: LM, actualizar_LM()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			LM_Form lmForm= (LM_Form)form;			
			LM_VO lmVO=new LM_VO();
			
		//campos suseso
			lmVO.setOperador(lmForm.getOperador());	
			lmVO.setTipoform(lmForm.getTipoform());
			lmVO.setFolio(lmForm.getFolio());
			lmVO.setArt77bis(lmForm.getArt77bis());		
			lmVO.setFecinform(lmForm.getFecinform());
			lmVO.setRuttrabaj(lmForm.getRuttrabaj());
			lmVO.setFecemision(lmForm.getFecemision());
			lmVO.setFecinirepo(lmForm.getFecinirepo());
			lmVO.setFecterrepo(lmForm.getFecterrepo());
			lmVO.setEdadtrabaj(lmForm.getEdadtrabaj());
			
			lmVO.setFecnactrab(lmForm.getFecnactrab());
			lmVO.setGentrabaj(lmForm.getGentrabaj());
			lmVO.setNumdiaslic(lmForm.getNumdiaslic());
			lmVO.setLicmatsupl(lmForm.getLicmatsupl());
			lmVO.setFecnachijo(lmForm.getFecnachijo());
			lmVO.setRuthijo(lmForm.getRuthijo());
			lmVO.setTipolic(lmForm.getTipolic());
			lmVO.setRecuplabor(lmForm.getRecuplabor());
			lmVO.setIniinvalid(lmForm.getIniinvalid());
			lmVO.setFecconcep(lmForm.getFecconcep());
			
			lmVO.setTiporeposo(lmForm.getTiporeposo());
			lmVO.setJorreposo(lmForm.getJorreposo());
			lmVO.setLugreposo(lmForm.getLugreposo());
			lmVO.setEspecialid(lmForm.getEspecialid());
			lmVO.setTipoprofes(lmForm.getTipoprofes());
			lmVO.setRutprofes(lmForm.getRutprofes());
			lmVO.setNomprofes(lmForm.getNomprofes());
			lmVO.setLicmodific(lmForm.getLicmodific());
			lmVO.setEntautoriz(lmForm.getEntautoriz());
			lmVO.setTipolmresu(lmForm.getTipolmresu());
			
			lmVO.setNdiasincap(lmForm.getNdiasincap());
			lmVO.setDiagresuel(lmForm.getDiagresuel());
			lmVO.setPeriodo(lmForm.getPeriodo());
			lmVO.setNdiasprev(lmForm.getNdiasprev());
			lmVO.setEstadoreso(lmForm.getEstadoreso());
			lmVO.setTiporesolu(lmForm.getTiporesolu());
			lmVO.setRedictamen(lmForm.getRedictamen());
			lmVO.setCausarecha(lmForm.getCausarecha());
			lmVO.setTiporepoau(lmForm.getTiporepoau());	
			lmVO.setJorrepoaut(lmForm.getJorrepoaut());
			
			lmVO.setDesubsidio(lmForm.getDesubsidio());
			lmVO.setFecreceent(lmForm.getFecreceent());
			lmVO.setFecresoent(lmForm.getFecresoent());
			lmVO.setRutcontral(lmForm.getRutcontral());
			lmVO.setRutemplead(lmForm.getRutemplead());
			lmVO.setFecreceemp(lmForm.getFecreceemp());
			lmVO.setRegionempl(lmForm.getRegionempl());
			lmVO.setComunaempl(lmForm.getComunaempl());
			lmVO.setActlabtrab(lmForm.getActlabtrab());
			lmVO.setOcupactrab(lmForm.getOcupactrab());
			
			lmVO.setFecrecepag(lmForm.getFecrecepag());
			lmVO.setTipregprev(lmForm.getTipregprev());
			lmVO.setCalitrabaj(lmForm.getCalitrabaj());
			lmVO.setTipoentpag(lmForm.getTipoentpag());	
			lmVO.setFecpriafil(lmForm.getFecpriafil());		
			lmVO.setFeccontrab(lmForm.getFeccontrab());
		//adicionales, completar segun respeta equipo AS			
			lmVO.setFecproceso(lmForm.getFecproceso());
			lmVO.setArchivo(lmForm.getArchivo());
			lmVO.setLicrechaz(lmForm.getLicrechaz());
			//lmVO.setEstadolic();
			
			log.info("setFecproceso "+lmForm.getFecproceso());
			
			log.info("SESSION GET");
			HttpSession session = request.getSession();
			HashMap<String, String> mapDatos = (HashMap<String, String>)session.getAttribute("mapa_datos_LM");
			
			
			LogLM_Manager lm_MGR = new LogLM_Manager();
			log.info("lmVO: "+lmVO.getFecterrepo());
			LM_VistaErrores_VO lmerr=lm_MGR.actualizarLog_LM(lmVO, mapDatos);
			String msgMantenedor="";
			
//			if(!lmerr.isKeyErrores()){
//				msgMantenedor="La actualización se realizo correctamente.";
//			}
			
			String flag = "0";
			
			if(lmerr.getKeyEstado()==3){
				msgMantenedor = "No se ha podido actualizar el nuevo registro debido a " +
					"que existe otro registro con los mismos datos (Datos clave son número " +
					"de folio, rut trabajador y fecha proceso).";
			}else{
				flag = "1";
				msgMantenedor = "La actualización se realizo correctamente.";
				
				mapDatos = new HashMap<String, String>();
				mapDatos.put("nro_folio", lmForm.getFolio()); 
				mapDatos.put("rut_trabaj", lmForm.getRuttrabaj());
				mapDatos.put("fecproceso", String.valueOf(lmForm.getFecproceso())); 
				mapDatos.put("fecterrepo", String.valueOf(lmForm.getFecterrepo()));
				//mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelab()));
				mapDatos.put("correlativo", String.valueOf(lmForm.getCorrelativ()));
			
				log.info("SESSION SET");
				session.setAttribute("mapa_datos_LM", mapDatos);
			}
			
			request.setAttribute("proceso", "2");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_LM", lmVO);
			request.setAttribute("glosaError_LM", lmerr);	
			request.setAttribute("KeyErrores", true);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "LM");
			forward = mapping.findForward("success_actualizar_LM");
		} catch (Exception e) {			
			log.error("* * * * * [ACTION: LM, CATCH: ]"+e.getMessage()+"* * * * *");
			request.setAttribute("msgError", "Error al intentar actualizar el registro");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end actualizar_LM
	
}//end class
