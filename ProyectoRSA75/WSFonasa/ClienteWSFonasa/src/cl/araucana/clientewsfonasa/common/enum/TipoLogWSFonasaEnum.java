package cl.araucana.clientewsfonasa.common.enum;


public class TipoLogWSFonasaEnum {
	public static final TipoLogWSFonasaEnum REGISTRO_CONSULTA_WEB = new TipoLogWSFonasaEnum(new Short((short)1), "Regitra consulta web");
	public static final TipoLogWSFonasaEnum MOSTRAR_RESULTADO_POR_WEB = new TipoLogWSFonasaEnum(new Short((short)6), "Mostrar resultado por web");
	
	public static final TipoLogWSFonasaEnum DETECTO_CONSULTA_AS400 = new TipoLogWSFonasaEnum(new Short((short)2), "Detecto consulta por AS400");
	
	public static final TipoLogWSFonasaEnum EJECUCION_LLAMANDA_WS = new TipoLogWSFonasaEnum(new Short((short)3), "Ejecuta llamado a WS Fonasa");
	public static final TipoLogWSFonasaEnum RESPUESTA_DESDE_WS = new TipoLogWSFonasaEnum(new Short((short)4), "Respuesta desde WS Fonasa");
	public static final TipoLogWSFonasaEnum GUARDA_RESPUESTA_WS = new TipoLogWSFonasaEnum(new Short((short)5), "Guarda respuesta WS Fonasa");
	
	public static final TipoLogWSFonasaEnum ERROR_LLAMADA_WS = new TipoLogWSFonasaEnum(new Short((short)-1), "Error en la llamada al WS Fonasa");
	public static final TipoLogWSFonasaEnum ERROR_FORMATO_RESPUESTA_WS = new TipoLogWSFonasaEnum(new Short((short)-2), "Error al formatear la respuesta del WS Fonasa");
	
	
	private final Short codLog;
	private final String descLog;
	
	private TipoLogWSFonasaEnum(Short codLog, String descLog){
		this.codLog = codLog;
		this.descLog = descLog;
	}

	public Short getCodLog() {
		return codLog;
	}

	public String getDescLog() {
		return descLog;
	}

	
}
