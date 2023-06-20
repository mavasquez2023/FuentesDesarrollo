/**
 * 
 */
package cl.laaraucana.licenciascompinemp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author J-Factory
 *
 */
@Entity
@Table(name = "BalanceoDocumentosPendientesLM")
public class CorreoBalanceo  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "EMAILEJECUTIVO")
	private String email;
	@Column(name = "LICASIGNADAS")
	private int numlicasigna;
	@Column(name = "BALANCEO")
	private int balanceo;
	@Column(name = "FECHA")
	private Date fecha;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the numlicasigna
	 */
	public int getNumlicasigna() {
		return numlicasigna;
	}
	/**
	 * @param numlicasigna the numlicasigna to set
	 */
	public void setNumlicasigna(int numlicasigna) {
		this.numlicasigna = numlicasigna;
	}
	/**
	 * @return the balanceo
	 */
	public int getBalanceo() {
		return balanceo;
	}
	/**
	 * @param balanceo the balanceo to set
	 */
	public void setBalanceo(int balanceo) {
		this.balanceo = balanceo;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
