package cl.laaraucana.silmsil.vo;

import java.util.HashMap;

/**
 * Clase que representa estructura para datos SIL
 * LIEXP.ILFSIL050 y LIEXP.ILFSIL054.
 * **/
public class SIL_GlosaErrores_VO extends SIL_VO{
	
	private String descripcion;
	private String idcampo;
	private String key;
	private String periodo;
	
	
	public SIL_GlosaErrores_VO() {}

	


	public SIL_GlosaErrores_VO(int codope, int tpofor, String nrofol,
			String ruttrabaj, int fecemi, int diasub, int mtoliq, int mtocot,
			int codint, int finipa, int mocope, int baseca, int idlice,
			int inimes, int tpolic, int ndicot, int ndiinc, int ndipag,
			int mtsbpa, int mtsbdi, int mcsegc, int motcot, String ofipgo,
			int ccopgo, String inssal, int submat, int tpoliq, int fecpgo,
			int mliqpa, int rimpms, int correlativ, int correlab,
			int lichasfec, int pagfol, int perpag, String archiv,
			String descripcion, String idcampo, String key,
			HashMap<String, String> map) {
		super(codope, tpofor, nrofol, ruttrabaj, fecemi, diasub, mtoliq,
				mtocot, codint, finipa, mocope, baseca, idlice, inimes, tpolic,
				ndicot, ndiinc, ndipag, mtsbpa, mtsbdi, mcsegc, motcot, ofipgo,
				ccopgo, inssal, submat, tpoliq, fecpgo, mliqpa, rimpms,
				correlativ, correlab, lichasfec, pagfol, perpag, archiv);
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
		String nro_fol = getNrofol();
		String rut_trab = getRuttrabaj();
		String pag_fol = String.valueOf(getPagfol());
		int periodo = getPerpag();
		
		key = nro_fol+"_"+pag_fol+"_"+rut_trab+"_"+periodo;
		
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}




	public String getPeriodo() {
		return periodo;
	}




	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


}//end class SIL_VO
