package cl.araucana.cotcarserv.main.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.ErrorUploadVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.main.forms.FileUploadForm;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.servicios.extincionSIAGF.CredentialWSTGR;
import cl.laaraucana.servicios.extincionSIAGF.DataResponseWS;
import cl.laaraucana.servicios.extincionSIAGF.DataWS;
import cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFPortBindingStub;
import cl.laaraucana.servicios.extincionSIAGF.ResponseWS;
import cl.recursos.EnviarMail;


/**
 * @version 1.0
 * @author
 */
public class CesacionMasivaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;		
		HttpSession sesion = request.getSession();
		String usuario= (String)sesion.getAttribute("usuario");
		String rol= (String)sesion.getAttribute("rol");
		
		request.setAttribute("menu", "cesacion");
		
		if(sesion.getAttribute("empresas")==null && sesion.getAttribute("rol")==null){
			forward = mapping.findForward("init");
			return forward;
		}
		
		String rutEmpresa = request.getParameter("rutEmpresa");
		String accion = request.getParameter("accion");
		String razonSocial="";
		if(accion==null){
			accion="";
		}
		
		//Si no es Ejecutivo muestra selección de empresa
		if(!rol.equals(CertificadoConst.RES_CERTIFICADOS.getString("ldap.cotycar.rol.usuario"))){
			if(accion.equals("volver")){
				forward = mapping.findForward("seleccion");
				return forward;
			}
			if(rutEmpresa==null || rutEmpresa.equals("") || accion.equals("menu")){
				forward = mapping.findForward("seleccion");
				return forward;
			}

			Map<String, String> indiceEmpresas= (TreeMap<String, String>)sesion.getAttribute("empresas");
			razonSocial= indiceEmpresas.get(rutEmpresa);
			sesion.setAttribute("rutEmpresa", rutEmpresa);
			sesion.setAttribute("razonSocial", razonSocial);
		}else{
			if(accion.equals("menu")){
				forward = mapping.findForward("success");
				return forward;
			}
		}
		
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				FileUploadForm fileUploadForm = (FileUploadForm)form;
				
			    FormFile file = fileUploadForm.getFile();
			    String fileName = file.getFileName();
			    
			    InputStream fileContent= file.getInputStream();
			    BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
				String line = null;
				
				List<ErrorUploadVO> erroneos= new ArrayList<ErrorUploadVO>();
				Map<String, String> listaRuts= new HashMap<String, String>();
				List<String> listaEmpTra= new ArrayList<String>();
				int numerolinea=1;
				boolean error_formato= false;
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
							if(registro.length== 12){


								//CotizacionesVO ufileTO= new CotizacionesVO();
								if(registro.length>=0 && !registro[0].equalsIgnoreCase("Periodo")){

									//PERIODO
									String periodo= registro[0];
									if(!periodo.equals("")){
										try {
											Integer.parseInt(periodo);
										} catch (NumberFormatException e) {
											ErrorUploadVO error= new ErrorUploadVO();
											error.setNumerolinea(numerolinea);
											error.setRutTrabajador(registro[5] + "-" + registro[6]);
											error.setObervacion("Periodo no válido");
											erroneos.add(error);
											error_formato=true;
										}
									}
									//OFICINA, SUCURSAL
									String oficina= registro[1];
									String sucursal=registro[2];


									//RUT EMPRESA
									String rutemp= registro[3];
									//RUT no numérico
									try {
										Integer.parseInt(rutemp);
									} catch (NumberFormatException e) {
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setRutTrabajador(registro[5] + "-" + registro[6]);
										error.setObervacion("Rut Empresa No Válido");
										erroneos.add(error);
										error_formato=true;
									}
									//RUT Empresa no autorizada
									if(!rol.equals(Constants.ROL_EJECUTIVO)){
										if(!(registro[3]+"-" + registro[4]).equals(rutEmpresa)){
											ErrorUploadVO error= new ErrorUploadVO();
											error.setNumerolinea(numerolinea);
											error.setRutTrabajador(registro[5] + "-" + registro[6]);
											error.setObervacion("Rut Empresa No Corresponde");
											erroneos.add(error);
											error_formato=true;
										}
									}
									//RUT no válido
									if(!RutUtil.IsRutValido(registro[3]+"-" + registro[4])){
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setRutTrabajador(registro[5] + "-" + registro[6]);
										error.setObervacion("Rut Empresa No Válido");
										erroneos.add(error);
										error_formato=true;
									}

									//RUT TRABAJADOR
									String ruttra= registro[5];
									try {
										Integer.parseInt(ruttra);
									} catch (NumberFormatException e) {
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setRutTrabajador(registro[5] + "-" + registro[6]);
										error.setObervacion("Rut Trabajador No Válido");
										erroneos.add(error);
										error_formato=true;
									}

									if(!RutUtil.IsRutValido(registro[5]+"-" + registro[6])){
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setRutTrabajador(registro[5] + "-" + registro[6]);
										error.setObervacion("Rut Trabajador No Válido");
										erroneos.add(error);
										error_formato=true;
									}

									//NONMBRE, APELLIDOS 
									String nombre=registro[7];
									String apellidoPaterno= registro[8];
									String apellidoMaterno= registro[9];

									//FECHAS
									String fechaAfiliacion= registro[10].replaceAll("/", "-");
									if(!fechaAfiliacion.equals("")){
										if(!Utils.isfechaValida(registro[10])){
											ErrorUploadVO error= new ErrorUploadVO();
											error.setNumerolinea(numerolinea);
											error.setRutTrabajador(registro[5] + "-" + registro[6]);
											error.setObervacion("Fecha Afiliación No Válida");
											erroneos.add(error);
											error_formato=true;
										}
									}
									
									//FECHA DESVINCULACIÓN
									String fechaDesv= registro[11].replaceAll("/", "-");
									if(!Utils.isfechaValida(registro[11])){
										ErrorUploadVO error= new ErrorUploadVO();
										error.setNumerolinea(numerolinea);
										error.setRutTrabajador(registro[5] + "-" + registro[6]);
										error.setObervacion("Fecha Cesación No Válida");
										erroneos.add(error);
										error_formato=true;
									}
									if(!error_formato){
										listaRuts.put(ruttra, fechaDesv);
										listaEmpTra.add(rutemp +""+ ruttra);
										//registrosOK.add(ufileTO);
									}else{
										listaRuts=null;
									}



								}
							}else{
								ErrorUploadVO error= new ErrorUploadVO();
								error.setNumerolinea(numerolinea);
								error.setRutTrabajador("");
								error.setObervacion("Número columnas incorrecto");
								erroneos.add(error);
								error_formato=true;
							}
						}
						numerolinea++;
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Error linea:" + numerolinea + ", Mensaje:"+ e.getMessage());
						ErrorUploadVO error= new ErrorUploadVO();
						error.setNumerolinea(numerolinea);
						error.setRutTrabajador("");
						error.setObervacion("Error desconocido al leer registro");
						erroneos.add(error);
					}
				}
				
				if(erroneos.size()>0){
					request.setAttribute("errores", erroneos);
					request.setAttribute("error", "-1");
					forward = mapping.findForward("success");
					return (forward);
				}else{
					ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
					Map param= new HashMap();
					if(!rol.equals(Constants.ROL_EJECUTIVO)){
						if(rutEmpresa!=null){
							param.put("rutEmpresa", rutEmpresa.split("-")[0]);
						}
					}
					Object[] rutsOK= listaRuts.keySet().toArray();
					param.put("rutTrabajador", rutsOK);
					List<CotizacionesVO> trabajadoresNOK=  consultaDAO.consultaListaTrabajadores(param);
					List<CotizacionesVO> trabajadoresOK= new ArrayList<CotizacionesVO>();
					
					for (Iterator iterator = trabajadoresNOK.iterator(); iterator.hasNext();) {
						CotizacionesVO trabajadorVO = (CotizacionesVO) iterator.next();
						if(listaEmpTra.contains(trabajadorVO.getRutEmpresa() + "" + trabajadorVO.getRutTrabajador())){
							String fechaDesvinculacion= listaRuts.get(String.valueOf(trabajadorVO.getRutTrabajador()));
							trabajadorVO.setFechaDesvinculacion(Utils.pasaFechaWEBaSAP(fechaDesvinculacion));
							trabajadorVO.setDateDesvinculacion(Utils.stringToDate(fechaDesvinculacion));
							trabajadorVO.setRazonSocial(razonSocial);
							trabajadoresOK.add(trabajadorVO);
						}						
					}
					
					logger.info("Update Estado Trabaajador");
					int resultado= consultaDAO.updateTrabajadores(trabajadoresOK);
					if(resultado==0){
						request.setAttribute("error", "1");
						forward = mapping.findForward("success");
						return (forward);
					}
					logger.info("Update Estado Cargas");
					int resultado_cargas= consultaDAO.updateCargas(trabajadoresOK);
					
					//Insert Bitácora
					String origen="E";
					if(rol.equals(CertificadoConst.RES_CERTIFICADOS.getString("ldap.cotycar.rol.usuario"))){
						origen="C";
					}
					for (Iterator iterator = trabajadoresOK.iterator(); iterator
							.hasNext();) {
						CotizacionesVO ok = (CotizacionesVO) iterator
								.next();
						insertBitacora(usuario, ok, origen);
					}
					
					//Cesar trabajadores SIAGF
					cesarTrabajadorWS(trabajadoresOK);
					
					request.setAttribute("resultado", resultado);
					request.setAttribute("error", "0");
					trabajadoresOK.add(0, new CotizacionesVO());
					sesion.setAttribute("certificado_PDF", trabajadoresOK);
					
					//Se respalda archivo
					//Get the servers upload directory real path name
					String filePath = 
							getServlet().getServletContext().getRealPath("/") +"/upload";

					//create the upload folder if not exists
					File folder = new File(filePath);
					if(!folder.exists()){
						folder.mkdir();
					}		    

					if(!("").equals(fileName)){  

						logger.info("Server path:" +filePath);
						File newFile = new File(filePath, fileName);

						if(!newFile.exists()){
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(file.getFileData());
							fos.flush();
							fos.close();
						}  

						request.setAttribute("uploadedFilePath",newFile.getAbsoluteFile());
						request.setAttribute("uploadedFileName",newFile.getName());
					}
				}
			}
			
		

			

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en upload archivo");
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		sesion.removeAttribute("repetido");
		forward = mapping.findForward("success");
		return (forward);

	}
	
	 public void insertBitacora(String usuario, CotizacionesVO traVO, String origen){
	    	try {
	    		if (traVO.getRutTrabajador()!= 0){
	    			ConsultaServicesDAO dao= new ConsultaServicesDAO();
	    			Map param= new HashMap();
	    			param.put("usuario", usuario);
	    			param.put("accion", "CESACION");
	    			param.put("rutTrabajador", traVO.getRutTrabajador());
	    			param.put("dvTrabajador", traVO.getDvTrabajador());
	    			param.put("rutEmpresa", traVO.getRutEmpresa());
	    			param.put("dvEmpresa", traVO.getDvEmpresa());
	    			param.put("fechaAfiliacion", Utils.stringToDateAS(traVO.getFechaAfiliacion()));
	    			param.put("fechaDesvinculacion",traVO.getDateDesvinculacion());
	    			param.put("origen", origen);

	    			int ok_bita= dao.insertBitacora(param);
	    			logger.info("Registrado en bitácora: " + ok_bita);
	    		}
			} catch (Exception e) {
				logger.warn(">>Error al registrar en bitácora consulta informe");
			}
	    }
	 
//		Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
	    public void enviarMail( String[] mailDestinatarios, String[] mailEncargados, String periodo, String adjunto) {
	    	String subject="";
			try {			
				EnviarMail mail= new EnviarMail("aplica", "aplica");
				StringBuffer body= new  StringBuffer();
				subject= "Informe Cargas Autorizadas y Compensadas La Araucana" ;
				body.append("Estimado: se adjunta archivo con información de cargas compensadas, periodo:" + periodo+ "<BR>");
				body.append("<br><br>");
				body.append("Saluda atte. a Ud. "+"<BR>");
				body.append("La Araucana - Soluciones Sociales.");
				mail.attach(adjunto);
				mail.mailTo("aplica@laaraucana.cl", mailDestinatarios, mailEncargados, null , subject, body.toString());

				System.out.println(".............. EMAIL GENERADO .................... " );

				}catch(Exception e) {
					System.out.println("CAI EN MAIL  " );
					e.printStackTrace();
				}
	 	 }
	    
	    public void cesarTrabajadorWS(List<CotizacionesVO> trabajadores){
	    	final int RESPUESTA_OK= 1;
	    	try {
	    		String ep=CertificadoConst.RES_CERTIFICADOS.getString("ep.extincion.tupla");
	    		ExtincionSIAGFPortBindingStub stub= new ExtincionSIAGFPortBindingStub();
		    	stub._setProperty(ExtincionSIAGFPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		    	
		    	CredentialWSTGR credential= new CredentialWSTGR();
		    	credential.setUser(CertificadoConst.RES_CERTIFICADOS.getString("user.ws.ext.siagf"));
		    	credential.setPassword(CertificadoConst.RES_CERTIFICADOS.getString("password.ws.ext.siagf"));
		    	
		    	String token=stub.autenticacionWS(credential);
		    	if(!token.equals("")){
		    		DataWS[] data= new DataWS[trabajadores.size()];
		    		int i=0;
		    		for (Iterator iterator = trabajadores.iterator(); iterator
		    				.hasNext();) {
		    			CotizacionesVO cotizacionesVO = (CotizacionesVO) iterator
		    					.next();
		    			data[i]= new DataWS();
		    			data[i].setFECHA_EMISION(Utils.fechaSAP());
		    			data[i].setFECHA_EXTINCION(cotizacionesVO.getFechaDesvinculacion());
		    			data[i].setRUT_EMPRESA(cotizacionesVO.getRutEmpresa() + "-" + cotizacionesVO.getDvEmpresa());
		    			data[i].setRUT_TRABAJADOR(cotizacionesVO.getRutTrabajador() + "-" + cotizacionesVO.getDvTrabajador());
		    			logger.info("Extinguiendo causante trabajador:" + cotizacionesVO.getRutTrabajador());
		    			i++;
		    		}
		    		ResponseWS responseWS= stub.extingueCausantes(token, data);
					logger.info(responseWS.getCODIGO());
					logger.info(responseWS.getDESCRIPCION());
					if(responseWS.getCODIGO()== RESPUESTA_OK){
						ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
						for (int j = 0; j < responseWS.getRESPUESTA().length; j++) {
							DataResponseWS dataResponse= responseWS.getRESPUESTA(j);
							Map param= new HashMap();
							param.put("rutEmpresa", dataResponse.getRUT_EMPRESA().split("-")[0]);
							param.put("rutTrabajador", dataResponse.getRUT_TRABAJADOR().split("-")[0]);
							param.put("estado", dataResponse.getESTADO());
							consultaDAO.updateEstadoTrabajadorSIAGF(param);

						}
					}
		    	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
