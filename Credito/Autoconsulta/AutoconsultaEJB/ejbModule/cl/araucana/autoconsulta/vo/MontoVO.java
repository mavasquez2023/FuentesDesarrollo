package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Fabrizio Barisione
 *
 */
public class MontoVO implements Serializable {

	private long monto;
	/**
	 * @return
	 */
	public long getMonto() {
		return monto;
	}

	/**
	 * @param i
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}

}
