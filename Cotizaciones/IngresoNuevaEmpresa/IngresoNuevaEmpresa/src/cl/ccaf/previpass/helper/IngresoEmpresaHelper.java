package cl.ccaf.previpass.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.LDAPUser;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.ccaf.previpass.dao.PrevipassDAO;
import cl.ccaf.previpass.ldap.PersonaVO;
import cl.ccaf.previpass.ldap.ProxyLDAP;
import cl.ccaf.previpass.mail.EnviaMail;
import cl.ccaf.previpass.mail.FormatoMail;
import cl.ccaf.previpass.util.SqlMapLocator;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IngresoEmpresaHelper {
	static Logger log = Logger.getLogger(IngresoEmpresaHelper.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		
		
	}
	
	static boolean empresaEnProceso = false;
	static HashMap registro=null;
	
	public static synchronized HashMap procesarIngreso(SqlMapClient sqlMap, String rutEmpresa,String emailEmpresa, String tipo, String formatoMapeo){
		
		
		final String fechaVigencia="31/12/2029";
		
		//Para no insertar 2 o mas veces Sucursal, empresas, admin, rep, etc
		boolean datosEntidadesInsertados = false;
		
		HashMap resultado = new HashMap();
		resultado.put("ESTADO", false+"");
		

		//SqlMapClient sqlMap = SqlMapLocator.getInstance();
		HashMap data = new HashMap();
		data.put("rut_empresa", rutEmpresa);
		data.put("email_empresa", emailEmpresa);
		registro = PrevipassDAO.obtenerRegistro(sqlMap,"custom.obtenerDataTMPPrevipass", data);
		if(registro == null){
			resultado.put("ERROR", "NO_HAY_REGISTRO_EN_PREVIPASS_TMP");
			resultado.put("info",data);
		}
		
		Calendar c = new GregorianCalendar();
		c.set(Integer.parseInt(fechaVigencia.substring(6,10)), Integer.parseInt(fechaVigencia.substring(3,5)) - 1, Integer.parseInt(fechaVigencia.substring(0,2)));
		
		registro.put("vigencia_representante_legal_empresa".toUpperCase(), c.getTime());
		
		log.info("Fecha Vigencia: "+ fechaVigencia);
		
		//String[] formatos = registro.get("FORMATO_NOMINA_SEL").toString().split("::");
		String formato="PC"; //Productos Caja - Empresa
		if(tipo.equals("I")){
			formato="IND";
		}
		//for(int i=0; i < formatos.length; i++){
			if(formato.equals("PC")){
				log.info("\n\n");
				log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				log.info("PROCESANDO FORMATO : PC");
				//HashMap parametro = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerGrupoConvenioDefault"+formatos[i], null);
				HashMap parametro = PrevipassDAO.obtenerRegistro(sqlMap, formatoMapeo, null);
				log.info("CONVENIO A DUPICAR "+ parametro);
				HashMap grupoConvenioTemplate = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerDatagrupoConvenioDefault", parametro);
				log.info(" grupo Conv : "+ grupoConvenioTemplate);
				
				HashMap idMapaNom = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleMapaNom",null);
				log.info("idMapaNom: "+ idMapaNom);
				
				HashMap idMapaCod = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleMapaCod",null);
				log.info("idMapaCod: "+ idMapaCod);
				
				HashMap idOpcion = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleOpcionProc",null);
				log.info("idOpcion: "+ idOpcion);
				
				HashMap idGrupoConv = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleGrupoConv",null);
				log.info("idGrupoConv: "+ idGrupoConv);
				
				log.info("**********************************");
				
				int idMapaNomRem = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString());
				int idMapaNomGra = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 1;
				int idMapaNomDep = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 2;
				int idMapaNomRel = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 3;
				
				duplicarMapeos(sqlMap,idMapaNomRem,"selectMapaNomRem","selectMapeoConceRem",grupoConvenioTemplate,"MAPEO REMUNERACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
				duplicarMapeos(sqlMap,idMapaNomGra,"selectMapaNomGra","selectMapeoConceGra",grupoConvenioTemplate,"MAPEO GRATIFICACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
				duplicarMapeos(sqlMap,idMapaNomDep,"selectMapaNomDep","selectMapeoConceDep",grupoConvenioTemplate,"MAPEO DEPOSITOS GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
				duplicarMapeos(sqlMap,idMapaNomRel,"selectMapaNomRel","selectMapeoConceRel",grupoConvenioTemplate,"MAPEO RELIQUIDACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
				
				HashMap parametroEntidadesPrevisionales = new HashMap();
				parametroEntidadesPrevisionales.put("ID_MAPACOD", getValue(idMapaCod,"id_mapacod"));
				parametroEntidadesPrevisionales.put("DESCRIPCION", "Mapeo GRUPO CONVENIO "+ getValue(idGrupoConv,"id_grupo_convenio"));
				
				log.info(parametroEntidadesPrevisionales);
				PrevipassDAO.ejecutarInsert(sqlMap,"mapeo.insertMapaCod", parametroEntidadesPrevisionales);
				
				parametroEntidadesPrevisionales.put("ID_MAPACOD_TEMPLATE", getValue(grupoConvenioTemplate,"ID_MAPACOD"));
				log.info("GC: "+grupoConvenioTemplate);
				log.info("parametro: "+parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoAPV", "insertMapeoAPV", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoAsfam", "insertMapeoAsfam", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoPens", "insertMapeoPens", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoGen", "insertMapeoGen", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoSalud", "insertMapeoSalud", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoTipmp", "insertMapeoTipmp", parametroEntidadesPrevisionales);
				duplicarMapeosPrevisonales(sqlMap,"selectMapeoTipmpaf", "insertMapeoTipmpaf", parametroEntidadesPrevisionales);
				
				
				crearOpcion(sqlMap, registro,idOpcion.get("ID_OPCION").toString());
				registro.put("TIPO", "EMPRESA");
				
				resultado.put("idMapaNomRem", String.valueOf(idMapaNomRem));
				resultado.put("idMapaNomGra", String.valueOf(idMapaNomGra));
				resultado.put("idMapaNomDep", String.valueOf(idMapaNomDep));
				resultado.put("idMapaNomRel", String.valueOf(idMapaNomRel));
				resultado.put("idGrupoConv", idGrupoConv.get("ID_GRUPO_CONVENIO").toString());
				resultado.put("idOpcion", idOpcion.get("ID_OPCION").toString());
				resultado.put("idMapaCod", idMapaCod.get("ID_MAPACOD").toString());
			
			}else{
				//Independiente
				registro.put("TIPO", "INDEPENDIENTE");
				
				log.info("PROCESANDO FORMATO : INDEPENDIENTE");
				HashMap parametro = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerGrupoConvenioDefaultIndep", null);
				log.info("Grupo convenio base independiente "+ parametro);
				HashMap grupoConvenioTemplate = PrevipassDAO.obtenerRegistro(sqlMap,"mapeo.obtenerDatagrupoConvenioDefault", parametro);
				log.info(" grupo Conv : "+ grupoConvenioTemplate);
				int idGrupoConv = Integer.parseInt( grupoConvenioTemplate.get("ID_GRUPO_CONVENIOS").toString());
				int idMapaNomRem = Integer.parseInt( grupoConvenioTemplate.get("ID_MAPANOM_REM").toString());
				int idMapaNomGra = Integer.parseInt( grupoConvenioTemplate.get("ID_MAPANOM_GRA").toString());
				int idMapaNomDep = Integer.parseInt( grupoConvenioTemplate.get("ID_MAPANOM_DEP").toString());
				int idMapaNomRel = Integer.parseInt( grupoConvenioTemplate.get("ID_MAPANOM_REL").toString());
				int idMapaCod = Integer.parseInt( grupoConvenioTemplate.get("ID_MAPACOD").toString());
				int idOpcion = Integer.parseInt( grupoConvenioTemplate.get("ID_OPCION").toString());
				
				resultado.put("idMapaNomRem", String.valueOf(idMapaNomRem));
				resultado.put("idMapaNomGra", String.valueOf(idMapaNomGra));
				resultado.put("idMapaNomDep", String.valueOf(idMapaNomDep));
				resultado.put("idMapaNomRel", String.valueOf(idMapaNomRel));
				resultado.put("idGrupoConv", String.valueOf(idGrupoConv));
				resultado.put("idOpcion", String.valueOf(idOpcion));
				resultado.put("idMapaCod", String.valueOf(idMapaCod));
				/*
				if(!crearAdmin(sqlMap, registro)){
					resultado.put("ERROR", "NO_SE_HA_CREADO_ADMIN");
					return resultado;
				}
				if(!crearRepLegal(sqlMap, registro)){
					resultado.put("ERROR", "NO_SE_HA_CREADO_REPRESENTANTE");
					return resultado;
				}
				
				if(!crearEmpresa(sqlMap, registro)){
					resultado.put("ERROR", "NO_SE_HA_CREADO_LA_EMPRESA");
					return resultado;
				}

				int idConvenio = 1;
				if(!crearConvenio(sqlMap, registro, idGrupoConv+"", idMapaNomRem+"", idMapaNomGra+"", idMapaNomDep+"", idMapaNomRel+"", idMapaCod + "", idOpcion + "",idConvenio)){
					resultado.put("ERROR", "NO_SE_HA_CREADO_EL_CONVENIO");
					return resultado;
				}
				*/
				//datosEntidadesInsertados = true;
			}
			
	//	}
				
		return resultado;
	}
	
	public static HashMap insertarEmpresa_Admin(SqlMapClient sqlMap, HashMap resultado){

		//SqlMapClient sqlMap = SqlMapLocator.getInstance();
			if(!crearAdmin(sqlMap, registro)){
				resultado.put("ERROR", "NO_SE_HA_CREADO_ADMIN");
				return resultado;
			}
			if(!crearRepLegal(sqlMap, registro)){
				resultado.put("ERROR", "NO_SE_HA_CREADO_REPRESENTANTE");
				return resultado;
			}
			
			if(!crearEmpresa(sqlMap, registro)){
				resultado.put("ERROR", "NO_SE_HA_CREADO_LA_EMPRESA");
				return resultado;
			}
		
		return resultado;
	}
	
	public static HashMap insertarConvenio(SqlMapClient sqlMap, HashMap resultado, int idConvenio){

		//SqlMapClient sqlMap = SqlMapLocator.getInstance();
		
		if(!crearConvenio(sqlMap, registro, resultado.get("idGrupoConv").toString(), resultado.get("idMapaNomRem").toString(), resultado.get("idMapaNomGra").toString(), resultado.get("idMapaNomDep").toString(), resultado.get("idMapaNomRel").toString(), resultado.get("idMapaCod").toString(), resultado.get("idOpcion").toString(),idConvenio)){
			resultado.put("ERROR", "NO_SE_HA_CREADO_EL_CONVENIO");
		}
		return resultado;

	}
	
	public static HashMap insertLDAP(String rutEmpresa, String tipo){
		HashMap resultado = new HashMap();
		
		PersonaVO p = new PersonaVO();
		p.setIdPersona(new Integer(registro.get("RUT_EMPRESA").toString().split("-")[0]));
		String razonSocial= registro.get("RAZON_SOCIAL_EMPRESA").toString();
		if(razonSocial.length()>20){
			razonSocial= razonSocial.substring(0, 20);
		}
		p.setNombres(razonSocial);
		p.setApellidoPaterno("USUARIO");
		if(tipo.equals("I")){
			p.setApellidoMaterno("INDEPENDIENTE");
		}else{
			p.setApellidoMaterno("EMPRESA");
		}
		String pass = registro.get("RUT_EMPRESA").toString().split("-")[0].substring(0, 4);
		ProxyLDAP.createUser(pass, p);

		if(ProxyLDAP.getUser(registro.get("RUT_EMPRESA").toString())!= null){
			resultado.put("PASS", pass);
		}
		else{
			resultado.put("ERROR", "NO_SE_HA_CREADO_USUARIO_LDAP");
		}

		return resultado;
	}
	
	public static HashMap informarResultado(HashMap resultado, String rutEmpresa){
		
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		if(resultado.get("ERROR")==null){
			registro.put("estado", "2");
			PrevipassDAO.ejecutarUpdate(sqlMap, "custom.updatePrevipassTMP", registro);
			resultado.put("ESTADO", true+"");
			EnviaMail.enviarMail("Registro de Empresa Satisfactorio. ",registro.get("EMAIL_CASA_MATRIZ").toString(), null,FormatoMail.obtenerTextoMailLdapOK(registro.get("RUT_EMPRESA").toString(), resultado.get("PASS").toString()));

			String nombreAdmin = registro.get("RAZON_SOCIAL_EMPRESA").toString(); 
			String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
			EnviaMail.enviarMail("Registro de Empresa Satisfactorio. ",mailsCC, null,FormatoMail.obtenerTextoMailRegistroOKCopiaCaja(registro.get("RAZON_SOCIAL_EMPRESA").toString(), rutEmpresa, nombreAdmin, registro.get("EMAIL_CASA_MATRIZ").toString()));
		}else{
			//registro.put("estado", "2");
			PrevipassDAO.ejecutarUpdate(sqlMap, "custom.updatePrevipassTMP", registro);
			resultado.put("ESTADO", false+"");
		}
		return resultado;
	}
	
	public static void duplicarMapeos(SqlMapClient sqlMap,int idMapaNomRem,String selectMapaNom,String selectMapeoConce,HashMap data, String texto){
		ArrayList mapasRemNom = PrevipassDAO.obtenerRegistros(sqlMap,"mapeo."+selectMapaNom,data);
		log.info("DUPLICANDO MAPEOS ...");
		
		for(int i=0; i<mapasRemNom.size();i++){
			HashMap mapaNomRem = (HashMap) mapasRemNom.get(i);
			mapaNomRem.put("ID_MAPANOM", idMapaNomRem+"");
			mapaNomRem.put("DESCRIPCION", texto );
			PrevipassDAO.ejecutarInsert(sqlMap,"mapeo.duplicarMapaNom", mapaNomRem);
		}
		ArrayList mapeosConceRem = PrevipassDAO.obtenerRegistros(sqlMap,"mapeo."+selectMapeoConce,data);
		for(int i=0; i<mapeosConceRem.size();i++){
			HashMap dataMapeoConceRem = (HashMap) mapeosConceRem.get(i);
			dataMapeoConceRem.put("ID_MAPANOM", idMapaNomRem+"");
			//System.out.print(".");
			PrevipassDAO.ejecutarInsert(sqlMap,"mapeo.duplicarMapeoConce", dataMapeoConceRem);
		}
		
		
	}
	
	public static void duplicarMapeosPrevisonales(SqlMapClient sqlMap,String selectMapeo,String insertMapeo,HashMap data){
		log.info("DUPLICANDO MAPEOS PREVISIONALES...");
		List mapeos = PrevipassDAO.obtenerRegistros(sqlMap,"mapeo."+selectMapeo,data);
		for(int i=0; i<mapeos.size();i++){
			HashMap mapeo =(HashMap) mapeos.get(i);
			mapeo.put("ID_MAPACOD", getValue(data, "ID_MAPACOD"));
			//System.out.print(".");
			PrevipassDAO.ejecutarInsertSinExeption(sqlMap,"mapeo."+insertMapeo, mapeo);
			
		}
		
	}

	
	public static void crearOpcion(SqlMapClient sqlMap,HashMap registro,String idOpcion){
	
		log.info("CREANDO OPCION...");
		HashMap data = new HashMap();
		data.put("ID_OPCION", idOpcion);
		
		data.put("GENERAR_INP_POR_SUCURSAL", 	getValueSiNo(registro.get("GEN_PLA_INP_SUCURSAL").toString()));
		data.put("GENERAR_MUTUAL_POR_SUCURSAL", getValueSiNo(registro.get("GEN_PLA_MUTUAL_SUCURSAL").toString()));
		data.put("GENERAR_CCAF_POR_SUCURSAL", 	getValueSiNo(registro.get("GEN_PLA_CCAF_SUCURSAL").toString()));
		data.put("CALCULAR_MONTO_INP", 			"0");
		data.put("CALCULAR_MONTO_MUTUAL", 		getValueSiNo(registro.get("CAL_MONTO_TOTAL_MUTUAL").toString()));
		data.put("CALCULAR_MONTO_CCAF", 		getValueSiNo(registro.get("CAL_MONTO_TOTAL_CCAF").toString()));
		data.put("CALCULAR_MONTO_TOTAL_SALUD", 	getValueSiNo(registro.get("CAL_MONTO_TOTAL_SALUD").toString()));
		data.put("CALCULAR_MONTO_TOTAL_PREVISION", getValueSiNo(registro.get("CAL_MONTO_TOTAL_PREVISION").toString()));
		data.put("IMPRIMIR_PLANILLAS", 			getValueSiNo(registro.get("IMPRIMIR_PLANILLAS").toString()));
		data.put("CALCULAR_MONTO_FONASA",		getValueSiNo(registro.get("CAL_MONTO_TOTAL_FONASA").toString()));
		
		
		try {		
			sqlMap.insert("mapeo.crear_opcion_proceso", data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object getValue(HashMap data,String key){
		return data.get(key.toUpperCase());
	}

	public static String getValueSiNo(String valor){
		if(valor.toLowerCase().equals("si")){
			return 1+"";
		}
		return 0+"";
	}
	
	public static boolean crearAdmin(SqlMapClient sqlMap,
			HashMap registro) {
			HashMap data = new HashMap();

			log.info("CREANDO ADMIN...");
			String idPersona = registro.get("RUT_EMPRESA").toString().split("-")[0];
		try {
			data.put("id_persona", idPersona);
			data.put("COMUNA", Integer.parseInt( registro.get("COMUNA_CASA_MATRIZ").toString().trim())+"");
			if(registro.get("CELULAR_CASA_MATRIZ").toString().length()==0){
				data.put("CELULAR","0");
			}
			else{
				data.put("CELULAR",registro.get("CELULAR_CASA_MATRIZ"));
			}
			data.put("DIRECCION_NUMERO" ,registro.get("DIRECCION_NUMERO_CASA_MATRIZ"));
			data.put("DEPARTAMENTO_NUMERO" ,registro.get("DEPTO_NUMERO_CASA_MATRIZ"));
			data.put("TELEFONO_FIJO" ,"("+registro.get("CODIGO_TELEFONO_CASA_MATRIZ")+")"+registro.get("TELEFONO_FIJO_CASA_MATRIZ"));
			if(registro.get("FAX_CASA_MATRIZ").toString().length()>5)
				data.put("FAX" ,"("+registro.get("CODIGO_FAX_CASA_MATRIZ")+")"+registro.get("FAX_CASA_MATRIZ"));
			else
				data.put("FAX" ,"");
			String nombre= registro.get("RAZON_SOCIAL_EMPRESA").toString();
			if (nombre.length()>30){
				nombre= nombre.substring(0, 30);
			}
			data.put("NOMBRE" , nombre);
			data.put("APELLIDO_PATERNO" ,"USUARIO");
			data.put("APELLIDO_MATERNO" ,"EMPRESA");
			data.put("EMAIL" ,registro.get("EMAIL_CASA_MATRIZ"));
			data.put("DIRECCION",registro.get("DIRECCION_CASA_MATRIZ").toString().trim());
			
			try{
			sqlMap.insert("custom.crear_persona", data);
			} catch(Exception e){
				e.printStackTrace();
			}
			
			if(registro.get("TIPO").toString().equals("INDEPENDIENTE")){
				data.put("is_empresa" , new Integer(0));
				data.put("is_independiente" , new Integer(1));
			}else{
				data.put("is_empresa" , new Integer(1));
				data.put("is_independiente" , new Integer(0));
			}
			sqlMap.insert("custom.crear_admin", data);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap dataAdmin = PrevipassDAO.obtenerRegistro( "custom.validarExistenciaAdmin",idPersona);
		if(dataAdmin != null && dataAdmin.get("RESULTADO").toString().toLowerCase().equals("true")){
			return true;
		}
		
		return false;
	}
	
	public static boolean crearRepLegal(SqlMapClient sqlMap,
			HashMap registro) {
		

			log.info("CREANDO REP. LEGAL...");
			HashMap data = new HashMap();
			String idPersona = registro.get("RUT_REP_LEG_EMP").toString().split("-")[0];
		try {
			data.put("id_persona", idPersona);
			data.put("COMUNA", "0");		
			data.put("CELULAR","0");
			data.put("DIRECCION_NUMERO" ,"");
			data.put("DEPARTAMENTO_NUMERO" ,"");
			data.put("TELEFONO_FIJO" ,"");
			data.put("FAX" ,"");
			data.put("NOMBRE" ,registro.get("NOMBRE_REP_LEG_EMP"));
			data.put("APELLIDO_PATERNO" ,registro.get("APE_PAT_REP_LEG_EMP"));
			data.put("APELLIDO_MATERNO" ,registro.get("APE_MAT_REP_LEG_EMP"));
			data.put("EMAIL" ,registro.get("EMAIL_CASA_MATRIZ"));
			data.put("DIRECCION","");
			
			try{
				sqlMap.insert("custom.crear_persona", data);
				} catch(Exception e){
					e.printStackTrace();
				}
			sqlMap.insert("custom.crear_rep_legal", data);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap dataAdmin = PrevipassDAO.obtenerRegistro( "custom.validarExistenciaRepresentante",idPersona);
		if(dataAdmin != null && dataAdmin.get("RESULTADO").toString().toLowerCase().equals("true")){
			return true;
		}
		return false;
	}


	public static boolean crearEmpresa(SqlMapClient sqlMap,
			HashMap registro) {
		

		log.info("CREANDO RUT EMPRESA..."+ registro);
		String idEmpresa = registro.get("RUT_EMPRESA").toString().split("-")[0];
		try {
			
			
			registro.put("id_empresa", idEmpresa);
			registro.put("id_rep_legal", registro.get("RUT_REP_LEG_EMP").toString().split("-")[0]);
			registro.put("id_admin", idEmpresa);
			registro.put("TELEFONO_FIJO_CASA_MATRIZ","("+registro.get("CODIGO_TELEFONO_CASA_MATRIZ")+")"+ registro.get("TELEFONO_FIJO_CASA_MATRIZ").toString());
			if(registro.get("FAX_CASA_MATRIZ").toString().length()>5){
				registro.put("FAX_CASA_MATRIZ","("+registro.get("CODIGO_FAX_CASA_MATRIZ")+")"+ registro.get("FAX_CASA_MATRIZ").toString());
			}
			else{
				registro.put("FAX_CASA_MATRIZ","");
			}
			if(registro.get("CELULAR_CASA_MATRIZ").toString().length()<8){
				registro.put("CELULAR_CASA_MATRIZ","0");
			}
			if(registro.get("NUMERO_ADHERENTES_MUTUAL").toString().length()==0){
				registro.put("NUMERO_ADHERENTES_MUTUAL","0");
			}
			if(registro.get("TASA_ADICIONAL_MUTUAL").toString().length()==0){
				registro.put("TASA_ADICIONAL_MUTUAL","0");
			}
			
			sqlMap.insert("custom.crear_empresa", registro);
			sqlMap.insert("custom.crear_sucursal", registro);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap dataEmpresa = PrevipassDAO.obtenerRegistro("custom.validarExistenciaEmpresa",idEmpresa);
		if(dataEmpresa!= null && "true".equals(dataEmpresa.get("RESULTADO").toString().toLowerCase())){
			return true;
		}
		return false;
	}
	public static boolean crearConvenio(SqlMapClient sqlMap,
			HashMap registro,
			String idGrupoConvenio,
			String idMapaNomRem,
			String idMapaNomGra,
			String idMapaNomDep,
			String idMapaNomRel,
			String idMapaCod,
			String idOpcion,
			int idConvenio
			) {

		log.info("CREANDO CONVENIO...");
		HashMap data = new HashMap();
		final String CAJA_COMPENSACION="3"; //La Araucana
		String idEmpresa = registro.get("RUT_EMPRESA").toString().split("-")[0];
		String idEmpresaConv = registro.get("RUT_EMPRESA").toString().split("-")[0];
		String nombre = "Empresa: "+ idEmpresa;
		String holding = registro.get("NOMBRE_HOLDING_EMPRESA").toString();
		if(holding.trim().length()>0){
			idEmpresa = "0";
			nombre =  idGrupoConvenio;
		}
		data.put("ID_GRUPO_CONVENIOS",idGrupoConvenio);
		data.put("ID_EMPRESA",idEmpresa);
		data.put("ID_MAPANOM_REM",idMapaNomRem);
		data.put("ID_MAPANOM_GRA",idMapaNomGra);
		data.put("ID_MAPANOM_DEP",idMapaNomDep);
		data.put("ID_MAPANOM_REL",idMapaNomRel);
		data.put("ID_MAPACOD",idMapaCod);
		data.put("ID_OPCION",idOpcion);
		data.put("NOMBRE",nombre);
		
		//tabla CONVENIO
		data.put("ID_EMPRESA",idEmpresaConv);
		data.put("ID_CONVENIO",idConvenio+"");
		data.put("ID_CCAF" ,CAJA_COMPENSACION);
		if( registro.get("NOMBRE_MUTUAL").toString().length()>0 ){
			data.put("ID_MUTUAL" ,registro.get("NOMBRE_MUTUAL").toString());
		}
		else{
			data.put("ID_MUTUAL" ,"0");
		}
		if(registro.get("NUMERO_ADHERENTES_MUTUAL").toString().length()==0){
			registro.put("NUMERO_ADHERENTES_MUTUAL","0");
		}
		if(registro.get("TASA_ADICIONAL_MUTUAL").toString().length()==0){
			registro.put("TASA_ADICIONAL_MUTUAL","0");
		}
		data.put("ID_ACTIVIDAD" ,registro.get("COD_ACT_ECO_EMP").toString());
		data.put("CALCULO_MOV_PERSONAL", getValueSiNo(registro.get("CAL_MOVIMIENTO_PERSONAL").toString()));
		data.put("MUTUAL_NUMERO_ADHERENTE" ,registro.get("NUMERO_ADHERENTES_MUTUAL"));
		data.put("MUTUAL_TASA_ADICIONAL",registro.get("TASA_ADICIONAL_MUTUAL"));
		data.put("MUTUAL_CALCULO_INDIVIDUAL", getValueSiNo(registro.get("CALCULO_INDIVIDUAL_MUTUAL").toString()));
		if(idConvenio==1){
			data.put("DESCRIPCION","PRODUCTOS_CAJA");
			data.put("PROD_CAJA","1");
		}else{
			data.put("DESCRIPCION","COTIZACION");
			data.put("PROD_CAJA","0");
		}
		
		log.info("data convenio: "+ data);
		try {
			if(!registro.get("TIPO").toString().equals("INDEPENDIENTE")){
				sqlMap.insert("mapeo.crear_grupo_convenio", data);
			}
			sqlMap.insert("mapeo.crear_convenio", data);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
