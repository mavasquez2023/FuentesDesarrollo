package cl.laaraucana.rendicionpagonomina.services;

import java.util.ArrayList;
import java.util.HashMap;

import cl.laaraucana.rendicionpagonomina.exception.MiException;

public interface ProcesaRendicionNominas24HService {
	public ArrayList<HashMap<String, Object>> getAvailablesFiles()  throws MiException;
	public int loadData() throws MiException;
	public int loadDataFromFile(String file) throws MiException;;
}
