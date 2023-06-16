package cl.araucana.cotfonasa.impl;


import cl.araucana.cotfonasa.dao.ProcesoFonasaDAO;
import cl.araucana.cotfonasa.vo.ParametrosVO;

public class ParametrosImpl {
	
	
	public ParametrosVO[] getParametrosImpl()
	{
		ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		
		return dao.getParametros();
		
	}
	
	public int guardaParametrosImpl(ParametrosVO parametros)
	{
		ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		
		return dao.guardaParametros(parametros);
		
	}
	

}
