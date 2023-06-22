/**
 * 
 */
package cl.araucana.cotcarserv.servlets;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import cl.araucana.cotcarserv.dao.VO.CargasVO;
import cl.araucana.cotcarserv.dao.VO.CorreoVO;
import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.InformeSAPVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.araucana.cotcarserv.utils.FormatoMail;
import cl.laaraucana.satelites.Utils.FTPUtils;
import cl.laaraucana.satelites.Utils.GeneratorXLS;
import cl.laaraucana.satelites.Utils.Utils;
import cl.recursos.EnviarMail;


/**
 * @author IBM Software Factory
 *
 */
public class DescargaMasivaCargas {
	protected Logger logger = Logger.getLogger(this.getClass());
	public void enviarCorreos(){
		
		try {
			boolean envioMail= Boolean.parseBoolean(CertificadoConst.MAIL_PROPERTIES.getString("app.envio.mail.cliente"));
			logger.info("Enviar mail Cargas No Autorizadas a clientes: " + envioMail);
			if(envioMail){
				ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
				int nummeses=1;
				String periodo= Utils.obtenerPeriodoCualquiera(nummeses*-1);
				logger.info("Periodo a buscar data:" + periodo);
				List<CorreoVO> listaCorreos= consultaDAO.consultaCorreosCargasAutorizadas(Integer.parseInt(periodo));
				logger.info("Lista correos a enviar:" + listaCorreos.size());
				String carpeta= CertificadoConst.RES_CERTIFICADOS.getString("was.nomina.cargas");
				
				if(listaCorreos.size()>0){
					for (Iterator iterator = listaCorreos.iterator(); iterator
							.hasNext();) {
						CorreoVO correoVO = (CorreoVO) iterator.next();
						ParamVO paramVO= new ParamVO();
						paramVO.setRutEmpresa(correoVO.getRutEmpresa());
						paramVO.setPeriodo(Integer.parseInt(periodo));

						if(!correoVO.getCorreo().trim().equals("")){
							logger.info("Consultando Cargas No Autorizadas Empresa:" + correoVO.getRutEmpresa() + "-"+ correoVO.getDvEmpresa());

							List<CargasVO> listaCargasNoAutorizadas= consultaDAO.consultaCargasAutorizadas(paramVO);
							logger.info("Cantidad registros trabajadores: " + listaCargasNoAutorizadas.size());
							
							if(listaCargasNoAutorizadas.size()>0){
								String filename= "CargasNoCanceladas_" + periodo + "_" + correoVO.getRutEmpresa() + "-" + correoVO.getDvEmpresa()+ ".csv";
								//Generando la salida
								String pathfile= carpeta + filename;
								logger.info("Ruta archivo:" + pathfile);

								OutputStream out = new FileOutputStream(pathfile);
								PrintStream flujo= new PrintStream(out);
								GeneratorXLS xls= new GeneratorXLS(flujo);

								//Configurando columnas a desplegar y titulos de estas.
								String[] columnas={"periodo", "oficina", "sucursal", "rutEmpresa", "dvEmpresa", "rutAfiliado", "dvAfiliado", "nombreAfiliado", "apellidoPaternoAfiliado", "apellidoMaternoAfiliado", "rutCarga", "nombreCarga", "tipoPago", "autorizado", "informado", "liquidado", "pendiente", "mayorpago", "diasTrabajados", "licencia"};
								String[] titulos={"Periodo", "Oficina", "Suc. Caja", "RUT Empresa", "Dv Empresa", "RUT Trabajador", "Dv Trabajador", "Nombre", "Apellido Paterno", "Apellido Materno", "RUT Carga", "Nombre Completo Carga", "Tipo Pago", "Monto Autorizado CCAF", "Monto Informado Empresa", "Monto Liquidado CCAF", "Pago Pendiente Trabajador", "Mayor Pago Trabajador", "Dias Trabajados", "Con Licencia"};

								xls.generarCSVfromCollection(listaCargasNoAutorizadas, columnas, titulos);
								logger.info("Archivo ha sido generado.");
								//Cerrando salida
								out.flush();
								out.close();

								//Enviando correo
								String host= CertificadoConst.MAIL_PROPERTIES.getString("smtp.host");
								logger.info("Enviando correo host:" + host);
								String port= CertificadoConst.MAIL_PROPERTIES.getString("smtp.port");
								String user= CertificadoConst.MAIL_PROPERTIES.getString("smtp.user");
								String password= CertificadoConst.MAIL_PROPERTIES.getString("smtp.password");
								String subject= CertificadoConst.MAIL_PROPERTIES.getString("mail.subject.empresas.cargas");
								EnviarMail mail= new EnviarMail(host, port, user, password);
								mail.attach(pathfile);
								logger.info("Send to: " + correoVO.getCorreo());
								mail.mailTo("aplica@laaraucana.cl", correoVO.getCorreo(), null, null, subject, FormatoMail.obtenerTextoMailCargas(correoVO.getRutEmpresa() + "-" + correoVO.getDvEmpresa(), periodo));
								mail.closeMail();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error:" + e.getMessage());
		}
	}
}
