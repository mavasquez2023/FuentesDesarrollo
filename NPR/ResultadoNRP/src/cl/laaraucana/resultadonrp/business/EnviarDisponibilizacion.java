/**
 * 
 */
package cl.laaraucana.resultadonrp.business;

import java.io.File;
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
import cl.laaraucana.resultadonrp.dao.vo.ResumenDisponibilizacionVO;
import cl.laaraucana.resultadonrp.util.Constantes;
import cl.laaraucana.resultadonrp.util.GeneratorXLS;
import cl.laaraucana.resultadonrp.util.UtilFolder;
import cl.laaraucana.resultadonrp.util.Utils;
import cl.recursos.Formato;

/**
 * @author IBM Software Factory
 *
 */
public class EnviarDisponibilizacion {
	protected Logger logger = Logger.getLogger(this.getClass());
	public boolean enviarArchivoDisponibilizacion(){
		try {

			ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();

			String periodo= Utils.obtenerPeriodoCualquiera(0);
			logger.info("Consolidación, generando archivo disponibilización");

			List<ResumenDisponibilizacionVO> listaresumen= nrpDAO.consultaResumenDisponibilizacion(periodo);

			
			List<CorreoVO> listacorreos= nrpDAO.consultaCorreos("DIS");
			List<String> destinatarios= new ArrayList<String>();
			for (Iterator iterator = listacorreos.iterator(); iterator
					.hasNext();) {
				CorreoVO correoVO = (CorreoVO) iterator.next();
				destinatarios.add(correoVO.getCorreo().trim());
			}

			if(listaresumen.size()>0){
				String filename= "Disponibilizacion_" + periodo + ".csv";;

				//Generando la salida
				String pathfile= Constantes.CONFIG_PROPERTIES.getString("nrp.resumen.pathfile") + filename;
				logger.info("Se generará salida en: " + pathfile);
				OutputStream out = new FileOutputStream(pathfile);
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);

				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"concepto", "ruta", "cantidadArchivos"};
				String[] titulos={"Nomina " + periodo, "Ruta", "Cantidad Archivos"};

				xls.generarCSVfromCollection(listaresumen, columnas, titulos);
				//logger.info("Archivo " + pathfile + " ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();
				
				logger.info("Enviando Resultado por correo.");

				//Enviar Correo con excel adjunto
				String[] sendto = Arrays.copyOf(destinatarios.toArray(), destinatarios.toArray().length, String[].class);
				EnviarMailProcesos.enviarMail(destinatarios.toArray(new String[0]), periodo, "Disponibilizacion", true, pathfile );

			}else{
				EnviarMailProcesos.enviarMail(destinatarios.toArray(new String[0]), periodo, "Disponibilizacion", false, null );

			}


			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;				
		}
	}
	
}
