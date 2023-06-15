/**
 * 
 */
package cl.laaraucana.resultadonrp.business;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.dao.ConsultaServicesDAO;
import cl.laaraucana.resultadonrp.dao.vo.CorreoVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenConsolidacionVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenGeneracionVO;
import cl.laaraucana.resultadonrp.util.Constantes;
import cl.laaraucana.resultadonrp.util.GeneratorXLS;
import cl.laaraucana.resultadonrp.util.Utils;
import cl.recursos.Formato;

/**
 * @author IBM Software Factory
 *
 */
public class EnviarGeneracion {
	protected Logger logger = Logger.getLogger(this.getClass());
	public boolean enviarArchivoGeneracion(){
		try {

			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();

			String periodo= Utils.obtenerPeriodoCualquiera(0);
			String periodo_anterior= Utils.obtenerPeriodoCualquiera(-1);
			logger.info("Generación, generando archivo.");

			List<ResumenGeneracionVO> listaresumen= nrpDAO.consultaResumenGeneracion(periodo, periodo_anterior);
			List<CorreoVO> listacorreos= nrpDAO.consultaCorreos("GEN");
			List<String> destinatarios= new ArrayList<String>();
			for (Iterator iterator = listacorreos.iterator(); iterator
					.hasNext();) {
				CorreoVO correoVO = (CorreoVO) iterator.next();
				destinatarios.add(correoVO.getCorreo().trim());
			}
			
			logger.info("Generación, total registros detalle:" + listaresumen.size());
			if(listaresumen.size()>0){
				String filename= "Generacion_" + periodo + ".csv";;

				//Generando la salida
				String pathfile= Constantes.CONFIG_PROPERTIES.getString("nrp.resumen.pathfile") + filename;
				logger.info("Se generará salida en: " + pathfile);
				OutputStream out = new FileOutputStream(pathfile);
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);

				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"concepto", "archivos", "cuotas", "monto", "concepto", "archivos_mesanterior", "cuotas_mesanterior", "monto_mesanterior"};
				String[] titulos={"Nominas " + periodo, "Cantidad Archivos", "Cantidad Cuotas", "Monto Total($)", "Nominas " + periodo_anterior, "Cantidad Archivos", "Cantidad Cuotas", "Monto Total($)"};

				xls.generarCSVfromCollection(listaresumen, columnas, titulos);
				//logger.info("Archivo " + pathfile + " ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();
				
				logger.info("Enviando Resultado por correo.");

				//Enviar Correo con excel adjunto
				String[] sendto = Arrays.copyOf(destinatarios.toArray(), destinatarios.toArray().length, String[].class);
				EnviarMailProcesos.enviarMail(destinatarios.toArray(new String[0]), periodo, "Generación", true, pathfile );

			}else{
				EnviarMailProcesos.enviarMail(destinatarios.toArray(new String[0]), periodo, "Generación", false, null );

			}


			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;				
		}
	}
	
	public boolean informarErrorGeneracion(){
		try {
			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();
			String periodo= Utils.obtenerPeriodoCualquiera(0);
			
			List<CorreoVO> listacorreos= nrpDAO.consultaCorreos("GEN");
			List<String> destinatarios= new ArrayList<String>();
			for (Iterator iterator = listacorreos.iterator(); iterator
					.hasNext();) {
				CorreoVO correoVO = (CorreoVO) iterator.next();
				destinatarios.add(correoVO.getCorreo().trim());
			}
			EnviarMailProcesos.enviarMail(destinatarios.toArray(new String[0]), periodo, "Generación", false, null );
			logger.info("Generación, enviado correo por error en Generación");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;				
		}
	}
	
}
