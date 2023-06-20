package cl.laaraucana.silmsil.vo;

import java.util.ArrayList;

public class LogErrorSILVO extends SIL_VO{
	
	private ArrayList listaErrores;
	
	public LogErrorSILVO() {}

	

	public LogErrorSILVO(int codope, int tpofor, String nrofol,
			String ruttrabaj, int fecemi, int diasub, int mtoliq, int mtocot,
			int codint, int finipa, int mocope, int baseca, int idlice,
			int inimes, int tpolic, int ndicot, int ndiinc, int ndipag,
			int mtsbpa, int mtsbdi, int mcsegc, int motcot, String ofipgo,
			int ccopgo, String inssal, int submat, int tpoliq, int fecpgo,
			int mliqpa, int rimpms, int correlativ, int correlab,
			int lichasfec, int pagfol, int perpag, String archiv,
			ArrayList listaErrores) {
		super(codope, tpofor, nrofol, ruttrabaj, fecemi, diasub, mtoliq,
				mtocot, codint, finipa, mocope, baseca, idlice, inimes, tpolic,
				ndicot, ndiinc, ndipag, mtsbpa, mtsbdi, mcsegc, motcot, ofipgo,
				ccopgo, inssal, submat, tpoliq, fecpgo, mliqpa, rimpms,
				correlativ, correlab, lichasfec, pagfol, perpag, archiv);
		this.listaErrores = listaErrores;
	}



	public ArrayList getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(ArrayList listaErrores) {
		this.listaErrores = listaErrores;
	}

	
	
	
}
