package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;

import cl.araucana.personal.vo.DescuentoVO;
;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DescuentoTotalSocioVO implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private long codigoDescuento=0;
	private Date fecha=null;
	private String rut=null;
	private int montoDescuento=0;
	private String usuario = null;
	
	public DescuentoTotalSocioVO() {	
	}
	
	public DescuentoTotalSocioVO(DescuentoVO descuento) {
		StringTokenizer st = new StringTokenizer(descuento.getClCoFu(),"-");				
		rut=st.nextToken();
		montoDescuento=Integer.parseInt(descuento.getClValo());	
	}

	/**
	 * @return
	 */
	public long getCodigoDescuento() {
		return codigoDescuento;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public int getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setCodigoDescuento(long l) {
		codigoDescuento = l;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param i
	 */
	public void setMontoDescuento(int i) {
		montoDescuento = i;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

}
