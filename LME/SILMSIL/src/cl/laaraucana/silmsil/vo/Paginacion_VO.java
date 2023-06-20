package cl.laaraucana.silmsil.vo;

import java.util.ArrayList;

/**
 * 
 * **/
public class Paginacion_VO {
	
	private int cantidad_BD;	
	private int largolistaActual;
	private ArrayList listaActual;
	private int paginaActual;
	private String primeroLista;
	private String ultimoLista;
	
	private int registrosPorPagina;
	private int cantidadPaginas;
	
	private String fechaProceso;
	
	public Paginacion_VO(){}
	
	public Paginacion_VO(int registrosPorPagina) {
		super();
		this.cantidad_BD = 0;
		//this.listaActual = 0;
		this.paginaActual = 1;
		this.primeroLista = "";
		//this.ultimoLista = 0;
		this.registrosPorPagina = registrosPorPagina;
		this.cantidadPaginas = 0;
		this.fechaProceso="";
	}

	public int getCantidad_BD() {
		return cantidad_BD;
	}

	public int getLargolistaActual() {
		return largolistaActual;
	}

	public ArrayList getListaActual() {
		return listaActual;
	}

	public String getPrimeroLista() {
		return primeroLista;
	}

	public String getUltimoLista() {
		return ultimoLista;
	}

	public int getRegistrosPorPagina() {
		return registrosPorPagina;
	}

	public int getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidad_BD(int cantidad_BD) {
		this.cantidad_BD = cantidad_BD;
	}

	public void setLargolistaActual(int largolistaActual) {
		this.largolistaActual = largolistaActual;
	}

	public void setListaActual(ArrayList listaActual) {
		this.listaActual = listaActual;
		this.largolistaActual=this.listaActual.size();		
	}

	public void setPrimeroLista(String primeroLista) {
		this.primeroLista = primeroLista;
	}

	public void setUltimoLista(String ultimoLista) {
		this.ultimoLista = ultimoLista;
	}

	public void setRegistrosPorPagina(int registrosPorPagina) {
		this.registrosPorPagina = registrosPorPagina;
	}

	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	
	
}//end class 
