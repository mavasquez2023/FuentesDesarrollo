package cl.laaraucana.mandato.ibatis.vo;


public class BancoVo implements Comparable<BancoVo>{

	private int codigo;
	private String nombre;
	private String estado;


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int compareTo(BancoVo o) {
		return (int)(this.nombre.compareTo(o.getNombre()));
	}

}
