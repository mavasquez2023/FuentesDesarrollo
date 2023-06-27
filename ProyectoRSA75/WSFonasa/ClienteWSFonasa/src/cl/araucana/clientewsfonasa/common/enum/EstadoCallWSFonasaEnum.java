package cl.araucana.clientewsfonasa.common.enum;


public class EstadoCallWSFonasaEnum {
	public static final EstadoCallWSFonasaEnum NO_PROCESADO = new EstadoCallWSFonasaEnum(new Short((short)1), "No Procesado");
	public static final EstadoCallWSFonasaEnum EN_PROCESO = new EstadoCallWSFonasaEnum(new Short((short)2), "En Proceso");
	public static final EstadoCallWSFonasaEnum PROCESADO = new EstadoCallWSFonasaEnum(new Short((short)3), "Procesado");
	
	private final Short codEstado;
	private final String descEstado;
	
	private EstadoCallWSFonasaEnum(Short codEstado, String descEstado){
		this.codEstado = codEstado;
		this.descEstado = descEstado;
	}

	public Short getCodEstado() {
		return codEstado;
	}

	public String getDescEstado() {
		return descEstado;
	}

	
}
