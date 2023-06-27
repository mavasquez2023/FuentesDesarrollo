package cl.araucana.clientewsfonasa.common.enum;


public class TipoCallWSFonasaEnum {
	public static final TipoCallWSFonasaEnum AS400 = new TipoCallWSFonasaEnum(new Short((short)1), "Llamada desde AS400");
	public static final TipoCallWSFonasaEnum WEB = new TipoCallWSFonasaEnum(new Short((short)2), "Llamada desde web");
	
	private final Short codTipo;
	private final String descTipo;
	
	private TipoCallWSFonasaEnum(Short codTipo, String descTipo){
		this.codTipo = codTipo;
		this.descTipo = descTipo;
	}

	public Short getCodTipo() {
		return codTipo;
	}

	public String getDescTipo() {
		return descTipo;
	}

	
}
