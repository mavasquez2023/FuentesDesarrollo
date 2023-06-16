package cl.araucana.ctasfam.presentation.struts.actions;

 
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.Parametros;
 



public class AceptaPropuestaAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
	 ActionForward forward=new ActionForward();
	 ActionMessages errors=new ActionMessages();
	 String mensaje=null;
	 
     AceptaPropuestaForm form1=(AceptaPropuestaForm)form;
	 
	 String options=form1.getOpcion();
	 String rut=form1.getRuts();
	
	 //AraucanaJdbcDao dao=new AraucanaJdbcDao();
	 Encargado enc=new Encargado();
	 AceptaPropuestaForm oestado=new AceptaPropuestaForm();
	 Encargado encargado = (Encargado) request.getSession().getAttribute("edocs_encargado");
	 String rol= (String)request.getSession().getAttribute("rol");
	 
	 String periodo=Parametros.getInstance().getParam().getPeriodoProceso();
	

	   
	 
	 try{
		 
		 if(rol.equals("Empresa")){
			 rut= rut.replaceAll("\\.", "");
			// System.out.println("rutestado " + rut);
			 
			 //Se revisa si ya envío archivo para informar
			 Mejoras2016DaoImpl estProDao = new Mejoras2016DaoImpl();
			 int rutemp= Integer.parseInt((rut.split("-"))[0]);
			 PeticionProcesamientoDto petPro = estProDao.selectPeticionProcesamiento(rutemp, Integer.parseInt(periodo));
			 if (petPro!= null && petPro.getEstado().equals("F")){
				 request.setAttribute("estado", petPro.getEstado());
			 }
			 request.setAttribute("rutestado",rut);
			 request.getSession().setAttribute("rutestado",(rut.split("-"))[0]);
		 }
		 periodo= periodo.substring(0, 4);
		 request.setAttribute("periodo_anterior",(Integer.parseInt(periodo)));

		 
	 }catch(Exception ex)
	 {
		 ex.printStackTrace();
		 mensaje="La sesión expiró o el sistema encontro una excepción";
		 errors.add("name", new ActionMessage("id"));
	 }
	 
	 if(!errors.isEmpty()){
		 
		 request.setAttribute("mensaje", mensaje);
		 forward=mapping.findForward("onError");
		 
	 }
	 else{
		 if(rol.equals("Ejecutivo")){
			 forward=mapping.findForward("onEjecutivo");
		 }else{
			 forward=mapping.findForward("onSuccess");
		 }
	 }
	 return forward;
	 
	}

}
