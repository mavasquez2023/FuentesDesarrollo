	/**
 * 
 */
package cl.araucana.clientewsfonasa.business.services.impl;

import java.rmi.RemoteException;

import WSFonaCajasNM.RespConFormLCC;
import WSFonaCajasNM.RespLicCertifTrab;
import WSFonaCajasNM.WSFonaCajasSoapProxy;

import cl.araucana.clientewsfonasa.business.services.WSConsultaFonasa;
import cl.araucana.clientewsfonasa.business.to.RequestWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseFormFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;
import cl.araucana.clientewsfonasa.common.util.ConfigUtil;
import cl.araucana.clientewsfonasa.common.util.FechaUtil;
import cl.araucana.clientewsfonasa.integration.dao.CallWSFonaDao;
import cl.araucana.clientewsfonasa.integration.dao.impl.CallWSFonaDaoImpl;
import cl.recursos.EnviarMail;

/**
 * @author usist199
 *
 */
public class WSConsultaFonasaImpl implements WSConsultaFonasa{

	/* (sin Javadoc)
	 * @see cl.araucana.clientewsfonasa.business.services.WSIntegracionFonasa#consultarRutFonasa(java.lang.String)
	 */
	public ResponseWSFonasaTO consultarRutFonasa(String rut)
			throws ServiceException, DaoException {
		RequestWSFonasaTO request= new RequestWSFonasaTO();
		ResponseWSFonasaTO objRes= null;
		String emails="";
		try {
			request.setRutBeneficiario(rut);
			request.setFecCertifica(FechaUtil.getFechaHoy("yyyyMMdd"));
			request.setRutInstitucion(ConfigUtil.getValor("araucana.clientewsfonasa.business.rutinstitucion"));
			request.setCodigoUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.codigousuario"));
			request.setClaveUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.claveusuario"));
			emails= ConfigUtil.getValor("araucana.clientewsfonasa.correo.destinatarios");
			
//			Realiza la llamada al WS Fonasa a traves del cliente WS
			WSFonaCajasSoapProxy proxy = new WSFonaCajasSoapProxy();
			RespLicCertifTrab obj = proxy.licCertifTrab(request.getRutBeneficiario(), 
					FechaUtil.formatearFecha("yyyyMMdd", request.getFecCertifica()), 
					request.getRutInstitucion(), request.getCodigoUsuario(),
					request.getClaveUsuario());
			
			if (obj != null) {
//				Recibe respuesta desde WS
				objRes = new ResponseWSFonasaTO(new Short(obj.getEstado()),
						obj.getGloEstado(), obj.getExtApellidoPat(), obj.getExtApellidoMat(), 
						obj.getExtNombres(), obj.getExtSexo(), FechaUtil.parsearFecha("yyyyMMdd", 
								obj.getExtFechaNacimi()), obj.getExtCodEstBen(), obj.getExtDescEstado());

//				Si WS no respondió se cambia estado del Response
				/*if(objRes.getExtCodEstBen() == null || objRes.getExtCodEstBen().equals("")){
					objRes= new ResponseWSFonasaTO();
					objRes.setEstado(new Short((short)-3));
					objRes.setGloEstado("El WS Fonasa respondio con error");
				}*/

			}

		} catch (RemoteException e) {
//			Cambia el estado de la llamada a PROCSADO y la marca con error porque el ws no responde o no fue posible conectar
			objRes= new ResponseWSFonasaTO();
			objRes.setEstado(new Short((short)-1)); 
			objRes.setGloEstado("Ocurrio un problema en la conexion con el WS Fonasa");
			ConfigUtil.enviarMail(ConfigUtil.split(emails, ";"), "consultarRutFonasa", "Problema en la conexion con el WS Fonasa, Estado -1: " + e.getMessage());
			e.printStackTrace();
			//throw new ServiceException("0011", "Ocurrio un problema en la llamada al WS Fonasa");
		} catch (Exception e) {
//			Cambia el estado de la llamada a PROCSADO y la marca con error
			objRes= new ResponseWSFonasaTO();
			objRes.setEstado(new Short((short)-2)); 
			objRes.setGloEstado("Ocurrio un error interno en la llamada al WS Fonasa");
			ConfigUtil.enviarMail(ConfigUtil.split(emails, ";"), "consultarRutFonasa", "Problema interno de aplicación, Estado -2: " + e.getMessage());
			e.printStackTrace();
			//throw new ServiceException("0012", "Ocurrio un problema en la llamada al WS Fonasa");
		}
		return objRes;
	}	

	public ResponseFormFonasaTO consultarEstadoFormulario(int color, int numeroLicencia, int rutAfiliado)
	throws ServiceException, DaoException {
		String emails="";
		System.out.println("Consultar estado Formulario, numero:" + numeroLicencia + ", color:" + color);
		RequestWSFonasaTO request= new RequestWSFonasaTO();
		request.setFecCertifica(FechaUtil.getFechaHoy("yyyyMMdd"));
		request.setRutInstitucion(ConfigUtil.getValor("araucana.clientewsfonasa.business.rutinstitucion"));
		request.setCodigoUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.codigousuario"));
		request.setClaveUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.claveusuario"));
		request.setCodigoAsegurador(Short.parseShort(ConfigUtil.getValor("araucana.clientewsfonasa.business.codigoasegurador")));
		emails= ConfigUtil.getValor("araucana.clientewsfonasa.correo.destinatarios");
		
		ResponseFormFonasaTO objRes= null;
		try {
			if(numeroLicencia <=0 || color>2 || color <=0){
				objRes= new ResponseFormFonasaTO();
				objRes.setEstado(new Short((short)-10)); 
				objRes.setGlosaEstado("Error en los parámetros ingresados al WS");
				return objRes;
			}
			
			
			/*
			 * verificación en SIL ingreso licencia
			 */
			String numcorto=String.valueOf(numeroLicencia);
			if(numcorto.length()>7){
				numcorto= numcorto.substring(numcorto.length()-7);		
			}
			int existe=0;
			if(rutAfiliado>0){
				CallWSFonaDao callDao = new CallWSFonaDaoImpl();
				System.out.println("Consultar licencia en SIL, numero:" + numcorto);
				existe=0;
				//existe= callDao.getCallExisteLicSIL(Integer.parseInt(numcorto), rutAfiliado);
			}
			if(existe>0){
				objRes= new ResponseFormFonasaTO();
				objRes.setEstado(new Short("0"));
				objRes.setGlosaEstado("OK");
				objRes.setResultado(new Short("99"));
				objRes.setGlosaResultado("Numero de Licencia ya ingresado");
				return objRes;
			}
			
			
			//	Realiza la llamada al WS Fonasa a traves del cliente WS
			WSFonaCajasSoapProxy proxy = new WSFonaCajasSoapProxy();
			RespConFormLCC obj = proxy.conFormuLCC(request.getCodigoUsuario(), request.getClaveUsuario(), request.getCodigoAsegurador(), request.getRutInstitucion(), (short)color, numeroLicencia);

			if (obj != null) {
				//		Recibe respuesta desde WS
				objRes = new ResponseFormFonasaTO(new Short(obj.getEstado()),
						obj.getGloEstado(), new Short(obj.getCodResultado()), obj.getGloResultado());

				//		Si WS no respondió se cambia estado del Response
				if(objRes.getEstado() != null && objRes.getEstado().intValue()==2){
					objRes.setGlosaResultado("Licencia se encuentra tramitada por otra entidad");
				}

			} 


		} catch (RemoteException e) {
			//	Cambia el estado de la llamada a PROCSADO y la marca con error porque el ws no responde o no fue posible conectar
			objRes= new ResponseFormFonasaTO();
			objRes.setEstado(new Short((short)-1)); 
			objRes.setGlosaEstado("Ocurrio un problema en la conexion con el WS Fonasa");
			ConfigUtil.enviarMail(ConfigUtil.split(emails, ";"), "consultarEstadoFormulario", "Problema en la conexion con el WS Fonasa, Estado -1: " + e.getMessage());
			e.printStackTrace();
			//throw new ServiceException("0011", "Ocurrio un problema en la llamada al WS Fonasa");
		} catch (Exception e) {
			//	Cambia el estado de la llamada a PROCSADO y la marca con error
			objRes= new ResponseFormFonasaTO();
			objRes.setEstado(new Short((short)-2)); 
			objRes.setGlosaEstado("Ocurrio un error interno en la llamada al WS Fonasa");
			ConfigUtil.enviarMail(ConfigUtil.split(emails, ";"), "consultarEstadoFormulario", "Problema interno de aplicación, Estado -2: " + e.getMessage());
			e.printStackTrace();
			//throw new ServiceException("0012", "Ocurrio un problema en la llamada al WS Fonasa");
		}
		return objRes;
	}
}
