package cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Configuraciones;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.treasury.MessageHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompContHeaderOUTServiceStub;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContract;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderRequest;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderRequestOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderResponseOut;
import cl.laaraucana.capaservicios.webservices.client.WSInterface;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public class ClienteQueryCompContHeaderIn implements WSInterface {

  protected Logger logger = Logger.getLogger(this.getClass());
  private HttpTransportProperties.Authenticator auth;
  private MessageHeader messageHeader;
  String ep = Configuraciones.getConfig("ep.QueryComCompContHeaderIn");

  @Override
  public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
    SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();

    EntradaQueryCompContHeaderInVO entradaVO = (EntradaQueryCompContHeaderInVO) entrada;
    logger.debug("<< Inicio Web Service: QueryCompContHeaderIn: "+entradaVO.getRut());

    QueryCompContHeaderOUTServiceStub stub = new QueryCompContHeaderOUTServiceStub();
    stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

    HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
    auth.setUsername(Configuraciones.getConfig("sap.user"));
    auth.setPassword(Configuraciones.getConfig("sap.pass"));
    auth.setPreemptiveAuthentication(true);
    stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

    QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
    QueryCompactContract entradaWs = new QueryCompactContract();

    entradaWs.setRut(entradaVO.getRut().toUpperCase());
    entradaWs.setCreditStatus(entradaVO.getFlagTipoCredito());

    MessageHeader messageHeader = new MessageHeader();
    messageHeader.setDATECREATION(Utils.fechaSAP());
    messageHeader.setHOST(Configuraciones.getConfig("sap.host"));
    messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("sap.org"));
    messageHeader.setUSER(Configuraciones.getConfig("sap.sysuser"));

    query.setMessageHeader(messageHeader);
    query.setQueryCompactContractHeader(entradaWs);

    QueryCompactContractHeaderRequestOut requestOUT = new QueryCompactContractHeaderRequestOut();
    requestOUT.setQueryCompactContractHeaderRequestOut(query);

    QueryCompactContractHeaderResponseOut respuesta = new QueryCompactContractHeaderResponseOut();


    try {
      respuesta = stub.queryCompContrHeader(requestOUT);
    } catch (RemoteException e) {
      //			logger.error("Remote Exception en QueryCompContHeaderin", e);
      //			salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);
      //			salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: compruebe el servicio");
      //			return salidaVO;
      throw new Exception("Error en servicio QueryCompContHeaderIn: compruebe el servicio", e);
    }

    if (respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_SUCCESS)) {
      salidaVO = mapear(respuesta);
      salidaVO.setCodigoError(Constantes.COD_RESPUESTA_SUCCESS);
      salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK. Código error: 0.");
      logger.debug(salidaVO.getMensaje());
    }else {
      // codigo error 1 por lista vacia.
      if(respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_VACIO)){
        salidaVO.setCodigoError(Constantes.COD_RESPUESTA_VACIO);
        salidaVO.setMensaje("QueryCompContHeaderIn OK. El rut no contiene créditos. 0");
        logger.debug(salidaVO.getMensaje());
      }else{
        //salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);
        //salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: " + msg);
        String msg = respuesta.getQueryCompactContractHeaderResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getQueryCompactContractHeaderResponseOut().getLog()[0].getSYSTEM() + ")";
        logger.debug(salidaVO.getMensaje());
        throw new Exception("Error en servicio QueryCompContHeaderIn:" + msg);
      }
    }
    return salidaVO;
  }

  private SalidaListaQueryCompContHeaderInVO mapear(
      QueryCompactContractHeaderResponseOut entrada) {

    SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
    List<SalidaQueryCompContHeaderInVO> listaCreditos = new ArrayList<SalidaQueryCompContHeaderInVO>();
    SalidaQueryCompContHeaderInVO credito = null;

    for (QueryCompactContractHeader creditoWs : entrada
        .getQueryCompactContractHeaderResponseOut()
        .getQueryCompactContractHeader()) {
      credito = new SalidaQueryCompContHeaderInVO();
      
      credito.setContractAmount(Utils.reemplazaNuloPorCero(creditoWs.getContractAmount()).trim().replace(".", "").replace(" ", ""));//
      credito.setInstallmentAmount(Utils.reemplazaNuloPorCero(creditoWs.getInstallmentAmount()).replace(".", "").replace(" ", ""));//
      credito.setInstallmentQuantity(Utils.reemplazaNuloPorCero(creditoWs.getInstallmentQuantity()).replace(".", "").replace(" ", ""));//
      
      credito.setContractBegin(creditoWs.getContractBegin());
      credito.setContractCurrency(creditoWs.getContractCurrency());
      credito.setContractEnd(creditoWs.getContractEnd());
      credito.setContractNumber(creditoWs.getContractNumber());
      credito.setPayer(creditoWs.getPayer());
      credito.setRepacta(creditoWs.getRepacta());
      credito.setReprogramac(creditoWs.getReprogramac());
      credito.setRole(creditoWs.getRole());
      credito.setTerminated(creditoWs.getTerminated());
      credito.setWithExtintion(creditoWs.getWithExtintion());

      listaCreditos.add(credito);
    }
    salidaVO.setListaCreditos(listaCreditos);

    return salidaVO;
  }
  
  /**
   * Busca un crédito de la lista, si no lo encuentra
   * @param rut
   * @param folio
   * @return null si el crédito no fue encontrado
   * @throws Exception
   */
  public SalidaQueryCompContHeaderInVO buscarCredito(String rut, String folio, String estado) throws Exception{
	  SalidaQueryCompContHeaderInVO credito = null;
	  
		try {
				QueryCompContHeaderOUTServiceStub stub = new QueryCompContHeaderOUTServiceStub();
				stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
	
				QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
				QueryCompactContract entradaWs = new QueryCompactContract();
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
				
				entradaWs.setRut(rut.toUpperCase());
				entradaWs.setCreditStatus(estado);
				query.setMessageHeader(messageHeader);
				query.setQueryCompactContractHeader(entradaWs);
	
				QueryCompactContractHeaderRequestOut requestOUT = new QueryCompactContractHeaderRequestOut();
				requestOUT.setQueryCompactContractHeaderRequestOut(query);
	
				QueryCompactContractHeaderResponseOut respuesta = stub.queryCompContrHeader(requestOUT);
				
				//No encontrado
				if(respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_VACIO)){
					return null;
				}
				
				if(respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_ERROR)){
					throw new Exception("Error al buscar crédito: " + respuesta.getQueryCompactContractHeaderResponseOut().getLog()[0].getMESSAGE());
				}
				
			    for (QueryCompactContractHeader creditoWs : respuesta.getQueryCompactContractHeaderResponseOut().getQueryCompactContractHeader()) {
			    	//Crédito encontrado
			    	if(creditoWs.getContractNumber().trim().equals(folio)){
			    		credito = new SalidaQueryCompContHeaderInVO();
			    		//numericos
			    		credito.setContractNumber(creditoWs.getContractNumber());
			    		credito.setContractAmount(Utils.reemplazaNuloPorCero(creditoWs.getContractAmount()).trim().replace(".", "").replace(" ", ""));
			    		credito.setInstallmentAmount(Utils.reemplazaNuloPorCero(creditoWs.getInstallmentAmount()).replace(".", "").replace(" ", ""));
			    		credito.setInstallmentQuantity(Utils.reemplazaNuloPorCero(creditoWs.getInstallmentQuantity()).replace(".", "").replace(" ", ""));
			    		//Fechas
			    	    credito.setContractBegin(creditoWs.getContractBegin());
			    	    credito.setContractEnd(creditoWs.getContractEnd());
			    	    
			    	    //Caracteres
			    	    credito.setContractCurrency(creditoWs.getContractCurrency());
			    	    credito.setPayer(creditoWs.getPayer());
			    	    credito.setRepacta(creditoWs.getRepacta());
			    	    credito.setReprogramac(creditoWs.getReprogramac());
			    	    credito.setRole(creditoWs.getRole());
			    	    credito.setTerminated(creditoWs.getTerminated());
			    	    credito.setWithExtintion(creditoWs.getWithExtintion());
			    	    return credito;
			    	}
			    }
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar crédito: " + e.getMessage());
		}
	  return credito;
  }
  
  /**
   * Constructor por defecto
   * Setea parámetros de seguridad del servicio
   */
  public ClienteQueryCompContHeaderIn(){
		auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("sap.user"));
		auth.setPassword(Configuraciones.getConfig("sap.pass"));
		auth.setPreemptiveAuthentication(true);
		
		messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(Configuraciones.getConfig("sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("sap.org"));
		messageHeader.setUSER(Configuraciones.getConfig("sap.sysuser"));
  }

}
