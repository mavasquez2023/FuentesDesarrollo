package cl.araucana.cotfonasa.dwr;

import java.sql.SQLException;

import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;
import cl.araucana.cotfonasa.util.EnviaCorreo;
import cl.araucana.cotfonasa.util.FonasaQuartz;

public class ProcesoFonasaDWR {
	
	
	
	
	public void iniciaProcesoAutomaticoDWR()
	{
		FonasaQuartz.init();
	}
	
	public int consultaProcesoDWR(String periodo)
	{
		
		ProcesoFonasaImpl impl = new ProcesoFonasaImpl();
		
		return impl.consultaProceso(periodo);
		
	}
	
	public void reprocesaProcesoDWR(String periodo){
		
		
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
		System.out.println("reprocesando proceso");
		System.out.println("periodo: "+periodo);
		
		
		proc.updateEstadoLog(periodo, 2);
		
		proc.ejecutaProceso(periodo);
		
		
	}
	
	public int ejecutaProcesoDWR(String periodo)
	{
		
	
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
	
		proc.ejecutaProceso(periodo);
		
		
		return 1;
		
	}

	
	
	

}
