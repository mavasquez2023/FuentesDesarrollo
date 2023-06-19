package cl.laaraucana.autoasfam.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.autoasfam.dto.AutorizacionesMODDto;
import cl.laaraucana.autoasfam.dto.CabeceraMODDto;
import cl.laaraucana.autoasfam.dto.CargasAUTDto;
import cl.laaraucana.autoasfam.dto.PaginasMODDto;
import cl.laaraucana.autoasfam.dto.PendientesMODDto;
import cl.laaraucana.autoasfam.dto.SuspensionesMODDto;
import cl.laaraucana.autoasfam.dto.TotalesAUTDto;
import cl.laaraucana.autoasfam.persistence.Dao;
import cl.laaraucana.autoasfam.services.ReporteService;
import cl.laaraucana.autoasfam.utils.Configuraciones;
import cl.laaraucana.autoasfam.vo.ModificacionesVO;


@Controller
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private Dao dao;

	private static final Logger logger = Logger.getLogger(ReporteController.class);

	@RequestMapping(value = { "/reporteAUT.do" }, method = RequestMethod.GET)
	public String reportAUT(@ModelAttribute("rutemp") String rutEmpresa, @ModelAttribute("periodo") String periodo,
			@ModelAttribute("rutafi") String rutAfiliado, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			List<CargasAUTDto> cargas = dao.getCargasAfiliadoAUT(periodo, rutEmpresa, rutAfiliado);

			if (cargas.size() == 0) {

				logger.debug("No hay datos......");

				model.addAttribute("vacio", "vacio");

				return "inicio";
			}
			
			List<TotalesAUTDto> totales=  dao.getTotalesAUT(periodo, rutEmpresa, rutAfiliado);
			
			
			reporteService.generarReportAUT(request, response, cargas, totales.get(0));

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "inicio";
	}
	
	@RequestMapping(value = { "/reporteMOD.do" }, method = RequestMethod.GET)
	public String reportMOD(@ModelAttribute("rutemp") String rutEmpresa, @ModelAttribute("periodo") String periodo,
			@ModelAttribute("oficina") String oficina, @ModelAttribute("sucursal") String sucursal, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			int registrosxpagina_autysusp=Integer.parseInt(Configuraciones.getConfig("registros.por.pagina.aut_susp"));
			int registros_pend=Integer.parseInt(Configuraciones.getConfig("registros.por.pagina.pendientes"));
			CabeceraMODDto cabeceraMOD= (CabeceraMODDto) request.getSession().getAttribute("cabecera");
			cabeceraMOD.setOficina(oficina);
			cabeceraMOD.setSucursal(sucursal);
			
			List<AutorizacionesMODDto> autorizaciones = dao.getAutorizacionesMOD(periodo, rutEmpresa, oficina, sucursal);
			List<SuspensionesMODDto> suspensiones = dao.getSuspensionesMOD(periodo, rutEmpresa, oficina, sucursal);
			List<PendientesMODDto> pendientes = dao.getPendientesMOD(periodo, rutEmpresa, oficina, sucursal);
			
			if (autorizaciones.size() == 0 & suspensiones.size()==0 && pendientes.size()==0) {
				logger.debug("No hay datos......");
				model.addAttribute("vacio", "vacio");
				return "iniciomod";
			}
			
			ModificacionesVO modificacionesVO= new ModificacionesVO();
			modificacionesVO.setCabecera(cabeceraMOD);
			modificacionesVO.setAutorizaciones(autorizaciones);
			modificacionesVO.setSuspensiones(suspensiones);
			modificacionesVO.setPendientes(pendientes);
			modificacionesVO.setCantAutorizaciones(autorizaciones.size());
			modificacionesVO.setCantSuspensiones(suspensiones.size());
			modificacionesVO.setCantPendientes(pendientes.size());
			
			int pagsAutorizaciones= (int)Math.ceil(autorizaciones.size()/registrosxpagina_autysusp);
			int pagsSuspensiones= (int)Math.ceil(suspensiones.size()/registrosxpagina_autysusp);
			PaginasMODDto pendientesObj= dao.getPaginaPenMOD(periodo, rutEmpresa, oficina, sucursal, registros_pend).get(0);
			int pagsPendientes= pendientesObj.getCantidad();
			if(pendientesObj.getPaginaAdicional()>0){
				pagsPendientes++;
			}
			int desdeAutorizaciones=0;
			int hastaAutorizaciones=0;
			int desdeSuspensiones=0;
			int hastaSuspensiones=0;
			int desdePendientes=0;
			int hastaPendientes=0;
			if(modificacionesVO.getCantAutorizaciones()>0){
				desdeAutorizaciones=1;
				hastaAutorizaciones= desdeAutorizaciones + pagsAutorizaciones;
			}
			if(modificacionesVO.getCantSuspensiones()>0){
				desdeSuspensiones=hastaAutorizaciones +1 ;
				hastaSuspensiones= desdeSuspensiones + pagsSuspensiones;
			}
			
			if(modificacionesVO.getCantPendientes()>0){
				if(hastaSuspensiones>0){
					desdePendientes=hastaSuspensiones +1 ;
				}else{
					desdePendientes=hastaAutorizaciones+1;
				}
				hastaPendientes= desdePendientes + pagsPendientes;
			}
			modificacionesVO.setDesdeAutorizaciones(desdeAutorizaciones);
			modificacionesVO.setHastaAutorizaciones(hastaAutorizaciones);
			modificacionesVO.setDesdeSuspensiones(desdeSuspensiones);
			modificacionesVO.setHastaSuspensiones(hastaSuspensiones);
			modificacionesVO.setDesdePendientes(desdePendientes);
			modificacionesVO.setHastaPendientes(hastaPendientes);
			
			reporteService.generarReportMOD(request, response, modificacionesVO);

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "iniciomod";
	}
}
