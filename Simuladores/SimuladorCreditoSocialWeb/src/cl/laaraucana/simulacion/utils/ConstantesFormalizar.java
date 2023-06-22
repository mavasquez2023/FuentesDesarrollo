package cl.laaraucana.simulacion.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.OficinaGastosSingleton;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaVO;
import cl.laaraucana.simulacion.xml.Comuna;
import cl.laaraucana.simulacion.xml.DatosComunas;
import cl.laaraucana.simulacion.xml.Region;
import cl.laaraucana.simulacion.xml.Regiones;

public class ConstantesFormalizar {
	private static ResourceBundle resource = ResourceBundle.getBundle("simulacionWeb");

	public ConstantesFormalizar() {
	}
	//public static final ResourceBundle RES_FORMALIZAR = ResourceBundle.getBundle("cl.laaraucana.satelites.formalizar.formalizar");
	public static final String WS_UNAVAILABLE = "Web Service no disponible";
	public static final String CONTENT_TYPE_PDF = "application/pdf";
	public static final String CONTENT_TYPE_JSON = "text/json";
	public static final int MAX_SOCIAL = Integer.parseInt(resource.getString("social.maximo"));
	public static final int MIN_SOCIAL = Integer.parseInt(resource.getString("social.minimo"));
	public static final int MAX_UNIVERSAL = Integer.parseInt(resource.getString("universal.maximo"));
	public static final int MIN_UNIVERSAL = Integer.parseInt(resource.getString("universal.minimo"));
	public static final int MIN_ESPECIAL = Integer.parseInt(resource.getString("especial.minimo"));
	public static final int CUOTA_UNICA_ESPECIAL = 48;
	public static final String NOM_AFILIADO = resource.getString("nombre.afiliado");
	public static final String NOM_PENSIONADO = resource.getString("nombre.pensionado");
	public static final String NOM_INDEPENDIENTE = resource.getString("nombre.independiente");
	public static final String COD_AFILIADO = resource.getString("codigo.afiliado");
	public static final String COD_PENSIONADO = resource.getString("codigo.pensionado");
	public static final String COD_INDEPENDIENTE = resource.getString("codigo.independiente");
	public static final String TIPO_EJECUCION_SIMULAR = resource.getString("tipo.ejecucion.simular");
	public static final String TIPO_EJECUCION_CREAR_COTIZACION = resource.getString("tipo.ejecucion.crearCotizacion");
	public static final String TIPO_PRO_SIMULACION_ESPECIAL = resource.getString("tipo.producto.simulacion.especial");
	public static final String TIPO_PRO_SIMULACION_SOCIAL = resource.getString("tipo.producto.simulacion.social");
	public static final String DESPLIEGUE_ESPECIAL = resource.getString("tipo.producto.despliegue.especial");
	public static final String DESPLIEGUE_SOCIAL = resource.getString("tipo.producto.despliegue.social");
	public static final String TIPO_PRO_QUERY_SIMULATION_ESPECIAL = resource.getString("tipo.producto.simulacion.QuerySimulation.social");
	public static final String TIPO_PRO_QUERY_SIMULATION_SOCIAL = resource.getString("tipo.producto.simulacion.QuerySimulation.social");
	public static final String ORIGEN_ID = resource.getString("origen.id");
	public static final String TIPO_MONEDA_PESOS = resource.getString("tipo.moneda.pesos");
	public static final String TIPO_MONEDA_UF = resource.getString("tipo.moneda.uf");
	public static final String CANAL_VENTA = resource.getString("canal.venta");
	public static final String SECTOR_VENTA = resource.getString("sector.venta");
	public static final String LINEA_PRIORIDAD1 = resource.getString("linea.preAprobado.prioridad1");
	public static final String LINEA_PRIORIDAD2 = resource.getString("linea.preAprobado.prioridad2");
	public static final String COD_SIMULADOR_SOCIAL = resource.getString("simulador.social");
	public static final String COD_SIMULADOR_PREAPROBADO = resource.getString("simulador.preaprobado");
	public static final String COD_SIMULADOR_UNIVERSAL = resource.getString("simulador.universal");
	public static final String SIMULADOR_SOCIAL_PARAM_OBS_INGRESO = resource.getString("simulador.social.parametro.observacionesIngreso");
	public static final String SIMULADOR_SOCIAL_PARAM_OBS_RESULTADO = resource.getString("simulador.social.parametro.observacionesResultado");
	public static final String MIN_TASA_MENSUAL_SOCIAL = resource.getString("social.tasa.mensual.min");
	public static final String MAX_TASA_MENSUAL_SOCIAL = resource.getString("social.tasa.mensual.max");
	public static final String SIMULADOR_CREDITO_SOCIAL_WSDL = resource.getString("ws.endpoint");
	public static final String SIMULACION_RESULTADO = resource.getString("simulacion.resultado");
	
	
	public static final Map sucursales = new HashMap();
	
	//Parámetros que se cargan al iniciar la aplicación
	public static HashMap<String, String> REGIONES = null;
	public static HashMap<String, DatosComunas> COMUNAS = null;
	
	

	/**
	 * Retorna la lista de oficinas
	 * @return Hash con la lista de oficinas y código.
	 */
	public static List<OficinaGastosNotarialSalidaVO> getOficinas() {
		// List<OficinaGastosNotarialSalidaVO> oficinasGastosList = OficinaGastosSingleton.getInstance().getListaOficinasGastos().getOficinasGastosList();
		return null;
	}

	/**	
	 * Retorna los tipos de afiliados en la araucana
	 * @return un hashmap de tipos de afiliados
	 * Afiliado, Pensionado e Independiente
	 */
	public static Map<String, String> getTipoAfiliados() {
		Map<String, String> hash = new LinkedHashMap<String, String>();
		hash.put(NOM_AFILIADO, COD_AFILIADO);
		hash.put(NOM_PENSIONADO, COD_PENSIONADO);
		hash.put(NOM_INDEPENDIENTE, COD_INDEPENDIENTE); //validar los codigos de los tipos
		return hash;
	}

	/**
	 * Retorna los tipos de afiliados en la araucana para perfil empresa
	 * @return un hashmap de tipos de afiliados
	 * Afiliado (Trabajador dependiente)
	 */
	public static Map<String, String> getTipoAfiliadosEmpresa() {
		Map<String, String> hash = new LinkedHashMap<String, String>();
		hash.put(NOM_AFILIADO, COD_AFILIADO);
		return hash;
	}

	/**
	 * Retorna los codigos de area de chile
	 * @return
	 * @throws Exception 
	 */
	public static void getRegionesComunas() throws Exception {
		HashMap<String, String> regiones = new LinkedHashMap<String, String>();
		HashMap<String, DatosComunas> comunas = new LinkedHashMap<String, DatosComunas>();
		byte[] bites = null;
		String ruta = Configuraciones.getConfig("xml.regiones");
		bites = UtilLeerArchivo.readFileToBites(ruta);
		if (bites != null) {
			try {
				JAXBContext context = JAXBContext.newInstance(cl.laaraucana.simulacion.xml.Regiones.class);
				Unmarshaller um;
				um = context.createUnmarshaller();
				Regiones regionesObj = (Regiones) um.unmarshal(new StreamSource(new ByteArrayInputStream(bites)));
				for (int i = 0; i < regionesObj.getRegion().size(); i++) {
					Region region = regionesObj.getRegion().get(i);
					regiones.put(region.getIdRegion(), region.getNombreRegion());
					for (Comuna comuna : region.getComuna()) {
						DatosComunas datosCom = new DatosComunas();
						datosCom.setIdRegion(region.getIdRegion());
						datosCom.setNombreComuna(comuna.getNombreComuna());
						comunas.put(comuna.getIdComuna(), datosCom);
					}
				}
				ConstantesFormalizar.REGIONES = regiones;
				ConstantesFormalizar.COMUNAS = comunas;
			} catch (Exception e) {
				throw new Exception("Error al leer archivo de regiones");
			}
		} else {
			throw new Exception("Error al leer archivo de regiones");
		}
	}

	public static void main(String[] args) throws Exception {
		getRegionesComunas();
		ImprimeUtil.imprimirHash(ConstantesFormalizar.REGIONES);
		ImprimeUtil.imprimirHash(ConstantesFormalizar.COMUNAS);
	}

	public static String getObservaciones(String codigoSimulador, String codigoParametro) {
		ParametroVO parametro;
		try {
			parametro = UtilesDAO.consultaParametro(Integer.valueOf(codigoSimulador), Integer.valueOf(codigoParametro));
			return parametro.getValor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map getSucursales() {
		sucursales.put("001", "Merced");
		sucursales.put("002", "PAC Merced Afiliados");
		sucursales.put("003", "San Bernardo");
		sucursales.put("004", "Talagante");
		sucursales.put("005", "Melipilla");
		sucursales.put("006", "Sucursal Metropolitana");
		sucursales.put("008", "Gran Avenida");
		sucursales.put("009", "Plataforma Atención Asociados");
		sucursales.put("010", "Espacio Barrio Universitario");
		sucursales.put("011", "Arica");
		sucursales.put("012", "Alto Hospicio");
		sucursales.put("013", "Iquique");
		sucursales.put("021", "Tocopilla");
		sucursales.put("022", "Antofagasta");
		sucursales.put("023", "Calama");
		sucursales.put("024", "María Elena");
		sucursales.put("025", "Mall Antofagasta");
		sucursales.put("026", "Tal-Tal");
		sucursales.put("027", "Mejillones");
		sucursales.put("031", "Copiapo");
		sucursales.put("032", "Vallenar");
		sucursales.put("033", "Caldera");
		sucursales.put("041", "Ovalle");
		sucursales.put("042", "La Serena");
		sucursales.put("043", "Coquimbo");
		sucursales.put("044", "Illapel");
		sucursales.put("045", "Vicuña");
		sucursales.put("046", "Salamanca");
		sucursales.put("051", "Viña del Mar");
		sucursales.put("052", "San Antonio");
		sucursales.put("053", "Quillota");
		sucursales.put("054", "Valparaiso");
		sucursales.put("055", "San Felipe");
		sucursales.put("056", "Los Andes");
		sucursales.put("057", "La Calera");
		sucursales.put("058", "Quilpue");
		sucursales.put("059", "Casablanca");
		sucursales.put("061", "Rancagua");
		sucursales.put("062", "San Fernando");
		sucursales.put("063", "Rengo");
		sucursales.put("064", "Santa Cruz");
		sucursales.put("065", "Pichilemu");
		sucursales.put("071", "Curicó");
		sucursales.put("072", "Talca");
		sucursales.put("073", "Linares");
		sucursales.put("074", "Constitución");
		sucursales.put("075", "Parral");
		sucursales.put("076", "San Javier");
		sucursales.put("077", "San Clemente");
		sucursales.put("078", "Molina");
		sucursales.put("081", "Chillan");
		sucursales.put("082", "Concepción");
		sucursales.put("084", "Los Angeles");
		sucursales.put("085", "Talcahuano");
		sucursales.put("086", "San Carlos");
		sucursales.put("087", "Cañete");
		sucursales.put("088", "Oficina Coronel");
		sucursales.put("089", "Oficina Mulchen");
		sucursales.put("091", "Carahue");
		sucursales.put("092", "Temuco");
		sucursales.put("093", "Angol");
		sucursales.put("094", "Agencia Cañete Antiguo");
		sucursales.put("096", "Villarrica");
		sucursales.put("097", "Victoria");
		sucursales.put("099", "Agencia Puerto Varas Antiguo");
		sucursales.put("101", "Valdivia");
		sucursales.put("102", "Osorno");
		sucursales.put("103", "Puerto Montt");
		sucursales.put("104", "Castro");
		sucursales.put("105", "Puerto Varas");
		sucursales.put("106", "La Unión");
		sucursales.put("107", "Ancud");
		sucursales.put("108", "Quellon");
		sucursales.put("109", "Calbuco");
		sucursales.put("110", "Puerto Aysen");
		sucursales.put("111", "Coyhaique");
		sucursales.put("121", "Puerto Natales");
		sucursales.put("122", "Punta Arenas");
		sucursales.put("123", "Porvenir");
		sucursales.put("124", "Presidente Ibañez");
		sucursales.put("130", "La Florida");
		sucursales.put("131", "Centro de Atención Parque Deportivo");
		sucursales.put("133", "Puente Alto");
		sucursales.put("134", "Independencia");
		sucursales.put("135", "Maipú");
		sucursales.put("136", "Espacio Quilicura");
		sucursales.put("137", "Agencia Centro Cívico");
		sucursales.put("138", "Providencia");
		sucursales.put("139", "PAC Merced");
		sucursales.put("140", "Río Bueno");
		sucursales.put("141", "Panguipulli");
		sucursales.put("170", "Las Condes");
		sucursales.put("171", "Ñuñoa");
		sucursales.put("172", "Huechuraba");
		sucursales.put("174", "Estación Central");
		sucursales.put("175", "Nacimiento");
		sucursales.put("176", "Penco");
		sucursales.put("177", "Tome");
		sucursales.put("178", "Oficina Dalcahue");
		sucursales.put("179", "Purranque");
		sucursales.put("180", "Chonchi");
		sucursales.put("200", "Movil Tiempo Pleno Santiago");
		sucursales.put("201", "Agencia Movil Oficina Central");
		sucursales.put("202", "Agencia Movil Osorno");
		sucursales.put("203", "Agencia Movil Puerto Montt 1");
		sucursales.put("204", "Agencia Movil Castro");
		sucursales.put("205", "Agencia Movil Melipilla");
		sucursales.put("206", "Agencia Movil San Bernardo");
		sucursales.put("209", "Agencia Movil Talagante");
		sucursales.put("210", "Agencia Movil Valdivia");
		sucursales.put("212", "Agencia Movil Calama");
		sucursales.put("213", "Agencia Movil Iquique");
		sucursales.put("222", "Agencia Movil Antofagasta");
		sucursales.put("223", "Agencia Movil Punta Arenas");
		sucursales.put("231", "Agencia Movil Copiapo");
		sucursales.put("233", "Agencia Movil La Florida");
		sucursales.put("239", "Agencia móvil Puerto Montt 3");
		sucursales.put("241", "Agencia Movil Ovalle");
		sucursales.put("242", "Agencia Movil La Serena 1");
		sucursales.put("243", "Agencia Movil La Serena 2");
		sucursales.put("244", "Agencia Movil Illapel");
		sucursales.put("251", "Agencia Movil Viña del Mar");
		sucursales.put("252", "Agencia Movil San Antonio");
		sucursales.put("253", "Agencia Movil Quillota");
		sucursales.put("254", "Agencia móvil Valparaíso");
		sucursales.put("255", "Agencia Movil Valparaiso");
		sucursales.put("261", "Agencia Movil Rancagua");
		sucursales.put("262", "Agencia Movil San Fernando");
		sucursales.put("271", "Agencia Movil Curico");
		sucursales.put("272", "Agencia Movil Talca");
		sucursales.put("273", "Agencia Movil Linares");
		sucursales.put("280", "Agencia Movil Virtual Concepción 4");
		sucursales.put("281", "Agencia Movil Chillan");
		sucursales.put("282", "Agencia Movil Concepción 1");
		sucursales.put("283", "Agencia Movil Concepción 2");
		sucursales.put("284", "Agencia Movil Los Angeles");
		sucursales.put("285", "Agencia Movil Concepción 3");
		sucursales.put("286", "Agencia móvil Talcahuano");
		sucursales.put("287", "Agencia Movil Cañete");
		sucursales.put("291", "Agencia Movil Temuco 1");
		sucursales.put("292", "Agencia Movil Temuco 2");
		sucursales.put("293", "Agencia Movil Angol");
		sucursales.put("294", "Agencia móvil La Unión");
		sucursales.put("295", "Agencia móvil Puerto Varas");
		sucursales.put("298", "Agencia móvil Vallenar");
		sucursales.put("299", "Agencia Móvil Casablanca");
		sucursales.put("451", "Agencia Móvil Huechuraba");
		
		return sucursales;
	}

	
	

}
