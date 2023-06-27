package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.InputUpLoadFileVO;
import cl.araucana.bienestar.bonificaciones.vo.ResultUpLoadFileVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.ui.MultiPartRequest;

/**
 * @version 	1.0
 * @author
 */
public class UpLoadFileAction extends Action {

	private static final String FILE_DIR = "./temp";
	private static final int FILE_MAXSIZE = 1 * 1024 * 1024; // 1 Megas

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(UpLoadFileAction.class);
		String target=null;
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		
		// Upload file
		MultiPartRequest multi = new
		MultiPartRequest(request, FILE_DIR, FILE_MAXSIZE);
				
		// Toma parametros
		String codigoConvenio = multi.getParameter("codigoConvenio");
		String codigoProducto = multi.getParameter("codigoProducto");
		String nombreConvenio = multi.getParameter("nombreConvenio");
		String nombreProducto = multi.getParameter("nombreProducto");
		
		
		long codConvenio=0;
		long codProducto=0;
		
		if(codigoConvenio!=null)
			codConvenio=Long.parseLong(codigoConvenio);
			
		if(codigoProducto!=null)
			codProducto=Long.parseLong(codigoProducto);

		logger.debug("Codigo Convenio: "+codConvenio);
		logger.debug("Codigo Producto: "+codProducto);
		
		if (codConvenio==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe seleccionar un Convenio");
			
		if (codProducto==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe seleccionar un Producto");
		
		Enumeration files = multi.getFileNames();
		String name = null;
		String filename = null;
		
		if (files.hasMoreElements()) {
			name = (String) files.nextElement();
			filename = multi.getFilesystemName(name);
		}
		
		if (filename==null)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe indicar el archivo");
		
		
		logger.debug("Filename="+filename);
		File dir = new File(FILE_DIR);
		FileReader f = new FileReader(dir + File.separator + filename);
		logger.debug("Archivo a cargar: "+f);
		
		BufferedReader file = new BufferedReader(f);
		logger.debug("BufferReader: "+file);
		
		String line = null;
		Stack lines = new Stack();
		while ( (line = file.readLine()) != null) {
			lines.add(line);
		}
		
		logger.debug("Stack Size: "+lines.size());
		
		if (lines== null || lines.size()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "El archivo no se ha encontrado o no posee información dentro de él");
		
		// Process STACK
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		
		InputUpLoadFileVO inputData = new InputUpLoadFileVO();
		inputData.setCodigoConvenio(codConvenio);
		inputData.setCodigoProducto(codProducto);
		inputData.setFilename(filename);
		inputData.setLines(lines);
		inputData.setNombreConvenio(nombreConvenio);
		inputData.setNombreProducto(nombreProducto);
		
		inputData.setUser(userinformation.getUsuario());
		
		ResultUpLoadFileVO resultUpLoadFile = delegate.updLoadFile(inputData);
				
		request.getSession(false).setAttribute("result",resultUpLoadFile);

  		target="resultUpLoadFile";
	
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
