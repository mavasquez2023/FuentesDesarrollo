package cl.laaraucana.capaservicios.database.vo;

public class ParametrosVO {
	
	private int par_id;
	private String par_nombre;
	private String par_descrip;
	private String par_tipo_parametro;
	private String par_valor;
	private String par_tipo_valor;
	
	
	
	
	public ParametrosVO(){
		
	}
	
	public ParametrosVO(int par_id, String par_nombre, String par_descrip,
			String par_tipo_parametro, String par_valor, String par_tipo_valor) {
		super();
		this.par_id = par_id;
		this.par_nombre = par_nombre;
		this.par_descrip = par_descrip;
		this.par_tipo_parametro = par_tipo_parametro;
		this.par_valor = par_valor;
		this.par_tipo_valor = par_tipo_valor;
		
	}

	public int getPar_id() {
		return par_id;
	}

	public void setPar_id(int par_id) {
		this.par_id = par_id;
	}

	public String getPar_nombre() {
		return par_nombre;
	}

	public void setPar_nombre(String par_nombre) {
		this.par_nombre = par_nombre;
	}

	public String getPar_descrip() {
		return par_descrip;
	}

	public void setPar_descrip(String par_descrip) {
		this.par_descrip = par_descrip;
	}

	public String getPar_tipo_parametro() {
		return par_tipo_parametro;
	}

	public void setPar_tipo_parametro(String par_tipo_parametro) {
		this.par_tipo_parametro = par_tipo_parametro;
	}

	public String getPar_valor() {
		return par_valor;
	}

	public void setPar_valor(String par_valor) {
		this.par_valor = par_valor;
	}

	public String getPar_tipo_valor() {
		return par_tipo_valor;
	}

	public void setPar_tipo_valor(String par_tipo_valor) {
		this.par_tipo_valor = par_tipo_valor;
	}

}
