package cl.araucana.tupla2.struts.utils;

public class Contadores {

	private int countErrores;
	private int countRutProcesados;
	private int countTuplas;
	private int countTramos;

	public Contadores(int inicioTuplas, int inicioTramos) {
		countTuplas = inicioTuplas;
		countTramos = inicioTramos;
	}

	public int getCountErrores() {
		return countErrores;
	}

	public int getCountRutProcesados() {
		return countRutProcesados;
	}

	public synchronized void addRutProcesados() {
		countRutProcesados++;
	}

	public int getCountTuplas() {
		return countTuplas;
	}

	public synchronized void addTupla() {
		countTuplas++;
	}
	
	public synchronized void resetTupla() {
		countTuplas=0;
	}
	
	public synchronized void resetErrores() {
		countErrores=0;
	}
	
	public synchronized void addError() {
		countErrores++;
	}

	public int getCountTramos() {
		return countTramos;
	}

	public synchronized void addTramo() {
		countTramos++;
	}

}
