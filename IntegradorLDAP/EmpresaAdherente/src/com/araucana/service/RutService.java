package com.araucana.service;

public interface RutService {
	public boolean validate(String rutFull);

	public int extractNumber(String rutFull);

	public String dv(int n);

	public String clearRut(String dirtyRut);
	
	public String calculateFullRut(int rut);

}
