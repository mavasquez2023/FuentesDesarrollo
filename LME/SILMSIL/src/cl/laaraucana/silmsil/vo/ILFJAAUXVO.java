package cl.laaraucana.silmsil.vo;

public class ILFJAAUXVO {
	
	private String nrofol;
	private String ruttrabaj;
	private int perpag;
	private int lichasfec;
	private int tpoproc;
	private String usuario;
	
	
	public ILFJAAUXVO(){}


	public ILFJAAUXVO(String nrofol, String ruttrabaj, int perpag,
			int lichasfec, int tpoproc, String usuario) {
		super();
		this.nrofol = nrofol;
		this.ruttrabaj = ruttrabaj;
		this.perpag = perpag;
		this.lichasfec = lichasfec;
		this.tpoproc = tpoproc;
		this.usuario = usuario;
	}


	public String getNrofol() {
		return nrofol;
	}


	public void setNrofol(String nrofol) {
		this.nrofol = nrofol;
	}


	public String getRuttrabaj() {
		return ruttrabaj;
	}


	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}


	public int getPerpag() {
		return perpag;
	}


	public void setPerpag(int perpag) {
		this.perpag = perpag;
	}


	public int getLichasfec() {
		return lichasfec;
	}


	public void setLichasfec(int lichasfec) {
		this.lichasfec = lichasfec;
	}


	public int getTpoproc() {
		return tpoproc;
	}


	public void setTpoproc(int tpoproc) {
		this.tpoproc = tpoproc;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
