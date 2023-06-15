package cl.jfactory.planillas.service.helper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.mail.impl.MailImpl;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class AlertasHelper {


	

	public static void procesarAlertaCambioEstado(final String estadoInicial, final String estadoFinal){
		procesarAlertas(estadoInicial, estadoFinal, true, false, null,null);
	}
	public static void procesarAlertaError(final String codigoError, Throwable e){
		MiHashMap data = new MiHashMap();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		sStackTrace = sStackTrace.replaceAll("\n", "<br/>");
		data.put("descripcion_error", sStackTrace);
		procesarAlertas(null, null, false, true, codigoError,data);
	}
	
	private static void procesarAlertas(final String estadoInicial, final String estadoFinal, final boolean cambioEstado, final boolean error, final String codigoError, final MiHashMap data){
			
			UtilLogWorkflow.debug("Enviando alertas...["+estadoInicial+","+estadoFinal+"]");
			
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String idConfiguracion = "mailer_wf";
					
					SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
					String query = "";
					if(cambioEstado){
						query = "carga_SAP.obtenerInfoAlertaEstado";
					}
					else{
						query = "carga_SAP.obtenerInfoAlertaError";
					}
					
					if(sqlMap != null){
						MiHashMap par = new MiHashMap();
						par.put("estado",estadoInicial);
						par.put("nuevo_estado",estadoFinal);
						par.put("cambio_estado",cambioEstado+"");
						par.put("error_proceso",error+"");
						par.put("codigo_error",codigoError);
						
						try {
							ArrayList alertas = (ArrayList) sqlMap.queryForList(query,par);
							if(alertas != null && alertas.size()>0){
								for(int i=0; i< alertas.size(); i++){
									MiHashMap parAlerta = new MiHashMap();
									parAlerta.put("id_alerta",Integer.parseInt( ((MiHashMap) alertas.get(i)).get("ID").toString())+"");
									ArrayList mails = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerMailsRelacionadosAlerta",parAlerta);
									
									if(mails != null && mails.size()>0){
										String listaTo = "";
										for(int j=0; j< mails.size(); j++){
											listaTo = listaTo+  ((MiHashMap) mails.get(j)) .get("MAIL")+";";
										}
										String descripcion = Utiles.getStringConData(((MiHashMap) alertas.get(i)).get("DESCRIPCION").toString(), data);
										String asunto = Utiles.getStringConData(((MiHashMap) alertas.get(i)).get("ASUNTO").toString(), data);
										
										MailImpl.enviarMail(idConfiguracion, 
												((MiHashMap) alertas.get(i)).get("REMITENTE").toString(), 
												listaTo, 
												asunto, 
												descripcion);
									}
								}
							}
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
	}
	
	
}
