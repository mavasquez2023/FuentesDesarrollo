package cl.laaraucana.mandatopublico.controller;

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
import cl.laaraucana.mandatopublico.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandatopublico.services.BancoService;
import cl.laaraucana.mandatopublico.services.MandatoAS400Service;
import cl.laaraucana.mandatopublico.util.Configuraciones;
import cl.laaraucana.mandatopublico.vo.EjecutivoVo;
import cl.laaraucana.mandatopublico.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.mandatopublico.ws.ClienteInfoAfiliado;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private BancoService bancoService;

	@Autowired
	private MandatoAS400Service mandatoas400Service;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		return "index-paso1";

	}

	@RequestMapping(value = { "lista.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request) {

		try {

			ClienteInfoAfiliado info = new ClienteInfoAfiliado();
			String rutAfiliado = form.getRutAfiliado().replace(".", "");

			request.getSession().setAttribute("rut", rutAfiliado);
			logger.info("Verificando si RUT  " + rutAfiliado + " es afiliado");

			SalidainfoAfiliadoVO res = info.getDataAfiliado(rutAfiliado);

			if (res.isDeudordirecto() && res.getNombreCompleto() == null) {
				logger.info("RUT " + rutAfiliado + " no es afiliado, se muestra mensaje de error al ejecutivo");
				model.addAttribute("errorMsg", "rut_error");
				return "index-paso1";
			}

			request.getSession().setAttribute("clienteInfo", res);
			model.addAttribute("nombre", res.getNombreCompleto());
			logger.info("Nombre Afiliado:" + res.getNombreCompleto());

			if (form.getRutAfiliado() != null) {

				int rut = Integer.parseInt(rutAfiliado.split("-")[0]);

				List<MandatoAS400Vo> vigentes = new ArrayList<MandatoAS400Vo>();
				List<MandatoAS400Vo> revocados = new ArrayList<MandatoAS400Vo>();
				List<MandatoAS400Vo> rechazados = new ArrayList<MandatoAS400Vo>();
				
				List<MandatoAS400Vo> listaVigentes = mandatoas400Service.consultaMandatos(rut);
				List<MandatoAS400Vo> listaRevocados = mandatoas400Service.consultaMandatosRev(rut);
				List<MandatoAS400Vo> listaRechazados = mandatoas400Service.consultaMandatosRechazados(rut);
				logger.info("Cantidad Mandatos vigentes: " + listaVigentes.size() + ", revocados: " + listaRevocados.size() + ", rechazados_ " + listaRechazados.size());

				for (MandatoAS400Vo vig : listaVigentes) {

					MandatoAS400Vo vo = new MandatoAS400Vo();

					vo.setIdMandato(vig.getIdMandato());
					vo.setNombreBanco(bancoService.findBancoById(vig.getCodbanco()).getNombre());
					vo.setNumcuenta(vig.getNumcuenta());
					vo.setDesCuenta(bancoService.findAccountkById(vig.getIdtipcta()).getDescripcion());

					vigentes.add(vo);
				}

				for (MandatoAS400Vo rev : listaRevocados) {

					MandatoAS400Vo vo = new MandatoAS400Vo();

					vo.setIdMandato(rev.getIdMandato());
					vo.setNombreBanco(bancoService.findBancoById(rev.getCodbanco()).getNombre());
					vo.setNumcuenta(rev.getNumcuenta());
					vo.setDesCuenta(bancoService.findAccountkById(rev.getIdtipcta()).getDescripcion());
					vo.setObservaciones(rev.getObservaciones());
					
					revocados.add(vo);
				}

				for (MandatoAS400Vo recha : listaRechazados) {

					MandatoAS400Vo vo = new MandatoAS400Vo();

					vo.setIdMandato(recha.getIdMandato());
					vo.setNombreBanco(bancoService.findBancoById(recha.getCodbanco()).getNombre());
					vo.setNumcuenta(recha.getNumcuenta());
					vo.setDesCuenta(bancoService.findAccountkById(recha.getIdtipcta()).getDescripcion());
					vo.setObservaciones(recha.getObservaciones());
					rechazados.add(vo);
				}

				if (vigentes.size() == 0 && revocados.size() == 0 && rechazados.size() == 0) {

					model.addAttribute("mandatosVig", vigentes);
					model.addAttribute("mandatosRev", revocados);
					model.addAttribute("mandatosRechazado", rechazados);
					
					return "lista_mandato";
				}

				model.addAttribute("mandatosVig", vigentes);
				model.addAttribute("mandatosRev", revocados);
				model.addAttribute("mandatosRechazado", rechazados);
				
			}

			request.getSession().setAttribute("rut", rutAfiliado);

		} catch (Exception e) {
			logger.error("Error inicio ", e);
		}

		return "lista_mandato";
	}

	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			response.sendRedirect(Configuraciones.getConfig("url.salir.sucursalvirtual"));

		} catch (Exception e) {

			logger.error("Error Ejecutivo Salir", e);

		}
		return null;
	}
}
