package cl.araucana.clientewsfonasa.business.services.impl;

import java.rmi.RemoteException;
import java.util.Date;

import WSFonaCajasNM.RespLicCertifTrab;
import WSFonaCajasNM.WSFonaCajasSoapProxy;
import cl.araucana.clientewsfonasa.business.services.CallWSFonasaService;
import cl.araucana.clientewsfonasa.business.services.LogWSFonasaService;
import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.enum.EstadoCallWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.enum.TipoLogWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;
import cl.araucana.clientewsfonasa.common.util.FechaUtil;
import cl.araucana.clientewsfonasa.integration.dao.CallWSFonaDao;
import cl.araucana.clientewsfonasa.integration.dao.impl.CallWSFonaDaoImpl;

public class CallWSFonasaServiceImpl implements CallWSFonasaService{

	public Integer saveCallWSFona(CallWSFonasaTO callTo) throws DaoException{
		CallWSFonaDao callDao = new CallWSFonaDaoImpl();
		Integer idCall = callDao.saveCallWSFona(callTo);
		callTo.setIdCall(idCall);
		return idCall;
	}
	
//	public void updCallWSFonaEstadoEnPreoceso(CallWSFonasaTO callTo) throws ServiceException, DaoException{
//		CallWSFonaDao callDao = new CallWSFonaDaoImpl();
//		callDao.updateCallWSFonaStep1(callTo);
//	}
	
	public void updCallWSFonaEstadoProcesado(CallWSFonasaTO callTo) throws ServiceException, DaoException{
		CallWSFonaDao callDao = new CallWSFonaDaoImpl();
		callDao.updateCallWSFonaStep2(callTo);
	}
	
	public ResponseWSFonasaTO consultarRutFonasa(CallWSFonasaTO call) throws ServiceException, DaoException {
//		Inicializa el servicio de registro de log
		LogWSFonasaService logServ = new LogWSFonasaServiceImpl();
		
//		Cambia el estado de la llamada a EN_PROCSO y guarda los datos 
//		con los que se realizara la llamada al WS Fonasa
//		call.setEstado(EstadoCallWSFonasaEnum.EN_PROCESO.getCodEstado());
//		call.getRequest().setFecCertifica(call.getFechaHora());
//		call.getRequest().setRutInstitucion(ConfigUtil.getValor("araucana.clientewsfonasa.business.rutinstitucion"));
//		call.getRequest().setCodigoUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.codigousuario"));
//		call.getRequest().setClaveUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.claveusuario"));
//		this.updCallWSFonaEstadoEnPreoceso(call);
		logServ.saveLogWSFona(new LogWSFonasaTO(null, call.getIdCall(), new Date(), 
				TipoLogWSFonasaEnum.EJECUCION_LLAMANDA_WS.getCodLog(), 
				TipoLogWSFonasaEnum.EJECUCION_LLAMANDA_WS.getDescLog()));
		try {
//			Realiza la llamada al WS Fonasa a traves del cliente WS
			WSFonaCajasSoapProxy proxy = new WSFonaCajasSoapProxy();
			RespLicCertifTrab obj = proxy.licCertifTrab(call.getRequest().getRutBeneficiario(), 
					FechaUtil.formatearFecha("yyyyMMdd", call.getRequest().getFecCertifica()), 
					call.getRequest().getRutInstitucion(), call.getRequest().getCodigoUsuario(),
					call.getRequest().getClaveUsuario());
			
			if (obj != null) {
//				Recibe respuesta desde WS
				ResponseWSFonasaTO objRes = new ResponseWSFonasaTO(new Short(obj.getEstado()),
						obj.getGloEstado(), obj.getExtApellidoPat(), obj.getExtApellidoMat(), 
						obj.getExtNombres(), obj.getExtSexo(), FechaUtil.parsearFecha("yyyyMMdd", 
						obj.getExtFechaNacimi()), obj.getExtCodEstBen(), obj.getExtDescEstado());
				logServ.saveLogWSFona(new LogWSFonasaTO(null, call.getIdCall(), new Date(), 
						TipoLogWSFonasaEnum.RESPUESTA_DESDE_WS.getCodLog(),
						TipoLogWSFonasaEnum.RESPUESTA_DESDE_WS.getDescLog()));
				
//				Guarda respuesta del WS y cambia la llamada a estado PROCESADO
				call.setResponse(objRes);
				if(objRes.getExtCodEstBen() == null || objRes.getExtCodEstBen().equals("")){
					call.setCodReturn(new Short((short)-3));
					call.setMsjReturn("El WS fonasa respondio con error");
				}else{
					call.setCodReturn(new Short((short)1));
					call.setMsjReturn("El WS fonasa respondio correctamente");
				}
				call.setEstado(EstadoCallWSFonasaEnum.PROCESADO.getCodEstado());
				this.updCallWSFonaEstadoProcesado(call);
				logServ.saveLogWSFona(new LogWSFonasaTO(null, call.getIdCall(), new Date(),
						TipoLogWSFonasaEnum.GUARDA_RESPUESTA_WS.getCodLog(),
						TipoLogWSFonasaEnum.GUARDA_RESPUESTA_WS.getDescLog()));
			}

		} catch (RemoteException e) {
//			Cambia el estado de la llamada a PROCSADO y la marca con error porque el ws no responde o no fue posible conectar
			call.setCodReturn(new Short((short)-1)); 
			call.setMsjReturn("Ocurrio un problema en la conexion con el WS Fonasa");
			call.setEstado(EstadoCallWSFonasaEnum.PROCESADO.getCodEstado());
			this.updCallWSFonaEstadoProcesado(call);
			logServ.saveLogWSFona(new LogWSFonasaTO(null, call.getIdCall(),
					new Date(), TipoLogWSFonasaEnum.ERROR_LLAMADA_WS.getCodLog(),
					TipoLogWSFonasaEnum.ERROR_LLAMADA_WS.getDescLog()));
			e.printStackTrace();
			throw new ServiceException("0011", "Ocurrio un problema en la llamada al WS Fonasa");
		} catch (Exception e) {
//			Cambia el estado de la llamada a PROCSADO y la marca con error
			call.setCodReturn(new Short((short)-2)); 
			call.setMsjReturn("Ocurrio un error interno en la llamada al WS Fonasa");
			call.setEstado(EstadoCallWSFonasaEnum.PROCESADO.getCodEstado());
			this.updCallWSFonaEstadoProcesado(call);
			logServ.saveLogWSFona(new LogWSFonasaTO(null, call.getIdCall(),
					new Date(), TipoLogWSFonasaEnum.ERROR_FORMATO_RESPUESTA_WS.getCodLog(),
					TipoLogWSFonasaEnum.ERROR_FORMATO_RESPUESTA_WS.getDescLog()));
			e.printStackTrace();
			throw new ServiceException("0012", "Ocurrio un problema en la llamada al WS Fonasa");
		}
		return call.getResponse();
	}
}
