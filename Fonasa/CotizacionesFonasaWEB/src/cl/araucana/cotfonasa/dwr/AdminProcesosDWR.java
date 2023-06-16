package cl.araucana.cotfonasa.dwr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import cl.araucana.cotfonasa.servlet.ControllerServlet;
import cl.araucana.cotfonasa.util.EnviaCorreo;
import cl.araucana.cotfonasa.vo.LogVO;
import cl.araucana.cotfonasa.vo.ParamVO;
import cl.araucana.cotfonasa.impl.AdminProcesosImpl;
import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;

public class AdminProcesosDWR {
	
	
   public ParamVO[] cargaAnosDWR(String ano){
		
		AdminProcesosImpl admin = new AdminProcesosImpl();
		
		return admin.cargaAnos();
		
	}
	
	

	public LogVO[] consultaLogAnoDWR(String ano){
		
		AdminProcesosImpl admin = new AdminProcesosImpl();
		
		System.out.println("Año de consulta: "+ano+"");
		
		return admin.consultaLogAno(ano.trim());
		
	}
	
	public int ejecutaProcesoDWR(String periodo)
	{
		
	
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
	
		proc.ejecutaProceso(periodo);
		
		
		return 1;
		
	}
	
	public String getKey(String str)
	{
        
		
		try {
			Properties props = new Properties();
			props.load(AdminProcesosDWR.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			return props.getProperty("LLAVE");
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		return "0";
		
		
	}
	
	
	
}
