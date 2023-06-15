package cl.laaraucana.simulacion.utils;

import java.util.Date;

import org.apache.log4j.Logger;
import cl.laaraucana.simulacion.VO.ParametrosCotizacionVO;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.ibatis.dao.RegistrarSolicitudDAO;
import cl.laaraucana.simulacion.ibatis.vo.SolicitudCotizacionVO;
import cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO.ConsultaCreditoPreAprobSalidaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.CreaCotizacionClient;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionEntradaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionSalidaVO;

public class CotizacionUtil {
	protected static Logger logger = Logger.getLogger(CotizacionUtil.class);
	
	public static CreaCotizacionSalidaVO enviarCotizacionService(ParametrosCotizacionVO parametrosCotiza, ResultadoSim resultado, ConsultaCreditoPreAprobSalidaVO resultadoPreAprobado) throws Exception{
		
		CreaCotizacionEntradaVO entrada = new CreaCotizacionEntradaVO();
		
		entrada.setTipoEjecucion(ConstantesFormalizar.TIPO_EJECUCION_CREAR_COTIZACION);
		entrada.setTipoAfiliado(resultado.getTipoAfiliado());
		entrada.setRut(resultado.getRut());
		if(resultadoPreAprobado == null){
			entrada.setRutEmpleador("N");
			entrada.setSapEmpleador("N");
		}else{
			entrada.setRutEmpleador(resultadoPreAprobado.getRutEmpleador());
			entrada.setSapEmpleador(resultadoPreAprobado.getBpEmpleador());
		}
		entrada.setMontoSolicitado(resultado.getMontoSolicitado());
		entrada.setCuotas(resultado.getCuotas());
		entrada.setProducto(resultado.getTipoProducto()); // se debe cambiar dependiendo
		entrada.setMoneda(ConstantesFormalizar.TIPO_MONEDA_PESOS);
		entrada.setOficinaVenta(resultado.getOficina());
		entrada.setCanalVenta(ConstantesFormalizar.CANAL_VENTA);
		entrada.setSeguroCesantia(resultado.getSegCesantia()); //DUMMY validar si se mantiene siempre igual
		entrada.setSectorVenta(ConstantesFormalizar.SECTOR_VENTA);
		
		/*campos para contacto*/
		entrada.setCalle(parametrosCotiza.getCalle());
		entrada.setNumero(parametrosCotiza.getCalleNro());
		entrada.setNroDpto(parametrosCotiza.getNroDpto());
		entrada.setComuna(parametrosCotiza.getComuna());
		entrada.setRegion(parametrosCotiza.getRegion());
		entrada.setNroTelFijo(parametrosCotiza.getExtension() + parametrosCotiza.getFono());
		entrada.setNroTelMovil(parametrosCotiza.getPreMovil()+ parametrosCotiza.getCelular());
		entrada.setEmail(parametrosCotiza.getEmail());
		System.out.println("el contacto es "+parametrosCotiza.isContacto());
		if(parametrosCotiza.isContacto()){
			entrada.setContacto("X"); // X o ' ' DUMMY validar si se incluye el contacto
		}else{
			entrada.setContacto(""); // X o ' ' DUMMY validar si se incluye el contacto	
		}
					
		
		CreaCotizacionClient cliente = new CreaCotizacionClient();
		
		CreaCotizacionSalidaVO salidaVO = cliente.call(entrada);
		logger.debug("la cotización "+salidaVO.getNroCotizacion());
		
		return salidaVO;
	}
	
	
	public static void enviarCotizacionAS400(ParametrosCotizacionVO parametrosCotiza, ResultadoSim resultado) throws Exception{
		SolicitudCotizacionVO solicitud = new SolicitudCotizacionVO();
		solicitud.setTipoForm(8);
		solicitud.setRut(resultado.getRut().replace("-", "").replace(".", ""));
		solicitud.setNombre(resultado.getNombreAfiliado());
		solicitud.setFono(parametrosCotiza.getExtension() + parametrosCotiza.getFono());
		solicitud.setCelular(parametrosCotiza.getCelular());
		solicitud.setEmail(parametrosCotiza.getEmail());
		
		String direccion = parametrosCotiza.getCalle() + " #" + parametrosCotiza.getCalleNro();
		if(!parametrosCotiza.getNroDpto().isEmpty()) direccion += ", Dpto. " + parametrosCotiza.getNroDpto();
		
		solicitud.setDireccion(direccion);
		solicitud.setComuna(parametrosCotiza.getComuna());
		solicitud.setRegion(parametrosCotiza.getRegion());
		solicitud.setFechaCreacion(new Date());
		solicitud.setMontoSoli(Long.parseLong(resultado.getMontoSolicitado()));
		solicitud.setNumCuotas(Integer.parseInt(resultado.getCuotas()));
		RegistrarSolicitudDAO.registrarSolicitudCotizacion(solicitud);
		
	//Enviar formulario a servidor de correo
		String body = textoCorreo(parametrosCotiza, resultado);
		UtilEmail.sendEmail(Configuraciones.getConfig("solicitud.destino"), 
							Configuraciones.getConfig("solicitud.asunto"),
							body);		
	}
	
	
	private static String textoCorreo(ParametrosCotizacionVO parametrosCotiza, ResultadoSim resultado){
		String salida = "";
		String direccion = parametrosCotiza.getCalle() + " #" + parametrosCotiza.getCalleNro();
		if(!parametrosCotiza.getNroDpto().isEmpty()) direccion += ", Dpto. " + parametrosCotiza.getNroDpto();
		
		salida = "<meta charset='utf-8' /><style>" +
				"th{background-color: #ADCCFF;text-align: left;width: 250px;} " +
				"td{width: 300px;height: 20px;background-color: #EFF5FA;} " +
				".cabecera{text-align: center;font-weight: bold;background-color: silver;} " +
				"table{" +
				"font-family: arial,helvetica,sans-serif; " +
				"font-size: 13px;border: 0.5px;border-style: solid;border-color: #CCCDC7;padding: 0px 2px 0px 2px;}" +
				"</style> " +
				"<br><br>" +
				"<table cellpadding='5px;'>" +
				"<tr><td class='cabecera' colspan='2'>Formulario de solicitud de evaluación de crédito</td></tr>" +
				"<tr><th>Nombres</th><td>"+resultado.getNombreAfiliado()+"</td></tr>" +
				"<tr><th>Rut</th><td>"+RutUtil.formatearRut(resultado.getRut())+"</td></tr>" +
				"<tr><th>Teléfono</th><td>"+ parametrosCotiza.getExtension() + " " + parametrosCotiza.getFono()+"</td></tr>" +
				"<tr><th>Teléfono celular</th><td>"+ parametrosCotiza.getPreMovil() + " "+parametrosCotiza.getCelular()+"</td></tr>" +
				"<tr><th>Email</th><td>"+parametrosCotiza.getEmail()+"</td></tr>" +
				"<tr><th>Dirección</th><td>"+direccion+"</td></tr>" +
				"<tr><th>Región</th><td>"+ConstantesFormalizar.REGIONES.get(parametrosCotiza.getRegion())+"</td></tr>" +
				"<tr><th>Comuna</th><td>"+ConstantesFormalizar.COMUNAS.get(parametrosCotiza.getComuna()).getNombreComuna()+"</td></tr>" +
				"<tr><th>Monto solicitado</th><td>$"+Utils.formateaDobleSinDecimal(Utils.stringToDouble(resultado.getMontoSolicitado()))+"</td></tr>" +
				"<tr><th>Cuotas</th><td>"+resultado.getCuotas()+"</td></tr>" +
				"</table>"; 		
		return salida;
	}
	

}
