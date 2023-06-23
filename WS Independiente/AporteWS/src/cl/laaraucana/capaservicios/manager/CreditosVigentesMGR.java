package cl.laaraucana.capaservicios.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoDetalleBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;
import cl.laaraucana.capaservicios.util.ConstantesWS;
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

public class CreditosVigentesMGR {

  protected Logger logger = Logger.getLogger(this.getClass());
  private int totalMonto=0;
  /**
   * Funcion que recibe el rut de un cliente y obtiene de distintos servicios web el/los creditos especiales
   * que tenga aprobado.
   * @param rut
   * @return
   */
  public Object getCreditosVigentes(String rut) {
    logger.info("Inicia proceso para obtener creditos vigentes");
    CreditoCallBO creditoCallBO = new CreditoCallBO();
	CreditoResultBO creditoBO = new CreditoResultBO();
	String creditoGlsError="";
    int creditoCodError=0;
  /*  
    ConsultaCreditosEspVigOut salidaVO = new ConsultaCreditosEspVigOut();
    Log log = null;
    //Validar datos de entrada
    if(!RutUtil.IsRutValido(rut)){
      salidaVO.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese un rut válido"));
      return salidaVO;
    }

    List<CreditoVO> listaCreditos = new ArrayList<CreditoVO>();
    CreditoVO credito = null;
*/
    // Instanciar clientes de servicios web necesarios.
    ClienteQueryCompContHeaderIn clienteQueryComp = new ClienteQueryCompContHeaderIn();
    ClienteQueryContractHeaderIn clienteQueryContract = new ClienteQueryContractHeaderIn();
    // ClienteQueryContractCashflowIn clienteQueryCashflow = new ClienteQueryContractCashflowIn();

    SalidaListaQueryCompContHeaderInVO salidaQueryCompContHeader;
    SalidaQueryContractHeaderInVO infoCredito;

    try {

    	SqlMapClient sqlMap = null;
    	sqlMap = cl.laaraucana.capaservicios.config.ConfiguracionSqlMap.cargarSqlMap();
    	String periodo= (String)sqlMap.queryForObject("querys.parametro_indep");
    	periodo= periodo.replaceAll("\"", "").trim();
    	int mesdiafin= Utils.getUltimoDiaMes(periodo);
    	String fecha_venc_indep= periodo +  mesdiafin;
    	
    	salidaQueryCompContHeader = (SalidaListaQueryCompContHeaderInVO) clienteQueryComp.call(mapQueryCompContHeaderIn(rut));
    	if(salidaQueryCompContHeader.getListaCreditos().size()==0){
    		creditoGlsError+= salidaQueryCompContHeader.getMensaje() + "\n";
    		//Log log = new Log(Constantes.COD_RESPUESTA_VACIO, "El rut no tiene créditos asociados");
    		creditoGlsError+= "El rut no tiene créditos asociados";
    	}

      List totalCuotas= new ArrayList();
      for (SalidaQueryCompContHeaderInVO queryComp : salidaQueryCompContHeader.getListaCreditos()) {
        //credito = new CreditoVO();
        // obtener datos restantes desde QueryContractHeaderIn
    	  infoCredito = (SalidaQueryContractHeaderInVO) clienteQueryContract.call(mapQueryContract(rut, queryComp.getContractNumber()));

    	  // obtener datos de fechas desde servicio CashFlowIn
    	  ClienteQueryContractCashflowIn clienteCashFlow = new ClienteQueryContractCashflowIn();
    	  SalidaListaQueryContractCashflowInVO salidaCashFlow = (SalidaListaQueryContractCashflowInVO) clienteCashFlow.call(mapCashflowIn(queryComp.getContractNumber()));

    	  //credito.setFechaPrimerVenc(Utils.pasaFechaSAPaWEBNulo(salidaCashFlow.getListaCuotas().get(0).getFechaVencCuota(), " "));
    	  //Fecha última cuota
    	  //credito.setFechaUltVenc(Utils.pasaFechaSAPaWEBNulo(salidaCashFlow.getListaCuotas().get(salidaCashFlow.getListaCuotas().size() - 1).getFechaVencCuota(), " "));

    	  int cantidadCuotasVigentes = 0;
    	  int cantidadCuotasPagadas = 0;
    	  // contar la cantidad de cuotas vigentes y pagadas.
    	  for (SalidaQueryContractCashflowInVO cuota : salidaCashFlow.getListaCuotas()) {
    		  if (cuota.getEstadoCuota().toUpperCase().equals("VIGENTE") || cuota.getEstadoCuota().toUpperCase().equals("MOROSO")) {
    			  String fecha_venc= cuota.getFechaVencCuota().replaceAll("-", "");
    			  if(Integer.parseInt(fecha_venc)<= Integer.parseInt(fecha_venc_indep)){
    				  CreditoDetalleBO creditoDetalle = new CreditoDetalleBO();            	
    				  String ofi_folio= Utils.formatearFolio(queryComp.getContractNumber());
    				  String par[]= ofi_folio.split("-");
    				  creditoDetalle.setCodigoOficina(Integer.parseInt(par[0]));
    				  creditoDetalle.setFolioCredito(Integer.parseInt(par[1]));
    				  creditoDetalle.setNumCuota(Integer.parseInt(cuota.getNroCuota().trim()));
    				  creditoDetalle.setTotalCoutas(Integer.parseInt(queryComp.getInstallmentQuantity().trim()));
    				  creditoDetalle.setEstadoCouta(getCogidoEstadoCuota(cuota.getEstadoCuota().trim()));
    				  creditoDetalle.setFechaVencimiento(Integer.parseInt(cuota.getFechaVencCuota().replaceAll("-", "")));
    				  creditoDetalle.setLineaCredito(Integer.parseInt(salidaCashFlow.getLineaComercial()));

    				  creditoDetalle.setCapital(Utils.stringToint(cuota.getMontoCapital()));
    				  creditoDetalle.setSeguros(Utils.stringToint(cuota.getMontoServAdic()));
    				  creditoDetalle.setIntereses(Utils.stringToint(cuota.getMontoInteres()));
    				  creditoDetalle.setGravamenes(Utils.stringToint(cuota.getMontoGravamenes()));
    				  creditoDetalle.setValorCouta(Utils.stringToint(cuota.getTotalCuota()));
    				  totalMonto+= creditoDetalle.getValorCouta();
    				  creditoDetalle.setMultas(0);	
    				  creditoDetalle.setMontoAbonado(Utils.stringToint(cuota.getMontoAbono()));
    				  creditoDetalle.setMontoDescuento(0);
    				  totalCuotas.add(creditoDetalle);
    			  }
    		  }
    	  }


    	  //credito.setCantCuotasPagadas(String.valueOf(cantidadCuotasPagadas));
    	  //credito.setCantCuotasVigentes(String.valueOf(cantidadCuotasVigentes));

    	  //listaCreditos.add(credito);
      }//Fin for recorrido de créditos vigentes
      CreditoDetalleBO [] creditoDetalleBO= new CreditoDetalleBO[totalCuotas.size()];
      int i=0;
      for (Iterator iterator = totalCuotas.iterator(); iterator.hasNext();) {
    	  CreditoDetalleBO credito = (CreditoDetalleBO) iterator.next();
    	  creditoDetalleBO[i]= credito;
    	  i++;
      }
      
      creditoBO.setCreditoDetalle(creditoDetalleBO);
      creditoBO.setNumRegistros(creditoDetalleBO.length);
      creditoBO.setMonto(totalMonto);

      creditoCallBO.setCredito(creditoBO);
      creditoCallBO.setCodError(creditoCodError);
      creditoCallBO.setGlsError(creditoGlsError);
		

    } catch (Exception e) {
      logger.error("::ConsultaCreditosEspVig: Error al obtener créditos esp. vigentes:", e);
      e.printStackTrace();
    }
    logger.info("Fin servicio Consulta Creditos Especiales.");
    return creditoCallBO;
  }
  
  public static int getCogidoEstadoCuota(String estado){
	  if(estado.equals("MOROSO")){
		  return 2;
	  }else if(estado.equals("VIGENTE")){
		  return 1;
	  }
	  return 0;
  }
  
  public static int getAAAAMMDD(Date date ) 
  { 
   Calendar cal = null;
   String salida=null;
   try {   
    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    cal=Calendar.getInstance();
    cal.setTime(date);
    salida = formatter.format(cal); 
    return Integer.parseInt(salida);
    }
    catch (Exception e)
    {
        System.out.println("Exception :"+e);
        return 20000101;
    }  
    
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