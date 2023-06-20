package cl.laaraucana.pubnominas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import cl.laaraucana.pubnominas.services.EstadisticasService;
import cl.laaraucana.pubnominas.utils.AvisoSingleton;
import cl.laaraucana.pubnominas.utils.Configuraciones;
import cl.laaraucana.pubnominas.utils.NominaComparator;
import cl.laaraucana.pubnominas.utils.Utils;
import cl.laaraucana.pubnominas.vo.DescargasxRol;
import cl.laaraucana.pubnominas.vo.DescargasxTipoNomina;
import cl.laaraucana.pubnominas.vo.EmpresasVO;
import cl.laaraucana.pubnominas.vo.NominasPeriodoVO;
import cl.laaraucana.pubnominas.vo.ParamFiltrar;
import cl.laaraucana.pubnominas.vo.ParamFormVO;



@Controller
public class EstadisticasController {

	private static final Logger logger = Logger.getLogger(EstadisticasController.class);
	@Autowired
	private EstadisticasService estadistica;
	
	
	@RequestMapping(value = { "/estadisticas.do" }, method = RequestMethod.GET)
	public String getNominasxPeriodo(ModelMap model, HttpServletRequest request) {
		
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			List<NominasPeriodoVO> nomxmes=  estadistica.getNominasxMes();
			request.getSession().setAttribute("periodos", nomxmes);
	
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
			return "nominaError";
		}
		request.getSession().setAttribute("menu", "estadisticas");
		return "estadisticas";
	}
	
	@RequestMapping(value = { "/estadisticas2.do" }, method = RequestMethod.GET)
	public String getDescargasxTipoNomina(ModelMap model, ParamFormVO form, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			String periodo= form.getPeriodo();
			if(periodo==null){
				periodo= (String)request.getSession().getAttribute("periodoActual");
			}
			if(periodo==null){
				periodo= Utils.getFechaPeriodo();
			}

			logger.info("Consultando Descargas x Tipo Nomina");
			List<DescargasxTipoNomina> descargasxtipo=  estadistica.getDescargasxRol(periodo);
			
			List<DescargasxRol> descargasxrol= new ArrayList<DescargasxRol>();
			String nominaAnterior="";
			DescargasxRol desxrol=null;
			for (Iterator iterator2 = descargasxtipo.iterator(); iterator2
					.hasNext();) {
				DescargasxTipoNomina descxtipnom = (DescargasxTipoNomina) iterator2
						.next();
				if(!descxtipnom.getTipoNomina().equals(nominaAnterior)){
					if(!nominaAnterior.equals("")){
						descargasxrol.add(desxrol);
					}
					desxrol= new DescargasxRol();
					desxrol.setTipoNomina(descxtipnom.getTipoNomina());
				}
				if(descxtipnom.getRol().trim().equals("encargado")){
					desxrol.setCantidadEncargado(descxtipnom.getCantidad());
				}else{
					desxrol.setCantidadEjecutivo(descxtipnom.getCantidad());
				}
				nominaAnterior= descxtipnom.getTipoNomina();
			}
			descargasxrol.add(desxrol);
			logger.info("cantidad tipo nomina= " + descargasxrol.size());
			
			//Collections.sort(descargasxrol, new NominaComparator());
			request.setAttribute("descargasxNomina", descargasxrol);
			request.getSession().setAttribute("periodoActual",periodo);

			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "nominaError";
		}

		return "estadisticas2";
	}
}
