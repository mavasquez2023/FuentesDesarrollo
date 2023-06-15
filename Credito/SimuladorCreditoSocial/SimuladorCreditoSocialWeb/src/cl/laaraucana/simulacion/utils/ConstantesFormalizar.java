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
	public static final String SIMULADOR_CREDITO_SOCIAL_WSDL = Configuraciones.getConfig("ws.endpoint");
	//public static final String SIMULADOR_CREDITO_SOCIAL_WSDL = resource.getString("ws.endpoint");
	//public static final String SIMULACION_RESULTADO = resource.getString("simulacion.resultado");
	
	
	public static final Map sucursales = new HashMap();
	
	//Parámetros que se cargan al iniciar la aplicación
	public static HashMap<String, String> REGIONES = null;
	public static HashMap<String, DatosComunas> COMUNAS = null;
	

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
	
	

}
