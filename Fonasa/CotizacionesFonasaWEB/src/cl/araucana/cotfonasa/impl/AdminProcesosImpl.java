package cl.araucana.cotfonasa.impl;

import java.sql.SQLException;

import cl.araucana.cotfonasa.dao.AdminProcesosDAO;
import cl.araucana.cotfonasa.vo.LogVO;
import cl.araucana.cotfonasa.vo.ParamVO;

public class AdminProcesosImpl {
	
	public ParamVO[] cargaAnos()
	{
		AdminProcesosDAO dao = new AdminProcesosDAO();
		
		
		return dao.cargaAnos();
	
	
	}
	
	public LogVO[] consultaLogAno(String ano)
	{
		AdminProcesosDAO dao = new AdminProcesosDAO();
		
		return dao.consultaLogAno(ano);
		
		
		
	}
	
	public int ejecutaProceso(String periodo)
	{
		
		String archivoEntrada= "C://CCAF112013_copia.txt";
		String archivoSalida = "C://CCAF112013_SAL.txt";
		/*
		try{
		Fonasa.ejecutaProceso(archivoEntrada, archivoSalida);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}*/
		
		
		return 1;
		
	}
	
	

}
