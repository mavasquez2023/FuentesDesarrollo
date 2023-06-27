/*
 * Created on 11-10-2011
 *
 */
package cl.araucana.lme.liq.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.liq.helper.Helper;
import cl.araucana.lme.liq.ibatis.domain.Ilfe011VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe031VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe034VO;
import cl.araucana.lme.liq.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.liq.service.ConsumoOperadorServiceLiq;
import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.SvcFactory_LIQ;
import cl.araucana.lme.liq.svc.exception.SvcException;
import cl.araucana.lme.liq.util.EndPointUtilLiq;
import cl.araucana.lme.liq.util.LabelValueVO;


/**
 * @author j-factory
 * 
 */
public class JobControllerLiquidacion {

	// private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());


	private IAS400Svc_LIQ svc_a = null;
	private String operador = "";
	private boolean logDetail = false;
	private Date dateLcc = null;
	
	public JobControllerLiquidacion(){
		svc_a = SvcFactory_LIQ.getAS400Svc_LME();
	}

	public void endProcess(String processName, String msg) {
		String dataInf = ":::" + processName + ", Resultado: " + msg;// +
		// resultado.rows
		log.info(dataInf);
		dataInf = ":::" + processName + ", Fin.";
		log.info(dataInf);
		dataInf = "---------------------------------------------------------------------------------------------------------------------------";
		log.info(dataInf);
	}

	public void startProcess(String processName) {
		String dataInf = ":::" + processName + ", Inicio.";
		log.info(dataInf);
	}

	public void repararLiquidacionesLME() {

		startProcess("Consumir Liquidaciones sin ZonaC");
		String dataInf = "";
		int totales=0;
		
		try {

			/**BUSCO OPERADORES***************************************/
			IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
			ilfeOpeVO.setStsOpe(Integer.valueOf("1"));
			List l = svc_a.getIlfeOpe(ilfeOpeVO);
			if (null != l && l.size() > 0) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					IlfeOpeVO vo = (IlfeOpeVO) iter.next();
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */

					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtilLiq.getInstance().getEstadoError(String.valueOf(vo.getCodOpe()));
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + ": " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}
					operador = vo.getCodOpe();

					/**CONSULTANDO LIQUIDACIONES ***************************************/

					ManagerLME manager= new ManagerLME();
					List licencias= manager.consumirLiquidacion(vo);
					totales= licencias.size();
					dataInf = "Licencias Filtradas[" + totales + "]";
					log.info(dataInf);
					int numliqok=0;
					int conteo=0;
					for (Iterator iterator = licencias.iterator(); iterator.hasNext();) {
						log.info("Procesando " + conteo + "/" + totales);
						try {
							Ilfe011VO entrada = (Ilfe011VO) iterator.next();
							LabelValueVO err= new LabelValueVO();							
							log.info("LIC=" + entrada.getNumImpre() + ", EVENTO:" + entrada.getEstado() + ", CODOPE=" + vo.getCodOpe());
							
							Ilfe031VO vo31= manager.existe31(entrada);
							if(vo31!=null){
									
								// Se busca 3 periodos anteriores de renta
								List rentas= new ArrayList();
								getPeriodosAnterior(entrada.getPeriodo(), rentas);
								
								svc_a.startTransaction();
								//Se inserta tabla ILFE033
								List listvo33= manager.insertIlfe033(entrada, rentas);
							
								ConsumoOperadorServiceLiq consumo= new ConsumoOperadorServiceLiq();
								Ilfe034VO vo34= new Ilfe034VO();
								vo31.setLicenciasAnteriores("NO");
								boolean exito= consumo.enviarZonaC(vo31, listvo33, null);
								//boolean exito= true;
								if(exito){
									boolean limpiado= manager.limpiaIlfe011(entrada);
									if(limpiado){
										svc_a.commitTransaction();
										numliqok++;
									}
								}
								
								svc_a.endTransaction();
							}
							
							
							
						} catch (Exception e) {
							logError(e);
							log.error("Error, continua con sigiente licencia");
							svc_a.endTransaction();
							e.printStackTrace();
						}
						conteo++;
					}

					try {
						if ((conteo)>0){
							log.info("Total licencias enviada ZONAC: " + numliqok );
							generarEstadisticas(vo.getCodOpe(), totales, 0, 0, numliqok);
						}
						//svc_a.closeConnection();
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			}

		} catch (Throwable e) {
			logError(e);
			e.printStackTrace();
		}
		operador = "";
		endProcess("Consumir Estados 11 LME", dataInf);
	}

	private void generarEstadisticas(String codope, int total, int validacion, int devolucion, int zonaC) {
		// TODO Apéndice de método generado automáticamente
		HashMap est= new HashMap();
		est.put("fechaEvento", Helper.getSdf());
		est.put("horaEvento", Helper.getShf());
		est.put("codOpe", codope);
		est.put("total", new Integer(total));
		est.put("devolucion", new Integer(devolucion));
		est.put("validacion", new Integer(validacion));
		est.put("zonac", new Integer(zonaC));
		log.info("Insertando estadísticas Operador " + codope);
		svc_a.insertEstadistica(est);
	}		
	
	private List getPeriodosAnterior(String periodo, List lista){
		int year= Integer.parseInt(periodo.substring(0, 4));
		int mes= Integer.parseInt(periodo.substring(4, 6));
		mes= mes -1;
		if(mes==0){
			mes=12;
			year--;
		}
		lista.add(new Integer(year*100+mes));
		if(lista.size()==3 || lista.size()==6){
			return lista;
		}
		return getPeriodosAnterior(String.valueOf(year*100+mes), lista);
	}

	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}
	
	/**
	 * @return Returns the dateLcc.
	 */
	public Date getDateLcc() {
		return dateLcc;
	}

	/**
	 * @param dateLcc
	 *            The dateLcc to set.
	 */
	public void setDateLcc(Date dateLcc) {
		this.dateLcc = dateLcc;
	}
	

}
