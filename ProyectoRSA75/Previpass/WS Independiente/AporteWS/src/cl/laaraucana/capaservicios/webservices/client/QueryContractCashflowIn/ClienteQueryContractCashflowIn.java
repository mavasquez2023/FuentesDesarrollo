package cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Configuraciones;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowOUTServiceStub;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowRequest;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowRequestOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowResponseOut;
import cl.laaraucana.capaservicios.webservices.client.WSInterface;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public class ClienteQueryContractCashflowIn implements WSInterface {

  protected Logger logger = Logger.getLogger(this.getClass());

  @Override
  public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {


    EntradaQueryContractCashflowInVO entradaVO = (EntradaQueryContractCashflowInVO) entrada;
    logger.debug("<< Ingreso a ClienteQueryContractCashflowIn con folio: "+entradaVO.getFolioCredito());
    String ep = Configuraciones.getConfig("ep.QueryContractCashFlowOUT");

    SalidaListaQueryContractCashflowInVO salidaVO = new SalidaListaQueryContractCashflowInVO();

    QueryContractCashFlowOUTServiceStub stub = new QueryContractCashFlowOUTServiceStub();
    stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

    HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
    auth.setUsername(Configuraciones.getConfig("sap.user"));
    auth.setPassword(Configuraciones.getConfig("sap.pass"));
    auth.setPreemptiveAuthentication(true);
    stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

    QueryContractCashFlowRequest query = new QueryContractCashFlowRequest();

    MessageHeader messageHeader = new MessageHeader();
    messageHeader.setDATECREATION(Utils.fechaSAP());
    messageHeader.setHOST(Configuraciones.getConfig("sap.host"));
    messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("sap.org"));
    messageHeader.setUSER(Configuraciones.getConfig("sap.sysuser"));

    query.setNroCuenta(entradaVO.getFolioCredito());
    query.setMessageHeader(messageHeader);

    QueryContractCashFlowRequestOut requestOut = new QueryContractCashFlowRequestOut();
    requestOut.setQueryContractCashFlowRequestOut(query);
    QueryContractCashFlowResponseOut respuesta = new QueryContractCashFlowResponseOut();

    try {
      respuesta = stub.queryContractCashFlow(requestOut);
    } catch (Exception e) {
      logger.error(e);
      salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);
      salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: compruebe el servicio");
      return salidaVO;
    }

    if(respuesta.getQueryContractCashFlowResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_SUCCESS)){
      salidaVO = mapear(respuesta);
      salidaVO.setCodigoError(Constantes.COD_RESPUESTA_SUCCESS);
      salidaVO.setMensaje("Carga de creditos QueryContractCashflowIn OK. Código error: 0.");
      logger.debug(salidaVO.getMensaje());
    }else{
      salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);
      String msg = respuesta.getQueryContractCashFlowResponseOut().getLog().getMESSAGE() + " (" + respuesta.getQueryContractCashFlowResponseOut().getLog().getSYSTEM() + ")";
      salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: " + msg);
      logger.debug(salidaVO.getMensaje());
    }

    logger.debug(">> Salida a ClienteQueryContractCashflowIn");
    return salidaVO;
  }

  private SalidaListaQueryContractCashflowInVO mapear(QueryContractCashFlowResponseOut entrada){
    SalidaListaQueryContractCashflowInVO salidaVO = new SalidaListaQueryContractCashflowInVO();
    SalidaQueryContractCashflowInVO cuotas =null;
    List<SalidaQueryContractCashflowInVO> listaCuotas = new ArrayList<SalidaQueryContractCashflowInVO>();

    if(entrada.getQueryContractCashFlowResponseOut().getLineaComercial()!=null){
      salidaVO.setLineaComercial(entrada.getQueryContractCashFlowResponseOut().getLineaComercial());
    }else{
      salidaVO.setLineaComercial("");
    }

    if(entrada.getQueryContractCashFlowResponseOut().getNroCuenta()!=null){
      salidaVO.setNroCuenta(entrada.getQueryContractCashFlowResponseOut().getNroCuenta());
    }else{
      salidaVO.setNroCuenta("");
    }

    if(entrada.getQueryContractCashFlowResponseOut().getE_TOTAL_CUOTAS()!=null){
      salidaVO.setCantidadTotalCuotas(entrada.getQueryContractCashFlowResponseOut().getE_TOTAL_CUOTAS());
    }else{
      salidaVO.setCantidadTotalCuotas("");
    }

    if(entrada.getQueryContractCashFlowResponseOut().getDetalleCuotas() == null){
      return salidaVO;
    }

    for (DetalleCuotasCF detalle : entrada.getQueryContractCashFlowResponseOut().getDetalleCuotas()) {
      cuotas = new SalidaQueryContractCashflowInVO();

      cuotas.setEstadoCuota(detalle.getEstadoCuota());
      cuotas.setFechaVencCuota(detalle.getFechaVencCuota());
      cuotas.setEstadoPago(detalle.getEstadoPago());
      cuotas.setFolioPago(detalle.getFolioPago());
      cuotas.setMoneda(detalle.getMoneda());
      cuotas.setMontoAbono(detalle.getMontoAbono());
      cuotas.setMontoGravamenes(detalle.getMontoGravamenes());
      cuotas.setMontoServAdic(detalle.getMontoServAdic());
      cuotas.setOficinaPago(detalle.getOficinaPago());
      cuotas.setTransactionType(detalle.getTransactionType());
      cuotas.setUltFechaPago(detalle.getUltFechaPago());
      
      //Valores numericos
      cuotas.setNroCuota(Utils.reemplazaNuloPorCero(detalle.getNroCuota()));
      cuotas.setTotalCuota(Utils.reemplazaNuloPorCero(detalle.getTotalCuota()));
      cuotas.setMontoInteres(Utils.reemplazaNuloPorCero(detalle.getMontoInteres()));
      cuotas.setMontoCapital(Utils.reemplazaNuloPorCero(detalle.getMontoCapital()));
      cuotas.setCapitalRestante(Utils.reemplazaNuloPorCero(detalle.getCapitalRestante()));
      

      listaCuotas.add(cuotas);
    }
    salidaVO.setListaCuotas(listaCuotas);

    return salidaVO;
  }

}
