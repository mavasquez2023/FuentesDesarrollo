package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;
import java.util.List;

public class Trimestre {

	private String trimestre;
	private ArrayList<Mes> listaMeses;
	
	
	public Trimestre(String trimestre, ArrayList<Mes> listaMeses) {
		super();
		this.trimestre = trimestre;
		this.listaMeses = listaMeses;
	}


	public String getTrimestre() {
		return trimestre;
	}


	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}


	public ArrayList<Mes> getListaMeses() {
		return listaMeses;
	}


	public void setListaMeses(ArrayList<Mes> listaMeses) {
		this.listaMeses = listaMeses;
	}
	
	
	
}
