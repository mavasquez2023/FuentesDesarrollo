package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.resources.util.UtilExcel;

public class DescargarExcelAction extends Action{
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		String mensaje=null;
		 
		 
		 
		UtilExcel utilse=new UtilExcel();
		
		try{
			
			DynaActionForm dForm = (DynaActionForm) form;
			Integer oficina = ((Integer)dForm.get("oficina"));
			String empresa = (String)dForm.get("empresa");
			String temp[]=empresa.split("-");
			empresa=temp[0];
			Integer sucursal = ((Integer) dForm.get("sucursal"));
			
			if(!empresa.equals("")){
			  if(oficina==null||oficina.equals(""))
		        	oficina=new Integer(0);
		        if(sucursal==null||sucursal.equals(""))
		        	sucursal=new Integer(0);
			 
			
		 
		//System.out.println(empresa);
		 
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "inline;filename=Araucana.xls");
		HSSFWorkbook objWB = utilse.generaPlantillaExcel(oficina.intValue(),sucursal.intValue(),Integer.parseInt(empresa));
		OutputStream out = response.getOutputStream();
		objWB.write(out);
		out.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
		}
		
		
		if(!errors.isEmpty()){
			
			request.setAttribute("mensaje", mensaje);
			forward=map.findForward("onError");
			
		}else{
			
			forward=null;
		}
	
		return forward;
	}

}
