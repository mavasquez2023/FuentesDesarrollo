package cl.ccaf.previpass.test;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.ccaf.previpass.dao.PrevipassDAO;
import cl.ccaf.previpass.util.SqlMapLocator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(PrevipassDAO.obtenerDataTMPPrevipass("15057836-1","luisibacache@gmail.com","1234"));

		/*
		 * String formulario="admin"; String rut_empresa="15057836-1"; String
		 * data="nombre_admin:=Luis andr;;0.4907045819692665";
		 * System.out.println
		 * (PrevipassDAO.actualizarData(rut_empresa,formulario,data));
		 */
		// Double clave = Math.random()*10000;
		// System.out.println(clave.intValue());
		// System.out.println( "Resultado: " +
		// PrevipassDAO.actualizarClave("15057836-1", "luisibacache@gmail.com",
		// 6620));
		// String data =
		// "rut_admin:=15057836-2;;nombre_admin:=Luis;;apellido_paterno_admin:=;;apellido_materno_admin:=;;codigo_telefono_fijo_admin:=;;telefono_fijo_admin:=;;codigo_fax_admin:=;;fax_admin:=;;celular_admin:=;;email_admin:=;;direccion_admin:=;;direccion_numero_admin:=;;departamento_numero_admin:=;;region_admin:=;;ciudad_admin:=;;comuna_admin:=;;rut_admin:=15057836-1;;nombre_admin:=;;apellido_paterno_admin:=;;apellido_materno_admin:=;;codigo_telefono_fijo_admin:=;;telefono_fijo_admin:=;;codigo_fax_admin:=;;fax_admin:=;;celular_admin:=;;email_admin:=;;direccion_admin:=;;direccion_numero_admin:=;;departamento_numero_admin:=;;region_admin:=;;ciudad_admin:=;;comuna_admin:=;;";

		// System.out.println("Resultado: "+
		// PrevipassDAO.actualizarData("15057836-1","luisibacache@gmail.com","6620",
		// "admin", data));

		//HashMap registro = PrevipassDAO
		//		.obtenerDataTMPPrevipass("15057836-1",
		//				"luisibacache@gmail.com", "4743");
		
		//System.out.println("registro: "+ registro);
		/*
		 * System.out.println(registro);
		 * 
		 * SqlMapClient sqlMap = SqlMapLocator.getInstance();
		 * 
		 * try {
		 * 
		 * 
		 * sqlMap.insert("custom.crear_persona",registro); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		//MapeoTest.initProceso();
		//SqlMapClient sqlMap = SqlMapLocator.getInstance();
		
		/*crearAdmin(sqlMap, registro);
		crearRepLegal(sqlMap, registro);
		crearEmpresa(sqlMap, registro);*/
		
		//crearConvenio(sqlMap,registro,"2749","6045","6046","6047","6048","1845","1778");
		
		//System.out.println("region  : "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_regiones", null));
		//System.out.println("ciudades: "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_ciudades", null));
		//System.out.println("comunas : "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_comunas", null));
		//System.out.println("act ecos: "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_actividades_economicas", null));
		//System.out.println("mutuales: "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_mutuales", null));
		//System.out.println("cajas   : "+ PrevipassDAO.obtenerRegistros(sqlMap, "custom.obtener_cajas", null));
		
		String fecha = "22/12/2014";
		System.out.println("fecha antes: "+ fecha);
		String fechaDespues = fecha.substring(6,10)+"/"+fecha.substring(3,5)+"/"+fecha.substring(0,2);
		System.out.println("fecha despues: "+ fechaDespues);
	}

	public static void crearAdmin(SqlMapClient sqlMap,
			HashMap registro) {
			HashMap data = new HashMap();
		try {
			data.put("id_persona", registro.get("RUT_ADMIN").toString().split("-")[0]);
			data.put("COMUNA", Integer.parseInt( registro.get("COMUNA_ADMIN").toString().trim())+"");		
			data.put("CELULAR",registro.get("CELULAR_ADMIN"));
			data.put("DIRECCION_NUMERO" ,registro.get("DIRECCION_NUMERO_ADMIN"));
			data.put("DEPARTAMENTO_NUMERO" ,registro.get("DEPARTAMENTO_NUMERO_ADMIN"));
			data.put("TELEFONO_FIJO" ,registro.get("TELEFONO_FIJO_ADMIN"));
			data.put("FAX" ,registro.get("FAX_ADMIN"));
			data.put("NOMBRE" ,registro.get("NOMBRE_ADMIN"));
			data.put("APELLIDO_PATERNO" ,registro.get("APELLIDO_PATERNO_ADMIN"));
			data.put("APELLIDO_MATERNO" ,registro.get("APELLIDO_MATERNO_ADMIN"));
			data.put("EMAIL" ,registro.get("EMAIL_ADMIN"));
			data.put("DIRECCION",registro.get("DIRECCION_ADMIN").toString().trim());
			
			
			
			sqlMap.insert("custom.crear_persona", data);
			sqlMap.insert("custom.crear_admin", data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearRepLegal(SqlMapClient sqlMap,
			HashMap registro) {
			HashMap data = new HashMap();
		try {
			data.put("id_persona", registro.get("RUT_REP_LEG_EMP").toString().split("-")[0]);
			data.put("COMUNA", "0");		
			data.put("CELULAR","0");
			data.put("DIRECCION_NUMERO" ,"");
			data.put("DEPARTAMENTO_NUMERO" ,"");
			data.put("TELEFONO_FIJO" ,"");
			data.put("FAX" ,"");
			data.put("NOMBRE" ,registro.get("NOMBRE_REP_LEG_EMP"));
			data.put("APELLIDO_PATERNO" ,registro.get("APE_PAT_REP_LEG_EMP"));
			data.put("APELLIDO_MATERNO" ,registro.get("APE_MAT_REP_LEG_EMP"));
			data.put("EMAIL" ,"");
			data.put("DIRECCION","");
			
			
			sqlMap.insert("custom.crear_persona", data);
			sqlMap.insert("custom.crear_rep_legal", data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void crearSucursal(SqlMapClient sqlMap,
			HashMap registro) {
		try {
			sqlMap.insert("custom.crear_sucursal", registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void crearEmpresa(SqlMapClient sqlMap,
			HashMap registro) {
		try {
			String idEmpresa = registro.get("RUT_EMPRESA").toString().split("-")[0];
			
			registro.put("id_empresa", idEmpresa);
			registro.put("id_rep_legal", registro.get("RUT_REP_LEG_EMP").toString().split("-")[0]);
			registro.put("id_admin", registro.get("RUT_ADMIN").toString().split("-")[0]);
			sqlMap.insert("custom.crear_empresa", registro);
			sqlMap.insert("custom.crear_sucursal", registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void crearConvenio(SqlMapClient sqlMap,
			HashMap registro,
			String idGrupoConvenio,
			String idMapaNomRem,
			String idMapaNomGra,
			String idMapaNomDep,
			String idMapaNomRel,
			String idMapaCod,
			String idOpcion) {
		
		HashMap data = new HashMap();
		String idEmpresa = registro.get("RUT_EMPRESA").toString().split("-")[0];
		String nombre = "Empresa: "+ idEmpresa;
		String holding = registro.get("NOMBRE_HOLDING_EMPRESA").toString();
		if(holding.trim().length()>0){
			idEmpresa = "0";
			nombre = "Holding: "+ idGrupoConvenio;
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
		
		System.out.println("data: "+ data);
		try {
			sqlMap.insert("mapeo.crear_grupo_convenio", data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
