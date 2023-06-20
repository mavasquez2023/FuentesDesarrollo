package cl.laaraucana.silmsil.vo;

public class EstadoSILLMVO {

	private ILFJA058VO sil;
	private ILFJA058VO lm;
	
	
	public EstadoSILLMVO(){}



	public EstadoSILLMVO(ILFJA058VO sil, ILFJA058VO lm) {
		super();
		this.sil = sil;
		this.lm = lm;
	}

	
	public ILFJA058VO getSil() {
		return sil;
	}

	public void setSil(ILFJA058VO sil) {
		this.sil = sil;
	}

	public ILFJA058VO getLm() {
		return lm;
	}

	public void setLm(ILFJA058VO lm) {
		this.lm = lm;
	}
	
	
	public String toString() {
		String txt="";
		
		if(sil!=null){
			txt+="SIL ESTADO: "+sil.getIdesta();
		}
		if(lm!=null){
			txt+="\nLM ESTADO: "+lm.getIdesta();
		}
		return txt;
	}
}
