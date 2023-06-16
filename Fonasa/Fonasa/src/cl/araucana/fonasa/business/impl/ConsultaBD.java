/**
 * 
 */
package cl.araucana.fonasa.business.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.fonasa.business.to.ResponseFormFonasaTO;
import cl.araucana.fonasa.dao.VO.FormularioVO;
import cl.araucana.fonasa.main.dao.ConsultaServicesDAO;
import cl.araucana.fonasa.utils.ConfigUtil;
import cl.araucana.fonasa.utils.ConstantsUtil;
import cl.araucana.fonasa.utils.GeneratorXLS;
import cl.araucana.fonasa.utils.Utils;


/**
 * @author IBM Software Factory
 *
 */
public class ConsultaBD{
	protected Logger logger = Logger.getLogger(this.getClass());

	private String destinatarios;
	public ConsultaBD() {
		
	}
	public void validaBD(){
		logger.info(" Iniciando ConsultaBD.");

		try {
			//Busca destinatarios correo
			destinatarios= ConstantsUtil.MAIL_PROPERTIES.getString("app.mail.usuario");
			
			//Buscando registros a procesar en BD
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			int periodo= Integer.parseInt(Utils.obtenerPeriodoCualquiera(0));
			List<FormularioVO> listaForm= consultaDAO.consultaFormularios(periodo);
			
			List<FormularioVO> listaFinal= new ArrayList<FormularioVO>();
			logger.info("Total registros consulta BD:" + listaForm.size());
			for (Iterator iterator = listaForm.iterator(); iterator.hasNext();) {
				FormularioVO formularioVO = (FormularioVO) iterator.next();
				if(formularioVO.getNumeroLicencia()>0){
					listaFinal.add(formularioVO);
				}
			}
			
			String horaInicio= Utils.getHora();
			WSConsultaFonasaImpl wsconsulta= new WSConsultaFonasaImpl();
			int totalValidaciones= listaFinal.size();
			
			List<String> listaTipoNumForm;
			List<FormularioVO> lista_erroneos= wsconsulta.consultaListaFormulario(listaFinal);
			if(lista_erroneos!=null){
				int totalno72= lista_erroneos.size();
				logger.info("Total registros erroneos:" + totalno72);
				String pathfile="";
				
				if(totalno72>0){
					//Generación Archivo CSV
					pathfile= ConstantsUtil.RES_CONFIGURACION.getString("fonasa.path.archivos.validacion");
					String filename="respuesta_validacion.csv";
					pathfile+= filename;
					logger.info("Archivo de salida: " + pathfile);
					OutputStream out = new FileOutputStream(pathfile);
					PrintStream flujo= new PrintStream(out);
					GeneratorXLS xls= new GeneratorXLS(flujo);

					//Configurando columnas a desplegar y titulos de estas.
					String[] columnas={"codigoOficina", "nombreOficina", "fechaDesde", "fechaHasta", "estadoLicencia", "fechaPago", "rutAfiliado", "tipoFormulario", "numeroFormulario", "numeroLicencia", "folioPago", "folioTesoreria", "estadoPago", "estado", "glosaEstado", "codEstado", "comentario"};
					String[] titulos={"Codigo Oficina", "Nombre Oficina", "Fecha Desde", "Fecha Hasta", "Estado Licencia", "Fecha Pago", "RUT Afliado", "Tipo Formulario", "Numero Formulario", "Numero Licencia", "Folio Pago", "Folio Tesoreria", "Estado Pago", "Estado WS", "Glosa Estado", "Codigo Estado", "Comentario"};
					logger.info("Generando archivo: " + pathfile);
					xls.generarCSVfromCollection(lista_erroneos, columnas, titulos);
				}
				//Insert Bitácora
				
				Map<String, String> param= new HashMap<String, String>();
				param.put("totalValidados", String.valueOf(totalValidaciones));
				param.put("estadono72", String.valueOf(lista_erroneos.size()-wsconsulta.getNum_timeout()));
				param.put("timeout", String.valueOf(wsconsulta.getNum_timeout()));
				param.put("observacion", "");
				param.put("usuario", "Scheduling");
				param.put("nombreArchivo", "");
				param.put("horaInicio", horaInicio);
				consultaDAO.insertBitacora(param);

				//Enviar Mail
				logger.info("Enviando Correo. destinatarios:" + destinatarios);
				ConfigUtil.enviarMail(destinatarios.split(";"), pathfile);
			}else{
				ConfigUtil.enviarMailError(destinatarios.split(";"), "Error validando Licencias en Fonasa, revise log aplicación");
			}
			
		}catch (Exception exc){
			logger.error("Error Consulta BD, mensaje= " + exc.getMessage());
			exc.printStackTrace();
			logger.info("Enviando mail por error");
			ConfigUtil.enviarMailError(destinatarios.split(";"), exc.getMessage());
		}
		logger.info("Consulta BD finalizando.");
	}
	

}
