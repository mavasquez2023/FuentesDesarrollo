package cl.araucana.spl.cron;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.spl.beans.CTACTE;
import cl.araucana.spl.beans.EntradaLibroBanco;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.util.Constantes;
import cl.araucana.spl.util.Utiles;

public class JobSPL implements Job {
	private static final Logger log = Logger.getLogger(JobSPL.class);

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("***INICIO DEL JOB SPL***");
		try {
			// obtiene fecha contable
			String fechaContable = Utiles.calculaFechaContable(Constantes.getInstancia().DIAS_RESTA);
			log.info("Se calcula FECHA_CONTABLE: " + fechaContable);

			// 1. obtener bancos batch
			MedioPagoManager medioManager = new MedioPagoManager();
			List mediosPago = medioManager.getMediosPagoBatch();
			log.info("Se obtienen los bancos BATCH, size[" + mediosPago.size() + "]");

			//2. obtener datos para libro banco.
			for (int i = 0; i < mediosPago.size(); i++) {
				MedioPago mp = (MedioPago) mediosPago.get(i);
				log.info("Se obtiene el " + (i + 1) + "° medio de pago: [" + mp.getId() + "] [" + mp.getCodigo() + "]");
				CTACTE cta = medioManager.getMontoPagadoPorMedioPago(mp.getId().toString(), fechaContable);
				if (cta != null) {
					log.info("Para el banco [" + mp.getCodigo() + "] cta :["+cta.getCtacte().trim()+"] se obtiene el monto total = [" + cta.getMontoTotal() + "] para un total de [" + cta.getCantidadRegistros() + "] registros");
					// 3. ejecutar libro banco
					CursarOperacionManager cmg = new CursarOperacionManager();
					EntradaLibroBanco bcoEntrada = new EntradaLibroBanco();
					bcoEntrada.setBanco(String.valueOf(mp.getCodigoBanco()));
					bcoEntrada.setNroCuentaCorriente(cta.getCtacte());
					bcoEntrada.setMonto(cta.getMontoTotal());
					bcoEntrada.setFechaMovimiento(fechaContable.replaceAll("\\-", ""));
					bcoEntrada.setDescripcion("Registro realizado por JOBSPL");
					bcoEntrada.setNroDeposito("");

					if (cmg.registrarPagoLibroBanco(bcoEntrada)) {
						//actuelizar marca pagos libroBanco
						int registrosActualizados = medioManager.updateMarcaLibroBanco(mp.getId().toString(), fechaContable, cta.getIdConvenio());
						log.info("Se aplica la marca de librobanco a [" + registrosActualizados + "] pagos");
					}else {
						log.info("Error al registrar en Libro Banco ");
						
					}
				} else {
					log.info("El banco [" + mp.getCodigo() + "] no registra pagos para la fecha indicada  [" + fechaContable + "] ");
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		log.info("***FIN DEL JOB SPL***");
	}
}
