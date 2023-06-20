package cl.laaraucana.pubnominas.controller;

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


import cl.laaraucana.pubnominas.dto.asfam.AutorizacionesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.CabeceraMODDto;
import cl.laaraucana.pubnominas.dto.asfam.CargasAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.PaginasMODDto;
import cl.laaraucana.pubnominas.dto.asfam.PendientesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.SuspensionesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.TotalesAUTDto;
import cl.laaraucana.pubnominas.dto.cotizacion.CabeceraDto;
import cl.laaraucana.pubnominas.dto.cotizacion.CargasDto;
import cl.laaraucana.pubnominas.dto.cotizacion.CotizacionDto;
import cl.laaraucana.pubnominas.dto.cotizacion.NormalDto;
import cl.laaraucana.pubnominas.dto.cotizacion.RetroactivosDto;
import cl.laaraucana.pubnominas.dto.cotizacion.TramoDto;
import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.dto.pex.OficinaDto;
import cl.laaraucana.pubnominas.persistence.DaoAsfam;
import cl.laaraucana.pubnominas.persistence.DaoCotizacion;
import cl.laaraucana.pubnominas.services.BitacoraService;
import cl.laaraucana.pubnominas.services.ReporteService;
import cl.laaraucana.pubnominas.utils.Configuraciones;
import cl.laaraucana.pubnominas.utils.Utils;
import cl.laaraucana.pubnominas.utils.UtilsReport;
import cl.laaraucana.pubnominas.vo.ModificacionesVO;



@Controller
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private DaoAsfam daoASFAM;
	
	@Autowired
	private DaoCotizacion daoCOT;
	
	@Autowired
	private BitacoraService bitacora;

	private static final Logger logger = Logger.getLogger(ReporteController.class);

	@RequestMapping(value = { "/reporteAUT.do" }, method = RequestMethod.GET)
	public String reportAUT(@ModelAttribute("rutemp") String rutEmpresa, @ModelAttribute("periodo") String periodo,
			@ModelAttribute("rutafi") String rutAfiliado, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			//Buscando Autorizaciones Afiliado
			logger.info("Buscando AUTORIZACION Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", RutAfiliado:" + rutAfiliado);
			List<CargasAUTDto> cargas = daoASFAM.getCargasAfiliadoAUT(periodo, rutEmpresa, rutAfiliado);

			if (cargas.size() == 0) {
				logger.info("No hay datos......");
				model.addAttribute("vacio", "vacio");
				return "inicio";
			}
			
			//Totalizando Autorizaciones Afiliado
			List<TotalesAUTDto> totales=  daoASFAM.getTotalesAUT(periodo, rutEmpresa, rutAfiliado);
			
			//Generando PDF
			logger.info("Descargando AUTORIZACION Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", RutAfiliado:" + rutAfiliado);
			reporteService.generarReportAUT(request, response, cargas, totales.get(0));
			
			//Grabando Bitacora
			bitacora.insertBitacora("AUTORIZACION", (String)request.getSession().getAttribute("rutUsuario"), (String)request.getSession().getAttribute("rol"), periodo, rutEmpresa, totales.get(0).getOficina(), totales.get(0).getSucursal());
			
		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return null;
	}
	
	@RequestMapping(value = { "/reporteMOD.do" }, method = RequestMethod.GET)
	public String reportMOD(@ModelAttribute("rutemp") String rutEmpresa, @ModelAttribute("periodo") String periodo,
			@ModelAttribute("oficina") String oficina, @ModelAttribute("sucursal") String sucursal, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			int registrosxpagina_autysusp=Integer.parseInt(Configuraciones.getConfig("registros.por.pagina.aut_susp"));
			int registros_pend=Integer.parseInt(Configuraciones.getConfig("registros.por.pagina.pendientes"));
			CabeceraMODDto cabeceraMOD= (CabeceraMODDto) request.getSession().getAttribute("cabecera");
			cabeceraMOD.setOficina(oficina);
			cabeceraMOD.setSucursal(sucursal);
			
			logger.info("Buscando MODIFICACIONES Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", Oficina:" + oficina);
			
			//buscando Autorizaciones, Suspensiones y Pendientes de Modificaciones por oficina
			List<AutorizacionesMODDto> autorizaciones = daoASFAM.getAutorizacionesMOD(periodo, rutEmpresa, oficina, sucursal);
			List<SuspensionesMODDto> suspensiones = daoASFAM.getSuspensionesMOD(periodo, rutEmpresa, oficina, sucursal);
			List<PendientesMODDto> pendientes = daoASFAM.getPendientesMOD(periodo, rutEmpresa, oficina, sucursal);
			
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
			PaginasMODDto pendientesObj= daoASFAM.getPaginaPenMOD(periodo, rutEmpresa, oficina, sucursal, registros_pend).get(0);
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
			
			//Genereando PDF
			logger.info("Descargando MODIFICACIONES Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", Oficina:" + oficina);
			reporteService.generarReportMOD(request, response, modificacionesVO);
			
			//Grabando Bitacora
			bitacora.insertBitacora("MODIFICACIONES", (String)request.getSession().getAttribute("rutUsuario"), (String)request.getSession().getAttribute("rol"), periodo, rutEmpresa, modificacionesVO.getCabecera().getOficina(), modificacionesVO.getCabecera().getSucursal());

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return null;
	}
	
	@RequestMapping(value = { "/reporteCOT.do" }, method = RequestMethod.GET)
	public String reportCOT(@ModelAttribute("rutemp") String rutEmpresa, @ModelAttribute("periodo") String periodo,
			@ModelAttribute("codofi") String oficina, @ModelAttribute("sucursal") String sucursal, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			String tipo = Configuraciones.getConfig("tasa.codigo");
			
			logger.info("Buscando COTIZACIONES Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", Oficina:" + oficina);
			List<CabeceraDto> cabeceraFull = daoCOT.getAllCabeceraFull(periodo, rutEmpresa, sucursal, Integer.parseInt(oficina));

			if (cabeceraFull.size() == 0) {

				logger.debug("No hay datos......");

				model.addAttribute("vacio", "vacio");

				return "inicio";
			}

			List<NormalDto> datos = new ArrayList<NormalDto>();

			NormalDto dato = null;

			for (CabeceraDto cabe : cabeceraFull) {

				CotizacionDto cotiza = daoCOT.getCotizacion(cabe.getPeriodo(), Integer.parseInt(tipo));

				dato = new NormalDto();

				dato.setFolio(cabe.getCodBar());
				dato.setOficina(UtilsReport.getCeros(cabe.getCodofi()));
				dato.setRutEmpleador(UtilsReport.formatear(cabe.getRutEmpresa() + "-" + cabe.getDigEmpresa()));
				dato.setSucursal(UtilsReport.getCeros(cabe.getSucursal()));
				dato.setPeriodo(cabe.getPeriodo());
				dato.setMes(cabe.getPeriodo().substring(4, 6));
				dato.setYear(cabe.getPeriodo().substring(0, 4));
				dato.setRazonSocial(cabe.getRazonsocial());
				dato.setOficinaRazon(cabe.getNomSucursal());
				dato.setCodActividadEconomica("");
				dato.setDireccion("");
				dato.setRepresentanteLegal("");
				dato.setRutRepresentante("");
				dato.setAfiliadoSi("");
				dato.setAfiliadoNo("");
				dato.setCorreo("");
				dato.setF101("");
				dato.setF102("");
				dato.setF103("");
				dato.setF111("");
				dato.setF112("");
				dato.setF113("");
				dato.setF121("");
				dato.setF122("");
				dato.setF123("");
				double pordouble= Double.parseDouble(cotiza.getPorcentaje());
				dato.setTasaCotizacion(String.valueOf(pordouble));
				dato.setMontoCotizaciones("");
				dato.setTipoDeclaracion("PAGO");
				//Si es una Declaración 
				if(cabe.getTipoDeclaracion().equals("D")){
					dato.setTipoDeclaracion("NO PAGO");
				}
				if (cabe.getTipo().trim().equals("N")) {

					List<TramoDto> tramo = daoCOT.getValorTramo(cabe.getPeriodo());
					List<CargasDto> carga = daoCOT.getCargas(cabe.getPeriodo(), cabe.getRutEmpresa(), cabe.getSucursal(),
							cabe.getCodofi());
					List<RetroactivosDto> retro = daoCOT.getRetroactivos(cabe.getPeriodo(), cabe.getRutEmpresa(),
							cabe.getSucursal(), cabe.getCodofi());

					dato.setF131(tramo.size() != 0 ? tramo.get(0).getTramo() : "0");
					dato.setF132(carga.size() != 0 ? carga.get(0).getInvalidas() : "0");
					dato.setF133(carga.size() != 0 ? carga.get(0).getCnormales() : "0");
					dato.setF134(tramo.size() != 0 && carga.size() != 0 ? String.valueOf(
							Long.parseLong(tramo.get(0).getTramo()) * (2 * Integer.parseInt(carga.get(0).getInvalidas())
									+ Integer.parseInt(carga.get(0).getCnormales())))
							: "0");
					dato.setF135("");
					dato.setF136("");
					dato.setF141(tramo.size() != 0 ? tramo.get(1).getTramo() : "0");
					dato.setF142(carga.size() != 0 ? carga.get(1).getInvalidas() : "0");
					dato.setF143(carga.size() != 0 ? carga.get(1).getCnormales() : "0");
					dato.setF144(tramo.size() != 0 && carga.size() != 0 ? String.valueOf(
							Long.parseLong(tramo.get(1).getTramo()) * (2 * Integer.parseInt(carga.get(1).getInvalidas())
									+ Integer.parseInt(carga.get(1).getCnormales())))
							: "0");
					dato.setF145("");
					dato.setF146("");
					dato.setF151(tramo.size() != 0 ? tramo.get(2).getTramo() : "0");
					dato.setF152(carga.size() != 0 ? carga.get(2).getInvalidas() : "0");
					dato.setF153(carga.size() != 0 ? carga.get(2).getCnormales() : "0");
					dato.setF154(tramo.size() != 0 && carga.size() != 0 ? String.valueOf(
							Long.parseLong(tramo.get(2).getTramo()) * (2 * Integer.parseInt(carga.get(2).getInvalidas())
									+ Integer.parseInt(carga.get(2).getCnormales())))
							: "0");
					dato.setF155("");
					dato.setF156("");
					dato.setF161(tramo.size() != 0 ? tramo.get(3).getTramo() : "0");
					dato.setF162(carga.size() != 0 ? carga.get(3).getInvalidas() : "0");
					dato.setF163(carga.size() != 0 ? carga.get(3).getCnormales() : "0");
					dato.setF164(tramo.size() != 0 && carga.size() != 0 ? String.valueOf(
							Long.parseLong(tramo.get(3).getTramo()) * (2 * Integer.parseInt(carga.get(3).getInvalidas())
									+ Integer.parseInt(carga.get(3).getCnormales())))
							: "0");
					dato.setF165("");
					dato.setF166("");
					dato.setF171("");
					dato.setF172(retro.size() != 0 ? retro.get(0).getCnormal() : "0");
					dato.setF173(retro.size() != 0 ? retro.get(0).getMonto().split("\\.")[0] : "0");
					dato.setF174("");
					dato.setF175("");
				} else {
					dato.setF131("");
					dato.setF132("");
					dato.setF133("");
					dato.setF134("");
					dato.setF135("");
					dato.setF136("");
					dato.setF141("");
					dato.setF142("");
					dato.setF143("");
					dato.setF144("");
					dato.setF145("");
					dato.setF146("");
					dato.setF151("");
					dato.setF152("");
					dato.setF153("");
					dato.setF154("");
					dato.setF155("");
					dato.setF156("");
					dato.setF161("");
					dato.setF162("");
					dato.setF163("");
					dato.setF164("");
					dato.setF165("");
					dato.setF166("");
					dato.setF171("");
					dato.setF172("");
					dato.setF173("");
					dato.setF174("");
					dato.setF175("");
				}
				dato.setCompensada("");
				dato.setEmpleador("");
				dato.setCcaf("");
				dato.setReajustes("");
				dato.setIntereses("");
				dato.setMultas("");
				dato.setTotalGravamenes("");

				datos.add(dato);
			}
			
			//Generando reporte
			logger.info("Descargando COTIZACIONES Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", Oficina:" + oficina);
			reporteService.generarReportCOT(request, response, datos);
			
			//grabando botacora
			bitacora.insertBitacora("COTIZACION", (String)request.getSession().getAttribute("rutUsuario"), (String)request.getSession().getAttribute("rol"), periodo, rutEmpresa, oficina, sucursal);

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return null;
	}
	
	@RequestMapping(value = { "/reportePEX.do" }, method = RequestMethod.POST)
	public String reportPEX(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			CuotaPEXDto cabecera= (CuotaPEXDto)request.getSession().getAttribute("cabecera");
			List<CuotaPEXDto> detalle= (List<CuotaPEXDto>)request.getSession().getAttribute("trabajadores");
			
			//Generando Reporte
			CuotaPEXDto pex= (CuotaPEXDto)detalle.get(0);
			logger.info("Descargando PEX Periodo: " + Utils.getFechaPeriodo() + ", RutEmpresa:" + pex.getRutEmpresa() + ", Oficina:" + pex.getOficina());
			reporteService.generarReportPEX(request, response, detalle);
			
			//grabando bitacora
			bitacora.insertBitacora("PEX", (String)request.getSession().getAttribute("rutUsuario"), (String)request.getSession().getAttribute("rol"), Utils.getFechaPeriodo(), String.valueOf(pex.getRutEmpresa()), pex.getOficina(), pex.getSucursal());
			

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return null;
	}
}
