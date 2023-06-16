/**
 * 
 */
package cl.araucana.fonasa.business.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
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
public class ProcesaArchivoThread extends Thread{
	protected Logger logger = Logger.getLogger(this.getClass());
	private List<FormularioVO> listaTipoNumForm;	
	private String usuario;
	private String nombreArchivo;
	private String destinatarios;
	public ProcesaArchivoThread(List<FormularioVO> listaTipoNumForm, String usuario, String nombreArchivo, String destinatarios) {
		this.listaTipoNumForm= listaTipoNumForm;
		this.usuario= usuario;
		this.nombreArchivo= nombreArchivo;
		this.destinatarios= destinatarios;
	}
	public void run(){
		logger.info(getName()+" iniciando Thread.");

		try {
			//Validar Formularios FONASA
			System.out.println("Total registros a validar:" + listaTipoNumForm.size());
			String horaInicio= Utils.getHora();
			WSConsultaFonasaImpl wsconsulta= new WSConsultaFonasaImpl();
			int totalValidaciones= listaTipoNumForm.size();
			List<FormularioVO> lista_erroneos= wsconsulta.consultaListaFormulario(listaTipoNumForm);
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
					String[] columnas={"rutAfiliado", "tipoFormulario", "numeroLicencia", "estado", "glosaEstado", "codEstado", "comentario"};
					String[] titulos={"RUT Afliado", "Tipo Formulario", "Numero Licencia", "Estado", "Glosa Estado", "Codigo Estado", "Comentario"};
					logger.info("Generando archivo: " + pathfile);
					xls.generarCSVfromCollection(lista_erroneos, columnas, titulos);
				}
				//Insert Bitácora
				ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
				Map<String, String> param= new HashMap<String, String>();
				param.put("totalValidados", String.valueOf(totalValidaciones));
				param.put("estadono72", String.valueOf(lista_erroneos.size()-wsconsulta.getNum_timeout()));
				param.put("timeout", String.valueOf(wsconsulta.getNum_timeout()));
				param.put("observacion", "");
				param.put("usuario", usuario);
				param.put("nombreArchivo", nombreArchivo);
				param.put("horaInicio", horaInicio);
				consultaDAO.insertBitacora(param);

				//Enviar Mail
				logger.info("Enviando Correo. destinatarios:" + destinatarios);
				ConfigUtil.enviarMail(destinatarios.split(";"), pathfile);
			}else{
				ConfigUtil.enviarMailError(destinatarios.split(";"), "Error validando Licencias en Fonasa, revise log aplicación");
			}
			
		}catch (Exception exc){
			logger.error(getName()+ " interrumpido." + exc.getMessage());
			exc.printStackTrace();
			logger.info("Enviando mail por error");
			ConfigUtil.enviarMailError(destinatarios.split(";"), exc.getMessage());
		}
		logger.info(getName()+ "finalizando.");
	}
	

}
