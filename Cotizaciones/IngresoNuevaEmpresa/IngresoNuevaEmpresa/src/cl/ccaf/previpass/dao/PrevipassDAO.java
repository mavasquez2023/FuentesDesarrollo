package cl.ccaf.previpass.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.ccaf.previpass.mail.EnviaMail;
import cl.ccaf.previpass.mail.FormatoMail;
import cl.ccaf.previpass.util.SqlMapLocator;
import cl.recursos.EnviarMail;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PrevipassDAO {
	static Logger log = Logger.getLogger(PrevipassDAO.class);
	
	public static void main(String[] args) {
		actualizarData("9000053-5", "clillo007@gmail.com", "empresa", "razon_social_empresa:=wequecillo;;tipo_empresa:=1;;numero_trabajadores:=501;;nombre_holding_empresa:=J-F%20Lillo;;rut_representante_legal_empresa:=15915915-9;;nombre_representante_legal_empresa:=Claudio;;apellido_paterno_representante_legal_empresa:=;;apellido_materno_representante_legal_empresa:=Azorín;;estado_representante_legal_empresa:=undefined;;vigencia_representante_legal_empresa:=;;codigo_acividad_economica_empresa:=;;nombre_acividad_economica_empresa:=;;&rand=0.6974775774463438");
	}

		public static HashMap obtenerDataTMPPrevipass(String rutEmpresa,String emailEmpresa){
			HashMap resultado = new HashMap();
			SqlMapClient sqlMap = SqlMapLocator.getInstance();
			HashMap  parametros = new HashMap();
			
			parametros.put("rut_empresa", rutEmpresa);
			parametros.put("email_empresa", emailEmpresa);
			try {
				resultado = (HashMap) sqlMap.queryForObject("custom.obtenerDataTMPPrevipass",parametros);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return resultado;
	}
		public static boolean autenticar(String rutEmpresa,String emailEmpresa,String clave){
			HashMap resultado = new HashMap();
			SqlMapClient sqlMap = SqlMapLocator.getInstance();
			HashMap  parametros = new HashMap();
				
			parametros.put("rut_empresa", rutEmpresa);
			parametros.put("email_empresa", emailEmpresa);
			parametros.put("clave", clave);
			
			
			
			try {
				
				resultado = (HashMap) sqlMap.queryForObject("custom.obtener_data_autenticacion",parametros);
				log.debug("result: "+ resultado);
				if(resultado != null && resultado.get("RESULTADO")!= null ){
					return Boolean.parseBoolean(resultado.get("RESULTADO").toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return false;
	}
		
		
		
		public static boolean actualizarData(String rutEmpresa,String emailEmpresa,String formulario,String data){
			
			HashMap  parametros = getHashFromString(data);
			if(parametros != null){
				
				parametros.put("rut_empresa", rutEmpresa);
				parametros.put("email_empresa", emailEmpresa);
				
				
				SqlMapClient sqlMap = SqlMapLocator.getInstance();
				try {
					int resultado = sqlMap.update("custom.update_"+formulario, parametros);
					if(resultado > 0)
						return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return false;
		}
		public static boolean ingresarNuevoRegistro(String rutEmpresa,String email){
			
			HashMap  parametros = new HashMap();
			if(parametros != null){
				
				parametros.put("rut_empresa", rutEmpresa);
				parametros.put("email_empresa", email);
				//parametros.put("clave", clave+"");
				
				SqlMapClient sqlMap = SqlMapLocator.getInstance();
				try{
					ejecutarInsert(parametros, "insertarRegistroPrevipassTMP");
					String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
					EnviaMail.enviarMail("Registro de Nuevo Usuario",mailsCC, null,FormatoMail.obtenerTextoMailRegistro(email, rutEmpresa));
				}
				catch(Exception e){
					
				}
				/*try {
					
					
					log.debug(parametros);
					int resultado = sqlMap.update("custom.actualizar_clave", parametros);
					if(resultado > 0){
						EnviaMail.enviarMail("Clave Acceso Previpass",email,null,FormatoMail.obtenerTextoMailClave(clave+"",rutEmpresa));
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				*/
			}
			return false;
		}
	
		
		
		
		public static HashMap getHashFromString(String data){
			HashMap resultado =  getHashDefault();
			log.debug(data);
			String[] parametros = data.split(";;");
			for(int i=0; i<parametros.length;i++){
				String[] partes = parametros[i].split(":=");
				if(partes.length == 2){
					resultado.put(partes[0].trim(), partes[1].trim());
					
				}
				
			}
			
			return resultado;
		}
		
		public static boolean ejecutarInsert(HashMap data, String query) throws SQLException{
			
			SqlMapClient sqlMap = SqlMapLocator.getInstance();
			
				sqlMap.insert("custom."+query,data);
			
			return true;
			
		}
		
		public static HashMap obtenerRegistro(String query, Object data){
			HashMap resultado = null;
			SqlMapClient sqlMap = SqlMapLocator.getInstance();
			
			try {
				resultado = (HashMap) sqlMap.queryForObject(query,data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
		
		public static ArrayList obtenerRegistros(SqlMapClient sqlMap, String id, HashMap data){
			ArrayList resultado = new ArrayList();
			try {
				resultado = (ArrayList) sqlMap.queryForList(id,data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
		public static ArrayList obtenerRegistros(String id, Object data){
			ArrayList resultado = new ArrayList();
			SqlMapClient sqlMap = SqlMapLocator.getInstance();
			try {
				resultado = (ArrayList) sqlMap.queryForList(id,data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
		
		public static HashMap obtenerRegistro(SqlMapClient sqlMap, String id, HashMap data){
			ArrayList resultado = obtenerRegistros(sqlMap,id, data);
			if(resultado!= null && resultado.size()>0){
				return (HashMap)resultado.get(0);
			}
			return null;
			
		}
		

		public static void ejecutarInsert(SqlMapClient sqlMap,String id,  HashMap data){
			
			try {
				sqlMap.insert(id, data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static void ejecutarUpdate(SqlMapClient sqlMap,String id,  HashMap data){
			
			try {
				log.debug("registro: "+ data);
				int resultado= sqlMap.update(id, data);
				log.debug("resultado: "+ resultado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static void ejecutarInsertSinExeption(SqlMapClient sqlMap,String id,  HashMap data){
			
			try {
				sqlMap.insert(id, data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}

		public static HashMap getHashDefault(){
			HashMap resultado = new HashMap();
			resultado.put("rut_admin","");
			resultado.put("nombre_admin","");
			resultado.put("apellido_paterno_admin",""); 
			resultado.put("apellido_materno_admin",""); 
			resultado.put("codigo_telefono_fijo_admin",""); 
			resultado.put("telefono_fijo_admin",""); 
			resultado.put("codigo_fax_admin",""); 
			resultado.put("fax_admin",""); 
			resultado.put("celular_admin",""); 
			resultado.put("email_admin",""); 
			resultado.put("direccion_admin",""); 
			resultado.put("direccion_numero_admin",""); 
			resultado.put("departamento_numero_admin",""); 
			resultado.put("region_admin",""); 
			resultado.put("ciudad_admin",""); 
			resultado.put("comuna_admin",""); 
			resultado.put("rut_empresa",""); 
			resultado.put("razon_social_empresa",""); 
			resultado.put("tipo_empresa",""); 
			resultado.put("nombre_holding_empresa",""); 
			resultado.put("rut_representante_legal_empresa","");
			resultado.put("numero_trabajadores","");
			resultado.put("nombre_representante_legal_empresa",""); 
			resultado.put("apellido_paterno_representante_legal_empresa",""); 
			resultado.put("apellido_materno_representante_legal_empresa",""); 
			resultado.put("estado_representante_legal_empresa",""); 
			resultado.put("vigencia_representante_legal_empresa",""); 
			resultado.put("codigo_acividad_economica_empresa",""); 
			resultado.put("nombre_acividad_economica_empresa",""); 
			resultado.put("codigo_casa_matriz",""); 
			resultado.put("nombre_casa_matriz",""); 
			resultado.put("direccion_casa_matriz",""); 
			resultado.put("direccion_numero_casa_matriz",""); 
			resultado.put("depto_numero_casa_matriz",""); 
			resultado.put("region_casa_matriz",""); 
			resultado.put("ciudad_casa_matriz",""); 
			resultado.put("comuna_casa_matriz",""); 
			resultado.put("codigo_telefono_casa_matriz",""); 
			resultado.put("telefono_fijo_casa_matriz",""); 
			resultado.put("celular_casa_matriz",""); 
			resultado.put("codigo_fax_casa_matriz",""); 
			resultado.put("fax_casa_matriz",""); 
			resultado.put("email_casa_matriz",""); 
			resultado.put("nombre_mutual","");
			resultado.put("numero_adherentes_mutual",""); 
			resultado.put("calculo_individual_mutual","");
			resultado.put("tasa_adicional_mutual",""); 
			resultado.put("caja_compensacion","");
			resultado.put("generar_planilla_inp_sucursal",""); 
			resultado.put("calcular_monto_total_ccaf",""); 
			resultado.put("generar_planilla_mutual_sucursal",""); 
			resultado.put("calcular_monto_total_salud",""); 
			resultado.put("generar_planilla_ccaf_sucursal",""); 
			resultado.put("calcular_monto_total_prevision",""); 
			resultado.put("calcular_monto_total_fonasa",""); 
			resultado.put("imprimir_planillas",""); 
			resultado.put("calcular_monto_total_mutual",""); 
			resultado.put("calcular_movimiento_personal",""); 
			resultado.put("formato_nomina_sel",""); 			
			return resultado;
		}
		
		
}
