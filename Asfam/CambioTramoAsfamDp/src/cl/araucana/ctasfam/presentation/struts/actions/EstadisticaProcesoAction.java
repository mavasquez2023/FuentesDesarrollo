package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.ctasfam.resources.util.UtilExcel;
import cl.araucana.ctasfam.resources.util.Utils;

public class EstadisticaProcesoAction extends Action{
	 public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
     throws Exception 
     {
		 ActionForward forward=null;
		 UtilExcel utils=new UtilExcel();
		 Utils util = new Utils();
		 
		 String nameFile = "EstadisticaProceso_"+util.getFechaHora()+".xls";

		 response.setContentType("application/vnd.ms-excel");
		 response.addHeader("Content-Disposition", "inline;filename="+nameFile);
		
		 OutputStream out = response.getOutputStream();
		 
		 HSSFWorkbook objWB = utils.estadisticaProcesoXls();
		 
		 objWB.write(out);
		 out.close();
		 
		 return forward;
	 }
}
