package cl.araucana.estasfam.app.business.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.services.EstadisticaASI4560Service;
import cl.araucana.estasfam.app.business.services.EstadisticaASI4580Service;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5460Service;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5490Service;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5491Service;
import cl.araucana.estasfam.app.business.services.EstadisticaCUADRO10Service;
import cl.araucana.estasfam.app.business.services.EstadisticaCUADRO8Service;
import cl.araucana.estasfam.app.business.services.EstadisticaManagerService;
import cl.araucana.estasfam.common.util.FechaUtil;

@Service
public class EstadisticaManagerServiceImpl implements EstadisticaManagerService {

	@Resource
	private EstadisticaASI5490Service estASI5490Serv;
	@Resource
	private EstadisticaASI5491Service estASI5491Serv;
	@Resource
	private EstadisticaASI5460Service estASI5460Serv;
	@Resource
	private EstadisticaASI4580Service estASI4580Serv;
	@Resource
	private EstadisticaASI4560Service estASI4560Serv;
	@Resource
	private EstadisticaCUADRO8Service estCUADRO8Serv;
	@Resource
	private EstadisticaCUADRO10Service estCUADRO10Serv;
	
	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	@Async
	public void generarEstadisticas(String [] codEstadisticas) {
		for(String nombre:codEstadisticas){
			if(EstadisticasEnum.ASI5490.equals(nombre)){
				estASI5490Serv.generarEstadistica();
				new File(EstadisticasEnum.ASI5490.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.ASI5491.equals(nombre)){
				estASI5491Serv.generarEstadistica();
				new File(EstadisticasEnum.ASI5491.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.ASI5460.equals(nombre)){
				estASI5460Serv.generarEstadistica();
				new File(EstadisticasEnum.ASI5460.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.ASI4580.equals(nombre)){
				estASI4580Serv.generarEstadistica();
				new File(EstadisticasEnum.ASI4580.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.ASI4560.equals(nombre)){
				estASI4560Serv.generarEstadistica();
				new File(EstadisticasEnum.ASI4560.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.CUADRO8.equals(nombre)){
				estCUADRO8Serv.generarEstadistica();
				new File(EstadisticasEnum.CUADRO8.getCodigo() + ".lock").delete();
			}else if(EstadisticasEnum.CUADRO10.equals(nombre)){
				estCUADRO10Serv.generarEstadistica();
				new File(EstadisticasEnum.CUADRO10.getCodigo() + ".lock").delete();
			}
		}
	}
	
	@Override
	public void bloquarGeneracionEstadisticas(String [] codEstadisticas){
		try {
//			System.out.println(new File(EstadisticasEnum.ASI5490.getCodigo() + ".lock").getAbsolutePath());
			
			for(String nombre:codEstadisticas){
				if(EstadisticasEnum.ASI5490.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.ASI5490.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.ASI5491.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.ASI5491.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.ASI5460.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.ASI5460.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.ASI4580.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.ASI4580.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.ASI4560.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.ASI4560.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.CUADRO8.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.CUADRO8.getCodigo() + ".lock"));
					file.close();
				}else if(EstadisticasEnum.CUADRO10.equals(nombre)){
					FileOutputStream file = new FileOutputStream(new File(EstadisticasEnum.CUADRO10.getCodigo() + ".lock"));
					file.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String validarEstadoGeneracionEstadisticas(String codigo){
		Date fecHoy = new Date();
		String descMes =FechaUtil.getDescripcionMes(fecHoy);
		String soloNomXls = EstadisticasEnum.getEstadisticasEnum(codigo).getSoloNombreXls();
		String soloExt = EstadisticasEnum.getEstadisticasEnum(codigo).getSoloExtencion();
		
		//Si existe el ".lock" es porque esta generandose
		if(new File(EstadisticasEnum.getEstadisticasEnum(codigo).getCodigo() + ".lock").exists())
			return "GENERANDO";
		//Si el archivo xls existe se retorna el codigo
		else if(new File(cnfPathXlsEstadisticas + soloNomXls + descMes + soloExt).exists()){
			return codigo;
		}else{
			return "NO_GENERADA";
		}
	}
}
