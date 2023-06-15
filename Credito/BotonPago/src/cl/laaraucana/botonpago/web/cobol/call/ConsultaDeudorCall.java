package cl.laaraucana.botonpago.web.cobol.call;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaDeudorVO;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.dao.DeudorNoAfiliadoDAO;
import cl.laaraucana.botonpago.web.utils.Util;

/**
 * Miembro Tipo Texto BPAGC002 CLP Consulta si rut es deudor
 */
public class ConsultaDeudorCall {

	public SalidaDeudorVO consultarDeudor(EntradaDeudorVO entrada) throws Exception {

		SalidaDeudorVO salidaDeudor = new SalidaDeudorVO();

		String entradaCobol = new String();
		String restoentrada = new String();

		restoentrada += Util.rellenarCampos(entrada.getEstCivil(), 1, " ");
		restoentrada += Util.rellenarCampos(entrada.getFono1(), 10, " ");
		restoentrada += Util.rellenarCampos(entrada.getFono2(), 10, " ");
		restoentrada += Util.rellenarCampos(entrada.getFono3(), 10, " ");
		restoentrada += Util.rellenarCampos(entrada.getEmail(), 50, " ");
		restoentrada += Util.rellenarCampos(entrada.getDireccion(), 60, " ");
		restoentrada += Util.rellenarCampos(entrada.getComuna(), 40, " ");
		restoentrada += Util.rellenarCampos(entrada.getProvincia(), 40, " ");
		restoentrada += Util.rellenarCampos(entrada.getRegion(), 40, " ");
		restoentrada += Util.rellenarCampos(entrada.getEnvEECC(), 1, " ");
		restoentrada += Util.rellenarCampos(" ", 28, " ");

		entradaCobol += Util.rellenarCampos(" ", 100, " ");
		entradaCobol += Util.rellenarCampos(entrada.getTipo(), 1, " ");
		entradaCobol += Util.rellenarCampos(entrada.getRut(), 9, "0");
		entradaCobol += Util.rellenarCampos(restoentrada, "Completa Entrada", 290, " ");
		entradaCobol = Util.rellenarCampos(entradaCobol, "Completa 4000", 4000, " ");
		
		System.out.println("entradaCobol>>>" + entradaCobol + "<<<");
		String respCobol = DeudorNoAfiliadoDAO.ejecutarProced(entradaCobol);
		System.out.println("SalidaCobol>>>" + respCobol + "<<<");

		int pos = 400; //Inicia en 400 porque se ignora tipo de transaccion
		salidaDeudor.setCodSalida(respCobol.substring(pos, pos += 3));
		salidaDeudor.setMsjSalida(respCobol.substring(pos, pos += 72).trim());
		salidaDeudor.setRutDeudor(respCobol.substring(pos, pos += 11).trim());
		salidaDeudor.setNombreDeudor(respCobol.substring(pos, pos += 20).trim());
		salidaDeudor.setAppDeu(respCobol.substring(pos, pos += 15));
		salidaDeudor.setApmDeu(respCobol.substring(pos, pos += 15));
		salidaDeudor.setSexoDeu(respCobol.substring(pos, pos += 1));
		salidaDeudor.setFechNac(respCobol.substring(pos, pos += 8));
		salidaDeudor.setEstCivil(respCobol.substring(pos, pos += 1).trim());
		salidaDeudor.setFono1(respCobol.substring(pos, pos += 10));
		salidaDeudor.setFono2(respCobol.substring(pos, pos += 10));
		salidaDeudor.setFono3(respCobol.substring(pos, pos += 10));
		salidaDeudor.setEmail(respCobol.substring(pos, pos += 50).trim());
		salidaDeudor.setDireccion(respCobol.substring(pos, pos += 60).trim());
		salidaDeudor.setComuna(respCobol.substring(pos, pos += 40).trim());
		salidaDeudor.setProvincia(respCobol.substring(pos, pos += 40).trim());
		salidaDeudor.setRegion(respCobol.substring(pos, pos += 40).trim());
		salidaDeudor.setEnvecta(respCobol.substring(pos, pos += 1));
		salidaDeudor.setFechaIngresoBD(respCobol.substring(pos, pos += 8));
		salidaDeudor.setUltFechaModBD(respCobol.substring(pos, pos += 8));
		salidaDeudor.setEstadoDeudor(respCobol.substring(pos, pos += 1));
		salidaDeudor.setEstadoInforme(respCobol.substring(pos, pos += 1));
		salidaDeudor.setEmailValid(respCobol.substring(pos, pos += 1));

		return salidaDeudor;

	}

}
