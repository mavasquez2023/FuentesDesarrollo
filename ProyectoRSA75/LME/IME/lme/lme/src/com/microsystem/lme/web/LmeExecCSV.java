/**
 * 
 */
package com.microsystem.lme.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;



import com.microsystem.lme.forms.FileUploadForm;
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.to.ErrorUploadTO;
import com.microsystem.lme.to.LicenciaTO;
import com.microsystem.lme.util.RutUtil;
import com.microsystem.lme.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author 11648834-5
 *
 */
public class LmeExecCSV extends Action {

		protected Logger logger = Logger.getLogger(this.getClass());
		private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
		private SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {
			ActionForward forward = new ActionForward(); // return value
			
			HttpSession sesion = request.getSession();
			System.out.println("En LmeExecCSV");
			request.setAttribute("action", "upload");
			FileUploadForm fileUploadForm = (FileUploadForm)form;
			String ultimoEstado= fileUploadForm.getUltimoEstado();
			
			if(ultimoEstado.equals("72")){
				request.setAttribute("ultimoEstado", "72");
			}else{
				request.setAttribute("ultimoEstado", "");
			}
			try {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(isMultipart){
					
				    FormFile file = fileUploadForm.getFile();
				    String fileName = file.getFileName();
				    String extencionFile = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
				    
				    InputStream fileContent= file.getInputStream();
				    BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
					String line = null;
					
					List registrosOK= new ArrayList();
					StringBuffer errores= new StringBuffer();
					int numerolinea=1;
					boolean error_general= false;
					
					while ((line = reader.readLine()) != null) {
						boolean error_formato= false;
						try {
							line= line.trim();
							if(!line.equals("")){
								if(line.substring(line.length()-1).equals(";")){
									line= line.substring(0, line.length()-1);
								}
								line= line.replaceAll(",", ";");
								line= line.replaceAll("\"", "");
								String[] registro= line.split(";");
								if(registro.length== 3){


									LicenciaTO ufileTO= new LicenciaTO();
									
									if(registro.length>=0){

										//Operador
										try {
											ufileTO.setOperador(Integer.parseInt(registro[0]));
										} catch (NumberFormatException e) {
											error_formato=true;
										}

										//LICENCIA
										String licencia= registro[1];

										try {
											ufileTO.setLicencia(Integer.parseInt(licencia));
										} catch (NumberFormatException e) {
											error_formato=true;
										}
										ufileTO.setDvLicencia(registro[2]);
										if(!RutUtil.IsRutValido(registro[1]+"-" + registro[2])){
											error_formato=true;
										}

										if(!error_formato && !error_general){
											registrosOK.add(ufileTO);
										}else if (error_formato){
											error_general= true;
											registrosOK=null;
											errores.append("linea : " + numerolinea + "<br>");
										}
									}
								}else{
									error_general= true;
									errores.append("linea : " + numerolinea + "<br>");
								}
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Error linea:" + numerolinea + ", Mensaje:"+ e.getMessage());
							error_general= true;
							errores.append("linea : " + numerolinea + "<br>");
						}
						numerolinea++;
					}
					
					if(error_general){
						request.setAttribute("respuesta", "Error en formato archivo: <br>" + errores);
						forward = mapping.findForward("salida");
						return (forward);
					}else{
						Date now = new Date();
						for (Iterator iterator = registrosOK.iterator(); iterator
								.hasNext();) {
							LicenciaTO licencia = (LicenciaTO) iterator.next();
							//se realiza el insert de la tabla  ILFE080
							Ilfe080VO vo = new Ilfe080VO();
							vo.setIdOperador(String.valueOf(licencia.getOperador()));
							vo.setNumLicencia(String.valueOf(licencia.getLicencia()));
							vo.setDigLicencia(licencia.getDvLicencia());
							vo.setFechaConsulta(sdf.format(now));
							vo.setHoraConsulta(shf.format(now));
							vo.setEstado("0");
							String respuesta="";
							if(ultimoEstado.equals("72")){
								respuesta = svc_a.insertIlfe085(vo);
								request.setAttribute("ultimoEstado", "72");
							}else{
								respuesta = svc_a.insertIlfe080(vo);
								request.setAttribute("ultimoEstado", "");
							}
						}
						request.setAttribute("respuesta", "Se han agregado " + registrosOK.size() + " registros a la cola.");
						forward = mapping.findForward("salida");
						return (forward);
					}
				}

			} catch (Exception e) {
				// Rut no valido
				logger.error("Error en upload archivo");
				e.printStackTrace();
				request.setAttribute("respuesta", "Error al upload archivo:" + e.getMessage());				
			}
			forward = mapping.findForward("salida");
			return (forward);

		}
		
	}
