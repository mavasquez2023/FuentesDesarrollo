package cl.araucana.fonasa.main.actions;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cl.araucana.fonasa.business.impl.ProcesaArchivoThread;
import cl.araucana.fonasa.dao.VO.ErrorUploadVO;
import cl.araucana.fonasa.dao.VO.FormularioVO;
import cl.araucana.fonasa.main.forms.FileUploadForm;
import cl.araucana.fonasa.utils.ConstantsUtil;



/**
 * @version 1.0
 * @author
 */
public class ValidacionFonasaMasivaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value	
		HttpSession sesion = request.getSession();
		String usuario= (String)sesion.getAttribute("usuario");		
		request.setAttribute("menu", "fonasa");
		String accion = request.getParameter("accion");
		
		if(accion==null){
			accion="";
		}
		String destinatarios= ConstantsUtil.MAIL_PROPERTIES.getString("app.mail.usuario");
		if(accion.equals("menu")){
			request.setAttribute("correos", destinatarios);
			forward = mapping.findForward("success");
			return forward;
		}
		
				
		try {
			destinatarios= request.getParameter("correos");
			destinatarios= destinatarios.replaceAll(",", ";");
			request.setAttribute("correos", destinatarios);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				FileUploadForm fileUploadForm = (FileUploadForm)form;
				
			    FormFile file = fileUploadForm.getFile();
			    String fileName = file.getFileName();
			    logger.info("Procesando archivo:" + fileName);
			    
			    InputStream fileContent= file.getInputStream();
			    BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
				String line = null;
				
				List<ErrorUploadVO> erroneos= new ArrayList<ErrorUploadVO>();
				
				List<FormularioVO> listaTipoNumForm= new ArrayList<FormularioVO>();
				int numerolinea=1;
				boolean error_formato= false;
				boolean titulo;
				while ((line = reader.readLine()) != null) {
					
					try {
						line= line.trim();
						if(!line.equals("")){
							if(line.substring(line.length()-1).equals(";")){
								line= line.substring(0, line.length()-1);
							}
							line= line.replaceAll(",", ";");
							line= line.replaceAll("\"", "");
							String[] registro= line.split(";");
							titulo= false;
							if(numerolinea==1){
								String numform= registro[2];
								try {
									Integer.parseInt(numform);
								} catch (Exception e) {
									titulo=true;
								}
							}
							if(registro.length== 3){
								if(titulo==false){
									//RUT Afliado
									String rutAfiliado= registro[0];
									//Tipo Formulario
									String tipoFormulario= registro[1];
									if(!tipoFormulario.equals("1") && !tipoFormulario.equals("2")){
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setNumeroFormulario(registro[2]);
										error.setObservacion("Tipo Formulario no válido");
										erroneos.add(error);
										error_formato=true;
									}

									//Número Formulario
									String numeroFormulario= registro[2];
									//RUT no numérico
									try {
										Integer.parseInt(numeroFormulario);
									} catch (NumberFormatException e) {
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setNumeroFormulario(numeroFormulario);
										error.setObservacion("Número Formulario No Válido");
										erroneos.add(error);
										error_formato=true;
									}

									if(!error_formato){
										FormularioVO formu= new FormularioVO();
										formu.setTipoFormulario(Integer.parseInt(tipoFormulario));
										formu.setNumeroLicencia(Integer.parseInt(numeroFormulario));
										formu.setRutAfiliado(rutAfiliado);

										listaTipoNumForm.add(formu);
										//registrosOK.add(ufileTO);
									}else{
										logger.warn("Error en formato archivo");
										listaTipoNumForm=null;
									}
								}

							}else{
								ErrorUploadVO error= new ErrorUploadVO();
								error.setNumerolinea(numerolinea);
								error.setNumeroFormulario("");
								error.setObservacion("Número columnas incorrecto");
								erroneos.add(error);
								error_formato=true;
								logger.warn("Número columnas incorrecto");
							}
						}
						numerolinea++;
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Error linea:" + numerolinea + ", Mensaje:"+ e.getMessage());
						ErrorUploadVO error= new ErrorUploadVO();
						error.setNumerolinea(numerolinea);
						error.setNumeroFormulario("");
						error.setObservacion("Error desconocido al leer registro");
						erroneos.add(error);
					}
				}
				
				if(erroneos.size()>0){
					request.setAttribute("errores", erroneos);
					request.setAttribute("error", "-1");
					forward = mapping.findForward("success");
					return (forward);
				}else{
					ProcesaArchivoThread procesa_lista= new ProcesaArchivoThread(listaTipoNumForm, usuario, fileName, destinatarios);
					procesa_lista.start();
					
					request.setAttribute("error", "0");
					forward = mapping.findForward("success");
					return (forward);

				}
			}
			
		

			

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en upload archivo, " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		sesion.removeAttribute("repetido");
		forward = mapping.findForward("success");
		return (forward);

	}
	
	
	 
}
