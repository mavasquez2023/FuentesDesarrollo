package cl.araucana.estasfam.web.listeners;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;

public class DeleteFileLocksListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) { }

	public void contextInitialized(ServletContextEvent arg0) {
		
		File ASI5490 = new File(EstadisticasEnum.ASI5490.getCodigo() + ".lock");
		if(ASI5490.exists()) ASI5490.delete();
		
		File ASI5491 = new File(EstadisticasEnum.ASI5491.getCodigo() + ".lock");
		if(ASI5491.exists()) ASI5491.delete();
		
		File ASI5460 = new File(EstadisticasEnum.ASI5460.getCodigo() + ".lock");
		if(ASI5460.exists()) ASI5460.delete();
		
		File ASI4580 = new File(EstadisticasEnum.ASI4580.getCodigo() + ".lock");
		if(ASI4580.exists()) ASI4580.delete();
		
		File ASI4560 = new File(EstadisticasEnum.ASI4560.getCodigo() + ".lock");
		if(ASI4560.exists()) ASI4560.delete();
		
		File CUADRO8 = new File(EstadisticasEnum.CUADRO8.getCodigo() + ".lock");
		if(CUADRO8.exists()) CUADRO8.delete();
		
		File CUADRO10 = new File(EstadisticasEnum.CUADRO10.getCodigo() + ".lock");
		if(CUADRO10.exists()) CUADRO10.delete();
	}
}
