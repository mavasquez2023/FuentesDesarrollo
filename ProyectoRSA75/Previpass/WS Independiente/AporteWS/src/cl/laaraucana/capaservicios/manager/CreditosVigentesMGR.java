package cl.laaraucana.capaservicios.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.ConstantesWS;
import cl.laaraucana.capaservicios.util.RutUtil;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn.ClienteQueryContractHeaderIn;
import cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn.VO.SalidaQueryContractHeaderInVO;
import cl.laaraucana.capaservicios.webservices.vo.Log;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaCreditosEspVigentes.ConsultaCreditosEspVigOut;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaCreditosEspVigentes.CreditoVO;

public class CreditosVigentesMGR {

  protected Logger logger = Logger.getLogger(this.getClass());
  /**
   * Funcion que recibe el rut de un cliente y obtiene de distintos servicios web el/los creditos especiales
   * que tenga aprobado.
   * @param rut
   * @return
   */
  public ConsultaCreditosEspVigOut getCreditosVigentes(String rut) {
    logger.info("Inicia proceso para obtener creditos especiales");

    ConsultaCreditosEspVigOut salidaVO = new ConsultaCreditosEspVigOut();
    Log log = null;
    //Validar datos de entrada
    if(!RutUtil.IsRutValido(rut)){
      salidaVO.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese un rut válido"));
      return salidaVO;
    }

    List<CreditoVO> listaCreditos = new ArrayList<CreditoVO>();
    CreditoVO credito = null;

    // Instanciar clientes de servicios web necesarios.
    ClienteQueryCompContHeaderIn clienteQueryComp = new ClienteQueryCompContHeaderIn();
    ClienteQueryContractHeaderIn clienteQueryContract = new ClienteQueryContractHeaderIn();
    // ClienteQueryContractCashflowIn clienteQueryCashflow = new ClienteQueryContractCashflowIn();

    SalidaListaQueryCompContHeaderInVO salidaQueryCompContHeader;
    SalidaQueryContractHeaderInVO infoCredito;

    try {

      salidaQueryCompContHeader = (SalidaListaQueryCompContHeaderInVO) clienteQueryComp.call(mapQueryCompContHeaderIn(rut));
      if(salidaQueryCompContHeader.getListaCreditos().size()==0){
        log = new Log(Constantes.COD_RESPUESTA_VACIO, "El rut no tiene créditos asociados");
        salidaVO.setLog(log);
        return salidaVO;
      }


      for (SalidaQueryCompContHeaderInVO queryComp : salidaQueryCompContHeader.getListaCreditos()) {
        credito = new CreditoVO();
        // obtener datos restantes desde QueryContractHeaderIn
        infoCredito = (SalidaQueryContractHeaderInVO) clienteQueryContract.call(mapQueryContract(rut, queryComp.getContractNumber()));

        // si el crédito pertenece a la linea 63 (crédito especial) se mapea, encaso contrario no.
        if (infoCredito.getComercialLine().equals(ConstantesWS.LINEA_CRED_ESP)) {
          // Mapeo servicio QueryCompContHeader en CreditoVO
          credito.setFolio(Utils.formatearFolio(queryComp.getContractNumber()));
          credito.setFechaOtorg(Utils.pasaFechaSAPaWEBNulo(queryComp.getContractBegin(), " "));
          credito.setMtoCredito(Utils.quitarCerosIzq(queryComp.getContractAmount()));
          credito.setNroCuotas(Utils.quitarCerosIzq(queryComp.getInstallmentQuantity()));
          credito.setValorCuota(Utils.quitarCerosIzq(queryComp.getInstallmentAmount()));

          credito.setTasaInteres(infoCredito.getMonthlyInterestrate());
          credito.setSegCesantia(infoCredito.getUnemploymentinsur());
          credito.setSegDesgravamen(infoCredito.getLifeInsurance());

          // Pasar todos los valores a numerico para sumarlos.
          long montoNominal = stringToInt(credito.getMtoCredito())
              + stringToInt(infoCredito.getLteAmount())
              + stringToInt(infoCredito.getNotarialCharge());
          credito.setMtoNominal(String.valueOf(montoNominal));

          long montoReajustado = (stringToInt(credito.getMtoNominal())+
              stringToInt(credito.getSegCesantia())+
              stringToInt(credito.getSegVida())+
              stringToInt(credito.getSegDesgravamen())+
              stringToInt(infoCredito.getInterestAmount()));
          credito.setMtoReajustado(String.valueOf(montoReajustado));

          // obtener datos de fechas desde servicio CashFlowIn
          ClienteQueryContractCashflowIn clienteCashFlow = new ClienteQueryContractCashflowIn();
          SalidaListaQueryContractCashflowInVO salidaCashFlow = (SalidaListaQueryContractCashflowInVO) clienteCashFlow.call(mapCashflowIn(queryComp.getContractNumber()));

          credito.setFechaPrimerVenc(Utils.pasaFechaSAPaWEBNulo(salidaCashFlow.getListaCuotas().get(0).getFechaVencCuota(), " "));
          //Fecha última cuota
          credito.setFechaUltVenc(Utils.pasaFechaSAPaWEBNulo(salidaCashFlow.getListaCuotas().get(salidaCashFlow.getListaCuotas().size() - 1).getFechaVencCuota(), " "));

          int cantidadCuotasVigentes = 0;
          int cantidadCuotasPagadas = 0;

          // contar la cantidad de cuotas vigentes y pagadas.
          for (SalidaQueryContractCashflowInVO cuota : salidaCashFlow.getListaCuotas()) {
            if (cuota.getEstadoCuota().toUpperCase().equals("VIGENTE")) {
              cantidadCuotasVigentes++;
            } else {
              if (cuota.getEstadoCuota().toUpperCase().equals("CANCELADA")) {
                cantidadCuotasPagadas++;
              }
            }
          }

          credito.setCantCuotasPagadas(String.valueOf(cantidadCuotasPagadas));
          credito.setCantCuotasVigentes(String.valueOf(cantidadCuotasVigentes));

          listaCreditos.add(credito);
        }
      }//Fin for recorrido de créditos vigentes

      if(listaCreditos.size() == 0){
        log = new Log(Constantes.COD_RESPUESTA_VACIO, "El rut no tiene créditos especiales asociados");
        salidaVO.setLog(log);
        return salidaVO;
      }

    } catch (Exception e) {
      logger.error("::ConsultaCreditosEspVig: Error al obtener créditos esp. vigentes:", e);
      e.printStackTrace();
      salidaVO.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Error al obtener créditos esp. vigentes: " + e.getMessage()));
      return salidaVO;
    }
    salidaVO.setLog(new Log(Constantes.COD_RESPUESTA_SUCCESS, "Créditos especiales vigentes obtenidos correctamente"));
    salidaVO.setCreditos(listaCreditos);
    logger.info("Fin servicio Consulta Creditos Especiales.");
    return salidaVO;
  }

  /**
   * Recibe el rut del cliente y lo mapea al objeto necesario para consultar el servicio QueryCompContHeaderIn
   * @param rut
   * @return EntradaWS con el rut del cliente y FlagTipoCredito = 1
   */
  private EntradaQueryCompContHeaderInVO mapQueryCompContHeaderIn(String rut) {
    EntradaQueryCompContHeaderInVO entradaWS = new EntradaQueryCompContHeaderInVO();
    entradaWS.setRut(rut.toUpperCase());
    entradaWS.setFlagTipoCredito(ConstantesWS.ESTADO_CREDITO_VIGENTE);// Todos los creditos Vigentes.
    return entradaWS;
  }
  /**
   * Recibe el rut del cliente y el folio del credito a consultar, los cuales mapea dentro del objeto de entrada necesario
   * para consultar el servicio QueryContractHeaderIn
   * @param rut
   * @param folioCredito
   * @return entradaWS con el el rut y folio seteado
   */
  private EntradaQueryContractHeaderInVO mapQueryContract(String rut, String folioCredito) {
    EntradaQueryContractHeaderInVO entradaWS = new EntradaQueryContractHeaderInVO();
    entradaWS.setRut(rut.toUpperCase());
    entradaWS.setAcnum_ext(folioCredito);
    return entradaWS;
  }
  /**
   * Recibe el folio de un crédito SAP el cual mapea en el objeto necesario para consultar el servicio web QueryContractFlowIn
   * @param folioCredito
   * @return entradaWS con el folio del credito seteado.
   */
  private EntradaQueryContractCashflowInVO mapCashflowIn(String folioCredito) {
    EntradaQueryContractCashflowInVO entradaWS = new EntradaQueryContractCashflowInVO();
    entradaWS.setFolioCredito(folioCredito);
    return entradaWS;
  }
  /**
   * Transforma un String que contiene numero en int
   * @param numero
   * @return
   */
  private static int stringToInt(String numero){
    if(numero == null || numero.length()==0){
      return 0;
    }
    return Integer.parseInt(numero);
  }

  /**
   * Se formatean los montos en el cliente, sin separador de miles
   * Recibe un numero (en String) en formato SAP (1.000 y lo transforma al formato chileno de numeros.
   * @param numero
   * @return

  private static String formatNumber(String numero){
    if(numero != null && numero.trim().length()>0){
      DecimalFormat sapFormat = new DecimalFormat("###.###");
      numero = numero.replace(".","").replace(",",".") ;
      numero =  sapFormat.format(Double.valueOf(numero));
    }else{
      numero = "0";
    }
    return numero;
  }   */
}