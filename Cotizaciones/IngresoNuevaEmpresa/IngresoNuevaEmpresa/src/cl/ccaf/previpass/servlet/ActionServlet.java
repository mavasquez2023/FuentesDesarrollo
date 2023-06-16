package cl.ccaf.previpass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.ccaf.previpass.dao.PrevipassDAO;
import cl.ccaf.previpass.helper.IngresoEmpresaHelper;
import cl.ccaf.previpass.helper.PresentacionHelper;
import cl.ccaf.previpass.util.SqlMapLocator;
import cl.ccaf.previpass.util.Utiles;

public class ActionServlet extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 9061711754376149030L;

	public static String OBTENER_DATA_TMP = "obtener_data_tmp_empresa";
	public static String ACTUALIZAR_DATA = "actualizar_data";
	public static String GENERAR_CLAVE = "generar_clave";
	public static String AUTENTICAR = "autenticar";
	public static String OBTENER_RECURSOS_PANTALLA = "obtener_recursos_pantalla";
	public static String CREAR_EMPRESA = "crear_empresa";
	public static String VER_DETALLE = "ver_detalle";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String html = "";
		String action = request.getParameter("action");

		if (action.equals(OBTENER_DATA_TMP)) {
			String rutEmpresa = request.getParameter("rut_empresa");
			String emailEmpresa = request.getParameter("email_empresa");

			JSONObject salida = new JSONObject();
			salida.put("data", PrevipassDAO.obtenerDataTMPPrevipass(rutEmpresa,emailEmpresa));
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}		
		if (action.equals(VER_DETALLE)) {
			String rutEmpresa = request.getParameter("rut_empresa");
			String emailEmpresa = request.getParameter("email_empresa");

			JSONObject salida = new JSONObject();
			HashMap data= PresentacionHelper.obtenerRegistroTMP(rutEmpresa, emailEmpresa);
			log.info("data: "+data);
			salida.put("data", data);
			html = salida.toJSONString();
			log.info("html: "+html);
		}
		if (action.equals(ACTUALIZAR_DATA)) {
			String formulario = request.getParameter("formulario");
			String rutEmpresa = request.getParameter("rut_empresa");
			String emailEmpresa = request.getParameter("email_empresa");
			String data = request.getParameter("data");
			data = data.replaceAll("amp;", "&");
			data = data.replaceAll("ene;", "Ã±");
			data = data.replaceAll("eNe;", "Ã‘");
			
			
			JSONObject salida = new JSONObject();
			log.info("Actualizando data: " + formulario + " \n "
					+ rutEmpresa + " \n " + data);
			salida.put("data",
					PrevipassDAO.actualizarData(rutEmpresa,emailEmpresa, formulario, data)+"");
			html = salida.toJSONString();
		}
		if (action.equals(GENERAR_CLAVE)) {
			String email = request.getParameter("email_empresa");
			String rutEmpresa = request.getParameter("rut_empresa");
			int clave = Utiles.generarClave(rutEmpresa, email);
			log.info("Clave generada: " + clave);
			JSONObject salida = new JSONObject();
			salida.put("data",PresentacionHelper.generarClave(rutEmpresa, email,clave));
			html = salida.toJSONString();
		}
		if (action.equals(AUTENTICAR)) {
			String emailEmpresa = request.getParameter("email_empresa");
			String rutEmpresa = request.getParameter("rut_empresa");
			String userCaptchaResponse = request.getParameter("jcaptcha");
			
			JSONObject salida = new JSONObject();
			salida.put("data", PresentacionHelper.autenticar(request, rutEmpresa, emailEmpresa, userCaptchaResponse));
			html = salida.toJSONString();
		}
		if (action.equals(OBTENER_RECURSOS_PANTALLA)) {
			
			JSONObject salida = new JSONObject();
			salida.put("regiones", PrevipassDAO.obtenerRegistros( "custom.obtener_regiones", null));
			salida.put("ciudades", PrevipassDAO.obtenerRegistros( "custom.obtener_ciudades", null));
			salida.put("comunas", PrevipassDAO.obtenerRegistros( "custom.obtener_comunas", null));
			salida.put("actividades_economicas", PrevipassDAO.obtenerRegistros( "custom.obtener_actividades_economicas", null));
			//salida.put("mutuales", PrevipassDAO.obtenerRegistros( "custom.obtener_mutuales", null));
			//salida.put("cajas", PrevipassDAO.obtenerRegistros( "custom.obtener_cajas", null));
			salida.put("formatos", PrevipassDAO.obtenerRegistros( "custom.obtener_textos_formatos", null));
			html = salida.toJSONString();
		}

		if (action.equals(CREAR_EMPRESA)) {
			
			String emailEmpresa = request.getParameter("email_empresa");
			String rutEmpresa = request.getParameter("rut_empresa");
			String tipo = request.getParameter("tipo_seleccion");
			log.info("Tipo seleccion=" + tipo);
			JSONObject salida = new JSONObject();
			
			HashMap data=null;
			SqlMapClient sqlMap =null;
			try {	
				sqlMap = SqlMapLocator.getInstance();
				log.info("Se inicia transacción");
				sqlMap.startTransaction();
				log.info("Se inicia procesarIngreso para Grupo Convenio PC");
				data = IngresoEmpresaHelper.procesarIngreso(sqlMap, rutEmpresa, emailEmpresa, tipo, "mapeo.obtenerGrupoConvenioDefaultPC");
				if(data.get("ERROR")==null){
					log.info("Se inicia insertarEmpresa_Admin ");
					data= IngresoEmpresaHelper.insertarEmpresa_Admin(sqlMap, data);
					if(data.get("ERROR")==null){
						log.info("Se inicia insertarConvenio 1");
						data= IngresoEmpresaHelper.insertarConvenio(sqlMap, data, 1);
						/*if(data.get("ERROR")==null && !tipo.equals("I")){
							//data.clear();
							log.info("Se inicia procesarIngreso para Grupo Convenio 2 formato Araucana");
							data= IngresoEmpresaHelper.procesarIngreso(sqlMap, rutEmpresa, emailEmpresa, tipo, "mapeo.obtenerGrupoConvenioDefaultC2PC");
							if(data.get("ERROR")==null){
								log.info("Se inicia insertarConvenio 2");
								data= IngresoEmpresaHelper.insertarConvenio(sqlMap, data, 2);
							}
						}*/
					}
				}
				if(data.get("ERROR")==null){
					log.info("Se inicia insertLDAP");
					data= IngresoEmpresaHelper.insertLDAP(rutEmpresa, tipo);
					if(data.get("ERROR")==null){
						log.info("Se realiza commitTransaction");
						sqlMap.commitTransaction();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				data.put("ERROR", "NO SE HA REGISTRADO INFORMACIÓN, INTENTE NUEVAMENTE");
			}finally{
				try {
					log.info("Se realiza endTransaction");
					sqlMap.endTransaction();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("Mensaje error: " + e.getMessage());
				}
				log.info("Se inicia informarResultado");
				data= IngresoEmpresaHelper.informarResultado(data, rutEmpresa);
				log.info("data: "+data);
				salida.put("data", data);
				html = salida.toJSONString();
			}
		}
		log.info("Fin registro");
		registrarSalida(response, html);

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

}
