package cl.laaraucana.diferimientoEspecial.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.diferimientoEspecial.entities.CreditoEntiti;
import cl.laaraucana.diferimientoEspecial.model.CreditoVo;
import cl.laaraucana.diferimientoEspecial.services.BitaEspecialService;
import cl.laaraucana.diferimientoEspecial.services.CreditoService;
import cl.laaraucana.diferimientoEspecial.vo.SalidaSinacofiVO;
import cl.laaraucana.diferimientoEspecial.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.diferimientoEspecial.ws.ClienteInfoAfiliado;
import cl.laaraucana.diferimientoEspecial.ws.ClienteSinacofi;
import cl.laaraucana.diferimientoEspecial.ws.ConstantesRespuestasWS;

 

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CreditoService creService;

	@Autowired
	private BitaEspecialService bitaService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 1  ", e);

			return "proceso_error";
		}

		return "index-paso1";
	}

	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step2(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		DecimalFormat df = new DecimalFormat("###,###");

		try {
			String rut= form.getRutCliente().replace(".", "").toUpperCase();
			ClienteInfoAfiliado info = new ClienteInfoAfiliado();

			logger.debug("ClienteInfo rut: " + rut);

			SalidainfoAfiliadoVO res = info.getDataAfiliado(rut);

			if (res.getNombreCompleto()==null || res.getNombreCompleto().equals("") ) {

				model.addAttribute("errorMsg", "rut_error");

				return "index-paso1";
			}

			ClienteSinacofi cli = new ClienteSinacofi();
			String mensaje = "";
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli
					.call(rut.replace("-", ""), (String) form.getSerie());
			if (respSina != null && respSina.getCodigoError() == ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS) {
				String codigoSina = respSina.getCodigoRetorno();
				logger.info("Respuesta Sinacofi, codigo retorno= " + codigoSina);
				if (respSina.getCodigoRetorno().equals("10000")) {
					logger.info("Cedula Vigente=" + respSina.getCedulaVigente() + ".");
					if (respSina.getCedulaVigente().equals("NO")) {
						mensaje = "serie_error";
					} else {
						mensaje = "OK";
					}
				} else {
					if (respSina.getCodigoRetorno().equals("10001")) {
						mensaje = "Error en parámetros de entrada";
					} else if (codigoSina.equals("10002")) {
						mensaje = "Error interno del servicio";
					} else if (codigoSina.equals("10003")) {
						mensaje = "Error en la autenticación del usuario";
					} else if (codigoSina.equals("10004")) {
						mensaje = "Error de permiso";
					} else if (codigoSina.equals("10005")) {
						mensaje = "Error RUT inválido";
					}
					logger.info("Respuesta de error Sinacofi:" + mensaje);
				}
			}

			if (!mensaje.equals("OK")) {

				model.addAttribute("errorMsg", "serie_error");

				model.addAttribute("rut", form.getRutCliente());
				model.addAttribute("serie", form.getSerie());

				return "index-paso1";
			}

			String existe = "NO";

			List<CreditoVo> listaCreditos = new ArrayList<CreditoVo>();
			List<CreditoEntiti> credito = creService
					.findAllByRut(Long.parseLong(form.getRutCliente().replace(".", "").split("-")[0]));

			int isIn = 0;

			for (CreditoEntiti cre : credito) {

				if (bitaService.findAllByAutorized(cre.getFolioCredito(),
						Integer.parseInt(String.valueOf(cre.getCuotaDiferir()))).size() == 0) {

					isIn++;

				}
			}

			if (isIn == 0) {

				existe = "SI";
			}

			String emptyTable = "notEmptyTable";

			if (credito.size() == 0) {

				emptyTable = "emptyTable";
			}

			for (CreditoEntiti creditoEntiti : credito) {

				CreditoVo vo = new CreditoVo();

				vo.setIdCredito(Long.parseLong(creditoEntiti.getFolioCredito()));
				vo.setCuotaDiferir(String.valueOf(creditoEntiti.getCuotaDiferir()));
				vo.setMontoCuota(String.valueOf(df.format(creditoEntiti.getMontoCuota())));
				vo.setFechaVencActual(sdf.format(creditoEntiti.getFechaVencActual()));
				vo.setFechaVencNuevo(sdf.format(creditoEntiti.getFechaVencNuevo()));

				listaCreditos.add(vo);
			}

			model.addAttribute("nombre", res.getNombreCompleto());
			model.addAttribute("rut", form.getRutCliente());
			model.addAttribute("existe", existe);
			model.addAttribute("emptyTable", emptyTable);
			
			logger.debug("email: " + form.getEmail());

			model.addAttribute("credito", listaCreditos);
			form.setNombreCliente(res.getNombreCompleto());
			request.getSession().setAttribute("datos", form);
			request.getSession().setAttribute("credito", credito);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 2 ", e);

			return "proceso_error";
		}

		return "index-paso2";
	}

	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.POST)
	public String step3(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		try {

			CreditoVo creditos = (CreditoVo) request.getSession().getAttribute("datos");

			logger.debug("email: " + form.getEmail());
			
			creditos.setEmail(form.getEmail());

			request.getSession().setAttribute("datos", creditos);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

			return "proceso_error";
		}

		return "proceso-exito";
	}

	@RequestMapping(value = { "/procesado.do" }, method = RequestMethod.POST)
	public String step4_1(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String email= request.getParameter("emailDescarga");
			request.getSession().setAttribute("emailDescarga", email);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en procesado ", e);

			return "proceso_error";

		}
		return "index-aprobado";
	}

	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(ModelMap model, HttpServletRequest request) {

		try {
			request.getSession().setAttribute("telefono", null);
			request.getSession().setAttribute("email", null);
			request.getSession().invalidate();
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "salir";
	}
}
