package cl.liv.cargas.masivas.dto;

import java.util.ArrayList;

public class CargaDTO {
	private String id="";
	private String action = "";
	private String actionFinish = "";
	private String separador = "";
	private String extensionSalida = "";
	private String onLoad="";
	private int tipoEntrada=0;
	private String hash="";
	private boolean generarArchivoResultado = false;
	private boolean eliminarArchivoOriginal = false;
	private ArrayList parametros = new ArrayList();
	private int filaInicial = 0;
	private String encoding = null;
	
	public CargaDTO(String _id, String _action, String _separador, String extensionSalida){
		id = _id;
		action = _action;
		separador = _separador;
	}
	public CargaDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArrayList getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList parametros) {
		this.parametros = parametros;
	}
	public String getSeparador() {
		return separador;
	}
	public void setSeparador(String separador) {
		this.separador = separador;
	}
	public String getExtensionSalida() {
		if(extensionSalida == null || extensionSalida.trim().length()==0){
			extensionSalida = "res";
		}
		return extensionSalida;
	}
	public void setExtensionSalida(String extensionSalida) {
		this.extensionSalida = extensionSalida;
	}
	public String getOnLoad() {
		return onLoad;
	}
	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}
	public int getTipoEntrada() {
		return tipoEntrada;
	}
	public void setTipoEntrada(int tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public boolean isGenerarArchivoResultado() {
		return generarArchivoResultado;
	}
	public void setGenerarArchivoResultado(boolean generarArchivoResultado) {
		this.generarArchivoResultado = generarArchivoResultado;
	}
	public boolean isEliminarArchivoOriginal() {
		return eliminarArchivoOriginal;
	}
	public void setEliminarArchivoOriginal(boolean eliminarArchivoOriginal) {
		this.eliminarArchivoOriginal = eliminarArchivoOriginal;
	}
	public String getActionFinish() {
		return actionFinish;
	}
	public void setActionFinish(String actionFinish) {
		this.actionFinish = actionFinish;
	}
	public int getFilaInicial() {
		return filaInicial;
	}
	public void setFilaInicial(int filaInicial) {
		this.filaInicial = filaInicial;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	
	
}
