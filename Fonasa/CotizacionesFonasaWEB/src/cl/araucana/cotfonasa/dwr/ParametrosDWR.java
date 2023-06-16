package cl.araucana.cotfonasa.dwr;

import java.io.IOException;

import cl.araucana.cotfonasa.impl.ParametrosImpl;
import cl.araucana.cotfonasa.util.AccesoAS400;
import cl.araucana.cotfonasa.vo.ParametrosVO;

public class ParametrosDWR {
	
	
	public ParametrosVO[] getParametrosDWR(String str)
	{
		ParametrosImpl impl = new ParametrosImpl();
		
		return impl.getParametrosImpl();
		
	}
	
	public boolean verificaDirectorioDWR(String path)
	{
		AccesoAS400 as400 = new AccesoAS400();
		
		try {
			return as400.verificarDir(path);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return false;
	}
	
	public int guardaParametrosDWR(String correoAdmin,String correoUsuario,String directorioEntrada,String directorioSalida,String idParametro)
	{
		ParametrosImpl impl = new ParametrosImpl();
		ParametrosVO parametro = new ParametrosVO();
		
		System.out.println("admin: "+correoAdmin+" usuario: "+correoUsuario+"entrada: "+ directorioEntrada+" salida:"+directorioSalida+" param:"+ idParametro);
		
		parametro.setCorreoAdmin(correoAdmin);
		parametro.setCorreoUsuario(correoUsuario);
		parametro.setDirectorioEntrada(directorioEntrada);
		parametro.setDirectorioSalida(directorioSalida);
		parametro.setIdParametro(idParametro);
		
		return impl.guardaParametrosImpl(parametro);
		
	}
	

}
