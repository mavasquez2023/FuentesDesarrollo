package cl.laaraucana.boletaelectronica.controller;

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
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.services.AceptaService;
import cl.laaraucana.boletaelectronica.services.BaseServices;
import cl.laaraucana.boletaelectronica.services.MigrateService;
import cl.laaraucana.boletaelectronica.services.OrigenBoletaService;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

@Controller
public class EmitirController {

	private static final Logger logger = Logger.getLogger(EmitirController.class);

	@Autowired
	private BaseServices baseService;

	@Autowired
	private AceptaService aceptaService;

	@Autowired
	private MigrateService migrateService;

	@Autowired
	private OrigenBoletaService origenService;

	@RequestMapping(value = { "/emitir.do" }, method = RequestMethod.GET)
	public String emitir(ModelMap model, @ModelAttribute("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<OrigenBoletaVo> origenListEmitidas = new ArrayList<OrigenBoletaVo>();
		String numeroBoleta = "";
		try {
			logger.info(">>Invocación manual emitir boleta, folio" + id);
			List<BoletaBase> base = baseService.getBoletaByFolio(Long.parseLong(id));
			boolean migrado=false;
			if (base.size() == 0 || (base.size()==1 && base.get(0).getNUMBOL()==0)) {
				logger.info(">>No se encuentra la boleta por folio, se crea");
				
				if(base.size() == 0) {
					logger.info("Llamando a migrate...");
					migrado= migrateService.migrate(id);
				}else{
					migrado=true;
				}
			} 
			if(migrado){
				logger.info("Se invoca webServices acepta, a enviar " + base.size() + " boleta");
				origenListEmitidas = aceptaService.wsAcepta(base);
				if(origenListEmitidas==null) {
					model.addAttribute("mensajeError",
							"Hubo un error en el Servicio Web ACEPTA");
					return "boleta";
				}

				if (origenListEmitidas.size() > 0) {
					for (OrigenBoletaVo origenBoletaVo : origenListEmitidas) {
						logger.info("Actualizando folio " + origenBoletaVo.getFOLIO() + " con número boleta: " + origenBoletaVo.getNUMBOL());
						base.get(0).setURLACEPTA(origenBoletaVo.getUrlDte());
						base.get(0).setESTADO(1);
						base.get(0).setNUMBOL(Long.parseLong(origenBoletaVo.getNUMBOL()));
						origenService.updateOrigen(Long.parseLong(origenBoletaVo.getNUMBOL()),
								Integer.parseInt(origenBoletaVo.getFOLIO() + ""));
						logger.info("Se actualizó tabla origen DTF630");
						baseService.updateBase(base.get(0));
						logger.info("Se actualizó tabla cabecera BOLECABE");
						numeroBoleta = origenBoletaVo.getNUMBOL();
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error al crear el reporte ", e);
			model.addAttribute("mensaje",
					"Hubo un error en el proceso " + e.getMessage());
			e.printStackTrace();
			return "error-process";
		}
		if(!numeroBoleta.isEmpty()) {
			request.getSession().setAttribute("numeroBoleta", numeroBoleta);
		}
		return "redirect:/emitidas.do";
	}

}
