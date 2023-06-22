/**
 * 
 */
package cl.laaraucana.cuotasdup.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.dao.VO.MapeoArchivoVO;
import cl.laaraucana.cuotasdup.dao.VO.ResultadoCuotasVO;
import cl.laaraucana.cuotasdup.main.dao.ConsultaTrabajadoresDAO;
import cl.laaraucana.cuotasdup.utils.FormatoMail;
import cl.laaraucana.cuotasdup.utils.ParamConfig;
import cl.laaraucana.cuotasdup.utils.MapeoArchivo;
import cl.laaraucana.cuotasdup.utils.UtilEmail;
import cl.laaraucana.cuotasdup.utils.ftp.ArchivoVO;
import cl.laaraucana.cuotasdup.utils.ftp.FtpUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class GeneracionCartas {
	private final static int EXITO= 0;
	private final static int ERROR_FORMATO= 1;
	private final static int ERROR_CONCEPTO= 2;
	private final static int ERROR_ARCHIVO= 3;
	private final static int ERROR_DB2= 4;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void generarCartas(){
		
		
		try {
			//Se recupera Archivo por FTP
			logger.info("Conectándose a FTP, server: " + ParamConfig.RES_CONFIG.getString("ftp.servidor"));
			FtpUtil ftp= new FtpUtil();
			String periodo= Utils.getFechaPeriodo();
			String nombreArchivo=ParamConfig.RES_CONFIG.getString("ftp.archivo");
			nombreArchivo= nombreArchivo.replaceAll("AAAAMM", periodo);
			String pathSalida=ParamConfig.RES_CONFIG.getString("ftp.download.ruta");
			boolean down= ftp.bajarArchivoViaFtp(nombreArchivo, pathSalida);
			
			//Se rescata mapeo de conceptos
			logger.info("Se rescata mapeo de conceptos desde RC20SAP");
			ConsultaTrabajadoresDAO dao= new ConsultaTrabajadoresDAO();
			List<MapeoArchivoVO> lista_mapeos= dao.consultaMapeo();
			
			//Se arma un mapa de los conceptos
			HashMap<String, MapeoArchivoVO> mapeos= new HashMap<String, MapeoArchivoVO>();
			for (Iterator iterator = lista_mapeos.iterator(); iterator.hasNext();) {
				MapeoArchivoVO mapeoArchivoVO = (MapeoArchivoVO) iterator.next();
				mapeos.put(mapeoArchivoVO.getCampo().trim(), mapeoArchivoVO);
			}
			String mensaje="";
			if(mapeos!= null && down){
				//se lee archivo
				logger.info("Se lee archivo para mapear columnas");
				ResultadoCuotasVO resultado= MapeoArchivo.leerArchivo(pathSalida+nombreArchivo, mapeos);
				int count=0;
				logger.info("Cantidad de cuotas a insertar:" + resultado.getListaCuotas().size());
				if(resultado.getCodigoResultado()==EXITO){
					//se inserta tablas con todas las cuotas
					logger.info("Se inserta cuotas");
					count= dao.insertCuotas(resultado.getListaCuotas());
					//se generan PDf y se envían por correo
					if(count>0){
						String subject= ParamConfig.RES_CONFIG.getString("mail.asunto.trabajador");
						int count_PDF=0;
						for (Iterator iterator = resultado.getListaCuotas().iterator(); iterator
								.hasNext();) {
							CuotaVO cuotaVO = (CuotaVO) iterator.next();

							if(!cuotaVO.getCorreo().equals("")){
								String filePath= ParamConfig.RES_CONFIG.getString("cronta.cuotasdup.pdf")+"PDF_" + cuotaVO.getRutAfiliado() + "-" + cuotaVO.getDvRutAfiliado()+ ".PDF";
								logger.info("Se Genera PDF " + cuotaVO.getRutAfiliado());
								boolean pdf= GenerarPDF.generaPDF(cuotaVO, filePath);
								if(pdf){
									List<String> correo_afiliado= new ArrayList<String>();
									correo_afiliado.add(cuotaVO.getCorreo());
									String body= FormatoMail.obtenerTextoMail_Trabajador(cuotaVO.getNombreAfiliado(), cuotaVO.getRutAfiliado()+ "-" + cuotaVO.getDvRutAfiliado(), Utils.pasaFechaASaWEB(cuotaVO.getFechaCreacion()));
									String filename= "Devolucion_" + cuotaVO.getRutAfiliado() + ".pdf";
									logger.info("Se envía correo a :" + cuotaVO.getCorreo());
									UtilEmail.sendEmail(correo_afiliado, null, subject, body, filePath);
									count_PDF++;
								}
							}
						}
						mensaje= "Se ha completado proceso<BR><BR> Total registros SAP: " + resultado.getListaCuotas().size() + "<BR> Total insert DB2: " + count + "<BR> Total PDF enviados: " + count_PDF;
						logger.info("Se ha completado proceso\n\n Total registros SAP:" + resultado.getListaCuotas().size() + "\n Total insert DB2:" + count + "\n Total PDF enviados:" + count_PDF);
					}else{
						resultado.setCodigoResultado(ERROR_DB2);
					}
				}
				String body="";
				if(resultado.getCodigoResultado()!= EXITO){
					//Parámetros Email  a Ejecutivo informando error

					String tipo_error="";
					if(resultado.getCodigoResultado()==ERROR_FORMATO){
						tipo_error="Formato";
					}else if(resultado.getCodigoResultado()==ERROR_CONCEPTO){
						tipo_error="Concepto";
					}else if(resultado.getCodigoResultado()==ERROR_ARCHIVO){
						tipo_error="Archivo";
					}else if(resultado.getCodigoResultado()==ERROR_DB2){
						tipo_error="DB2";
					}
					mensaje= "Error de " + tipo_error + " en linea: " + resultado.getLineaError() + ", columna:" + resultado.getColumnaError();
					body= FormatoMail.obtenerTextoMail_Ejecutivo_Error(mensaje);
					logger.info("Se envía error a ejecutivo por error encontrado");
				}else{
					//Parámetros email ejecutivo por exito
					body= FormatoMail.obtenerTextoMail_Ejecutivo_Exito(mensaje);
					logger.info("Se envía informe a ejecutivo por exito proceso");
				}
				//Se envía correo ejecutivo
				String correo_ejecutivo= ParamConfig.RES_CONFIG.getString("mail.ejecutivo");
				logger.info("mail ejecutivo:" + correo_ejecutivo);
				String[] lista_eje= correo_ejecutivo.split(";");
				List<String> lista_correos= new ArrayList<String>();
				for (int i = 0; i < lista_eje.length; i++) {
					lista_correos.add(lista_eje[i]);
				}
				String subject= ParamConfig.RES_CONFIG.getString("mail.asunto.trabajador");
				UtilEmail.sendEmail(lista_correos, null, subject, body, null);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
