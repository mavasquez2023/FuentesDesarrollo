package cl.laaraucana.RentasPrevired.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.RentasPrevired.entities.ArchivoErrorEntity;
import cl.laaraucana.RentasPrevired.entities.PreviredArchivosEntity;
import cl.laaraucana.RentasPrevired.entities.RespuestaAfiliacionEntity;
import cl.laaraucana.RentasPrevired.entities.RespuestaCotizacionEntity;
import cl.laaraucana.RentasPrevired.utils.Util;

@Service
public class FTPProcesoServiceImpl implements FTPProcesoService {

	private static final Logger logger = Logger.getLogger(FTPProcesoServiceImpl.class);

	@Autowired
	private ErrorArchivoService errorArchivoService;

	@Autowired
	private RepuestaAfiliadoService respuestaAfiliadoService;

	@Autowired
	private FTPService ftpservice;

	@Autowired
	private PreviredProcesoService previredProcesosService;

	@Override
	public void processFTP() throws Exception {

		ResourceBundle prop = ResourceBundle.getBundle("etc/properties");

		String con = prop.getString("con");
		int port = Integer.parseInt(prop.getString("port"));
		String userFtp = prop.getString("userFtp");
		String passFtp = prop.getString("passFtp");

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

		String rutaUp = prop.getString("rutaPreviredFTP");
		String rutaLocal = prop.getString("rutaLocalFTP");

		ftpservice.connectToFTP(con, port, userFtp, passFtp);

		String file = "RESPCCAF" + format.format(new Date()) + "_22222_0001.csv";

		List<String> name = ftpservice.downloadFileFromFTP("previred/", rutaLocal);

		for (String nombre : name) {

			if (!previredProcesosService.existName(nombre)) {

				if (!Util.isError(rutaLocal + nombre)) {

					// ftpservice.uploadFileToFTP(new File(rutaLocal + file), rutaUp,
					// "CONSCCAF" + format.format(new Date()) + "_22222_0001.csv");

					PreviredArchivosEntity previredOk = new PreviredArchivosEntity();

					previredOk.setNOMBRE(nombre);
					previredOk.setFECHACREACION(new Date());
					previredOk.setSTATUS(1);

					previredProcesosService.saveProcess(previredOk);

					procesar(rutaLocal + nombre);

				} else {
					PreviredArchivosEntity previredError = new PreviredArchivosEntity();

					previredError.setNOMBRE(nombre);
					previredError.setFECHACREACION(new Date());
					previredError.setSTATUS(0);

					previredProcesosService.saveProcess(previredError);

					String nameError = ftpservice.downloadFileErrorFromFTP("previred/",
							rutaLocal + "ERROCCAF" + format.format(new Date()) + "_22222_0001.csv");

					procesarError(rutaLocal + "ERROCCAF" + format.format(new Date()) + "_22222_0001.csv", nameError);

				}
			}
		}
		ftpservice.disconnectFTP();

	}

	private void procesar(String ruta) {

		try {

			File file = new File(ruta);

			List<String> ln = FileUtils.readLines(file);
			List<String> inverse = new ArrayList<String>();

			String[] inv = ln.toString().split(",");

			for (int j = inv.length - 1; j >= 0; j--) {

				inverse.add(inv[j]);
			}

			RespuestaAfiliacionEntity resa;
			List<RespuestaCotizacionEntity> cotList = new ArrayList<RespuestaCotizacionEntity>();

			int i = 0;
			for (String str : inverse) {

				str = str.replace("[", "");
				str = str.replace("]", "");
				str = str.trim();
				String[] s = str.split(";");

				if (!s[0].contains("R0") && !s[0].contains("C0") && !s[0].isEmpty()) {

					if (s[0].contains("C1")) {

						RespuestaCotizacionEntity cot = new RespuestaCotizacionEntity();

						cot.setTIPOREG(s[0]);
						cot.setPERIODO(s[1]);
						cot.setTIPOCOT(s[2]);
						cot.setRENTIM(Integer.valueOf(s[3]));
						cot.setRUTEMP(Integer.valueOf(s[4]));
						cot.setDVEMP(s[5]);
						cot.setCODINST(Integer.valueOf(s[6]));
						cot.setTIPOPAG(s[7]);
						cot.setRUTTRAB(Integer.valueOf(s[8]));
						cot.setDVTRAB(s[9]);
						cot.setCODMOVPER(Integer.valueOf(s[10]));
						cot.setFECINMOV(s[11]);
						cot.setFECTERMOV(s[12]);
						cot.setMONCOTTRAB(Integer.valueOf(s[13]));
						cot.setMONCOTEMP(Integer.valueOf(s[14]));

						cotList.add(cot);

					} else {

						resa = new RespuestaAfiliacionEntity();

						resa.setTIPOREG(s[0]);
						resa.setCODREAFI(Integer.valueOf(s[1]));
						resa.setGLSRETAFI(s[2]);
						resa.setRUTAFI(Integer.valueOf(s[3]));
						resa.setDVAFI(s[4]);
						resa.setNOMBRES(s[5]);
						resa.setAPEPAT(s[6]);
						resa.setAPEMAT(s[7]);
						resa.setFECINSIS(s[8]);
						resa.setCODAFPVIG(Integer.valueOf(s[9]));
						resa.setFECINAFP(s[10]);
						resa.setSITUAAFI(s[11]);
						resa.setRespuestaCotizacionEntity(cotList);

						respuestaAfiliadoService.saveRespAfi(resa);

						cotList.clear();
					}

				}

				i++;
			}

		} catch (

		IOException e) {

			logger.error("Error el leer el fichero.", e);
		}

	}

	private void procesarError(String ruta, String nombre) {

		try {

			File file = new File(ruta);

			List<String> ln = FileUtils.readLines(file);

			if (!errorArchivoService.existName(nombre)) {

				for (String str : ln) {

					String[] field = str.split(";");

					ArchivoErrorEntity error = new ArchivoErrorEntity();

					error.setTIPOREG(field[0]);
					error.setLINERR(field[1]);
					error.setCODERR(Integer.valueOf(field[2]));
					error.setGLOSAERR(field[3]);
					error.setFECHERR(new Date());
					error.setNOMARCH(nombre);

					errorArchivoService.saveError(error);
				}
			}

		} catch (IOException e) {

			logger.error("Error el leer el fichero.");
		}

	}
}
