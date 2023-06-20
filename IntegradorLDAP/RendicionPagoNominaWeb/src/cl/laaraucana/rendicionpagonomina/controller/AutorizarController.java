package cl.laaraucana.rendicionpagonomina.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.services.AutorizarService;
import cl.laaraucana.rendicionpagonomina.services.ParametrosService;


@Controller
public class AutorizarController {

	private static final Logger logger = Logger.getLogger(AutorizarController.class);

	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private AutorizarService autorizarService;
	
	@RequestMapping(value = { "/autorizar.do" }, method = RequestMethod.GET)
	public String autorizar(ModelMap model, HttpServletRequest request) {
		
		try {
			List<ConvenioEntity> listaConvenios= parametrosService.consultaConvenios();
			logger.info("Total convenios=" + listaConvenios);
			request.getSession().setAttribute("conveniosAutorizacion", listaConvenios);
			return "autorizacion";
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   Autorizaciones: ");
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		
		return "autorizacion";
	}
	
	@RequestMapping(value = { "/autorizar.do" }, method = RequestMethod.POST)
	public String gestionUsuarios(ModelMap model, HttpServletRequest request) {
		
		String accion = request.getParameter("accion");
		String convenio = request.getParameter("convenio");
		String idUsuario= request.getParameter("idUsuario");
		request.getSession().setAttribute("convenio_actual", convenio);
		try {
			List<ConvenioEntity> listaConvenios = (List<ConvenioEntity>)request.getSession().getAttribute("conveniosAutorizacion");
			if(listaConvenios==null){
				listaConvenios= parametrosService.consultaConvenios();
				logger.info("Total convenios=" + listaConvenios);
				request.getSession().setAttribute("conveniosAutorizacion", listaConvenios);
				return "autorizacion";
			}
			if(accion.equals("agregarUsuario")){
				agregar(model, request);
			}else if(accion.equals("eliminarUsuario")){
				eliminar(model, request);
			}
			
			//Buscando usuarios convenio
			List<String> usuarios= autorizarService.consultaUsuariosConvenio(Integer.parseInt(convenio));
			request.getSession().setAttribute("usuariosConvenio", usuarios);
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   Autorizaciones: ");
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		
		return "autorizacion";
	}
	
	@RequestMapping(value = { "/eliminarUsuario.do" }, method = RequestMethod.GET)
	public String eliminar(ModelMap model, HttpServletRequest request) throws MiException {
	
		String idUsuario= request.getParameter("id");
		String idConvenio = request.getParameter("convenio");
		try {
			autorizarService.deleteUsuario(idUsuario, idConvenio);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		
		return "autorizacion";
	}
	
	
	@RequestMapping(value = { "/agregarUsuario.do" }, method = RequestMethod.GET)
	public String agregar(ModelMap model, HttpServletRequest request) throws MiException {
	
		String idUsuario= request.getParameter("idUsuario");
		String idConvenio = request.getParameter("convenio");
		try {
			autorizarService.insertUsuario(idUsuario, idConvenio);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "autorizacion";
	}
	
	
}
