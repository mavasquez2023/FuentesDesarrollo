package cl.laaraucana.rendicionpagonomina.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.AbstractEntradaVO;
import cl.laaraucana.rendicionpagonomina.vo.AbstractSalidaVO;
import cl.laaraucana.rendicionpagonomina.vo.EntradaWSBES;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaVo;
import cl.laaraucana.rendicionpagonomina.vo.ParamRendicionVO;
import cl.laaraucana.rendicionpagonomina.vo.RespuestaBanco;
import cl.laaraucana.rendicionpagonomina.vo.RespuestaServicioVo;

@Service
public class WsServiceIml implements WsService {

	private static final Logger logger = Logger.getLogger(WsServiceIml.class);

	@Autowired
	private EncryptService encryptService;

	@Autowired
	private ProcessService processService;


	//Rendición x Fecha
	@Override
	public String rendicionWsNominasBES(ParamRendicionVO rendicionVO) throws Exception {

		String path = Configuraciones.getConfig("path.Nominas.recepcion");
		path= path.replaceAll("#banco#", rendicionVO.getCodigoBanco());
		String metodo_rendicion = Configuraciones.getConfig("bes.metodo.rendicion");
		String metodo_token = Configuraciones.getConfig("bes.metodo.token");
		
		String encrypt = "";
		String encrypt2 = "";
		AbstractSalidaVO salidaEncriptadaWS = null;

		List<RespuestaBanco> respuesta = new ArrayList<RespuestaBanco>();
		List<RespuestaServicioVo> validate = new ArrayList<RespuestaServicioVo>();

		RespuestaBanco vo = new RespuestaBanco();
		
		//TOKEN
		vo.setMetodo(metodo_token);
		vo.setConvenio(rendicionVO.getConvenioBanco());
		vo.setNumeroNomina("");
		vo.setFechaPagoDesde("");
		vo.setFechaPagoHasta("");

		String strXmlValidate = Utils.enterVerifyXml(vo);
		logger.info("Mensaje Token:" + strXmlValidate);
		EntradaWSBES entrada = new EntradaWSBES();
		entrada.setMensaje(Utils.prepareXml(encryptService.encrypt(strXmlValidate)));
		
		//llamado WS
		salidaEncriptadaWS = Utils.call(entrada);
		if(salidaEncriptadaWS.getCodigoError().equals("-1")){
			logger.error("Error en la llamada al webservice de banco estado.");
			throw new Exception("Respuesta NOK BES, mensaje: " +  salidaEncriptadaWS.getMensaje());
		}
		encrypt = Utils.extraeEncrypt(salidaEncriptadaWS.getMensaje());
		validate = Utils.parseXmlValidate(encryptService.decrypt(encrypt));
		logger.info("Salida txt token:" + encryptService.decrypt(encrypt));
		
		//Si respuesta Token es exitosa se invoca Rendición
		String nameArchivoSalida = "";
		if (validate.size()>0 && validate.get(0).getCodigoRetorno().toUpperCase().equals("OK")) {

			//RENDICION
			RespuestaBanco vo2 = new RespuestaBanco();
			vo2.setMetodo(metodo_rendicion);
			vo2.setConvenio(rendicionVO.getConvenioBanco());
			vo2.setNumeroNomina(rendicionVO.getNumNomina());
			vo2.setPlantillaRendicion(rendicionVO.getPlantilla());
			vo2.setFechaPagoDesde(rendicionVO.getFechaDesde());
			vo2.setFechaPagoHasta(rendicionVO.getFechaHasta());

			String strXmlEnter = Utils.getNominaRendicion(vo2);
			logger.info("XML entrada WS:" + strXmlEnter);
			entrada.setMensaje(Utils.prepareXml(encryptService.encrypt(strXmlEnter)));

			salidaEncriptadaWS = Utils.call(entrada);

			encrypt2 = Utils.extraeEncrypt(salidaEncriptadaWS.getMensaje());
			respuesta = Utils.parseXml(encryptService.decrypt(encrypt2));
			logger.info("Salida txt:" + encryptService.decrypt(encrypt2));

			if (encryptService.decrypt(encrypt2).trim().isEmpty()) {
				logger.error("Error en la llamada al webservice de banco estado, respuesta vacía.");
				throw new Exception("No hay respuesta.");
			}

			if (respuesta.size() > 0 && (respuesta.get(0).getCodigoRetorno().equals("10") || respuesta.get(0).getCodigoRetorno().equals("5"))) {
				int i = 0;
				for (RespuestaBanco respuestaBanco : respuesta) {
					//Date date= new Date();
					//long mili= date.getTime();
					processService.decodeBase64(respuestaBanco.getArchivo(), "decode_" + i + ".zip", path);
					nameArchivoSalida = processService.zipFiles(path, path + "decode_" + i + ".zip");
					i++;
				}
			}
		}else {
			logger.error("Error en la llamada al Token webservice de banco estado.");

			throw new Exception("Respuesta NOK Token BES. ");
		}
		logger.info("Nombre archivo salida" + nameArchivoSalida);
		return nameArchivoSalida;
	}

	@Override
	public String envioNominaWsBES(EnvioNominaVo vo) throws Exception {

		String xml = Utils.sendNomina(vo);

		logger.info("XML envío:" + xml);

		xml = Utils.prepareXml(encryptService.encrypt(xml));

		EntradaWSBES entrada = new EntradaWSBES();

		entrada.setMensaje(xml);

		AbstractSalidaVO salida = Utils.call(entrada);

		String salidaXml = Utils.extraeEncrypt(salida.getMensaje());

		return encryptService.decrypt(salidaXml);

	}

}
