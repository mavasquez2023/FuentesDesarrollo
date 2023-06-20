package cl.laaraucana.pubnominas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.pubnominas.services.EmpresasService;
import cl.laaraucana.pubnominas.utils.AvisoSingleton;
import cl.laaraucana.pubnominas.utils.Configuraciones;
import cl.laaraucana.pubnominas.utils.Utils;
import cl.laaraucana.pubnominas.vo.EmpresasVO;
import cl.laaraucana.pubnominas.vo.ParamFiltrar;



@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	@Autowired
	private EmpresasService empsingleton;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {
		String appID="PorEmpAdhe";
		String appEjecutivos=Configuraciones.getConfig("app.Ejecutivos");
		String appOperadores=Configuraciones.getConfig("app.Encargados");;
		String rolID= "PorEmpAdheEnc";
		try {
			HttpSession sesion= request.getSession();
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				//USUARIO
				Principal principal = request.getUserPrincipal();
				if (principal != null && principal.getName() != null) {
					String userID= principal.getName();
					logger.info("RUT: " + userID + " ha iniciado sesión:");
					
					//USERREGISTRY
					UserRegistryConnection connection = new UserRegistryConnection();
					User user= connection.getUser(userID);
					sesion.setAttribute("nombreUsuario", user.getName());
					sesion.setAttribute("rutUsuario",userID);
					//ROLES
					List rolesOpe= (List)connection.getUserRoles(userID, appOperadores);
					List rolesEje= (List)connection.getUserRoles(userID, appEjecutivos); 
					if(rolesOpe.size()>0){
						if(rolesOpe.contains("Encargado")){
							rol="operador";
						}
					}else if(rolesEje.size()>0){
						rol="ejecutivo";
					}else{
						//EMPRESAS ENCARGADO
						Map<String, Enterprise> mapaEmpresas= new HashMap<String, Enterprise>();
						Collection<Enterprise> empresas= (Collection<Enterprise>)connection.getEnterprises(userID, appID, rolID);
						if(empresas.size()>0){
							rol= "encargado";
							sesion.setAttribute("filtro", "0");
							for (Iterator iterator = empresas.iterator(); iterator
									.hasNext();) {
								Enterprise enterprise = (Enterprise) iterator.next();
								mapaEmpresas.put(enterprise.getID(), enterprise);
								if(empresas.size()==1){
									request.getSession().setAttribute("rutEmpresas", enterprise.getID().split("-")[0]);
									sesion.setAttribute("filtro", "-1");
								}
							}

							//logger.info("Empresas autorizadas:" + empresas.toString());
							sesion.setAttribute("empresasAutorizadas", empresas);
							sesion.setAttribute("empresasFiltradas", empresas);
							sesion.setAttribute("mapaEmpresas", mapaEmpresas);
							
						}
					}
					if(rol==null){
						return "redirect:/exit.do";
					}
					request.getSession().setAttribute("rol", rol);
					
					//AÑOS COMBOBOX PERIODO PERSONALIZADO
					Integer cantidadAnios= (Integer)AvisoSingleton.getInstance().getCatidadAnios();
					List<Integer> anios = Utils.getAnios(cantidadAnios-1);
					sesion.setAttribute("Anios", anios);
					
					//PRIODODOS
					String periodo= Utils.getFechaPeriodo();
					String periodo_anterior= Utils.obtenerPeriodoCualquiera(-1);
					sesion.setAttribute("periodo", periodo);
					sesion.setAttribute("periodo_actual", periodo);
					sesion.setAttribute("periodo_anterior", periodo_anterior);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "inicio");
		return "inicio";
	}
	
	@RequestMapping(value = { "/setPeriodo" }, method = RequestMethod.GET)
	public String setPeriodo(ModelMap model, @RequestParam(value = "periodo", required = true) String periodo, @RequestParam(value = "perso", required = true) String personalizado, HttpServletRequest request, HttpServletResponse response) {
		JSONObject salida = new JSONObject();
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			request.getSession().setAttribute("periodo", periodo.trim());
			
			request.getSession().setAttribute("mes", periodo.trim().substring(4, 6));
			request.getSession().setAttribute("ano", periodo.trim().substring(0, 4));
			
			if(personalizado.equals("")){
				request.getSession().removeAttribute("mes");
				request.getSession().removeAttribute("ano");
			}
			salida.put("periodo", periodo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error ", e);
		}
		registrarSalida(response, salida.toJSONString()); 
		return null;
	}
	
	@RequestMapping(value = { "/setAnios" }, method = RequestMethod.GET)
	public String setAnios(ModelMap model, @RequestParam(value = "cantidad", required = true) String cantidad, HttpServletRequest request, HttpServletResponse response) {
		JSONObject salida = new JSONObject();
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			List<Integer> anios = Utils.getAnios(Integer.parseInt(cantidad)-1);
			request.getSession().setAttribute("Anios", anios);
			AvisoSingleton.getInstance().setCatidadAnios(Integer.parseInt(cantidad));
			
			salida.put("anios", anios);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error ", e);
		}
		registrarSalida(response, salida.toJSONString()); 
		return null;
	}
	
	@RequestMapping(value = { "/setRutEmpresa" }, method = RequestMethod.GET)
	public String setRutEmpresa(ModelMap model, @RequestParam(value = "rutEmpresa", required = true) String rutEmpresa, HttpServletRequest request, HttpServletResponse response) {
		JSONObject salida = new JSONObject();
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String rutint= rutEmpresa.replaceAll("\\.", "").split("-")[0];
			request.getSession().setAttribute("rutEmpresa", rutEmpresa);
			request.getSession().setAttribute("rutEmpresas", rutint);
			request.getSession().setAttribute("aviso", "0");
			
			//EmpresasVO empresa= EmpresasSingleton.getInstance().getEmpresas().get(rutint);
			EmpresasVO empresa= empsingleton.getEmpresas().get(rutint);
			
			salida.put("estado", "OK");
			if(empresa!=null){
				salida.put("razonSocial", empresa.getName());
				request.getSession().setAttribute("razonSocial", empresa.getName());
			}else{
				salida.put("razonSocial", "Sin Información");
				request.getSession().setAttribute("razonSocial", "");
			}
		} catch (Exception e) {
			// TODO: handle exception
			salida.put("estado", "ERROR");
			logger.error("Error ", e);
		}
		registrarSalida(response, salida.toJSONString()); 
		return null;
	}
	
	@RequestMapping(value = { "/setAviso" }, method = RequestMethod.GET)
	public String setAviso(ModelMap model, 
			@RequestParam(value = "activo", required = true) String activo,
			@RequestParam(value = "parrafo1", required = true) String parrafo1,
			@RequestParam(value = "parrafo2", required = true) String parrafo2,
			@RequestParam(value = "nota", required = true) String nota,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject salida = new JSONObject();
		try {
			if(activo.equals("true")){
				AvisoSingleton.getInstance().setActivo(true);
				AvisoSingleton.getInstance().setParrafo1(parrafo1);
				AvisoSingleton.getInstance().setParrafo2(parrafo2);
				AvisoSingleton.getInstance().setNota(nota);
				salida.put("mensaje", "AVISO ACTIVO");
			}else{
				AvisoSingleton.getInstance().setActivo(false);
				salida.put("mensaje", "AVISO OCULTO" );
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		registrarSalida(response, salida.toJSONString()); 
		return null;
	}
	
	@RequestMapping(value = { "/seleccion.do" }, method = RequestMethod.GET)
	public String getFiltrar(ModelMap model, HttpServletRequest request) {
		
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		return "filtrar";
	}
	
	@RequestMapping(value = { "/limpiarFiltro.do" }, method = RequestMethod.GET)
	public String limpiarFiltro(ModelMap model, HttpServletRequest request) {
		
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			request.getSession().setAttribute("empresasFiltradas", request.getSession().getAttribute("empresasAutorizadas"));
			request.getSession().setAttribute("filtro", "0");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		return "inicio";
	}
	
	@RequestMapping(value = { "/filtrar.do" }, method = RequestMethod.POST)
	public String setFiltrar(ModelMap model, @ModelAttribute ParamFiltrar form, HttpServletRequest request) {
		
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Enterprise> mapaEmpresas= (Map<String, Enterprise>)request.getSession().getAttribute("mapaEmpresas");
			List<Enterprise> empresas_filtradas= new ArrayList<Enterprise>();
			
			String lista="";
			String[] filtradas= form.getEmpresa_filtrar().split(",");			
			for (int i = 0; i < filtradas.length; i++) {
				empresas_filtradas.add(mapaEmpresas.get(filtradas[i]));
				lista= lista + mapaEmpresas.get(filtradas[i]).getID().split("-")[0] + " ";
			}
			lista=lista.trim();
			request.getSession().setAttribute("empresasFiltradas", empresas_filtradas);
			request.getSession().setAttribute("rutEmpresas", lista);
			request.getSession().setAttribute("filtro", "1");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		return "inicio";
	}
	
	@RequestMapping(value = { "/initaut.do" }, method = RequestMethod.GET)
	public String getInitAUT(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "autorizacion");
		return "nominaAutorizaciones";
	}
	
	@RequestMapping(value = { "/initcot.do" }, method = RequestMethod.GET)
	public String getInitCOT(ModelMap model, HttpServletRequest request) {

		try {

			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "cotizacion");
		return "nominaCotizaciones";
	}
	
	@RequestMapping(value = { "/initpex.do" }, method = RequestMethod.GET)
	public String getInitPEX(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "pex");
		return "nominaPEX";
	}
	
	@RequestMapping(value = { "/initcre.do" }, method = RequestMethod.GET)
	public String getInitCREDITO(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "credito");
		return "nominaCredito";
	}
	@RequestMapping(value = { "/initlea.do" }, method = RequestMethod.GET)
	public String getInitLEASING(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "leasing");
		return "nominaLeasing";
	}
	@RequestMapping(value = { "/initanx.do" }, method = RequestMethod.GET)
	public String getInitANEXO(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String empresas= (String)request.getSession().getAttribute("rutEmpresas");
			if(empresas==null){
				request.getSession().setAttribute("aviso", "1");
				if(rol.equals("ejecutivo") || rol.equals("operador")){
					return "inicio";
				}else{
					return "redirect:/seleccion.do";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}
		request.getSession().setAttribute("menu", "anexo");
		return "nominaAnexo";
	}
	
	@RequestMapping(value = { "/sv_planillas.do" }, method = RequestMethod.GET )
	public String goPlanillasSV(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Rediregiendo a Planillas de SV");
			request.getSession().removeAttribute("rol");
			request.getSession().invalidate();
			response.sendRedirect(Configuraciones.getConfig("url.sv.planillas"));
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "nominaError";
		}

		return null;
	}
	
	@RequestMapping(value = { "/sv_inicio.do" }, method = RequestMethod.GET )
	public String goInicioSV(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Rediregiendo a Inicio de SV");
			request.getSession().removeAttribute("rol");
			request.getSession().invalidate();
			response.sendRedirect(Configuraciones.getConfig("url.sv.inicio"));
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "nominaError";
		}

		return null;
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			String rol= (String)request.getSession().getAttribute("rol");
			request.getSession().removeAttribute("rol");
			request.getSession().invalidate();
			if (rol.equals("ejecutivo") || rol.equals("operador")){
				response.sendRedirect("ibm_security_logout?logoutExitPage=index.jsp");
			}else{
				response.sendRedirect(Configuraciones.getConfig("url.sv.inicio"));
				//response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "nominaError";
		}

		return null;
	}
	
	private void registrarSalida(HttpServletResponse response, String result) {
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
