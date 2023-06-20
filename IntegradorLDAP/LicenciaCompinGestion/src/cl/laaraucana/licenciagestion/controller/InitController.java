package cl.laaraucana.licenciagestion.controller;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.araucana.core.util.Rut;
import cl.laaraucana.licenciagestion.ibatis.dao.ConsultaLicenciasDAO;
import cl.laaraucana.licenciagestion.services.RegistroLicenciasService;
import cl.laaraucana.licenciagestion.util.OficinaComparator;
import cl.laaraucana.licenciagestion.util.RangoComparator;
import cl.laaraucana.licenciagestion.util.Utils;
import cl.laaraucana.licenciagestion.vo.OficinasLicenciasVO;
import cl.laaraucana.licenciagestion.vo.LicenciasPeriodoVO;
import cl.laaraucana.licenciagestion.vo.LicenciasViaIngresoVO;
import cl.laaraucana.licenciagestion.vo.OficinaViaIngreso;
import cl.laaraucana.licenciagestion.vo.OficinasxRango;
import cl.laaraucana.licenciagestion.vo.ParamFormVO;
import cl.laaraucana.licenciagestion.vo.RangoVO;


@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private RegistroLicenciasService licenciasService;
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String rut="";
			Principal principal = request.getUserPrincipal();
			if(principal!=null){
				rut= principal.getName();
			}

			Rut rut_format= new Rut(rut.split("-")[0]);
			request.getSession().setAttribute("rut", rut_format.toString());
			
			logger.info("Consultando max rango licencias");
			List<LicenciasPeriodoVO> lista= licenciasService.licenciasxMes();
			logger.info("lista meses= " + lista.size());
			request.getSession().setAttribute("periodos", lista);
			request.getSession().removeAttribute("periodoActual");

			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}
	
	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.GET)
	public String getLicenciasOficinas(ModelMap model, ParamFormVO form, HttpServletRequest request) {

		try {
			String periodo= form.getPeriodo();
			if(periodo==null){
				periodo= (String)request.getSession().getAttribute("periodoActual");
			}
			if(periodo==null){
				periodo= Utils.getPeriodo();
			}

			logger.info("Consultando licencias x Oficina");
			List<LicenciasViaIngresoVO> oficinasxViaIngreso=  licenciasService.licenciasxViaIngeso(periodo);
			
			List<OficinaViaIngreso> listaOficinas= new ArrayList<OficinaViaIngreso>();
			String sucursalAnterior="";
			OficinaViaIngreso oficina=null;
			for (Iterator iterator2 = oficinasxViaIngreso.iterator(); iterator2
					.hasNext();) {
				LicenciasViaIngresoVO licviaingreso = (LicenciasViaIngresoVO) iterator2
						.next();
				if(!licviaingreso.getSucursal().equals(sucursalAnterior)){
					if(!sucursalAnterior.equals("")){
						listaOficinas.add(oficina);
					}
					oficina= new OficinaViaIngreso();
					oficina.setSucursal(licviaingreso.getSucursal());
				}
				if(licviaingreso.getViaIngreso().equals("A")){
					oficina.setCantidadAfiliado(licviaingreso.getCantidad());
				}else if(licviaingreso.getViaIngreso().equals("E")){
					oficina.setCantidadEmpresa(licviaingreso.getCantidad());
				}
				sucursalAnterior= licviaingreso.getSucursal();
			}
			listaOficinas.add(oficina);
			logger.info("cantidad oficinas= " + listaOficinas.size());
			
			Collections.sort(listaOficinas, new OficinaComparator());
			request.setAttribute("licenciasxOficina", listaOficinas);
			request.getSession().setAttribute("periodoActual",periodo);

			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "paso2DN.do" }, method = RequestMethod.GET)
	public String getDistribucionNormal(ModelMap model, ParamFormVO form, HttpServletRequest request) {

		try {
			String distribucion= form.getDistribucion();
			if(distribucion==null ||  distribucion.equals("") || distribucion.equals("RANGO")){
				distribucion="R";
			}else{
				distribucion="P";
			}
			String periodo= form.getPeriodo();
			if(periodo==null){
				periodo= (String)request.getSession().getAttribute("periodoActual");
			}
			if(periodo==null){
				periodo= Utils.getPeriodo();
			}
			request.getSession().setAttribute("distribucion", distribucion);
			logger.info("Consultando max rango licencias");
			int rango= licenciasService.rangoLicencias(periodo);
			logger.info("rango = " + rango);
			request.getSession().setAttribute("rango", new Integer(rango));
			Map<String, Integer> rangos= new HashMap<String, Integer>();
			Map<Integer, OficinasxRango> mapOficinasRango=new HashMap<Integer, OficinasxRango>();
			List<RangoVO> rangos_combo= new ArrayList<RangoVO>();
			for (int i = 1; i <= 10; i++) {
				rangos.put("rango"+i, i*rango);
				OficinasxRango oxr= new OficinasxRango();
				oxr.setPercentil(i*10);
				oxr.setRango((i-1)*rango + "-" + i*rango);
				mapOficinasRango.put(i*10, oxr);
				RangoVO rangoVO= new RangoVO();
				rangoVO.setDescripcion((i-1)*rango + "-" + i*rango);
				rangoVO.setValue(String.valueOf(i*10));
				rangos_combo.add(rangoVO);
				
			}
			
			 List<LicenciasViaIngresoVO> oficinasxViaIngreso=  licenciasService.licenciasxViaIngeso(periodo);
			
			
			List<OficinasLicenciasVO> listaOficinas=  licenciasService.OficinasxRango(rangos, periodo);
			List<OficinasxRango> listaOficinasxRango= new ArrayList<OficinasxRango>();
			for (Iterator iterator = listaOficinas.iterator(); iterator
					.hasNext();) {
				OficinasLicenciasVO oficinasLicenciasVO = (OficinasLicenciasVO) iterator
						.next();
				OficinasxRango ofixrango= mapOficinasRango.get(oficinasLicenciasVO.getPercentil());
				//ofixrango.setPercentil(oficinasLicenciasVO.getPercentil());
				//ofixrango.setRango(oficinasLicenciasVO.getRango());
				ofixrango.addTotal(1);
				
				OficinaViaIngreso oficina= new OficinaViaIngreso();
				oficina.setSucursal(oficinasLicenciasVO.getSucursal());
				ofixrango.addSucursal(oficina);
				for (Iterator iterator2 = oficinasxViaIngreso.iterator(); iterator2
						.hasNext();) {
					LicenciasViaIngresoVO licviaingreso = (LicenciasViaIngresoVO) iterator2
							.next();
					if(licviaingreso.getSucursal().equals(oficinasLicenciasVO.getSucursal())){
						if(licviaingreso.getViaIngreso().equals("A")){
							oficina.setCantidadAfiliado(licviaingreso.getCantidad());
						}else if(licviaingreso.getViaIngreso().equals("E")){
							oficina.setCantidadEmpresa(licviaingreso.getCantidad());
						}
					}
					
				}
			
			}
			
			for (Iterator iterator = mapOficinasRango.values().iterator(); iterator
					.hasNext();) {
				OficinasxRango oficinasxRango = (OficinasxRango) iterator
						.next();
				listaOficinasxRango.add(oficinasxRango);
			}
			Collections.sort(listaOficinasxRango, new RangoComparator());
			request.setAttribute("oficinas", listaOficinasxRango);
			request.getSession().setAttribute("periodoActual",periodo);
			request.getSession().setAttribute("rango_select", rangos_combo);
			request.getSession().setAttribute("mapaOficinasxRang", mapOficinasRango);
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2DN";
	}
	
	@RequestMapping(value = { "paso3.do" }, method = RequestMethod.GET)
	public String getOficinasRango(ModelMap model, ParamFormVO form, HttpServletRequest request) {

		try {
			//int rango= (Integer)request.getSession().getAttribute("rango");
			String rango_selected= form.getRango();
			if(rango_selected==null){
				rango_selected= 10 + "";
			}
			logger.info("Consultando oficinas x Rango del Mapa");
			Map<Integer, OficinasxRango> mapOficinasRango= (Map<Integer, OficinasxRango>)request.getSession().getAttribute("mapaOficinasxRang");
			
			OficinasxRango oficonasxrango= mapOficinasRango.get(Integer.parseInt(rango_selected));
			List<OficinaViaIngreso> listaOficinas=null;
			if(oficonasxrango!=null && oficonasxrango.getListaOficinas()!=null){
				listaOficinas =oficonasxrango.getListaOficinas();
				Collections.sort(listaOficinas, new OficinaComparator());
			}
			
			
			request.setAttribute("oficinas", listaOficinas);
			//request.setAttribute("periodoActual", Utils.getPeriodo());
			request.setAttribute("rangoActual", rango_selected);
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso3";
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	
	@RequestMapping(value = { "/logout.do" }, method = RequestMethod.GET )
	public String closeSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión para no afiliado");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=logout.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}

}
