

package cl.araucana.autoconsulta.common;


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.schema.util.FileSettings;

import cl.araucana.autoconsulta.dao.DAOFactory;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.env.AppConfig;


public class UserEnvLoader {

	private static Logger logger = Logger.getLogger(UserEnvLoader.class);

	/*
	 * Se utiliza el valor de "dao-type" en esta clase para determinar si se está en Dummy o REAL
	 * Recurso DAO de Autoconsulta
	 */
	int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/autoconsulta/dao-type"));
	
	public String getAdminCosal(String rut){
		String isAdmin="false";
		String strAdminCosal = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
							"/application-settings/autoconsulta/param/adminCosal");
											 					    
		Vector vecAdmin = (Vector) Funciones.getArreglo(strAdminCosal,';');
		
		for(int i=0;i<vecAdmin.size();i++){
			String rutAdm=(String)vecAdmin.get(i);
			if(rutAdm.equalsIgnoreCase(rut)){
				isAdmin="true";
				break;
			}
		}
	   return isAdmin;
	}
		
	public ArrayList getServicios(UsuarioVO usuario, String subapp) {
		
		String servicesFile = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
							"/application-settings/common/services-file");
											 					    
		String clavesServicios = FileSettings.getValue(servicesFile,
							"/services-settings/AutoconsultaWeb/services/keys/"+subapp);

		ArrayList servicios = new ArrayList();
		logger.debug("Claves de Servicio: "+clavesServicios);
		if (clavesServicios!=null) {
			StringTokenizer st = new StringTokenizer(clavesServicios,";");
			while (st.hasMoreTokens()) {
				String clave = st.nextToken();
				Servicio servicio = new Servicio();
								
				logger.debug("Aplicando servicio: "+clave);
				servicio.setClave(clave);
				servicio.setNombre(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/nombre"));
				servicio.setAction(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/action"));
				servicio.setTipo(Integer.parseInt(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/tipo")));
				String data = FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/siempreActivo");
				servicio.setSiempreActivo(data!=null && data.equalsIgnoreCase("on"));
								
				// Perfiles
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/empresa");
				servicio.setForEmpresa(data!=null && data.equalsIgnoreCase("on"));									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/afiliadoActivo");
				servicio.setForAfiliadoActivo(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAfiliadoActivo());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/afiliadoPasivo");
				servicio.setForAfiliadoPasivo(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAfiliadoCesado());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/pensionado");
				servicio.setForPensionado(data!=null && data.equalsIgnoreCase("on") && usuario.isEsPensionado());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/ahorrante");
				servicio.setForAhorrante(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAhorrante());
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/publico");
				servicio.setForEmpleadoPublico(data!=null && data.equalsIgnoreCase("on") && usuario.isEsPublico());
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/empresaPublica");
				servicio.setForEmpresaPublica(data!=null && data.equalsIgnoreCase("on") && usuario.isEsEmpresaPublica());
								
				// Determina la validez del servicio
				servicio.setValid(
					(servicio.isForEmpresa() && usuario.isEsEmpresa()) || 
					(servicio.isForAfiliadoActivo() && usuario.isEsAfiliadoActivo()) ||
					(servicio.isForAfiliadoPasivo() && usuario.isEsAfiliadoCesado()) ||
					(servicio.isForPensionado() && usuario.isEsPensionado()) ||
					(servicio.isForAhorrante() && usuario.isEsAhorrante() ||
					(servicio.isForEmpleadoPublico() && usuario.isEsPublico()) ||
					(servicio.isForEmpresaPublica() && usuario.isEsEmpresaPublica())) 
				);
				
				//Valida el dao-type para determinar si se está en DUMMY o REAL
				if(daoType==DAOFactory.DUMMY) {
					servicio.setValid(true); //DUMMY
				}
								
				logger.debug("Se activa servicio?: "+servicio.isValid());
								
				if (servicio.isValid() || servicio.isSiempreActivo()) {
					servicios.add(servicio);
				}
			}						
		}
		
		return servicios;
	}
}
