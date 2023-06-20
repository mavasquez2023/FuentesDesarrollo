/**
 * 
 */
package cl.araucana.ldap.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import cl.araucana.core.util.Rut;
import cl.araucana.ldap.api.AppRolesVO;
import cl.araucana.ldap.api.RolUsuarioEmpVO;
import cl.araucana.ldap.api.UsuarioVO;
import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;
import cl.araucana.ldap.to.UploadFileTO;
import cl.araucana.ldap.ws.ClienteSMSService;
import cl.laaraucana.sms.ws.MessageOutput;

/**
 * @author usist199
 *
 */
public class UploadServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(UploadServlet.class);
	private final int USUARIO=0;
	private final int EMPRESA=1;
	private final int APPROL=2;
	private final int LISTAUSU=3;
	private static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private String urlsms="";
	private String nuevosms="";
	private String mensajesms="";
	
	public void init(){
		log.info("Parametros");
		nuevosms= mailProperties.getString("app.envio.sms.nuevo");
		log.info("mensaje sms:" + nuevosms);
		mensajesms= mailProperties.getString("app.envio.sms.mensaje");
		log.info("mensaje sms:" + mensajesms);
		//List roles= LdapDB2DAO.obtenerRegistros("lista_roles_LDAP3000", null);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		log.info(">>>>Leyendo Archivo");
		String html = "";
		List<UploadFileTO> empresas= new ArrayList<UploadFileTO>();
		String resultado="";
		int total=0;
		int ok=0;
		int del=0;
		String error="";
		String invalido="";
		Map params= new HashMap();
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				log.info("Es Multipart");
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				log.info("Cantidad de items:" + items.size());
				String fieldName ="";
				for (FileItem item : items) {
					
					if (item.isFormField()) {
						// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
						fieldName = item.getFieldName();
						log.info("fieldName=" + fieldName);
						String fieldValue = item.getString();
						log.info("fieldValue=" + fieldValue);
						params.put(fieldName, fieldValue);
						
						
					} else {
						// Process form file field (input type="file").
						log.info(">>>>FILE");
						fieldName = item.getFieldName();
						params.put("tipo", fieldName);
						log.info("fieldName=" + fieldName);
						String fileName = FilenameUtils.getName(item.getName());
						log.info("fileName=" + fileName);
						InputStream fileContent = item.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
						String line = null;
						
						while ((line = reader.readLine()) != null) {
							log.info(line);
							String rutreg="";
							boolean rutval=true;
							try {
								line= line.trim();
								if(!line.equals("")){
									total++;
									try {
										if(line.substring(line.length()-1).equals(";")){
											line= line.substring(0, line.length()-1);
										}
										line= line.replaceAll(",", ";");
										line= line.replaceAll("\"", "");
										String[] duplex= line.split(";");
										UploadFileTO ufileTO= new UploadFileTO();
										if(duplex.length>=2){
											String[] rutdv= duplex[0].trim().split("-");
											try {
												Integer.parseInt(rutdv[0]);
											} catch (NumberFormatException e) {
												rutval= false;
											}
											String estado= duplex[1].trim();
											if(rutval && rutdv.length==2 && (estado.equals("N") || estado.equals("E"))){
												rutreg= duplex[0];
												Rut rut= new Rut(Integer.parseInt(rutdv[0].replaceAll("\\.", "")), rutdv[1].charAt(0));
												ufileTO.setRut(rut);
												ufileTO.setEstado(estado);
												if(duplex.length==4){
													ufileTO.setCelular(duplex[2].trim());
													ufileTO.setEmail(duplex[3].trim());
												}else if(duplex.length==6){
													ufileTO.setCelular(duplex[2].trim());
													ufileTO.setEmail(duplex[3].trim());
													ufileTO.setNombre(duplex[4].trim());
													ufileTO.setApellido(duplex[5].trim());
												}
												empresas.add(ufileTO);
											}else{
												error+=duplex[0].trim() + ",";
											}
										}else{
											error+=line.trim()+ ",";
										}
									} catch (Exception e) {
										log.error("Mensaje=" + e.getMessage());
										error+=line.trim()+ ",";
									}
								}
							} catch (Exception e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
								log.error("Mensaje:" + e.getMessage());
								invalido+=rutreg + ",";
							}
						}
					}
				}
				
				int tipoConsulta= getTipoConsulta(params.get("tipo").toString());

				for (Iterator iter = empresas.iterator(); iter.hasNext();) {
					UploadFileTO ufileTO = (UploadFileTO) iter.next();
					String rutreg= ufileTO.getRut().getNumber()+ "-" + ufileTO.getRut().getDV();
					
					boolean e_sms=false, e_email=false;
					if(tipoConsulta==LISTAUSU){
						if(ufileTO.getEstado().equals("E")){
							resultado= IngresaUsuarioLDAP.eliminarUsuario(ufileTO.getRut().getNumber()+ "-" + ufileTO.getRut().getDV());
//							se graba campos de auditoria
							insertAuditoria(request.getSession().getAttribute("rutuser").toString(), rutreg, "ELIMINAR", "U",  (e_sms)?"1":"0", (e_email)?"1":"0");
						}
					}
					//Para agregar cualquier tipo de usuario se debe verificar que exusta en rama de usuarios, sino se crea.
					if(ufileTO.getEstado().equals("N")){
						String rut= ufileTO.getRut().getNumber() + "-" + ufileTO.getRut().getDV();
							boolean existe= IngresaUsuarioLDAP.existeUsuario(rut);
							if(!existe){
								UsuarioVO usuario= new UsuarioVO();
								String password= "000" + String.valueOf(Math.round(Math.random()* 9999));
								password= password.substring(password.length()-4);
								usuario.setUsername(rut);
								usuario.setClave(password);
								usuario.setNombres(ufileTO.getNombre());
								usuario.setApellidoPaterno(ufileTO.getApellido());
								usuario.setEmail(ufileTO.getEmail());
								usuario.setTelefono(ufileTO.getCelular());
								usuario.setEstado(ufileTO.getEstado());
								resultado= IngresaUsuarioLDAP.newUsuario(usuario);

								if(resultado.equals("")){

									if( ufileTO.getEmail()!=null && !ufileTO.getEmail().equals("")){
										EnviaMail.enviarMail("Cuenta creada en La Araucana. ",ufileTO.getEmail() , null,FormatoMail.obtenerTextoMailLdapOK(usuario.getUsername(), usuario.getClave(), usuario.getNombres(), usuario.getApellidoPaterno()));
									}
									if( ufileTO.getCelular()!=null && !ufileTO.getCelular().equals("")){
										//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
										//proxy.setEndpoint(urlsms);
										String msgsms= nuevosms.replaceAll("#clave#", usuario.getClave());
										msgsms= msgsms.replaceAll("#rut_ldap#", rutreg);
										
										ClienteSMSService clientesms= new ClienteSMSService();
										MessageOutput output= clientesms.sendMesage(usuario.getUsername(), usuario.getTelefono(), msgsms);
										if(output!=null & output.getStatusCode().equals("SENT")){
											e_sms=true;
											log.info("Mensaje SMS enviado correctamente");
										}
										//e_sms= proxy.enviarSMS(ufileTO.getCelular(), msgsms, "1", codigo_negocio);
									}
								}
								//se graba campos de auditoria
								insertAuditoria(request.getSession().getAttribute("rutuser").toString(), rutreg, "GUARDAR", "U",  (e_sms)?"1":"0", (e_email)?"1":"0");
							}
						}
					
					//Es Archivo de Usuario-RolEmpresa
					if(tipoConsulta==USUARIO || tipoConsulta==EMPRESA){
						RolUsuarioEmpVO rolempVO= new RolUsuarioEmpVO();
						rolempVO.setApp(params.get("appid").toString());
						rolempVO.setApprol(params.get("rolid").toString());
						rolempVO.setEstado(ufileTO.getEstado());
						if(tipoConsulta==USUARIO){
							log.info("Cargando archivo de Rut Empresas para Usuario");
							rolempVO.setRutEmpresa(rutreg);
							rolempVO.setRutUsuario(params.get("userid").toString());
							log.info(">>>>insertando en LDAP Rutempresa:" + rolempVO.getRutEmpresa());
						}else{
							log.info("Cargando archivo de Rut Usuarios para Empresa");
							rolempVO.setRutUsuario(rutreg);
							rolempVO.setRutEmpresa(params.get("rutemp").toString().replaceAll("\\.", ""));
							log.info(">>>>insertando en LDAP RutUsuario:" + rolempVO.getRutUsuario());
						}
						resultado= IngresaUsuarioLDAP.grabarUsuarioRolEmp(rolempVO);
		
					//Es Archivo de Usuario Roles Aplicación
					}else if(tipoConsulta==APPROL) {
						log.info("Cargando archivo de Rut Usuarios para Roles Aplicación");
						AppRolesVO approl= new AppRolesVO();
						approl.setAppID(params.get("appid_rol").toString());
						approl.setRolID(params.get("rolid_rol").toString());
						approl.setEstado(ufileTO.getEstado());
						approl.setRutUsuario(ufileTO.getRut().getNumber()+ "-" + ufileTO.getRut().getDV());
						resultado= IngresaUsuarioLDAP.grabarUsuarioRolApp(approl);
						
					}
					if(resultado.equals("")){
						if(ufileTO.getEstado().equals("N")){
							ok++;
						}else if(ufileTO.getEstado().equals("E")){
							del++;
						}

					}else{
						error+=rutreg + ",";

					}
				}
				if(error.length()>0){
					error=error.substring(0, error.length()-1);
				}
				if(invalido.length()>0){
					invalido=invalido.substring(0, invalido.length()-1);
				}
			}else{
				log.warn("No es Multipart");
			}
	    } catch (FileUploadException e) {
	        //throw new ServletException("Cannot parse multipart request.", e);
	    	e.printStackTrace();
	    } catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	    finally{
	    	JSONObject salida = new JSONObject();
	    	salida.put("total", total);
	    	salida.put("ok", ok);
	    	salida.put("del", del);
	    	salida.put("error", error);
	    	salida.put("invalido", invalido);
	    	html = salida.toJSONString();
	    	registrarSalida(response, html);
	        //response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	        //response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	        //response.getWriter().write(text);
	    }
	}
	
	public void registrarSalida(HttpServletResponse response, String result) {
		response.setCharacterEncoding("iso-8859-1");
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getTipoConsulta(String tipo){
		if(tipo.equals("listusu_e")){
			return EMPRESA;
		}else if(tipo.equals("listemp_u")){
			return USUARIO;
		}else if(tipo.equals("listusu_a")){
			return APPROL;
		}else if(tipo.equals("listusu_u")){
			return LISTAUSU;
		}
		return 0;
	}
	
	public void insertAuditoria(String usercreate, String username, String action, String tipo, String sms, String email){
		try {
			HashMap auditoria= new HashMap();
			auditoria.put("username", username);
			auditoria.put("usercreate", usercreate);
			auditoria.put("accion", action);
			auditoria.put("tipo", tipo);
			auditoria.put("sms", sms);
			auditoria.put("email", email);
			int resultado= IngresaUsuarioLDAP.grabarAuditoria("custom.insertLDAP4500", auditoria);
			log.info(">>Resultado grabacion Auditoria:" + resultado);
		} catch (Exception e) {
			log("Error al grabar tabla auditoria");
			e.printStackTrace();
		}
	}
	
}
