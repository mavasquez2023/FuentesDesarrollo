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
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import cl.laaraucana.silmsil.forms.SIL_Form;
import cl.laaraucana.silmsil.manager.LogSIL_Manager;
import cl.laaraucana.silmsil.manager.SIL_Manager;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;
import cl.laaraucana.silmsil.vo.SIL_VistaErrores_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;

/**
 * @version 1.0
 * @author
 */
public class SIL_Action extends DispatchAction
{
	private Logger log = Logger.getLogger(this.getClass());
	
	private String mensaje = null;
	
	/**
	 * obtenerTodo, DispatchAction para mantenedor de base intermedia SIL
	 * **/
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		log.info("* * * * * [ACTION: SIL, unspecified(), obtenerTodo]* * * * *");
		try {
			Configuraciones conf=new Configuraciones();
			SIL_Manager sil_MGR=new SIL_Manager();
			//inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			//asignamos periodo por mantenedor.
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			//pag.setFechaProceso((String)request.getParameter("periodo"));
			SIL_Form silForm= (SIL_Form)form;
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: SIL, unspecified(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos lista de pagina.
			//pag=lm_MGR.paginarAvance(pag);
			pag=sil_MGR.paginaInicio(pag);
			HttpSession sesion = request.getSession();
			//cargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			log.info("size paglista actual: "+pag.getListaActual().size());
			
			String flag = (pag.getListaActual().size()==0?"0":String.valueOf(pag.getListaActual()));
			
			request.setAttribute("flag", flag);
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			
			forward = mapping.findForward("success_obtenerTodo_SIL");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFsil050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	/**
	 * buscar, DispatchAction para mantenedor de base intermedia SIL
	 * ILFSIL050
	 */
	public ActionForward buscar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, buscar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<SIL_VO> silList=new ArrayList<SIL_VO>();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			SIL_VO silVO=new SIL_VO();			
			if(silForm!=null){				
				silVO.setNrofol(silForm.getNrofol());
				silVO.setRuttrabaj(silForm.getRuttrabaj());
				silVO.setPerpag(silForm.getPerpag());
				if(silVO.getPerpag()!=0){
					
					log.info("* * * * * rut: "+silVO.getRuttrabaj()+"\n * * * * * folio: "+silVO.getNrofol()+
						"\n * * * * * fechaPrceso: "+silVO.getPerpag());
					
					silList=sil_MGR.buscar_ListaTabla(silVO, pag);
				}			
			}
			request.setAttribute("silList", silList);
			request.setAttribute("Keybusqueda", true);
			
			request.setAttribute("mensajeBusqueda", "* La búsqueda retorna máximo " + Configuraciones.getAtributoPaginacion("cantidadPaginacion") + " registros.");
			forward = mapping.findForward("success_buscar_SIL");			
		} catch(Exception e) {
			log.error("Error buscar_SIL :"+e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_SIL
	
	/*paginacion*/
	
	/**
	 * obtener pagina inicio
	 * **/
	public ActionForward paginarInicio_SIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarInicio_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
						
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: SIL, paginarInicio_SIL(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			//pag=lm_MGR.paginarAvance(pag);
			pag=sil_MGR.paginaInicio(pag);
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_SIL");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	public ActionForward paginarAvance_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarAvance_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			
			
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: sil, paginarAvance_SIL(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			pag=sil_MGR.paginarAvance(pag);
			
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_SIL");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_SIL
	
	public ActionForward paginarRetroceso_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarRetroceso_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;			
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			//pag.setFechaProceso((String)request.getAttribute("periodo"));
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: SIL, paginarRetroceso_SIL(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			pag=sil_MGR.paginarRetroceso(pag);
			
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_SIL");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarRetroceso_SIL
	
	/**
	 * obtener ultima pagina 
	 * **/
	public ActionForward paginarUltimo_SIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarUltimo_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
						
			HttpSession sesion = request.getSession();
			
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: SIL, paginarUltimo_SIL(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			//solicitamos pagina
			//pag=lm_MGR.paginarAvance(pag);
			pag=sil_MGR.paginarUltimo(pag);
			//reCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("success_obtenerTodo_SIL");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	/**
	 * insertar, DispatchAction para mantenedor de base intermedia SIL
	 * ILFSIL050
	 */
	public ActionForward insertar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, insertar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			String flag = "0";
			SIL_Form silForm= (SIL_Form)form;			
			SIL_VO silVO=new SIL_VO();
		//campos suseso
			silVO.setCodope(silForm.getCodope());
			silVO.setTpofor(silForm.getTpofor());
			silVO.setNrofol(silForm.getNrofol());
			silVO.setRuttrabaj(silForm.getRuttrabaj());	
			silVO.setFecemi(silForm.getFecemi());
			silVO.setDiasub(silForm.getDiasub());
			silVO.setMtoliq(silForm.getMtoliq());
			silVO.setMtocot(silForm.getMtocot());
			silVO.setCodint(silForm.getCodint());
			silVO.setFinipa(silForm.getFinipa());
			silVO.setMocope(silForm.getMocope());
			silVO.setBaseca(silForm.getBaseca());			
			silVO.setCcopgo(silForm.getCcopgo());
			silVO.setIdlice(silForm.getIdlice());
			silVO.setInimes(silForm.getInimes());						
			silVO.setTpolic(silForm.getTpolic());	
			silVO.setNdicot(silForm.getNdicot());
			silVO.setNdiinc(silForm.getNdiinc());
			silVO.setNdipag(silForm.getNdipag());
			silVO.setMtsbpa(silForm.getMtsbpa());
			silVO.setMtsbdi(silForm.getMtsbdi());
			silVO.setMcsegc(silForm.getMcsegc());
			silVO.setMotcot(silForm.getMotcot());
			silVO.setOfipgo(silForm.getOfipgo());
			silVO.setInssal(silForm.getInssal());
			silVO.setSubmat(silForm.getSubmat());
			silVO.setTpoliq(silForm.getTpoliq());			
			silVO.setFecpgo(silForm.getFecpgo());
			silVO.setMliqpa(silForm.getMliqpa());
			silVO.setRimpms(silForm.getRimpms());
						
		//adicionales, completar segun  equipo AS	
			Configuraciones conf=new Configuraciones();
			silVO.setArchiv(conf.getMainConfig("archivo"));
			//silVO.setCorrelativ(correlativ);
			silVO.setLichasfec(silForm.getLichasfec());
			silVO.setPagfol(silForm.getPagfol());
			silVO.setPerpag(silForm.getPerpag());
			
			log.info("lichasfec: "+silVO.getLichasfec()+"pagfol: "+silVO.getPagfol()+
				"getPerpag: "+silVO.getPerpag());
			
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_VistaErrores_VO silerr=sil_MGR.insertar(silVO);
			String msgMantenedor="";
			if(silerr.isKeyErrores()==false){
				if(silerr.isKeyExiste()){
					System.out.println("NO SE INSERTO!");
					flag = "0";
					msgMantenedor="No se inserto el registro porque ya existe.";
				}else{
					System.out.println("SE INSERTO");
					flag = "1";
					msgMantenedor="Se ha Insertado Correctamente el registro";					
				}				
			}		
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_SIL", silVO);
			request.setAttribute("glosaError_SIL", silerr);	
			request.setAttribute("KeyErrores", silerr.isKeyErrores());
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "SIL");
			forward = mapping.findForward("success_insertar_SIL");
		} catch (Exception e) {			
			log.equals("* * * * * [ACTION: SIL, CATCH: ]"+e.getMessage()+"* * * * *");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end insertar_SIL
	
	/**
	 * actualizar , DispatchAction para mantenedor de base intermedia SIL
	 * ILFSIL050
	 */
	public ActionForward actualizar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, actualizar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			String flag = "1";
			SIL_Form silForm= (SIL_Form)form;			
			SIL_VO silVO=new SIL_VO();
			
			//Campos suseso
			silVO.setCodope(silForm.getCodope());
			silVO.setTpofor(silForm.getTpofor());
			silVO.setNrofol(silForm.getNrofol());
			silVO.setRuttrabaj(silForm.getRuttrabaj());	
			silVO.setFecemi(silForm.getFecemi());
			silVO.setDiasub(silForm.getDiasub());
			silVO.setMtoliq(silForm.getMtoliq());
			silVO.setMtocot(silForm.getMtocot());
			silVO.setCodint(silForm.getCodint());
			silVO.setFinipa(silForm.getFinipa());
			silVO.setMocope(silForm.getMocope());
			silVO.setBaseca(silForm.getBaseca());			
			silVO.setCcopgo(silForm.getCcopgo());
			silVO.setIdlice(silForm.getIdlice());
			silVO.setInimes(silForm.getInimes());						
			silVO.setTpolic(silForm.getTpolic());	
			silVO.setNdicot(silForm.getNdicot());
			silVO.setNdiinc(silForm.getNdiinc());
			silVO.setNdipag(silForm.getNdipag());
			silVO.setMtsbpa(silForm.getMtsbpa());
			silVO.setMtsbdi(silForm.getMtsbdi());
			silVO.setMcsegc(silForm.getMcsegc());
			silVO.setMotcot(silForm.getMotcot());
			silVO.setOfipgo(silForm.getOfipgo());
			silVO.setInssal(silForm.getInssal());
			silVO.setSubmat(silForm.getSubmat());
			silVO.setTpoliq(silForm.getTpoliq());			
			silVO.setFecpgo(silForm.getFecpgo());
			silVO.setMliqpa(silForm.getMliqpa());
			silVO.setRimpms(silForm.getRimpms());
			
			silVO.setCorrelativ(silForm.getCorrelativ());
			silVO.setCorrelab(silForm.getCorrelab());
			
			log.info("NroFolio :" + silVO.getNrofol()+"Correlab :" + silVO.getCorrelab());
						
		//adicionales, completar segun  equipo AS			
			silVO.setArchiv(silForm.getArchiv());
			silVO.setLichasfec(silForm.getLichasfec());
			silVO.setPagfol(silForm.getPagfol());
			silVO.setPerpag(silForm.getPerpag());
			
			log.info("lichasfec: "+silVO.getLichasfec()+"pagfol: "+silVO.getPagfol()+
				"getPerpag: "+silVO.getPerpag());	
			
			HttpSession session = request.getSession();
			HashMap<String, String> mapDatos = (HashMap<String, String>)session.getAttribute("mapa_datos_SIL");
			
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_VistaErrores_VO silerr=sil_MGR.actualizar(silVO,mapDatos);
			String msgMantenedor="";
			if(!silerr.isKeyErrores()){
				flag = "1";
				msgMantenedor="La actualizacion se realizo correctamente";
			}	
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_SIL", silVO);
			request.setAttribute("glosaError_SIL", silerr);	
			request.setAttribute("KeyErrores", true);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "SIL");
			forward = mapping.findForward("success_actualizar_SIL");
		} catch (Exception e) {			
			log.error("* * * * * [ACTION: SIL, CATCH: ]"+e.getMessage()+"* * * * *");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end actualizar_sil
	
	/**
	 * eliminar, DispatchAction para mantenedor de base intermedia SIL
	 * ILFSIL050
	 */
	public ActionForward eliminar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, eliminar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			String flag = "0";
			String msgMantenedor="";
			boolean respDel=false;
			ArrayList<SIL_VO> silList=new ArrayList<SIL_VO>();
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			SIL_VO silVO=new SIL_VO();
			if(silForm!=null){
				silVO.setNrofol(silForm.getNrofol());
				silVO.setRuttrabaj(silForm.getRuttrabaj());
				silVO.setPerpag(silForm.getPerpag());
				silVO.setLichasfec(silForm.getLichasfec());
				silVO.setPagfol(silForm.getPagfol());
				silVO.setCorrelativ(silForm.getCorrelativ());
				
				log.info("* * * * * rut: "+silVO.getRuttrabaj()+"* * * * * folio: "+silVO.getNrofol()+
					"* * * * * fechaPrceso: "+silVO.getPerpag()+"* * * * * Fecterrepo: "+silVO.getLichasfec()+
					"* * * * * Correlativ: "+silVO.getCorrelativ());
				
				//if(lmVO.getFolio()!=null && lmVO.getFolio()!="" && lmVO.getRuttrabaj()!=null && lmVO.getRuttrabaj()!="" && lmVO.getFecproceso()!=0 && lmVO.getFecterrepo()!=0){
				if(silVO.getNrofol()!=null && silVO.getNrofol()!="" && silVO.getRuttrabaj()!=null && silVO.getRuttrabaj()!="" && silVO.getLichasfec()!=0 && silVO.getLichasfec()!=0){
					respDel=sil_MGR.eliminar(silVO);					
				}
				if(respDel){//si es true, entoces registro sigue existiendo.
					msgMantenedor="Eliminación no realizada.";
					log.info("* * * * * [ACTION: SIL, eliminar: "+msgMantenedor);
				}else{
					flag = "1";
					//actualizar variables de sesion infpag.
					log.info("* * * * * [ACTION: SIL, eliminar: "+msgMantenedor);						
					msgMantenedor="Se elimino correctamente el registro seleccionado";						
				}
			}
			request.setAttribute("proceso", "1");
			request.setAttribute("flagAct", flag);
			request.setAttribute("KeyErrores", false);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "SIL");			
			request.setAttribute("registro_SIL", silList);
			
			forward = mapping.findForward("success_eliminar_SIL");			
		} catch (Exception e) {
			log.error("Error eliminar_SIL :" +e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_SIL
		
	/**
	 * Actions para realizar búsqueda de un único registro Sil.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mostrar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, mostrar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		SIL_VistaErrores_VO silerr=new SIL_VistaErrores_VO();
		
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<SIL_VO> silList;
			SIL_Manager sil_MGR=new SIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			SIL_VO silVO=new SIL_VO();
			String msgMantenedor="";
			if(silForm!=null){
				silVO.setNrofol(silForm.getNrofol());
				silVO.setRuttrabaj(silForm.getRuttrabaj());
				silVO.setPerpag(silForm.getPerpag());
				silVO.setLichasfec(silForm.getLichasfec());
				silVO.setPagfol(silForm.getPagfol());
				
				log.info("* * * * * rut: "+silVO.getRuttrabaj()+"* * * * * folio: "+silVO.getNrofol()+
					"* * * * * fechaPrceso: "+silVO.getPerpag()+"* * * * * lichasfec: "+silVO.getLichasfec()+
					"* * * * * pagfol: "+silVO.getPagfol());
				
				HashMap<String, String> mapDatos = new HashMap<String, String>();
				mapDatos.put("nro_fol", silForm.getNrofol()); 
				mapDatos.put("rut_trabaj", silForm.getRuttrabaj());
				mapDatos.put("perpag", String.valueOf(silForm.getPerpag())); 
				mapDatos.put("lichasfec", String.valueOf(silForm.getLichasfec()));
				mapDatos.put("pag_fol", String.valueOf(silForm.getPagfol()));
				mapDatos.put("correlativo", String.valueOf(silForm.getCorrelativ()));
				
				log.info("* * [corelativ: "+String.valueOf(silForm.getCorrelativ()));
				
				HttpSession session = request.getSession();
				session.setAttribute("mapa_datos_SIL", mapDatos);
				
				if(silVO.getNrofol()!=null && silVO.getNrofol()!="" && silVO.getRuttrabaj()!=null && silVO.getRuttrabaj()!="" && silVO.getPerpag()!=0 && silVO.getLichasfec()!=0){
					
					silList=sil_MGR.buscar_ListaLog(silVO, pag);
					if(silList!=null && silList.size()>0){
						silVO=silList.get(0);
					}else{
						msgMantenedor="registro no encontrado.";
						request.setAttribute("msgMantenedor",msgMantenedor );
						request.setAttribute("codPlano", "SIL");
						request.setAttribute("KeyErrores", false);
					}
				}
			}
			
			request.setAttribute("registro_SIL", silVO);
			//enviar glosa error en blanco
			request.setAttribute("glosaError_SIL", silerr);
			forward = mapping.findForward("success_mostrar_SIL");			
		} catch (Exception e) {
			log.error("Error mostrar_SIL :" + e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_SIL
	
	public ActionForward mostrar_Insertar_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, mostrar_Insertar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		//mostrara formulario en blanco.
		try {
			SIL_VistaErrores_VO silerr=new SIL_VistaErrores_VO();
			
			request.setAttribute("KeyErrores", true);
			request.setAttribute("registro_SIL", silerr);
			//enviar glosa error en blanco
			request.setAttribute("glosaError_SIL", silerr);
			forward = mapping.findForward("success_mostrar_Insertar_SIL");			
		} catch (Exception e) {
			log.error("Error mostrar_Insertar_SIL :" + e.getMessage());
			e.printStackTrace();
		}
	// Finish with
	return (forward);
	}
	
	/* ************************************************************************************************************************* */
	/* **************************************Sesión para log de errores de proceso SIL ***************************************** */
	/* ************************************************************************************************************************* */
	
	/**
	 * Obtener todos los campos del listado para log de errores del proceso SIL.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward allListSIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		log.info("Entro en actions -> allListSil()");
		SIL_Form forms = (SIL_Form)form;
		try {
			Configuraciones conf=new Configuraciones();
			LogSIL_Manager silLog = new LogSIL_Manager();
			
			//Inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			
			//Seteo de datos.
			String agrupar = forms.getChk_agrupar_SIL();
			pag.setFechaProceso(String.valueOf(forms.getPerpag()));
			String usuario = "JAVA_WEB_W8";
			
			//Asignamos periodo por mantenedor.
			SIL_Form silForm= (SIL_Form)form;
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			
			log.info("* * * * * [ACTION: SIL, unspecified(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
			
			
			//Solicitamos lista de pagina.
			if(agrupar.length() == 0){
				log.info("Sin agrupar.");
				pag = silLog.paginaInicio(pag);
				ArrayList<SIL_GlosaErrores_VO> silList = pag.getListaActual();
			}else{
				log.info("Agrupando.");
				pag = silLog.paginaInicioAgrupada(pag, usuario);
			}
			
			String flag = (pag.getListaActual().size() == 0?"0":String.valueOf(pag.getListaActual().size()));
			
			//En caso que dato venga vacio se carga nuevo listado.
			ArrayList<SIL_GlosaErrores_VO> silList = (pag.getListaActual().size() == 0? new ArrayList<SIL_GlosaErrores_VO>(): pag.getListaActual());
						
			//Cargar variables para informacion de pantalla para paginar.
			HttpSession sesion = request.getSession();
			sesion.setAttribute("pagInfo", pag);
			
			log.info("silList = " + silList.size()+" cantidadPaginas = "+pag.getCantidadPaginas()+"paginaActual = " + pag.getPaginaActual());
						
			//Lista a reemplazar en tabla.
			request.setAttribute("flag", flag);
			request.setAttribute("silList", silList);
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorSil");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	public ActionForward paginarInicio_LogSIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		log.info("Entro en actions -> allListSil()");
		SIL_Form forms = (SIL_Form)form;
		try {
			Configuraciones conf=new Configuraciones();
			LogSIL_Manager silLog = new LogSIL_Manager();
			
			//Inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			
			//Seteo de datos.
			String agrupar = forms.getChk_agrupar_SIL();
			pag.setFechaProceso(String.valueOf(forms.getPerpag()));
			String usuario = "JAVA_WEB_W8";
			
			//Asignamos periodo por mantenedor.
			SIL_Form silForm= (SIL_Form)form;
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			
			log.info("* * * * * [ACTION: SIL, unspecified(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
						
			//Solicitamos lista de pagina.
			if(agrupar.length() == 0){
				log.info("Sin agrupar.");
				pag = silLog.paginaInicio(pag);				
			}else{
				log.info("Agrupando.");
				pag = silLog.paginaInicioAgrupada(pag, usuario);
			}
			
			String flag = (pag.getListaActual().size() == 0?"0":String.valueOf(pag.getListaActual().size()));
			
			//En caso que dato venga vacio se carga nuevo listado.
			ArrayList<SIL_GlosaErrores_VO> silList = (pag.getListaActual().size() == 0? new ArrayList<SIL_GlosaErrores_VO>(): pag.getListaActual());
						
			//Cargar variables para informacion de pantalla para paginar.
			HttpSession sesion = request.getSession();
			sesion.setAttribute("pagInfo", pag);
			
			log.info("silList = " + silList.size()+" cantidadPaginas = "+pag.getCantidadPaginas()+"paginaActual = " + pag.getPaginaActual());
						
			//Lista a reemplazar en tabla.
			request.setAttribute("flag", flag);
			request.setAttribute("silList", silList);
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorSil");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	/**
	 * Actions para obtener la siguiente tanda de datos a desplegar por paginación.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward paginarAvance_LogSIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarAvance_LogSIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogSIL_Manager sil_MGR=new LogSIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			
			//Seteo de datos.
			String agrupar = silForm.getChk_agrupar_SIL();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			
			log.info("* * * * * [ACTION: SIL, paginarAvance_LogSIL(), fecha: ]"
				+pag.getFechaProceso()+"* * * * *");
			
			//Solicitamos pagina.
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = sil_MGR.paginarAvance(pag);
			}else{
				log.info("Con Agrupar....");
				pag = sil_MGR.paginarAvanceAgrupado(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			//lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorSil_Avanzar");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarAvance_SIL
	
	/**
	 * Actions para obtener la anterior tanda de datos a desplegar por paginación.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward paginarRetroceso_LogSIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, paginarRetroceso_LogSIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag;
			Configuraciones conf=new Configuraciones();
			LogSIL_Manager sil_MGR=new LogSIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			
			//Seteo de datos.
			String agrupar = silForm.getChk_agrupar_SIL();
			String usuario = "JAVA_WEB_W8";
			
			HttpSession sesion = request.getSession();
			pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			log.info("* * * * * [ACTION: SIL, paginarRetroceso_SIL(), fecha: ]"
				+pag.getFechaProceso()+"* * * * *");
			
			//Solicitamos página
			if(agrupar.length()==0){
				log.info("Sin Agrupar....");
				pag = sil_MGR.paginarRetroceso(pag);
			}else{
				log.info("Con Agrupar....");
				pag = sil_MGR.paginarRetrocesoAgrupado(pag, usuario);
			}
			
			//ReCargar variables para informacion de pantalla para paginar.
			sesion.setAttribute("pagInfo", pag);
			
			//Lista a reemplazar en tabla.
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorSil_Retroceder");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}//end paginarRetroceso_SIL
	
	public ActionForward paginarUltimo_LogSIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		log.info("Entro en actions -> allListSil()");
		SIL_Form forms = (SIL_Form)form;
		try {
			Configuraciones conf=new Configuraciones();
			LogSIL_Manager silLog = new LogSIL_Manager();
			
			//Inicializamos paginacion a cero.
			Paginacion_VO pag=new Paginacion_VO(Integer.parseInt(conf.getAtributoPaginacion("cantidadPaginacion")));
			
			//Seteo de datos.
			String agrupar = forms.getChk_agrupar_SIL();
			pag.setFechaProceso(String.valueOf(forms.getPerpag()));
			String usuario = "JAVA_WEB_W8";
			
			//Asignamos periodo por mantenedor.
			SIL_Form silForm= (SIL_Form)form;
			pag.setFechaProceso(String.valueOf(silForm.getPerpag()));
			
			log.info("* * * * * [ACTION: SIL, unspecified(), fecha: ]"+pag.getFechaProceso()+"* * * * *");
						
			//Solicitamos lista de pagina.
			if(agrupar.length() == 0){
				log.info("Sin agrupar.");
				pag = silLog.paginarUltimo_LogSIL(pag);
				ArrayList<SIL_GlosaErrores_VO> silList = pag.getListaActual();
			}else{
				log.info("Agrupando.");
				pag = silLog.paginaUltimaAgrupada(pag, usuario);
			}
			
			String flag = (pag.getListaActual().size() == 0?"0":String.valueOf(pag.getListaActual().size()));
			
			//En caso que dato venga vacio se carga nuevo listado.
			ArrayList<SIL_GlosaErrores_VO> silList = (pag.getListaActual().size() == 0? new ArrayList<SIL_GlosaErrores_VO>(): pag.getListaActual());
						
			//Cargar variables para informacion de pantalla para paginar.
			HttpSession sesion = request.getSession();
			sesion.setAttribute("pagInfo", pag);
			
			log.info("silList = " + silList.size()+" cantidadPaginas = "+pag.getCantidadPaginas()+"paginaActual = " + pag.getPaginaActual());
						
			//Lista a reemplazar en tabla.
			request.setAttribute("flag", flag);
			request.setAttribute("silList", silList);
			request.setAttribute("cantidadPaginas", pag.getCantidadPaginas());
			request.setAttribute("paginaActual", pag.getPaginaActual());
			request.setAttribute("Keybusqueda", false);
			forward = mapping.findForward("successLogErrorSil");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar por base intermedia SIL (ILFSIL050): " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar los datos.");
			return mapping.findForward("error");
		}
		return (forward);
	}
	
	/**
	 * Buscar, DispatchAction para log error proceso SIL.
	 * ILFSIL050 y ILFSIL054.
	 */
	public ActionForward buscarLog_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, buscarLog_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			Paginacion_VO pag = new Paginacion_VO();
			LogSIL_Manager sil_MGR=new LogSIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			SIL_GlosaErrores_VO glosa = new SIL_GlosaErrores_VO();
			 
			//Obtención de datos de interfaz.
			String nro_folio = (silForm.getNrofol().length()==0?null:silForm.getNrofol());
			String rut_trab = (silForm.getRuttrabaj().length()==0?null:silForm.getRuttrabaj());
			String usuario = "JAVA_v01(2014/05)";
			
			glosa.setNrofol(nro_folio);
			glosa.setRuttrabaj(rut_trab);
			glosa.setPerpag(silForm.getPerpag());
			
			//LLama a método buscar.
			log.info("Buscador común para LogSil.");
			pag.setListaActual(sil_MGR.buscarLog_Lista(glosa, pag));
			
			request.setAttribute("silList", pag.getListaActual());
			request.setAttribute("Keybusqueda", true);
			request.setAttribute("mensajeBusqueda", "* La búsqueda retorna máximo " + Configuraciones.getAtributoPaginacion("cantidadPaginacion") + " registros.");
			forward = mapping.findForward("success_buscarLog_SIL");			
		} catch(Exception e) {
			log.error("Error buscarLog_SIL :"+e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_SIL
	
	/**
	 * actualizar , DispatchAction para mantenedor de base intermedia SIL
	 * ILFSIL050
	 */
	public ActionForward actualizarLog_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, actualizar_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value		
		try {
			SIL_Form silForm= (SIL_Form)form;			
			SIL_VO silVO=new SIL_VO();
			
			//Campos suseso
			silVO.setCodope(silForm.getCodope());
			silVO.setTpofor(silForm.getTpofor());
			silVO.setNrofol(silForm.getNrofol());
			silVO.setRuttrabaj(silForm.getRuttrabaj());	
			silVO.setFecemi(silForm.getFecemi());
			silVO.setDiasub(silForm.getDiasub());
			silVO.setMtoliq(silForm.getMtoliq());
			silVO.setMtocot(silForm.getMtocot());
			silVO.setCodint(silForm.getCodint());
			silVO.setFinipa(silForm.getFinipa());
			silVO.setMocope(silForm.getMocope());
			silVO.setBaseca(silForm.getBaseca());			
			silVO.setCcopgo(silForm.getCcopgo());
			silVO.setIdlice(silForm.getIdlice());
			silVO.setInimes(silForm.getInimes());						
			silVO.setTpolic(silForm.getTpolic());	
			silVO.setNdicot(silForm.getNdicot());
			silVO.setNdiinc(silForm.getNdiinc());
			silVO.setNdipag(silForm.getNdipag());
			silVO.setMtsbpa(silForm.getMtsbpa());
			silVO.setMtsbdi(silForm.getMtsbdi());
			silVO.setMcsegc(silForm.getMcsegc());
			silVO.setMotcot(silForm.getMotcot());
			silVO.setOfipgo(silForm.getOfipgo());
			silVO.setInssal(silForm.getInssal());
			silVO.setSubmat(silForm.getSubmat());
			silVO.setTpoliq(silForm.getTpoliq());			
			silVO.setFecpgo(silForm.getFecpgo());
			silVO.setMliqpa(silForm.getMliqpa());
			silVO.setRimpms(silForm.getRimpms());
			silVO.setCorrelab(silForm.getCorrelab());
			
		//adicionales, completar segun  equipo AS			
			silVO.setArchiv(silForm.getArchiv());
			//silVO.setCorrelativ(correlativ);
			silVO.setLichasfec(silForm.getLichasfec());
			silVO.setPagfol(silForm.getPagfol());
			silVO.setPerpag(silForm.getPerpag());
			
			log.info("lichasfec: "+silVO.getLichasfec()+" pagfol: "+silVO.getPagfol()+" " +
				"getPerpag: "+silVO.getPerpag());
			
			log.info("SESSION GET");
			HttpSession session = request.getSession();
			HashMap<String, String> mapDatos = (HashMap<String, String>)session.getAttribute("mapa_datos_SIL");
			
			log.info("folio: "+mapDatos.get("nro_fol"));
			
			log.info("Entrando en método sil_MGR.actualizarLog_SIL(silVO, map).");
			
			LogSIL_Manager sil_MGR = new LogSIL_Manager();
			SIL_VistaErrores_VO silerr = sil_MGR.actualizarLog_SIL(silVO, mapDatos);
			String msgMantenedor="";
			
			//Setea en falso para que siempre se abra Pop-Up despliega mensaje.
			 //silerr.setKeyErrores(false);
			
			log.info("------->" + silerr.getKeyEstado()+ " <--------");

			String flag = "0";
			
			if(silerr.getKeyEstado()==3){
				msgMantenedor = "No se ha podido actualizar el nuevo registro debido a " +
					"que existe otro registro con los mismos datos (Datos clave son número " +
					"de folio, rut trabajador y fecha proceso).";
			}else{
				flag = "1";
				msgMantenedor = "La actualización se realizo correctamente.";
				
				mapDatos = new HashMap<String, String>();
				mapDatos.put("nro_fol", silForm.getNrofol()); 
				mapDatos.put("rut_trabaj", silForm.getRuttrabaj());
				mapDatos.put("perpag", String.valueOf(silForm.getPerpag())); 
				mapDatos.put("lichasfec", String.valueOf(silForm.getLichasfec()));
				mapDatos.put("pag_fol", String.valueOf(silForm.getPagfol()));
				//mapDatos.put("correlativo", String.valueOf(silForm.getCorrelab()));
				mapDatos.put("correlativo", String.valueOf(silForm.getCorrelativ()));
				
				log.info("SESSION SET");
				session.setAttribute("mapa_datos_SIL", mapDatos);
			}
			
			
			request.setAttribute("proceso", "2");
			request.setAttribute("flagAct", flag);
			request.setAttribute("registro_SIL", silVO);
			request.setAttribute("glosaError_SIL", silerr);	
			request.setAttribute("KeyErrores", true);
			request.setAttribute("msgMantenedor", msgMantenedor);
			request.setAttribute("codPlano", "SIL");
			forward = mapping.findForward("success_actualizar_SIL");
		} catch (Exception e) {			
			log.error("* * * * * [ACTION: SIL, CATCH: ]"+e.getMessage()+"* * * * *");
			return mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}//end actualizar_sil
		
	/**
	 * Actions para realizar búsqueda de un único registro Sil.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mostrarLog_SIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: SIL, mostrarLOG_SIL()]* * * * *");
		ActionForward forward = new ActionForward(); // return value
		SIL_VistaErrores_VO silerr=new SIL_VistaErrores_VO();
		
		try {
			Paginacion_VO pag = new Paginacion_VO();
			ArrayList<SIL_VO> silList;
			SIL_Manager sil_MGR=new SIL_Manager();
			LogSIL_Manager silLog_MGR = new LogSIL_Manager();
			SIL_Form silForm= (SIL_Form)form;
			SIL_VO silVO=new SIL_VO();
			String msgMantenedor="";
			
			if(silForm!=null){
				silVO.setNrofol(silForm.getNrofol());
				silVO.setRuttrabaj(silForm.getRuttrabaj());
				silVO.setPerpag(silForm.getPerpag());
				silVO.setLichasfec(silForm.getLichasfec());
				silVO.setPagfol(silForm.getPagfol());
				
				log.info("Rut: "+silVO.getRuttrabaj()+" Folio: "+silVO.getNrofol()+ " FechaPrceso: "+silVO.getPerpag()+" " +
					"Lichasfec: "+silVO.getLichasfec()+" Pagfol: "+silVO.getPagfol());
				
				HashMap<String, String> mapDatos = new HashMap<String, String>();
				mapDatos.put("nro_fol", silForm.getNrofol()); 
				mapDatos.put("rut_trabaj", silForm.getRuttrabaj());
				mapDatos.put("perpag", String.valueOf(silForm.getPerpag())); 
				mapDatos.put("lichasfec", String.valueOf(silForm.getLichasfec()));
				mapDatos.put("pag_fol", String.valueOf(silForm.getPagfol()));
				//mapDatos.put("correlativo", String.valueOf(silForm.getCorrelab()));
				mapDatos.put("correlativo", String.valueOf(silForm.getCorrelativ()));
				
				
				log.info("SESSION SET");
				HttpSession session = request.getSession();
				session.setAttribute("mapa_datos_SIL", mapDatos);
				
				if(silVO.getNrofol()!=null && silVO.getNrofol()!="" && silVO.getRuttrabaj()!=null 
					&& silVO.getRuttrabaj()!="" && silVO.getPerpag()!=0 && silVO.getLichasfec()!=0){
					
					//Busca listado a mostrar.
					log.info("Ingreso al buscar lista.");
					//silList = sil_MGR.buscar_Lista(silVO, pag);
					silList = sil_MGR.buscar_ListaLog(silVO, pag);
					
					log.info("Tamaño encontrado una vez se busco = " + silList.size());
					
					if(silList.size()!=0){
						silVO = silList.get(0);
						
						//Mostrar datos error.
						String opcion="mostrarErrores";
						silerr = silLog_MGR.marcarErrores(silVO,opcion);
						
						silerr.setKeyErrores(true);
						request.setAttribute("KeyErrores", silerr.isKeyErrores());
					}else{
						msgMantenedor="Registro no encontrado.";
						request.setAttribute("msgMantenedor",msgMantenedor );
						request.setAttribute("codPlano", "SIL");
						request.setAttribute("KeyErrores", false);
					}
				}
			}
			
			request.setAttribute("registro_SIL", silVO);
			//Enviar glosa error en blanco
			request.setAttribute("glosaError_SIL", silerr);	
			request.setAttribute("proceso", "2");
			
			forward = mapping.findForward("success_mostrar_SIL");			
		} catch (Exception e) {
			log.error("Error mostrarLog_SIL :" + e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (forward);
	}//end buscar_SIL
}//end class
