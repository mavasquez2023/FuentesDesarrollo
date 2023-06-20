package cl.laaraucana.silmsil.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

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

import cl.laaraucana.silmsil.forms.EjecucionList_Form;
import cl.laaraucana.silmsil.forms.ProcesarForm;
import cl.laaraucana.silmsil.forms.SIL_Form;
import cl.laaraucana.silmsil.manager.ManagerArchivosPlanos;
import cl.laaraucana.silmsil.manager.ManagerInicio;
import cl.laaraucana.silmsil.manager.ManagerProcesar;
import cl.laaraucana.silmsil.manager.SIL_Manager;
import cl.laaraucana.silmsil.manager.Trimestre;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.util.UtilProcesar;
import cl.laaraucana.silmsil.vo.ILFLM052VO;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;
import cl.laaraucana.silmsil.vo.RespuestaEscritura_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;

/**
 * @version 1.0
 * @author
 */
public class ProcesoAction extends DispatchAction
{
	private Logger log = Logger.getLogger(this.getClass());
	private String msj = null;
	
	
	/**
	 * Redirecciona dependiendo de acción usuario.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward asignar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("Ingreso al actions Asignar.");
		ActionForward forward = new ActionForward(); // return value
		ProcesarForm forms = (ProcesarForm)form;
		
		ArrayList checkBoxSIL = new ArrayList(); ArrayList listaSil = new ArrayList();
		ArrayList checkBoxLM = new ArrayList(); ArrayList listaLm = new ArrayList();
		
		ArrayList<Trimestre> listaTabla = new ArrayList<Trimestre>();
		
		try {
			//Datos provenientes de la interfaz gráfica.
			HttpSession sesion = request.getSession(true);
			String usuario = (String)sesion.getAttribute("login");
			String ahno = (forms.getSelAnno().length()==0?getDateAhno():forms.getSelAnno());
			
			log.info("Usuario : ["+usuario+"] - Año : ["+ ahno+"].");
			
	    	//Carga lista de procesos SIL y LM.
			checkBoxSIL.add(forms.getChk_SIL01()); checkBoxSIL.add(forms.getChk_SIL02()); checkBoxSIL.add(forms.getChk_SIL03()); 
			checkBoxSIL.add(forms.getChk_SIL04()); checkBoxSIL.add(forms.getChk_SIL05()); checkBoxSIL.add(forms.getChk_SIL06()); 
			checkBoxSIL.add(forms.getChk_SIL07()); checkBoxSIL.add(forms.getChk_SIL08()); checkBoxSIL.add(forms.getChk_SIL09()); 
			checkBoxSIL.add(forms.getChk_SIL10()); checkBoxSIL.add(forms.getChk_SIL11()); checkBoxSIL.add(forms.getChk_SIL12());
			
			checkBoxLM.add(forms.getChk_LM01()); checkBoxLM.add(forms.getChk_LM02()); checkBoxLM.add(forms.getChk_LM03()); 
			checkBoxLM.add(forms.getChk_LM04()); checkBoxLM.add(forms.getChk_LM05()); checkBoxLM.add(forms.getChk_LM06()); 
			checkBoxLM.add(forms.getChk_LM07()); checkBoxLM.add(forms.getChk_LM08()); checkBoxLM.add(forms.getChk_LM09()); 
			checkBoxLM.add(forms.getChk_LM10()); checkBoxLM.add(forms.getChk_LM11()); checkBoxLM.add(forms.getChk_LM12());
			
			
			//Valor que sirve para redireccionar.
			int asigna = Integer.parseInt(forms.getHd_Asignar());
			
			log.info("***** Asignar : " + asigna);
			
			switch(asigna){
			case 1:
				
				//Llama a método procesar.	
				
				ArrayList listaEjecucion=this.procesarListaEjecucion(ahno, checkBoxSIL, checkBoxLM, usuario);
				
				request.setAttribute("listaEjecucion", listaEjecucion);
				request.setAttribute("keyEjecucion", true);
				request.setAttribute("KeyEstados", true);					
				forward =  mapping.findForward("success_ejecucion");
				return forward;				
				//break;
			case 4: 
				//Llama a método refrescar para actualizar lista a desplegar.
				listaTabla = this.refrescar(ahno);  
				
				if(listaTabla.size()==0){
					//Seteo de mensaje de error.
					String msgError = "Ha ocurrido un error, no se encontraron elementos a mostrar.";
					
					//Envíar a página de error.
					request.setAttribute("msgError", msgError);
					forward =  mapping.findForward("error");
				}
				break;
			case 5:
				//Creación de planos.
				String procesoPeriodo=forms.getKeyProcesoPeriodo();				
				log.info("* *[creando planos]* *");				
				/*
				boolean planos = this.generarPlano(ahno, checkBoxSIL, checkBoxLM, usuario);
				if(planos==false){
					//Seteo de mensaje de error.
					String msgError = "Error en la generación de archivo(s) plano(s).";
					
					//Envíar a página de error.
					request.setAttribute("msgError", msgError);
					forward =  mapping.findForward("error");
				}
				*/
				//Obtiene listado actualizado a mostrar por pantalla.
				
				
				ArrayList<RespuestaEscritura_VO> listaDescarga=this.procesarListaDescarga(ahno, checkBoxSIL, checkBoxLM, usuario,procesoPeriodo);
				
				log.info("* * * * * [listaDescarga: "+listaDescarga.size()+ "\n* * * * * [checkBoxSIL: "+checkBoxSIL.size()+
					"\n* * * * * [checkBoxLM: "+checkBoxLM.size()+"\n* * * * * [procesoPeriodo: "+procesoPeriodo);
				
				RespuestaEscritura_VO re=new RespuestaEscritura_VO();
				
				re=(RespuestaEscritura_VO) listaDescarga.get(listaDescarga.size()-1);
				listaDescarga.remove(listaDescarga.size()-1);
				
				request.setAttribute("listaDescarga", listaDescarga);
				request.setAttribute("nombreZip", re.getNombreArchivo());
				request.setAttribute("rutaOrigenZip", re.getRutaArchivo());
				
				forward =  mapping.findForward("success_Descarga");
				//listaTabla = this.refrescar(ahno);
				
				return forward;
			default: 
				//Mensajes de error.
				log.info("Se ha ingresado a una opción no válida del menú del aplicativo.");
				String msgError = "Se ha ingresado a una opción no válida del menú del aplicativo.";
				
				//Envíar a página de error.
				request.setAttribute("msgError", msgError);
				forward =  mapping.findForward("error");				
				break;
			}
			request.setAttribute("selAhno", ahno);
			request.setAttribute("listaTabla", listaTabla);
			forward =  mapping.findForward("index");
			
		} catch (Exception ex) {
			log.error("Error, actions asignar" + ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}

	public ActionForward reprocesar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: reprocesar()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			ArrayList<SIL_VO> silList=new ArrayList<SIL_VO>();
			ManagerProcesar mp=new ManagerProcesar();
			EjecucionList_Form ejForm= (EjecucionList_Form)form;
			boolean keyEjecucion=false;
			if(ejForm!=null){
				String concatAux=ejForm.getConcat();
				
				log.info("concat: "+concatAux);
				
				String aux[]=concatAux.split("#");
				int vc=0;
				ArrayList listaEjecucion=new ArrayList();
				for(vc=0;vc<(aux.length);vc++){
					listaEjecucion.add(aux[vc]);
				}
				if(listaEjecucion.size()>0){
					keyEjecucion=mp.reprocesarPeriodos_SIL_LM(listaEjecucion);
				}
			}
			log.info("* * * * * [ACTION END reprocesar()]* * * * *");
		}catch(Exception e){
			log.error("Error reprocesar :" + e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (null);
	}//end reprocesar
	
	public ActionForward revalidar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("* * * * * [ACTION: revalidar()]* * * * *");
		ActionForward forward = new ActionForward(); // return value				
		try {
			ArrayList<SIL_VO> silList=new ArrayList<SIL_VO>();
			ManagerProcesar mp=new ManagerProcesar();
			EjecucionList_Form ejForm= (EjecucionList_Form)form;
			boolean keyEjecucion=false;
			if(ejForm!=null){
				String concatAux=ejForm.getConcat();
				
				log.info("concat: "+concatAux);
				
				String aux[]=concatAux.split("#");
				int vc=0;
				ArrayList listaEjecucion=new ArrayList();
				for(vc=0;vc<(aux.length);vc++){
					listaEjecucion.add(aux[vc]);
				}
				keyEjecucion=mp.revalidarPeriodos_SIL_LM(listaEjecucion);
			}			
		}catch(Exception e){
			log.error("Error revalidar :" + e.getMessage());
			e.printStackTrace();
		}
		// Finish with
		return (null);
	}//end revalidar
	
	/**
	 * Método procesar, genera carga y validación de procesos SIL y LM.
	 * @param ahno
	 * @param SIL
	 * @param LM
	 * @param usuario
	 * @return
	 */
	/*
	private String procesar(String ahno, ArrayList SIL, ArrayList LM, String usuario){
		String sil; String lm;
		try{
			System.out.println("Entro al procesar.");
			ManagerProcesar manager = new ManagerProcesar();
			sil = manager.procesarSIL(ahno, SIL, usuario);
			lm = manager.procesarLM(ahno, LM, usuario);			
			System.out.println("Sil =" + sil); System.out.println("Lm =" + lm);			
			msj = (sil.length()==0?"Error, no se han generado proceso de manera correcta":sil);
			msj = (lm.length()==0?"Error, no se han generado proceso de manera correcta":lm);			
			System.out.println("MSJ = " + msj);			
			return msj;
		}
		catch(Exception ex){
			log.error("Error, procesar()" + ex.getMessage());
			ex.printStackTrace();
		}
		return msj;
	}
	*/
	
	/**
	 * metodo que instanciara a manager de procesos para obtener lista 
	 * a reprocesar o revalidar.
	 * los que tengan procesos anteriores a validacion seran ejecutados nuevamente mediante (carga/validacion).
	 * **/
	private ArrayList procesarListaEjecucion(String ahno, ArrayList SIL, ArrayList LM, String usuario){
		ArrayList listaEjecucion = new ArrayList();
		try{
			log.info("Entro al procesar.");
			
			ManagerProcesar manager = new ManagerProcesar();
			listaEjecucion.addAll(manager.procesarSIL_ejecucion(ahno, SIL, usuario));
			
			listaEjecucion.addAll(manager.procesarLM_ejecucion(ahno, LM, usuario));
			
		}catch(Exception ex){
			log.error("Error, procesar()" + ex.getMessage());
			ex.printStackTrace();
		}
		return listaEjecucion;
	}//end procesarListaEjecucion
	
	/**
	 * Método para procesar la lista de archivos planos a descargar.
	 * @param ahno
	 * @param SIL
	 * @param LM
	 * @param usuario
	 * @param procesoPeriodo
	 * @return
	 */
	private ArrayList procesarListaDescarga(String ahno, ArrayList SIL, ArrayList LM, String usuario,String procesoPeriodo){
		ArrayList listaDescarga = new ArrayList();
		try{			
			log.info("ACTION: procesarListaDescarga"+"\n ACTION: procesoPeriodo= "+procesoPeriodo);
			
			String aux[];
			if(procesoPeriodo!=null && !procesoPeriodo.equalsIgnoreCase("")){
				SIL.clear();
				LM.clear();
				
				log.info("ACTION: clear "+procesoPeriodo+"\n * *[keyProcesoPeriodo]* *"+ procesoPeriodo);
				String auxProceso[]=procesoPeriodo.split("_");
				if(auxProceso[0].equalsIgnoreCase("SIL")){
					
					SIL=new ArrayList();					
					SIL.add("chk_SIL"+auxProceso[1].substring((auxProceso[1].length()-2),auxProceso[1].length()));
				}else if(auxProceso[0].equalsIgnoreCase("LM")){
					
					LM=new ArrayList();
					LM.add("chk_LM"+auxProceso[1].substring((auxProceso[1].length()-2),auxProceso[1].length()));
				}
			}			
			//boolean planos = this.generarPlano(ahno, SIL, LM, usuario);			
			//listaDescarga.add(0, re);
			ManagerArchivosPlanos planos = new ManagerArchivosPlanos();
			listaDescarga=planos.generarArchivos(SIL, LM, ahno, usuario);	
			//listaDescarga.addAll(manager.procesarSIL_ejecucion(ahno, SIL, usuario));
			//lm = manager.procesarLM(ahno, LM, usuario);
			//listaDescarga.addAll(manager.procesarLM_ejecucion(ahno, LM, usuario));
		}catch(Exception ex){
			log.error("Error, procesar()" + ex.getMessage());
			ex.printStackTrace();
		}
		return listaDescarga;
	}
	
	
	/**
	 * Método para validar proceso SIL y LM si ya ha sido procesado con anterioridad.
	 * @param ahno
	 * @return
	 */
	/*
	private boolean validar(String ahno, ArrayList SIL, ArrayList LM, String usuario){
		try{
			System.out.println("Entro al validar.");
			ManagerProcesar manager = new ManagerProcesar();
			
			return true;
		}
		catch(Exception ex){
			log.error("Error, validar" + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	*/
	
	/**
	 * Método para refrescar y actualizar listado mostrado en interfaz gráfica.
	 * @param ahno
	 * @return
	 */
	private ArrayList<Trimestre> refrescar(String ahno){
		ArrayList<Trimestre> lista = new ArrayList<Trimestre>();
		try{
			
			ManagerInicio manager = new ManagerInicio();
			lista = manager.cargarListadoInterfaz(ahno);
			
			return lista;
		}
		catch(Exception ex){
			log.error("Error, refrescar" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Genera archivo planos.
	 * @param ahno
	 * @param lista
	 * @param usuario
	 * @return
	 */
	/*
	public boolean generarPlano(String ahno, ArrayList SIL, ArrayList LM, String usuario){
		try{
			//Creación de archivos planos.
			ManagerArchivosPlanos planos = new ManagerArchivosPlanos();
			//boolean sil = planos.generarPlanoSIL(SIL, ahno, usuario);
			//boolean lm = planos.generarPlanoLM(LM, ahno, usuario);
			//boolean ce=planos.generarPlanoEstadistico(SIL, LM, ahno, usuario);			
			
			//reamplazar if por mensaje
			//aqui ->
			return true;			
			
		}catch(Exception ex){
			log.error("Error generarPlano : " + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	*/
	/**
	 * Método para generar fecha actual.
	 * @return
	 */
	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String date = sdf.format(new Date());
		return date;
	}
	
	/**
	 * Método para generar año actual.
	 * @return
	 */
	private static String getDateAhno(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(new Date());
		return date;
	}
}
