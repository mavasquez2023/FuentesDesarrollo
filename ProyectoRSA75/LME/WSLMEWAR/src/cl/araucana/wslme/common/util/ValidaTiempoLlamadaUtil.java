package cl.araucana.wslme.common.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.wslme.integration.dao.impl.EmpleadorDaoImpl;

public class ValidaTiempoLlamadaUtil {
	private Logger log = Logger.getLogger(ValidaTiempoLlamadaUtil.class);
	public boolean llamadaEjecutada = false;
	public void validarTiempoLlamada(final long tiempoEsperaMaximo,final long tiempoIteracion, final CallableStatement cstmt ){
		final long tiempoInicio = new Date().getTime();
		new Thread(new Runnable() {
			
			public void run() {
				log.info("validando tiempo de llamada en Thread");
				if(!llamadaEjecutada){
					log.info("Date().getTime()-tiempoInicio: " + (new Date().getTime() - tiempoInicio) + " ms");
					log.info("Tiempo de espera máximo=" + tiempoEsperaMaximo + " ms");
					while( !llamadaEjecutada && ( (new Date().getTime() - tiempoInicio) < tiempoEsperaMaximo ) ){
						log.info("["+Thread.currentThread().getName()+"] iterando..");
						try {
							Thread.sleep(tiempoIteracion);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						log.info("tiempo transcurrido=" + (new Date().getTime() - tiempoInicio) + " ms");
					}
					//tiempo expirado
					if(!llamadaEjecutada){
						log.info("["+Thread.currentThread().getName()+"] matando la instancia...");
						log.info("tiempo transcurrido=" + (new Date().getTime() - tiempoInicio) + " ms");
						try {
							cstmt.cancel();
							cstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error("Error al matar la instancia SQL Server , mensaje: " + e.getMessage());
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}).start();
	}
	
	public void validarTiempoLlamada(final long tiempoEsperaMaximo,final long tiempoIteracion, final PreparedStatement cstmt ){
		final long tiempoInicio = new Date().getTime();
		new Thread(new Runnable() {
			
			public void run() {
				log.info("validando tiempo de llamada en Thread");
				// TODO Auto-generated method stub
				if(!llamadaEjecutada){
					log.info("Date().getTime()-tiempoInicio: " + (new Date().getTime() - tiempoInicio) + " ms");
					log.info("Tiempo de espera máximo=" + tiempoEsperaMaximo + " ms");
					while( !llamadaEjecutada && ( (new Date().getTime() - tiempoInicio) < tiempoEsperaMaximo ) ){
						log.info("["+Thread.currentThread().getName()+"] iterando..");
						try {
							Thread.sleep(tiempoIteracion);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						log.info("tiempo transcurrido=" + (new Date().getTime() - tiempoInicio) + " ms");
					}
					//tiempo expirado
					if(!llamadaEjecutada){
						log.info("["+Thread.currentThread().getName()+"] matando la instancia...");
						log.info("tiempo transcurrido=" + (new Date().getTime() - tiempoInicio) + " ms");
						try {
							cstmt.cancel();
							cstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}).start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		ValidaTiempoLlamadaUtil validator = new ValidaTiempoLlamadaUtil();
		validator.validarTiempoLlamada(10000, 500, null);
		
		System.out.println("["+Thread.currentThread().getName()+"] durmiendo 1");
		Thread.sleep(2000);

		System.out.println("["+Thread.currentThread().getName()+"] durmiendo 2");
		Thread.sleep(2000);

		System.out.println("["+Thread.currentThread().getName()+"] durmiendo 3");
		Thread.sleep(2000);
		validator.llamadaEjecutada = true;
		System.out.println("["+Thread.currentThread().getName()+"] durmiendo 4");
		Thread.sleep(3000);	

		System.out.println("["+Thread.currentThread().getName()+"] durmiendo 5");
		Thread.sleep(3000);
	}
	
	
}
