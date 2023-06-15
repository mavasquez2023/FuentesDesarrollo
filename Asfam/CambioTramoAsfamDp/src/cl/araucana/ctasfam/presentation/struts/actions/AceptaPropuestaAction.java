package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import org.apache.struts.action.DynaActionForm;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.business.to.EtapasTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Padder;

public class AceptaPropuestaAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
	 ActionForward forward=new ActionForward();
	 ActionMessages errors=new ActionMessages();
	 String mensaje=null;
	 
     AceptaPropuestaForm form1=(AceptaPropuestaForm)form;
	 
	 String options=form1.getOpcion();
	 String ruts=form1.getRutt();
	 String temp[]=ruts.split(";");
	 String rute=null;
	 String rut=null;
	 Vector v=new Vector();
	 List empresas=new ArrayList();
	 AraucanaJdbcDao dao=new AraucanaJdbcDao();
	 Encargado enc=new Encargado();
	 AceptaPropuestaForm oestado=new AceptaPropuestaForm();
	 Encargado encargado = (Encargado) request.getSession().getAttribute("edocs_encargado");
	 
	 int etapafinal=4;

	 Properties Param = new Properties();
	 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
	String periodo=Param.getProperty("PERIODO");
	String proceso=Param.getProperty("PROCESO");
	
	AceptaPropuestaForm acepta=new AceptaPropuestaForm();
	acepta.setProceso(proceso);
	 
		enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
	    String rutenc= String.valueOf(enc.getRut());
	 
	 try{
	 
	  for(int j=0;j<temp.length;j++){
		    rute=temp[j];
		    System.out.println(rute);
		    String temp1[]=rute.split("-");
		    rut="";
			for(int i=0;i<temp1[0].length();i++)
			{
				
				if(temp1[0].charAt(i)!='.'&&temp1[0].charAt(i)!=';')
				{
					rut+=temp1[0].charAt(i);
				}
				
			}
			 
			System.out.println("aqui");
			
		 
			while(rut.length()<8){
				rut="0".concat(rut);
			}
			v.add(rut);
		    }
		
	  FlujoTO flujo=new FlujoTO();
	  
	
	   
	 
	 
	 
	
	
	flujo.setCantregistros(0);
	if(options.equalsIgnoreCase("0")){
		  for(int i=0;i<v.size();i++){
			  flujo.setRutempresa(v.get(i).toString());
			  flujo.setPeriodo(periodo);
			  flujo.setISAJKM94("");
			  flujo.setISAJKM92("CTADMIN");
			  flujo.setEtapa("3");
			  flujo.setRutencargado(rutenc);
			  dao.updateFlujo(flujo);
			  flujo.setISAJKM94("CTADMIN");
			  flujo.setISAJKM92("");
			  flujo.setRutencargado(rutenc);
		      flujo.setOperacion("RECHAZA PROPUESTA");
		      flujo.setEtapa("3");
		      flujo.setNombrearchivo("");
		      dao.InsertaBitacora(flujo);
		      oestado.setEtapa("0");
		  	
		  }
	 }
	 else{
		 for(int i=0;i<v.size();i++){
			  flujo.setRutempresa(v.get(i).toString());
			  flujo.setPeriodo(periodo);
			  flujo.setISAJKM94("");
			  flujo.setISAJKM92("CTADMIN");
			  flujo.setEtapa("3");
			  flujo.setRutencargado(rutenc);
			  dao.updateFlujo(flujo);
			  flujo.setISAJKM94("CTADMIN");
			  flujo.setISAJKM92("");
			  flujo.setRutencargado(rutenc);
		      flujo.setOperacion("ACEPTA PROPUESTA");
		      flujo.setEtapa("3");
		      flujo.setNombrearchivo("");
		      dao.InsertaBitacora(flujo);
		      oestado.setEtapa("1");
		  	
		  }
	 }
		 
	Empresa empresa=new Empresa();
	for (Iterator iter = encargado.getEmpresas().iterator(); iter
	.hasNext();) {
        empresa = (Empresa) iter.next();
String rutEmpresa = "" + empresa.getRut();
	 for(int i=0;i<v.size();i++){
		 if(Integer.parseInt(rutEmpresa)==Integer.parseInt(v.get(i).toString())){
			 empresa.setFlag(1);
			 
		 
	 }

	}
	}
    
    
	  
	
		 
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
		 
		 
	 
		 if(options.equalsIgnoreCase("0")){
			 
			 request.setAttribute("estado", oestado);
			 request.setAttribute("proceso", acepta);
			 forward=mapping.findForward("onSuccess");	 
		 }
		 else
			
		 {  
			 
			 request.setAttribute("estado", oestado);
			 request.getSession().setAttribute("edocs_encargado", encargado);
			 request.getSession().setAttribute("proceso", acepta);
			 
			 forward=mapping.findForward("showForm");
			  
			 
		 }
		 
		 
	 }
	 return forward;
	 
	}

}
