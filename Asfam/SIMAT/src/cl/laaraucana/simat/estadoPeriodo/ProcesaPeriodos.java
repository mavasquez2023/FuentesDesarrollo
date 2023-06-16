package cl.laaraucana.simat.estadoPeriodo;

import cl.laaraucana.simat.entidades.ProcesoPeriodo;
import cl.laaraucana.simat.entidades.SMF09_VO;
import cl.laaraucana.simat.mannagerDB2.ProcedimientosMannager;
import cl.laaraucana.simat.mannagerDB2.SMF09_Mannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class ProcesaPeriodos {
	
	/**
	 * Método genérico que permite consultar por el estado de un proceso SIMAT
	 * @param proceso: Utilizar constantes definidas en la clase ProcesoPeriodo
	 * @param periodo: Periodo en formato yyyyMM
	 * @return
	 * @throws Exception
	 */
	public ProcesoPeriodo getEstadoProceso(String proceso, String periodo) throws Exception{
		ProcesoPeriodo res = new ProcesoPeriodo();
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		String keyAvisoProceso = null;
		smf09.setFecha_periodo(periodo);
		smf09.setProceso(proceso);
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 != null) {
			if (smf09.getEstado_proceso().equals("E")) {
				keyAvisoProceso = "Proceso En Ejecución";
			}
			if (smf09.getEstado_proceso().equals("C")) {
				keyAvisoProceso = "Proceso Cancelado";
			}
			if (smf09.getEstado_proceso().equals("S")) {
				keyAvisoProceso = "Proceso Pendiente de Ejecución";
			}
			if (smf09.getEstado_proceso().equals("T")) {
				keyAvisoProceso = "Proceso Finalizado, puede ejecutar nuevamente el proceso";
			}
			res.setCodEstado(smf09.getEstado_proceso());
		} else {
			keyAvisoProceso = "No se ha ejecutado el proceso para el Periodo seleccionado.";
		}
		
		res.setNombreProceso(proceso);
		res.setDesEstado(keyAvisoProceso);
		return res;
	}
	
	
	public String getEstadoProcesoCarga(String periodo) throws Exception {
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		String keyAvisoProceso = null;
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("CARGA");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 != null) {
			if (smf09.getEstado_proceso().equals("E")) {
				keyAvisoProceso = "Estado Generacion BD SIMAT: Proceso En Ejecución";
			}
			if (smf09.getEstado_proceso().equals("C")) {
				keyAvisoProceso = "Estado Generacion BD SIMAT: Proceso Cancelado";
			}
			if (smf09.getEstado_proceso().equals("S")) {
				keyAvisoProceso = "Estado Generacion BD SIMAT: Proceso Pendiente de Ejecución";
			}
			if (smf09.getEstado_proceso().equals("T")) {
				keyAvisoProceso = "Estado Generacion BD SIMAT: Proceso Finalizado, puede efectuar nuevamente la carga del periodo";
			}
		} else {
			keyAvisoProceso = "Estado Generacion BD SIMAT: No se ha generado carga del Periodo seleccionado.";
		}

		return keyAvisoProceso;
	}

	public String getEstadoProcesoValidacion(String periodo) throws Exception {
		String estadoPeriodo = "";
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("VALIDACION");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);

		if (smf09 != null) {
			if (smf09.getEstado_proceso().equals("E")) {
				estadoPeriodo = "Estado Validacion BD SIMAT: Proceso En Ejecución";
			}
			if (smf09.getEstado_proceso().equals("C")) {
				estadoPeriodo = "Estado Validacion BD SIMAT: Proceso Cancelado";
			}
			if (smf09.getEstado_proceso().equals("S")) {
				estadoPeriodo = "Estado Validacion BD SIMAT: Proceso Pendiente de Ejecución";
			}
			if (smf09.getEstado_proceso().equals("T")) {
				estadoPeriodo = "Estado Validacion BD SIMAT: Proceso Finalizado, puede efectuar nuevamente la validacion";
			}
		} else {
			estadoPeriodo = "Estado Validacion BD SIMAT: No se ha Validado el periodo: " + periodo;
		}

		return estadoPeriodo;
	}

	public String callProcesoCargar2(String periodo, String user) throws Exception {
		System.out.println("* * * * * [START callProcesoCargar ]* * * * *");
		boolean keyValidacion = false;
		boolean keyCarga = false;
		boolean keyCargaAux = false;
		boolean keyValidacion_SE = false;
		boolean keyValidacion_CT = false;
		boolean keyCarga_CSE = false;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		/*
		comprobacion estado proceso VALIDACION, debe ser 'C' o 'T' o 'null' 
		para poder correr proceso de carga
		*/
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("VALIDACION");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 == null) {
			keyValidacion = true;
		} else {
			if (smf09.getEstado_proceso().equals("C") || smf09.getEstado_proceso().equals("T")) {
				keyValidacion = true;
				keyValidacion_CT = true;
			}
			if (smf09.getEstado_proceso().equals("S") || smf09.getEstado_proceso().equals("E")) {
				keyValidacion_SE = true;
			}
		}
		/*
		comprobacion estado proceso CARGA, debe ser 'C' o 'T' o 'null' 
		para poder correr proceso de carga
		y ademas el proceso de validacion 'keyValidacion' debe ser true
		*/

		smf09 = new SMF09_VO();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("CARGA");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 == null) {
			keyCarga = true;
			keyCargaAux = true;
			if (keyValidacion_SE) {
				keyValidacion = true;
			}
		} else {
			if (smf09.getEstado_proceso().equals("C") || smf09.getEstado_proceso().equals("T")) {
				keyCarga = true;
			}
			if (smf09.getEstado_proceso().equals("C") || smf09.getEstado_proceso().equals("S") || smf09.getEstado_proceso().equals("E")) {
				keyCarga_CSE = true;
			}
		}
		//borrara registro validacion en caso que no corresponda el estado.
		if (keyCarga_CSE && keyValidacion_SE) {
			System.out.println("* * * * * [borrando proceso validacion S o E, estado no corresponde]* * * * *");
			this.delProcesoValidacion(periodo);
		} else if (keyCarga_CSE && keyValidacion_CT) {
			System.out.println("* * * * * [borrando proceso validacion C o T, estado no corresponde]* * * * *");
			this.delProcesoValidacion(periodo);
		}
		//evaluacion de llaves
		if (keyValidacion && keyCarga) {
			smf09 = new SMF09_VO();
			smf09.setFecha_periodo(periodo);
			smf09.setProceso("CARGA");//proceso
			smf09.setDescripcion("Proceso Submitido");//descripcion			
			smf09.setEstado_proceso("S");//estado
			smf09.setFecha_proceso(hfa.getFechaEstadoProceso());
			smf09.setHora_proceso(hfa.getHoraEstadoProceso());
			smf09.setUsuario_proceso(user);//nombr
			if (keyCargaAux) {
				//el periodo no ha sido ingresado en el estadoproceso, se ingresara a continuacion
				System.out.println("* * * * * [carga submitida, estado seteado]* * * * *");
				smf09MGR.setEstadoPeriodoByProceso(smf09);
			} else {
				//el periodo y proceso ya existe, solo actualizar.					
				System.out.println("* * * * * [carga submitida, estado actualizado]* * * * *");
				smf09MGR.upEstadoPeriodoByProceso(smf09);
			}
			//se borrar validacion, porque se cargaran nuevamente los datos.
			this.delProcesoValidacion(periodo);
			//ambos procesos,carga y validacion no estan en ejecucion actual
			//se procesde a ejecutar procedimiento almacenado.
			System.out.println("* * * * * [procesaPeriodos: callProcedureCargaArchivosDB2]* * * * *");
			ProcedimientosMannager pm = new ProcedimientosMannager();
			/**comentado para test**/
			pm.callProcedureCargaArchivosDB2(periodo);
		} else {
			System.out.println("* * * * * [proceso carga no realizado]* * * * *");
		}
		//retorna estado del proceso luego de la llamada, para mostrar a usuario
		return (this.getEstadoProcesoCarga(periodo));
	}//end callProcesoCargar2

	public String callProcesoValidar2(String periodo, String user) throws Exception {
		System.out.println("* * * * * [START callProcesoVALIDAR ]* * * * *");
		boolean keyValidacion = false;
		boolean keyCarga = false;
		boolean keyCargaAux = false;

		boolean keyValidacion_SE = false;
		boolean keyCarga_NULL = false;
		boolean keyCarga_T = false;
		boolean keyValidacion_CT = false;

		ManejoHoraFecha hfa = new ManejoHoraFecha();
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		/*
		comprobacion estado proceso CARGA, debe ser 'T' 
		para poder correr proceso de VALIDACION
		*/
		System.out.println("* * * * * [callProcesoVALIDAR: consultando estado CARGA ]* * * * *");
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("CARGA");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 == null) {
			//no se debe procesar si no se ha ejecutado proceso CARGA.
			keyValidacion = false;
			keyCarga_NULL = true;
		} else {
			//solo procesara si la carga termino correctamente.
			if (smf09.getEstado_proceso().equals("T")) {
				keyValidacion = true;
				keyCarga_T = true;
			}
		}
		/*
		comprobacion estado proceso VALIDACION, debe ser 'C' o 'T' o 'null' 
		para poder correr proceso de validacion
		y ademas el proceso de CARGA 'keyValidacion' debe ser true
		*/
		System.out.println("* * * * * [callProcesoVALIDAR: consultando estado VALIDACION ]* * * * *");
		smf09 = new SMF09_VO();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("VALIDACION");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		if (smf09 == null) {
			keyCarga = true;
			keyCargaAux = true;
		} else {
			if (smf09.getEstado_proceso().equals("C") || smf09.getEstado_proceso().equals("T")) {
				keyCarga = true;
				keyValidacion_CT = true;
			}
			if (smf09.getEstado_proceso().equals("S") || smf09.getEstado_proceso().equals("E")) {
				keyValidacion_SE = true;
			}
		}

		//borramos estado validacion que no debiera darse, por proceso CARGA=null.
		if (keyCarga_NULL) {
			if (keyValidacion_CT || keyValidacion_SE) {
				System.out.println("* * * * * [eliminando proceso validacion, no corresponde por CARGA=null]* * * * *");
				this.delProcesoValidacion(periodo);
				keyValidacion = false;
				keyCarga = false;
			}
		} else {
			if (!keyCarga_T) {
				System.out.println("* * * * * [eliminando proceso validacion, no corresponde por CARGA!=T]* * * * *");
				this.delProcesoValidacion(periodo);
				keyValidacion = false;
				keyCarga = false;
			}
		}

		//evaluacion de llaves
		if (keyValidacion && keyCarga) {
			smf09 = new SMF09_VO();
			smf09.setFecha_periodo(periodo);
			smf09.setProceso("VALIDACION");//proceso
			smf09.setDescripcion("Proceso Submitido");//descripcion			
			smf09.setEstado_proceso("S");//estado
			smf09.setFecha_proceso(hfa.getFechaEstadoProceso());
			smf09.setHora_proceso(hfa.getHoraEstadoProceso());
			smf09.setUsuario_proceso(user);//nombr
			if (keyCargaAux) {
				//el periodo no ha sido ingresado en el estadoproceso, se ingresara a continuacion
				System.out.println("* * * * * [proceso validacion submitida, estado seteado]* * * * *");
				smf09MGR.setEstadoPeriodoByProceso(smf09);
			} else {
				//el periodo y proceso ya existe, solo actualizar.
				System.out.println("* * * * * [proceso validacion submitida, estado actualizado]* * * * *");
				smf09MGR.upEstadoPeriodoByProceso(smf09);
			}
			//ambos procesos,carga=T y validacion no estan en ejecucion actual.(!= S o E)
			//se procesde a ejecutar procedimiento almacenado.
			System.out.println("* * * * * [procesaPeriodos: callProcedureValidar]* * * * *");
			ProcedimientosMannager pm = new ProcedimientosMannager();
			/**comentado para test**/
			pm.callProcedureValidar(periodo);
		} else {
			System.out.println("* * * * * [proceso validacion no realizado]* * * * *");
		}
		//retorna estado del proceso luego de la llamada, para mostrar a usuario
		return (this.getEstadoProcesoValidacion(periodo));
	}//end callProcesoValidar2

	/*metodo que permite saber estado de proceso validacion en estado T.
	 * para poder ejecutar escritura de archivos
	 * */
	public boolean getEstadoValidacionExpress(String periodo) throws Exception {
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("VALIDACION");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		boolean key = false;
		if (smf09 != null) {
			//if(smf09.getEstado_proceso().equals("T") || smf09.getEstado_proceso().equals("C")){
			if (smf09.getEstado_proceso().equals("T")) {
				key = true;
			}
		} else {
			key = false;
		}
		return key;
	}

	/*metodo que permite saber estado de proceso CARGA en estado T.
	 * para poder ejecutar escritura de archivos
	 * */
	public boolean getEstadoCargaExpress(String periodo) throws Exception {
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("CARGA");
		smf09 = smf09MGR.getEstadoPeriodoByProceso(smf09);
		boolean key = false;
		if (smf09 != null) {
			//if(smf09.getEstado_proceso().equals("T") || smf09.getEstado_proceso().equals("C")){
			if (smf09.getEstado_proceso().equals("T")) {
				key = true;
			}
		} else {
			key = false;
		}
		return key;
	}

	public void delProcesoValidacion(String periodo) throws Exception {
		SMF09_VO smf09 = new SMF09_VO();
		SMF09_Mannager smf09MGR = new SMF09_Mannager();
		smf09.setFecha_periodo(periodo);
		smf09.setProceso("VALIDACION");
		smf09MGR.delEstadoPeriodoByProceso(smf09);
	}

}//end class