package cl.laaraucana.ventaremota.ws;

import java.io.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Logger;

import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.entities.UsuarioEntiti;
import cl.laaraucana.ventaremota.ibatis.dao.CreditosDao;
import cl.laaraucana.ventaremota.ibatis.dao.CreditosDaoImpl;
import cl.laaraucana.ventaremota.model.CreditoVo;
import cl.laaraucana.ventaremota.model.VentaServiceVo;
import cl.laaraucana.ventaremota.services.FTPService;
import cl.laaraucana.ventaremota.services.FTPServiceImpl;
import cl.laaraucana.ventaremota.services.UsuarioService;
import cl.laaraucana.ventaremota.services.UsuarioServiceImpl;
import cl.laaraucana.ventaremota.util.Configuraciones;
import cl.laaraucana.ventaremota.ws.vo.CredencialesVO;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/ingresarVenta", serviceName = "VentaServicioService", portName = "VentaServicioPort")
public class VentaRemotaService {

	protected Logger logger = Logger.getLogger(VentaRemotaService.class);
	private UsuarioEntiti dataUsuario = null;

	FTPService ftp = new FTPServiceImpl();

	private boolean autenticacionWS(CredencialesVO aut) {
		boolean salida = false;
		try {
			if (dataUsuario == null) {
				UsuarioService user = new UsuarioServiceImpl();
				dataUsuario = user.consultaCredenciales(aut);
			}
			if (dataUsuario != null) {
				String password = dataUsuario.getPassword();

				if (aut.getPassword().equals(password)) {

					salida = true;
				}
			}

		} catch (Exception e) {
			logger.error("Error ", e);

		}
		return salida;
	}

	@WebMethod(action = "http://servicio.laaraucana.cl/ingresarVenta/informarCredito", operationName = "informarCredito")
	public VentaServiceVo informarCredito(@WebParam(name = "autenticacion") CredencialesVO autenticacion,
			@WebParam(name = "credito") CreditoVo credito) {
		// TODO Auto-generated method stub

		VentaServiceVo vo = new VentaServiceVo();
		CreditoEntiti en = new CreditoEntiti();
		CreditosDao dao = new CreditosDaoImpl();

		try {
			boolean usuarioValido = autenticacionWS(autenticacion);

			if (!usuarioValido) {
				logger.info("Usuario o Clave no válido: " + autenticacion.getUsuario() + ", clave="
						+ autenticacion.getPassword());
				vo.setCodigo("2");
				vo.setSalida("Usuario o Clave no válido");

			} else {
				logger.info("Procesando NUMERO_OFERTA:" + credito.getNumeroOferta());
				en.setCae(credito.getCAE());
				en.setEstado(credito.getEstado());
				en.setFechaEnvio(credito.getFechaEnvio());
				en.setFechaOtorgamiento(credito.getFechaOtorgamiento());
				en.setFolioCredito(credito.getFolioCredito());
				en.setFormaPago(credito.getFormaPago());
				en.setGastoNotarial(credito.getGastoNotarial());
				en.setHoraEnvio(credito.getHoraEnvio());
				en.setImpuesto(credito.getImpuesto());
				en.setMontoComision(credito.getMontoComision());
				en.setMontoCompraCartera(credito.getMontoCompraCartera());
				en.setMontoCuota(credito.getMontoCuota());
				en.setMontoLiquido(credito.getMontoLiquido());
				en.setMontoPagoMora(credito.getMontoPagoMora());
				en.setMontoRenegociado(credito.getMontoRenegociado());
				en.setMontoSolicitado(credito.getMontoSolicitado());
				en.setMontoTotalSolicitado(credito.getMontoTotalSolicitado());
				en.setNombreCliente(credito.getNombreCliente());
				en.setNumeroCuotas(credito.getNumeroCuotas());
				en.setNumeroDocumento(credito.getNumeroDocumento());
				en.setNumeroOferta(credito.getNumeroOferta());
				en.setRutCliente(Long.parseLong(credito.getRutCliente().split("-")[0]));
				en.setDvCliente(credito.getRutCliente().split("-")[1]);
				en.setSeguroCesantia(credito.getSeguroCesantia());
				en.setSeguroDesgravamen(credito.getSeguroDesgravamen());
				en.setTasaInteresMensual(credito.getTasaInteresMensual());
				en.setTipoCreditoNormal(credito.getTipoCreditoNormal());
				
				//Se agrega nuevos parámetros por set 3 preguntas
				en.setIdTipoContrato(credito.getIdTipoContrato());
				en.setDescripcionTipoContrato(credito.getDescripcionTipoContrato());
				en.setRentaDepurada(credito.getRentaDepurada());
				en.setNumeroDireccion(credito.getNumeroDireccion());
				en.setCelular(String.valueOf(credito.getCelular()));
				en.setCodigoComuna(credito.getCodigoComuna());
				en.setCodigoTipoCuenta(credito.getCodigoTipoCuenta());
				en.setCodigoBanco(credito.getCodigoBanco());
				en.setPlazoCredito(credito.getPlazoCredito());
				en.setRutEmpresa(credito.getRutEmpresa());
				en.setFechaNacimiento(credito.getFechaNacimiento());
				en.setRegimenSalud(credito.getRegimenSalud());
				en.setCreditosVigentes(credito.getCreditosVigentes());
				
				//Se agrega nuevo campo para Reprogramacion
				en.setTipoReprogramacion(credito.getTipoReprogramacion());
				//Se agrega nuevo campo para contrato canales remotos
				String contratoCR= credito.getContratoCR();
				contratoCR= contratoCR.equals("X")?"NO":"SI";
				en.setContratoCR(contratoCR);
				//Se agrega nuevo campo para deudor Alimenticio
				String deudorAlimenticio= credito.getDeudorAlimenticio();
				deudorAlimenticio= deudorAlimenticio.equals("X")?"SI":"NO";
				en.setDeudorAlimenticio(deudorAlimenticio);
				en.setContratoCR(contratoCR);
				
				String host = Configuraciones.getConfig("hostFTP");
				String port = Configuraciones.getConfig("portFTP");
				String user = Configuraciones.getConfig("usuarioFTP");
				String clave = Configuraciones.getConfig("claveFTP");
				String FTPCarpeta = Configuraciones.getConfig("FTPCarpeta");
				
				logger.info("Conectando al FTP host:" + host + ":" + port );
				ftp.connectToFTP(host, Integer.parseInt(port), user, clave);
				logger.info("Donload File" + host + ":" + port );
				String file = ftp.downloadFileFromFTP(FTPCarpeta, en.getNumeroDocumento());
				logger.info("Respuesta download ftp:" + file);
			/*	boolean ret = ftp.getFileFromFTP(FTPCarpeta,
						en.getNumeroDocumento());
*/
				if (file.equals("")) {
					vo.setSalida("Error, falta archivo.");
					vo.setCodigo("3");
					logger.info("Eror ftp falta archivo");
				} else if (file.equals("99")) {

					vo.setSalida("Error, mas de un archivo.");
					
					vo.setCodigo("3");
				}else if (file.equals("-1")) {

					vo.setSalida("Error, no se pudo verificar archivo.");
					
					vo.setCodigo("3");
				}else {

					logger.info("Insertando crédito " + credito.getFolioCredito());
					logger.info(en.toString());
					dao.insertCredito(en);

					vo.setSalida("OK");
					vo.setCodigo("0");
				}
				ftp.disconnectFTP();
			}
			
		} catch (Exception ex) {

			logger.error("Error del servicio ", ex);

			vo.setSalida("Error del servicio, " + ex.getMessage());
			vo.setCodigo("1");

		}
		
		return vo;
	}

}
