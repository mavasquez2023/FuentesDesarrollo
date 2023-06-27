package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class DetalleCasoDescontadoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private String detalleDescripcion=null;
	private int detalleAporteSocio=0;
	
	public DetalleCasoDescontadoVO() {
	}
	
	/**
	 * Constructor de un DetalleCasoDescontadoVO a partir de un CasosDescontadosSinFormatoVO
	 * @param CasosDescontadosSinFormatoVO casoSinFormato
	 * @return DetalleCasoDescontadoVO
	 */
	public DetalleCasoDescontadoVO(CasosDescontadosSinFormatoVO sinFormato) {
		detalleDescripcion=sinFormato.getDetalleDescripcion();
		detalleAporteSocio=sinFormato.getDetalleAporteSocio();
	}

	/**
	 * @return
	 */
	public int getDetalleAporteSocio() {
		return detalleAporteSocio;
	}

	/**
	 * @return
	 */
	public String getDetalleDescripcion() {
		return detalleDescripcion;
	}

	/**
	 * @param i
	 */
	public void setDetalleAporteSocio(int i) {
		detalleAporteSocio = i;
	}

	/**
	 * @param string
	 */
	public void setDetalleDescripcion(String string) {
		detalleDescripcion = string;
	}

}
