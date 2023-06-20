package cl.laaraucana.rendicionpagonomina.services;

import java.util.ArrayList;
import java.util.HashMap;

import cl.laaraucana.rendicionpagonomina.exception.MiException;

public interface ProcesaRendicionesBCI {
	public ArrayList<HashMap<String, Object>> getAvailablesFiles()  throws MiException;
	public int execute() throws MiException;
	public int executeByFiles(ArrayList<HashMap<String, Object>> files) throws MiException;
}
