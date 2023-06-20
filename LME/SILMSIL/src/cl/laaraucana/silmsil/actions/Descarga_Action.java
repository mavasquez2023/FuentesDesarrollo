package cl.laaraucana.silmsil.actions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
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

import cl.laaraucana.silmsil.forms.DescargaList_Form;
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
public class Descarga_Action extends DispatchAction
{
	private Logger log = Logger.getLogger(this.getClass());
	
	public ActionForward descargarZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			log.info("* * [ACTION: descargarZip] * *");
			DescargaList_Form dZipForm=(DescargaList_Form) form;
			//String path = getServlet().getServletContext().getRealPath("")+dZipForm.getNombreZip();
			
			String path =dZipForm.getRutaOrigenZip();			
			//path="C:\\SILMSIL\\estadistico\\AS400\\SILMSIL_2014.zip";
			String ruta;
			String nombre=dZipForm.getNombreZip();
			if(nombre.equalsIgnoreCase("ZIP")){
				//no hacer nada.
				log.info("* *  no se genero ningun archivo");
				ActionForward forward = new ActionForward();
				forward =  mapping.findForward("errorDescarga");
				return (forward);
			}else{
				log.info("* * [descargarZip: " + "(ruta="+path+ ") (nombre="+nombre+")] * *");
				
				FileInputStream archivo = new FileInputStream(path);
				int longitud = archivo.available();
				byte[] datos = new byte[longitud];
				archivo.read(datos);
				archivo.close();				
				
				log.info("archivo Close");
				response.setContentType("application/octet-stream");
				response.setCharacterEncoding("ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment;filename=" + nombre);			
				
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(datos);
				ouputStream.flush();
				ouputStream.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return null;
	}//descargarZip
	
}//end class
