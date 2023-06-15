package cl.laaraucana.botonpago.web.manager;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;

public class ManagerDeudor {
	Logger logger = Logger.getLogger(this.getClass());

	public SalidaDeudorVO esDeudorNoAfiliadoSapYAs400(String rut) throws Exception {
		String rutDeudor = rut;
		// boolean esDeudor = false;
		SalidaDeudorVO salida;
		// String[] rutArray = rut.replaceAll("\\.", "").split("-");

		// Se verifica si es Deudor
		ManagerSAP managerSap = new ManagerSAP();
		logger.info("Consultando en SAP RUT: " + rutDeudor);
		salida = managerSap.esDeudorSapYpStatus(rutDeudor);
		/*
		 * if(cliente.isDeudor()){ ManagerBPAGC002 mgr = new ManagerBPAGC002(); salida =
		 * mgr.consultaEsDeudor(rutDeudor); salida.setDeudor(true); //esNoAfiliado =
		 * mgr.esCodigoNoAfiliadoMigrado(salida.getCodSalida());
		 * logger.debug("codigo de salida consulta deudor: " + salida.getCodSalida()); }
		 */
		// logger.debug("Es Deudor:" + esDeudor);

		return salida;
	}

	/*
	 * public SalidaDeudorVO esDeudorNoAfiliadoSapYAs400(String rut) throws
	 * Exception { String rutDeudor = rut; boolean esNoAfiliado = false; boolean
	 * esDeudor = false; SalidaDeudorVO salida = new SalidaDeudorVO(); String[]
	 * rutArray = rut.replace(".", "").split("-"); MigradoDAO migradoDao = new
	 * MigradoDAO(); boolean esMigrado = migradoDao.isMigradoByRut(rutArray[0],
	 * rutArray[1]); logger.debug("El rut " + rutDeudor + " migrado = " +
	 * esMigrado);
	 * 
	 * // migrados: 005 006 007 008 011 if (esMigrado) { ManagerBPAGC002 mgr = new
	 * ManagerBPAGC002(); salida = mgr.consultaEsDeudor(rutDeudor); esNoAfiliado =
	 * mgr.esCodigoNoAfiliadoMigrado(salida.getCodSalida());
	 * logger.debug("codigo de salida consulta deudor: " + salida.getCodSalida());
	 * logger.debug("es no afiliado =" + esNoAfiliado);
	 * 
	 * if (esNoAfiliado) { ManagerSAP managerSap = new ManagerSAP(); SalidaDeudor
	 * salidaDeudor = managerSap.tieneDeuda(rutDeudor); logger.debug("es deudor =" +
	 * salidaDeudor.getCodError()); if
	 * (Constantes.getInstancia().APP_COD_SUCCESS.equals(salidaDeudor.getCodError())
	 * ) { esDeudor = true; } else { esDeudor = false; } } } else { // no migrados:
	 * 005 // cuando es no migrado se valida el codigo 005 indica que // es deudor
	 * directo ManagerBPAGC002 mgr = new ManagerBPAGC002(); salida =
	 * mgr.consultaEsDeudor(rutDeudor); esNoAfiliado =
	 * mgr.esCodigoNoAfiliadoNoMigrado(salida.getCodSalida());
	 * 
	 * logger.debug("es no afiliado =" + esNoAfiliado);
	 * 
	 * if (esNoAfiliado) { esDeudor = true; logger.debug("es deudor =" + esDeudor);
	 * } } logger.debug("noafi:" + esNoAfiliado + " && deudor:" + esDeudor); if
	 * (esNoAfiliado && esDeudor) { salida.setDudorNoAfiliado(true); } else {
	 * salida.setDudorNoAfiliado(false); } return salida; }
	 */

	public Bpagf001 mapeoDeudor(SalidaDeudorVO deudor) {
		Bpagf001 salida = new Bpagf001();

		salida.setRutdeu(deudor.getRutDeudor().split("-")[0]);
		salida.setDvdeu(deudor.getRutDeudor().split("-")[1]);
		// String nombre= deudor.getNombreDeudor();
		salida.setNomdeu(setLargo(deudor.getNombreDeudor(), 20));
		salida.setAppdeu(setLargo(deudor.getAppDeu(), 15));
		salida.setAppmdeu(setLargo(deudor.getApmDeu(), 15));
		salida.setSexodeu(deudor.getSexoDeu());
		salida.setFecnac(deudor.getFechNac());
		salida.setEstcivil(deudor.getEstCivil());
		salida.setFono1(deudor.getFono1());
		salida.setFono2(deudor.getFono2());
		salida.setFono3(deudor.getFono3());
		salida.setEmail(setLargo(deudor.getEmail(), 50));
		salida.setDirdeu(setLargo(deudor.getDireccion(), 60));
		salida.setComudeu(deudor.getComuna());
		salida.setProvdeu(deudor.getProvincia());
		salida.setRegdeu(deudor.getRegion());
		salida.setEnvecta(deudor.getEnvecta());
		salida.setFecing(deudor.getFechaIngresoBD());
		salida.setFecmod(null);
		salida.setEstdeu(deudor.getEstadoDeudor());
		salida.setEstinf(deudor.getEstadoInforme());
		salida.setEmailval(deudor.getEmailValid());
		return salida;
	}

	public String setLargo(String data, int largo) {
		String salida = data;
		if (data.length() > largo) {
			salida = data.substring(0, largo);
		}
		return salida;
	}
}
