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

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.ParametrosTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.Parametros;
 



public class ParametrosSistemaAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
		if(request.getParameter("action")!= null && request.getParameter("action").equals("cpyFile")){
			return cpyFile(mapping, form, request, response);
		}
	 ActionForward forward=new ActionForward();
	 ActionMessages errors=new ActionMessages();
	 String mensaje=null;
	 

	 Mejoras2016DaoImpl dao=new Mejoras2016DaoImpl();

	 String rol= (String)request.getSession().getAttribute("rol");
	 String periodoProceso= (String)request.getParameter("periodoproceso");	   
	 String fechaApertura= (String)request.getParameter("fechaapertura");	
	 String fechaCierre= (String)request.getParameter("fechacierre");
	 String fechaEnvio= (String)request.getParameter("fechaenvio");
	 String copiaAFP64= (String)request.getParameter("copiaAFP64");
	 String tipoDescarga= (String)request.getParameter("tipoDescarga");

	 ParametrosTO params= new ParametrosTO();
	 params.setPeriodoProceso(periodoProceso);
	 params.setFechaApertura(fechaApertura);
	 params.setFechaCierre(fechaCierre);
	 params.setFechaEnvio(fechaEnvio);
	 params.setCopiaAFP64(copiaAFP64);
	 params.setTipoDescarga(tipoDescarga);
	 try{
		 int result=dao.setParams(params);
		 if (result>0){
			 Parametros.getInstance().getParam().setPeriodoProceso(periodoProceso);
			 Parametros.getInstance().getParam().setFechaApertura(fechaApertura);
			 Parametros.getInstance().getParam().setFechaCierre(fechaCierre);
			 Parametros.getInstance().getParam().setFechaEnvio(fechaEnvio);
			 Parametros.getInstance().getParam().setCopiaAFP64(copiaAFP64);
			 Parametros.getInstance().getParam().setTipoDescarga(tipoDescarga);
		 } 
	 }catch(Exception ex)
	 {
		 ex.printStackTrace();
		 mensaje="La sesión expiró o el sistema encontro una excepción";
		 errors.add("name", new ActionMessage("id"));
	 }
	 
	 forward=mapping.findForward("onSuccess");
	 return forward;
	 
	}
public ActionForward cpyFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	ActionForward forward=new ActionForward();
	ActionMessages errors=new ActionMessages();
	String tablacpy= (String)request.getParameter("copiaAFP64");
	// Work with commands on system named "as400"
	String ip=Parametros.getInstance().getParam().getCpyf_ip();
	String user=Parametros.getInstance().getParam().getCpyf_usuario();
	String clave=Parametros.getInstance().getParam().getCpyf_password();
    
	AS400 system = new AS400(ip, user, clave);
    CommandCall command = new CommandCall(system);
    try
    {	
    	String periodo= Parametros.getInstance().getParam().getPeriodoProceso();
    	periodo= periodo.substring(0, 4) + "00";
        // Run the command "CPYF"
        if (command.run("CPYF FROMFILE(AFDTA/AFP64F1) TOFILE(AFDTA/"+ tablacpy + ") MBROPT(*ADD) CRTFILE(*YES) INCREL((*IF AFP7A *GE "+ periodo +")) ") != true)
        {
            // Note that there was an error.
            System.out.println("Command failed!");
        }
        // Show the messages (returned whether or not there was an error.)
        AS400Message[] messagelist = command.getMessageList();
        for (int i = 0; i < messagelist.length; ++i)
        {
            // Show each message.
            System.out.println(messagelist[i].getText());
        }
    }
    catch (Exception e)
    {
        System.out.println("Command " + command.getCommand() + " issued an exception!");
        e.printStackTrace();
    }
    // Done with the system.
    system.disconnectService(AS400.COMMAND);
    forward=mapping.findForward("onSuccess");
	return forward;
}
}
