package cl.liv.archivos.comun.xml.lector.dto;

import java.util.ArrayList;

public class NodoDTO {
	private String name = "";
	private ArrayList atributos = new ArrayList();
	private ArrayList nodosHijos = new ArrayList();
	
	public NodoDTO(String _name){
		name = _name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList getAtributos() {
		return atributos;
	}
	public void setAtributos(ArrayList atributos) {
		this.atributos = atributos;
	}
	public ArrayList getNodosHijos() {
		return nodosHijos;
	}
	public void setNodosHijos(ArrayList nodosHijos) {
		this.nodosHijos = nodosHijos;
	}
	
	
	public String getValorAtributo(String nombreAtributo) {
		
		if(atributos != null && atributos.size()>00) {
			for(int i=0; i< atributos.size();i++) {
				AtributoDTO atr = (AtributoDTO) atributos.get(i);
				if(atr != null && nombreAtributo.equalsIgnoreCase(atr.getKey())) {
					return atr.getValue();
				}
			}
		}
		
		return null;
	}
	public String toString() {
		// TODO Auto-generated method stub
		String retorno = "";
		
		retorno = "[ NODO:"+name;
		
		
		retorno = retorno +"]";
		return retorno;
	}
	
}
