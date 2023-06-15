package cl.laaraucana.botonpago.web.manager;

import cl.laaraucana.botonpago.web.cobol.call.ConsultaDeudorCall;
import cl.laaraucana.botonpago.web.cobol.vo.EntradaDeudorVO;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class ManagerBPAGC002 {

	public SalidaDeudorVO consultaEsDeudorYGuarda(String rut) throws Exception {

		EntradaDeudorVO entrada = new EntradaDeudorVO();
		entrada.setRut(rut.split("-")[0]);
		entrada.setTipo(Constantes.getInstancia().PRM_BPAGC002_ESDEUDOR_SIG);

		ConsultaDeudorCall cblCall = new ConsultaDeudorCall();
		SalidaDeudorVO salida = cblCall.consultarDeudor(entrada);

		return salida;
	}

	public SalidaDeudorVO consultaEsDeudor(String rut) throws Exception {

		EntradaDeudorVO entrada = new EntradaDeudorVO();
		entrada.setTipo(Constantes.getInstancia().PRM_BPAGC002_ESDEUDOR_NOG);
		entrada.setRut(rut.split("-")[0]);

		ConsultaDeudorCall cblCall = new ConsultaDeudorCall();
		SalidaDeudorVO salida = cblCall.consultarDeudor(entrada);

		return salida;
	}

	public SalidaDeudorVO actializaDeudor(EntradaDeudorVO deudor) throws Exception {

		EntradaDeudorVO entrada = deudor;
		entrada.setTipo(Constantes.getInstancia().PRM_BPAGC002_ACTUALIZA);

		ConsultaDeudorCall cblCall = new ConsultaDeudorCall();
		SalidaDeudorVO salida = cblCall.consultarDeudor(entrada);

		return salida;
	}

	public boolean esCodigoNoAfiliadoMigrado(String codigoEntrada) throws Exception {
		boolean salida = false;
		String[] codigos = Constantes.getInstancia().COD_NOAFI_BPAGC002_MIG.split(";");
		for (String codigo : codigos) {
			if (codigo.equals(codigoEntrada)) {
				salida = true;
				break;
			}
		}
		return salida;
	}

	public boolean esCodigoNoAfiliadoNoMigrado(String codigoEntrada) throws Exception {
		boolean salida = false;
		String[] codigos = Constantes.getInstancia().COD_NOAFI_BPAGC002_NO_MIG.split(";");
		for (String codigo : codigos) {
			if (codigo.equals(codigoEntrada)) {
				salida = true;
				break;
			}
		}
		return salida;
	}

}
