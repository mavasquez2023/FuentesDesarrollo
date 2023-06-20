package cl.laaraucana.rendicionpagonomina.vo;

import java.util.ArrayList;

public class FormatoCargaVO {

	public static final int TIPO_ENTRADA_POR_POSICION = 1;
	public static final int TIPO_ENTRADA_POR_INDICE = 2;
	
	private String id = null;
	private int tipoEntrada = 0;
	private String encoding = null;
	private String separador = null;
	private ArrayList<ColumnaFormatoVO> columnas = null;

	public static FormatoCargaVO createForPlainFile(String id, String encoding) {
		FormatoCargaVO archivo = new FormatoCargaVO();
		archivo.setId(id);
		archivo.setTipoEntrada(TIPO_ENTRADA_POR_INDICE);
		archivo.setEncoding(encoding);
		archivo.setColumnas(new ArrayList<ColumnaFormatoVO>());
		return archivo;
	}
	
	public static FormatoCargaVO createForCSVFile(String id, String separador, String encoding) {
		FormatoCargaVO archivo = new FormatoCargaVO();
		archivo.setId(id);
		archivo.setTipoEntrada(TIPO_ENTRADA_POR_POSICION);
		archivo.setEncoding(encoding);
		archivo.setSeparador(separador);
		archivo.setColumnas(new ArrayList<ColumnaFormatoVO>());
		return archivo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTipoEntrada() {
		return tipoEntrada;
	}
	public void setTipoEntrada(int tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public ArrayList<ColumnaFormatoVO> getColumnas() {
		return columnas;
	}
	public void setColumnas(ArrayList<ColumnaFormatoVO> columnas) {
		this.columnas = columnas;
	}
	public String getSeparador() {
		return separador;
	}
	public void setSeparador(String separador) {
		this.separador = separador;
	}

	@Override
	public String toString() {
		return "FormatoCargaVO [id=" + id + ", tipoEntrada=" + tipoEntrada + ", encoding=" + encoding + ", separador="
				+ separador + ", columnas=" + columnas + "]";
	}
	
	
	
	
}
