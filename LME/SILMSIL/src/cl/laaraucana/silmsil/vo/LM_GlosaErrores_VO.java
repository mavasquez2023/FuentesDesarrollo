package cl.laaraucana.silmsil.vo;
/**
 * 
 * **/
public class LM_GlosaErrores_VO extends LM_VO{
	
	private String descripcion;
	private String idcampo;
	private String key;
	
	
	public LM_GlosaErrores_VO(){}

	public LM_GlosaErrores_VO(int operador, int tipoform, String folio,
			int art77bis, int fecinform, String ruttrabaj, int fecemision,
			int fecinirepo, int fecterrepo, int edadtrabaj, int fecnactrab,
			int gentrabaj, int numdiaslic, int licmatsupl, int fecnachijo,
			String ruthijo, int tipolic, int recuplabor, int iniinvalid,
			int fecconcep, int tiporeposo, String jorreposo, int lugreposo,
			String especialid, int tipoprofes, String rutprofes,
			String nomprofes, int licmodific, int entautoriz, int tipolmresu,
			int ndiasincap, String diagresuel, int periodo, int ndiasprev,
			int estadoreso, int tiporesolu, int redictamen, int causarecha,
			int tiporepoau, String jorrepoaut, String desubsidio,
			int fecreceent, int fecresoent, String rutcontral,
			String rutemplead, int fecreceemp, int regionempl, int comunaempl,
			int actlabtrab, int ocupactrab, int fecrecepag, int tipregprev,
			int calitrabaj, String tipoentpag, int fecpriafil, int feccontrab,
			int estadolic, String licrechaz, int fecproceso, String archivo,
			int correlativ, int correlab, String descripcion, String idcampo,
			String key) {
		super(operador, tipoform, folio, art77bis, fecinform, ruttrabaj,
				fecemision, fecinirepo, fecterrepo, edadtrabaj, fecnactrab,
				gentrabaj, numdiaslic, licmatsupl, fecnachijo, ruthijo,
				tipolic, recuplabor, iniinvalid, fecconcep, tiporeposo,
				jorreposo, lugreposo, especialid, tipoprofes, rutprofes,
				nomprofes, licmodific, entautoriz, tipolmresu, ndiasincap,
				diagresuel, periodo, ndiasprev, estadoreso, tiporesolu,
				redictamen, causarecha, tiporepoau, jorrepoaut, desubsidio,
				fecreceent, fecresoent, rutcontral, rutemplead, fecreceemp,
				regionempl, comunaempl, actlabtrab, ocupactrab, fecrecepag,
				tipregprev, calitrabaj, tipoentpag, fecpriafil, feccontrab,
				estadolic, licrechaz, fecproceso, archivo, correlativ, correlab);
		this.descripcion = descripcion;
		this.idcampo = idcampo;
		this.key = key;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdcampo() {
		return idcampo;
	}

	public void setIdcampo(String idcampo) {
		this.idcampo = idcampo;
	}




	public String getKey() {
		String nro_fol = getFolio();
		String rut_trab = getRuttrabaj();
		int periodo = getFecproceso();
		
		key = nro_fol+"_"+rut_trab+"_"+periodo;
		
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	
	
}//end class LM_GlosaErrores_VO
