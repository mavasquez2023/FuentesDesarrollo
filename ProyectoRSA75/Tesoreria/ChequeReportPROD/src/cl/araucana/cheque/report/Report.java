/**
 * 
 */
package cl.araucana.cheque.report;

import java.util.HashMap;
import java.util.Map;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import cl.araucana.cheque.dao.TesoDAO;
import cl.araucana.cheque.dao.TesoreriaDAO;
import cl.araucana.cheque.to.Parametros;
import cl.araucana.cheque.util.EstructuraCarpeta;
import cl.araucana.cheque.util.Numero_a_Letra;
import cl.recursos.Formato;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * @author usist199
 *
 */
public class Report {
	private static Logger log = Logger.getLogger(Report.class);
	
public String createReport(int folio, EstructuraCarpeta carpetas){
	TesoDAO tesodao= null;
	try {
		tesodao= new TesoDAO();

		//Se crea conexión a Tesorería para rescatar datos Cabecera
		TesoreriaDAO dao= new TesoreriaDAO(tesodao.getConnection());

		//El resultado de consulta es un Hashmap con todos los datos necesarios de cabecera
		Map param_map= (HashMap)dao.select(new Integer(folio));
		if(param_map.size()>0){
			//Se agrega las firmas al pdf
			param_map.put("firma", Parametros.getInstance().getFirma1());
			param_map.put("firma2", Parametros.getInstance().getFirma2());

			//se agrega monto cheque con relleno  de *
			Integer monto= (Integer)param_map.get("monto");
			String montostr= Formato.numEntero(monto.intValue());
			String montocheque= Formato.paddingLeft(montostr, 15, '*');
			montocheque+= "**";
			param_map.put("montocheque", montocheque);

			//Se agrega monto cheque en palabras
			log.debug("Monto Cheque:" + monto.intValue());
			String montoletra= Numero_a_Letra.convertir(montostr, true).trim();
			montoletra= formatMontoLetra(montoletra, '*');
			log.debug("Monto en letras: \n" + montoletra);
			param_map.put("montoletra", montoletra);

			//Se carga diseño del template
			log.info("Compilando Report");
			JasperDesign design = JRXmlLoader.load(Parametros.getInstance().getTemplate());
			JasperReport jReport = JasperCompileManager.compileReport(design);
			JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map,
					//				new JREmptyDataSource());
					tesodao.getConnection().getConnection());

			//Se exporta el pdf a ruta salida
			log.info("Exportando PDF, ruta:" + Parametros.getInstance().getRutaPDF()+ carpetas.getRutasalida() + folio + ".pdf");
			//JasperExportManager.exportReportToPdfFile(jPrint, Parametros.getInstance().getUnidad()+ pathout + folio + ".pdf");
			OutputStream output= carpetas.getOutput(Parametros.getInstance().getRutaPDF() + carpetas.getRutasalida() + folio + ".pdf");
			JasperExportManager.exportReportToPdfStream(jPrint, output);
			output.flush();
			output.close();
			log.info("PDF OK Folio:" + folio);
		}else{
			log.warn("Folio no existe en Tesorería: " + folio);
			return "Folio no existe en Tesorería: " + folio;
		}
		return "1";
	} catch (Exception e) {
		log.error("Error Report, mensaje " + e.getMessage());
		e.printStackTrace();
		return e.getMessage();
	}
	finally{
		if(tesodao!=null){
			tesodao.closeConnectionDAO();
		}
	}
}

private String formatMontoLetra(String montoboleta, char relleno){
	String linea= formatRecursivo(montoboleta, relleno);
	String[] cantidad= linea.split("\n");
	if(cantidad.length==1){
		linea+=formatRecursivo(String.valueOf(relleno), relleno);
		linea+=formatRecursivo(String.valueOf(relleno), relleno);
	}else if(cantidad.length==2){
		linea+=formatRecursivo(String.valueOf(relleno), relleno);
	}
	return linea;
}

private String formatRecursivo(String montoletra, char relleno){
	String linea="";
	if(montoletra.length()<=50){
		linea= montoletra= Formato.paddingRigth(montoletra.trim(), 50, relleno) + "\n";

	}else if(montoletra.length()>50){
		int pos= montoletra.substring(0, 51).lastIndexOf(" ");
		linea= montoletra.substring(0, pos) + "\n";
		linea+= formatRecursivo(montoletra.substring(pos).trim(), relleno);
	}
	return linea;
	
}

}
