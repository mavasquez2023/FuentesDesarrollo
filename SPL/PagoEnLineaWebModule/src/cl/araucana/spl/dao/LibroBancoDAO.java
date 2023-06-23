package cl.araucana.spl.dao;

import java.util.List;

import cl.araucana.spl.beans.EntradaLibroBanco;

public interface LibroBancoDAO {
	public boolean registrarEnLibroBanco(EntradaLibroBanco entrada) throws Exception;
	public boolean registrarEnLibroBancoMasivo(List entrada) throws Exception;
}