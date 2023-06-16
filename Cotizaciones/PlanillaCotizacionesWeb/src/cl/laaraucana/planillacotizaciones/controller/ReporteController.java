package cl.laaraucana.planillacotizaciones.controller;

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
import cl.laaraucana.planillacotizaciones.dto.CabeceraDto;
import cl.laaraucana.planillacotizaciones.dto.CargasDto;
import cl.laaraucana.planillacotizaciones.dto.CotizacionDto;
import cl.laaraucana.planillacotizaciones.dto.NormalDto;
import cl.laaraucana.planillacotizaciones.dto.RetroactivosDto;
import cl.laaraucana.planillacotizaciones.dto.TramoDto;
import cl.laaraucana.planillacotizaciones.persistence.Dao;
import cl.laaraucana.planillacotizaciones.services.ReporteService;
import cl.laaraucana.planillacotizaciones.utils.Configuraciones;
import cl.laaraucana.planillacotizaciones.utils.Utils;

@Controller
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private Dao dao;

	private static final Logger logger = Logger.getLogger(ReporteController.class);

	@RequestMapping(value = { "/reporte.do" }, method = RequestMethod.GET)
	public String report(@ModelAttribute("rut") String rut, @ModelAttribute("per") String per,
			@ModelAttribute("codofi") String codofi, @ModelAttribute("suc") String suc, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String tipo = Configuraciones.getConfig("tasa.codigo");

			List<CabeceraDto> cabeceraFull = dao.getAllCabeceraFull(per, rut, suc, Integer.parseInt(codofi));

			if (cabeceraFull.size() == 0) {

				logger.debug("No hay datos......");

				model.addAttribute("vacio", "vacio");

				return "inicio";
			}

			List<NormalDto> datos = new ArrayList<NormalDto>();

			NormalDto dato = null;

			for (CabeceraDto cabe : cabeceraFull) {

				CotizacionDto cotiza = dao.getCotizacion(cabe.getPeriodo(), Integer.parseInt(tipo));

				dato = new NormalDto();

				dato.setFolio(cabe.getCodBar());
				dato.setOficina(Utils.getCeros(cabe.getCodofi()));
				dato.setRutEmpleador(Utils.formatear(cabe.getRutEmpresa() + "-" + cabe.getDigEmpresa()));
				dato.setSucursal(Utils.getCeros(cabe.getSucursal()));
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
				if (cabe.getTipo().trim().equals("N")) {

					List<TramoDto> tramo = dao.getValorTramo(cabe.getPeriodo());
					List<CargasDto> carga = dao.getCargas(cabe.getPeriodo(), cabe.getRutEmpresa(), cabe.getSucursal(),
							cabe.getCodofi());
					List<RetroactivosDto> retro = dao.getRetroactivos(cabe.getPeriodo(), cabe.getRutEmpresa(),
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

			reporteService.generarReport(request, response, datos);

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "inicio";
	}
}
