/**
 * 
 */
package cl.laaraucana.imed.business;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.imed.clientews.ClienteImed;
import cl.laaraucana.imed.clientews.model.ConstantesRespuestasWS;
import cl.laaraucana.imed.clientews.vo.SalidaImedVO;
import cl.laaraucana.imed.config.Configuraciones;
import cl.laaraucana.imed.dao.ConsultaServicesDAO;
import cl.laaraucana.imed.dao.vo.RegistroAltaBajaVO;
import cl.laaraucana.imed.services.FormatoCorreo;
import cl.laaraucana.imed.services.MailService;
import cl.laaraucana.imed.services.MailServiceImpl;

/**
 * @author J-Factory
 *
 */
public class ServiciosImed {
	protected Logger logger = Logger.getLogger(this.getClass());
	private int num_alta_ok=0;
	private int num_alta_error=0;
	private int num_baja_ok=0;
	private int num_baja_error=0;
	private MailService mailService;
	
	public void serviciosImed(String tipo){
		if(tipo==null || tipo.equals("")){
			serviciosAltaImed();
			serviciosBajaImed();
		}else if(tipo.equalsIgnoreCase("altas") || tipo.equalsIgnoreCase("alta")){
			serviciosAltaImed();
		}else if(tipo.equalsIgnoreCase("bajas") || tipo.equalsIgnoreCase("baja")){
			serviciosBajaImed();
		}
		try {
			String correo_to= Configuraciones.getConfig("imed.correo.to.usuario");
			String asunto= Configuraciones.getConfig("imed.asunto.correo");
			logger.info("Enviando mail término proceso a " + correo_to);
			MailService mailService= new MailServiceImpl();
			mailService.sendEmail(correo_to, asunto,
					FormatoCorreo.getMailbody(num_alta_ok, num_alta_error, num_baja_ok, num_baja_error));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("Error, mensaje" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String serviciosAltaImed(){
		logger.info("Ejecuntando altas Imed");
		//Consulta Altas Imed
		String mensaje="";
		try {
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			List<RegistroAltaBajaVO> list_altas= consultaDAO.consultaAltas();
			logger.info("Total registros de altas a procesar:"  + list_altas.size());
			if(list_altas!=null){				
				ClienteImed clienteImed= new ClienteImed();
				int procesados=0;
				for (Iterator iterator = list_altas.iterator(); iterator.hasNext();) {
					RegistroAltaBajaVO entradaImedVO = (RegistroAltaBajaVO) iterator
							.next();
					SalidaImedVO salida=(SalidaImedVO)clienteImed.callAlta(entradaImedVO);
					if(salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS){
						if(salida.getEstado()==0){
							num_alta_ok++;
						}else if(salida.getEstado()==1){
							num_alta_error++;
							consultaDAO.insertErroneo(entradaImedVO);
						}
					}else{
						logger.warn("Servicio Imed Error, Rut Titular: " + entradaImedVO.getRutTitular() + ", Rut Beneficiario:" + entradaImedVO.getRutBeneficiario());
						num_alta_error++;
						//Si servicio Imed no responde se notifica error
						mensaje="Error servicio Imed";
						consultaDAO.insertErroneo(entradaImedVO);
					}
					UtilBitacora.addRegistro(entradaImedVO, salida);
					procesados++;
					if(procesados % 100 == 0 ){
						logger.info("Enviando registro número " + procesados);
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	public String serviciosBajaImed(){
		logger.info("Ejecuntando bajas Imed");
		//Consulta Altas Imed
		String mensaje="";
		try {
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			List<RegistroAltaBajaVO> list_bajas= consultaDAO.consultaBajas();
			logger.info("Total registros de bajas a procesar:"  + list_bajas.size());
			if(list_bajas!=null){
				ClienteImed clienteImed= new ClienteImed();
				int procesados=0;
				for (Iterator iterator = list_bajas.iterator(); iterator.hasNext();) {
					RegistroAltaBajaVO entradaImedVO = (RegistroAltaBajaVO) iterator
							.next();
					SalidaImedVO salida=(SalidaImedVO)clienteImed.callBaja(entradaImedVO);
					if(salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS){
						if(salida.getEstado()==0){
							num_baja_ok++;
						}else if(salida.getEstado()==1){
							num_baja_error++;
							consultaDAO.insertErroneo(entradaImedVO);
						}

					}else{
						logger.warn("Servicio Imed Error, Rut Titular: " + entradaImedVO.getRutTitular() + ", Rut Beneficiario:" + entradaImedVO.getRutBeneficiario());
						num_baja_error++;
						//Si servicio Imed no responde se notifica error
						mensaje="Error servicio Imed";
						consultaDAO.insertErroneo(entradaImedVO);
					}
					UtilBitacora.addRegistro(entradaImedVO, salida);
					procesados++;
					if(procesados % 100 == 0 ){
						logger.info("Enviando registro número " + procesados);
					}
					
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
}
