package cl.laaraucana.capaservicios.webservices.vo.DatosAuxiliares;

import java.util.List;
import cl.laaraucana.capaservicios.webservices.vo.Log;

public class ListaCodigosSTLOut {
	private List<CodigoVO> codigos;
	private Log log;

	public List<CodigoVO> getCodigos() {
		return codigos;
	}

	public void setCodigos(List<CodigoVO> codigos) {
		this.codigos = codigos;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
}
