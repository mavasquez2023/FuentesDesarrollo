package com.microsystem.lme.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import wwwLmeGovClLme.CTEstado;

import com.microsystem.lme.helper.EnviaMailUtil;
import com.microsystem.lme.helper.Helper;
import com.microsystem.lme.helper.ManipuladorArchivo;
import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.job.ConsumoOperadorService;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.EndPointUtil;
import com.microsystem.lme.util.LabelValueVO;
import com.microsystem.lme.util.Util;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import conector.vo.SalidaLMEDetLcc;

public class UploadAction extends Action {

	private static MessageResources messages;
	private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();

	private Logger log = Logger.getLogger(this.getClass());

	final String TIPO_INSTITUCION = "C";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//		HttpSession session = request.getSession();

		UploadForm formulario = (UploadForm) form;

		messages = getResources(request);

		FormFile theFile = formulario.getTheFile();
		String nombreFileOrig = theFile.getFileName();
		String extencionFile = nombreFileOrig.substring(nombreFileOrig.lastIndexOf(".")).toLowerCase();

		if (!extencionFile.equals(".txt")) {
			request.setAttribute("respuesta", "La extención del archivo no es válida.");
			request.setAttribute("codError", "1");
			return mapping.findForward("salida");
		}

		//drubilar 08-01-2013
		int opcion = formulario.getCbOpcion();

		String contentType = theFile.getContentType();

		try {

			byte[] fileData = theFile.getFileData();

			File file = new File("");
			StringBuffer rutaFile = new StringBuffer();
			StringBuffer nameFile = new StringBuffer();
			StringBuffer fileResultado = new StringBuffer();

			//Ruta para el archivo subido en el servidor
			rutaFile.append(file.getAbsolutePath());
			rutaFile.append(messages.getMessage("DIR_UPLFILE"));

			fileResultado.append(file.getAbsolutePath());
			fileResultado.append(messages.getMessage("DIR_OUTFILE"));

			String filePath = rutaFile.toString();

			//Nombre del archivo
			String fecha = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date date = new Date();
			fecha = dateFormat.format(date);

			nameFile.append(fecha);
			fileResultado.append(fecha);
			nameFile.append(messages.getMessage("EXT_texto"));
			fileResultado.append(messages.getMessage("EXT_csv"));

			String fileNameOutput = nameFile.toString();
			String fileResultadoStr = fileResultado.toString();

			ManipuladorArchivo subir = new ManipuladorArchivo();

			subir.upload(request, response, filePath, fileNameOutput, contentType, fileNameOutput, fileData);

			if (opcion == 1) {
				this.execLMEDetLcc(request, filePath + fileNameOutput, fileResultadoStr);
			} else {
				this.consumoMasivo(request, filePath + fileNameOutput);
			}

		} catch (IOException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (ServletException f) {
			//f.printStackTrace();
			log.error(f.getClass() + "; " + f.getMessage());
		}

		request.setAttribute("respuesta", "Consulta Masiva ralizada con éxito, se ha generado un correo electrónico con el resultado.");
		request.setAttribute("codError", "0");
		return mapping.findForward("salida");
	}

	//drubilar 08-01-2013

	private void consumoMasivo(HttpServletRequest request, String fileResultado) throws IOException {
		//String res = null;		
		try {

			FileReader fr = null;
			BufferedReader br = null;
			System.out.println("Comenzo Consumo...");
			try {
				// Apertura del fichero y creacion de BufferedReader para poder
				// hacer una lectura comoda (disponer del metodo readLine()).
				fr = new FileReader(fileResultado);
				br = new BufferedReader(fr);
				//String codOpe = URLDecoder.decode(request.getParameter("codOpe"), "UTF-8");		
				String lic = "";
				String codOpe = "";
				String linea;
				String[] arreglo;
				while ((linea = br.readLine()) != null) {
					arreglo = new String[2];
					arreglo = linea.split(",");
					System.out.print("Licencia de archivo = " + arreglo[1]);
					lic = arreglo[1].toString().trim();
					codOpe = arreglo[0].toString().trim();
					BigInteger numLic = new BigInteger(lic);
					IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
					ilfeOpeVO.setCodOpe(codOpe);
					ilfeOpeVO.setStsOpe(Integer.valueOf("1"));

					List l = svc_a.getIlfeOpe(ilfeOpeVO);
					ilfeOpeVO = (IlfeOpeVO) l.get(0);
					ilfeOpeVO.setNumLicencia(lic);
					ilfeOpeVO.setDigLicencia(Util.dv(numLic));

					ConsumoOperadorService consumoOperadorService = new ConsumoOperadorService(getResources(request));
					consumoOperadorService.setDateLcc(new Date());
					consumoOperadorService.startProcess("ConsumirDetallesLME");
					consumoOperadorService.consumirDetalleLME(ilfeOpeVO, "");
					consumoOperadorService.actualizarLmeCCAF();

					//res = consumoOperadorService.getLogLcc().toString();
				}
			} catch (Exception e) {
				//e.printStackTrace();
				log.error(e.getClass() + "; " + e.getMessage());
			} finally {

				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					//e2.printStackTrace();
					log.error(e2.getClass() + "; " + e2.getMessage());
				}
			}

		} catch (SvcException e) {
			//res = e.getMessage();
			//log.error(e.getClass() + "; "+ e.getMessage());
		}
		
		InitConexion_LME.closeConexion_LME();

	}

	private void execLMEDetLcc(HttpServletRequest request, String fileEntrada, String fileResultado) throws IOException {
		String res = null;
		/*
		 try 
		 {*/
		FileReader fr = null;
		BufferedReader br = null;

		try {

			File file = new File(fileResultado);
			file.createNewFile();
			fr = new FileReader(fileEntrada);
			br = new BufferedReader(fr);

			String lic = "";
			String linea;
			String[] arreglo;

			// Escribimos cabecera del archivo
			FileWriter fw = null;
			PrintWriter pw = null;

			try {

				fw = new FileWriter(fileResultado, true);
				pw = new PrintWriter(fw);

				//pw.println("Cod. Operador;Num. Licencia;Ultimo Estado;Nombre;RUT");
				pw.println("Cod. Operador;Num. Licencia;Ultimo Estado");
			} catch (IOException e) {
			} finally {
				try {

					if (null != fw)
						fw.close();
				} catch (Exception e2) {
					//e2.printStackTrace();
					log.error(e2.getClass() + "; " + e2.getMessage());
				}
			}

			//Se consutan las licencias y se llena el archivo de salida
			while ((linea = br.readLine()) != null) {
				arreglo = new String[2];
				arreglo = linea.split(",");

				String codOpe = arreglo[0].toString();
				lic = arreglo[1].toString();

				System.out.println("CODIGO OPERADOR: " + codOpe + " - " + " Licencia: " + lic);

				BigInteger numLic = new BigInteger(lic);
				/*
				 IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
				 ilfeOpeVO.setCodOpe(codOpe);
				 ilfeOpeVO.setStsOpe(Integer.valueOf("1"));
				 
				 List l = svc.getIlfeOpe(ilfeOpeVO);
				 
				 ilfeOpeVO = (IlfeOpeVO)l.get(0);			
				 ilfeOpeVO.setNumLicencia(lic);
				 ilfeOpeVO.setDigLicencia(Util.dv(numLic));
				 */
				//this.consultarDetalleMasivo(ilfeOpeVO, fileResultado);
				//codigo modificado
				if (codOpe.equals("2")) {
					this.consultarDetalleMasivoAux(Util.dv(numLic), numLic, "2", "3", "http://192.168.1.111:8080/LME/IMDetLcc", "IMDetLcc", "petrohue", "10105", fileResultado);
				} else {
					this.consultarDetalleMasivoAux(Util.dv(numLic), numLic, "4", "4", "http://192.168.1.111:8080/LME/MPDetLcc", "MPDetLcc", "fonasa", "10105", fileResultado);
				}

			}

			// Se envía por correo el archivo resultante
			String mailTokens = messages.getMessage("MAIL_to");
			EnviaMailUtil.EnviarMail(mailTokens, "Consumo masivo LME", "Archivo", fileResultado, fileResultado.substring(fileResultado.lastIndexOf("\\") + 1, fileResultado.length()), 0);

		} catch (Exception e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		} finally {
			// Se cierra el archivo
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				//e2.printStackTrace();
				log.error(e2.getClass() + "; " + e2.getMessage());
			}
		}

		/*}  catch (SvcException e) {
		 res = e.getMessage();
		 }
		 */
	}

	public LabelValueVO consultarDetalleMasivo(IlfeOpeVO lic, String fileResultado) throws IOException {
		String flg = "-1";
		String error = "OK";

		UrlBorderVO urlVO = new UrlBorderVO();
		urlVO.setCodOpe(lic.getCodOpe());
		urlVO.setNombreServicio("DETALLE");
		String nombreHash = "DETALLE";

		try {
			/*String url = svc.getUrlVordel(urlVO);
			 
			 if(null==url)
			 {
			 //logger.logLcc("IGNORANDO SERVICIO");
			 return new LabelValueVO("SERVICIO IGNORANDO URL_VORDEL=null","-1");
			 }
			 String urlOpe = url;*/

			/*********************************************/

			String url1 = null;
			Boolean error1 = EndPointUtil.getInstance().getEstadoError(lic.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				System.out.println("No tiene error en la prioridad 1");
				url1 = EndPointUtil.getInstance().getEndPoint(lic.getCodOpe(), nombreHash, "1");
			}

			String url2 = null;
			Boolean error2 = EndPointUtil.getInstance().getEstadoError(lic.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				System.out.println("No tiene error en la prioridad 2");
				url2 = EndPointUtil.getInstance().getEndPoint(lic.getCodOpe(), nombreHash, "2");
			}

			/************************************************/

			System.out.println("codOPE: " + lic.getCodOpe().trim() + "TIPO_INSTITUCION: " + TIPO_INSTITUCION + "codCodCcaf: " + lic.getCodCcaf().trim() + "pwdCcaf: " + lic.getPwdCcaf().trim()
					+ "urlOPE: " + url1 + "urlOPE2: " + url2);

			//ServiciosMultiOperador servicio = new ServiciosMultiOperador(lic.getCodOpe().trim(), TIPO_INSTITUCION, lic.getCodCcaf().trim(), lic.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(lic.getCodOpe().trim(), TIPO_INSTITUCION, lic.getCodCcaf().trim(), lic.getPwdCcaf().trim(), url1, url2);

			//RespuestaDetalleLicencia respuesta = servicio.consultaDetalleLicencia(Helper.toBigInteger(lic.getNumLicencia()), lic.getDigLicencia());
			SalidaLMEDetLcc respuesta = servicio.consultaDetalleLicencia2(Helper.toBigInteger(lic.getNumLicencia()), lic.getDigLicencia());

			if (null != respuesta && respuesta.getRespuesta() != null && null != respuesta.getRespuesta().getZonaA() && null != respuesta.getRespuesta().getZonaA().getZONAA1()
					&& null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador() && null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getRut()) {

				CTEstado[] listaEstado = respuesta.getRespuesta().getZona0().getZONA01().getEstadoArray();

				//Busca ultimo estado
				int ultimoEstado = 0;
				Date fechaUtimoEstado = null;

				for (int i = 0; i < listaEstado.length; i++) {
					if (fechaUtimoEstado == null) {
						ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
						fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
					}
					if (fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()) < 0) {
						// actualizo estado
						ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
						fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
					}
				}

				FileWriter fw = null;
				PrintWriter pw = null;

				String nombre = Util.decodeUTF8(respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getNombres());

				try {

					fw = new FileWriter(fileResultado, true);
					pw = new PrintWriter(fw);

					pw.println(lic.getCodOpe() + ";" + lic.getNumLicencia() + ";" + ultimoEstado + ";" + Helper.dataTruncation(nombre, 30) + ";"
							+ respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getRut());

				} catch (IOException e) {
				} finally {
					try {

						if (null != fw)
							fw.close();
					} catch (Exception e2) {
						//e2.printStackTrace();
						log.error(e2.getClass() + "; " + e2.getMessage());
					}
				}

			}
			/*else{
			 System.out.println("no hubo respuesta del webservice para la licencia: "+lic.getNumLicencia());			
			 }*/
		} catch (ConfiguracionException e) {
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		} catch (ErrorInvocacionOperadorException e) {
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		} catch (ErrorRespuestaOperadorException e) {
			flg = "1";
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		} catch (Exception e) {
			error = Helper.dataTruncation(e.getMessage(), 50);
		}
		
		InitConexion_LME.closeConexion_LME();
		return new LabelValueVO(error, flg);
	}

	public LabelValueVO consultarDetalleMasivoAux(String dv, BigInteger numlicencia, String ideope, String codeOpe, String urlWS, String nombreServicio, String pwdCcaf, String codCcaf,
			String fileResultado) throws IOException {
		String flg = "-1";
		String error = "OK";
		/*
		 UrlBorderVO urlVO = new UrlBorderVO();
		 urlVO.setCodOpe(lic.getCodOpe());
		 urlVO.setCodOpe("5");
		 urlVO.setNombreServicio("DETALLE");
		 */
		try {
			/*
			 String url = svc.getUrlVordel(urlVO);
			 
			 if(null==url)
			 {
			 //logger.logLcc("IGNORANDO SERVICIO");
			 return new LabelValueVO("SERVICIO IGNORANDO URL_VORDEL=null","-1");
			 }
			 String urlOpe = url;
			 */

			ServiciosMultiOperador servicio = new ServiciosMultiOperador(codeOpe, TIPO_INSTITUCION, codCcaf, pwdCcaf, urlWS, urlWS);

			//RespuestaDetalleLicencia respuesta = servicio.consultaDetalleLicencia(numlicencia, dv);
			SalidaLMEDetLcc respuesta = servicio.consultaDetalleLicencia2(numlicencia, dv);

			if (null != respuesta && respuesta.getRespuesta() != null && null != respuesta.getRespuesta().getZonaA() && null != respuesta.getRespuesta().getZonaA().getZONAA1()
					&& null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador() && null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getRut()) {

				CTEstado[] listaEstado = respuesta.getRespuesta().getZona0().getZONA01().getEstadoArray();

				//Busca ultimo estado
				int ultimoEstado = 0;
				Date fechaUtimoEstado = null;

				for (int i = 0; i < listaEstado.length; i++) {
					if (fechaUtimoEstado == null) {
						ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
						fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
					}
					if (fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()) < 0) {
						// actualizo estado
						ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
						fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
					}
				}

				FileWriter fw = null;
				PrintWriter pw = null;

				//String nombre=Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getNombres()); 

				try {

					fw = new FileWriter(fileResultado, true);
					pw = new PrintWriter(fw);

					pw.println(codeOpe + ";" + numlicencia + ";" + ultimoEstado);

				} catch (IOException e) {
				} finally {
					try {

						if (null != fw)
							fw.close();
					} catch (Exception e2) {
						//e2.printStackTrace();
						log.error(e2.getClass() + "; " + e2.getMessage());
					}
				}

			}
			/*else{
			 System.out.println("no hubo respuesta del webservice para la licencia: "+lic.getNumLicencia());			
			 }*/
		} catch (ConfiguracionException e) {
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		} catch (ErrorInvocacionOperadorException e) {
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		} catch (ErrorRespuestaOperadorException e) {
			flg = "1";
			//logger.logErrorLcc(e);
			error = Helper.dataTruncation(e.getMessage(), 50);
		}
		return new LabelValueVO(error, flg);
	}

}
