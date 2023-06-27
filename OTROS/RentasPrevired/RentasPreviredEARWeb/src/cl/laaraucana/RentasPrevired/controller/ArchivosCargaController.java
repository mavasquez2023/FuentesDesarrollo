package cl.laaraucana.RentasPrevired.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.RentasPrevired.entities.AfiliadoConsultaEntity;
import cl.laaraucana.RentasPrevired.entities.ArchivoEntradaEntity;
import cl.laaraucana.RentasPrevired.services.AfiliadoConsultaService;
import cl.laaraucana.RentasPrevired.services.ArchivoService;
import cl.laaraucana.RentasPrevired.utils.Util;

@Controller
public class ArchivosCargaController {

	private static final Logger logger = Logger.getLogger(ArchivosCargaController.class);

	@Autowired
	private ArchivoService archivoservice;

	@Autowired
	private AfiliadoConsultaService afiliadosConsultaService;

	 


	@RequestMapping(value = { "/loadFile", "/ClaveEmpresasWeb/loadFile" }, method = RequestMethod.POST)
	public String guardaFichero(@ModelAttribute AfiliadoConsultaEntity afiliados, Model model,
			HttpServletRequest request) {

		try {
			
			ResourceBundle prop = ResourceBundle.getBundle("etc/properties");

			String nombre = afiliados.getFichero().getOriginalFilename();
			String ext = Util.getTipe(nombre);

			if (!ext.toLowerCase().equals("csv")) {

				model.addAttribute("archivo", "El archivo debe ser CSV");

				return "previred";

			}

			List<String> errores = new ArrayList<String>();

			File fichero = Util.grabarFicheroTemporal(afiliados.getFichero(), prop.getString("rutaLocalFTP"));
			List<AfiliadoConsultaEntity> listaAfiliados = new ArrayList<AfiliadoConsultaEntity>();
			try {

				@SuppressWarnings("deprecation")
				List<String> lines = FileUtils.readLines(fichero);

				for (String string : lines) {

					String[] str = string.split(";");

					AfiliadoConsultaEntity afiliado = new AfiliadoConsultaEntity();

					
					afiliado.setTIPOREGISTRO(str[0]);
					afiliado.setRUTAFILIADO(Integer.parseInt(str[1]));
					afiliado.setDVAFILIADO(str[2]);
					afiliado.setNPERIODOS(Integer.parseInt(str[3]));
					afiliado.setFECHAEMISIONLICENCIA(str[4]);
					afiliado.setFECHAINICIOLICENCIA(str[5]);
					afiliado.setFOLIOLICENCIA(Long.parseLong(str[6]));
					afiliado.setTIPOLICENCIA(Integer.parseInt(str[7]));
					afiliado.setTIPOCONSULTA(Integer.parseInt(str[8]));
					afiliado.setUSUARIOCREADOR("ALEXIS");
					afiliado.setFECHACREACION(new Date());

					listaAfiliados.add(afiliado);

				}
			} catch (Exception ex) {

				logger.error("Errores en el proceso carga de archivos", ex);

				errores.add("Error en el formato del documento");
			}
			// procesa archivo
			for (AfiliadoConsultaEntity af : listaAfiliados) {

				if (!Util.validarRut(af.getRUTAFILIADO() + "-" + af.getDVAFILIADO())) {

					errores.add("error formato rut.");

				}

			}

			if (!errores.isEmpty()) {

				model.addAttribute("errores", errores);

				return "previred";

			} else {

				for (AfiliadoConsultaEntity afiliadoConsulta : listaAfiliados) {

					afiliadosConsultaService.saveAfiliadoConsulta(afiliadoConsulta);
				}

				ArchivoEntradaEntity file = new ArchivoEntradaEntity();

				file.setFECHACARGA(new Date());
				file.setNOMBRE(nombre);
				file.setSTATUS(1);

				archivoservice.saveFile(file);
			}

			// 2720 1500 File file =
			// Utils.grabarFicheroTemporal(formularioBean.getFichero());

			// byte[] f2 = claveEmpresasService.readFile(30).getArchivoAdjunto();

			// FileUtils.writeByteArrayToFile(new File("C:/upload/archivo.pdf"), f2);

		} catch (Exception e) {

			logger.error("Error al cargar el archivo", e);
		}

		return "previred";
	}

}
