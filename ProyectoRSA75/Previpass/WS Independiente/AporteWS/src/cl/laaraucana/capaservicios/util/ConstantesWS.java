package cl.laaraucana.capaservicios.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConstantesWS {
  private static ResourceBundle resource = ResourceBundle.getBundle("confServicios");
  private static ResourceBundle resourceSIM = ResourceBundle.getBundle("simulacionWeb");
  // Servicio ConsultaCreditosEspeciales
  public static final String LINEA_CRED_ESP = resource.getString("queryContractHeader.comercialLine.credEsp");
  
  //Usuario conexi�n
  public static final String USUARIO_PI=resource.getString("bs.credito.username");
  public static final String PASWORD_PI=resource.getString("bs.credito.password");
  
  //codigos de retorno para el servicio solicitud de codigo seguridad.
  public static final String SMS_CODIGO_NO_EXISTE= resource.getString("solicidudSMS.noExisteCodigo");
  public static final String SMS_CODIGO_NO_EXISTE_MSG= resource.getString("solicidudSMS.noExisteCodigo.mensaje");
  public static final String SMS_CODIGO_ACTIVO= resource.getString("solicitudSMS.codSegActivo");
  public static final String SMS_CODIGO_ACTIVO_MSG= resource.getString("solicitudSMS.codSegActivo.mensaje");
  public static final String SMS_CODIGO_ERRONEO =resource.getString("solicitudSMS.codigoErroneo");
  public static final String SMS_CODIGO_ERRONEO_MSG =resource.getString("solicitudSMS.codigoErroneo.mensaje");
  public static final String SMS_CODIGO_VENCIDO = resource.getString("solicitudSMS.codigoVencido");
  public static final String SMS_CODIGO_VENCIDO_MSG = resource.getString("solicitudSMS.codigoVencido.mensaje");
  
  //C�digos de respuesta ConsultaDatosCliente
  public static final String DATOS_CLIENTE_COD_FALTAN_DATOS = "2";

  // Servicio Simulacion
  // public static final resourceSIMBundle RES_FORMALIZAR = resourceSIMBundle.getBundle("cl.laaraucana.satelites.formalizar.formalizar");
  /*  public static final String WS_UNAVAILABLE = "Web Service no disponible";
  public static final String CONTENT_TYPE_PDF = "application/pdf";
  public static final String CONTENT_TYPE_JSON = "text/json";
  public static final int MAX_SOCIAL = Integer.parseInt(resourceSIM.getString("social.maximo"));
  public static final int MIN_SOCIAL = Integer.parseInt(resourceSIM.getString("social.minimo"));
  public static final int MIN_ESPECIAL = Integer.parseInt(resourceSIM.getString("especial.minimo"));*/

  //QueryCompContractHeader
  public static final String ESTADO_TODOS_CREDITOS = resource.getString("queryComp.getAllContractsCode");
  public static final String ESTADO_CREDITO_VIGENTE = resource.getString("queryComp.getCurrentContractsCode");

  public static final int CUOTA_UNICA_ESPECIAL = 48;

  public static final String NOM_AFILIADO = resourceSIM.getString("nombre.afiliado");
  public static final String NOM_PENSIONADO = resourceSIM.getString("nombre.pensionado");
  public static final String NOM_INDEPENDIENTE = resourceSIM.getString("nombre.independiente");

  public static final String COD_AFILIADO = resourceSIM.getString("codigo.afiliado");
  public static final String COD_PENSIONADO = resourceSIM.getString("codigo.pensionado");
  public static final String COD_INDEPENDIENTE = resourceSIM.getString("codigo.independiente");

  //constantes servicio simulacion
  public static final String TIPO_EJECUCION_SIMULAR = resourceSIM.getString("tipo.ejecucion.simular");
  public static final String TIPO_EJECUCION_CREAR_COTIZACION = resourceSIM.getString("tipo.ejecucion.crearCotizacion");
  public static final String TIPO_PRO_SIMULACION_ESPECIAL = resourceSIM.getString("tipo.producto.simulacion.especial");
  public static final String TIPO_PRO_SIMULACION_SOCIAL = resourceSIM.getString("tipo.producto.simulacion.social");

  public static final String DESPLIEGUE_ESPECIAL = resourceSIM.getString("tipo.producto.despliegue.especial");
  public static final String DESPLIEGUE_SOCIAL = resourceSIM.getString("tipo.producto.despliegue.social");

  public static final String TIPO_PRO_QUERY_SIMULATION_ESPECIAL = resourceSIM.getString("tipo.producto.simulacion.QuerySimulation.social");
  public static final String TIPO_PRO_QUERY_SIMULATION_SOCIAL = resourceSIM.getString("tipo.producto.simulacion.QuerySimulation.social");
  public static final String ORIGEN_ID = resourceSIM.getString("origen.id");

  public static final String TIPO_MONEDA_PESOS = resourceSIM.getString("tipo.moneda.pesos");
  public static final String TIPO_MONEDA_UF = resourceSIM.getString("tipo.moneda.uf");

  public static final String CANAL_VENTA = resourceSIM.getString("canal.venta");
  public static final String SECTOR_VENTA = resourceSIM.getString("sector.venta");
  public static final String SEGURO_CESANTIA = "";// Por defecto desactivado

  // Escalas de cuotas
  public static final String ESCALA_1 = resourceSIM.getString("escala.1");
  public static final String ESCALA_4 = resourceSIM.getString("escala.4");
  public static final String ESCALA_13 = resourceSIM.getString("escala.13");
  public static final String ESCALA_25 = resourceSIM.getString("escala.25");
  public static final String ESCALA_37 = resourceSIM.getString("escala.37");
  public static final String ESCALA_49 = resourceSIM.getString("escala.49");

  //nombre de usuario y contrase�a para validar el servicio de Autenticacion LDAP
  public static final String USER_AUTH = resource.getString("usuarioAuth");
  public static final String PASS_AUTH = resource.getString("passwdAuth");
  public static final String MENSAJE_AUTH_FAIL = resource.getString("msjAuthFail");
  /**
   * Retorna los tipos de afiliados en la araucana
   * 
   * @return un hashmap de tipos de afiliados
   *         Afiliado, Pensionado e Independiente
   */
  public static Map<String, String> getTipoAfiliados() {
    Map<String, String> hash = new LinkedHashMap<String, String>();
    hash.put(NOM_AFILIADO, COD_AFILIADO);
    hash.put(NOM_PENSIONADO, COD_PENSIONADO);
    hash.put(NOM_INDEPENDIENTE, COD_INDEPENDIENTE); // validar los codigos de los tipos
    return hash;
  }

  /**
   * Retorna los tipos de afiliados en la araucana para perfil empresa
   * 
   * @return un hashmap de tipos de afiliados
   *         Afiliado (Trabajador dependiente)
   */
  public static Map<String, String> getTipoAfiliadosEmpresa() {
    Map<String, String> hash = new LinkedHashMap<String, String>();
    hash.put(NOM_AFILIADO, COD_AFILIADO);
    return hash;
  }

  /**
   * Obtiene l�mites superiores de intervalos de cuotas para obtener tasas de inter�s
   * 
   * @param codigo
   * @return
   */
  public static String getTasaEscala(String codigo) {
    return resourceSIM.getString(codigo);
  }
}
