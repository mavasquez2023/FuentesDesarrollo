package cl.araucana.cp.distribuidor.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Constants 
{
	// Llave de sesion donde se almacena la configuracion actual de los menus
	public static String SESSION_KEY_MENUS = "SESSION_KEY_MENUS";
	//Llave de sesion donde se almacena el path de navegacion actual
	public static String SESSION_PATH_NAVEGACION = "SESSION_PATH_NAVEGACION";
	//accion por defecto: Tiles.do?accion=inicio
	public static String ACCION_DEFAULT = "inicio";
	public static String ACCION_ERROR = "error";

	//ruta de almacenamiento de cmps
	public static String RUTA_CMPS = "./CP.server/comprobantes/";

	//texto botones, se carga desde un properties (/files/textoBotones.properties) al inicio de la app
	public static Properties TXT_BTNS = new Properties();
	public static String MESSAGES_FILE="utiltexto";

	public static HashMap ESTADO_ENVIO = new HashMap();
	public static HashMap ESTADO_RECHAZO = new HashMap();
	public static Map ESTADO_NOMINA = new HashMap();

	//MX total de cada seccion
	public static HashMap TOTAL_MX_SECCION = new HashMap();

	//resultados validaciones y transformaciones texto
	public static int TEXTO_VACIO = 1;
	public static int TEXTO_INVALIDO = 2;
	public static int TEXTO_OK = 0;

	//primera validaciona aplicar
	public static String PRIMERA_VALIDACION = "VN020";
	public static String PRIMERA_VALIDACION_MP = "VN160";
	public static String PRIMERA_VALIDACION_APV = "VN430";
	
	public static String PRIMERA_VALIDACION_CARGASFAM = "VN940";//"VN120";
	public static String ULTIMA_VALIDACION_CARGASFAM  = "VN960";//"VN140";

	//errores proxy AS400
	public static String AS400_GENERIC_ERROR = "errorGenerico";
	public static String AS400_NOT_RUN = "errorRUN";
	public static String AS400_BAD_PARAMS = "errorPARAMS";
	public static String AS400_BAD_PROCESS = "errorPROCESS";

	//Origen de Avisos
	public static int AVISOS_TABLA_CAUSAAVISOCXP = 1;
	public static int AVISOS_TABLA_CAUSACXP = 2;
	

// grupo de convenios por defecto para copiar mapeos
	public static int ID_GRUPO_CONVENIO_DEFAULT = 0;

	public static int NUMBER_NULL = -9;

//	Tipos de pago para la tabla SECCION, columna TIPO_PAGO
	//indefinido, buscar valor un nivel mas abajo (si es seccion => en tipo_detalle)
	public static int EST_SECCION_INDEF = 0;
	public static int EST_SECCION_PAGO = 1;
	public static int EST_SECCION_DNP = 2;
	public static int EST_SECCION_NO_PAGO = 3;
	
	//Seccion DNP Informa el pago de la seccion
	/*PAGADO*/
	public static final String EST_SECCION_PAGO_DNP_P = "P";
	/*DECLARADO Y NO PAGADO*/
	public static final String EST_SECCION_PAGO_DNP = "DNP";
	/*NO PAGADO*/
	public static final String EST_SECCION_PAGO_NP = "NP";
	
	public static final String EST_SECCION_PAGO_DNP_VALOR = "Declarado";
	/*NO PAGADO*/
	public static final String EST_SECCION_PAGO_DNP_NP_VALOR = "No Pago";

	//formas de pago
	public static int FORMA_PAGO_NO_PAGO = 0;
	public static int FORMA_PAGO_SPL = 1;
	public static int FORMA_PAGO_CAJA = 2;
	public static int FORMA_PAGO_SPL_INDEPENDIENTE = 4;
	public static int FORMA_PAGO_CAJA_INDEPENDIENTE = 3;
	

	//CODIGOS HABILITACION
	public static int COD_HABILITACION_EMPRESA = 1;
	public static int COD_HABILITACION_CONVENIO = 1;
	public static int COD_HABILITACION_GRUPO_CONVENIO = 1;
	public static int COD_HABILITACION_ADMIN = 1;
	public static int COD_HABILITACION_ENCARGADO = 1;
	public static int COD_HABILITACION_LECTOR = 1;
	public static int COD_HABILITACION_DISTRIBIDOR = 1;
	public static int COD_HABILITACION_NODO = 1;

	//IDS FALSOS
	public static int RUT_EMPRESA_FALSA = 0;
	public static int ID_GRUPO_FALSO = 0;
	public static int SIN_CCAF = 0;
	public static int SIN_MUTUAL = 0;
	public static int SIN_APV = -1;
	public static int APV_INVALIDO = -111;
	public static int ENT_PAGADORA_DEFAULT = 0;
	public static int GENERO_FALSO = 0;
	public static int AFC_FALSO = 0;
	public static int EXCAJA_FALSO = 0;
	public static int ENTSIL_FALSO = 0;
	public static int CODREGIMP_FALSO = 0;
	public static int TRAMO_ASIGFAM_FALSO = 0; //(-1) NO EXISTE EN TRAMASFAM
	public static int APVC_FALSO = 1;
	public static int NODO_FALSO = 0;
	public static int BANCO_FALSO = 0;
	public static String CTA_CTE_FALSA = "0";
	public static int SIN_REGIMEN_APV = 0;

	//IDS POR DEFECTO
	public static int ID_COMUNA_DEFAULT = 0;
	public static int ID_CIUDAD_DEFAULT = 0;
	public static int ID_REGION_DEFAULT = 0;
	public static int ID_USUARIO_DEFAULT = 0;
	public static int ID_CONVENIO_DEFAULT = 1;
	public static int ID_UNSAVED_GRUPO_CONVENIO = 0;
	public static int ID_UNSAVED_MAPACOD = 0;
	public static int ID_UNSAVED_MAPANOM = 0;
	public static int ID_UNSAVED_OPCPROC = 0;
	public static int NUM_BLOQUEOS_DEFAULT = 0;
	public static int ID_OPCION_DEFAULT = 0;
	public static int ID_RUBRO_DEFAULT = 0;
	public static int ID_ACCESO_DEFAULT = 0;
	public static int ID_NOMINA_NULA = 0;

	//opciones desde convenio
	public static int OPC_CALC_MOV_PERSONAL = 0;
	public static int OPC_MUTUAL_CALC_IND = 1;
	public static int SUMAR_MONTOS_MOVIMIENTO = 1;


	//NIVEL ACCESOS ENCARGADOS
	public static int NIVEL_ACC_CONV_SUC_NADA = 0;
	public static int NIVEL_ACC_CONV_SUC_LECTOR = 1;
	public static int NIVEL_ACC_CONV_SUC_EDITOR = 2;
	//Fecha por defecto
	public static long FECHA_DEFECTO_MILESIMAS=0;
	public static long FECHA_DEFECTO_FACTIBLE = 24L*3600000;
	public static java.sql.Date FECHA_DEFECTO_SQL = new java.sql.Date(FECHA_DEFECTO_MILESIMAS);

	//PROPERTIES MAPEO
	//TODO poperties mapeo 1 no se usa??
	public static int PROP_MAPEO_SECC_TP = 2; //secciones por tipo de proceso: ["RAFP", 1]
	public static int PROP_MAPEO_MX_SECC = 3; //mXX por seccion: ["1previsionAdicional", "Cotizacion Voluntaria"]
	public static int PROP_MAPEO_MX_LEYENDA = 4; //leyendas por seccion: [1, "Incluye montos por pago de Seguro de Cesantia de trabajadores de INP"]

	//DETALLES REPORTES
	public static int DET_REPORTE_HTML = 1;
	public static int DET_REPORTE_PDF = 2;
	public static int DET_REPORTE_SABANA = 3;

	//niveles error
	public static int NIVEL_VAL_ERROR = 0;
	public static int NIVEL_VAL_AVISO = 1;
	public static int NIVEL_VAL_NA = 2;	

	public static String VALOR_MAPEO_DEFECTO = "";

	//TIPOS DE NOMINA
	public static final char TIPO_NOM_REMUNERACION = 'R';
	public static final char TIPO_NOM_RELIQUIDACION = 'A';
	public static final char TIPO_NOM_GRATIFICACION = 'G';
	public static final char TIPO_NOM_DEPOSITOCONVENIDO = 'D';
	
	/*TIPOS DE SECCION
	 * Son utilizados para generar los comprobantes y las sabanas
	 * ej. 	if(seccion.equals(Constants.TIPO_SEC_INP))
	 * 			add....
	 * 		else
	 * 			add....	
	 */		
	
	public static final String TIPO_SEC_INP = "INP";
	public static final String TIPO_SEC_APV = "APV";
	public static final String TIPO_SEC_AFP = "AFP";
	public static final String TIPO_SEC_APVC = "APVC";
	public static final String TIPO_SEC_CAJA = "CAJA";
	public static final String TIPO_SEC_MUTUAL = "MUTUAL";	
	public static final String TIPO_SEC_ISAPRE = "ISAPRE";
	public static final String TIPO_SEC_OTROS = "OTROS";

	//tipos de seccion de un comprobante
	public static int TIPO_SECCION_AFP = 1;
	public static int TIPO_SECCION_AFP_RELIQUIDACION = 20;
	public static int TIPO_SECCION_AFP_GRATIFICACION = 40;	
	public static int ID_TIPO_SECCION_INP = 3;
	public static int ID_TIPO_SECCION_CAJA = 5;
	public static int mxCCAF = 6; //identificacion de posicion CCAF/MUTUAL dentro de INP
	public static int mxMUTUAL = 3;

	/*NOMBRES DE COLUMNAS
	 * Son utilizados para la identificacion de columnas 
	 * del detalle de una seccion en la sabana,
	 * ej. 	if(detalle.getNombre(i).equals(Constants.COLUMNA_remuneracionImponible))
	 * 			posCol = i;	
	 */
	public static final String COLUMNA_subTotalCot = "Sub Total Cotizaciones";
	public static final String COLUMNA_subTotalReb = "Sub Total Rebajas";
	public static final String COLUMNA_total = "Total";
	public static final String COLUMNA_cotizacionObligatoria = "Cotizacion Obligatoria";
	public static final String COLUMNA_sis = "SIS";	//asepulveda 18-06-2010
	public static final String COLUMNA_cuentaAhorro = "Cuenta de Ahorro";
	public static final String COLUMNA_Pension = "Pension";
	public static final String COLUMNA_remuneracionImponible = "Remuneracion Imponible";
	public static final String COLUMNA_totalAFC = "Total AFC";
	public static final String COLUMNA_ley15386 = "Ley 15.386";

    // NUMERO APVS POR COTIZANTE
	public static final int nAPVs_COTIZANTE = 5;
	
	// Numero tope en BD
	public static final int TOPE_TOTAL_BD = 99999999;
	
	//numero maximo filas comprobante por hoja
	public static final int MAX_FILAS_COMPROBANTE = 52;
	
	//numero aproximado de filas pie de comprobante
	public static final int MAX_FILAS_PIE = 11;
	
	//Cantidad Máxima de días por mes (Asepulveda)
	public static final int MAX_DIAS_MES = 31;
	
	//Tipo de Prevision
	public static final int TIPO_PREVISION_NINGUNA = 0;
	public static final int TIPO_PREVISION_AFP = 1;
	public static final int TIPO_PREVISION_INP = 2;

	//MENSAJES ESTADISTICAS
	//mensaje a mostrar cuando se pide una estadistica, y no ocurre ningun problema
	public static final String MSG_STATS_OK = " ";
	//mensaje a mostrar cuando se pide una estadistica, y ocurre algun problema
	public static final String MSG_STATS_ERROR = "Error al obtener estadisticas";
	//mensaje a mostrar cuando se pide una estadistica, y ocurre algun problema
	public static final String MSG_STATS_ERROR_CONN = "Error al conectarse al nodo";

	//mensaje a mostrar cuando se refrescan parametros
	public static final String MSG_REFRESH_PARAM_OK = " ";
	public static final String MSG_REFRESH_PARAM_ERROR = "No ha sido posible refrescar parámetros.";

	//ids de tipos de movimientos que requieren entidad SIL, para movimiento personal y movimiento personal voluntario
	public static final int TIPO_MOVTO_SIL = 3;
	public static final int TIPO_MOVTOAF_SIL = 2;

	//números de movimientos permitidos, para movimiento personal y movimiento personal voluntario
	public static final int NUM_MAX_MOVTO = 9;
	public static final int NUM_MAX_MOVTOAF = 4;

	public static final int TIPO_MOVTO_SINMVTO = 0;
	public static final int TIPO_MOVTO_CESACION = 2;


	/**
	 * 
	 * Identificadores de tabla parametros, estos SIEMPRE deben mantenerse sincronizados
	 *
	 */
	public static final int PARAM_PERIODO = 1;
	public static final int PARAM_PERIODO_INDEPENDIENTE = 138;
	public static final int PARAM_ESTADO = 2;//3,4 no existe
	public static final int PARAM_UF_ACTUAL = 5;
	public static final int PARAM_UF_ANTERIOR = 6;
	public static final int PARAM_NUM_MAX_REGISTROS = 7;
	public static final int PARAM_DIAS_TOPE_ASIGFAM = 8;//9 no existe
	public static final int PARAM_TOPE_AFP = 10;
	public static final int PARAM_TOPE_INP = 11;
	public static final int PARAM_TOPE_INDEMNIZACION = 12;
	public static final int PARAM_TOPE_CESANTIA = 13;
	public static final int PARAM_TOPE_APV = 14;
	public static final int PARAM_MIN_TRAB_PESADO = 15;
	public static final int PARAM_MAX_TRAB_PESADO = 16;
	public static final int PARAM_MIN_TASA_INDEM = 17;
	public static final int PARAM_MAX_TASA_INDEM = 18;
	public static final int PARAM_NUM_MAX_ERRORES = 19;
	public static final int PARAM_DIAS_X_MES = 20;
	public static final int PARAM_FACTOR_REMU = 21;
	public static final int PARAM_FACTOR_GRATI = 22;
	public static final int PARAM_FACTOR_RELI = 23;
	public static final int PARAM_FACTOR_DEPO = 24;
	public static final int PARAM_CRIT_DISTRIBUCION_1 = 25;
	public static final int PARAM_CRIT_DISTRIBUCION_2 = 26;//27 servidorFTP
	public static final int PARAM_TIEMPO_SESSION_CLIENTE = 28;
	public static final int PARAM_COD_SISTEMA_SPL = 29;
	public static final int PARAM_CLAVE_SPL = 30;
	public static final int PARAM_BANCOS_SPL = 31;
	public static final int PARAM_URL_SPL = 32;
	public static final int PARAM_INT_TES_URL_SISTEMA = 33;
	public static final int PARAM_INT_TES_USER_CONN = 34;
	public static final int PARAM_INT_TES_PASS_CONN = 35;
	public static final int PARAM_INT_TES_LIB_CREA_FOLIO = 36;//37 codigoBarras
	public static final int PARAM_INT_TES_SUC_EMP_LCF = 38;
	public static final int PARAM_INT_TES_COD_OF_LCF = 39;
	public static final int PARAM_INT_TES_COD_AREA_NEG_LCF = 40;//41 libLibroBanco no se usa, repetido en 42
	public static final int PARAM_INT_TES_LIB_UP_LIBRO_BCO = 42;
	public static final int PARAM_URL_RETORNO_SPL = 43;
	public static final int PARAM_URL_NOTIFICACION_SPL = 44;
	//TODO GMALLEA 25-04-2012 Se agrega la notoficaciones del pago en linea para los Independiente
	public static final int PARAM_URL_RETORNO_SPL_INDEPEMDIENTE = 123;
	public static final int PARAM_URL_NOTIFICACION_SPL_INDEPENDIENTE = 124;
	
	public static final int PARAM_APORTE_CCAF_FONASA = 45;
	public static final int PARAM_NUM_M = 46;
	public static final int PARAM_TASA_FIJA = 47;//48 publisherPDF no se usa, repetido 51
	public static final int PARAM_DOC_TYPE_NAME_PDF = 49;
	public static final int PARAM_ID_LOG_PDF = 50;
	public static final int PARAM_PUBLISHER_NAME = 51;//52 no existe
	public static final int PARAM_TOPE_GRATI = 53;
	public static final int PARAM_AP_TRAB_IND_SEG_CES = 54;
	public static final int PARAM_AP_TRAB_PF_SEG_CES = 55;
	public static final int PARAM_AP_EMP_PF_SEG_CES = 56;
	public static final int PARAM_AP_EMP_IND_SEG_CES = 57;
	public static final int PARAM_FIN_EDICION_NOM = 58;
	public static final int PARAM_FIN_PAGO_CAJA = 59;
	public static final int PARAM_FIN_PAGO_LINEA = 60;	
	public static final int PARAM_FIN_PAGO_CAJA_IND = 131;
	public static final int PARAM_FIN_PAGO_LINEA_IND = 132;	
	public static final int PARAM_USER_GENERA_FOLIO = 61;
	public static final int PARAM_LOGGEAR_VALIDACIONES = 62;
	public static final int PARAM_MAIL_TO = 63;
	public static final int PARAM_MAIL_HOST_LOCAL = 64;
	public static final int PARAM_MAIL_FROM = 65;
	public static final int PARAM_MAIL_USER = 66;
	public static final int PARAM_MAIL_PASS = 67;
	public static final int PARAM_MAIL_HOST_TO = 68;
	public static final int PARAM_LINK_CONTACTENOS = 69;
	public static final int PARAM_LINK_HOME = 70;
	public static final int PARAM_REINICIO_FOLIOS = 71;
	public static final int PARAM_LIB_ANULA_FOLIO = 72;
	public static final int PARAM_EST_RECH_EXITOSO = 73;
	public static final int PARAM_EST_RECH_DUPLICADO = 74;
	public static final int PARAM_EST_RECH_REENVIO = 75;
	public static final int PARAM_EST_RECH_DATA_CORRUPTA = 76;
	public static final int PARAM_EST_RECH_RECEPCION = 77;
	public static final int PARAM_EST_RECH_ID_INVALIDO = 78;
	public static final int PARAM_EST_RECH_RUT_INVALIDO = 79;
	public static final int PARAM_EST_RECH_TIPO_INVALIDO = 80;
	public static final int PARAM_EST_RECH_CONV_INVALIDO = 81;
	public static final int PARAM_EST_RECH_VACIA = 82;
	public static final int PARAM_EST_RECH_SIN_FORMATO = 83;
	public static final int PARAM_TC_RUT_REPETIDO = 84;
	public static final int PARAM_TC_MOVTO_PERSO_REQUERIDO = 85;
	public static final int PARAM_ID_INP = 86;
	public static final int PARAM_ID_AFP_NINGUNA = 87;
	public static final int PARAM_ID_FONASA = 88;
	public static final int PARAM_ID_SALUD_NINGUNA = 89;
	public static final int PARAM_ID_TIPO_DET_ARAUCANA = 90;
	public static final int PARAM_ID_TRAMO_ASIG_FAM_NINGUNO = 91;
	public static final int PARAM_EST_CMP_POR_PAGAR = 92;
	public static final int PARAM_EST_CMP_PARCIAL = 93;
	public static final int PARAM_EST_CMP_PAGADO = 94;
	public static final int PARAM_EST_CMP_PRECURSADO = 95;
	public static final int PARAM_EST_NOM_NO_ENVIADA = 96;
	public static final int PARAM_EST_NOM_PROCESO = 97;
	public static final int PARAM_EST_NOM_ERRORES = 98;
	public static final int PARAM_EST_NOM_POR_PAGAR = 99;
	public static final int PARAM_EST_NOM_PARCIAL = 100;
	public static final int PARAM_EST_NOM_PAGADA = 101;
	public static final int PARAM_EST_NOM_PRECURSADA = 102;
	public static final int PARAM_EST_NOM_NO_CARGADA = 103;
	public static final int PARAM_NUM_PAG_CLIENTE = 104;
	public static final int PARAM_NUM_REG_PAG_CLIENTE = 105;
	public static final int PARAM_NUM_PAG_ADMIN = 106;
	public static final int PARAM_NUM_REG_PAG_ADMIN = 107;
	public static final int PARAM_NIVEL_ACC_NADA = 108;
	public static final int PARAM_NIVEL_ACC_LECTOR = 109;
	public static final int PARAM_NIVEL_ACC_ESCRITOR = 110;
	public static final int PARAM_TOLERANCIA_PESO = 111;
	public static final int PARAM_INST_JRE_WIN = 112;
	public static final int PARAM_INST_JRE_LINUX = 113;
	public static final int PARAM_TOPE_MUTUAL = 114;
	public static final int PARAM_MAIL_PORT = 116; //115 no se usa
	public static final int PARAM_PRIMER_PERIODO_VIG_PLENA_SIS = 117;
	public static final int PARAM_MINIMO_TRABAJADORES_SIS = 118;
	public static final int PARAM_URL_MIS_PLANILLAS = 119;
	public static final int PARAM_CANT_LINEAS = 120;
	public static final int PARAM_URL_MIS_PLANILLAS_INDEPENDIENTE = 122;
	public static final int ACTIVO_VALIDACION_SIS_EMPRESA = 125;
	public static final int ACTIVO_VALIDACION_SIS_INDEPENDIENTE = 134;
	
	
	
	/**************************
	 *
	 * 
	 * 
	 * PARAMETROS CARGADOS DESDE DB CUANDO SE LEVANTA LA APP
	 * 
	 * 
	 * 
	 **************************/
	public static float TASA_FIJA_MUTUAL = 0;
	public static Set TIPOS_SECCION_CAAF;

	//estado envio exitoso
	public static int EST_ENVIO_EXITOSO = 0;

	//MOTIVOS DE RECHAZO DE ENVIOS (TABLA RECHAZO)
	public static int EST_RECH_EXITOSO = 0;
	public static int EST_RECH_DUPLICADA = 1;
	public static int EST_RECH_REENVIO = 2;
	public static int EST_RECH_DATA_CORRUPTA = 3;
	public static int EST_RECH_RECEPCION = 4;
	public static int EST_RECH_ID_INVALIDO = 5;
	public static int EST_RECH_RUT_INVALIDO = 6;
	public static int EST_RECH_TIPO_INVALIDO = 7;
	public static int EST_RECH_CONV_INVALIDO = 8;
	public static int EST_RECH_VACIA = 9;
	public static int EST_RECH_SIN_FORMATO = 10;
	public static int EST_RECH_NOMINA_PAGADA = 11;
	public static int EST_RECH_OBTENCION_INFO_ARCHIVO = 12;
	public static int EST_RECH_REGISTRO_EN_BD = 13;
	public static int EST_RECH_OBTENCION_ARCHIVO = 14;
	public static int EST_RECH_NOMINA_EN_PROCESO = 15;
	public static int EST_RECH_SIN_PERMISOS = 16;
	public static int EST_RECH_EMP_DESHABILITADA = 17;
	public static int EST_RECH_CONV_DESHABILITADO = 18;
	public static int EST_RECH_USU_DESHABILITADO = 19;
	public static int EST_RECH_PERIODO_INCORRECTO = 20;
	public static int EST_RECH_RUT_MAL_MAPEADO = 21;

	//codigo error cotizante repetido
	public static int TIPO_CAUSA_RUT_REPETIDO = 102;
	//codigo error sin movto personal, pero numDiasTrabajados < diasXMes
	public static int TIPO_CAUSA_MOVTO_PERSO_NECESARIO = 314;
	//codigo error comprobante con total menor a 0
	public static int TIPO_CAUSA_TOTAL_COMPROBANTE = 6;
	//Error al guardar Cotizante
	public static int TIPO_CAUSA_COTIZANTE_NO_GUARDADO = 7;
	//Error al guardar convenio
	public static int TIPO_CAUSA_CONVENIO_NO_GUARDADO = 8; 
	//informa entidad SIL, y no la necesita
	public static int TIPO_CAUSA_ENTSIL_SOBRA = 343; 

	//IDS ENTIDADES ESPECIALES
	public static int ID_INP = 0;
	public static int ID_AFP_NINGUNA = -100;
	public static int ID_SIN_AFP = -1;
	public static int ID_FONASA = 0;
	public static int ID_SALUD_NINGUNA = -1;
	public static int ID_TIPO_DETALLE_ARAUCANA = 1;
	public static int TRAMO_ASIGFAM_NINGUNO = 0;
	public static int TRAMO_ASIGFAM_D = 4;
	public static int TRAMO_ASIGFAM_NO_ENCONTRADO = -111;
	public static int ID_EXCAJA = -1;
	public static int ID_AFBR = 1;

	//estados de comprobantes
	public static int EST_CMP_POR_PAGAR = 3;
	public static int EST_CMP_PAGO_PARCIAL = 4;
	public static int EST_CMP_PAGADO = 5;
	public static int EST_CMP_PRECURSADO = 8;
	public static final int EST_CMP_PREPAGADO = 9;

	//estados nomina
	public static int EST_NOM_NO_ENVIADA = -1; 
	public static int EST_NOM_EN_PROCESO = 1; 
	public static int EST_NOM_CON_ERRORES = 2; 
	public static int EST_NOM_POR_PAGAR = 3; 
	public static int EST_NOM_PAGADO_PARCIALMENTE = 4; 
	public static int EST_NOM_PAGADO = 5;
	public static int EST_NOM_PRECURSADA = 6;
	public static int EST_NOM_NO_CARGADA = 7; //recibida, pero con errores en procesamiento/validaciones	
	public static int EST_NOM_EN_EJB = 8; //algun nodo de procesamiento ha dado inicio
	public static int EST_NOM_CREADA_EN_LINEA = 9; // NOMINA_EN_LINEA
	public static int EST_NOM_NO_PROCESADA = 10; // Para cuando no existen nodos habilitados.
	public static int EST_NOM_PREPAGADA = 11; // PREPAGAR.

	//PAGINACION
	public static int NUM_PAG_CL = 6;
	public static int NUM_REG_PAG_CL = 15;
	public static int NUM_PAG_ADMIN = 6;
	public static int NUM_REG_PAG_ADMIN = 15;

	public static int CAIDA_SISTEMA  = 5;
	public static int CONCEPTOS_SIN_VALOR  = 3;
	public static int SIN_CONCEPTOS  = 2;
	public static int INICIO_COD_ERROR_GENERICO  = 500;
	public static int INICIO_COD_ERROR_VACIO  = 700;

	public static int CANTIDAD_MAXIMA_REGISTROS_ERROR = 100;
	
	//Tipo de separador de datos para la carga de nóminas
	public static int TIPO_SEPARADOR_POSICION = 1;
	public static int TIPO_SEPARADOR_CARACTER = 2;
	
	//ID de la tabla TIPO_SECCION para Depósito Convenido
	public static int SECCION_DEPOSITOCONVENIDO = 60;

	//Identificador del concepto, en la tabla CONCEPTO
	public static int CONCEPTO_MES = 1;
	public static int CONCEPTO_RUT = 2;
	public static int CONCEPTO_APELLIDOS = 4;
	public static int CONCEPTO_ENTIDAD_EXCAJA = 9;
	public static int CONCEPTO_REGIMEN_IMPOSITIVO = 10;
	public static int CONCEPTO_RUT_DEP_AFPV = 110;
	public static int CONCEPTO_APELLIDOS_DEP_AFPV = 130;
	

	public static String NOMINA_R = "Remuneración";
	public static String NOMINA_G = "Gratificación";
	public static String NOMINA_A = "Reliquidación";
	public static String NOMINA_D = "Depósito Convenido";

	//Identificadores de los Tipos de Carga Familiar
	public static int TIPO_CARGA_SIMPLE    = 1;
	public static int TIPO_CARGA_MATERNAL  = 2;
	public static int TIPO_CARGA_INVALIDEZ = 3;

	public static int ID_CCAF_TODAS    = -1;
	public static int ID_CCAF_SINCAJA  = 0;
	public static int ID_CCAF_LOSANDES = 1;

	public static Integer[] AVISOS_CARGASFAM = new Integer[] {new Integer(363), new Integer(364), new Integer(365), new Integer(900), new Integer(901), new Integer(902), new Integer(903), new Integer(904), new Integer(905)};
	
	public static int CONCEPTO_OBLIGATORIO = 1;

	public static String DETALLE_SECCION_MUTUAL = "MUTUAL";
	
	public static String DETALLE_SECCION_CAJA = "CAJA";
		
	public static int CANT_COLUM_SECCION_PDF_COMPROBANTE = 6;
	
	public static int ERROR_CARGAFAM_EN_OTRA_CAJA = 904;
	public static int GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR = 121;
	
	public static int GRUPO_CONV_TRAB_INDEPENDIENTE_ID = 1;
	
	public static String TIPO_EMPRESA_INDEPENDIENTE = "INDEPENDIENTE";
	
	public static String TIPO_EMPRESA = "EMPRESA";
	
	public static String GRUPO_CONV_PRODUCTOS_CAJA = "PRODUCTOS_CAJA";
	
	/*
	 * INICIO ONDEMAND
	 */
	/** Ruta remota para carga de archivos de pdfs para ser cargados posteriormente en ondemand */
    public static final String REMOTE_FOLDER_ONDEMAND = "/reports/Declaracion/";
    
    /** Nombre Archivo de ï¿½ndices */
    public static final String INDICE_ONDEMAND = "indice.ind";
    
    /** Servididor Ondemand */
    public static final String SERVIDOR_ONDEMAND = "QUSROND";
    
    /** Usuario Ondemand */
    public static final String USUARIO_ONDEMAND = "QONDADM";
    
    /** Password Ondemand */
    public static final String PASSWORD_ONDEMAND = "QONDADM";
    
    /** Nombre Grupo Aplicaciones */
    public static final String GRUPO_APLICACIONES = "Comprobantes Declaracion y No Pago";
    
    /** Nombre Aplicacion */
    public static final String APLICACION = "Comprobantes Declaracion y No Pago";
    
    public static final int ENTIDAD_PAGADORA_INP = 61533000;
    /*
	 * FIN ONDEMAND
	 */
    
    /*
     * PORCENTAJE VALOR FONASA
     * 
     */
    /**Porcentaje fonasa sin caja  */
    public static final float PORCENTAJE_FONASA_SIN_CAJA = 7;
    
    /**Porcentaje fonasa con caja  */
    public static final float PORCENTAJE_FONASA_CON_CAJA = new Float(6.4).floatValue();
    
    public static final int PARAM_MAIL_INFORME_PAGO = 126;
    
    public static final int PARAM_MAIL_PAGO_BY_SPL = 136;
    
    public static final int PARAM_URL_WS_INFORMAR_PAGO = 127;
    
    public static String URL_WS_INFORMAR_PAGO = "/AporteWS/services/OrqOutputServiceImpl";
    
    public static final int PARAM_URL_WS_OBTENER_PAGO = 133;
    
    public static String URL_WS_OBTENER_PAGO = "/AporteWS/services/OrqInputServiceImpl";
    
    /**  CONCEPTO_TESORERIA  APLICA PARA  */
    public static String CONCEPTO_TESORERIA_APLICA_PARA_IND = "INDEPENDIENTE";
    
    public static String CONCEPTO_TESORERIA_APLICA_PARA_EMPRESA = "EMPRESA";
    
    public static String CONCEPTO_TESORERIA_APLICA_PARA_AMBOS = "AMBOS";
	
    public static String TIPO_PAGO_OBLIGATORIO= "OBLIGATORIO";
		
    public static String TIPO_PAGO_VOLUNTARIAS = "VOLUNTARIAS";
    
    public static String NUM_DIA_TRABAJADO = "30";
    
    public static final int PARAM_TIME_OUT_OBT_PAGO = 137;

    public static String URL_WS_MAIL_SIS = "/WSConsultaMail/services/ConsultaMail";
    
    public static final int PARAM_URL_WS_MAIL_SIS = 157;
    
    public static char REGIMEN_APV_A = 'A';
    
    public static char REGIMEN_APV_B = 'B';
}